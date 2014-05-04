/**
 * TrainCenterController.java	  V1.0   2014年3月24日 下午2:49:38
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.train;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 功能描述：认证中心控制层
 *
 * @author  biyun.huang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
@Controller
public class TrainCenterController {
	
	public static final Log logger= LogFactory.getLog(TrainCenterController.class);
	
	/**
	 * 
	 * 功能描述：跳转认证中心页面
	 *
	 * @author  biyun.huang
	 * <p>创建日期 ：2014年3月24日 下午2:53:40</p>
	 *
	 * @param request
	 * @param response
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/train/trainIndex/")
	public String trainIndex(HttpServletRequest request,HttpServletResponse response){
		return "train/trainIndex.htm";
	}

}
