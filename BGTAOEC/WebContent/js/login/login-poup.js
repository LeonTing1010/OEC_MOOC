var oec = oec||{};
oec.login = function(){};
oec.login.loginCallBack = null;
//加载登陆框
oec.login.loadPoup = function(){
	this.removeLoginPoup();
	$(window).bind("scroll",function(){
		oec.login.resetPoupLocation();
	});
	$.ajax({
		   url:base+"/login/getLoginPoup.ajax?code="+Math.random(),		   
		   async:false,
		   error:function(data){
			   oec.login.removeLoginPoup();
		   }, 
		   success:function(data){
			   $(data).appendTo($("body"));
			   oec.login.resetPoupLocation();
			   
		   }
	   })
}
//调整登录框位置
oec.login.resetPoupLocation = function(){
	 $("#loginPoupWin").css({top:$(document).scrollTop()+"px"});
	 setTimeout("loginFocus()",1000); 
}

//更新头部
oec.login.loadTop = function(){
	$('.headBar').load(base+"/login/loadTop.ajax?code="+Math.random());
}
//移除登录弹出框
oec.login.removeLoginPoup = function removeLoginPoup(){
	$(window).unbind("scroll");
    $("#loginPoupWin").remove();
}
//登陆框表单提交
oec.login.loginPoupSubmit = function loginPoupSubmit(){
   $.ajax({
	   url:base+"/login/loginAjax.ajax",
	   async:false,
	   data:$("#loginPoupForm").serialize(),
	   dataType:"json",
	   type:"post",
	   error:function(){
		 oec.login.removeLoginPoup();
	   },
	   success:function(data){
		   if (data.result) {
			   oec.login.loadTop();
			   oec.login.afterLogin();
		    }else {
		    	$("#loginPoupWin .login-tips span").html(data.errorMsg);
		    	$("#loginPoupWin .login-tips .error").removeClass("hide");
			}
	   }
   });
}
//登录完成后的操作
oec.login.afterLogin = function(){
	   oec.login.removeLoginPoup();
	   //如果回调
	   if (oec.login.loginCallBack) {
		   oec.login.loginCallBack();
	    }
}
//判断是否登录
oec.login.isLogin = function(calltack) {
	oec.login.loginCallBack = calltack;
	$.ajax({
		   url:base+"/login/checkUserLogin.ajax",
		   dataType:"json",
		   async:false,
		   error:function(data){
			   oec.login.removeLoginPoup();
		   }, 
		   success:function(data){			   
			   if(!data.result){
				   oec.login.loadPoup();
			   }else {
				   oec.login.afterLogin();
			   }
		   }
	   })
}
// 360急速浏览器回显密码时需要
function loginFocus(){
	$("#account").focus();
}