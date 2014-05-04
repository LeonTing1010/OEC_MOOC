package com.gta.oec.controller.feedback;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gta.oec.common.web.utils.RequestUtils;
import com.gta.oec.common.web.utils.ResponseUtils;
import com.gta.oec.common.web.utils.SiteUtils;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.LoginException;
import com.gta.oec.service.feedback.FeedbackService;
import com.gta.oec.service.user.UserService;
import com.gta.oec.vo.feedback.FeedbackVo;
import com.gta.oec.vo.user.UserVo;

/**
 * 功能描述:意见反馈
 *
 * @author xin.yi
 *
 * <p>2014-4-23 上午10:52:52<p>
 *
 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
 */
@Controller
public class FeedbackController {
	private  final Logger logger = LoggerFactory.getLogger(FeedbackController.class);
	
	
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping("/feedback/")
	public String feedbackPage(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap)throws BaseException{
	      String returnUri = RequestUtils.getQueryParam(request, "returnUri");
	      modelMap.put("returnUri", returnUri);
	   
	      UserVo user = new UserVo();      
	      try {
	    	  user = SiteUtils.getUser(request);

	    	  modelMap.put("user", user);
	    	  modelMap.put("userEmail", user.getUserEmail());
			
	      	} catch (BaseException e) {
	      		logger.error(e.getMessage()); 
	      		e.printStackTrace();
	      	}

	      	return "login/feedback.htm";
	}
	
	/**
	 * 功能描述:保存反馈信息
	 *
	 * @author xin.yi
	 *
	 * <p>2014-4-24 下午1:43:00<p>
	 * @param feedbackVo
	 * @param
	 *
	 * @return
	 */
	@RequestMapping("/feedback/feeSuc/")
	public String saveFeedback(FeedbackVo feedbackVo,HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws BaseException{
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		String feeSource = "PC"; 
		System.out.println(feeSource);
		//获取反馈表单中的数据
		String textTitle = request.getParameter("textTitle"); //标题
		String textContent = request.getParameter("textContent"); //内容
		String textEmail = request.getParameter("textEmail"); //邮箱
		String randomCode = request.getParameter("randomCode");//验证码
		
		String errorMsg ="";
		if(SiteUtils.getCheckCode(request) != null && !SiteUtils.getCheckCode(request).equals(""))
		{
			if(!randomCode.equals(SiteUtils.getCheckCode(request)))
			{	
				errorMsg = "验证码不正确！";
				modelMap.put("errorMsg", errorMsg);
				modelMap.put("textTitle", (textTitle!=null&&!"".equals(textTitle))?textTitle:"");
				modelMap.put("textContent", (textContent!=null&&!"".equals(textContent))?textContent:"");
				modelMap.put("userEmail", (textEmail!=null&&!"".equals(textEmail))?textEmail:"");
				return "login/feedback.htm";	
			}
			else
			{
				SiteUtils.setCheckCode(request, "");
			}
		}
		else
		{
			errorMsg = "验证码不正确！";
			modelMap.put("errorMsg", errorMsg);
			return "login/feedback.htm";	
		}
		FeedbackVo feeVo = new FeedbackVo();
		if(textTitle!=null&&!textTitle.equals("")){
			feeVo.setFeeTitle(textTitle);//标题
		}
		if(textContent!=null&&!textContent.equals("")){
			feeVo.setFeeContent(textContent);//内容
		}
		if(textEmail!=null&&!textEmail.equals("")){
			feeVo.setFeeEmail(textEmail);  //邮箱
		}
		feeVo.setFeeCtime(new Date()); //提交时间	
		feeVo.setFeeSource(feeSource); //来源	
		
		feedbackService.saveFeedback(feeVo);
	
		return "login/feedbackSuccess.htm";
	}
	
}

