function step4Prev(){
	loadUpload(3,$('input[name=courseId]').val());
}
//修改课程状态 暂不发布/发布课程
function updateStatus(status){
    if ('1'==status) {
    	eoc.success('温馨提示','课程暂不发布！',toCourseBroad);
    	return ;
	}
	$.ajax({
		url:base+"/teacherCenter/upload/step4/updateCourseStatus/",
		type:"post",
		data:{status:status,courseId:$('input[name=courseId]').val()},
		success:function(data){
			if (data.result) {
				eoc.success('温馨提示','课程提交审核成功！',toCourseBroad);
			}
		}
	});

}
function toCourseBroad(){
	if(liveType==2){
		switchLoad('1'); 
	}else{
		switchLoad('2'); 
	}
}