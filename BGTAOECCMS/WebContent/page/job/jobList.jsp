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
<div id="searchFormPanel" class="easyui-panel" title="岗位管理" style="width:1250px;height:80px;">
        <div style="padding:10px 0 10px 60px">
        <form id="jobmanage" method="post" buttons="#schdlg-buttons">
            <table>
                <tr>
                    <td>岗位名称:</td>
                    <td>
                    	<input class="easyui-validatebox" type="text" name="jobName" style="width:210px;height:20px"></input>
                    </td>
                	<td style="padding-left:60px">岗位推荐:</td>
                    <td style="padding-right:40px">
                    	<input class="easyui-validatebox" type="text" name="jobRecommend" style="width:210px;height:20px"></input>
                    </td>
                
                	<td>
                	<div id="schdlg-buttons">
                		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" onclick="$('form#jobmanage').form('reset');">重置</a>
                		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="queryForm()">查询</a>
                	</div>	
                	</td>
                </tr>
            </table>
        </form>
        </div>
</div>
    
<table id="joblist_data" cellspacing="0" cellpadding="0"> </table>
<div id="dlg" class="easyui-dialog" style="width:500px;height:550px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
    <div class="ftitle">岗位信息</div>
    <form id="fm" method="post" novalidate>
    	<input name="jobID" type="hidden" >
        <div class="fitem" >
            <label>岗位名称:</label>
            <input name="jobName" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>岗位介绍 :</label>
            <input name="jobDescription" class="easyui-validatebox" required="true">
        </div>
         <div class="fitem">
            <label>岗位收藏数 :</label>
            <input name="jobCollectCount" class="easyui-validatebox" required="true">
        </div>
         <div class="fitem">
            <label>岗位详情:</label>
            <input name="jobDetail" class="easyui-validatebox" required="true">
        </div>
         <div class="fitem">
            <label>发展方向 :</label>
            <input name="jobDevelpping" class="easyui-validatebox" required="true">
        </div>
         <div class="fitem">
            <label>岗位职责:</label>
            <input name="jobDuty" class="easyui-validatebox" required="true">
        </div>
         <div class="fitem">
            <label>岗位需求 :</label>
            <input name="jobDemand" class="easyui-validatebox" required="true">
        </div>
         <div class="fitem">
            <label>岗位级别 :</label>
            <input name="jobLevel" class="easyui-validatebox" required="true">
        </div>
         <div class="fitem">
            <label>薪资行情 :</label>
            <input name="jobWage" class="easyui-validatebox" required="true">
        </div>
         <div class="fitem">
            <label>岗位图片:</label>
            <input name="jobImage" class="easyui-validatebox" required="true">
        </div>
         <div class="fitem">
            <label>岗位推荐 :</label>
            <input name="jobRecommend" class="easyui-validatebox" required="true">
        </div>
    </form>
</div>
<div id="dlg-buttons">
   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveJob(this)">保存</a>
   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
</div>

<div id="dlgview" class="easyui-dialog" style="width:550px;height:600px;padding:10px 20px" closed="true">
    <div class="ftitle">岗位信息预览</div>
     <form id="fmview" novalidate>
     <div class="fitem" >
            <label>岗位名称:</label>
            <input name="jobName" class="easyui-validatebox" disabled>
        </div>
        <div class="fitem">
            <label>岗位介绍 :</label>
            <input name="jobDescription" class="easyui-validatebox" disabled>
        </div>
         <div class="fitem">
            <label>岗位收藏数 :</label>
            <input name="jobCollectCount" class="easyui-validatebox" disabled>
        </div>
         <div class="fitem">
            <label>岗位详情:</label>
            <input name="jobDetail" class="easyui-validatebox" disabled>
        </div>
         <div class="fitem">
            <label>发展方向 :</label>
            <input name="jobDevelpping" class="easyui-validatebox" disabled>
        </div>
         <div class="fitem">
            <label>岗位职责:</label>
            <input name="jobDuty" class="easyui-validatebox" disabled>
        </div>
         <div class="fitem">
            <label>岗位需求 :</label>
            <input name="jobDemand" class="easyui-validatebox" disabled>
        </div>
         <div class="fitem">
            <label>岗位级别 :</label>
            <input name="jobLevel" class="easyui-validatebox" disabled>
        </div>
         <div class="fitem">
            <label>薪资行情 :</label>
            <input name="jobWage" class="easyui-validatebox" disabled>
        </div>
         <div class="fitem">
            <label>岗位图片:</label>
            <input name="jobImage" class="easyui-validatebox" disabled>
        </div>
         <div class="fitem">
            <label>岗位推荐 :</label>
            <input name="jobRecommend" class="easyui-validatebox" disabled>
        </div>
    </form>
</div>
<script type="text/javascript">
	var url;
	function addFunc(){
	    $('#dlg').dialog('open').dialog('setTitle','添加岗位信息');
	    $('#fm').form('clear');
	    url = 'job/saveJob';
	}
	
	function modFunc(){
	    var row = $('#joblist_data').datagrid('getSelected');
	    if (row){
	        $('#dlg').dialog('open').dialog('setTitle','编辑岗位信息');
	        $('#fm').form('load',row);
	       	url = 'job/updateJob';
	    }else{
	    	$.messager.alert('提醒','请选择一条记录!');
	    }
	}
	
	function saveJob(obj){
	    $('#fm').form('submit',{
	        url: url,
	        onSubmit: function(){
	            return $(this).form('validate');
	        },
	        success: function(result){
	        	$('#dlg').dialog('close');       
	            var result = eval('('+result+')');
	            if (result.success){
	                $('#joblist_data').datagrid('reload'); 
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
	    var row = $('#joblist_data').datagrid('getSelected');
	    if (row){
	        $.messager.confirm('Confirm','Are you sure you want to destroy this job?',function(r){
	            if (r){
	                $.post('job/delJob',{jobID:row.jobID},function(result){
	                    if (result.success){
	                        $('#joblist_data').datagrid('reload'); 
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
	
	function viewJob(row){
		var row = $('#joblist_data').datagrid('getSelected');
	    if (row){
	        $('#dlgview').dialog('open').dialog('setTitle','岗位信息');
	        $('#fmview').form('load',row);
	    } 
	}
	
	function queryForm(){
		var _jobmanageForm = $('#jobmanage');
		var _jobNameInputVal = _jobmanageForm.find('input[name=jobName]').val();
		var _jobRecommendInputVal = _jobmanageForm.find('input[name=jobRecommend]').val(); 
		var _queryParams = $('#joblist_data').datagrid('options').queryParams;
        $('#joblist_data').datagrid({
        	queryParams: {
        		jobName_gp: _jobNameInputVal,
        		jobRecommend_gp: _jobRecommendInputVal,
        		pageIndex : _queryParams.pageIndex,
        		pageSize : _queryParams.pageSize 
        	}
        });
	}
	
	var _page = '${page}';
	var _h = $("#searchFormPanel").height();
	//datagrid初始化  
    $('#joblist_data').datagrid({  
        //title:'岗位管理列表',  
        iconCls:'icon-edit',//图标  
        width: eoc.cms.main.tabsVolume.width,  
        height: eoc.cms.main.tabsVolume.height-_h,  
        nowrap: false,  
        striped: true,  
        border: true,  
        collapsible:false,//是否可折叠的  
        url:'job/jobPageList',  
        remoteSort:false,   
        singleSelect:true,//是否单选  
        pagination:true,//分页控件  
        rownumbers:true,//行号  
        frozenColumns:[[  
            {field:'ck',checkbox:true}  
        ]],
		columns:[[
	  		{field:'jobName',title:'岗位名称', width:200},
	  		{field:'jobDescription',title:'岗位介绍', width:300},
	  		{field:'jobCollectCount',title:'岗位收藏数', width:185},
	  		{field:'jobDevelpping',title:'发展方向', width:200},
	  		{field:'jobDuty',title:'岗位职责', width:160},
	  		
	  		{field:'jobDemand',title:'岗位需求', width:200},
	  		{field:'jobLevel',title:'岗位级别', width:300},
	  		{field:'jobWage',title:' 薪资行情', width:185},
	  		{field:'jobCtime',title:'创建时间', width:200},
	  		{field:'jobRecommend',title:'岗位推荐', width:160},
	  		
	  		{field:'opt',title:'操作',width:120,formatter : function(value,row,index){
	  			var _row = JSON.stringify(row);
	  			var s = "<a href='javascript:void(0);' onclick='viewJob("+_row+")'>查看</a> ";  
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
	var pageInfo = {pageSize: _page.pageSize,pageList: _page.pageList,params:"key=value"};
	eoc.cms.main.createPagination(pageInfo,$('#joblist_data'));
</script>
</body>
</html>