function saveUpdate(){
	if (!checkStep1()) {
		 return ;	
	}
	if ($('.form-bodyTab .error-icon').length>0) {
		return false;	
	}
	$('#step1Form input').remove();
	$("input[name=titlePage]").clone().appendTo($('#step1Form'));
	$("input[name=previewUrl]").clone().appendTo($('#step1Form'));
	$("input[name=type]").clone().appendTo($('#step1Form'));
	$("input[name=job]").clone().appendTo($('#step1Form'));
	$("input[name=courseName]").clone().appendTo($('#step1Form'));
	$('<input>').attr('name','introduction').val($("textarea[name=introduction]").val()).appendTo($('#step1Form'));
	$('<input>').attr('name','scoreMethod').val($("textarea[name=scoreMethod]").val()).appendTo($('#step1Form'));
	$("input[name=credit]").clone().appendTo($('#step1Form'));
	$("input[name=price]").clone().appendTo($('#step1Form'));
	$("input[name=step1CourseId]").clone().appendTo($('#step1Form'));
	$.ajax({
		url:base+"/teacherCenter/upload/saveStep1/",
		data:$('#step1Form').serialize(),
		type:"post",
		success:function(data){
			if (isNull(data.errorMsg)) {
				eoc.alert("提示","保存成功");
				setTimeout(function(){
					updateCourse($("input[name=step1CourseId]").val());
				},10);
				
			}else {
				eoc.alert("提示",data.errorMsg);
			};
		}
	});
}