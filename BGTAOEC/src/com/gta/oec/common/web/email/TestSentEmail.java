package com.gta.oec.common.web.email;  
  
import java.util.HashMap;  
import java.util.Map;  
  
import javax.annotation.Resource;  
  
import org.springframework.stereotype.Component;  
  
/** 
 * 发送邮件 
 * @author ajun 
 * @email zhaojun2066@gmail.com 
 * @blog http://blog.csdn.net/ajun_studio 
 * 2012-3-13 上午10:50:26 
 */  
@Component("demoEmail")  
public class TestSentEmail {  
  
    private SendEmailTemplate templateEmail;  
      
    @Resource(name="templateEmail")  
    public void setTemplateEmail(SendEmailTemplate templateEmail) {  
        this.templateEmail = templateEmail;  
    }   
      
    public void send(){  
        Map<String,Object> root = new HashMap<String,Object>();  
        root.put("username", "ajun");  
        try {
			templateEmail.sendTemplateMail(root, "liulixajh@sina.cn","测试邮件发送","demo.ftl");
		} catch (Exception e) {
			e.printStackTrace();
		}  
    }  
      
      
      
}  