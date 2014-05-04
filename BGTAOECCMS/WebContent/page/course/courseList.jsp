<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/header.jsp"%>
<body>
<%@page import="com.gta.oec.cms.enums.course.CourseStateEnum"%>
<div id="searchCoursePanel" class="easyui-panel" title="课程管理" style="height:130px;">
        <div style="padding:15px">
        <form id="coursemanage" method="post" buttons="#coursedlg-buttons">
            <table>
                <tr>
                    <td>
                    	用户名:&nbsp;&nbsp;&nbsp;&nbsp;<input class="easyui-validatebox" type="text" id="uname" name="uname"></input>
                    </td>
                    
                    <td colspan="2">
		                 &nbsp;&nbsp;&nbsp;&nbsp;职位:&nbsp;&nbsp;&nbsp;&nbsp;<input class="easyui-combobox" editable="false" id="searchProId" name="proId"/>&nbsp;&nbsp;
		                 <input class="easyui-combobox" editable="false" id="searchJobPId" name="jobPid"/>&nbsp;&nbsp;
		                 <input class="easyui-combobox" editable="false" id="searchjobId" name="jobId"/>
					&nbsp;&nbsp;状态:&nbsp;&nbsp;&nbsp;&nbsp;
	                	<select  class="easyui-combobox" editable="false" name="status" id="status">
								<option  value="">-------请选择------</option>
								<option value="3">待审核</option>
								<option value="2">已发布</option>
								<option value="4">审核未通过</option>
								<option value="5">已结束</option>
						</select>
                	</td>
                	
                    <td>
                    	&nbsp;&nbsp;&nbsp;&nbsp;校 名:&nbsp;&nbsp;&nbsp;&nbsp;<input class="easyui-validatebox" type="text" id="schname" name="schname"></input>
                    </td>
                
                </tr>
                <tr style="line-height:25px">
                    <td>
                    	课程名:&nbsp;&nbsp;&nbsp;&nbsp;<input class="easyui-validatebox" type="text" id="courname" name="courname"></input>
                    </td>
                    
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;类型:&nbsp;&nbsp;&nbsp;&nbsp;
                    	<select  class="easyui-combobox" editable="false" id="types" name="types">
								<option value="">-------请选择------</option>
								<option value="1">直播</option>
								<option value="2">点播</option>
						</select>
                    </td>
                    
                    	<td>&nbsp;&nbsp;&nbsp;&nbsp;发布日期:&nbsp;&nbsp;&nbsp;&nbsp;
                    		从&nbsp;&nbsp;<input id="ctime" editable="false" name="ctime" type="text" class="easyui-datebox">&nbsp;&nbsp;
                    		到&nbsp;&nbsp;<input id="uTime" editable="false" name="uTime" type="text" class="easyui-datebox">
                    	</td>
                    	
                    	<td>
                	<div id="schdlg-buttons" align="center">
	                	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" onclick="$('form#coursemanage').form('reset');">重置</a>
                		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="queryCourseInfo();">查询</a>
                	</div>	
                	</td>
                </tr>
            </table>
        </form>
        </div>
</div>

<table id="courselist_data" cellspacing="0" cellpadding="0">  </table>
<div id="courseView_Dialog"></div>
<div id="setChannelwin"></div>
<div id="moive"></div>
<div id="courseAmend"></div>

<script type="text/javascript">
//日期框清除按钮
	$('#ctime').datebox({
		width :130,
		editable : false,
		buttons : dateboxbuttons
	});
	$('#uTime').datebox({
		width :130,
		editable : false,
		buttons : dateboxbuttons
	});
	function operator(msg){
		alert(msg);
	}
	var _page = '${page}';
	_page = jQuery.parseJSON(_page);
	var _h = $("#searchCoursePanel").height();
	//datagrid初始化  
    $('#courselist_data').datagrid({  
        title:'课程内容管理',  
        iconCls:'icon-edit',//图标  
       // width: eoc.cms.main.tabsVolume.width,  
        height: eoc.cms.main.tabsVolume.height-_h, 
        nowrap: false,  
        striped: true,  
        border: true,  
        collapsible:false,//是否可折叠的  
        //fit: true,//自动大小  
        url:'course/coursePageList',  
        remoteSort:false,   
		checkOnSelect : false,
		selectOnCheck : false,
		singleSelect : true,//是否单选
        pagination:true,//分页控件  
        rownumbers:true,//行号  
        frozenColumns:[[  
            {field:'ck',checkbox:true}  
        ]],
		columns:[[
			{field:'courseId',title:'课程id',hidden:true}, 
			{field:'jobId',title:'岗位id',hidden:true},
			{field:'jobCourRecommend',title:'推荐课程',hidden:true},
			{field:'userName',title:'会员名', width:100}, 
			{field:'courseName',title:'课程名称', width:200}, 
			{field:'schoolName',title:'所属学校', width:120},
			{field:'liveType',title:'课程类型', width:100,formatter: function(value,row,index){
				if(row.liveType=='1'){
					return '直播';
				}else if(row.liveType=='2'){
					return '点播';
				}
			}},
			{field:'jobName',title:'所属职位', width:120},
			{field:'status',title:'显示状态', width:120,formatter: function(value,row,index){
				if(row.status=='2'){
					return '已发布';
				}else if(row.status=='3'){
					return '待审核';
				}else if(row.status=='4'){
					return '审核未通过';
				}else if(row.status=='5'){
					return '已结束';
				}
			}},
			{field:'cTime',title:'发布时间', width:150,formatter:function(value,row,index){
	  			if(value){
	  				var time =new Date(value); 
		  			return time.format("yyyy-MM-dd hh:mm:ss");  
	  			}
	  			
	  		}},
			{field:'opt',title:'操作', width:190,formatter : function(value,row,index){
				var s='',d='',e='',f='',x='',c='';
					s = '<a href="javascript:void(0);" onclick="viewCourseInfo('+row.courseId+','+row.jobId+')">查看</a> ';
					if(row.status=='3' || row.status=='4'){
						d = '<a href="javascript:void(0);" onclick="checkCourse(1,'+row.courseId+','+row.status+')">审核中</a> ';
					}else if(row.status=='5'){
						d ='';
					}
					if(row.jobCourRecommend=='0'&&row.status!='5'){
						e = '<a href="javascript:void(0);" onclick="courseRecommend(1,'+row.courseId+')">推荐</a> ';
					}else if(row.jobCourRecommend=='1'&&row.status!='5'){
						e = '<a href="javascript:void(0);" onclick="courseRecommend(2,'+row.courseId+')">取消推荐</a> ';
					}
					if(row.status=='2'){
						x = '<a href="javascript:void(0);" onclick="courseDrop('+row.courseId+')">下架</a>'
					}
					if(row.status=='2'){
						f = '<a href="javascript:void(0);" onclick="courseAmend('+row.courseId+')">修改</a>';
					}
					if(row.status=='5'){
						c = '<a href="javascript:void(0);" onclick="againAudit('+row.courseId+')">重新发布</a>'
					}
					 /* return s+"&nbsp;&nbsp;"+d+"&nbsp;&nbsp;"+e+"&nbsp;&nbsp;"+x+"&nbsp;&nbsp;"+c;  */
				return s+"&nbsp;&nbsp;"+d+"&nbsp;&nbsp;"+e+"&nbsp;&nbsp;"+x+"&nbsp;&nbsp;"+f+"&nbsp;&nbsp;"+c;
			}}
		]],
		toolbar: [{  
			          text: '通过审核',  
			          iconCls: 'icon-ok',  
			          handler: function() {  
						passAudit(); 
			          }  
			},{
				text: '课程下架',  
				          iconCls: 'icon-ok',  
				          handler: function() {  
							selectCourseDrops(); 
				          } 	
			}],
		queryParams: {
			'pageIndex': _page.pageIndex,
			'pageSize': _page.pageSize
		}
    }); 

    //设置分页控件
var pageInfo = {
			pageSize: _page.pageSize,
			pageList: _page.pageList,params:"key=value",
			callBack : function() {
				$('#courselist_data').datagrid('clearChecked');
			}
		};
eoc.cms.main.createPagination(pageInfo,$('#courselist_data'));

$(document).ready(function(){
	proSelectInit('searchProId');
	proSelectInit('proId');
	pJobSelectInit('searchJobPId');
	pJobSelectInit('jobPid');
	jobSelectInit('searchjobId');
	jobSelectInit('jobId');
});

function queryCourseInfo(){
	seachByCourseParams();
}


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

function seachByCourseParams(){
	var userName = $('#uname').val();
	var proId = $("#searchProId").combobox('getValue');
	var jobPid = $("#searchJobPId").combobox('getValue');
	var jobId = $("#searchjobId").combobox('getValue');
	var status = $("#status").combobox('getValue');
	var schoolName = $("#schname").val();
	var courseName = $("#courname").val();
	var liveType = $("#types").combobox('getValue');
	var cTime = $("#ctime").datebox('getValue');
	var uTime = $("#uTime").datebox('getValue'); 
	var t=compareTime(cTime,uTime);
	if(!t){
		$.messager.alert('错误', '开始日期不能大于结束日期!');
	}
	var _queryParams = $('#courselist_data').datagrid('options').queryParams;
	$('#courselist_data').datagrid({
    	queryParams: {
    		userName_gp: userName,
    		proId_gp: proId,
    		jobPid_gp: jobPid,
    		jobId_gp: jobId,
    		status_gp: status,
    		schoolName_gp: schoolName,
    		courseName_gp: courseName,
    		liveType_gp: liveType,
    		cTime_gp: cTime,
    		uTime_gp: uTime,
    		pageIndex : _page.pageIndex,
    		pageSize : _page.pageSize 
    	}
    });
	$('#courselist_data').datagrid('clearChecked');
	 // 设置分页控件
	var pageInfo = {
		pageSize : _page.pageSize,
		pageList : _page.pageList,
		params : "key=value",
		callBack : function() {
			$('#courselist_data').datagrid('clearChecked');
		}
	};
	eoc.cms.main.createPagination(pageInfo, $('#courselist_data'));
}

//条件查询，选择行业下拉框时显示数据及触发加载岗位群信息
function proSelectInit(id){
	$("#"+id).combobox({
		url:'course/selectProfession',  
  	  	valueField:'proId',
    	textField:'proName',
   	 	panelHeight:300,
   	 	editable:false,
		onLoadSuccess: function (data) {
			console.log(data);
			if (data) {
    			$("#"+id).combobox('setValue',data[0].proId);
			}
		},
		onSelect:function(record){
			if(!eoc.cms.main.isEmpty(record.proId)){
				var url = 'course/selectPjobByPid?id='+record.proId;
				$.post(
			    	url,
			        function (msg) {
			            if(msg.length<=0){
			            	msg.unshift({ "jobName": "---请选择---", "jobId": "" });
				            $("#searchJobPId").combobox('loadData', msg);
							$("#searchjobId").combobox('loadData', msg);
			            }else{
			            	msg.unshift({ "jobName": "---请选择---", "jobId": "" });
				            $("#searchJobPId").combobox('loadData', msg);
			            }
			    	});
			}else{
				var msg = [{ "jobName": "---请选择---", "jobId": "" }];
	            $("#searchJobPId").combobox('loadData', msg);
				$("#searchjobId").combobox('loadData', msg);
			}
		}
	});
}

//条件查询，父岗位群下拉框点击是显示的数据及加载岗位信息数据
function pJobSelectInit(id){
	$("#"+id).combobox({
		url:'course/selectPjob',  
  	  	valueField:'jobId',
    	textField:'jobName',
    	panelHeight:300,
   	 	editable:false,
		onLoadSuccess: function (data) {
			if (data) {
    			$("#"+id).combobox('setValue',data[0].jobId);
			}
		},
		onShowPanel: function(){
			var pId = $("#searchProId").combobox("getValue");
			var url = 'course/selectPjob';
			if(pId==0){
				$.post(
			   	 		url,
			   	 		function(msg){
				            $("#searchJobPId").combobox('loadData', msg);
			   	 		}
			   	 )	
			}
		},
		onSelect:function(record){
			if(!eoc.cms.main.isEmpty(record.jobId)){
				var url = 'course/selectJobByPid?id='+record.jobId;
				$.post(
			    	url,
			        function (msg) {
			            msg.unshift({ "jobName": "---请选择---", "jobId": "" });
			            $("#searchjobId").combobox('loadData', msg);
			        })
			}else{
				var msg = [{ "jobName": "---请选择---", "jobId": "" }];
	            $("#searchjobId").combobox('loadData', msg);
			}
		}
	});
}

//岗位下拉框点击时加载数据，及根据行业和岗位群信息重新加载岗位信息
function jobSelectInit(id){
	var url;
	$("#"+id).combobox({
		url:'course/selectJob',
  	  	valueField:'jobId',
    	textField:'jobName',
    	panelHeight:300,
   	 	editable:false,
   	 	onShowPanel: function(){
	   	 	var pId = $("#searchProId").combobox("getValue");
	   		var pjobId = $("#searchJobPId").combobox("getValue");
	   		if(pId!=0&&pjobId==0){
	   			url = 'course/selectJob?pId='+pId;
	   		}else if(pId!=0&&pjobId!=0){
	   			url = 'course/selectJob?pId='+pId+'&pjId='+pjobId;
	   		}else if(pId==0&&pjobId!=0){
	   			url = 'course/selectJob?pjId='+pjobId;
	   		}else{
	   			url = 'course/selectJob';
	   		}
	   	 	$.post(
	   	 		url,
	   	 		function(msg){
		            $("#searchjobId").combobox('loadData', msg);
	   	 		}
	   	 	)
   	 	},
		onLoadSuccess: function (data) {
			if (data) {
    			$("#"+id).combobox('setValue',data[0].jobId);
			}
		}
	});
}

//允许发布
function checkCourse(courseType,id,status){
	$.messager.defaults = { ok:"通过", cancel:"不通过" };
	var checkmessage = '';
	if(courseType==1){
		checkmessage="确定通过审核？";
	}
	$.messager.confirm('  ', checkmessage, function(r){
		if (r){
		    $.post('course/checkCourse',{courseId:id,courseType:courseType,status:status},function(result){
		    	if (result.success){
		    		 $.messager.show({
 	                    title: 'Success',
 	                    msg:result.message
 	                });
		    		seachByCourseParams();
                }else{
                	 $.messager.show({    // show error message
                         title: 'Error',
                         msg: '操作失败!'
                     });
                }
		    },'json');
		}else{
			$.post('course/checkCourse',{courseId:id,courseType:'3',status:status},function(result){
		    	if (result.success){
		    		 $.messager.show({
 	                    title: 'Success',
 	                    msg:result.message
 	                });
		    		seachByCourseParams();
                }else{
                	 $.messager.show({    // show error message
                         title: 'Error',
                         msg: '操作失败!'
                     });
                }
		    },'json');
		}
	});
	
}

//推荐
function courseRecommend(reType,id){
	$.messager.defaults = { ok:"确定", cancel:"取消" };
	var remessage = '';
	if(reType==1){
		remessage="确定推荐该课程？";
	}else if(reType==2){
		remessage="取消推荐？";
	}
	$.messager.confirm('',remessage,function(r){
		if(r){
			$.post('course/courseRecommend',{courseId:id,reType:reType},function(result){
				if(result.success){
					$.messager.show({
 	                    title: 'Success',
 	                    msg:result.message
 	                });
					$('#courselist_data').datagrid('reload');
				}else{
               	 $.messager.show({    // show error message
                     title: 'Error',
                     msg: '操作失败!'
                 });
            	}
			},'json');
		}else{
			$.post('course/courseRecommend',{courseId:id,reType:3},function(result){
				if(result.success){
					$.messager.show({
 	                    title: 'Success',
 	                    msg:result.message
 	                });
				}else{
               	 $.messager.show({    // show error message
                     title: 'Error',
                     msg: '操作失败!'
                 });
            	}
			},'json');
		}
	});
}

//下架后重新发布
function againAudit(cid){
	$.messager.defaults = { ok:"确定", cancel:"取消" };
	var remessage = '重新发布此课程？';
	$.messager.confirm(' ',remessage,function(r){
		if(r){
			$.post('course/courseAgainAudit',{courseId:cid},function(result){
				if(result.success){
					$('#courselist_data').datagrid('reload');
				}else{
               	 $.messager.show({    // show error message
                     title: 'Error',
                     msg: '操作失败!'
                 });
            	}
			},'json');
		}
	});
}

//批量审核
function passAudit(){
	$.messager.defaults = { ok:"通过", cancel:"不通过" };
	var row = $('#courselist_data').datagrid('getChecked');
	if(!eoc.cms.main.isEmpty(row)){
		$.messager.confirm(' ', '确定通过审核？', function(r){
			if(r){
				var arr = new Array();
				 var row = $('#courselist_data').datagrid('getSelections');
				 for(var i=0;i<row.length;i++){  
					 var value=row[i].courseId;
					 var status=row[i].status;
					 //状态2表示待审核，4表示审核未通过
					 if(status=='3'||status=='4'){
						 arr[i] = value;
					 }
				 }
				 $.post('course/passAudit',{courseId:arr,type:'1'},function(result){
					if(result.success){
						$('#courselist_data').datagrid('clearChecked');
						$.messager.show({
	 	                    title: 'Success',
	 	                    msg:result.message
	 	                });
						$('#courselist_data').datagrid('reload');
					}else{
		               	 $.messager.show({    // show error message
		                     title: 'Error',
		                     msg: '操作失败!'
		                 });
		            }
				},'json'); 
			}else{
				var arr = new Array();
				 var row = $('#courselist_data').datagrid('getSelections');
				 for(var i=0;i<row.length;i++){  
					 var value=row[i].courseId;
					 var status=row[i].status;
					 if(status=="2"){
						 arr[i] = value;
					 }
				 }
				 $.post('course/passAudit',{courseId:arr,type:'2'},function(result){
					if(result.success){
						$('#courselist_data').datagrid('clearChecked');
						$.messager.show({
	 	                    title: 'Success',
	 	                    msg:result.message
	 	                });
						$('#courselist_data').datagrid('reload');
					}else{
		               	 $.messager.show({    // show error message
		                     title: 'Error',
		                     msg: '操作失败!'
		                 });
		            }
				},'json');
			}
		});
	}else{
		$.messager.alert('提醒','请选择至少一条记录!');
	}
}

//批量下架课程
function selectCourseDrops(){
	$.messager.defaults = { ok:"确定", cancel:"取消" };
	var row = $('#courselist_data').datagrid('getChecked');
	if(!eoc.cms.main.isEmpty(row)){
		$.messager.confirm(' ', '确定下架课程？', function(r){
			if(r){
				var arr = new Array();
				var row = $('#courselist_data').datagrid('getSelections');
				for(var i=0;i<row.length;i++){
					var value=row[i].courseId;
					var status=row[i].status;
					//已发布课程才能下架，状态3表示已发布状态
					if(status=='2'){
						arr[i]=value;
					}
					$.post('course/selectCourseDrops',{courseId:arr},function(result){
						if(result.success){
							$('#courselist_data').datagrid('clearChecked');
							$('#courselist_data').datagrid('reload');
						}else{
			               	 $.messager.show({    // show error message
			                     title: 'Error',
			                     msg: '操作失败!'
			                 });
			            }
					},'json');
				}
			}
		});
	}else{
		$.messager.alert('提醒','请选择至少一条记录!');
	}
}

//下架
function courseDrop(cid){
	$.messager.defaults = { ok:"确定", cancel:"取消" };
	var remessage = '确定下架此课程？';
	$.messager.confirm(' ',remessage,function(r){
		if(r){
			$.post('course/courseDrop',{courseId:cid},function(result){
				if(result.success){
					seachByCourseParams();
				}else{
               	 $.messager.show({    // show error message
                     title: 'Error',
                     msg: '操作失败!'
                 });
            	}
			},'json');
		}
	});
}

//查看
function viewCourseInfo(cid,jobId){
	var _options = {'Did':'courseView_Dialog','title':'查看详情','href':'course/openCourseDetail?id='+cid+'&jobId='+jobId};
	var _id = _options.Did;
	$('#'+_id).dialog({
	    title: _options.title,
	    width: 1000,
	    height: 680, 
	    href: _options.href,
		closable : true,
		collapsible : false,
		minimizable : false,
		maximizable : false,
		doSize : true,
		closed : false,
		resizable : false,
		draggable : false,
		modal : true
	});   
}

//修改课程
function courseAmend(courseId){
	var _options = {'Did':'courseAmend','title':'修改课程信息','href':'course/courseAmend?cid='+courseId};
	var _id = _options.Did;
	$('#'+_id).dialog({
		 	title: _options.title,
		    width: 1000,
		    height: 680, 
		    href: _options.href,
			closable : true,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			doSize : true,
			closed : false,
			resizable : false,
			draggable : false,
			modal : true
	});
}


//直播通道设置
function setLiveChannel(courseId) {
	$('#setChannelwin').dialog({
		title: '直播通道设置',
		width : 400,
		height : 100,
		href : 'course/setLiveChannel?couseId='+courseId,
		modal : true
	});
}

//查看视频
function movieView(courseId){
	$('#moive').dialog({
		title: '查看视频',
		width : 617,
		height : 542,
		href : 'course/movieView?secId='+courseId,
		modal : true
	});
}

</script>

<%@ include file="/page/common/footer.jsp"%>
</body>
</html>