/**
 * BaseController.java	  V1.0   2014-4-9 下午3:30:05
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.controller.course;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gta.oec.common.web.utils.SiteUtils;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.InterfaceFieldExcepiton;
import com.gta.oec.exception.LoginException;
import com.gta.oec.service.course.CourseService;
import com.gta.oec.util.CourseUtils;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.user.UserVo;

public abstract class BaseCourseController {
	
	private  final Log logger = LogFactory.getLog(getClass());
	  @Autowired
      protected CourseService courseService;
	  /**
	   * 
	   * 功能描述：校验课程的合法性
	   *
	   * @author  bingzhong.qin
	   * <p>创建日期 ：20142014-4-9 下午3:04:38</p>
	   *
	   *
	   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	   */
		protected  void checkCourse(int courseId,HttpServletRequest request) throws BaseException{
			if (courseId<=0) {
				throw new InterfaceFieldExcepiton("courseId is not less than.");
			}
		    CourseVo courseVo = this.courseService.getCourseInfoById(courseId);
		    if (null==courseVo) {
				throw new BaseException("course is not exist");
			}
		    if (CourseUtils.STATE_NORMAL!=courseVo.getStatus()) {
		    	/* try {
				     UserVo userVo = SiteUtils.getUser(request);
					 if (courseVo.getUserId()!=userVo.getUserId()) {
					    	throw new BaseException("course is not exist");
				     }
					} catch (LoginException e) {
						logger.error(e);
						throw e;
					}*/
		    	 throw new BaseException("course is not exist");
			}
		    //如果课程状态为未发布且课程创建的用户为课程登录的用户，则校验通过，此项是为了预览操作
		   

		}

}
