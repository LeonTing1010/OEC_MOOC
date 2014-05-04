
package com.gta.oec.service.school;


import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.school.SchoolVo;
/**
 * 
 * 功能描述：根据老师id查学校
 *
 * @author  jin.zhang
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface SchoolService {
 
  public SchoolVo getSchoolByUserId(Integer userId) throws BaseException;
 
}
