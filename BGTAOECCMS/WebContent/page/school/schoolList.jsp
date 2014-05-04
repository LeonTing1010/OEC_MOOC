<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/header.jsp"%>
<body>
<style type="text/css">
    #fmSchool{
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
<div id="searchFormPanel" class="easyui-panel" title="学校管理" style="width:1250px;height:80px;">
        <div style="padding:10px 0 10px 60px">
        <form id="schmanage" method="post" buttons="#schdlg-buttons">
            <table>
                <tr>
                    <td>学校名称:</td>
                    <td>
                    	<input class="easyui-validatebox" type="text" name="schName" style="width:210px;height:20px" maxlength="15"></input>
                    </td>
                
                	<td style="padding-left:60px">学校地址:</td>
                    <td style="padding-right:40px">
                    	<input class="easyui-validatebox" type="text" name="schAddress" style="width:210px;height:20px" maxlength="30"></input>
                    </td>
                
                	<td>
                	<div id="schdlg-buttons">
                		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" onclick="$('form#schmanage').form('reset');">重置</a>
                		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="queryForm();">查询</a>
                	</div>	
                	</td>
                </tr>
            </table>
        </form>
        </div>
</div>
    
<table id="schoollist_data" cellspacing="0" cellpadding="0"> </table>
<div id="dlg" class="easyui-dialog" style="width:450px;height:400px;padding:10px 20px" closed="true" buttons="#school-buttons">
    <div class="ftitle">学校信息</div>
    <form id="fmSchool" method="post" novalidate>
    	<input type="hidden" name="schId">
        <div class="fitem" >
            <label>学校名称:</label>
            <input name="schName" class="easyui-validatebox" required="true" validType="length[1,15]" invalidMessage="长度不能超过15个字符">
        </div>
        <div class="fitem">
            <label>学校简介:</label>
            <input name="schDescription" class="easyui-validatebox" maxlength="200">
        </div>
        <div class="fitem">
            <label>学校地址:</label>
            <input name="schAddress" class="easyui-validatebox" required="true" validType="length[1,30]" invalidMessage="长度不能超过30个字符">
        </div>
        <div class="fitem">
            <label>学校网址:</label>
            <input name="schWww" class="easyui-validatebox" maxlength="30">
        </div>
        <div class="fitem">
            <label>学校图片:</label>
            <input id="schFile" type="file" name="schFile"  onchange="ajaxFileUploadSch();" required="true">
            <input type="hidden" id="schLogo" name="schLogo"/>
            <div id="showSchImg" align="center">
            	<img alt="" src="" id="schImg"/>
            </div>
        </div>
        
    </form>
</div>
<div id="school-buttons">
   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveSchool(this)">保存</a>
   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>

<div id="dlgview" class="easyui-dialog" style="width:450px;height:400px;padding:10px 20px" closed="true">
    <div class="ftitle">学校信息预览</div>
    <form id="fmview" novalidate>
        <div class="fitem" >
            <label>学校名称:</label>
            <input name="schName" class="easyui-validatebox" disabled>
        </div>
        <div class="fitem">
            <label>学校简介:</label>
            <input name="schDescription" class="easyui-validatebox" disabled>
        </div>
        <div class="fitem">
            <label>学校地址:</label>
            <input name="schAddress" class="easyui-validatebox" disabled>
        </div>
        <div class="fitem">
            <label>学校网址:</label>
            <input name="schWww" class="easyui-validatebox" disabled>
        </div>
        <div class="fitem">
            <label>学校图片:</label>
            
            <div id="schImg" name="schImg" type="hidden" align="center">
            	<img alt="" src="" height='70' width='80'/>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
	var url;
	function addSchoolFunc(){
		$('#showSchImg').empty();
		 $("#schImg").hide();
	    $('#dlg').dialog('open').dialog('setTitle','添加学校信息');
	    $('#fmSchool').form('clear');
	    url = 'school/saveSchool';
	}
	
	function modSchoolFunc(){
	    var row = $('#schoollist_data').datagrid('getSelected');
	    if (row){
	    	var imgurl = row.schLogo;
			var imageUrl;
	    	if(imgurl.charAt(0)=="/"){
	    		imageUrl=imgurl.substring(1);
	    	}
	        $('#dlg').dialog('open').dialog('setTitle','编辑学校信息');
	        $('#schImg').attr('src',imageUrl);
	        $('#fmSchool').form('load',row);
	       	url = 'school/updateSchool';
	       	$('#schoollist_data').datagrid('reload');
	    }else{
	    	$.messager.alert('提醒','请选择一条记录!');
	    }
	}
	
	function saveSchool(obj){
		var schoolImg=$("#schLogo").val();
	    $('#fmSchool').form('submit',{
	        url: url,
	        onSubmit: function(){
	        	var flag=false;
	        	flag=$(this).form('validate');
	        	if(flag){
	        		if(schoolImg==null ||schoolImg==''|| typeof(schoolImg)=='undefined'){
	            		$.messager.alert('提醒','请上传图片!');
	            		flag= false;
	            	}
	        	}
	        	if(!flag){
	        		$(obj).linkbutton('enable');
	        	}
	        	return flag;
	        },
	        success: function(result){
	        	$('#dlg').dialog('close');        // close the dialog
	            var result = eval('('+result+')');
	            if (result.success){
	                $('#schoollist_data').datagrid('reload'); 
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
	    var row = $('#schoollist_data').datagrid('getSelected');
	    if (row){
	        $.messager.confirm('Confirm','Are you sure you want to destroy this school?',function(r){
	            if (r){
	                $.post('school/delSchool',{schId:row.schId},function(result){
	                    if (result.success){
	                        $('#schoollist_data').datagrid('reload'); 
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
	
	function viewSchool(row){
		//var row = $('#schoollist_data').datagrid('getSelected');
		var imgurl = row.schLogo;
		var imageUrl;
    	if(imgurl.charAt(0)=="/"){
    		imageUrl=imgurl.substring(1);
    	}
	    if (row){
	        $('#dlgview').dialog('open').dialog('setTitle','学校信息');
	        $("#schImg img").attr("src", imageUrl);
	        $("#schImg").show();
	        $('#fmview').form('load',row);
	    } 
	}
	
	var _page = '${page}';
	_page = jQuery.parseJSON(_page);
	var _h = $("#searchFormPanel").height();
	//datagrid初始化  
    $('#schoollist_data').datagrid({  
        //title:'学校管理列表',  
        iconCls:'icon-edit',//图标  
        width: eoc.cms.main.tabsVolume.width,  
        height: eoc.cms.main.tabsVolume.height-_h,  
        nowrap: false,  
        striped: true,  
        border: true,  
        collapsible:false,//是否可折叠的  
        url:'school/schoolPageList',  
        remoteSort:false,   
        singleSelect:true,//是否单选  
        pagination:true,//分页控件  
        rownumbers:true,//行号  
        frozenColumns:[[  
            {field:'ck',checkbox:true}  
        ]],
		columns:[[
	  		{field:'schName',title:'学校名称', width:200},
	  		{field:'schDescription',title:'学校简介', width:300},
	  		{field:'schAddress',title:'学校地址', width:185},
	  		{field:'schWww',title:'学校网址', width:200},
	  		{field:'schLogo',title:'学校图片', width:160},
	  		{field:'opt',title:'操作',width:120,formatter : function(value,row,index){
	  			var _row = JSON.stringify(row);
	  			var s = "<a href='javascript:void(0);' onclick='viewSchool("+_row+")'>查看</a> ";  
              	return s;
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
				addSchoolFunc(); 
            }  
        },'-',{  
            text: '修改',  
            iconCls: 'icon-edit',  
            handler: function() {  
				modSchoolFunc(); 
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
	eoc.cms.main.createPagination(pageInfo,$('#schoollist_data'));
	
	function queryForm(){
		var _schManageForm = $('#schmanage');
		var _schNameInputVal = _schManageForm.find('input[name=schName]').val();
		var _schAddressInputVal = _schManageForm.find('input[name=schAddress]').val(); 
		var _queryParams = $('#schoollist_data').datagrid('options').queryParams;
        $('#schoollist_data').datagrid({
        	queryParams: {
        		schName_gp: _schNameInputVal,
        		schAddress_gp: _schAddressInputVal,
        		pageIndex : _queryParams.pageIndex,
        		pageSize : _queryParams.pageSize 
        	}
        });
     // 设置分页控件
    	var pageInfo = {
    		pageSize : _page.pageSize,
    		pageList : _page.pageList,
    		params : "key=value"
    	};
    	eoc.cms.main.createPagination(pageInfo, $('#schoollist_data'));
	}
</script>
<script type="text/javascript" src="js/jquery_plugins/ajaxfileupload.js"></script>
<script type="text/javascript" src="js/school/schoolImg_upload.js"></script>
</body>
</html>