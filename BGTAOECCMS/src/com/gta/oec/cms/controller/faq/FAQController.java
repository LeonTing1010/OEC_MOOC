package com.gta.oec.cms.controller.faq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gta.oec.cms.common.pagination.PageModel;
import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.controller.BaseController;
import com.gta.oec.cms.service.faq.IFAQService;
import com.gta.oec.cms.util.ResponseUtils;
import com.gta.oec.cms.util.VeDate;
import com.gta.oec.cms.util.WebUtils;
import com.gta.oec.cms.vo.faq.FAQ;

@Controller
@RequestMapping("/faq")
public class FAQController extends BaseController<FAQ> {
	
	private static Logger log = Logger.getLogger(FAQ.class);
	
@Autowired
private IFAQService faqService;
	
@RequestMapping(value="/searchFAQ")
public String searchFAQ(HttpServletRequest request, HttpServletResponse response){
	log.info("enter the FAQController and the execute the searchFAQ method! ");
	request.setAttribute("page", gson.toJson(new PageModel<FAQ>()));
	return  "faq/faq_list.jsp";
}

//查询所有的faq表信息
@RequestMapping(value="/findFAQ")
public void findFAQ(HttpServletRequest request,HttpServletResponse response)throws Exception{
	PaginationContext<FAQ> pc = initPaginationInfo(new PaginationContext<FAQ>(), request);
	try {
		List<FAQ> faqList = faqService.findFAQPageQuery(pc);
		pc.setResult(faqList);
		Map<String, Object> jsonMap = new HashMap<String, Object>(); //需要分页的方法名 必须加上PageQuery 
		jsonMap.put("total", pc.getTotalSize());                 //total键 存放总记录数，必须的  
		jsonMap.put("rows", pc.getResult());					 //rows键 存放每页记录 list  
		ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
	} catch (Exception e) {
		e.printStackTrace();
		throw e;
	}
}

//删除faq
@RequestMapping(value="/delFAQ")
public void delFAQ(Integer faqID, HttpServletRequest request,HttpServletResponse response){
	Map<String, Object> jsonMap = new HashMap<String, Object>();
	List<FAQ> list = faqService.findPIDByID(faqID);      //按ID查询下面是否还有子节点
	try {
		if(list.size()==0){
			FAQ faq = new FAQ();
			faq = faqService.findTitleByID(faqID);
			String cc=faq.getFaqContent();
			//System.out.println("+++++++++++++++"+cc);
			 int index = 0;
			while(index!=-1){
				 int i=0;
				 index= cc.indexOf("src",index+1);
				 //System.out.println("AAA"+index);
				 if(index!=-1){
					WebUtils.delUserImg( cc.substring(index+6, index+78), "D:/Java/tomcat-6.0.14/webapps");
					//System.out.println("D:/Java/tomcat-6.0.14/webapps"+cc.substring(index+6, index+78));
				 }
				 i++;
			 }
			
			faqService.delFAQ(faqID);
			jsonMap.put("success", true);
		}else{
			jsonMap.put("success", false);
		}
	} catch (Exception e) {
		e.printStackTrace();
		jsonMap.put("success", false);
	}
	ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
}

//修改faq
@RequestMapping(value="/updateFAQ")
public void updateFAQ(HttpServletRequest request,HttpServletResponse response){
	Map<String,Object> jsonMap=new  HashMap<String,Object>();
	FAQ faq = new FAQ();
	FAQ faq2 = new FAQ();
	Integer id = -1;
	String titles = request.getParameter("faqTitle");
	String title = request.getParameter("faqTitle2");   //获取ID
	String ID = request.getParameter("faqID");
	String Content = request.getParameter("faqContent");					//获取内容
	List<FAQ> list = faqService.findTitle();            //查询faq的标题信息
	for (FAQ faq3 : list) {                        //下拉框如果不选获取到的是value 如果选过去到的就是文本内容
		if(faq3.getFaqTitle().equals(title) ){
			id= faq3.getFaqPID();
			break;
		}
	}
	if(id==-1){
		for (FAQ faq3 : list) {
			if(faq3.getFaqID().equals(Integer.parseInt(title))){
				id= faq3.getFaqID();
				break;
			}
		}
	}
	faq2 = faqService.findTitleByID(Integer.parseInt(ID));                              //根据ID来获取指定的标题
	Integer pID = faq2.getFaqPID();
	faq.setFaqID(Integer.parseInt(ID));
	faq.setFaqContent(Content);
	if(title.equals("0")){
		faq.setFaqPID(0);
	}else{
		faq.setFaqPID(pID);
	}
	faq.setFaqUTime(VeDate.getStringDate());
	faq.setFaqTitle(titles);
	try {
		if(ID.equals(id)){
			faqService.updateFAQ(faq);
			jsonMap.put("success", true);
		}else if(!ID.equals(pID)){
			faq.setFaqPID(id);
			faqService.updateFAQ(faq);
			jsonMap.put("success", true);
		}else if(ID.equals(pID)){
			jsonMap.put("success", false);
		}
	} catch (Exception e) {
		e.printStackTrace();
		jsonMap.put("success", false);
	}
	ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
}

//新增faq
@RequestMapping(value="/addFAQ")
public void addFAQ(HttpServletRequest request,HttpServletResponse response){
	Map<String,Object> jsonMap=new  HashMap<String,Object>();
	FAQ faq = new FAQ();
	//FAQ faq2 = new FAQ();
	String title = request.getParameter("faqTitle");
	String id = request.getParameter("faqTitle2");   //获取ID
	if(id.equals("")&&id==""){
		id="0";
	}
	String Content = request.getParameter("faqContent");					//获取内容
	faq.setFaqTitle(title);
	faq.setFaqContent(Content);
	faq.setFaqPID(Integer.parseInt(id));
	faq.setFaqCTime(VeDate.getStringDate());                            
	
	try {
		faqService.addFAQ(faq);
		jsonMap.put("success", true);
	} catch (Exception e) {
		e.printStackTrace();
		jsonMap.put("success", false);
	}
	ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
}

//查询标题   查找标题用来实现下拉框
@RequestMapping(value="/findTitle")
public void findTitle(HttpServletRequest request,HttpServletResponse response)throws Exception{
	try {
		List<FAQ> titleList = faqService.findTitle();
		FAQ faq = new FAQ();
		faq.setFaqID(0);
		faq.setFaqTitle("请选择");
		titleList.add(faq);
		FAQ firstFaq = titleList.get(0);
		titleList.set(titleList.size()-1, firstFaq);
		titleList.set(0, faq);
		ResponseUtils.renderHtmlText(response, this.gson.toJson(titleList));
	} catch (Exception e) {
		e.printStackTrace();
	}
}

		
//处理FAQ  跳转时的乱码问题
@RequestMapping(value="/disposeFAQ")
public String disposeFAQ(HttpServletRequest request,HttpServletResponse response)throws Exception{
	try {
		String ID = request.getParameter("faqID");
		String title = request.getParameter("faqTitle");
		String faqContent = request.getParameter("faqContent");
		title = new String(title.getBytes("iso8859-1"),"UTF-8");
		faqContent = new String(faqContent.getBytes("iso8859-1"),"UTF-8");
		request.setAttribute("faqTitle", title);
		request.setAttribute("faqContent", faqContent);
		request.setAttribute("faqID", ID);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "faq/faq_add.jsp";
}


}
