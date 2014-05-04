/**
 * UserLearnDao.java	  V1.0   2014-1-15 上午10:44:43
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.learn;


import java.util.List;

import com.gta.oec.vo.learn.LearnVo;

/**
 * 
 * 功能描述：用户学习明细DAO
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface LearnDao {
	/**
	 * 
	 * 功能描述：获取节的学习信息
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-1-15 上午10:50:43
	 *         </p>
	 * 
	 * @param sectionVO
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public LearnVo getLearn(LearnVo learnVo);

	public LearnVo insert(LearnVo learnVo);

	/**
	 * 
	 * 功能描述：获取用户课程章节的学习数量(用于计算学习进度)
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-1-20 下午6:38:47
	 *         </p>
	 * 
	 * @param learnVo
	 * @return
	 * 
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public Integer getUserCourseLearnCount(LearnVo learnVo);

	/**
	 * 
	 * 功能描述：更新用户学习进度
	 * 
	 * @author bingzhong.qin
	 *         <p>
	 *         创建日期 ：2014-1-23 下午6:33:14
	 *         </p>
	 * 
	 * @param courseId
	 * @param userId
	 * @param progress
	 * 
	 *            <p>
	 *            修改历史 ：(修改人，修改时间，修改原因/内容)
	 *            </p>
	 */
	public void updateUserCourseProgress(Integer courseId, Integer userId,float progress);
     
	/**
	 * 
	 * 功能描述：获取用户课程
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-23 下午6:38:33</p>
	 *
	 * @param learnVo
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<LearnVo> getUserCourse(LearnVo learnVo);
	
	public void insertUserCourse(LearnVo learnVo);
	
}
