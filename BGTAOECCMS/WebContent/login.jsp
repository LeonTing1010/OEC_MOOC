<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<head>
    <meta charset="UTF-8">
    <title>国泰安网络教育管理系统登录</title>
</head>
<body>
<link href="${applicationScope.ctx}/css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${applicationScope.ctx}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${applicationScope.ctx}/js/main/eoc-main.js" ></script>
<script type="text/javascript" src="${applicationScope.ctx}/js/main/eoc-util.js" ></script>
<script type="text/javascript" src="${applicationScope.ctx}/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${applicationScope.ctx}/js/jquery_plugins/jquery.cookie.js"></script>
    <div class="wrapper">
        <div class="logWrapper">
            <div class="logo"></div>
            <input type="hidden" name="isLogin" id="isLogin" value="true" />
            <div class="nameWrapper log_option">
                <input type="text" class="logname input_option" name="username" id="username" >
            </div>
            <!-- end name wrapper -->
            <div class="pwdWrapper log_option">
                <input type="password" class="logpwd input_option" name="password" id="password" onpaste='return false' oncopy='return false' >
            </div>
            <!-- end name pwd wrapper -->
            <div class="error" id="error">
                <div class="errorTip" id="errorTip">
                用记名或密码错误
                </div>
            </div>
            <!-- end error -->
            <div class="subWrapper ">
                <input type="button" value="登录"  class="subBtn" id="subBtn">
                <div class="cookie">
                    <span class="setCookie" setCookie="false" id="setCookie"></span>
                    <span class="cookieTip">记录我</span>
                </div>
                <!-- end cookie -->
            </div>
            <!-- end log subWrapper -->
            </div>
            <span style="background-color:red;">[username:admin;password:123456]</span>
        <!-- end logWrapper -->
    </div>
    <!-- end wrapper -->
<script type="text/javascript">
$(document).keypress(function(e) {		  
    if(e.keyCode==13){
    	 $('#subBtn').click();
       }
 });
$(document).ready(function() {
	if(!$.isFunction($.cookie)){
		return ;
	}
    //set the value with cookie when the page being loaded
	if($.cookie("logo_cms_username")){
        $("#username").val($.cookie("logo_cms_username"));
        if($.cookie("logo_cms_password")){
            $("#password").val($.cookie("logo_cms_password"));
        };
        var _remenberMeVal = $.cookie("logo_cms_remenberMe");
        if(_remenberMeVal && _remenberMeVal == 'true'){
        	$("#setCookie").toggleClass("setCookieChecked");
        	$("#setCookie").attr("setCookie",$.cookie("logo_cms_remenberMe"));
        }
    }

    $("#setCookie").click(function(){
        $(this).toggleClass("setCookieChecked");
        $(this).attr("setCookie")=="false"? $(this).attr("setCookie","true"):$(this).attr("setCookie","false");
    });

    //login operation 
    $("#subBtn").on("click", function() {
        var username = $("#username").val();
        var password = $("#password").val();
        var setCookie = $("#setCookie").attr("setCookie");
    	if(eoc.cms.main.isEmpty(username) || eoc.cms.main.isEmpty(password)){
    		 $("#errorTip").html("用记名或密码不能为空");
             $("#error").css("display","block");
             setTimeout(function(){
             	$("#error").hide();
             }, 3000);
    		return ;	
    	}
        //make the ajax,and get the return json
        $.getJSON("${applicationScope.ctx}/login?username_op=" + username + "&userpassword_op=" + password,
            function(returnJSON) {
                if (returnJSON.success) {
                	//when success,set the cookies
                    if(setCookie&&setCookie=="true"){
                    	$.cookie("logo_cms_username",username,{expires : 30});
                        $.cookie("logo_cms_password",password,{expires : 30});
                        $.cookie("logo_cms_remenberMe",setCookie,{expires : 30});
                    }else{
                    	$.removeCookie("logo_cms_username");
                        $.removeCookie("logo_cms_password");
                        $.removeCookie("logo_cms_remenberMe");
                    }
                     //location.href = "#";
                	var _hn = location.hostname;// ret web hostname
    	            var _pn = location.pathname;// ret the pathname
    	            var _p = location.port;// ret web port （80 or 443）
    	            var _pl = location.protocol;// ret the  web protocol（http:// or https://）
    	            var _op = _pl+"//"+_hn+":"+_p+'${applicationScope.ctx}/';
    	            window.location.href=_op;
                } else {
                    //when error,set the errorTips
                    $("#errorTip").html(returnJSON.errorMsg);
                    $("#error").css("display","block");
                    $("#username").val('');
                    $("#password").val('');
                    setTimeout(function(){
                    	$("#error").hide();
                    }, 3000);
                }
            }).fail(function() {
	            $("#errorTip").html("service error!");
	            $("#error").css("display","block");
	            $("#username").val('');
                $("#password").val('');
	            setTimeout(function(){
                	$("#error").hide();
                }, 3000);
        });
    });
});
</script>    
</body>
</html>