package com.gta.oec.cms.controller.comment;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.gta.oec.cms.common.ApplicationPropertiesUtil;
import com.gta.oec.cms.common.pagination.PageModel;
import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.controller.BaseController;
import com.gta.oec.cms.controller.profession.ProfessionController;
import com.gta.oec.cms.service.comment.ICommentService;
import com.gta.oec.cms.util.ResponseUtils;
import com.gta.oec.cms.vo.JavaMailTool;
import com.gta.oec.cms.vo.comment.Comment;

import freemarker.template.Template;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController<Comment> {
@Autowired
private ICommentService cService;

private static Logger log = Logger.getLogger(CommentController.class);

@Autowired
private  FreeMarkerConfigurer freeMarkerConfigurer;

@RequestMapping(value="/searchComment")
public String searchComment(HttpServletRequest request,HttpServletResponse response){
	log.info("enter the Comment and the execute the findFeedbackPageQuery method! ");
	request.setAttribute("page", this.gson.toJson(new PageModel<Comment>()));
	return "comment/comment_list.jsp";
}

@RequestMapping(value="/findComment")
public void findComment(Comment comment,HttpServletRequest request,HttpServletResponse response) throws Exception{
	//用于分页
	PaginationContext<Comment> pc = initPaginationInfo(new PaginationContext<Comment>(), request);
	try{
				List<Comment> list = cService.findCommentPageQuery(pc);    //需要分页的方法名 必须加上PageQuery 
				pc.setResult(list);
				Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
		        jsonMap.put("total", pc.getTotalSize());//total键 存放总记录数，必须的  
		        jsonMap.put("rows", pc.getResult());//rows键 存放每页记录 list  
				ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
		 }catch(Exception e){
			log.error("查询所有信息失败", e);
			 e.printStackTrace();
			 throw  e;
		 }
	}

@RequestMapping(value="delComment")
public void delComment(Integer cID,HttpServletRequest request, HttpServletResponse response){
	Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map 
	try {
		cService.updateCommentByID(cID);
		for(int i=0;i<10;i++){
			System.out.println("++++++++++++"+randomString());   //产生6位随机数
		}
		sendRegSuccessEmail(request,"mingkai.cao@gtafe.com");    //发送邮件
		
		jsonMap.put("success", true);
	} catch (Exception e) {
		jsonMap.put("success", false);
		log.error("删除失败", e);
		e.printStackTrace();
	}
	ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
}

public  void  sendRegSuccessEmail(HttpServletRequest request,String userEmail){
	try{
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("/user/regsuccess.htm");
		Map<String, String> map = new HashMap<String, String>();
		
		
		String host = ApplicationPropertiesUtil.getStringValue("mail.host");
		String sendEmail = ApplicationPropertiesUtil.getStringValue("mail.email");
		String password = ApplicationPropertiesUtil.getStringValue("mail.password");
		String messageContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
		
		JavaMailTool JavaMailTool = new JavaMailTool(host,sendEmail, password,
				userEmail,sendEmail, "发送邮件", messageContent);
		JavaMailTool.start();
	}catch(Exception e){
		log.error("邮件发送失败:" + e);
		if(log.isDebugEnabled()){
			log.debug("邮件发送失败:" + e);
		}
		e.printStackTrace();
	}
}
private String randomString() {
	 String[] CHARS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"}; 
	   char c[] = new char[6];  
	   for (int i = 0; i < 6; i++) {   
		   int r = (int) (Math.random() * CHARS.length);   
		   c[i] = r % 2 == 0 ? CHARS[r].toUpperCase().charAt(0) : CHARS[r].charAt(0);  
		   }  
	   return new String(c); 
} 


}
