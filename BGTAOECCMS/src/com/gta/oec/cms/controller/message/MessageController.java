package com.gta.oec.cms.controller.message;

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
import com.gta.oec.cms.controller.user.UserController;
import com.gta.oec.cms.service.message.MessageService;
import com.gta.oec.cms.util.ResponseUtils;
import com.gta.oec.cms.vo.message.Message;

@Controller
@RequestMapping("/message")
public class MessageController extends BaseController<Message>{

	private static Logger log = Logger.getLogger(UserController.class);
	@Autowired
	private MessageService messageService;
	
	/**
	 * 消息列表页面
	 * @author can.xie
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/searchMessageUI")
	public String searchMessageUI(HttpServletRequest request,HttpServletResponse response)throws Exception{
		try {
			request.setAttribute("page", this.gson.toJson(new PageModel<Message>()));
			return "message/message_list.jsp";
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		}
	}
	

	/**
	 * 查询消息
	 * @author can.xie
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/searchMessage")
	public void searchMessage(HttpServletRequest request,HttpServletResponse response)throws Exception{
		try {
			PaginationContext<Message> pc = initPaginationInfo(new PaginationContext<Message>(), request);
			List<Message> list = messageService.findMessagePageQuery(pc);
			pc.setResult(list);
			Map<String, Object> jsonMap = new HashMap<String, Object>();//定义map  
	        jsonMap.put("total", pc.getTotalSize());//total键 存放总记录数，必须的  
	        jsonMap.put("rows", pc.getResult());//rows键 存放每页记录 list  
			ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			throw e;
		}
	}
	/**
	 * 发送消息
	 * @author can.xie
	 * 1.老师， 2.学生，3，后台管理人员，4全站，5.指定个人
	 */
	@RequestMapping("/sendMessage")
	public void sendMessage(Message message,HttpServletRequest request,HttpServletResponse response)throws Exception{

		Map<String, Object> jsonMap=new HashMap<String, Object>();
		String resultMessage="";
		try {
			Map<String,Object> in=new HashMap<String, Object>();
			//系统管理员ID 从session 得到
			in.put("adminId", 2);
			resultMessage =messageService.sendMessage(in, message);
			}catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			throw  e;
		}
		jsonMap.put("resultMessage", resultMessage);
		ResponseUtils.renderHtmlText(response, this.gson.toJson(jsonMap));
	}
}
