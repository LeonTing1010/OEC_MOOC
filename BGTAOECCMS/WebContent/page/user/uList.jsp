<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/header.jsp"%>
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
    }
    
    .fitem input{
    	width:190px;
    }
</style>
<table id="list_data" cellspacing="0" cellpadding="0"></table>
<div id="dlg" class="easyui-dialog" style="width:450px;height:450px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
    <div class="ftitle">用户信息</div>
    <form id="fm" method="post" novalidate>
    	<input type="hidden" name="userId">
        <div class="fitem" >
            <label>roleID:</label>
            <input name="roleID" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>userName:</label>
            <input name="userName" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>userMobile:</label>
            <input name="userMobile" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>userEmail:</label>
            <input name="userEmail" class="easyui-validatebox" validType="email">
        </div>
        <div class="fitem">
            <label>userType:</label>
            <input name="userType" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>password:</label>
            <input name="password" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>userHeadPic:</label>
            <input name="userHeadPic" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>userLoginInfo:</label>
            <input name="userLoginInfo" class="easyui-validatebox">
        </div>
        <div class="fitem">
            <label>userResetPwdCode:</label>
            <input name="userResetPwdCode" class="easyui-validatebox">
        </div>
    </form>
</div>
<div id="dlg-buttons">
   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser(this)">保存</a>
   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>
<script type="text/javascript">

	var url;
	function addFunc(){
	    $('#dlg').dialog('open').dialog('setTitle','创建用户');
	    $('#fm').form('clear');
	    url = 'user/saveUser';
	}
	function modFunc(){
	    var row = $('#list_data').datagrid('getSelected');
	    if (row){
	        $('#dlg').dialog('open').dialog('setTitle','编辑用户');
	        $('#fm').form('load',row);
	        //console.log(row);
	        url = 'user/updateUser';//?userId=row.userId;
	    }else{
	    	$.messager.alert('提醒','请选择一条记录!');
	    }
	}
	function saveUser(obj){
		$(obj).linkbutton('disable');
		
	    $('#fm').form('submit',{
	        url: url,
	        onSubmit: function(){
	            return $(this).form('validate');
	        },
	        success: function(result){
	        	$('#dlg').dialog('close');        // close the dialog
	            var result = eval('('+result+')');
	            if (result.success){
	                $('#list_data').datagrid('reload');    // reload the user data
	                $.messager.show({
	                    title: 'Success',
	                    msg: '保存成功!'
	                });
	            } else {
	            	$.messager.show({
	                    title: 'Error',
	                    msg: result.errorMsg
	                });
	            }
	            $(obj).linkbutton('enable');
	        }
	    });
	}
	function delFunc(){
	    var row = $('#list_data').datagrid('getSelected');
	    if (row){
	        $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
	            if (r){
	                $.post('user/delUser',{userId:row.userId},function(result){
	                    if (result.success){
	                        $('#list_data').datagrid('reload');    // reload the user data
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

	var _page = '${page}';
	_page = jQuery.parseJSON(_page);
	//datagrid初始化  
    $('#list_data').datagrid({  
        title:'会员管理列表',  
        iconCls:'icon-edit',//图标  
		width: eoc.cms.main.tabsVolume.width,  
        height: eoc.cms.main.tabsVolume.height,  
        nowrap: false,  
        striped: true,  
        border: true,  
        collapsible:false,//是否可折叠的  
        //fit: true,//自动大小  
        url:'user/findAJUserJson',  
        sortName: 'userId',  
        sortOrder: 'asc',  
        remoteSort:false,   
        idField:'userId',
        singleSelect:true,//是否单选  
        pagination:true,//分页控件  
        rownumbers:false,//行号  
        frozenColumns:[[  
            {field:'ck',checkbox:true}  
        ]],
		columns:[[
	  		{field:'userId',title:'userId', width:100},
	  		{field:'roleID',title:'roleID', width:100},
	  		{field:'userName',title:'userName', width:100},
	  		{field:'userMobile',title:'userMobile', width:100},
	  		{field:'userEmail',title:'userEmail', width:100},
	  		{field:'password',title:'password', width:100},
	  		{field:'userType',title:'用户类型', width:70,align:'center',formatter: function(value,row,index){
				if (row.userType=='1'){//用户类型：1.学生 2.教师 3.后台管理人员
					return '学生';
				} else if(row.userType=='2'){
					return '教师 ';
				}else if(row.userType=='3'){
					return '后台管理人员';
				}else{
					return value;
				}
			}},
	  		{field:'userHeadPic',title:'userHeadPic', width:100},
	  		{field:'userLoginInfo',title:'userLoginInfo', width:100},
	  		{field:'userResetPwdCode',title:'userResetPwdCode', width:100},
	  		{field:'opt',title:'操作',width:100,formatter : function(value,row,index){
	  			var s = '<a href="javascript:void(0);" onclick="alert('+row.userId+')">查看</a> ';  
                var e = '<a href="javascript:void(0);" onclick="alert('+row.userId+')">编辑</a> ';  
                var d = '<a href="javascript:void(0);" onclick="alert('+row.userId+')">删除</a> ';  
                return s+e+d;
	  		}}
  		]],
		queryParams: {
			'pageIndex': _page.pageIndex,
			'pageSize': _page.pageSize
		},  
        toolbar: [{  
            text: '添加',  
            iconCls: 'icon-add',  
            handler: function() {  
				addFunc(); 
            }  
        },'-',{  
            text: '修改',  
            iconCls: 'icon-edit',  
            handler: function() {  
				modFunc(); 
            }  
	    },'-',{  
            text: '删除',  
            iconCls: 'icon-remove',  
            handler: function() {  
				delFunc(); 
            }  
		}],  
    });
	//设置分页控件
	var pageInfo = {pageSize: _page.pageSize,pageList: _page.pageList,params:"key=value"};//&key1=value1&key2=value2
	eoc.cms.main.createPagination(pageInfo,$('#list_data'));
</script>
</body>
</html>