/**
 * HelpController.java	  V1.0   2014年3月14日 下午4:35:37
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.help;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gta.oec.common.web.utils.RequestUtils;

@Controller
public class HelpController {

	private static final Logger logger = LoggerFactory.getLogger(HelpController.class);

	@RequestMapping("/help/contact/")
	public String helpContact(HttpServletRequest request, HttpServletResponse response, ModelMap map) {

		return "help/contact.htm";

	}

	@RequestMapping("/help/center/")
	public String helpCenter(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		return "help/helpcenter.htm";

	}

	@RequestMapping("/help/center/content/")
	public String helpCenterContent(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		String index = RequestUtils.getQueryParam(request, "index");
		if (StringUtils.isNumeric(index)) {
			switch (Integer.parseInt(index)) {
			case 1:
				return "help/helpCenter-register.htm";
			case 2:
				return "help/helpCenter-login.htm";
			case 3:
				return "help/helpCenter-findpwd.htm";
			case 4:
				return null;
			case 5:
				return "help/helpCenter-learning.htm";
			case 6:
				return "help/helpCenter-exercise.htm";
			case 7:
				return null;
			case 8:
				return null;
			case 9:
				return "help/helpCenter-answer.htm";
			case 10:
				return "help/helpCenter-question.htm";
			case 11:
				return "help/helpCenter-appeal.htm";
			case 12:
				return null;
			case 13:
				return "help/helpCenter-student-profile.htm";
			case 14:
				return "help/helpCenter-score.htm";
			case 15:
				return "help/helpCenter-collect.htm";
			case 16:

				return "help/helpCenter-updatepwd.htm";
			case 17:

				return "help/helpCenter-teacher-profile.htm";
			case 18:

				return "help/helpCenter-create-course.htm";
			case 19:
				return "help/helpCenter-test.htm";
			case 20:

				return "help/helpCenter-homework.htm";
			case 21:

				return "help/helpCenter-resolve.htm";

			default:
				return null;
			}

		}
		return null;

	}
}
