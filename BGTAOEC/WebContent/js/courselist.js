
var jobGroupId='';
var pageNo=1;
$(function($){
	loadCourse();
})
//加载课程
function loadCourse(){
	$.ajax({
		url:base+"/course/getCourseList/?",
		data:{jobId:jobId,jobGroupId:jobGroupId,proId:proId,pageNo:pageNo},
		error:function(){},
		success:function(data){
		    $('.classList ul').html($('.classList ul').html()+data);
			pageNo = pageNo+1;
		}
	});
}
//初始化请求参数并加载课程信息
function intRequestParaAndLoad(arg0,arg1,arg2) {
	proId = arg0;
	jobGroupId = arg1;
	jobId = arg2;
	pageNo = 1;
	$('.classList ul').html('');
	$('.subCrumbs span').hide();
	loadCourse();
}
//鼠标移至行业名称触发事件
function proMouseover(proId,proName) {
	$('#jobList'+proId).load(base+"/course/getJobPage/?proId="+proId+"&proName="+encodeURI(proName));
}
//单击行业名称触发事件
function proClick(proId,proName){

	intRequestParaAndLoad(proId,'','');
	proTab(proId,proName);	
}
//单击岗位群触发事件
function jobGroupClick(jobGroupId,proId,proName,jobGroupName){
	intRequestParaAndLoad('',jobGroupId,'');
	proTab(proId,proName);
	jobGroupTab(jobGroupId,jobGroupName);
}
//单击岗位触发事件
function jobClick(proId,jobGroupId,jobId,proName,jobGroupName,jobName){
	intRequestParaAndLoad('','',jobId);
	proTab(proId,proName);
	jobGroupTab(jobGroupId,jobGroupName);
	$('.subCrumbs span').last().html(jobName).show();
}
//展示行业标签
function proTab(proId,proName){
	$('.subCrumbs a').first().find('span').html(proName).show();
	$('.subCrumbs a').first().unbind( "click" );
	$('.subCrumbs a').first().bind('click',function(){
		$('#pro'+proId).click();
	});
	
}
//展示岗位群标签
function jobGroupTab(jobGroupId,jobGroupName){
	$('.subCrumbs a').eq(1).find('span').html(jobGroupName).show();
	$('.subCrumbs a').eq(1).unbind( "click" );
	$('.subCrumbs a').eq(1).bind('click',function(){		
		  $('#jobG'+jobGroupId).click();
	});
	
}