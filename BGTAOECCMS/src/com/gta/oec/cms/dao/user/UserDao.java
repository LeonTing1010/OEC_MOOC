package com.gta.oec.cms.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.SqlMapper;
import com.gta.oec.cms.vo.user.User;

public interface UserDao extends SqlMapper<User>{
	/*public List<User> getAllUser();
    
    public User getUserById(@Param(value="userId") int id);
    
    public User getUserByName(@Param(value="userName") String name);
    
    public void addUser(@Param(value="user") User user);
    
    public void editUser(@Param(value="user") User user);
    
    public void removeUser(@Param(value="userId") int id);
    
    public List<User> findUserByIn(@Param(value="user") User user);
    
    public List<User> allUserPageQuery(PageModel<User> page);
    //@Param("page") 
    public List<User> allUserPageQueryCtx(PaginationContext<User> pc);
    */
    /**
     * 会员管理， 会员查询
     * @author can.xie
     * @create date 2014.3.19
     */
    
    public List<User> findUserPageQuery(PaginationContext<User> pc);
    /**
     * 查询所有的学校
     * @author can.xie
     * @create date 2014.3.19
     */
    public List<User> getSchoolAll();
    
    /**
     * 插入学生表
     * @author can.xie
     * @create date 2014.3.19
     */
    public void insertUser(@Param(value="user") User user)throws Exception; 
    /**
     * 插入老师表
     * @author can.xie
     * @create date 2014.3.19
     */
    public void insertUserByTeacher(@Param(value="user") User user)throws Exception;
    /**
     * 插入老师的行业岗位
     * @author can.xie
     * @create date 2014.3.19
     */
    public void insertTeacherShine(@Param(value="user") User user)throws Exception;
    
    /**
     * 更改user 状态
     * @author can.xie
     */
    public void updateUserStateById(@Param(value="in")Map<String, Object> in)throws Exception;
    
    /**
     * 更改teacher推荐
     * @author can.xie
     */
    public void updateTeacherIsRec(@Param(value="in")Map<String, Object> in)throws Exception;
    
    /**
     * 验证邮箱是否唯一
     * @author can.xie
     */
    public Integer findUserCount(@Param(value="params") Map<String, Object> params) throws Exception;
    
    /**
     * 查询用户信息
     */
	public List<User> findUserByparams(@Param(value="params")Map<String, Object> params) throws Exception;
	
	/**
	 * 查询老师信息
	 */
	public User findUserTearchObj(@Param(value="params")Map<String, Object> params)throws Exception;
	/**
	 * 更新user表
	 * 
	 */
	public void upTeahcerByUser(@Param(value="user")User user)throws Exception;
	
	/**
	 * 更新teacher表
	 * 
	 */
	public void upTeahcer(@Param(value="user")User user)throws Exception;


	/**
	 * 查询已推荐的老师个数
	 * 
	 */
	public Integer getTeacherByIsrc(@Param(value="params")Map<String, Object> params)throws Exception;
	
}
