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
<div class="easyui-panel" title="点评管理" style="width:1250px">
        <div style="padding: 10px" >
        <form id="cmanage" method="post" buttons="#prodlg-buttons">
            <table>
                <tr>
                    <td class="t">课程名：</td>
                    <td>
                    	<input class="easyui-validatebox" type="text" id="cName" name="cName" style="width:110px;height:20px"></input>
                    </td>
                     <td class="t">点评内容：</td>
                    <td>
                    	<input class="easyui-validatebox" type="text" id="cContent" name="cContent" style="width:120px;height:20px"></input>
                    </td>
                    <td class="t" align="center">来源：</td>
						<td>
							<select  id="cSource" name="cSource"  class="easyui-combobox" data-options="panelHeight:'auto',editable:false" style="width:100px;" >
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

<div id="c_dlgview" class="easyui-dialog" style="width:420px;height:380px;padding:10px 20px" closed="true">
    <div class="ftitle">点评信息预览</div>
    <form id="c_fmview" novalidate>
        <div class="fitem">
            <label>课程名:</label>
            <input name="cName" class="easyui-validatebox" disabled>
        </div>
        <div class="fitem">
            <label>点评人:</label>
            <input name="uName" class="easyui-validatebox" disabled>
        </div>
        <div class="fitem">
            <label>来源:</label>
            <input name="cSource" class="easyui-validatebox" disabled>
        </div>
        <div class="fitem">
            <label>时间:</label>
            <input name="cTime" class="easyui-validatebox" disabled>
        </div>
        <div class="fitem">
            <label style="float: left">点评内容:</label>
            <!-- <input name="fContent" class="easyui-validatebox" disabled> -->
            <textarea id="editor_id" name="cContent" style="width:190px;height:150px;"></textarea>
        </div>
    </form>
</div>

<table id="comment_data" cellspacing="0" cellpadding="0"></table>   <!-- DataGrid -->


<script type="text/javascript">
//显示数据
var _page = '${page}';
_page = jQuery.parseJSON(_page);
var _h = $("#cmanage").height();  
//datagrid初始化  
    $('#comment_data').datagrid({
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
        url:'comment/findComment',  
        remoteSort:false,   
        singleSelect:false,//是否单选  
        pagination:true,//分页控件  
        rownumbers:true,//行号  
        frozenColumns:[[  
            {field:'ck',checkbox:true}     //设置前面的小框
        ]],
	columns:[[
  		{field:'cName',title:'课程名', width:200},
  		{field:'cContent',title:'点评内容', width:270},
  		{field:'uName',title:'点评人', width:200},
  		{field:'cTime',title:'点评时间', width:185,
  			   formatter:function (value,row,index){    //处理时间格式
		  			if(value){
						var time =new Date(value);
						row.cTime=time.format("yyyy-MM-dd hh:mm:ss");             //把时间改成这种格式
		  			return time.format("yyyy-MM-dd hh:mm:ss");  
					}
				 }  
  		},
  		{field:'cSource',title:'来源', width:200},
  		{field:'opt',title:'操作',width:120,formatter : function(value,row,index){
  			var _row = JSON.stringify(row);  
  			var e = "<a href='javascript:void(0);' onclick='delFunc("+_row+")'>删除</a> ";
  			var s = "<a href='javascript:void(0);' onclick='viewComment("+_row+")'>查看</a> ";
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
				var rows = $('#comment_data').datagrid('getSelections'); 
				//判断是否选择行
				if (!rows || rows.length == 0) { 
					$.messager.alert('提示', '请选择要删除的数据!', 'info'); 
				return; 
				}else{
					 $.messager.confirm('Confirm','確定要刪除？',function(r){
				           if (r){
				        	   for(var i =0;i<rows.length;i++)	  {
									$.post('comment/delComment',{cID:rows[i].cID},function(result){
						                   if (result.success){
						                       $('#comment_data').datagrid('reload'); 
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
eoc.cms.main.createPagination(pageInfo,$('#comment_data'));

//多条件查询
function queryForm(){
	seachParams();
}

//重置form
function resetForm(){
	$('#cmanage').form('reset');
}

//封装分页参数
function seachParams(){
var cName=$("#cName").val();
var cContent=$("#cContent").val();
var cSource=$("#cSource").combobox('getValue');
var aTime = $("#aTime").datebox('getValue');
var oTime = $("#oTime").datebox('getValue');
var t=compareTime(aTime,oTime);
if(!t){
	$.messager.alert('错误', '开始日期不能大于结束日期!');
}
var _queryParams = $('#comment_data').datagrid('options').queryParams;
    $('#comment_data').datagrid({
    	queryParams: {
    		cName_gp: cName,
    		cContent_gp: cContent,
    		cSource_gp: cSource,
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
	eoc.cms.main.createPagination(pageInfo,$('#comment_data'));     //再次设置一下就不会出现查询后分页控制使用出错
	
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

function viewComment(row){
    if (row){
        $('#c_dlgview').dialog('open').dialog('setTitle','意见反馈详细信息');
        $('#c_fmview').form('load',row);
    } 
}

function delFunc(row){
	 $.messager.confirm('Confirm','確定要刪除？',function(r){
           if (r){
               $.post('comment/delComment',{cID:row.cID},function(result){
                   if (result.success){
                       $('#comment_data').datagrid('reload'); 
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