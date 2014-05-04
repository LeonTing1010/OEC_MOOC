package com.gta.oec.cms.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.teacher.TeacherShineDao;
import com.gta.oec.cms.dao.user.UserDao;
import com.gta.oec.cms.enums.user.UserStateEnum;
import com.gta.oec.cms.md5.Md5PwdEncoder;
import com.gta.oec.cms.util.VeDate;
import com.gta.oec.cms.vo.teacher.TeacherShineVo;
import com.gta.oec.cms.vo.user.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private TeacherShineDao teacherShineDao;
	
/*	@Override
	public List<User> queryAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public User queryUserById(Integer id) {
		return userDao.getUserById(id);
	}

	@Override
	public User queryUserByName(String name) {
		return userDao.getUserByName(name);
	}



	@Override
	public List<User> allUserPageQuery(PageModel<User> pm) {
		return userDao.allUserPageQuery(pm);
	}

	@Override
	public List<User> allUserPageQuery(PaginationContext<User> pc) {
		return userDao.allUserPageQueryCtx(pc);
	}

	@Override
	public PaginationContext<User> userPageQuery(PaginationContext<User> pc) {
		pc.setResult(userDao.allUserPageQueryCtx(pc));
		return pc;
	}

	@Override
	public void saveUser(User user) throws ServiceException {
		userDao.addUser(user);
	}

	@Override
	public void updateUser(User user) throws ServiceException {
		userDao.editUser(user);
	}

	@Override
	public void removeUser(int id) throws ServiceException {
		userDao.removeUser(id);
	}*/

	@Override
	public List<User> findUserPageQuery(PaginationContext<User> pc) {
		return userDao.findUserPageQuery(pc);
	}
	
	@Override
	public List<User> getSchoolAll() {
		return userDao.getSchoolAll();
	}
	@Override
	public void insertUserByTeacher(User user) throws Exception{
		try{
			//初始化参数
			 
			user.setUserType(2);
			user.setCreateDate(VeDate.getNowDate());
			user.setUserEmail(user.getUserEmail().trim());
			user.setUserState(UserStateEnum.ABLE.getValue());
			//MD5加密
			user.setPassword(new Md5PwdEncoder().encodePassword(user.getPassword()));
			//插入user表
			userDao.insertUser(user);
			//插入teacher表
			userDao.insertUserByTeacher(user);
			//插入行业岗位
			String[] ids=user.getJobGroupIdsInput().split(",");
				for(String id:ids){
					String[] proJobId=id.split("\\|");
					TeacherShineVo tsVo=new TeacherShineVo();
					tsVo.setUserid(user.getUserId());
					tsVo.setProid(Integer.parseInt(proJobId[0]));
					tsVo.setJobid(Integer.parseInt(proJobId[1]));
					teacherShineDao.insertTeacherShine(tsVo);
			}
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	  public void updateUserStateById(Integer id,Integer state)throws Exception{
		try {
			Map<String,Object> in =new HashMap<String, Object>();
			in.put("userId", id);
			in.put("state", state);
			userDao.updateUserStateById(in);
		} catch (Exception e) {
			throw e;
		}
	
}

	@Override
	public void updateTeacherIsRec(Integer id, Integer IsRec) throws Exception {
		try {
			Map<String,Object> in =new HashMap<String, Object>();
			in.put("userId", id);
			in.put("isRec", IsRec);
			userDao.updateTeacherIsRec(in);
		} catch (Exception e) {
				throw e;
		}
		
		
	}

	@Override
	public Integer findUserCount(Map<String, Object> params) throws Exception {
		try{
			return userDao.findUserCount(params);
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	public List<User> findUserByparams(Map<String, Object> in) throws Exception {
		 
		try{
			return userDao.findUserByparams(in);
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	public User findUserTearchObj(Map<String, Object> params) throws Exception {
		return userDao.findUserTearchObj(params);
	}

	@Override
	public void updateUserByTeacher(User user) throws Exception {
		try {
			//更新user表
		 
			user.setUserName(user.getUserName().trim());
			userDao.upTeahcerByUser(user);
			//更新teacher表
			userDao.upTeahcer(user);
			//更新领域模块，先删除再插入
			teacherShineDao.delTeacherShine(user);
			String []proJobsStr=user.getJobGroupIdsInput().split(",");
			for(String ids:proJobsStr){
				String[] proJobId=ids.split("\\|");
				TeacherShineVo tsVo=new TeacherShineVo();
				tsVo.setUserid(user.getUserId());
				tsVo.setProid(Integer.parseInt(proJobId[0]));
				tsVo.setJobid(Integer.parseInt(proJobId[1]));
				teacherShineDao.insertTeacherShine(tsVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Integer getTeacherByIsrc(Map<String, Object> params)
			throws Exception {
		return userDao.getTeacherByIsrc(params);
	}

	@Override
	public void updateTeahcerByUser(User user) throws Exception {
		userDao.upTeahcerByUser(user);
	}

 
	
}
