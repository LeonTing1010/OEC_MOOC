package com.gta.oec.cms.service.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.vo.user.User;

public interface UserService {
	
/*	public List<User> queryAllUser();
    
    public User queryUserById(Integer id);
    
    public User queryUserByName(String name);
    

    public void saveUser(User user) throws ServiceException;
    
    public void updateUser(User user) throws ServiceException;
    
    public void removeUser(int id) throws ServiceException;
    
    public List<User> allUserPageQuery(PageModel<User> pm);
    
    public List<User> allUserPageQuery(PaginationContext<User> pc);
    
    public PaginationContext<User> userPageQuery(PaginationContext<User> pc);
    */
    /**
     * 查询会员信息
     * @parmas map
     * @author can.xie
     * @create date :2014.3.18
     * 
     */
    public List<User> findUserPageQuery(PaginationContext<User> pc);
    
    /**
     * @author can.xie
     * 查询所有的学校
     * 
     */
    public List<User> getSchoolAll();

    /**
     * @author can.xie
     * 添加老师
     * 
     */
    public void insertUserByTeacher(User user) throws Exception;
    /**
     * @author can.xie
     * 修改user状态
     * 
     */
    
    public void updateUserStateById(Integer id,Integer state)throws Exception;
   
    /**@author can.xie
     * 修改老师推荐
     */
    public void updateTeacherIsRec(Integer id,Integer isRec)throws Exception;
    /**
     * @author can.xie
     * 验证用户邮箱是否唯一
     * 
     */
    public Integer findUserCount(Map<String, Object> params) throws Exception;
    
    /**
     * @author can.xie
     * 查询用户信息
     */
	public List<User> findUserByparams(Map<String, Object> in) throws Exception;
	/**
	 * @author can.xie
	 * 查询老师用户和老师信息
	 * 
	 */
	public User findUserTearchObj(Map<String, Object> params)throws Exception;
	
	
	/**
	 * @author can.xie
	 * 更改老师信息
	 */
	public void updateUserByTeacher(User user)throws Exception;
	

	/**
	 * 查询已推荐的老师个数
	 * 
	 */
	public Integer getTeacherByIsrc(Map<String, Object> params) throws Exception;
	
	/**
	 * 更新老师
	 */
	
	public void updateTeahcerByUser(@Param(value="user")User user)throws Exception;
}
