/**
 * CourseService.java	  V1.0   2013-12-27 ����10:30:24
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.studentcenter;

import java.util.List;

import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.message.MessageVo;
import com.gta.oec.vo.student.StudentVO;
import com.gta.oec.vo.user.UserVo;
/**
 * 
 * 功能描述：课程
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface StudentcenterService {
 
  public MessageVo getMessageById(Integer id) throws BaseException;
  
  public List<MessageVo> getMessageList(Integer userId) throws BaseException;
  
  public UserVo getUserById(Integer userId) throws BaseException;
 
  public int updateUser(UserVo user) throws BaseException;
  
  public List getCourseList(int jobID) throws BaseException;
  
}
