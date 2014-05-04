var pageNo = 1;
$(function($){
	 loadJob();
})
//鼠标移至行业触发事件
function proMouseover(proId,proName) {
	$('#jobGroup'+proId).load(base+"/job/getJobGroupPage/?proId="+proId+"&proName="+encodeURI(proName));
}
//加载岗位
function loadJob(){
	$.ajax({
		url:base+"/job/getJobList/?",
		data:{jobGroupId:jobGroupId,tradeId:tradeId,pageNo:pageNo},
		error:function(){},
		success:function(data){
		    $('.professions ul').html($('.professions ul').html()+data);
			pageNo = pageNo+1;
			getJobArray = true;
			checkJobCollect();	
		}
	});
}
//初始化请求参数并加载岗位信息
function intRequestParaAndLoad(arg0,arg1) {
	tradeId = arg0;
	jobGroupId = arg1;
	pageNo = 1;
	$('.professions ul').html('');
	$('.subCrumbs span').hide();
	$('.subCrumbs span').first().show();
	loadJob();
}
//单击行业触发事件
function proClick(tradeId,tradeName) {
	intRequestParaAndLoad(tradeId,'');
	proTab(tradeId,tradeName);
}
//单击岗位群触发事件
function jobGroupClick(jobGroupId,tradeId,jobGroupName,tradeName) {
	intRequestParaAndLoad('',jobGroupId);
	proTab(tradeId,tradeName);	
	jobGroupTab(jobGroupId,jobGroupName);
}
//展示行业标签
function proTab(tradeId,tradeName){
	$('.subCrumbs a').eq(1).find('span').html(tradeName).show();
	$('.subCrumbs a').eq(1).unbind( "click" );
	$('.subCrumbs a').eq(1).bind('click',function(){
		$('#pro'+proId).click();
	});	
}
//展示岗位群标签
function jobGroupTab(jobGroupId,jobGroupName){
	$('.subCrumbs a').eq(2).find('span').html(jobGroupName).show();
	$('.subCrumbs a').eq(2).unbind( "click" );
	$('.subCrumbs a').eq(2).bind('click',function(){
		$('#jobGroup'+jobGroupId).click();
	});
}
//岗位收藏
 
var refsh = true;
function collectJob(tag) {
	oec.login.isLogin(function() {
		if(refsh){
			getJobArray = true;
			refsh = false;
			checkJobCollect();			
		}
		var type = 2;
		if ($(tag).hasClass("empty") ) {
			type = 1;
		 }
		$.ajax({
			url:base+'/job/jobCollect/',
			data:{jobId:$(tag).attr('data-id'),type:type},
			error:function(){},
			success:function(data){
				//判断是否登录
				if (data.loginFlag) {
					if (data.flag) {
						if (type==1) {
							$(tag).removeClass('empty');
							$(tag).attr('title','取消收藏');
						}
						if (type==2) {
							$(tag).addClass('empty');
							$(tag).attr('title','收藏职位');
						}
					}
				}
			}
		});
	});		

}
//如果用户处于登录状态，则选中已收藏的岗位
var jobArray;
//状态标识，是否需要重新获取最新的岗位收藏列表
var getJobArray = true;
function checkJobCollect(){
	if (!getJobArray) {
		$.each(jobArray,function(index,data){
			$("#job"+data.jobId).removeClass("empty").attr('title','取消收藏');
		});		
		return ;
	}
	$.ajax({
		url:base+'/job/hasJobCollect/',
		data:{},
		error:function(){},
		success:function(data){
			jobArray = data;
			getJobArray = false;
			checkJobCollect();
		}
	});
}