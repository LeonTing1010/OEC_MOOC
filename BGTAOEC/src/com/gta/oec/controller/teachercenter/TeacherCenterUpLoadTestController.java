/**
 * TeacherCenterUpdateController.java	  V1.0   2014-1-22 上午9:52:41
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.teachercenter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.io.*;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gta.oec.common.web.springmvc.MessageResolver;
import com.gta.oec.common.web.upload.FileRepository;
import com.gta.oec.common.web.upload.UploadUtils;
import com.gta.oec.common.web.utils.FileUtils;
import com.gta.oec.common.web.utils.RequestUtils;
import com.gta.oec.common.web.utils.ResponseUtils;
import com.gta.oec.common.web.utils.SiteUtils;
import com.gta.oec.enums.CourseStateEnum;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.LoginException;
import com.gta.oec.service.course.CourseService;
import com.gta.oec.service.exam.ExamService;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.course.SectionVO;
import com.gta.oec.vo.exam.ExamOptionVo;
import com.gta.oec.vo.exam.ExamPaperVo;
import com.gta.oec.vo.exam.ExamQuestionVo;
import com.gta.oec.vo.exam.ExamVo;
import com.gta.oec.vo.user.UserVo;

/**
 * 
 * 功能描述：教师中心-修改课程控制层
 * 
 * @author bingzhong.qin
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Controller
public class TeacherCenterUpLoadTestController {
	private static final Log logger = LogFactory.getLog(TeacherCenterUpLoadTestController.class);
	/**
	 * 
	 * 功能描述：【-发布试题页面-】
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-10 下午3:01:06</p>
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 * @throws LoginException 
	 */
	@RequestMapping("/teacherCenter/releasetest/")
	public String releasetest(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws LoginException{

		UserVo userVo =  SiteUtils.checkTeacher(request);
		List<CourseVo> courselist = new ArrayList<CourseVo>();
		String type=RequestUtils.getQueryParam(request, "type");
		CourseVo courseVo = new CourseVo();
		//已发布的课程2 才能发布试题
		courseVo.setStatus(CourseStateEnum.PUBLISHED.getValue());
		courseVo.setUserId(userVo.getUserId());
		try {
			courselist = courseService.getCourseList(courseVo);
		} catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}

		modelMap.put("courselist", courselist);
		modelMap.put("type", type);
		return "teacenter/releasetest/releaseTest.htm";
	}
/**
 * 
 * 功能描述：【-Excel试题上传-】
 *
 * @author  jin.zhang
 * <p>创建日期 ：2014-2-11 下午3:51:04</p>
 *
 * @param request
 * @param response
 * @param modelMap
 * @return
 * @throws LoginException
 *
 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
 */
	@RequestMapping(value = "/teacherCenter/upload/", method = RequestMethod.POST)
	public void upload(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap)
			throws LoginException {
		
		try {
			    MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
	            MultipartFile file = mhs.getFile("file");
	            //得到文件名   
	            String soursefilename=file.getOriginalFilename();           
	            
	            String excelRoot = MessageResolver.getMessage(mhs, "excelRoot", null);
				String newfileName = fileRepository.storeByFilename(UploadUtils.generateFilename(excelRoot,FileUtils.getFileType(soursefilename)), file);

				response.setHeader("Charset","UTF-8");  
	            response.setContentType("text/html;charset=UTF-8");  
	            PrintWriter out = null;  
	            out = response.getWriter();  
	            String json="";  
	            json="{soursefilename:\""+soursefilename+"\",newfileName:\""+newfileName+"\"}";  
	            out.print(json);  
	            out.flush();  
	            out.close();  
	            
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			logger.error(e); e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e); e.printStackTrace();
		} 

	}
	
	/**
	 * 
	 * 功能描述：【--检查课程对应的考试是否已经存在--】
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-25 上午9:24:21</p>
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value = "/teacherCenter/checkExamIsExist/", method = RequestMethod.POST)
	public void checkExamIsExist(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap)
			throws IOException, NumberFormatException, BaseException {
	
		try {
			String courseId = RequestUtils.getQueryParam(request, "courseId");
			if(!StringUtils.isBlank(courseId)){
				
				List<?> list = examService.getExamListByCourId(Integer.valueOf(courseId), 0, 1);
				JSONObject json  = new JSONObject();
			    if(list!=null&&list.size()>0){
			  	    json.put("result", 1);  //课程考试已经存在
			    }else{
			    	json.put("result", 2);  //课程考试不存在
			    }
			    ResponseUtils.renderJson(response, json.toString());	
			}
		
		}catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
	
	}
	
	
	/**
	 * 
	 * 功能描述：检查课程章节下的练习是否存在
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-3-6 下午6:46:56</p>
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value = "/teacherCenter/checkPracticeIsExist/", method = RequestMethod.POST)
	public void checkPracticeIsExist(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap)
			throws IOException, NumberFormatException, BaseException {
	
		try {
			String courseId = RequestUtils.getQueryParam(request, "courseId");
			String toJ = RequestUtils.getQueryParam(request, "toJ");
			if(!StringUtils.isBlank(courseId)){
				
				List<?> list = examService.getExamListByCourId(Integer.valueOf(courseId), Integer.valueOf(toJ), 2);
				JSONObject json  = new JSONObject();
			    if(list!=null&&list.size()>0){
			  	    json.put("result", 1);  //练习已经存在
			    }else{
			    	json.put("result", 2);  //练习不存在
			    }
			    ResponseUtils.renderJson(response, json.toString());	
			}
		
		}catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
	
	}
	
	

	/**
	 * 
	 * 功能描述：【-验证excel试题的数据格式和内容是否正确-】
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-10 下午4:17:29</p>
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException 
	 * @throws BaseException 
	 * @throws ParseException 
	 */
	@RequestMapping("/teacherCenter/checkExcelIsRight/")
	public void checkExcelIsRight(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException, BaseException {

		//试题类型（ 1：课堂考试；2：课堂练习；3：课堂作业）
		String examType = request.getParameter("examType");
		String tempExcel = request.getParameter("tempExcel");
		if(tempExcel.equals("undefined")){
			 JSONObject json  = new JSONObject();
			 json.put("message", "您上传的文件为空!");
			  ResponseUtils.renderJson(response, json.toString());	
			  return;
		}
		ServletContext servletContext = request.getSession().getServletContext(); 
		String filePath = servletContext.getRealPath(tempExcel);
		if(filePath==null||filePath==""){
			 JSONObject json  = new JSONObject();
			 json.put("message", "您上传的文件为空!");
			  ResponseUtils.renderJson(response, json.toString());	
			  return;
		}
       
	try {	
		InputStream input = new FileInputStream(filePath);  //建立输入流  
		Workbook workbook = new XSSFWorkbook(input);  
		Sheet sheet = workbook.getSheetAt(0);     //获得第一个表单  
		//获取总行数
		int rowCount = sheet.getLastRowNum();
		if (rowCount == 0) {
		    JSONObject json  = new JSONObject();
		    json.put("message", "您上传的文件内容为空!");
		    ResponseUtils.renderJson(response, json.toString());	
		    return;
		 } 
		for(int i=0;i<rowCount;i++){
			Row firstrow=sheet.getRow(i);
			//获取总列数
			int columnNum=firstrow.getPhysicalNumberOfCells();
			if (columnNum<9) {
			    JSONObject json  = new JSONObject();
			    json.put("message", "上传的文件有格式错误,请参考模板!");
			    ResponseUtils.renderJson(response, json.toString());	
			    return;
			 } 
		}
		
		 Double selectScore = 0.0;
		 Double askScore = 0.0;
		 
		 Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器 
		 while (rows.hasNext()) {  
	        	Row row = rows.next();  //获得行数据  
	            if(row.getRowNum()>0){ //获得行号从1开始(第二行开始)  
	            Iterator<Cell> cells = row.cellIterator();    //获得第二行的迭代器  
                   while (cells.hasNext()) {  
	                Cell cell = cells.next();  
	                int index = cell.getColumnIndex();
	                if(index==1){   //题目类型
	                	if(cell.getCellType()!=1){
	                		JSONObject json  = new JSONObject();
	            		    json.put("message", "上传的文件有格式错误,题目类型只能填单选题或者多选题,请参考模板!");
	            		    ResponseUtils.renderJson(response, json.toString());
	            		    return;
	                	}else{
	                		String strType = cell.getStringCellValue(); //题目类型
		                	if(!strType.equals("单选题")&&!strType.equals("多选题")){
		                		JSONObject json  = new JSONObject();
		            		    json.put("message", "上传的文件有格式错误,题目类型只能填单选题或者多选题,请参考模板!");
		            		    ResponseUtils.renderJson(response, json.toString());	
		            		    return;
		               		}
	                	}
	                }
	                if(index==2){
	                	if(cell.getCellType()!=1){
	                		JSONObject json  = new JSONObject();
	            		    json.put("message", "上传的文件有格式错误!");
	            		    ResponseUtils.renderJson(response, json.toString());
	            		    return;
	                	}
	                }
	                if(index==8){  //分数
	                	if(cell.getCellType()!=0){ //如果不是整型
	                		JSONObject json  = new JSONObject();
	            		    json.put("message", "excel上传的试题分数必须为整数!");
	            		    ResponseUtils.renderJson(response, json.toString());
	            		    return;
	                	}else{
	                		Double score = cell.getNumericCellValue(); //分数
	                		selectScore = selectScore+score;
	                	}
	                 }
                   }
	            }
		 }
		/*****-课堂练习只有选择题分数-**********/
		if(examType.equals("2")&&selectScore>100){
			JSONObject json  = new JSONObject();
			json.put("message", "分数总分不能大于100!");
			ResponseUtils.renderJson(response, json.toString());
			return;
		}
		/*****-课堂考试包含选择题和主观题分数-******/
		else if(examType.equals("1")){
				//问答题分数集合
	 		   String[] quesScores = RequestUtils.getQueryParamValues(request, "examQuesScore");
	 		   if(quesScores!=null&&quesScores.length>0){
	 			   for(int i=0;i<quesScores.length;i++){
	 				  askScore = askScore +Double.valueOf(quesScores[i]);
	 			   }
	 		   }
	 		   if((selectScore+askScore)>100){
	 			    JSONObject json  = new JSONObject();
	 				json.put("message", "分数总分不能大于100!");
	 				ResponseUtils.renderJson(response, json.toString());
	 				return;
	 		   }
		}
		/*****-课堂作业包含选择题和主观题分数-******/
		else if(examType.equals("3")){
				//问答题分数集合
	 		   String[] quesScores = RequestUtils.getQueryParamValues(request, "taskQuesScore");
	 		   if(quesScores!=null&&quesScores.length>0){
	 			   for(int i=0;i<quesScores.length;i++){
	 				  askScore = askScore +Double.valueOf(quesScores[i]);
	 			   }
	 		   }
	 		   if((selectScore+askScore)>100){
	 			    JSONObject json  = new JSONObject();
	 				json.put("message", "分数总分不能大于100!");
	 				ResponseUtils.renderJson(response, json.toString());
	 				return;
	 		   }
		}
		JSONObject json  = new JSONObject();
		json.put("message", "验证成功");
		ResponseUtils.renderJson(response, json.toString());
		
		 } catch (IOException e) {
		   logger.error(e); e.printStackTrace();
	   }
        
	}
	
	/**
	 * 
	 * 功能描述：【-保存发布的试题-】
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-10 下午4:17:29</p>
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException 
	 * @throws BaseException 
	 * @throws ParseException 
	 */
	@RequestMapping("/teacherCenter/releasetestSave/")
	public void releasetestSave(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException, BaseException, ParseException{

	try {
		UserVo userVo =  SiteUtils.checkTeacher(request);
		ExamVo examVo = new ExamVo();
		ExamPaperVo examPaperVo = new ExamPaperVo();
		
		ExamOptionVo examOption = new ExamOptionVo();
	 
		//试题类型（ 1：课堂考试；2：课堂练习；3：课堂作业；4：认证考试）
		String examType = request.getParameter("examType");
		String courseId = request.getParameter("courseId");//课程ID
		
		CourseVo course = courseService.getCourseById(Integer.valueOf(courseId));
		
		Date date = new Date();
		int quesType = 0;
		int paperId = 0;
		int quesId = 0;
		
		String paperName = null;
		if(examType.equals("1")){
			 paperName = course.getCourseName()+" 考试";
		}else if(examType.equals("2")){
			
			String toZ = request.getParameter("toZ");//课程章ID
			String toJ = request.getParameter("toJ");//课程节ID
			SectionVO sectionZ = courseService.getSectionById(Integer.valueOf(toZ));
			SectionVO sectionJ = courseService.getSectionById(Integer.valueOf(toJ));
			
			paperName = course.getCourseName()+" "+sectionZ.getName()+" "+sectionJ.getName()+" 练习";
			examVo.setSecId(Integer.valueOf(toJ)); //课程章节
		
		}else if(examType.equals("3")){
			   paperName = request.getParameter("taskName"); //作业名称
		}
		/********************* 1.生成试卷************************************/
		 examPaperVo.setPaperName(paperName);
    	 examPaperVo.setUserId(userVo.getUserId()); //创建试题人ID
    	 examPaperVo.setPaperCtime(date);   //创建时间
    	 
    	 ExamPaperVo examPaper = examService.insertExamPaper(examPaperVo);
    	 paperId = examPaper.getPaperId();  //生成的试卷id
		
    	if(!StringUtils.isBlank(courseId)&&!StringUtils.isBlank(examType)) {
    		ExamQuestionVo examQuestion = new ExamQuestionVo();
    	   /***************************--保存考试主观题--***********************************/
    	   if(examType.equals("1")){
    		  
    		   //问答题题目数组集合
    		   String[] quesContents = RequestUtils.getQueryParamValues(request, "examQuesContent");
    		   //问答题答案数组集合
    		   String[] quesAnswers = RequestUtils.getQueryParamValues(request, "examQuesAnswer");
    		   //问答题分数集合
    		   String[] quesScores = RequestUtils.getQueryParamValues(request, "examQuesScore");
    		  
    		   if(quesContents!=null&&quesContents.length>0){
	    		
	    		   for(int i=0;i<quesContents.length;i++){	
	                    String quesContent = quesContents[i];
	                    String quesAnswer = quesAnswers[i];
	                    String quesScore = quesScores[i];
		              
	                    examQuestion.setPaperId(paperId); 
		            	examQuestion.setExamQuesContent(quesContent); //问答题题目
		            	examQuestion.setExamQuesAnswer(quesAnswer);  //问答题答案
		            	examQuestion.setExamQuesType(4); //试题类型4 问答题
		            	examQuestion.setExamQuesScore(Double.valueOf(quesScore)); //问答题分数
		            	examQuestion.setExamQuesCtime(date); //试题创建时间
		        	    examService.insertExamQuestion(examQuestion);
	               }

    		   }
    	 
    	   }
    	   /***************************--保存作业主观题--***********************************/
    	   else if(examType.equals("3")){ 
    		 
    		   String examLastTime = request.getParameter("taskLastTime"); //作业提交截止时间
    		   String examInstruction = request.getParameter("examInstruction"); //试题说明
    		 
    		   if(examLastTime!=null&&examLastTime.length()>0){
    			   DateFormat format = new SimpleDateFormat("yyyy-MM-dd");        
        		   Date examLastdate = format.parse(examLastTime);
        		   examVo.setExamLastTime(examLastdate);  //作业、考试提交截止时间
    		   }
    		       examVo.setExamInstruction(examInstruction);
    		   //问答题题目数组集合
    		   String[] quesContents = RequestUtils.getQueryParamValues(request, "taskQuesContent");
    		   //问答题答案数组集合
    		   String[] quesAnswers = RequestUtils.getQueryParamValues(request, "taskQuesAnswer");
    		   //问答题分数集合
    		   String[] quesScores = RequestUtils.getQueryParamValues(request, "taskQuesScore");
    		  
    		   if(quesContents!=null&&quesContents.length>0){
	    		   for(int i=0;i<quesContents.length;i++){	
	                    String quesContent = quesContents[i];
	                    String quesAnswer = quesAnswers[i];
	                    String quesScore = quesScores[i];
		              
	                    examQuestion.setPaperId(paperId); 
		            	examQuestion.setExamQuesContent(quesContent); //问答题题目
		            	examQuestion.setExamQuesAnswer(quesAnswer);  //问答题答案
		            	examQuestion.setExamQuesType(4); //试题类型4 问答题
		            	examQuestion.setExamQuesScore(Double.valueOf(quesScore)); //问答题分数
		            	examQuestion.setExamQuesCtime(date); //试题创建时间
		        	    examQuestion = examService.insertExamQuestion(examQuestion);
	               }	
    	      }
    	   }
    	   examVo.setCourId(Integer.valueOf(courseId)); //课程ID
    	   examVo.setExamName(paperName);  //试卷名称
    	   examVo.setPaperId(paperId);   //试卷ID
    	   examVo.setExamType(Integer.valueOf(examType)); //试题类型
    	   examVo.setTeacherId(userVo.getUserId());  //老师ID
    	   examVo.setExamCtime(date);  //创建时间
    	   examService.insertExam(examVo);
    	}
  	      
		String[] tempExcels = null;
		if(examType.equals("1")){
			tempExcels = RequestUtils.getQueryParamValues(request, "ksTempExcel");
		}else if(examType.equals("2")){
			tempExcels = RequestUtils.getQueryParamValues(request, "lxTempExcel");
		}else if(examType.equals("3")){
			tempExcels = RequestUtils.getQueryParamValues(request, "zyTempExcel");
		}
		
	/***************************--解析Excel保存选择题--****************************************/
	if(tempExcels!=null&&tempExcels.length>0){
	   for(int i=0;i<tempExcels.length;i++){
		
		ServletContext servletContext = request.getSession().getServletContext(); 
		String filePath = servletContext.getRealPath(tempExcels[i]);
		
		InputStream input = new FileInputStream(filePath);  //建立输入流  
        Workbook wb = new XSSFWorkbook(input);  
        Sheet sheet = wb.getSheetAt(0);     //获得第一个表单  
        Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器 
        while (rows.hasNext()) {  
        	
        	Row row = rows.next();  //获得行数据  
            if(row.getRowNum()>0){ //获得行号从1开始(第二行开始)  
            Iterator<Cell> cells = row.cellIterator();    //获得第二行的迭代器  
            while (cells.hasNext()) {  
            	
                Cell cell = cells.next();  
                int index = cell.getColumnIndex();
                if(index==0){
                	cell.getNumericCellValue(); //题目序号
                }
                else if(index==1){
                	try {
                		String strType = cell.getStringCellValue(); //题目类型
                		if(strType.equals("单选题")){
                    		quesType = 1; 
                    	}else if(strType.equals("多选题")){
                    		quesType = 2; 
                    	}
					} catch (Exception e) {
						logger.error(e); e.printStackTrace();
					}
                }
                else if(index==2){
                	String quesContent = cell.getStringCellValue(); //题目内容
                	
                	if(quesContent!=null&&quesContent.length()>0){
                		ExamQuestionVo examQuestion = new ExamQuestionVo();
                    	/**--保存试题-- **/
                    	examQuestion.setPaperId(paperId); 
                    	examQuestion.setExamQuesContent(quesContent); //试题内容
                    	examQuestion.setExamQuesType(quesType); //试题类型
                    	examQuestion.setExamQuesCtime(date); //试题创建时间
                    	examQuestion = examService.insertExamQuestion(examQuestion);
                	    quesId = examQuestion.getExamQuesId();
                	}
                }
                else if(index==3){
                	String optContent = cell.getStringCellValue(); //选择A
                	
                	if(optContent!=null&&optContent.length()>0){
                		/**--保存试题项A-- **/
                    	examOption.setQuesId(quesId); //试题id
                    	examOption.setOptNum("A");
                    	examOption.setOptCtime(date); //试题项创建时间
                    	examOption.setOptContent(optContent); //试题项内容
                    	examService.insertExamOption(examOption);
                	}
                }
                else if(index==4){
                	String optContent = cell.getStringCellValue(); //选择B
                	
                	if(optContent!=null&&optContent.length()>0){
                	/**--保存试题项B-- **/
                	examOption.setQuesId(quesId); //试题id
                	examOption.setOptNum("B");
                	examOption.setOptCtime(date); //试题项创建时间
                	examOption.setOptContent(optContent);//试题项内容
                	examService.insertExamOption(examOption);
                	}
                }
                else if(index==5){
                	String optContent = cell.getStringCellValue(); //选择C
                	
                	if(optContent!=null&&optContent.length()>0){
                	/**--保存试题项C-- **/
                	examOption.setQuesId(quesId); //试题id
                	examOption.setOptNum("C");
                	examOption.setOptCtime(date); //试题项创建时间
                	examOption.setOptContent(optContent);//试题项内容
                	examService.insertExamOption(examOption);
                	}
                }
                else if(index==6){
                    String optContent = cell.getStringCellValue(); //选择D
                	
                    if(optContent!=null&&optContent.length()>0){
                	/**--保存试题项D-- **/
                	examOption.setQuesId(quesId); //试题id
                	examOption.setOptNum("D");
                	examOption.setOptCtime(date); //试题项创建时间
                	examOption.setOptContent(optContent);//试题项内容
                	examService.insertExamOption(examOption);
                    }
                }
                else if(index==7){
                	String examQuesAnswer = cell.getStringCellValue(); //答案
                	
                	if(examQuesAnswer!=null&&examQuesAnswer.length()>0){
                	ExamQuestionVo examQuestionVo = examService.selectExamQuestionById(quesId);
                	examQuestionVo.setExamQuesAnswer(examQuesAnswer);
                	
                	examService.update(examQuestionVo);
                	}
                }
                else if(index==8){
                	Double score = cell.getNumericCellValue(); //分数
                	
                	ExamQuestionVo examQuestionVo = examService.selectExamQuestionById(quesId);
                	examQuestionVo.setExamQuesScore(score);
                	
                	examService.update(examQuestionVo);
                }
               }
             }
            }    
           }
         }
		}catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}

	}
	
	/**
	 * 
	 * 功能描述：【-获取课程下的章-】
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-17 下午2:27:11</p>
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 * @throws BaseException 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value = "/teacherCenter/getSectionZ/", method = RequestMethod.POST)
	public void getSectionZ(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap)
			throws IOException, NumberFormatException, BaseException {
	
	try {
			String courseId = RequestUtils.getQueryParam(request, "courseId");
			
			int secPid = Integer.valueOf(courseId);
			int secId= 0;
			//查询课程章集合(课程id和父章节id相等时查的是章)
			List<SectionVO> sectionlist = courseService.getSectionList(Integer.valueOf(courseId),secPid,secId);
			// 取集合
			JSONArray jsonArray = JSONArray.fromObject(sectionlist);
			JSONObject jsobjcet = new JSONObject();
			jsobjcet.put("data", jsonArray);

			response.setHeader("Charset","UTF-8");  
	        response.setContentType("text/html;charset=UTF-8");  
	        PrintWriter out = null;  
	        out = response.getWriter();  
	        out.print(jsobjcet);  
	        out.flush();  
	        out.close();  
        
		}catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}

	}
	
	/**
	 * 
	 * 功能描述：【-获取课程下的节-】
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-17 下午2:27:31</p>
	 *
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 * @throws BaseException 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value = "/teacherCenter/getSectionJ/", method = RequestMethod.POST)
	public void getSectionJ(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap)
			throws IOException, NumberFormatException, BaseException {
	
	try {
			String courseId = RequestUtils.getQueryParam(request, "courseId");
			String secId = RequestUtils.getQueryParam(request, "secId");
			//查询课程章下面的节集合
			List<SectionVO> sectionlist = courseService.getSectionList(Integer.valueOf(courseId),Integer.valueOf(secId),0);
			// 取集合
			JSONArray jsonArray = JSONArray.fromObject(sectionlist);
			JSONObject jsobjcet = new JSONObject();
			jsobjcet.put("data", jsonArray);
			
			response.setHeader("Charset","UTF-8");  
	        response.setContentType("text/html;charset=UTF-8");  
	        PrintWriter out = null;  
	        out = response.getWriter();  
	        out.print(jsobjcet);  
	        out.flush();  
	        out.close();  
	        
		}catch (BaseException e) {
			logger.error(e); e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * 功能描述：文件下载（支持在线打开文件的一种方式）
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-2-28 下午4:12:03</p>
	 *
	 * @param filePath
	 * @param response
	 * @param isOnLine
	 * @throws Exception
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping(value = "/teacherCenter/download/", method = RequestMethod.GET)
	public void download(String filePath, HttpServletRequest request,HttpServletResponse response,  boolean isOnLine) throws Exception {
		
		//获取excel模板的文件名
		String excelName = RequestUtils.getQueryParam(request, "excelName");
		
		ServletContext servletContext = request.getSession().getServletContext(); 
		String filePathas = servletContext.getRealPath("template/"+excelName+"");
        File f = new File(filePathas);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 非常重要
        if (isOnLine) { // 在线打开方式
            URL u = new URL("file:///" + filePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", "inline; filename=" + java.net.URLEncoder.encode(f.getName().replace("questions", "批量导入题目模板"), "UTF-8"));
            // 文件名应该编码成UTF-8
        } else { // 纯下载方式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(f.getName().replace("questions", "批量导入题目模板"), "UTF-8"));
        }
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }

	@Autowired
	private ExamService examService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private FileRepository fileRepository;
}
