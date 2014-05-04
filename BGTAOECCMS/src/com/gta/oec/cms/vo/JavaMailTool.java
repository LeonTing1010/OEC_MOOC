package com.gta.oec.cms.vo;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class JavaMailTool extends Thread{
	private static final Log logger = LogFactory.getLog(JavaMailTool.class);
    /**
     * 邮件认证服务器
     */
	private String host;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 邮件接收方
	 */
	private String sendToAddress;
	/**
	 * 邮件发送方
	 */
	private String sendFromAddress;
	/**
	 * 邮件主题
	 */
	private String subject;
    /**
     * 邮件内容
     */
	private String messageContent;
	
	public JavaMailTool(){}
	
	public JavaMailTool(String host, String username, String password,
			String sendToAddress, String sendFromAddress, String subject,
			String messageContent) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.sendToAddress = sendToAddress;
		this.sendFromAddress = sendFromAddress;
		this.subject = subject;
		this.messageContent = messageContent;
	}
	
	public void run(){
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(this.host);
		javaMailSender.setUsername(this.username);
		javaMailSender.setPassword(this.password);
		// JavaMail 配置 例如: mail.smtp.auth true;mail.smtp.timeout 1200;
		Properties javaMailProperties = new Properties();
		javaMailProperties.setProperty("mail.smtp.auth", "true");
		javaMailProperties.setProperty("mail.smtp.timeout", "2500");
		javaMailSender.setJavaMailProperties(javaMailProperties);
		//设置邮件基本内容
		MimeMessage mailMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,"UTF-8"); 
		try {
			messageHelper.setTo(this.sendToAddress);
			messageHelper.setFrom(this.sendFromAddress);
			messageHelper.setSubject(this.subject);
			messageHelper.setSentDate(new Date());
			messageHelper.setText(this.messageContent);
			javaMailSender.send(mailMessage);
		} catch (MessagingException e) {
			logger.error(e); e.printStackTrace();
		 
		}catch (Exception e) {
			logger.error(e); e.printStackTrace();
		}
		

	}



	public String getHost() {
		return host;
	}



	public void setHost(String host) {
		this.host = host;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}




	public String getSendToAddress() {
		return sendToAddress;
	}

	public void setSendToAddress(String sendToAddress) {
		this.sendToAddress = sendToAddress;
	}

	public String getSendFromAddress() {
		return sendFromAddress;
	}

	public void setSendFromAddress(String sendFromAddress) {
		this.sendFromAddress = sendFromAddress;
	}

	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getMessageContent() {
		return messageContent;
	}



	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
}
