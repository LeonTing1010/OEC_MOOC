//章单击选中时间
var sectionId;
function clickSection(tag) {
	var $span = $(tag);
	$(tag).parents('dl').find('dd').toggle(350,function(){
		if ($(tag).parents('dl').find('dd').is(":hidden")) {
			$span.html("+");
		}else {
			$span.html("-");
		}
	});
}
//单击节触发
function clickPart(tag){
	$('input[name=sectionId]').val($(tag).attr('data-id'));
	sectionId = $(tag).attr('data-id');
	$('#thirdGrid .rightBox').load(ctx+"/course/step3/getRight?sectionId="+$(tag).attr('data-id')+"&courseId="+$('input[name=courseId]').val()+"&code="+Math.random(),function(){
		$('#thirdGrid .rightBox span').first().html($(tag).find('span').html());
	});
}
function clickSec() {
	$('#thirdGrid .rightBox').html('');
}
//第三步保存
function step3Sub() {
	var knowledge = $('textarea').val();
	if (isNull(knowledge)) {
		$.messager.alert('提示', '核心知识点不允许为空！');
		return false;
	}
	if (knowledge.length>500) {
		$.messager.alert('提示', '核心知识点不能大于500字！');
		return false;
	}
    $('#step3Form input').remove();
    $('#step3Form textarea').remove();
    $('input[name=videoUrl]').clone().appendTo($('#step3Form'));
    $('input[name=aidResource]').clone().appendTo($('#step3Form'));
    $('<input>').attr('name','coreKnowledge').val(knowledge).appendTo($('#step3Form'));
    $('input[name=sectionId]').clone().appendTo($('#step3Form'));
	var $input = $('input[name=courseId]').clone().attr('name', 'step3CourseId');
	$input.appendTo($('#step3Form'));
	$('input[name=videoResourceName]').clone().appendTo($('#step3Form'));
	$('input[name=aidResourceName]').clone().appendTo($('#step3Form'));
	$('input[name=movieResource]').clone().appendTo($('#step3Form'));
	
	$.ajax({
		url:ctx+"/course/upload/saveStep3",
		type:"post",
		data:$('#step3Form').serialize(),
		success:function(data){
			if (data.result) {
				$.messager.alert('提示','保存成功');
			    $('#step3Form input').remove();
			    $('#step3Form textarea').remove();
			}else {
				$.messager.alert('提示',data.errorMsg);
			}
		}
	})
}

function returnSteptwo(){
	$('#courseAmendTab').tabs('select', 1);
}

function reduceRe(tag){
	 deleteResource($(tag).attr('data-id'));
	 $(tag).parent().remove();
	 var reduceName = $("#reduceName").html();
	 $.ajax({
   		url:ctx+'/course/uploadfzFileDelete',
   		data:{uploadurl:reduceName},
   		error:function(){},
   		success:function(){	}
   	});
}

function fileChange(tag,type){
	var flag = true;
	if ('1'==type) {
		flag =  checkVideos($(tag));
	}
	if ('2'==type) {
		flag =  checkAidReourse($(tag));
	}

}
//校验视频格式
function checkVideos($obj){
	if (!isNull($obj.val())) {
		if (!(($obj.val().indexOf('flv')!=-1)||($obj.val().indexOf('mp4')!=-1))) {			
			$.messager.alert('提示','视频格式必须为flv或mp4');
			return false;
		}
		var fileNamed="";
		var courseVideoObj= $("input[id=resourceMovie]");
		fileNamed = courseVideoObj.val();
		var recommendVideo= $("#movieResource").val();
		if(recommendVideo == courseVideoObj.val()){
			fileNamed="";
		}
		startUpload();
		$('.thirdStep').hide();
		$.ajaxFileUpload({
		 		url:'course/upload/uploadSectionVideo',
		 		secureuri:false,
		 		fileElementId:'mainFile',
				dataType:'json',
				data: {'fileNamed': fileNamed},  
		 		success:function(date,status){
		 			if(isNull(date.message)){		 				
		 				$('#alreadyResource').remove();
		 				$('.thirdStep').show();
		 				 endUpload();
		 				$pform = $("input[name=mainFile]").parents('.form-row');
		 				$pform.find('.input').remove();
		 				$('input[name=videoUrl]').val(date.dataUrl);
		 				$('input[name=videoResourceName]').val(date.resourceName);
		 				var $div = $('<div>').addClass("reduceBtn").html('-');
		 				var $span = $('<span>').addClass('text').addClass("mr10");
		 				var $pdiv = $('<div>').addClass("input");
		 				$pdiv.css("margin-left","180px");
		 				$span.appendTo($pdiv);
		 				$div.appendTo($pdiv);
		 				$pdiv.find('span').html($("input[name=mainFile]").val());
		 				$pdiv.find('.reduceBtn').click(function(){
			 				$(this).parent().remove(); 	 
			 				$('input[name=videoUrl]').val('');
			 				$('input[name=videoResourceName]').val('');
		 				});
		 				$pdiv.appendTo($pform);
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
	 }
	return true;
}
//校验辅助资源格式
function checkAidReourse($obj){
	if (!isNull($obj.val())) {
		if ($obj.val().indexOf('swf')==-1) {
			eoc.alert('提示','辅助资源格式必须为swf');
			return false;
		}
		startUploadLocal();
		$('.thirdStep').hide();
		$('#aidFileForm').submit();
	 }
	return true;
}
//删除资源
function deleteResource(resourceId) {
//	$.ajax({
//		url:ctx+"/course/upload/step3/deleteResource/",
//		type:"post",
//		data:{resourceId:resourceId,sectionId:sectionId,courseId:$('input[name=courseId]').val()},
//		success:function(data){
//			if (data.result) {
//				
//			}
//		}
//	});
} 

//下一步
function step3Next(){
	$.ajax({
		url:ctx+"/course/upload/checkStep3/",
		type:"post",
		data:{courseId:$('input[name=courseId]').val()},
		success:function(data){
			if (data.result) {
				loadUpload(4,$('input[name=courseId]').val());
			}else {
				$.messager.alert('温馨提示','有部分节未编辑核心知识点！');
			}
		}
	});

}
//视频上传成功后的操作
function uploadSectionOk(dataUrl, errorMsg,resourceName) {
	$('#alreadyResource').remove();
	$('.thirdStep').show();
	 endUpload();
	if (isNull(errorMsg)) {			
		 $pform = $("input[name=mainFile]").parents('.form-row');
		 $pform.find('.input').remove();
		 $('input[name=videoUrl]').val(dataUrl);
		 $('input[name=videoResourceName]').val(resourceName);
		 var $div = $('<div>').addClass("reduceBtn").html('-');
		 var $span = $('<span>').addClass('text').addClass("mr10");
		 var $pdiv = $('<div>').addClass("input");
		 $span.appendTo($pdiv);
		 $div.appendTo($pdiv);
		 $pdiv.find('span').html($("input[name=mainFile]").val());
	     $pdiv.find('.reduceBtn').click(function(){
	          $(this).parent().remove(); 	 
	          $('input[name=videoUrl]').val('');
	          $('input[name=videoResourceName]').val('');
	     });
	     $pdiv.appendTo($pform);
	} else {
		$.messager.alert('提示', errorMsg);
		return ;
	}


}
//辅助资源上传成功
function uploadAidResourceOk(dataUrl, errorMsg,resourceName) {
	$('.thirdStep').show();
	 endUpload();
	if (isNull(errorMsg)) {				
	} else {
		$.messager.alert('提示', errorMsg);
		return ;
	}
	 $pform = $("input[name=aidFile]").parents('.form-row');
	 var $div = $('<div>').addClass("reduceBtn").html('-');
	 var $span = $('<span>').addClass('text').addClass("mr10");
	 var $pdiv = $('<div>').addClass("input");
	 $pdiv.css("margin-left","180px");
	 $span.appendTo($pdiv);
	 $div.appendTo($pdiv);
	 $pdiv.find('span').html($("input[name=aidFile]").val());
     $pform = $("input[name=aidFile]").parents('.form-row');
     $input = $("<input>").attr('name','aidResource').attr('type','hidden').val(dataUrl);
     $inputName = $("<input>").attr('name','aidResourceName').attr('type','hidden').val(resourceName);
     $input.appendTo($pdiv);
     $inputName.appendTo($pdiv);
     $pdiv.find('.reduceBtn').click(function(){
          $(this).parent().remove();
          $.ajax({
      		url:ctx+'/course/uploadfzFileDelete',
      		data:{uploadurl:dataUrl},
      		error:function(){},
      		success:function(){
      			
      		}
      	});
     });
     $pdiv.appendTo($pform);
}

