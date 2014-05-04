/**
 * SectionDao.java	  V1.0   2014-1-7 上午8:44:57
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.dao.section;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.gta.oec.vo.course.SectionVO;

/**
 * 
 * 功能描述：章节DAO
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public interface SectionDao {
	/**
	 * 
	 * 功能描述：获取获取课程的章信息
	 *
	 * @author  bingzhong.qin
	 * <p>创建日期 ：2014-1-9 下午6:37:23</p>
	 *
	 * @param sectionVO
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
   public List<SectionVO> getSectionList(SectionVO sectionVO);
   /**
    * 
    * 功能描述：获取用户课程下的节信息
    *
    * @author  bingzhong.qin
    * <p>创建日期 ：2014-1-9 下午7:23:21</p>
    *
    * @param sectionVO
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public List<SectionVO> getUserSectionList(SectionVO sectionVO,int userId);
   /**
    * 
    * 功能描述：插入章/节
    *
    * @author  bingzhong.qin
    * <p>创建日期 ：2014-1-13 下午1:01:10</p>
    *
    * @param sectionVO
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public SectionVO insert(SectionVO sectionVO);
   
   /**
    * 
    * 功能描述：更新章/节
    *
    * @author  biyun.huang
    * <p>创建日期 ：2014年1月17日 上午10:43:50</p>
    *
    * @param sectionVO
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public SectionVO update(SectionVO sectionVO);
   public void updateSecUrlNull(String secUrl);
   /**
    * 
    * 功能描述：删除章/节
    *
    * @author  bingzhong.qin
    * <p>创建日期 ：2014-2-11 下午2:04:11</p>
    *
    * @param sectionId
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public void deleteSection(Integer sectionId);
   

   public SectionVO getSectionById (Integer sectionId);
   /**
    * 
    * 功能描述：获取未编辑核心知识点或未上传视频的节的数量
    *
    * @author  bingzhong.qin
    * <p>创建日期 ：2014-2-12 下午1:32:11</p>
    *
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public int getSectionNotCompleteCount(Integer courseId);
   /**
    * 
    * 功能描述：更改节的视频类型
    *
    * @author  bingzhong.qin
    * <p>创建日期 ：2014-2-17 下午8:25:16</p>
    *
    * @param sectionVO
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   public void updateSectionTypeByCourseId(SectionVO sectionVO);
   
}
