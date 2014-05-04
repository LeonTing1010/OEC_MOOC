var vcheight;
var wdheight;
var mcwidth;
var rcheight;
var rcheight;
var playFlag = true;//视频播放标识
$(document).ready(function(){
   wdheight=$(window).height();
   mcwidth=$(".mainCon").width();
   rcheight=$(".rightCon").height();
   vcheight=$(".videoCon").height();
  $(".curriculum-body").css("height",wdheight-202);
  $(".videoCon").css("width",mcwidth-325);
  $(".tabCon").css("height",$(".videoCon").height()-50);
  vcheight=$(".videoCon").height();

  $(window).resize(function() {
	  wdwidth=$(window).width();
	  wdheight=$(window).height();
	  $(".curriculum-body").css("height",wdheight-202);
	  $(".videoCon").css("width",wdwidth-400);
	  vcheight=$(".videoCon").height();
	  $(".tabCon").css("height",$(".videoCon").height()-50);
	  mcwidth=$(".mainCon").width();
	  rcheight=$(".rightCon").height();
	  changeVideo();
	  }); /*根据窗口高度宽度变化*/
  $(".outline h4").click(function(){
	  $(this).next("ul").toggle();
	  });
  $('.tab li').click(function(){
	  var $obj = $(this);
	  oec.login.isLogin(function() { 
	  $obj.addClass('current').siblings('.current').removeClass('current');
	  $('.tabCon').addClass('hide');
	  $('.tabCon').eq( $obj.index()).removeClass('hide');
	  if ($('.tabCon').eq( $obj.index()).hasClass('note')) {
		  $('.tabCon').eq( $obj.index()).find("ul").load(base+"/course/getNote.ajax?sectionId="+sectionId+"&courseId="+$('input[name=noteCourseId]').val()+"&code="+Math.random());
	   }
	  if ($('.tabCon').eq( $obj.index()).hasClass('ask')) {
		  $('.tabCon').eq( $obj.index()).find("ul").load(base+"/course/getAsk.ajax?sectionId="+sectionId+"&courseId="+$('input[name=noteCourseId]').val()+"&code="+Math.random());
	   }
	  });
  });

  $('.outline li').click(function(){
	  var $obj = $(this);
	  oec.login.isLogin(function() {
	  $('.poupBox').hide();
	  sectionId = $obj.attr('id');
	  checkResource($obj);
	  if(isNull($('#'+sectionId+" input[name=videoUrl]").val())){ 
		  return ;
	  }
	  if (playFlag) {
		playVideo();
		if (!$obj.find('.statusA').hasClass('viewed')) {
		   $obj.find('.statusA').addClass('viewed');
			saveLearn();
		}
	  }
	  //直播视频，禁用播放切换
      if (secType=='1') {
    	  playFlag = false;
	  }
	 });
  }); 
  
  $(".outline li").each(function(index){
	  $(this).attr('seq-id',index);
  });
  
  //首次加载情况，如果已选择具体节，则播放当前节，如果没有选择，则默认加载第一节
  if (sectionId!='') {
	  $('#'+sectionId).click();
   }else {
	   $('.outline li').eq(0).click();
   }
});
//校验该节是否存在辅助资源
function checkResource($obj) {
	var resCount = $obj.attr('data-resource');
	$('.video-footer a').last().show();
	if (parseInt(resCount)<=0) {
		$('.video-footer a').last().hide()
	}
}
//下一节视频
function nextVideo() {
	$('.poupBox').hide();
	var next = parseInt($('.outline li[id='+sectionId+']').attr('seq-id'))+1;
	$('.outline li').eq(next).click();
}
//上一节视频
function prevVideo() {
	$('.poupBox').hide();
	var prev =  parseInt($('.outline li[id='+sectionId+']').attr('seq-id'))-1;
	$('.outline li').eq(prev).click();
}
//头部显示h1的信息
function showH1(){
	var h4 = $('#'+$('.outline li[id='+sectionId+']').attr("data-pid")).html();
	var sectionHtml = $('.outline li[id='+sectionId+'] label').html();
	$("h1").html(h4+"&nbsp;&nbsp;"+sectionHtml);
}
//获取视频时间
function getCurrentPlayTime() {
	if ("2"==secType&&"http"==videoProtocol) {
		 if (window.document["courseVideo"]) 
		   {
		      return window.document["courseVideo"].getCurrentTime();
		   }
		   if (navigator.appName.indexOf("Microsoft Internet")==-1) 
		   {
		      if (document.embeds && document.embeds["courseVideo"]) 
		      {
		         return document.embeds["courseVideo"].getCurrentTime(); 
		      }
		   } 
		   else 
		   {
		   		return document.getElementById(movie).getCurrentTime();
		   }
	}
	return swfobject.getObjectById('courseVideo').ckplayer_getstatus().time;
}
//保存笔记
function saveNote() {
   $('input[name=noteSectionId]').val(sectionId);
   var noteContent = $('#noteForm textarea').val();
 
   var videoTime = getCurrentPlayTime();

   $('input[name=videoTime]').val(videoTime);
   if (isNull(noteContent)) {
	   eoc.alert('提示','笔记不能为空!');
	   return ;
     }
   if (noteContent.length>500) {
	   eoc.alert('提示','笔记内容不能超过500个字符!');
	   return ;
   }
   $.ajax({
	   url:base+"/course/saveNote/",
	   type:"post",
	   data:$('#noteForm').serialize(),
	   error:function(){
		   
	   },
	   success:function(data){
		   if (data.result) {
			   $('#noteForm textarea').val('');
			   var $li = $('<li>');
			   $('<div>').addClass('pb10 wordBreak').html(noteContent).appendTo($li);
			   $('<div>').addClass('time').html('视频时间:&nbsp;'+data.videoTime).appendTo($li);
			   $('.noteInput .gray').html('还可输入500字');
			   $('.noteList ul').prepend($li);
		     }
	   }
   })
}
//保存提问
function saveAsk() {
   $('input[name=askSectionId]').val(sectionId);
   var askContent = $('#askForm textarea').val();
   var courseVideo=swfobject.getObjectById('courseVideo');
   if (isNull(courseVideo)) {
	   eoc.alert('提示','没有相关课程视频.不能提问！');
	   return ;
   }
   var videoTime = getCurrentPlayTime();
   $('input[name=videoTime]').val(videoTime);
   if (isNull(askContent)) {
	   eoc.alert('提示','提问内容不能为空！');
	   return ;
     }
   if (askContent.length>150) {
	   eoc.alert('提示','提问内容不能超过150个字符!');
	   return ;
     }
   $.ajax({
	   url:base+"/course/saveAsk/",
	   type:"post",
	   data:$('#askForm').serialize(),
	   error:function(){
		   
	   },
	   success:function(data){
		   if (data.result) {
			   $('#askForm textarea').val('');
			   var $li = $('<li>');
			   $('<div>').addClass('pb10 wordBreak').html(askContent).appendTo($li);
			   $('<div>').addClass('time').html('创建时间:&nbsp;'+data.createTime).appendTo($li);
			   $('.askList ul').prepend($li);	
			   eoc.alert('提示','问题提交成功，老师会在24小时内给予回答！')   
			   $('.askInput .gray').html('还可输入100字');
		   }else{
			   eoc.alert('提示',data.errorMsg);   
			  
		   }
	   }
   })
}
//显示剩余输入字数
function showLast(obj,length) {
	var lastCount = length-$(obj).val().length;
	var parClass= $(obj).parent().attr('class');

	if (lastCount<=0) {
		$("."+parClass+" .gray").html('还可输入 0字');
		$(obj).val($(obj).val().substring(0,length));
	}else {
		$("."+parClass+" .gray").html('还可输入 '+lastCount+'字');
		//$(obj).removeAttr('disabled');
	}
}

//视频播放后触发的函数
function playerstop() {
	cordKnowledge();
}

//根据窗口变化视频宽度
function changeVideo() {
	$('.showVideo').width($('.videoCon').width()-320);
	$('.showVideo').height(vcheight-45);
	$('#courseVideo').attr('width',$('.videoCon').width());
	$('#courseVideo').attr('height', vcheight-45);
}
function playVideo(){
	showH1();//显示头部章节信息
	$('.video-footer .btn-small').show();
	$('.video').removeAttr('style');
	//根据视频播放类型与播放协议选择播放器
	if (("1"==secType)||("2"==secType&&"rtmp"==videoProtocol)) {
		playCkplay();
	}
	if ("2"==secType&&"http"==videoProtocol) {
		playMediaPlayer();
	}
}
function playCkplay(){
	if ("2"==secType) {
		flashvars.f = vodServer + $('#'+sectionId+" input[name=videoUrl]").val();
	}
	if ("1"==secType) {
		flashvars.f = liveServer + $('#'+sectionId+" input[name=videoUrl]").val();
	}
	swfobject.embedSWF(base+'/player/ckplayer.swf', 'courseVideo',$('.videoCon').width()-40, vcheight-45, '10.0.0',base+'/player/expressInstall.swf', flashvars, params, attributes);
}
function playMediaPlayer(){
	flashvars = 
	{
		//sourceUrl:"http://192.168.101.163:1935/vod/mp4:sample.mp4/manifest.f4m",
	    sourceUrl:vodServer + $('#'+sectionId+" input[name=videoUrl]").val(),	//播放地址
		onPlayComplete:"playerstop",//播放结束回调函数
		autoPlay:"true"
	};
  	params.quality = "high";
  	params.bgcolor = "#ffffff";
  	params.allowscriptaccess = "sameDomain";
 	params.allowfullscreen = "true";
 	params.wmode = "transparent";
	swfobject.embedSWF(base+'/MediaPlayer/GPlayer.swf', 'courseVideo',$('.videoCon').width()-40, vcheight-45, '10.0.0',base+'/MediaPlayer/playerProductInstall.swf', flashvars, params, attributes,null);
}

var params = {
		bgcolor : '#FFF',
		allowFullScreen : true,
		allowScriptAccess : 'always',
		wmode:'transparent'
	};
var flashvars = {
			s:'0',
			c:'0',
		    e:'0',
		    x:'',
		    v:'80',
	    	p:'1',
		    m:'0',
		    wh:'',
		    ct:'2',
		    my_url : encodeURIComponent(window.location.href)
	};
	// 这里定义播放器的其它参数如背景色（跟flashvars中的b不同），是否支持全屏，是否支持交互
var attributes = {
		id : 'courseVideo',
		name : 'courseVideo',
		menu : 'false'
};
//保存学习信息
function saveLearn(){
   $.ajax({
	   url:base+"/course/learn/saveLearn/",
	   type:"post",
	   data:{courseId:$("input[name=courseId]").val(),sectionId:sectionId},
	   error:function(){
		   
	   },
	   success:function(){
		   
	   }
   });	
}
//跳转辅助资源列表
function showResource(courseName){
	var url =  base +"/course/resource/?courseId="+$("input[name=courseId]").val()+"&sectionId="+sectionId+"&courseName="+encodeURI(courseName);
	var $a = $("<a>").attr('href',url).attr('target','_blank');
	var $span = $("<span>").attr('id','resourseTarget');
	$span.appendTo($a);
	$a.appendTo($("body"));
	$('#resourseTarget').click();
	$('#resourseTarget').parent().remove();
}
//展示核心知识点
function cordKnowledge(){
	$('.video-footer .btn-small').hide();
	$('.poupBox').load(base+"/course/learn/getCoreKnowledge.ajax?sectionId="+sectionId+"&code="+Math.random(),function(){
		if ($('.poupBox-con ol').length>0) {
			
			$('.video').height($('#courseVideo').height());
			$('#courseVideo').height(1);
			$('#courseVideo').width(1);
			$('.poupBox').show();
		}else {
			$('.poupBox').hide();
			testShow();
		}
	});
}
//展示课程练习
function testShow(){
	$('.poupBox').load(base+"/course/learn/getCourseTest.ajax?sectionId="+sectionId+"&courseId="+$("input[name=courseId]").val()+"&code="+Math.random(),function(){	
			$('.poupBox').show();	
	});
}
//保存练习
function saveTest() {
	/**答题个数**/
    var checkLen = $('input[type=radio]:checked').length;
    /***********/
    var quesLen = $('.poupBox-con dl').length;
    if (checkLen!=quesLen) {
		eoc.alert('提示','请答完题再提交！');
		return ; 
	}
  $.ajax({
    	url:base+"/course/submitPaper/",
    	data:$('#testForm').serialize(),
    	type:'post',
    	success:function(data){
    		
    		if(data.result=='1'){
    	    	eoc.alert('提示','选择题未选填完！');
	    	 }
	    	 else if(data.result=='2'){
	    		 eoc.alert('提示','已经提交过,不能重复提交！');
	    	 }else if(data.result=='3'){
	    		 testShow();
	    	 }
			
    	}
    	
    })
}