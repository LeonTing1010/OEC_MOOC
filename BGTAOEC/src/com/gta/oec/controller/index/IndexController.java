/**
 * IndexController.java	  V1.0   2014-1-2 下午2:14:23
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.index;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gta.oec.common.web.utils.RequestUtils;
import com.gta.oec.service.user.UserService;
import com.gta.oec.util.ImageUtils;
import com.gta.oec.vo.common.CaptchaVO;
  

@Controller
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired 
	UserService userService;
    /**
     * 
     * 功能描述：首页
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：2014-1-2 下午2:16:40</p>
     *
     * @param request
     * @param response
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     * @throws IOException 
     */
	@RequestMapping("/index/")
	public String  index(HttpServletRequest request,HttpServletResponse response) throws IOException{
	   return "index.htm";
	}
	
	@RequestMapping("/index/test/")
	public String  indexTest(HttpServletRequest request,HttpServletResponse response ,ModelMap modelMap) throws IOException{

	   return "error/error.htm";
	}
	@RequestMapping("/index/404/")
	public String  index404(HttpServletRequest request,HttpServletResponse response ,ModelMap modelMap) throws IOException{

	   return "error/404.htm";
	}
	@RequestMapping("/index/error/")
	public String  indexError(HttpServletRequest request,HttpServletResponse response ,ModelMap modelMap) throws IOException{
       String errorMsg = RequestUtils.getQueryParam(request, "errorMsg");
       modelMap.put("errorMsg", errorMsg);
	   return "error/error.htm";
	}
	@RequestMapping("/index/indexQuex/")
	public String  indexQuex(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException{
		String tradeId = RequestUtils.getQueryParam(request, "tradeId");
	    modelMap.put("tradeId", tradeId==null?"1":tradeId);
	   return "index_ques.htm";
	}
	
    /**
     * 
     * 功能描述：验证码
     *
     * @author  bingzhong.qin
     * <p>创建日期 ：2014-1-2 下午2:16:40</p>
     *
     * @param request
     * @param response
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     * @throws IOException 
     */
	@RequestMapping("/index/captcha/")
	public void Captcha(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		CaptchaVO captchaVO = ImageUtils.creatImage(true);
		ServletOutputStream responseOutputStream = response.getOutputStream();
		// 输出图象到页面
		ImageIO.write(captchaVO.getImage(), "JPEG", responseOutputStream);
		System.out.println(captchaVO.getCaptchaValue());
		// 以下关闭输入流！
		responseOutputStream.flush();
		responseOutputStream.close();
	}
}
