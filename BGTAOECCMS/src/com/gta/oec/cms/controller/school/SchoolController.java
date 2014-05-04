/**
 * SchoolController.java	  V1.0   2014-3-21 上午11:10:41
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.cms.controller.school;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.gta.oec.cms.common.pagination.PageModel;
import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.controller.BaseController;
import com.gta.oec.cms.service.school.SchoolService;
import com.gta.oec.cms.util.ResponseUtils;
import com.gta.oec.cms.util.SystemConstant;
import com.gta.oec.cms.util.WebUtils;
import com.gta.oec.cms.vo.school.School;
import com.gta.oec.cms.util.VeDate;

@Controller
@RequestMapping("/school")
public class SchoolController extends BaseController<School> {
	
	
	
	@RequestMapping(value = "/findSchool")
	public String findSchool(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("enter the SchoolController and the execute the findSchool method! ");
		request.setAttribute("page", this.gson.toJson(new PageModel<School>()));
		return "school/schoolList.jsp";
	}

	@RequestMapping(value = "/schoolPageList")
	public void schoolPageList(HttpServletRequest request,
			HttpServletResponse response) {
		//初始化pagination context 
		PaginationContext<School> page = initPaginationInfo(new PaginationContext<School>(), request);
		//根据pagination context 查询数据
		List<School> schools = this.schService.schoolPageQuery(page);
		//将数据放到pagination context里面去
		page.setResult(schools);
		//填充total和rows的数据给前台
		Map<String, Object> jsonMap = this.buildRetPageInfo(page);
		//返回给前台json数据
		ResponseUtils.renderJson(response, this.gson.toJson(jsonMap));
	}

	@RequestMapping(value = "/saveSchool")
	public void saveSchool(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			School school = new School();
			String schName = request.getParameter("schName");
			String schDescription = request.getParameter("schDescription");
			String schAddress = request.getParameter("schAddress");
			String schWww = request.getParameter("schWww");
			String schFile = request.getParameter("schLogo");
			String schoolImg = "/"+SystemConstant.schURLPATH+schFile;
			school.setSchName(schName);
			school.setSchDescription(schDescription);
			school.setSchAddress(schAddress);
			school.setSchWww(schWww);
			school.setSchLogo(schoolImg);
			school.setSchCtime(form.format(new Date()));
			this.schService.addSchool(school);
			jsonMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e);
			jsonMap.put("success", false);
			jsonMap.put("errorMsg", e.getMessage());
		}
		ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
	}

	@RequestMapping(value = "/updateSchool")
	public void updateSchool(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		try {
			String schId = request.getParameter("schId");
			String schName = request.getParameter("schName");
			String schDescription = request.getParameter("schDescription");
			String schAddress = request.getParameter("schAddress");
			String schWww = request.getParameter("schWww");
			String schLogo = request.getParameter("schLogo");
			String[] str=schLogo.split("/");
			schLogo=str[str.length-1];
			schLogo = "/"+SystemConstant.schURLPATH+schLogo;
			School school = new School();
			school.setSchId(Integer.parseInt(schId));
			school.setSchName(schName);
			school.setSchDescription(schDescription);
			school.setSchAddress(schAddress);
			school.setSchWww(schWww);
			school.setSchLogo(schLogo);
			this.schService.updateSchool(school);
			jsonMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e);
			jsonMap.put("success", false);
			jsonMap.put("errorMsg", e.getMessage());
		}
		ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
	}

	@RequestMapping(value = "/delSchool")
	public void delSchool(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		try {
			String schId = request.getParameter("schId");
			this.schService.delSchool(Integer.parseInt(schId));
			jsonMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug(e);
			jsonMap.put("success", false);
			jsonMap.put("errorMsg", e.getMessage());
		}
		ResponseUtils.renderJson(response, this.gson.toJson(jsonMap));
	}
	
	@RequestMapping(value = "/updateSchoolImg",method = RequestMethod.POST)
	public void uploadUserImg(HttpServletRequest  request,HttpServletResponse response) throws Exception{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String ctxPath=super.findCtxPath(request, SystemConstant.schURLPATH);
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile userFile=multipartRequest.getFile("schFile");
			String schoolFileName=null;
			String fileName=userFile.getOriginalFilename();
			File dirPath = new File(ctxPath);  
			if (!dirPath.exists()) {  
					dirPath.mkdirs();   
			}  
			int index = fileName.indexOf(".");
			String time = VeDate.getTimeSS();
			if (index != -1) {
				schoolFileName = time + fileName.substring(index);
			}
			String fileNamed=request.getParameter("fileNamed");
		 	if(null !=fileNamed && !fileNamed.equals("")){
		 		WebUtils.delUserImg( fileNamed,ctxPath);
		 	}
		 	InputStream fis =  userFile.getInputStream();
			FileOutputStream fos = new FileOutputStream(ctxPath + "/" + schoolFileName);
			byte[] buff = new byte[1024];
			int leng = 0;
			while ((leng = fis.read(buff)) > 0) {
				fos.write(buff, 0, leng);
			}
			fos.flush();
			fis.close();
			fos.close();
		    jsonMap.put("schoolFileName",schoolFileName); 
		    jsonMap.put("ctxPath", ctxPath);
		    jsonMap.put("urlPath", SystemConstant.schURLPATH);
		}catch (Exception e) {
			jsonMap.put("schoolFileName",null);
			log.info(e.getMessage());
			e.printStackTrace();
		}
		ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
	}
	
	@RequestMapping(value = "/delSchoolImg")
	public void delSchoolImg(HttpServletRequest  request,HttpServletResponse response)throws Exception{
		Map<String,Object> jsonMap=new  HashMap<String,Object>();
		String ctxPath=super.findCtxPath(request, SystemConstant.schURLPATH);
		try{
			String schoolFileName = request.getParameter("fileName");
			String message=WebUtils.delUserImg( schoolFileName,ctxPath);
			jsonMap.put("message", message);
			jsonMap.put("success", true);
		}catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("success", false);
			jsonMap.put("errorMsg", e.getMessage());
		}
		ResponseUtils.renderJson(response, this.gson.toJson(jsonMap));
	}
	
	private static Logger log = Logger.getLogger(SchoolController.class);
	@Autowired
	private SchoolService schService;
}
