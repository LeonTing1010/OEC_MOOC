var flag = false;

//首次选择岗位，加载岗位信息
function showJobList() {
	var html = $('#selectJob .list').html().replace(/\s+/g, "").replace(
			/[\r\n]/g, "").replace(/<\/?.+?>/g, "");
	if (eoc.cms.main.isEmpty(html)) {
		$('#selectJob .list').load(ctx + "/course/step1_job", function() {
			$('#selectJob').removeClass('hide');
			//给checkbox绑定选中函数
			$('#selectJob .list input[type=checkbox]').click(function(){
				var id = $(this).attr('id');			   
			   if (!$(this).is(":checked")&&flag) {
				  $('#jobs'+id).remove();
			   }else {				  
					var $i = $("<i>").html('x').click(function(){
						$('input[id='+id+']').removeAttr('checked');
						$(this).parent().remove();
					});
					var $span = $('<span>').html($(this).parent().find('label').html());
					$span.attr('id','jobs'+id);
					$i.appendTo($span);
					$span.appendTo($('#selectJob .selected'));
			  }	
			});
			// 展示已选择的岗位
			$.each(jobStr, function(index, value) {
				$('#selectJob .list input[id=' + value +']').click();
			});			
			flag = true;
		});
	} else {
		$('#selectJob').removeClass('hide');
	}

}

function errorSpan(msg) {
	var $i = $('<i>').addClass('error-icon');
	var $pspan = $('<span>').addClass('validation');
	var $espan = $('<span>').addClass('error').attr('style', 'color:red;')
			.html(msg);
	$i.appendTo($pspan);
	$espan.appendTo($pspan);
	return $pspan;
}
function rightSpan() {
	var $i = $('<i>').addClass('ok-icon');
	var $pspan = $('<span>').addClass('validation');
	var $espan = $('<span>').addClass('ok');
	$espan.html('&nbsp');
	$i.appendTo($pspan);
	$espan.appendTo($pspan);
	return $pspan;
}
function removePoint($obj) {
	$obj.find('.validation').remove();
}

// 岗位选择完成
function jobOk() {
	checkCount = $("#jobSelectCount").children("span").length;
	$('.selectTip').html('已选择' + checkCount + '岗位');
	$('#selectJob').addClass('hide');
}

// 校验课程名称是否重复
function checkCourseName() {
	var courseName = $('input[name=courseName]').val();
	var $p = $('input[name=courseName]').parent();
	removePoint($p);
	if (eoc.cms.main.isEmpty(courseName)) {
		errorSpan('课程名称为空').appendTo($p);
		return false;
	}
	if (courseName.length > 20) {
		errorSpan('超出长度限制').appendTo($p);
		return false;
	}
	var oldCourseName = $('input[name=oldCourseName]').val();
	if (!eoc.cms.main.isEmpty(oldCourseName) && (oldCourseName == courseName)) {
		return true;
	}
	var rtFalg = true;
	$.ajax({
		url : ctx + "/course/checkCourseName",
		data : {
			courseName : courseName
		},
		async : false,
		error : function() {

		},
		success : function(data) {
			if (data.result) {
				var uCname = $('input[name=updateCame]').val();
				if (uCname != courseName) {
					errorSpan('课程名称重复').appendTo($p);
				} else {
					rightSpan().appendTo($p);
				}
				rtFalg = false;
			} else {
				rightSpan().appendTo($p);
			}
		}
	})
	return rtFalg;
}
function changeIntroduction() {
	var introduction = $('textarea[name=introduction]').val();
	var $p = $('textarea[name=introduction]').parent();
	$p.find('.validation').remove();
	$p.find('span').first().html(introduction.length + '/500');
	if (introduction.length > 500) {
		$p.find('span').first().html('500/500');
		$('textarea[name=introduction]').val(
				$('textarea[name=introduction]').val().substring(0, 500));
		errorSpan('超出长度限制').appendTo($p);
		return false;
	}
}
function changeScoreMethod() {
	var introduction = $('textarea[name=scoreMethod]').val();
	var $p = $('textarea[name=scoreMethod]').parent();
	$p.find('.validation').remove();
	$p.find('span').first().html(introduction.length + '/500');
	if (introduction.length > 500) {
		$p.find('span').first().html('500/500');
		$('textarea[name=scoreMethod]').val(
				$('textarea[name=scoreMethod]').val().substring(0, 500));
		errorSpan('超出长度限制').appendTo($p);
		return false;
	}
}
// 校验简介
function checkIntroduction() {
	var introduction = $('textarea[name=introduction]').val();
	var $p = $('textarea[name=introduction]').parent();
	removePoint($p);
	if (!introduction) {
		errorSpan('课程简介为空').appendTo($p);
		return false;
	}
	if (introduction.length > 500) {
		errorSpan('超出长度限制').appendTo($p);
		return false;
	}
	rightSpan().appendTo($p);
	return true;
}
// 校验评分方法
function checkScoreMethod() {
	var scoreMethod = $('textarea[name=scoreMethod]').val();
	var $p = $('textarea[name=scoreMethod]').parent();
	removePoint($p);
	if (!scoreMethod) {
		errorSpan('课程评分方法为空').appendTo($p);
		return false;
	}
	if (scoreMethod.length > 1000) {
		errorSpan('超出长度限制').appendTo($p);
		return false;
	}
	rightSpan().appendTo($p);
	return true;
}
// 校验课程学分
function checkCredit() {
	var credit = $('input[name=credit]').val();
	var $p = $('input[name=credit]').parent();
	removePoint($p);
	if (eoc.cms.main.isEmpty(credit)) {
		errorSpan('课程学分 为空').appendTo($p);
		return false;
	}
	if (!(isNum(credit) || isFloat(credit))) {
		errorSpan('课程学分必须为数字且大于零').appendTo($p);
		return false;
	}
	if (parseFloat(credit) <= 0) {
		errorSpan('课程学费必须大于0').appendTo($p);
		return false;
	}
	if (parseFloat(credit) > 100) {
		errorSpan('超过学分最大值').appendTo($p);
		return false;
	}
	rightSpan().appendTo($p);
	return true;
}
// 校验价格
function checkPrice() {
	var price = $('input[name=price]').val();
	var $p = $('input[name=price]').parent();
	removePoint($p);
	if (eoc.cms.main.isEmpty(price)) {
		errorSpan('课程价格为空').appendTo($p);
		return false;
	}
	if (!isNum(price)) {
		errorSpan('课程价格必须为数字').appendTo($p);
		return false;
	}
	if (parseInt(price, 10) < 0) {
		errorSpan('课程价格必须大于0').appendTo($p);
		return false;
	}
	rightSpan().appendTo($p);
	return true;
}
// 校验图片
function checkImg() {
	var titlePage = $('input[name=titlePageImg]').val();
	if (eoc.cms.main.isEmpty(titlePage)) {
		$.messager.alert('提示', '图片为空!');
		return false;
	}
	if (!eoc.cms.main.isEmpty(titlePage) && titlePage.indexOf('jpg') == -1) {
		$.messager.alert('提示', '图片格式必须为jpg');
		return false;
	}
	return true;
}
// 校验视频格式
function checkVideo() {
	var video = $('input[name=previewUrl]').val();
	if (eoc.cms.main.isEmpty(video)) {
		$.messager.alert('提示', '预览视频为空');
		return false;
	}
	if (!eoc.cms.main.isEmpty(video)
			&& !((video.indexOf('flv') != -1) || (video.indexOf('mp4') != -1))) {
		$.messager.alert('提示', '预览视频格式必须为flv或mp4');
		return false;
	}
	return true;
}
function fileChange(tag, type) {
	if ("1" == type) {
		var titlePage = $('input[name=titlePageFile]').val();
		if (eoc.cms.main.isEmpty(titlePage)) {
			$.messager.alert('提示', '图片为空');
			return false;
		}
		if (!eoc.cms.main.isEmpty(titlePage) && titlePage.indexOf('jpg') == -1) {
			$.messager.alert('提示', '图片格式必须为jpg');
			return false;
		}
 		 //上传图片
 		if(type==1){
 			//修改
 	 		var fileNamed="";
 	 		//已上传的图片路径
 	 		var courseImgObj= $("input[id=titlePageImg]");
 	 		 fileNamed=courseImgObj.val();
 			 
 			//已保存数据库的图片路径, 上传不删除保存数据库的图片
 			var titlePage= $("#titlePage").val();
 			if(titlePage == courseImgObj.val()){
 				 fileNamed="";
 			}
 			$.ajaxFileUpload({
 		 		url:'course/uploadCourseImg',
 		 		secureuri:false,
 		 		fileElementId:'titlePageFile',
 				dataType:'json',
 				data: {'fileNamed': fileNamed},  
 		 		success:function(date,status){
 		 			if(date.userFileName!=null){
 				 		var url=cache+date.urlPath+date.userFileName;
 				 		$("img[id=show2]").attr("src",url);
 				 		courseImgObj.val(date.urlPath+date.userFileName);	
 				 		$.messager.show({
 		                    title: '上传成功',
 		                    msg:'上传成功'
 		                });
 		 			}else{
 		 				$.messager.show({
 		                    title: date.title,
 		                    msg:date.message
 		                });
 		 	 		} 
 		 		},
 		 			error:function(date,status,e){
 		 			alert(e);
 		 			}
 		 		})
 		}  
 		
 		return false;
//		uploadImg();
	}
	//上传视频
	if ("2" == type) {
		var video = $('input[name=previewFile]').val();
		if (eoc.cms.main.isEmpty(video)) {
			$.messager.alert('提示', '预览视频为空!');
			return false;
		}
		if (!eoc.cms.main.isEmpty(video)
				&& !((video.indexOf('flv') != -1) || (video.indexOf('mp4') != -1))) {
			$.messager.alert('提示', '预览视频格式必须为flv或mp4');
			return false;
		}
		uploadVideo();
		$(tag).parents('.form-row').find("span").last().html($(tag).val());
		$(tag).parents('.form-row').find("div").last().show();
	}

}

function uploadVideo() {
	var fileNamed="";
	var courseVideoObj= $("input[id=introducationVideo]");
	fileNamed = courseVideoObj.val();
	var recommendVideo= $("#recommendVideo").val();
	if(recommendVideo == courseVideoObj.val()){
		fileNamed="";
	}
	$('.form-button-bar').hide();
	startUpload();
	$.ajaxFileUpload({
	 		url:'course/uploadVideo',
	 		secureuri:false,
	 		fileElementId:'previewFile',
			dataType:'json',
			data: {'fileNamed': fileNamed},  
	 		success:function(date,status){
	 			if(date.dataUrl!=null){
	 				endUpload();
	 				$('input[name=previewUrl]').val(date.dataUrl);
	 				$('input[name=resourceName]').val(date.resourceName);
			 		$("#realName").html(date.dataUrl);
			 		courseVideoObj.val(date.dataUrl);	
			 		$.messager.show({
	                    title: '上传成功',
	                    msg:'上传成功'
	                });
	 			}else{
	 				$.messager.show({
	                    title: '',
	                    msg:date.message
	                });
	 	 		} 
	 		},
	 			error:function(date,status,e){
	 			alert(e);
	 			}
	 		})
//	$('#previewForm').submit();
}
// 图片上传
//function uploadImg() {
//	$('#imgForm').submit();
//}
// 提交前校验各个表单信息
function checkStep1() {
	if (!checkCourseName()) {
		return false;
	}
	if (!checkImg()) {
		return false;
	}
	if (!checkIntroduction()) {
		return false;
	}
	if (!checkVideo()) {
		return false;
	}
	if (!checkScoreMethod()) {
		return false;
	}
	if (!checkCredit()) {
		return false;
	}
	if (!checkPrice()) {
		return false;
	}
	if (checkCount <= 0) {
		$.messager.alert('提示', '请选择岗位!');
		return false;
	}
	return true;
}
// 第一步表单提交
function step1Sub() {
	if (!checkStep1()) {
		return;
	}
	$('#step1Form input').remove();
	$("input[name=titlePage]").clone().appendTo($('#step1Form'));
	$("input[name=previewUrl]").clone().appendTo($('#step1Form'));
	$("input[name=type]").clone().appendTo($('#step1Form'));
	$("input[name=job]").clone().appendTo($('#step1Form'));
	$("input[name=courseName]").clone().appendTo($('#step1Form'));
	$('<input>').attr('name', 'introduction').val(
			$("textarea[name=introduction]").val()).appendTo($('#step1Form'));
	$('<input>').attr('name', 'scoreMethod').val(
			$("textarea[name=scoreMethod]").val()).appendTo($('#step1Form'));
	$("input[name=credit]").clone().appendTo($('#step1Form'));
	$("input[name=price]").clone().appendTo($('#step1Form'));
	$("input[name=step1CourseId]").clone().appendTo($('#step1Form'));
	$('input[name=resourceName]').clone().appendTo($('#step1Form'));
	$.ajax({
		url : ctx + "/course/saveCourseInfo",
		data : $('#step1Form').serialize(),
		type : "post",
		success : function(data) {
			step1Ok(data.courseId, data.errorMsg);
		}
	});
}
// 第一步完成之后
function step1Ok(courseId, errorMsg) {
	// 加载第二步
	$('input[name=courseId]').val(courseId);
	if (eoc.cms.main.isEmpty(errorMsg)) {
		loadUpload(2, courseId);
	} else {
		$.messager.alert('提示', errorMsg);
	}

}

// 第一步图片上传成功
function uploadImgOk(dataUrl, errorMsg, resourceName) {

	if (eoc.cms.main.isEmpty(errorMsg)) {
		$('input[name=titlePage]').parent().find('img').remove();
		$('input[name=titlePage]').val(dataUrl);
		var $img = $('<img>').css("height", "80px;").css("width", "120px")
				.attr("src", cache + dataUrl);
		$img.insertAfter($('input[name=titlePage]'));
	} else {
		$.messager.alert('提示', errorMsg);
	}
}
// 第一步视频上传成功
function uploadVideoOk(dataUrl, errorMsg, resourceName) {
	if (eoc.cms.main.isEmpty(errorMsg)) {
		endUpload();
		$('input[name=previewUrl]').val(dataUrl);
		$('input[name=resourceName]').val(resourceName);
		$('#realName').text(dataUrl);
	} else {
		$.messager.alert('提示', errorMsg);
	}
}

// 在IE下显示默认提示
$(document)
		.ready(
				function() {
					var doc = document, inputs = doc
							.getElementsByTagName('input'), supportPlaceholder = 'placeholder' in doc
							.createElement('input'), placeholder = function(
							input) {
						var text = input.getAttribute('placeholder'), defaultValue = input.defaultValue;
						input.style.color = "gray";

						if (defaultValue == '') {
							input.value = text;
						}
						input.onfocus = function() {
							if (input.value === text) {
								this.value = '';
							}
							input.style.color = "black";
						};
						input.onblur = function() {
							if (input.value === '') {
								this.value = text;
								input.style.color = "gray";
							}
						}
					};
					if (!supportPlaceholder) {
						for ( var i = 0, len = inputs.length; i < len; i++) {
							var input = inputs[i], text = input
									.getAttribute('placeholder');
							if (input.type === 'text' && text) {
								placeholder(input);
							}
						}
					}
				});
