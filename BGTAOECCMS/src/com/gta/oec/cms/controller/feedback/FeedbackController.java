package com.gta.oec.cms.controller.feedback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gta.oec.cms.common.pagination.PageModel;
import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.controller.BaseController;
import com.gta.oec.cms.service.feedback.IFeedbackService;
import com.gta.oec.cms.util.ResponseUtils;
import com.gta.oec.cms.vo.feedback.Feedback;

@Controller
@RequestMapping("/feedback")
public class FeedbackController extends BaseController<Feedback> {
	@Autowired
	private IFeedbackService fService;
	
	private static Logger log = Logger.getLogger(FeedbackController.class);
	
	@RequestMapping(value="/searchFeedback")
	public String searchFeedback(HttpServletRequest request,HttpServletResponse response){
		log.info("enter the ProfessionFeedback and the execute the findFeedbackPageQuery method! ");
		request.setAttribute("page", this.gson.toJson(new PageModel<Feedback>()));
		return "feedback/feedback_list.jsp";
	}
	
	@RequestMapping(value="/findFeedback")
	public void findFeedback(Feedback feedback,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//用于分页
		PaginationContext<Feedback> pc = initPaginationInfo(new PaginationContext<Feedback>(), request);
		try{
					List<Feedback> list = fService.findFeedbackPageQuery(pc);    //需要分页的方法名 必须加上PageQuery 
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
	
	@RequestMapping(value="delFeedback")
	public void delFeedback(Integer fID,HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map 
		try {
			fService.delFeedbackByID(fID);
			jsonMap.put("success", true);
		} catch (Exception e) {
			jsonMap.put("success", false);
			log.error("删除失败", e);
			e.printStackTrace();
		}
		ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
	}
	
}
