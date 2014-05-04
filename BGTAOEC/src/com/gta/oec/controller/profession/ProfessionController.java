package com.gta.oec.controller.profession;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.LoginException;
import com.gta.oec.service.profession.ProfessionService;
import com.gta.oec.util.ObjectUtils;
import com.gta.oec.vo.profession.ProfessionVo;

@Controller
public class ProfessionController {
	private static final Log logger = LogFactory.getLog(ProfessionController.class);
	 /**
	  * 
	  * 功能描述：热门职业推荐名查询
	  *
	  * @author  yangyang.ou
	  * <p>创建日期 ：2014-1-9 下午6:10:50</p>
	  *
	  * @param professionId
	  * @param type
	  * @param modelMap
	  * @param request
	  * @param response
	  * @return
	  * @throws LoginException
	  *
	  * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	  */
	 @RequestMapping("/profession/{professionId}/{type}/")
		public String  getTopProName(@PathVariable int professionId,@PathVariable String type,ModelMap modelMap , HttpServletRequest request,HttpServletResponse response) throws LoginException {  	
	        List<ProfessionVo> professionVo = null;
			try {
				professionVo = professionService.getTopProName(1);
			} catch (BaseException e) {
				logger.error("get course info exception.", e);
			}
	       
	    	modelMap.put("getProfessionList", ObjectUtils.prse(professionVo, ProfessionVo.class));
			return "job/professions.htm";
		}
	 @Autowired
	 private ProfessionService professionService;
}
