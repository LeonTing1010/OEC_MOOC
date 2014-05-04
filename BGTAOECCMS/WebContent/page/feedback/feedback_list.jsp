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
    .t{
    	padding: 0px 0px 0px 30px;
    }
    #editor_id{
    	margin-left: 3px;
    }
</style>
<div class="easyui-panel" title="意见反馈管理" style="width:1250px">
        <div style="padding: 10px" >
        <form id="fmanage" method="post" buttons="#prodlg-buttons">
            <table>
                <tr>
                    <td class="t">标题：</td>
                    <td>
                    	<input class="easyui-validatebox" type="text" id="fTitle" name="fTitle" style="width:210px;height:20px"></input>
                    </td>
                    <td class="t" align="center">来源：</td>
						<td>
							<select  id="fSource" name="fSource"  class="easyui-combobox" data-options="panelHeight:'auto',editable:false" style="width:100px;" >
								<OPTION  value="">----全部----</OPTION>
								<OPTION  value="PC">PC</OPTION>
								<OPTION  value="APP">APP</OPTION>
							</select>
						</td>
                    <td class="t">
                    <td class="t">时间：</td>
                    <td>
                    		从&nbsp;&nbsp;<input id="aTime" editable="false" name="aTime" type="text" class="easyui-datebox">&nbsp;&nbsp;
                    		到&nbsp;&nbsp;<input id="oTime" editable="false" name="oTime" type="text" class="easyui-datebox">
                    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                    <td>
                	<div id="prodlg-buttons">
                		<a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-undo" onclick="resetForm()">重置</a>
                		<a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-search" onclick="queryForm()">查询</a>
                	</div>	
                	</td>
                </tr>
            </table>
        </form>
        </div>
</div>

<div id="f_dlgview" class="easyui-dialog" style="width:420px;height:380px;padding:10px 20px" closed="true">
    <div class="ftitle">意见反馈信息预览</div>
    <form id="f_fmview" novalidate>
        <div class="fitem">
            <label>标题:</label>
            <input name="fTitle" class="easyui-validatebox" disabled>
        </div>
        <div class="fitem">
            <label>邮箱:</label>
            <input name="fEmail" class="easyui-validatebox" disabled>
        </div>
        <div class="fitem">
            <label>来源:</label>
            <input name="fSource" class="easyui-validatebox" disabled>
        </div>
        <div class="fitem">
            <label>时间:</label>
            <input name="fCtime" class="easyui-validatebox" disabled>
        </div>
        <div class="fitem">
            <label style="float: left">内容:</label>
            <!-- <input name="fContent" class="easyui-validatebox" disabled> -->
            <textarea id="editor_id" name="fContent" style="width:190px;height:150px;"></textarea>
        </div>
    </form>
</div>

<table id="feedback_data" cellspacing="0" cellpadding="0"></table>   <!-- DataGrid -->


<script type="text/javascript">
//显示数据
var _page = '${page}';
_page = jQuery.parseJSON(_page);
var _h = $("#promanage").height();  
//datagrid初始化  
    $('#feedback_data').datagrid({
        //title:'职业管理列表',  
        iconCls:'icon-edit',//图标  
       // width: eoc.cms.main.tabsVolume.width,  
        //height: eoc.cms.main.tabsVolume.height,
 		width: eoc.cms.main.tabsVolume.width,  
        height: eoc.cms.main.tabsVolume.height-_h-70,  
        nowrap: false,  
        striped: true,  
        border: true,  
        collapsible:false,//是否可折叠的  
        url:'feedback/findFeedback',  
        remoteSort:false,   
        singleSelect:false,//是否单选  
        pagination:true,//分页控件  
        rownumbers:true,//行号  
        frozenColumns:[[  
            {field:'ck',checkbox:true}     //设置前面的小框
        ]],
	columns:[[
  		{field:'fTitle',title:'标题', width:200},
  		{field:'fContent',title:'内容', width:270},
  		{field:'fEmail',title:'邮箱', width:200},
  		{field:'fCtime',title:'时间', width:185,
  			  formatter:function (value,row,index){    //处理时间格式
		  			if(value){
						var time =new Date(value);
						row.fCtime=time.format("yyyy-MM-dd hh:mm:ss");             //把时间改成这种格式
		  			return time.format("yyyy-MM-dd hh:mm:ss");  
					}
			  } 
  		},
  		{field:'fSource',title:'来源', width:200},
  		{field:'opt',title:'操作',width:120,formatter : function(value,row,index){
  			var _row = JSON.stringify(row);  
  			var e = "<a href='javascript:void(0);' onclick='delFunc("+_row+")'>删除</a> ";
  			var s = "<a href='javascript:void(0);' onclick='viewFeedback("+_row+")'>查看</a> ";
          	return s+e;
  		}}
	]],
	queryParams: {
		'pageIndex': _page.pageIndex,
		'pageSize': _page.pageSize
	},  
        toolbar: [{  
            text: '删除',  
            iconCls: 'icon-remove',  
            handler: function() {      //多行删除  还需要改进
				//获取表格选择行
				var rows = $('#feedback_data').datagrid('getSelections'); 
				//判断是否选择行
				if (!rows || rows.length == 0) { 
					$.messager.alert('提示', '请选择要删除的数据!', 'info'); 
				return; 
				}else{
					 $.messager.confirm('Confirm','確定要刪除？',function(r){
				           if (r){
				        	   for(var i =0;i<rows.length;i++)	  {
									$.post('feedback/delFeedback',{fID:rows[i].fID},function(result){
						                   if (result.success){
						                       $('#feedback_data').datagrid('reload'); 
						                       $.messager.show({
						   	                    title: 'Success',
						   	                    msg: '删除成功！'
						   	                });
						                   } else {
						                       $.messager.show({    // show error message
						                           title: 'Error',
						                           msg: '删除失败！'
						                       });
						                   }
						               },'json');
								}
				           }
				       });
				}
            } 
	}],  
    });
//设置分页控件
var pageInfo = {
		pageSize: _page.pageSize,
		pageList: _page.pageList,
		params:"key=value"
};				//&key1=value1&key2=value2
eoc.cms.main.createPagination(pageInfo,$('#feedback_data'));

//多条件查询
function queryForm(){
	seachParams();
}

//重置form
function resetForm(){
	$('#fmanage').form('reset');
}

//封装分页参数
function seachParams(){
var fTitle=$("#fTitle").val();
var fSource=$("#fSource").combobox('getValue');
var aTime = $("#aTime").datebox('getValue');
var oTime = $("#oTime").datebox('getValue');
var t=compareTime(aTime,oTime);
if(!t){
	$.messager.alert('错误', '开始日期不能大于结束日期!');
}
var _queryParams = $('#feedback_data').datagrid('options').queryParams;
    $('#feedback_data').datagrid({
    	queryParams: {
    		fTitle_gp: fTitle,
    		fSource_gp: fSource,
    		aTime_gp: aTime,
    		oTime_gp: oTime,
    		
    		//分页参数
    		pageIndex : _queryParams.pageIndex,
    		pageSize : _queryParams.pageSize 
    	}
    });
    var pageInfo = {
    		pageSize: _page.pageSize,
    		pageList: _page.pageList,
    		params:"key=value"
    };						//&key1=value1&key2=value2
	eoc.cms.main.createPagination(pageInfo,$('#feedback_data'));     //再次设置一下就不会出现查询后分页控制使用出错
	
}	
//比较两个时间控件大小
function compareTime(startTime,endTime)  
{  
	var stime,etime;  
	stime = startTime.replace(/-/g,"/");//替换字符，变成标准格式  
	etime = endTime.replace(/-/g,"/");
	var d1 = new Date(Date.parse(stime));  
	var d2 = new Date(Date.parse(etime));
	if(d1>d2){  
	  return false;  
	}else{
		return true;
	}
}  

function viewFeedback(row){
    if (row){
        $('#f_dlgview').dialog('open').dialog('setTitle','意见反馈详细信息');
        $('#f_fmview').form('load',row);
    } 
}

function delFunc(row){
	 $.messager.confirm('Confirm','確定要刪除？',function(r){
           if (r){
               $.post('feedback/delFeedback',{fID:row.fID},function(result){
                   if (result.success){
                       $('#feedback_data').datagrid('reload'); 
                       $.messager.show({
   	                    title: 'Success',
   	                    msg: '删除成功！'
   	                });
                   } else {
                       $.messager.show({    // show error message
                           title: 'Error',
                           msg: '删除失败！'
                       });
                   }
               },'json');
           }
       });
}
</script>
</body>
</html>