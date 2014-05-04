/**
re * CourseDaoTest.java	  V1.0   2014-1-7 上午10:06:12
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.junit.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import com.gta.oec.common.web.email.SendEmailTemplate;
import com.gta.oec.dao.course.CourseCommentDao;
import com.gta.oec.dao.course.CourseDao;
import com.gta.oec.dao.exam.ExamDao;
import com.gta.oec.dao.feedback.FeedbackDao;
import com.gta.oec.dao.job.JobDao;
import com.gta.oec.dao.learn.LearnDao;
import com.gta.oec.dao.note.NoteDao;
import com.gta.oec.dao.resource.ResourceDao;
import com.gta.oec.dao.section.SectionDao;
import com.gta.oec.dao.trade.TradeDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.service.course.CourseCommentService;
import com.gta.oec.service.course.CourseService;
import com.gta.oec.service.course.LearnService;
import com.gta.oec.service.feedback.FeedbackService;
import com.gta.oec.service.job.JobService;
import com.gta.oec.vo.common.PageModel;
import com.gta.oec.vo.course.CourseCommentVo;
import com.gta.oec.vo.course.CourseVo;
import com.gta.oec.vo.feedback.FeedbackVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring/application-*.xml" })
public class DaoTest implements ApplicationContextAware {
	private static final Log logger = LogFactory.getLog(DaoTest.class);
	
	private static ApplicationContext applicationContext;
	private static DataSourceTransactionManager transactionManager;
	private static TransactionStatus status;
	@Autowired
	private  TradeDao tradeDao;
	@Autowired
	private  CourseDao courseDao;
	@Autowired
	private  JobDao jobDao;
	@Autowired
	private  JobService jobService;
	@Autowired
	private  CourseService courseService;
	@Autowired
	private SectionDao sectionDao;
	@Autowired
	private NoteDao noteDao;
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private LearnDao learnDao;
	@Autowired
	private LearnService learnService;
	@Autowired
	private ExamDao examDao;
	@Autowired
	private CourseCommentService courseCommentService;
	@Autowired
	private CourseCommentDao courseCommentDao;
	@Autowired
	private FeedbackDao feedbackDao;
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private FreeMarkerConfig freemarkerConfig;
	@Autowired
	private SendEmailTemplate sendEmailTemplate;  
	
	
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.applicationContext = arg0;
	}
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {
		transactionManager = (DataSourceTransactionManager) applicationContext
				.getBean("transactionManager");
		DefaultTransactionDefinition td = new DefaultTransactionDefinition();
		td.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		status = transactionManager.getTransaction(td);
	}

	@After
	public void destroy() {
		transactionManager.commit(status);
	}
	@Test
	public void testMail() {
		
	}
	@Test
	public void testDao() {
		try {
//          CourseVo courseVo = new  CourseVo();
//          courseVo.setUserId(44);
//          List<CourseVo> liveList;   //直播课程
//      	  List<CourseVo> demandList;  //点播课程 
//      		//获取直播课程
//  			courseVo.setLiveType(new Integer(1));
//  			liveList=courseService.getUserCourList(courseVo);
//  			//获取点播课程
//  			courseVo.setLiveType(new Integer(2));
//  			demandList=courseService.getUserCourList(courseVo);
//  			
//  			System.out.println(liveList);
//  			System.out.println(demandList);
			
//			ExamVo examVo=new ExamVo();
//			examVo.setCourId(1);
//			examVo.setSecId(1);
//			examVo.setExamType(2);
//			List<ExamVo> list=examDao.getExamList(examVo);
//			System.out.println("==========");
//			System.out.println(list);
//			System.out.println("==========");

			
			CourseVo courseVo=new CourseVo();
			courseVo.setUserId(2);		
			PageModel pageModel =courseService.getCourseTab(courseVo, 1, 20,1,2);
			@SuppressWarnings("unchecked")
			List<CourseVo> course=pageModel.getList();
			System.out.println(course);


			
			
//			CourseVo courseVo=new CourseVo();
//			courseVo.setUserId(2);		
//			PageModel pageModel =courseService.getCourseTab(courseVo, 1, 20,1,"2");
//			@SuppressWarnings("unchecked")
//			List<CourseVo> course=pageModel.getList();
//			System.out.println(course);
			

//			
//			


//			CourseVo courseVo=new CourseVo();
//			courseVo.setUserId(2);		
//			//PageModel pageModel =courseService.getCourseTab(courseVo, 1, 20,1,"2");
//			@SuppressWarnings("unchecked")
//			List<CourseVo> course=pageModel.getList();
//			System.out.println(course);
//			
//			CourseVo courseVo=new CourseVo();
//			courseVo.setUserId(1);
//			 List<CourseVo> courList=new ArrayList<CourseVo>();
//			//获取该用户下的课程
//			List<CourseVo> list=courseService.getCourseList(courseVo);
//			//遍历课程，得到带章节和练习的课程对象
//	    	for(CourseVo course:list){
//	    		//courseVo=courseService.getCourseVoInfo(course.getCourseId(), new Integer(2));
//	    		courList.add(courseVo);
//	    	}
//	    	for(CourseVo cVo:courList){
//	    		System.out.println(cVo);
//	    	}

		} catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}
		
	}
	
	@Test
	public void testCourseComment() {
		testCourseCommentSave();
		
		//testCourseCommentFind();
	}
	private void testCourseCommentFind() {
		PageModel pageModel = null;//courseCommentService.findCourseCommentCoursePageListByCourseId(219,1,6);
		List<CourseCommentVo> list = pageModel.getList();
		for (CourseCommentVo courseComment : list) {
			System.out.println(courseComment.getComId() + "::::" + courseComment.getComContent());
		}
	}
	private void testCourseCommentSave() {
		CourseCommentVo courseComment = new CourseCommentVo();
		courseComment.setComUserId(215); // 215 219
		courseComment.setComCourseId(48);
		courseComment.setComSource("PC");
		courseComment.setComCriTime(new Date());
		courseComment.setComContent("这真是一本好书啊！");
		
		//courseCommentDao.saveCourseComment(courseComment);
		//int b = courseCommentService.deliverComment(courseComment);
	}
	
	
	@Test
	public void testSaveFeedback(){
		FeedbackVo feedbackVo = new FeedbackVo();
		feedbackVo.setFeeTitle("我只是来测试的");
		feedbackVo.setFeeContent("好好评价，u are very good");
		feedbackVo.setFeeEmail("p126@126.com");
		feedbackVo.setFeeCtime(new Date());
		feedbackVo.setFeeSource("PC");	
		feedbackDao.saveFeedback(feedbackVo);
		
	}
	
	@Test
	public void TestSendMail() throws Exception{
		Map<String,Object> root = new HashMap<String, Object>();
        root.put("regUserName","刘利");
        sendEmailTemplate.sendTemplateMail(root, "liulixajh@sina.cn","国泰安网络教育注册成功通知","regSuccessSendEmail.ftl");  
	}
}
