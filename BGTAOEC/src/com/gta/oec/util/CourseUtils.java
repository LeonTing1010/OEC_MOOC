/**
 * CourseUtils.java	  V1.0   2014-1-15 下午7:35:19
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sun.security.jca.GetInstance;

import com.gta.oec.common.web.utils.SiteUtils;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.InterfaceFieldExcepiton;
import com.gta.oec.exception.LoginException;
import com.gta.oec.service.course.CourseService;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.user.UserVo;

/**
 * 
 * 功能描述：课程工具类
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
@Component
public class CourseUtils {
	@Autowired
	private CourseService courseService;
	/**
	 * 课程状态：正常
	 */
	public static final int STATE_NORMAL=2;
	/**
	 * 
	 * 功能描述：校验课程名称
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-15 下午7:38:42</p>
	 *
	 * @param courseName
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
  public static boolean checkCourseName(String courseName){
	  if (StringUtils.isBlank(courseName)||courseName.length()>60) {
		return false;
	  }
	  return true;
  }

  /**
   * 
   * 功能描述：校验课程简介
   *
   * @author  bingzhong.qin
   * <p>创建日期 ：2014-1-15 下午7:39:23</p>
   *
   * @param introduction
   * @return
   *
   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
   */
  public static boolean checkIntroduction(String introduction){
	  if (StringUtils.isBlank(introduction)||introduction.length()>200) {
		return false;
	  }
	  return true;
  }
  /**
   * 
   * 功能描述：校验课程评分方式
   *
   * @author  bingzhong.qin
   * <p>创建日期 ：2014-1-15 下午7:40:50</p>
   *
   * @param scoreMethod
   * @return
   *
   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
   */
  public static boolean checkScoreMethod(String scoreMethod){
	  if (StringUtils.isBlank(scoreMethod)||scoreMethod.length()>500) {
		return false;
	  }
	  return true;
  }
  /**
   * 
   * 功能描述：校验课程学分
   *
   * @author  bingzhong.qin
   * <p>创建日期 ：2014-1-15 下午7:41:44</p>
   *
   * @param credit
   * @return
   *
   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
   */
  public static boolean checkCredit(String credit){
	  if (StringUtils.isBlank(credit)) {
		return false;
	  }
	  if (!(StrUtils.isNum(credit)||StrUtils.isFloat(credit))) {
		  return false;
	   }
	  return true;
  }
  /**
   * 
   * 功能描述：校验课程价格
   *
   * @author  bingzhong.qin
   * <p>创建日期 ：2014-1-15 下午7:58:54</p>
   *
   * @param price
   * @return
   *
   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
   */
  public static boolean checkPrice(String price){
	  if (StringUtils.isBlank(price)) {
		return false;
	  }
	  if (!StrUtils.isNum(price)) {
		  return false;
	   }
	  return true;
  }
  
}
