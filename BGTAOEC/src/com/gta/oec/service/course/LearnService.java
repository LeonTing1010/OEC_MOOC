/**
 * LearnService.java	  V1.0   2014-1-20 下午5:38:49
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.course;

import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.learn.LearnVo;

/**
 * 
 * 功能描述：用户学习相关服务层
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface LearnService {
	/**
	 * 
	 * 功能描述：保存学习信息
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-20 下午5:41:10</p>
	 *
	 * @return
	 * @throws BaseException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
   public LearnVo saveLearn(LearnVo learnVo) throws BaseException;
   /**
    * 
    * 功能描述：更新学习进度
    *
    * @author  bingzhong.qin
    * <p>创建日期 ：2014-1-20 下午6:35:28</p>
    *
    * @param courseId
    * @param userId
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public void updateLearnProgress(Integer courseId,Integer userId)throws BaseException;
   
   
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
	public Integer getUserCourseLearnCount(LearnVo learnVo) throws BaseException;
}
