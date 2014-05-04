package com.gta.oec.cms.controller.profession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gta.oec.cms.common.ApplicationPropertiesUtil;
import com.gta.oec.cms.common.pagination.PageModel;
import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.controller.BaseController;
import com.gta.oec.cms.service.profession.IProfessionService;
import com.gta.oec.cms.util.FileRepository;
import com.gta.oec.cms.util.ResponseUtils;
import com.gta.oec.cms.util.SystemConstant;
import com.gta.oec.cms.util.VeDate;
import com.gta.oec.cms.util.WebUtils;
import com.gta.oec.cms.vo.profession.Profession;

@Controller
@RequestMapping("/pro")
public class ProfessionController extends BaseController<Profession> {
	
private static Logger log = Logger.getLogger(ProfessionController.class);

private String ctxPath = ApplicationPropertiesUtil.getStringValue("ResourcesRootDir");  //取出资源文件内的远程服务器路径

@Autowired 
private FileRepository fileRepository; 
	
@Autowired
private IProfessionService proService;
	
	
@RequestMapping(value="/searchAJProfession")
public String searchAJProfession(HttpServletRequest request,HttpServletResponse response){
	log.info("enter the ProfessionController and the execute the findProfession method! ");
	request.setAttribute("page", this.gson.toJson(new PageModel<Profession>()));
	return "profession/profession.jsp";
}
	
	
@RequestMapping(value="/findProfession")
public void findProfession(Profession profession,HttpServletRequest request,HttpServletResponse response) throws Exception{
	//用于分页
	PaginationContext<Profession> pc = initPaginationInfo(new PaginationContext<Profession>(), request);
	try{
				List<Profession> list = proService.findProfessionsPageQuery(pc);    //需要分页的方法名 必须加上PageQuery 
				pc.setResult(list);
				Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
		        jsonMap.put("total", pc.getTotalSize());//total键 存放总记录数，必须的  
		        jsonMap.put("rows", pc.getResult());//rows键 存放每页记录 list  
				ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
				
				 
		 }catch(Exception e){
			 e.printStackTrace();
			 throw  e;
		 }
	}

@RequestMapping(value = "/findName")      //产生查询的下拉框
public void findName(HttpServletRequest  request,HttpServletResponse response)throws Exception{
	try{
		//PaginationContext<Profession> pc = initPaginationInfo(new PaginationContext<Profession>(), request);
		List<Profession> nameList= proService.getAllProfessions();    
		Profession pro = new Profession();
		pro.setProID(0);
		pro.setProName("请选择");
		nameList.add(pro);
		Profession firstPro = nameList.get(0);
		nameList.set(nameList.size()-1, firstPro);
		nameList.set(0, pro);
		ResponseUtils.renderJson(response, this.gson.toJson(nameList));
		
		}catch(Exception e){
		 	e.printStackTrace();
	}
	
}

//修改
@RequestMapping(value="updateProfession")   //根据职业名更新行业图片
public void updateProfession(HttpServletRequest request,HttpServletResponse response)throws Exception{
	Profession pro = new Profession();
	//System.out.println("S"+request.getParameter("proID")+"UUUUUUUUUUUUUUUUUUUUUUUUUUUUU"+"/"+SystemConstant.proPATH+request.getParameter("proImage"));
	pro.setProID(Integer.parseInt(request.getParameter("proID")));
	String[]  img = request.getParameterValues("proImage");
	//System.out.println(img+"IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII"+img.length());
	//img[0] 上传的图片路径+名称    img[1]  数据库里的图片路径+名称
	if(!img[0].equals(img[1])){
		WebUtils.delUserImg( img[1],ctxPath);
	}
	pro.setProImage(img[0]);                               //如果没有上传新的图片就不改变
	Map<String, Object> jsonMap = new HashMap<String, Object>();
	pro.setProUtime(VeDate.getNow());
	try {
		proService.updateProfessions(pro);
		jsonMap.put("success", true);
	} catch (Exception e) {
		log.error("修改失败", e);
		jsonMap.put("success",false );
		jsonMap.put("errorMsg", e.getMessage());
	}
	ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
}


//新增
@RequestMapping(value="addProfession")
public void addProfession(HttpServletRequest request,HttpServletResponse response)throws Exception{
	Profession pro = new Profession();
	//System.out.println("S"+request.getParameter("proName")+"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+"/"+SystemConstant.proPATH+request.getParameter("proImage"));
	pro.setProID(Integer.parseInt(request.getParameter("proName")));
	//img[0] 上传的图片路径+名称    img[1]  数据库里的图片路径+名称
	String[]  img = request.getParameterValues("proImage");     //proImage2的值取不到 所以没有删除
	if(!img[0].equals(img[1])){
		WebUtils.delUserImg( img[1],ctxPath);
	}
	pro.setProImage(img[0]); 
	Map<String, Object> jsonMap = new HashMap<String, Object>();
	pro.setProUtime(VeDate.getNow());
	try {
		proService.updateProfessions(pro);   
		jsonMap.put("success", true);
	} catch (Exception e) {
		log.error("新增失败", e);
		jsonMap.put("success",false );
		jsonMap.put("errorMsg", e.getMessage());
	}
	ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
}

@RequestMapping(value = "/updateProfessionImg",method = RequestMethod.POST)
public void updateProfessionImg(HttpServletRequest  request,HttpServletResponse response) throws Exception{
	Map<String, Object> jsonMap = new HashMap<String, Object>();
	//System.out.println("++++++++++++++++++++++++++++++++++++++++");
	//String ctxPath = super.findCtxPath(request, SystemConstant.proPATH);
	try{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile userFile=multipartRequest.getFile("proFile");
		//有图片就删除
		String fileNamed=request.getParameter("fileNamed");
		String fileNamed2=request.getParameter("fileNamed2");
		//fileNamed = fileNamed.substring(15);

	 	if(null !=fileNamed && !fileNamed.equals("")&&!fileNamed.equals(fileNamed2)){
	 		//System.out.println("1路径："+ctxPath+ "---/" +fileNamed);
	 		WebUtils.delUserImg( fileNamed,ctxPath);
	 	}
	 	jsonMap = WebUtils.uploadImg(userFile, ctxPath+SystemConstant.proPATH);         //上传图片到本地服务器
	 	jsonMap.put("urlPath", SystemConstant.proPATH);
	}catch (Exception e) {
		jsonMap.put("userFileName",null);
		log.error("上传图片失败", e);
		e.printStackTrace();
	}
	ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
}
@RequestMapping(value = "/delProImg")
public void delProImg(HttpServletRequest  request,HttpServletResponse response)throws Exception{
	Map<String,Object> jsonMap=new  HashMap<String,Object>();
	String ctxPath = ApplicationPropertiesUtil.getStringValue("ResourcesRootDir")+SystemConstant.proPATH;     //拼凑删除的图片路径
	try{
		String fileNamed=request.getParameter("fileName");
		if(fileNamed.length()>25){
			fileNamed = fileNamed.substring(15);
		}
		//System.out.println("2路径："+ctxPath+ "---/" +fileNamed);
		String message=WebUtils.delUserImg(fileNamed,ctxPath);
		jsonMap.put("message", message);
		jsonMap.put("success", true);
	}catch (Exception e) {
		jsonMap.put("success", false);
		jsonMap.put("errorMsg", e.getMessage());
		log.error("删除失败", e);
		e.printStackTrace();
	}
	ResponseUtils.renderJson(response, this.gson.toJson(jsonMap));
}

}
