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
	$("input[name=schoolId]").clone().appendTo($('#step1Form'));
	$("input[name=step1CourseId]").clone().appendTo($('#step1Form'));
	$("input[name=titlePageImg]").clone().appendTo($('#step1Form'));
	
	$('input[name=recommendVideo]').clone().appendTo($('#step1Form'));
	$.ajax({
		url:ctx+"/course/saveCourseInfo",
		data:$('#step1Form').serialize(),
		type:"post",
		success:function(data){
			if (isNull(data.errorMsg)) {
//				$.messager.alert('提示', '保存成功!');
//				var courseId = data.courseId;
//				setTimeout(function(){
//				},10);
					$('#courseAmendTab').tabs('select', 1);
			}else {
				$.messager.alert('提示', data.errorMsg);
			};
		}
	});
}

function cancelHandle(){
	$('#courseAmend').dialog('close');
	$.ajax({
		url:ctx+"/course/coursePageList",
		type:"post",
		success:function(data){}
	});
}