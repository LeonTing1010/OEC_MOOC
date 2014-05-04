package com.gta.oec.cms.test;

import java.util.ArrayList;
import java.util.List;

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

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.test.ITestDao;
import com.gta.oec.cms.exception.DAOException;
import com.gta.oec.cms.vo.test.TestVo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-common.xml"})
public class MainJunitTest implements ApplicationContextAware {

	private static final Log logger = LogFactory.getLog(MainJunitTest.class);
	
	private static ApplicationContext applicationContext;
	private static DataSourceTransactionManager transactionManager;
	private static TransactionStatus status;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext = arg0;
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}
	
	@Before
	public void setUp() {
		logger.info("setUp start..............");
		transactionManager = (DataSourceTransactionManager) applicationContext.getBean("transactionManager");
		DefaultTransactionDefinition td = new DefaultTransactionDefinition();
		td.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		status = transactionManager.getTransaction(td);
		logger.info("setUp end..............");
	}
	
	@After
	public void destory() {
		transactionManager.commit(status);
	}
	
	@Autowired
	private ITestDao itestDao;
	
	@Test
	public void mainTest(){
		System.out.println("############################## mainTest ##############################");
		/*this.testTestDaoInsert();
		this.testTestDaoUpdate();
		this.testTestDaoDelete();*/
		this.testTestDaoPageQuery();
		System.out.println("############################## mainTest ##############################");
	}
	
	/**
	 * 分页查询
	 * */
	public void testTestDaoPageQuery(){
		PaginationContext<TestVo> page = new PaginationContext<TestVo>();
		page.setPageSize(30);
		page.addParameter("name", "4");
		List<TestVo> list = itestDao.findListPageQuery(page);
		System.out.println(".....list.size...:"+list.size());
		for(TestVo t : list){
			System.out.println("Id : "+t.gettId()+"; Name : "+t.gettName()+"; Desc : "+t.gettDesc()+"; Remark : "+t.gettRemark());
		}
	}
	
	/**
	 * 单条删除和批量删除
	 * */
	public void testTestDaoDelete(){
		List<TestVo> list = itestDao.findList();
		try {
			itestDao.delete(list.get(0));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		try {
			itestDao.deleteBatch(list.subList(1, list.size()));
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 单条更新和批量更新
	 * */
	public void testTestDaoUpdate(){
		List<TestVo> list = itestDao.findList();
		
		int j = 0;
		TestVo tt = list.get(j);
		tt.settName(tt.gettName()+j+"--");
		tt.settDesc(tt.gettDesc()+j+"--");
		tt.settRemark(tt.gettRemark()+j+"--");
		try {
			itestDao.update(tt);
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		for(int i = 1; i < list.size(); i ++){
			TestVo t = list.get(i);
			t.settName(t.gettName()+i);
			t.settDesc(t.gettDesc()+i);
			t.settRemark(t.gettRemark()+i);
		}
		try {
			itestDao.updateBatch(list);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 单条插入和批量插入
	 * */
	public void testTestDaoInsert(){
		TestVo tvo = new TestVo();
		tvo.settName("tName");
		tvo.settDesc("tDesc");
		tvo.settRemark("tRemark");
		try {
			itestDao.insert(tvo);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		List<TestVo> list = new ArrayList<TestVo>();
		for(int i = 0; i < 10; i ++){
			TestVo t = new TestVo();
			t.settName("tName"+i);
			t.settDesc("tDesc"+i);
			t.settRemark("tRemark"+i);
			list.add(t);
		}
		try {
			itestDao.insertBatch(list);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
}