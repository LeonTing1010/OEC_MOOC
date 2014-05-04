<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List Page</title>
 

</head>
<body>
 <style type="text/css">
    #fm{
        margin:0;
        padding:10px 30px;
    }
    .ftitle{
        font-size:14px;
        font-weight:bold;
        padding:5px 0;
        margin-bottom:10px;
        border-bottom:1px solid #ccc;
    }
    .fitem{
        margin-bottom:5px;
    }
    .fitem label{
        display:inline-block;
        width:130px;
        text-align:right;
    }
    
    .fitem input{
    	width:190px;
    }
    
    
    /* 彈出層DIV  Add BY:缪佳 2014-01-22 */
#alertdiv{position:absolute; height:260px; width:550px; z-index:9999;left:50%; 
margin-left:-200px; padding:1px; border:1px #ccc solid; font-size:12px; 
display:none; background-color:#FFFFFF} 
 
#alertdiv h3{ position:relative; height:23px; background-color:#E4E4E4; 
font-size:12px;padding:0; padding-left:5px; line-height:23px; margin:0; } 
 
#alertdiv h3 a{position:absolute; display:block;right:5px; top:3px;display:block; 
margin:0; width:16px; height:16px; margin:0; padding:0; overflow:hidden; 
background:url(../img/close_new.gif) no-repeat; 
cursor:pointer; text-indent:-999px} 
 
.forminfo{padding:5px;} 
 
.inputR{width:120px;} 
 
/* .bg{-moz-opacity: 0.5; height:10000px;opacity:0.7; filter: alpha(opacity=30); background-color:#000;
width:120%; position:absolute; z-index:9998; left:0; top:0; display:none}
 */
.btn-file-input{
	cursor: pointer;
    direction: ltr;
    font-size: 23px;
    margin: 0;
    opacity: 0;
    position: absolute;
    right: 0;
    top: 0;
    transform: translate(-300px, 0px) scale(4);
	height: 30px;
    line-height: 30px;
	filter:alpha(opacity:0);
	}
	.btn-file{
	overflow: hidden;
	display: inline-block;
    position: relative;
	}
 
</style>
 
<form method="post" id="seachForm" >
	<div style="padding:10px 0 10px 60px">
			<table  cellspacing="0" cellpadding="0"  width="90%" >
			<tr> 
						<td align="right" >学历:</td>
						<td>
							<select name="education" id="education" class="easyui-combobox"  " data-options="panelHeight:'auto',editable:false"  >
								<OPTION  value="">-------请选择------</OPTION>
								<OPTION  value="2">博士</OPTION>
								<OPTION  value="3">硕士</OPTION>
								<OPTION  value="1">本科</OPTION>
								<OPTION  value="4">大专</OPTION>
								<OPTION  value="5">其他</OPTION>
							</select>
						</td>  	
						<td align="right">用户名:</td>
						<td>
								<input type="text"  name="userName" id="userName" maxlength="150" size="21"/>
						</td>
						<td align="right"> 
							学校:
						</td>
						<td>
						<input class="easyui-combobox"  id="searchSchId"
					            			name="schId" style="width:150px;"
					            />
						</td>
						
						<td align="right" > 
							是否推荐:
						</td>
						<td>
							<select name="isRecommended"   id="isRecommended" class="easyui-combobox" data-options="panelHeight:'auto',editable:false" >
								<option  value="">-------请选择------</option>
								<option value="1">是</option>
								<option value="0">否</option>
							</select>
						</td>
			</tr>
			 
			<tr>
				<td align="right">身份:</td>
						<td>
							<select name="userType"   id="userType" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"  >
								<OPTION  value="">-------请选择------</OPTION>
								<OPTION  value="2">老师</OPTION>
								<OPTION  value="1">学生</OPTION>
								<OPTION  value="3">后台管理人员</OPTION>
								
							</select>
						</td> 
						
						<td align="right">状态:</td>
						<td width="5">
							<select name="userState"  id="userState" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"  style="width:138px;">
								<OPTION  value="">-------请选择---------</OPTION>
								<OPTION  value="1">正常</OPTION>
								<OPTION  value="0">封停</OPTION>
							</select>
						</td>
						 
						<td align="right"> 
							注册日期:&nbsp; &nbsp;从&nbsp;&nbsp;
						</td>
						<td colspan="2" >
							<input  type="text"  name="createDate" style="width:150px;" id="createDate" data-options="editable:false" />&nbsp;&nbsp;到&nbsp;&nbsp;
							<input  type="text"  name="endDate"  style="width:150px;" id="endDate" data-options="editable:false" />
						</td>
						
						<td align="left" > 
						<!--<input type="button" value="搜索"   class="easyui-linkbutton"  onclick="userSearch();"/>-->
							<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" onclick="formRest();">重置</a>
							 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="userSearch()">查询</a>
						</td>
			</tr>
			<script type="text/javascript">
				function  formRest(){
					$('form#seachForm').form('reset');
					$("#searchSchId").combobox('setValue',0);
				}
			</script>
	</table>
	</div>
</form>
		 
	<table id="list_user_data" cellspacing="0" cellpadding="0"  width="100%">  
  </table>
 
 <form  id="divForm">
 <!-- 添加弹出层 -->
 <div id="addUserDiv" style="padding:10px;" autocomplete="off"   buttons="#addUser-buttons">
    		   		<div id="addUser-buttons" align="right">
  							  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser(this)">保存</a>
								<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addUserDiv').dialog('close')">取消</a>
					</div>
</div>
<!-- 编辑弹出层 -->
<div id="editUserDiv" style="padding:10px; "  buttons="#editUser-buttons">
					<div id="editUser-buttons" align="right">
  							   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="editUser(this);">保存</a>
							   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editUserDiv').dialog('close')">取消</a>
					</div>

</div>
<!-- 领域弹出层 -->
<div id="alertDiv" style="padding:10px; "></div>
<!-- 领域弹出层 编辑-->
<div id="editDiv" style="padding:10px; "></div>
 </form>
<script type="text/javascript">
	var _page = '${page}';
	_page = jQuery.parseJSON(_page);
</script>
</table> 
 	<script type="text/javascript" src="${ctx}/js/jquery_plugins/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctx}/js/user/user_upload.js"></script>
	<script type="text/javascript" src="${ctx}/js/user/user.js"></script>
</body>
</html>