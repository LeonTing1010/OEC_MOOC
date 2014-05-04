/**
 * TeacherShineVo.java	  V1.0   2014-2-10 下午3:47:14
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.teacher;
import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.job.TeacherShineItemVo;
import com.gta.oec.vo.profession.ProfessionVo;
import com.gta.oec.vo.teacher.TeacherShineVo;

import java.util.List;
import java.util.Map;
/**
 * 
 * 功能描述：教师擅长领域
 *
 * @author  Miaoj
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface TeacherShineService {
	/**
	 * 
	 * 功能描述：
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-2-10 下午4:04:23</p>
	 *
	 * @param stuId
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<TeacherShineVo> getTeacherShineByUserId(int userId);
	/**
	 * 
	 * 功能描述：删除客户擅长的领域
	 *
	 * @author  Miaoj
	 * <p>创建日期 ：2014-2-11 下午3:50:34</p>
	 *
	 * @param userId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int deleteTeacherShineInfor(int userId)throws BaseException;
	
	/**
	 * 
	 * 功能描述：批量添加教师擅长的领域
	 *
	 * @author  jin.zhang
	 * <p>创建日期 ：2014-1-16 下午6:45:21</p>
	 *
	 * @param noteVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int addTeacherShine(List<TeacherShineVo> teacherShineVo);
	
	/**
	 * 功能描述：初始化教师擅长领域选择弹出层的数据.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年3月6日 下午1:37:32</p>
	 *
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public Map<String, List<ProfessionVo>> initDataOfTeacherShine(List<TeacherShineVo> teacherShines)throws BaseException;
	
	/**
	 * 功能描述：初始化教师擅长领域选择弹出层的数据.
	 *
	 * @author  dongs
	 * <p>创建日期 ：2014年3月6日 下午1:37:32</p>
	 *
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<ProfessionVo>[] initDataOfTeacherShineItem(int userId)throws BaseException;
	
}
