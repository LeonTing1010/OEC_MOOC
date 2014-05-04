<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/header.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List Page</title>
 

</head>
<body>
  
     
<form method="post" id="seachForm" >
	<div style="padding:10px 0 10px 60px">
			<table  cellspacing="0" cellpadding="0"  width="90%" >
			<tr> 
						<td align="right" >对象:</td>
						<td>
							<select name="userType" id="userType" class="easyui-combobox">
								<OPTION  value="">-------请选择------</OPTION>
								<OPTION  value="2">老师</OPTION>
								<OPTION  value="1">学生</OPTION>
								<OPTION  value="3">后台管理人员</OPTION>
							</select>
						</td>  	
						<td align="right">用邮箱:</td>
						<td>
								<input type="text"  name="userEmail" id="userEmail" maxlength="150" size="21"/>
						</td>
						<td align="right"> 
							标题:
						</td>
						<td>
							<input type="text"  name="title" id="title" maxlength="150" size="21"/>
						</td>
						
						<td align="right"> 
							发送时间:
						</td>
						<td colspan="2" >
							<input  type="text"  name="createDate" style="width:150px;" id="createDate"/>To
							<input  type="text"  name="endDate"  style="width:150px;" id="endDate"/>
						</td>
						<td align="left" > 
						 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" onclick="$('form#seachForm').form('reset');">重置</a>
						 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="messageSearch()">查询</a>
						</td>
			</tr>
	</table>
	</div>
</form>
		 
	<table id="list_message_data" cellspacing="0" cellpadding="0"  width="100%">  
  </table>
 
 
 <div id="addMessage" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px" closed="true" >
 
 
    <form id="addMessageFm" method="post" novalidate>
    <center>
    	<table>
    	  
        <tr>
            <td  align="right">发送对象:</td>
            <td align="left">
            &nbsp;  
	            <input type="radio" name="sendObject" value='2'  checked="true"  > 老师</input>  
	              &nbsp;  
	            <input  type="radio" name="sendObject" value="1"> 学生</input>
	              &nbsp;  
	            <input  type="radio" name="sendObject"  value="3" > 后台管理人员</input>
	              &nbsp;  
	              <input  type="radio" name="sendObject"  value="4" >全站</input>
	                &nbsp;  
	            <input  type="radio" name="sendObject"  value="5" > 个人</input>
	              &nbsp;  
	            <input type ="text"  name="userVo.userEmail" id="addEmail"  size="20"/><div id ="errorEmail"> </div>
            </td> 
        </tr>
        
          <tr>
            <td  align="right">标题:</td>
            <td align="left">
	            <input type ="text"  name="title" id="addTitle"size="50" class="easyui-validatebox" required="true" />
            </td> 
        </tr>
          <tr>
            <td  align="right">内容:</td>
            <td align="left">
	            <textarea rows="15" cols="60" id="addContent" name="content" class="easyui-validatebox" required="true"  />
            </td> 
        </tr>
     
        <tr>
			<td colspan="2" align="center">
			
					   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveMessage(this)">保存</a>
					   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#addMessage').dialog('close')">取消</a>
			</td>
		</tr>
       </table> 
       </center>
    </form>
</div>



 <div id="showMessage" class="easyui-dialog" style="width:600px;height:450px;padding:10px 20px" closed="true" >
 
 
    <form id="showFrom" method="post" novalidate>
    <center>
    	<table>
    	  
    
          <tr>
            <td  align="right">标题:</td>
            <td align="left">
	            <input type ="text"  name="title" id="showTitle"size="50" disabled="disabled" />
            </td> 
        </tr>
          <tr>
            <td  align="right">内容:</td>
            <td align="left">
	            <textarea rows="15" cols="60" id="showContent" name="content" disabled="disabled" />
            </td> 
        </tr>
     <tr >
      
     </tr>
     <tr>
     		  <td  align="right">消息发送人:</td>
            	<td align="left">
	            <input type ="text"  name="userEmail" id="showUserEmail"size="50" disabled="disabled" />
            	</td> 
     </tr>
      <tr>
     		  <td  align="right">消息发送时间:</td>
            	<td align="left">
	            <input type ="text"  name="messageCreateDate" id="messageCreateDate"size="50" disabled="disabled" />
            	</td> 
     </tr>
     
      <tr>
     		  <td  align="right">消息发送操作人:</td>
            	<td align="left">
	            <input type ="text"  name="adminUserEmail" id="adminUserEmail"size="50" disabled="disabled" />
            	</td> 
     </tr>
     
     <tr>
			<td colspan="2" align="center">
					   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#showMessage').dialog('close')">取消</a>
			</td>
		</tr>
       </table> 
       </center>
    </form>
</div>
 
 
<script type="text/javascript">
  



<%--  - 弹出框 --%>
var url;
function addMessage(){
	
	var addMessageFm = $('#addMessageFm');
 	addMessageFm.find('input[id=addTitle]').val("");
 	addMessageFm.find('textarea[id=addContent]').val("");
 	addMessageFm.find('input[id=addEmail]').val("");
 	addMessageFm.find('div[id=errorEmail]').html("");
 	
 	
	$("#jobGroupIdsInput").val("");
	$("#selectedShines").html("");
 
    $('#addMessage').dialog('open').dialog('setTitle','发送消息');
	 
   	// url = 'user/saveUser';
}

function showMessage(row){
	if(row){
	 $('#showMessage').dialog('open').dialog('setTitle','消息详情息');
     $('#showFrom').form('load',row);
 } 
	
}
 
function saveMessage(obj){
	 
	$(obj).linkbutton('disable');
    $('#addMessageFm').form('submit',{
        url: 'message/sendMessage',
        onSubmit: function(){
        	var flag=false;
        	flag=$(this).form('validate');
        	return flag;
        },
        success: function(result){
            // close the dialog
        var result = eval('('+result+')');
            if (null ==result.resultMessage || result.resultMessage==""){
            	messageSearch();  // reload the user data
                $.messager.show({
                    title: 'Success',
                    msg: '保存成功!'
                });
                $('#addMessage').dialog('close');  
            } else {
            	$("#errorEmail").html("<font color='red'> "+result.resultMessage+" &nbsp;无效邮箱</font>");
            	$.messager.show({
                    title: 'Error',
                    msg: '保存失败'
                });
            }
          $(obj).linkbutton('enable');
        }
    });
}
function delMessage(){
    var row = $('#list_message_data').datagrid('getSelected');
    if (row){
        $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
            if (r){
                $.post('user/delUser',{userId:row.userId},function(result){
                    if (result.success){
                        $('#list_message_data').datagrid('reload');    // reload the user data
                        $.messager.show({
    	                    title: 'Success',
    	                    msg: '删除成功!'
    	                });
                    } else {
                        $.messager.show({    // show error message
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    }
                },'json');
            }
        });
    }else{
    	$.messager.alert('提醒','请选择一条记录!');
    }
}


<%--  table js --%>
var _h = $("#seachForm").height()
	var _page = '${page}';
	_page = jQuery.parseJSON(_page);
	//datagrid初始化  
    $('#list_message_data').datagrid({  
        title:'消息管理列表',  
        iconCls:'icon-edit',//图标  
        width: eoc.cms.main.tabsVolume.width,  
        height: eoc.cms.main.tabsVolume.height-_h,
        nowrap: false,  
        striped: true,  
        border: true,  
        collapsible:false,//是否可折叠的  
        //fit: true,//自动大小  
        url:'message/searchMessage',  
        //sortName: 'createDate',  
       // sortOrder: 'desc',  
        remoteSort:false,   
        idField:'messageId',
        singleSelect:true,//是否单选  
        pagination:true,//分页控件  
        rownumbers:true,//行号  
       /*  frozenColumns:[[  
            {field:'ck',checkbox:true}  
        ]], */
		columns:[[
	  		{field:'title',title:'标题', width:300},
	  		{field:'userEmail',title:'用户邮箱', width:200,formatter:function(value,row,index){
	  				row.userEmail=row.userVo.userEmail;
		  			return row.userVo.userEmail;  
	  			}},
	  		{field:'readFlag',title:'消息状态', width:100,formatter:function(value,row,index){
  				 if(value==1){
  					 return '未读';
  				 }else{
  					return '已读';  
  				 }
	  			
  			}},
	  		{field:'messageCreateDate',title:'发送时间', width:200,formatter:function(value,row,index){
	  			if(value){
	  				var time =new Date(value);
	  				var messDate=time.format("yyyy-MM-dd hh:mm:ss");
	  				row.messageCreateDate=messDate;
		  			return messDate;  
	  			}
	  		}},
	  		{field:'opt',title:'操作',width:300,formatter : function(value,row,index){
	  			var _row = JSON.stringify(row);
	  			 var e =null;
	  			var d="";
	  			 
	  				  	e = "<a href='javascript:void(0);'  onclick='showMessage("+ _row +")'>查看详情</a> ";  
	  			  
                		d = '<a href="javascript:void(0);" onclick="">删除</a> ';  
               
                	 
                return e+" &nbsp;&nbsp;  "+d;
	  		}}
  		]],
		queryParams: {
			'pageIndex': _page.pageIndex,
			'pageSize': _page.pageSize
		},  
        toolbar: [{  
            text: '发送消息',  
            iconCls: 'icon-add',  
            handler: function() {  
				addMessage(); 
            }  
		}],  
    });
//设置分页控件
var pageInfo = {pageSize: _page.pageSize,pageList: _page.pageList,params:"key=value"};//&key1=value1&key2=value2
eoc.cms.main.createPagination(pageInfo,$('#list_message_data'));

	
function messageSearch(){
	messageSeachParams();
}
	
	//封装分页参数
	function messageSeachParams(){
	var messageForm = $('#seachForm');
	var	userType= messageForm.find("select[id='userType']").combobox('getValue');
	var userEmail= messageForm.find('input[id=userEmail]').val();
	var title= messageForm.find('input[id=title]').val();
	var createDate=	messageForm.find('input[id=createDate]').datetimebox('getValue');
	var endDate=	messageForm.find('input[id=endDate]').datetimebox('getValue');
	var _queryParams = $('#list_message_data').datagrid('options').queryParams;
	    $('#list_message_data').datagrid({
	    	queryParams: {
	    		userType_gp: userType,
	    		title_gp: title,
	    		userEmail_gp: userEmail,
	    		createDate_gp: createDate,
	    		endDate_gp: endDate,
	    		//分页参数
	    		pageIndex : _queryParams.pageIndex,
	    		pageSize : _queryParams.pageSize 
	    	}
	    });
	 // 设置分页控件
		var pageInfo = {
			pageSize : _page.pageSize,
			pageList : _page.pageList,
			// &key1=value1&key2=value2
			params : "key=value"
		};
		eoc.cms.main.createPagination(pageInfo, $('#list_message_data'));
	}
	
	
	 

	$('#createDate').datetimebox({
	    showSeconds: true,
	    formatter:formatD
	    });
	
	$('#endDate').datetimebox({
	    showSeconds: true,
	    formatter:formatD
	    });
	function formatD(date){
		return date.format('yyyy-MM-dd hh:mm:ss');
		} 
	
	 
</script>
</table> 
  
</body>
</html>