/**
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.course;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gta.oec.common.web.utils.RequestUtils;
import com.gta.oec.common.web.utils.ResponseUtils;
import com.gta.oec.common.web.utils.SiteUtils;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.LoginException;
import com.gta.oec.service.course.CourseCommentService;
import com.gta.oec.service.course.CourseService;
import com.gta.oec.util.ImageUtils;
import com.gta.oec.vo.common.CaptchaVO;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.course.CourseCommentVo;
import com.gta.oec.vo.user.UserVo;

@Controller
public class CourseCommentController extends BaseCourseController {
	private static final Log logger = LogFactory.getLog(CourseCommentController.class);

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseCommentService courseCommentService;
    
    /**
     * 功能描述：对课程发表评论
     *
     * @author  li.liu
     * <p>创建日期 ：2014-4-24 上午9:51:02</p>
     *
     * @param courseCommentVo
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @throws BaseException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/course/deliverComment/")
  	public void deliverComment(ModelMap modelMap , 
  			HttpServletRequest request,HttpServletResponse response) throws BaseException {
    	
    		boolean result = true;
    		JSONObject json  = new JSONObject();
    		CourseCommentVo courseCommentVo = new CourseCommentVo();
	    	UserVo currUser = SiteUtils.getUser(request);
	    	courseCommentVo.setComUserId(currUser.getUserId());
	    	courseCommentVo.setComContent(RequestUtils.getQueryParam(request, "comContent"));
	    	courseCommentVo.setComCourseId(Integer.parseInt(RequestUtils.getQueryParam(request, "comCourseId")));
	    	
	    	try{
	    		courseCommentService.deliverComment(courseCommentVo,"PC"); // TODO 最好能确切获得 访问来源。
	    		json.put("result", result);
         		ResponseUtils.renderJson(response, json.toString());
	    	}catch (Exception e){
	    		result = false;
	    		json.put("result", result);
         		ResponseUtils.renderJson(response, json.toString());
         		
	    		if(logger.isDebugEnabled()){
	    			logger.debug(e);
	    		}
	    		logger.error(e);
	    		e.printStackTrace();
	    	}
    }
    
    /**
     * 功能描述：发表评论时验证验证码输入是否正确
     *
     * @author  li.liu
     * <p>创建日期 ：2014-4-24 上午9:51:27</p>
     *
     * @param request
     * @param response
     * @throws BaseException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/course/verifyComVerificationCodeAjax")
    public void verifyComVerificationCodeAjax(HttpServletRequest request,HttpServletResponse response) throws BaseException {
		String verificationCode = RequestUtils.getQueryParam(request, "verificationCode").trim();
		boolean result = false;
		if(verificationCode!= null && !verificationCode.equals(""))
		{
			if(SiteUtils.getCheckCode(request).equals(verificationCode)){
				result = true;
			}
		}
	    JSONObject json  = new JSONObject();
	    json.put("result", result);
	    ResponseUtils.renderJson(response, json.toString());		
	}
    
    /**
     * 功能描述：课程点评时生成验证码
     *
     * @author  li.liu
     * <p>创建日期 ：2014-4-24 上午9:52:28</p>
     *
     * @param request
     * @param response
     * @param modelMap
     * @throws BaseException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/course/generateCommentCode/")
	public void generateCommentCode(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws BaseException
	{
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
		CaptchaVO captchaVo = new CaptchaVO();
        try {
        	captchaVo=ImageUtils.creatImage(true);
		} catch (IOException e) {
			logger.error(e.getMessage());e.printStackTrace();
		}
        try {
        	if(captchaVo!= null)
        	{
        	  SiteUtils.setCheckCode(request, captchaVo.getCaptchaValue());
        	  modelMap.put("checkcode", captchaVo.getCaptchaValue());
        	  ImageIO.write(captchaVo.getImage(), "JPEG", response.getOutputStream());//将内存中的图片通过流动形式输出到客户端
        	}
        } catch (Exception e) {
            logger.error(e.getMessage());e.printStackTrace();
        }
	}
    
    /**
     * 功能描述：课程评论分页查询
     *
     * @author  li.liu
     * <p>创建日期 ：2014-4-24 上午9:57:42</p>
     *
     * @param modelMap
     * @param request
     * @param response
     * @return
     * @throws LoginException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/course/courseCommentPageSearch/")
    public String  courseCommentPageSearch(ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	
    	
    	String comCourseId = RequestUtils.getQueryParam(request, "comCourseId");
    	String pageNo = RequestUtils.getQueryParam(request, "commentPageNo");
    	if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
    	int pageSize = 8;
    	
    	PageModel courseCommentPageModel = courseCommentService.findCourseCommentCoursePageListByCourseId(Integer.parseInt(comCourseId),Integer.parseInt(pageNo),pageSize); 
    	// 该课程下共有多少条评论
    	Integer courseCommentCount = courseCommentPageModel.getTotalItem();
    	
    	modelMap.put("courseId",comCourseId);
    	modelMap.put("courseCommentCount",courseCommentCount);
    	modelMap.put("courseCommentPageModel", courseCommentPageModel);
    	
    	return "course/courseCommentPage.htm";
    }
}
