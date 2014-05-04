<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/header.jsp"%>
<html>
		<head>
		    <title>编辑名师信息</title>
		</head>
			<script type="text/javascript" src="js/jquery_plugins/ajaxfileupload.js"></script>
			<script type="text/javascript" src="js/user/user_upload.js"></script>
			<script type="text/javascript" src="js/user/user.js"></script>
		<body>
	 
				    <form id="addUserForm" method="post" novalidate>
				    <center>
				    	<table>
				    	<tr>
				    		<td colspan="2">
				    			<div class="ftitle" align="left">一.创建名师账号</div>
				    		</td>
				    	</tr>
				    	
				    	<tr>
				        	<td align="right">用户名: </td>
				         	<td align="left">
				         	<input id="userName" name="userName" class="easyui-validatebox"  	/>
				         	 </td>
				         <tr>
				
				        
				      	<tr>
				            	<td align="right">邮箱: </td>
				            	<td align="left">
				            			<input name="userEmail" id="userEmail" class="easyui-validatebox"  maxlength="30" required=" true"    
				            			data-options="	validType:['email','userEmailValidate']"
				            			/>
				            			<input type="hidden" id="checkFlag" value="false" />
				            	</td>
				            	
				      	 </tr>
				        <tr>
				        
				        
				        		<td align="right">密码:
				        		<!--解决谷歌浏览器记住用户名密码 -->
				        		 	<div style="display: none"> 
				            			<input   type="text"   autocomplete="off"  />  
				            			<input   type="password"   autocomplete="off"  />
				            		</div>
				            	</td>
				            	<td align="left" id="userPass">
				           		 	<input name="password" id="password" class="easyui-validatebox"  type="password"   autocomplete="off"  style="width:127.5px;" onpaste='return false' oncopy='return false' />
				            	</td>
				   
				        </tr>
				        <tr>
				            <td align="right"> 确认密码:</td>
				           <td align="left" id="qcPass">
				           		 <input name="qcPassword" id ="qcPassword" class="easyui-validatebox" type="password" 
				           		  autocomplete="off"   required="true"  validType="equalTo['#password']" invalidMessage="两次输入密码不匹配"  style="width:127.5px;"  onpaste='return false' oncopy='return false' />
				           		
				           </td>
				        </tr>
				                 	<script type="text/javascript">
				             			
						            	$('#password').validatebox({    
						            	    required: true,    
						            	  	validType:['length[6,20]','safepass']
						            	});  
						           
						            	$("#addUserForm").find("input[id=userName]").validatebox({
						            	    required: true,    
						            	  	validType:['userNameLengthValiate[14]','nameStringCheck']
						            	});  
						            	
				            	</script>
				        
				        <tr rowspan="2">
				        </tr>
				        <br>
				        <tr>
					        <td>
					        	<div class="ftitle">二.老师基本信息</div>
					        </td>
				        </tr>
				        <tr>
				            <td align="right">电话:</td>
				            <td align="left">
				          	 	<input name="userMobile" id="userMobile" class="easyui-validatebox" maxlength="20"  validType="userMobileValidate">
				           </td>
				        </tr> 
				         <tr>
				            <td align="right">头像:</td>
				            <td align="left">
					             	<span class="btn btn-small btn-default btn-file"> 
						             	<span	class="fileupload-new">请选择图片</span>
						            	<input id="userFile" type="file" name="userFile" class="margin-none btn-file-input"  required="true" onchange="ajaxFileUpload(1);"/>
					            	</span>
					            	<input type="hidden" id="userImgUrl" name="userImgUrl"/>
				 
				            </td>	
				        </tr>
				        <tr>
				        
				        	<td colspan="2"  align="center" id="show"  />
				        </tr>
				        <tr>
				            <td align="right">职称:</td>
				            <td align="left"><input name="teacherLevel" id="teacherLevel" class="easyui-validatebox" required="true" maxlength="21"  validType="maxLength[1,20]"></td>
				        </tr>
				        <tr>
				            <td align="right">专业:</td>
				           <td align="left"> <input id ="major" name="major" class="easyui-validatebox" maxlength="21" required="true" validType="maxLength[1,20]"></td>
				        </tr>
				        <tr>
				            <td align="right">就职学校:</td>
				            <td align="left">
				         	<input  	id="schId" name="schId"   validType ="schoolValidate['#schId']"
									 />
							</td>
				        </tr>  
				       <script type="text/javascript">
								       $("#schId").combobox({
								         		url:'user/findSchools',  
								       	  		valueField:'schId',
								       	  		textField:'schName',
								        	 	panelHeight:'180',
								        	 	editable:false,
								   				onLoadSuccess: function (data) {
								    				 if (data) {
								         			$("#schId").combobox('setValue',data[0].schId);
								     			}
								   			 },
								     });
				       </script>
				        <tr>
				            <td align="right">个人介绍 :</td>
				            <td align="left">
				         	<textarea  	id="usIntroduce" name="usIntroduce"   validType = "maxLength[1,500]"   	class="easyui-validatebox" rows="5" cols="25"   required="true" />
							</td>
				        </tr>  
				        
				        <tr>
				            <td  align="right">名师推荐:</td>
				            <td align="left">
					            <input type="radio" name="isRecommended" value='1'  <c:if test="${teahcerIsrcSzie>=10 }">disabled="disabled"</c:if> > 推荐</input>  
					            <input  type="radio" name="isRecommended"  checked="true" value="0" id="checkrec"> 不推荐</input>
				            </td> 
				        </tr>
				        <tr>
				        <td>
				        </td>
				        	<td  align="right">
				        	  		<c:if test="${teahcerIsrcSzie>=10 }">
						            	<font color="red">&nbsp;&nbsp;已推荐十个老师. 如需推荐,请先取消推荐其他老师
						     			</font>
					           		 </c:if>
				        	</td>
				        </tr>
				        <tr>
				        
							<td align="center" colspan="2">
								<div class="form-row">
										<div class="input">
											<button type="button" class="btn btn-default btn-small"
												onclick="popupTeachershineUI('addUserForm');">选择擅长领域</button>
										</div>
											<input type="hidden" id="jobGroupIdsInput" name="jobGroupIdsInput"/>
									</div>
							</td>
				        </tr>
					<tr>
						<td align="center" colspan="2">	<span class="ml10 text" id="selectedShines"></span></td>	
					</tr>        
				       </table> 
				       </center>
    </form>
 
	  </body>
 </html>