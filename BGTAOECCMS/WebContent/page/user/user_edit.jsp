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
		<form id="editUser" method="post">
		<center>
		<table>
			    	<tr>
			    		<td colspan="2">
			    			<div class="ftitle" align="left">一.创建名师账号</div>
			    		</td>
			    	</tr>
			    	<!-- 用户ID-->
			            	<input type="hidden" name="userId" id="userId" value="${user.userId }"/>
			    	<tr>
			        	<td align="right">用户名: </td>
			         	<td align="left">
			         		<input id="userName" name="userName" class="easyui-validatebox"   value="${user.userName }" 
			         		data-options="
								required:true,
								validType:['userNameLengthValiate[14]','nameStringCheck']"
			         		/>
			         </td>
			         <input type="hidden" id="editValName" value="false"/>
			         <tr>
			
			        
			      	<tr>
			            	<td align="right">用户邮箱：</td>
			            	<td align="left">
			            		<input  name="userEmail" type="text" readonly="readonly" id="userEmail" class="easyui-validatebox" value="${user.userEmail}"  />
			            	</td>
			      	 </tr>
			
			      <tr>
			      			<td colspan="2">
			    				<div class="ftitle" align="left">二.重置密码</div>
			    			</td>
			      </tr>
				
				
				
				<tr>
						
						<td  align="left" colspan="2">发送随机密码至用户创建邮箱:</td>
					 
					</tr>
					<tr>
						 <td colspan="2"  align="right">
			      		    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="magEmail(1);">发送密码</a>
			      		    <div id="toregionPass">
			      		    	<font color="red"></font>
			      		    </div>
			      	 	</td>
					</tr>
			      	 
			      	 <tr>
			            	<td align="left" colspan="2" >发送随机密码至用户指定邮箱: </td>
			         </tr>
			         <tr>
			       	  		<td align="left" colspan="2">
			            		<input name="passwordToEmail" id="passwordToEmail" class="easyui-validatebox"  validType="email" maxlength="30"/>
			            		  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="magEmail(2);">发送密码</a>
			            		    <div id="touserPass">
			      		    
			      		    </div>
			            	</td>
			         </tr>
			
			
			
			            	
			        <tr rowspan="2">
			        </tr>
			        <br>
			        <tr>
				        <td colspan="2" align="left">
				        	<div class="ftitle">三.老师基本信息</div>
				        </td>
			        </tr>
			        <tr>
			            <td align="right">电话:</td>
			            <td align="left">
			          	 	<input name="userMobile" id="userMobile" value="${user.userMobile}" maxlength="20"  validType="userMobileValidate" class="easyui-validatebox"  />
			           </td>
			        </tr> 
			         <tr>
			            <td align="right">头像:</td>
			            <td align="left">
		            	   	<span class="btn btn-small btn-default btn-file"> 
			             		<span	class="fileupload-new">请选择图片</span>
			            		<input id="editFile" type="file" name="userFile" class="margin-none btn-file-input"    required="true" onchange="ajaxFileUpload(2);">
			            	</span>
			            	<input type="hidden" id="userImgUrl" name="userImgUrl" value="${user.userHeadPic }"/>
			            	<!-- 保存已存在的图片名字用来删除 -->
			            	<input type="hidden" id="userHeadPic" name="userHeadPic" value="${user.userHeadPic }"/>
			            	
			            </td>
			        </tr>
			        <tr>
			        	<td colspan="2" id="show" align="center">
			        	<img  src="${applicationScope.ResourcesWebSite}${user.userHeadPic}" height='70' width='80' />
			        	</td>
			        </tr>
			        <tr>
			            <td align="right">职称:</td>
			            <td align="left"><input name="teacherLevel" id="teacherLevel" value="${user.teacherLevel }" class="easyui-validatebox" required="true"maxlength="21"  validType="maxLength[1,20]"></td>
			        </tr>
			        <tr>
			            <td align="right">专业:</td>
			           <td align="left"> <input id ="major"  value="${user. major}"name="major" class="easyui-validatebox" maxlength="21"  validType="maxLength[1,20]" required="true" ></td>
			        </tr>
			        <tr>
			            <td align="right">就职学校:</td>
			            <td align="left">
			         		<input  	id="editSchId" name="schId"   validType ="schoolValidate['#editSchId']"/>
						</td>
						<script type="text/javascript">
						
							var schId='${user.schId}';
							if(schId==null || schId==''){
								schId=0;
							}
						 	   $("#editSchId").combobox({
						         	url:'user/findSchools',  
						       	  	valueField:'schId',
						         	textField:'schName',
						         	panelHeight:'200',
						         	editable:false,
						 			onLoadSuccess: function (data) {
						    			 if (data) {
						         		$("#editSchId").combobox('setValue',schId);
						     		}
									 },
						     });
						</script>
			        </tr>
			          <tr>
				            <td align="right">个人介绍 :</td>
				            <td align="left">
				         	<textarea  	id="usIntroduce" name="usIntroduce"   validType = "maxLength[1,500]"  	class="easyui-validatebox" rows="5" cols="25"   required="true" >${user.usIntroduce}</textarea>
							</td>
        			</tr>  
			        
			        <tr>
			            <td  align="right">名师推荐:</td>
			            <td align="left">
				            <input type="radio" name="isRecommended" value='1'   <c:if test="${user.isRecommended==1 }"> checked="true"  </c:if> <c:if test="${teahcerIsrcSzie>=10 && user.isRecommended !=1}">disabled="disabled"</c:if> >  推荐</input>  
				            <input  type="radio" name="isRecommended"   value="0" <c:if test="${user.isRecommended==0 }"> checked="true"  </c:if> > 不推荐</input>
			            </td> 
			        </tr>
			        
			          <tr>
				        <td>
				        </td>
				        	<td  align="right">
				        	  		  <c:if test="${teahcerIsrcSzie>=10 && user.isRecommended !=1}">
						            	<font color="red">&nbsp;&nbsp;已推荐十个老师. 如需推荐,请先取消推荐其他老师	</font>
					           		 </c:if>
				        	</td>
				   </tr>
			        
			        <tr>
						<td align="center" colspan="2">
							<div class="form-row">
									<div class="input">
										<button type="button" class="btn btn-default btn-small"
											onclick="popupTeachershineUI('editUser');">选择擅长领域</button>
									</div>
										<input type="hidden" id="jobGroupIdsInput" name="jobGroupIdsInput" value="${jobGroupIdsInput}"/>
								</div>
						</td>
			        </tr>
				<tr>
					<td align="center" colspan="2">	<span class="ml10 text" id="selectedShines" ><c:if test="${!empty selectedShines }">已选择擅长领域:${selectedShines}</c:if> </span></td>	
				</tr>        
			        
       </table> 
       </center>
       
  </form>
  
  

	  </body>
 </html>