//添加章
function addSection() {
	var $section = $('#secondGrid dl').first().clone();
	var len = $('#secondGrid dl').length;
	$section.attr('data-id',len);
	$section.removeClass('hide');
	$section.find('label').first().html("第"+Uppercase(len)+"章：");
	$section.appendTo($('.classList'));
	changSbt($('#secondGrid'));
}
//章加减按钮变化
function changSbt($p){
	//减按钮
	   var $div = $('<div>').addClass('reduceBtn').html('-').click(function(){
		   eoc.confirm('提示', '确定要删除该章/节吗?', function(result) {
			if (result) {
				$(this).parents('dl').remove();
				// 变化按钮
				changSbt($('#secondGrid'));
				// 值发生变化
				changeSc();
			}
		});
	   });
	   //增加按钮
	   var $addDiv = $('<div>').addClass('addBtn').html('+').click(function(){
		   addSection();
	   });
	   $p.find('dt .reduceBtn').remove();
	   $p.find('dt .addBtn').remove();
	   $addDiv.appendTo($p.find('dt .input').last());
	   if ($p.find('dl').length>2) {
		   $div.appendTo($p.find('dt .input'));
	   }
	    
}
//添加节
function addPart(tag) {
   var $part = $('#secondGrid dd').first().clone();
   var $p = $(tag).parents('.creatList');
   var len = $p.find('dd').length;
   $part.attr('data-id',len+1);
   $part.find('label').html("第"+Uppercase(len+1)+"节：");
   $part.appendTo($p); 
   changeBt($p);
   $(tag).remove();
}
//节按钮发生变化
function changeBt($p){
	//减按钮 
	   var $div = $('<div>').addClass('reduceBtn').html('-').click(function(){
		   eoc.confirm('提示','确定要删除该章/节吗?',function(result){
			   if (result) {
				   var $ps = $(this).parents('dl');
			       $(this).parents('dd').remove();
			       //变化按钮
			       changeBt($ps);
			       //值变化
			       changeDl($ps);
			    }     
		   });

	   });
	   //增加按钮
	   var $addDiv = $('<div>').addClass('addBtn').html('+').click(function(){
		   addPart(this);
	   });
	   $p.find('dd .reduceBtn').remove();
	   $p.find('dd .addBtn').remove();
	   $addDiv.appendTo($p.find('li').last());
	   if ($p.find('li').length!=1) {
		   $div.appendTo($p.find('li'));
	    }
	  
}
//减少节的时候触发
function changeDl($dl) {
	$dl.find('dd').each(function(i){
		 $(this).attr('data-id',i+1);
		 $(this).find('label').html("第"+Uppercase(i+1)+"节：");
	});
	$('#secondGrid dl .large').change();
}
//减少章的时候触发
function changeSc() {
	$('#secondGrid dl').each(function(i){
		 $(this).attr('data-id',i);
		 $(this).find('label').first().html("第"+Uppercase(i)+"章：");
	});
	$('#secondGrid dl .large').change();
	
}
//章内容change函数
function sectionChange(tag){
	$p = $(tag).parents('dl');
	if (isNull($(tag).val())) {
		$p.find('input[name=section]').val('');
	}else {
		$p.find('input[name=section]').val($p.attr('data-id')+':'+$(tag).val());
	}
}
//节内容change函数
function partChange(tag){
	$p = $(tag).parents('dd');
	if (isNull($(tag).val())) {
		$p.find('input[name=part]').val('');
	}else {
		$p.find('input[name=part]').val($(tag).parents('dl').attr('data-id')+":"+$p.attr('data-id')+":"+$(tag).val());
	}	
}

//第二步提交
function step2Sub() {
	var $section  = $('input[name=section]');
	var flag = true;
	var  errorMsg;
	$section.each(function(index){
		if (index>0) {
			if (oec.tc.isEmpty($(this).val())) {
				flag = false;
			 }else if (!oec.tc.checkSection($(this).val())) {
				 flag = false;
				 errorMsg ="课程章/节长度不能超过80字节";
			}			
		}
	})
	var $part  = $('input[name=part]');
	$part.each(function(index){
		if (index>0) {
			if (oec.tc.isEmpty($(this).val())) {
				flag = false;
			 }else if (!oec.tc.checkSection($(this).val())) {
				 flag = false;
				 errorMsg ="课程章/节长度不能超过80字节";
			}			
		}
	})
	if (!flag) {
		if (isNull(errorMsg)) {
		//	eoc.alert('提示','章/节不允许为空！');
			removePoint($('.thirdStep').parent());
			errorSpan('章/节不允许为空').appendTo($('.thirdStep').parent());
		}else{
			//eoc.alert('提示',errorMsg);
			removePoint($('.thirdStep').parent());
			errorSpan(errorMsg).appendTo($('.thirdStep').parent());
		}
		return ;
	}
	
	var $input = $('input[name=courseId]').clone().attr('name','step2CourseId');
	$input.appendTo($('#step2Form'));
	$.ajax({
		url:base+'/teacherCenter/upload/saveStep2/',
		data:$('#step2Form').serialize(),
		type:'post',
		success:function(data){
			if (data.result) {
				loadUpload(3, $('input[name=courseId]').val());	
			}
		}
			
	});
}

function errorSpan(msg) {
	var $i =$('<i>').addClass('error-icon');
	var $pspan = $('<span>').addClass('validation');
	var $espan = $('<span>').addClass('error').attr('style','color:red;').html(msg);
	$i.appendTo($pspan);
	$espan.appendTo($pspan);
	return $pspan;
}
function removePoint($obj) {
	$obj.find('.validation').remove();
}

//返回第二步的上一步
function step2Pre(){
	loadUpload(1,$('input[name=courseId]').val());
}