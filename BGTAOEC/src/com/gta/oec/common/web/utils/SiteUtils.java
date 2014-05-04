/**
 * SiteUtils.java	  V1.0   2013-12-30 ����8:51:32
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.common.web.utils;

import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.gta.oec.common.web.manager.OecCacheManager;
import com.gta.oec.exception.LoginException;
import com.gta.oec.vo.user.UserVo;

/**
 * 
 * 功能描述：站点工具类
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class SiteUtils {
	private static final Log logger = LogFactory.getLog(SiteUtils.class);
	/**
	 * 用户的session
	 */
   public static final String USER="_user";
   
   /**
    * 验证码的Session
    */
   public static final String CHECKCODE="RANDOMCODEKEY";
   public  static UserVo checkTeacher(HttpServletRequest request) throws LoginException{
		UserVo userVo = getUser(request);
		 if (userVo.getIsTeacher()!=1) {
			 throw new LoginException("非教师登录，请勿操作");
		}
		return userVo;
	  }
   
  /**
   * 
   * 功能描述：设置站点用户信息
   *
   * @author  bingzhong.qin
   * <p>创建日期 ：2014-1-2 下午5:39:30</p>
   *
   * @param request
   * @throws LoginException
   *
   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
   */
  public  static void setUser(HttpServletRequest request,UserVo userVo) throws LoginException{
	  if (userVo.getUserType()==2) {
		  userVo.setIsTeacher(1);
	   }
	 // this.cacheManager.set(String.valueOf(userVo.getUserId()), request.getSession().getId());
	  OecCacheManager.getInstance().set(String.valueOf(userVo.getUserId()), request.getSession().getId());
	  request.getSession().setAttribute(USER, userVo);
  }
  public  static void removeUser(HttpServletRequest request){
	  request.getSession().removeAttribute(USER);
  }
  /**
   * 
   * 功能描述：获取验证码的session
   *
   * @author  Miaoj
   * <p>创建日期 ：2014-1-15 下午2:04:30</p>
   *
   * @param request
   * @param userVo
   * @throws LoginException
   *
   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
   */
  public static void setCheckCode(HttpServletRequest request,String strCode) throws LoginException{
	  request.getSession().setAttribute(CHECKCODE, strCode);
  }
  
  /**
   * 
   * 功能描述：获取存入到数据库中的验证码的值
   *
   * @author  Miaoj
   * <p>创建日期 ：2014-1-15 下午4:46:27</p>
   *
   * @param request
   * @return
   * @throws LoginException
   *
   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
   */
  public static String getCheckCode(HttpServletRequest request) throws LoginException{
	  return request.getSession().getAttribute(CHECKCODE)== null?"":request.getSession().getAttribute(CHECKCODE).toString();
  }
  /**
   * 
   * 功能描述：得到用户Session存的数据
   *
   * @author  Miaoj
   * <p>创建日期 ：2014-1-15 上午9:38:47</p>
   *
   * @param request
   * @param userVo
   * @throws LoginException
   *
   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
   */
  public  static UserVo getUser(HttpServletRequest request) throws LoginException{
	    
	  
	  UserVo userVo = (UserVo) request.getSession().getAttribute(USER);
	  if (null==userVo) {
		throw new LoginException("");
	  }
	//  String sessiongId = loginMap.get(String.valueOf(userVo.getUserId()));
	  Object object = OecCacheManager.getInstance().get(String.valueOf(userVo.getUserId()));
	  if (null == object) {
		  throw new LoginException("");
	   }
	  String sessiongId = String.valueOf(object);
	  if (StringUtils.isBlank(sessiongId)) { 
		   throw new LoginException("");
	   }
	  if (!sessiongId.equals(request.getSession().getId())) {
		  throw new LoginException("该账号已在别处登录!");
	   }
	  return userVo;
  }
  
  /**
   * 
   * 功能描述：生成带日期的随机数
   *
   * @author  Miaoj
   * <p>创建日期 ：2014-1-15 上午10:09:38</p>
   *
   * @return
   * @throws LoginException
   *
   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
   */
  public static String getCode()throws LoginException{
      //返回以毫秒为单位的当前时间。
      long r1 = System.currentTimeMillis();
      Random rand=new Random();   
      int i= rand.nextInt();
      String strCode= Long.toString(r1)+Integer.toString(i);
      return strCode;
  }
  
  /**
   * 
   * 功能描述：校验用户是否登录
   *
   * @author  bingzhong.qin
   * <p>创建日期 ：2014-1-2 下午5:40:29</p>
   *
   * @param request
   * @return
   *
   * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
   */
  public  static boolean checkLoin(HttpServletRequest request){
	try {
		getUser(request);
	} catch (LoginException e) {
		logger.error(e); e.printStackTrace();
		return false;
	}
      return true;
 }
  
   /**
    * 
    * 功能描述：生成六位随机数
    *
    * @author  Miaoj
    * <p>创建日期 ：2014-1-16 下午3:35:59</p>
    *
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
  public static String generateWord() {  
      int length = 6;// 随机密码长度为
      StringBuffer buffer = new StringBuffer("0123456789QWERTYUIOPASDFGHJKLZXCVBNM");// 随机字符中也可以为汉字（任何）
      StringBuffer sb = new StringBuffer();
      Random r = new Random();
      int range = buffer.length();
      for (int i = 0; i < length; i++) {

          sb.append(buffer.charAt(r.nextInt(range)));

      }
      return sb.toString();
  }
}
