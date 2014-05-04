/**
 * RegisterServiceImpl.java	  V1.0   2014年1月6日 下午2:14:05
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.user;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gta.oec.dao.note.NoteDao;
import com.gta.oec.dao.student.StudentDao;
import com.gta.oec.dao.user.UserDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.exception.CustomerDisableException;
import com.gta.oec.exception.InterfaceFieldExcepiton;
import com.gta.oec.exception.SystemDBException;
import com.gta.oec.vo.course.NoteVo;
import com.gta.oec.vo.student.StudentVO;
import com.gta.oec.vo.user.UserVo;
/**
 * 
 * 功能描述：注册操作方法类
 *
 * @author  Miaoj
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Log logger = LogFactory.getLog(UserServiceImpl.class); 
    @Autowired
	private UserDao userDao;
	@Autowired
    private StudentDao studentDao;
	@Autowired
	private  NoteDao noteDao;
	public UserVo  userLogin(UserVo userVo) throws BaseException{
		
		if (null==userVo) {
			throw new InterfaceFieldExcepiton("user is null!");
		}
		if (StringUtils.isBlank(userVo.getUserEmail())) {
			throw new InterfaceFieldExcepiton("user is null!");
		}
		if (StringUtils.isBlank(userVo.getPassword())) {
			throw new InterfaceFieldExcepiton("user is null!");
		}
		try {
		  List<UserVo> list = userDao.getUserInfo(userVo);
		  if (null == list || list.size()==0 || list.size()<1) {
			 throw new BaseException("CustomerDisableException:account info is not exists.");
		   }
		   userVo = list.get(0);
		   if (userVo.getUserState().intValue() != userVo.STATE_NORMAL) {
			  throw new CustomerDisableException("CustomerDisableException: customer state is ".concat(String.valueOf(userVo.getUserState())));
		    }
		}catch (BaseException baseException){
			throw baseException;
		} catch (Exception e) {
			logger.error("get user info error.",e);
			throw new SystemDBException("SystemDBException:get user info error.",e);
		}
		return userVo;
	}
	@Override
	public UserVo getUserById(int userId){	
		try {
			return userDao.getUserById(userId);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}
		return null;
		
	}
	
	@Override
	public UserVo registerUser(UserVo user) throws BaseException {
		
		if (null==user) {
			throw new InterfaceFieldExcepiton("user is null!");
		}
		try {
			return userDao.addUser(user);
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
		
	}
	/**
	 * 
	 * 功能描述：保存用户注册信息
	 *
	 * @author  Miaoj
	 *
	 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
	 */
	@Override
	public UserVo savaUser(UserVo user)throws BaseException{
		//保存用户信息
		if (null==user) {
			throw new InterfaceFieldExcepiton("user is null!");
		}
		try {
			 UserVo userModel= userDao.addUser(user);
			 return userModel;
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
		//保存学生信息

	}
	
	/**
	 * 
	 * 功能描述：登录检查类
	 *
	 * @author  Miaoj        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            logger.error(e); e.printStackTrace();
        }
	 *
	 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
	 */

	@Override
	public UserVo getUserByVo(UserVo user)throws BaseException{	
		if (null==user) {
			throw new InterfaceFieldExcepiton("user is null!");
		}
		try {
			List<UserVo> userList=userDao.getUserInfo(user);
			//处理登录限制
			if (userList.size() == 1) {
				return userList.get(0);
			} else {
				return null;
			}
			
		} catch (Exception e) {
			logger.error("DB Operate Error", e);
			throw new SystemDBException("DB Operate Error!");
		}
	}
	/**
	 * 
	 * 功能描述：根据用户名获取用户信息
	 *
	 * @author  Miaoj
	 *
	 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
	 */
	
   @Override
   public List<UserVo> getUserByName(String userName)throws BaseException{   
	   List<UserVo> userList=userDao.getUserByName(userName);
	   return userList;
   }
   
	/**
	 * 
	 * 功能描述：根据用户列表获取用书信息
	 *
	 * @author  Miaoj
	 *
	 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
	 */
   @Override
	public List<UserVo> getUserByUserVo(UserVo userVo)throws BaseException{
		   List<UserVo> userList=userDao.getUserByUserVo(userVo);
		   return userList;
	}
   
	/**
	 * 
	 * 功能描述：通过邮件更新修改的Code
	 *
	 * @author  Miaoj
	 *
	 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
	 */
    @Override
	public int updateUserPwdCode(String strCode,String strEmail)throws BaseException{
		return userDao.updateUserPwdCode(strCode,strEmail);
	}
    
	/**
	 * 
	 * 功能描述：修改用户密码
	 *
	 * @author  Miaoj
	 *
	 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
	 */
    @Override
	public int updateUserPwd(String strPwd,String strEmail)throws BaseException{
		return userDao.updateUserPwd(strPwd,strEmail);		
	}	
    
	/**
	 * 
	 * 功能描述：更新用户信息
	 *
	 * @author  Miaoj
	 *
	 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
	 */
    @Override
	public int updateUser(UserVo userVo)throws BaseException{
		return userDao.updateUserMode(userVo);	
	}
    
	/**
	 * 
	 * 功能描述：更新用户信息头像
	 *
	 * @author  Miaoj
	 *
	 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
	 */
    @Override
	public int updateUser_HeadPic(UserVo userVo)throws BaseException{
		return userDao.updateUser_HeadPic(userVo);		
	}

	@Override
	public List<NoteVo> getNote(int courseId, int sectionId, int userId)
			throws BaseException {
		try {
			NoteVo noteVo = new NoteVo();
			noteVo.setCourseId(courseId);
			noteVo.setSecId(sectionId);
			noteVo.setUserId(userId);
			return noteDao.getNoteList(noteVo);
		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
			throw new SystemDBException("System operate error!");
		}
		
	}
}
