package com.gta.oec.cms.service.message;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.message.MessageDao;
import com.gta.oec.cms.dao.message.MessageModelDao;
import com.gta.oec.cms.dao.user.UserDao;
import com.gta.oec.cms.vo.message.Message;
import com.gta.oec.cms.vo.user.User;

/**
 * 发送消息业务处理类
 * @author can.xie
 *
 */

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageDao messageDao;
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MessageModelDao messageModelDao;
	@Override
	public void saveMessage(Message  message)
			throws Exception {
		 try {
			 messageDao.saveMessage(message);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

		
	}

	@Override
	public List<Message> findMessagePageQuery(PaginationContext<Message> pc)
			throws Exception {
		 try {
			 return messageDao.findMessagePageQuery(pc);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String sendMessage(Map<String, Object> in,Message message) throws Exception {
		StringBuffer reslutMessage=new StringBuffer("");
		 try {
			 //操作系统管理员ID
			 Integer adminId=(Integer)in.get("adminId");
			 //1.老师， 2.学生，3，后台管理人员，4全站，5.指定个人
			 Integer userType=message.getSendObject();
			 if(userType==5){
				 String userEmailAll=message.getUserVo().getUserEmail();
				 if(userEmailAll!=null && !"".equals(userEmailAll)){
					 String []userEmails=userEmailAll.split(",");
					 for(String mail:userEmails){
						 in.put("userEmail", mail);
						 List<User> userList=userDao.findUserByparams(in);
						 if(null ==userList || userList.size()<1){
							 	reslutMessage.append(mail+",");
						 	} 
						 }
					 	//如果用户名输入都有效再发送消息
					 	if("".equals(reslutMessage.toString())){
					 		 //插入消息模板
							 messageModelDao.inserMessageModel(message);
							 
					 		 for(String mail:userEmails){
					 			 in.put("userEmail", mail);
								 List<User> userList=userDao.findUserByparams(in);
					 			sendMessagePotting(userList, message, adminId);
					 		 }
					 }
				 }
			 }else{
				 if(userType==4){
					 in.put("userType", null);
				 }else{
					 in.put("userType", userType);
				 }
				 
				 List<User> userList=userDao.findUserByparams(in);
				 //插入消息模板
				 messageModelDao.inserMessageModel(message);
				 //发送消息
				 sendMessagePotting(userList, message, adminId);
			 }
		} catch (Exception e) {
			throw e;
		}
		 String cMessage=!"".equals(reslutMessage) && reslutMessage.length()>0?reslutMessage.deleteCharAt(reslutMessage.length()-1).toString():null;
		 return cMessage;
	}
	public void sendMessagePotting(List<User> userList,Message message,Integer adminId)throws Exception{
		 for(User user:userList){
			 	Message sendMessage =new Message();
			 	sendMessage.setMessageModelid(message.getMessageModelid());
			 	sendMessage.setUserId(user.getUserId());
			 	sendMessage.setAdminUserId(adminId);
			 	saveMessage(sendMessage);
		 }
	}
	public static void main(String[] args) {
		StringBuffer aa =new StringBuffer("");
		System.out.println(aa.length());
		System.out.println(aa.deleteCharAt(aa.length()-1).toString());
	}
}
