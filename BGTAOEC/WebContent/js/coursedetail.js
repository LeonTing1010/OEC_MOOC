
$(document).ready(function(){
    if ("rtmp"==videoProtocol) {
    	 playCkplayer();
	}
    if ("http"==videoProtocol) {
    	playMediaPlayer();
	}
})
function playCkplayer() {
	//flashvars.f='rtmp://vod.gtadata.com/vod/20140320171441368.flv';
	flashvars.f = vodServer + $('#video').attr('data-src');
	flashvars.i = img;
	swfobject.embedSWF(base+'/player/ckplayer.swf', 'video',$('#video').width(), $('#video').height(), '10.0.0',base+'/player/expressInstall.swf', flashvars, params, attributes);
}
function playMediaPlayer() {
	flashvars = 
	{
	    sourceUrl:vodServer + $('#video').attr('data-src'),//播放地址
	    previewImageUrl:img,
	    autoPlay:"false"
	};
  	params.quality = "high";
  	params.bgcolor = "#ffffff";
  	params.allowscriptaccess = "sameDomain";
 	params.allowfullscreen = "true";
	swfobject.embedSWF(base+'/MediaPlayer/GPlayer.swf', 'video',$('#video').width(), $('#video').height(), '10.0.0',base+'/MediaPlayer/playerProductInstall.swf', flashvars, params, attributes,null);
}
var secType = '2';
var params = {
	bgcolor : '#FFF',
	allowFullScreen : true,
	allowScriptAccess : 'always',
	wmode:'transparent'
};// 这里定义播放器的其它参数如背景色（跟flashvars中的b不同），是否支持全屏，是否支持交互
var attributes = {
	id : 'courseVideo',
	name : 'courseVideo',
	menu : 'false'
};
var flashvars = {
		s:'0',
		c:'0',
	    e:'0',
	    x:'',
	    v:'80',
    	p:'1',
	    m:'1',
	    wh:'',
	    ct:'2',
	    my_url : encodeURIComponent(window.location.href)
};

function collectcourse(courseId){
	oec.login.isLogin(function(){
		var obj = document.getElementById("coursecollimg"+courseId);//先得到所有的SPAN标记
		if(obj.className=='collectBtn'){
			if(courseId!=null){
				$.ajax({
					url : base+"/course/collect/",
					type : 'post',
					data : {"courseId":courseId,"type":1},
					success : function(data){
						if(data.flag=="right"){
							$("#collcourseCount").html(data.collCount+"人已收藏");
							$("#coursecollimg"+courseId).removeClass('collectBtn');
							$("#coursecollimg"+courseId).addClass('collectBtn current');
							obj.title='取消收藏';
						}else if(data.flag=="tourist"){
							//window.location.href=base+'/user/loginIndex/?returnUri='+getUri();
						}
					}
				})
			}
		} else if(obj.className == 'collectBtn current'){
			$.ajax({
				url : base+"/course/collect/",
				type : 'post',
				data : {"courseId":courseId,"type":2},
				success : function(data){
					if(data.flag=="sure"){
						$("#collcourseCount").html(data.collCount+"人已收藏");
						$("#coursecollimg"+courseId).removeClass('collectBtn current');
						$("#coursecollimg"+courseId).addClass('collectBtn');
						obj.title = '收藏本课程';
						
					}else if(data.flag=="tourist"){
						//window.location.href=base+'/user/loginIndex/?returnUri='+getUri();
					}
				}
			})
		}
	});
}
//获取URI
function getUri() {
	var url = window.location.href;
	var arr = url.split("//");
	url = arr[1].substring(arr[1].indexOf("/"));
	return url;
}

function toCourse(courseId) {
	var url = base + "/course/main/"+courseId+"/";
	oec.login.isLogin(function(){
		window.location.href = url;
	});
}

/***********************与课程评论相关****************************************/
var contentErrMsg = "";
var codeErrMsg = "";
//我要点评该课程
function toDeliverComment(obj){
	oec.login.isLogin(function(){
		refreshCode($("#verifyComVerificationCodeImg").get(0));
		$("#view").show(); 
	});
} 
// 取消发表评论
function cancelDeliverComment(){
	$("#view").hide();
}
// 正式发表评论之情再次对评论相关信息进行验证
function verifyCourseCommentForm(){
	var content = verifyComContent();
	var code = verifyComVerificationCode();
	return content && code;
}
// 发表评论
function  deliverComment(){
	var canDeliver = verifyCourseCommentForm();
	if(canDeliver){
			$.ajax({
			   url:base + "/course/deliverComment/",
			   async:false,
			   data:$("#courseCommentId").serialize(),
			   dataType:"json",
			   type:"post",
			   success:function(data){
			   		 if (data.result) {
						  var courseId = $("#comCourseId").val();
						  $('#courseCommentDiv').load(base+'/course/courseCommentPageSearch/?commentPageNo=1&comCourseId='+courseId+'&code='+Math.random());
					}
			  }
	   });
	}
}
// 刷新验证码
 function refreshCode(obj) {
 	// var url = baseUrl + "/course/generateCommentCode/?"+Math.random();
 	// alert(url + "||");
    obj.src = "/course/generateCommentCode/?"+Math.random();
  }
  // 验证评论内容
  function verifyComContent(){
  	var currObj = $("#txtareac");
  	if(currObj.val().length > 200){
  		contentErrMsg = "点评内容最多200字";
  		showCourseCommentErrorMsg(contentErrMsg);
  		return false;
  	}else if(currObj.val() == "" || currObj.val() == null){
  		contentErrMsg = "请填写您的点评信息";
  		showCourseCommentErrorMsg(contentErrMsg);
  		return false;
  	}else {
  		contentErrMsg = "";
	  	return true;
  	}
  }
  // 验证评论验证码
 function verifyComVerificationCode(){
 	var currObj = $("#verificationCode");
  	if(currObj.val() == "" || currObj.val() == null){
  		codeErrMsg = "请填写验证码";
  		showCourseCommentErrorMsg(codeErrMsg);
  		return false;
  	}else {
  		verifyComVerificationCodeAjax(currObj);
  		if(codeErrMsg == ""){
  			return true;
  		}else{
  			return false;
  		}
  	}
 	verifyComVerificationCodeAjax(currObj);
 }
  function verifyComVerificationCodeAjax($obj){
  	// alert($("#verificationCode").val()+",,,,");
  	var verificationCode = $("#verificationCode").val();
  	 $.ajax({
	   url:base+"/course/verifyComVerificationCodeAjax",
	   async:false,
	   data:{"verificationCode":verificationCode},
	   dataType:"json",
	   type:"post",
	   success:function(data){
		   if (data.result) {
			  codeErrMsg = "";
		    }else {
		    	 codeErrMsg = "请输入正确的验证码";
		    	showCourseCommentErrorMsg(codeErrMsg);
			}
	   }
   });
  }
  // 显示课程评论错误信息
  function showCourseCommentErrorMsg(errorMsg){
  	$("#courseCommentMsg").html(errorMsg);
  	if(errorMsg != ""){
  		$("#courseCommentMsgDiv").removeClass("hide");
  	}else {
  		$("#courseCommentMsgDiv").addClass("hide");
  	}
  }

  