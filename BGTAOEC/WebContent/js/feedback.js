/**返回正确提示**/
function rightSpan(){
	return "<span class='Okspan'><span class='validation'><i class='ok-icon'></i><span class='ok'>&nbsp;</span></span></span>";
};
/**返回错误提示**/
function errorSpan(errorMessage){
	return  "<span class='errspan'><span class='validation'><i class='error-icon'></i><span class='tip' style='color:red;'>"+errorMessage+"</span></span></span>";
};
/*---------------------------以下与注册页面相关-------------------------*/
// 载入页面，加载默认样式
$(function() {
    $("[feetag]").blur(function () {
    	$(this).nextAll(".Okspan").remove();
    	$(this).next(".flag").remove();
        var state = verify($(this));
        if (!state) {
            TipMessage(this);
        }
        else {
            delMessage(this); 
        }
    });
});

errmessage = "";
// 添加错误提示信息
function TipMessage(obj) {
	if ($(obj).nextAll(".verifytip").length == 0) {
		
		$(obj).nextAll(".errspan").remove();
		var curTr = $(obj).parent().parent().next("tr");
		if (curTr && curTr.attr("class") == "trdisplay") {
			$(curTr).remove();
		}
		$(obj).nextAll(".Okspan").remove();

		if (errmessage == "*") {
			//alert($(obj).attr("name")=="textTitle");
			if($(obj).attr("name")=="textTitle"){
				$(obj).after(errorSpan('请输入标题'));
			}else if($(obj).attr("name")=="textContent"){
				$(obj).after(errorSpan('请输入内容'));
			}else if($(obj).attr("name")=="textEmail"){
				$(obj).after(errorSpan('请输入邮箱'));
			}else if($(obj).attr("name")=="randomCode"){
				$(obj).after(errorSpan('请输入验证码'));
			}else{
				$(obj).after(errorSpan('该项为必填项'));
			}
		} else {
				$(obj).after(errorSpan(errmessage));
		}
	} else {
		$(obj).nextAll(".verifytip").html(
				"<span style='color:Red; font-size:13px; vertical-align: middle;'>"
						+ errmessage + "</span>");
	}
}

function delMessage(obj) {
	if ($(obj).nextAll(".verifytip").length == 0) {
		var curTr = $(obj).next("tr");
		if (curTr && curTr.attr("class") == "errspan") {
			$(curTr).remove();
		}
		$(obj).nextAll(".errspan").remove();
	} else {
		$(obj).nextAll(".verifytip").html("");
	}
}


// 验证对象是否符合要求
function verify(obj) {

	var flag = true;
	var types = obj.attr("feetag"); // 获取验证对象需要进行何种验证处理
	// var objValue = $.trim(obj.attr("value")); //获取验证对象的值
//	 // 获取验证对象的值
	var typestr = new Array(); // 定义一个数组，用来存放用户验证处理需求点
	typestr = types.split(",");
	var objValue ;
	for (var i = 0; i < typestr.length; i++) {
		var type = typestr[i].toString();	
		objValue = $.trim($(obj).val()); //获取验证对象的值
		if (tagcheck(type, objValue)) {// 满足验证要求
		} else {
			flag = false;
			break;
		}
	}
	$(obj).after(rightSpan());
	return flag;
}

// 判断验证对象的值是否满足验证要求
function tagcheck(type, vals) {
	var objvals = $.trim(vals);
	if (objvals == "") {
		if (type == "*") {
			errmessage = "*";
			return false;
		} else {
			return true;
		}
	} else {
		switch (type) {
		case "*": // 非空
			return true;
		case "email":// 邮箱
			if(vals=="您的常用邮箱账号"){
				errmessage = "*";
				return false;
			}
			var rgexp = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/; /**含盖了不能包括特殊符号。*/
			// /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.\w+$/;  /**也能作为格式验证，*/
			errmessage = "请输入正确格式的邮箱"; 
			return rgexp.test(vals);
			
		case "checkRandomCode": // 名称验证
			if(vals=="请输入验证码"){
				errmessage = "*";
				return false;
			}
			checkRandomCode();
			return true;


	/*-----------------------意见反馈验证部分----------------------------*/
					
		
		case "feedbackTile":// 标题
			if(vals=="最多50字"){
				errmessage = "*";
				return false;
			}
		case "checkFeedbackTitle": // 标题验证
			var len = vals.length;
			if (len > 0 && len <= 50) {
				return true;
			} else {
				errmessage = "标题最多50字";
				return false;
			}
		case "feedbackContent":// 内容
			if(vals=="最多500字"){
				errmessage = "*";
				return false;
			}
		case "checkFeedbackContent": // 内容验证
			var len = vals.length;
			if (len > 0 && len <= 500) {
				return true;
			} else {
				errmessage = "内容最多500字";
				return false;
			}		
	/*-----------------------意见反馈验证部分----------------------------*/	
			
		default:
			return false;
		}
	}
}

// 检查用户名是否存在
function checkUserName() {
	var userTemp = $("#uname").val();
	$.ajax({
				url : base + "/user/checkusername.ajax",
				data : {username : userTemp},
				success : function(data) {
					if (data.result == true) {
						$('#uname').parent().find('.Okspan').remove();
						$('#uname').parent().find('.errspan').remove();
						$('#uname').after(errorSpan('用户名已存在'));
						notExitsUserName = false;
					}else{
						notExitsUserName = true;
					}
				}
			});
}


// 检查验证码是否输入正确
function checkRandomCode() {
	var userTemp = $("#randomCode").val();
	$.ajax({
		url : base + "/user/checkrandomcode.ajax",
		data : {
			RandomCode : userTemp
		},
		success : function(data) {
			if (data.result==true) {
//						$('#emailID').parent().find('.Okspan').remove();
				$('#randomCode').parent().find('.errspan').remove();
				randomCodeRight = true;
			}else if(data.result==false){
				$('#randomCode').parent().find('.Okspan').remove();
				$('#randomCode').parent().find('.errspan').remove();
				$('#randomCode')
				.after(errorSpan('请输入正确的验证码'));
				randomCodeRight = false;		
			}
		}
	});
}
/*---------------------------以上与注册页面相关-------------------------*/


//点击提交按钮，检查所有的待验证对象是否符合要求
function formverify() {
	var flag = true;
	$("[feetag]:visible").each(function() {
		$(this).nextAll(".Okspan").remove();
		if (!verify($(this))) {
			TipMessage(this);
			flag = false;
		}
	});
	return flag;
}



var eoc = eoc || {};
eoc.user = function(){};
/*
 * 用户点击登录按钮的次数
 * 当点击输入错误的用户名和密码大于3次后
 * 将验证码输入框被填充
 * 
 * */
eoc.user.loginNum = 0;
/*
 * 用户点击登录按钮触发的事件
 * 
 * @param obj : 点击目标按钮
 * */
eoc.user.login = function(obj){
	// var _num = eoc.user.loginNum;
	var _form = $("#loginForm");// $(obj).parent().parent();
	var _randomCode = _form.find('input#randomCode');
	
	var _account = _form.find('input#account');
	var _password = _form.find('input#password');
		if(eoc.isEmpty(_account.val())){
		// eoc.alert('提示',"帐号或密码不能为空！");
		if(eoc.user.loginNum>=2){
		   eoc.user.fillValidationCode(obj);
		   _randomCode.val('');
	   }
	    eoc.user.loginNum++;
		$("#loginErrorMsg").html("邮箱不能为空！");
		return ;
	}else{
		var rgexp = /^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$/;
		var account = _account.val();
		var b = rgexp.test(account);
		if(b){
			eoc.user.loginNum++;
			$("#loginErrorMsg").html("邮箱只能为邮箱！");
			return ;
		}
	}
	/*if(eoc.isEmpty(_account.val()) | eoc.isEmpty(_password.val())){
		// eoc.alert('提示',"用户名或密码不能为空！");
		if(eoc.user.loginNum>=2){
		   eoc.user.fillValidationCode(obj);
		   _randomCode.val('');
	   }
	   eoc.user.loginNum++;
	   _account.val(_account.val());
	   _password.val('');
		$("#loginErrorMsg").html("用户名或密码不能为空！");
		return ;
	}*/
	
	if(eoc.user.loginNum >=3 && !eoc.isEmpty(_randomCode) && _randomCode.length>0){
		if(eoc.isEmpty(_randomCode.val())){
			// eoc.alert('提示',"验证码不能为空！");
			if(eoc.user.loginNum>=2){
			   eoc.user.fillValidationCode(obj);
			   _randomCode.val('');
		   }
		   eoc.user.loginNum++;
		   _account.val(_account.val());
		   _password.val(_password.val());
		  $("#loginErrorMsg").html("验证码不能为空！");
		  return ;
		}
	}
	
	var _returnUri = _form.find('input[name=returnUri]').val();
	var _login = _form.find('input.login-btn').val();
	var _param = "userEmail="+_account.val()+"&password="+_password.val()+"&returnUri="+_returnUri+"&login="+_login;
	if(eoc.user.loginNum >=3 && !eoc.isEmpty(_randomCode) && _randomCode.length>0){
		_param += "&randomCode="+_randomCode.val();
	}
	
	$.ajax({
		   url:base+"/user/loginUser/",
		   dataType:"json",
		   type : 'POST',
		   data : _param,
		   error:function(data){
			   //window.location.href=base+"/login/index/?returnURI="+window.location.href;
			   _account.val('');
			   _password.val('');
			   // eoc.alert('提示','请求错误!');
			   $("#loginErrorMsg").html(data.errorMsg);
		   }, 
		   success:function(data){
			   if(data.success==true){
				   eoc.user.loginNum=0;
				   window.location.href=data.returnUri;//"/login/index/?returnURI="+window.location.href;
			   }else{
			   	
				   if(eoc.user.loginNum>=2){
					   eoc.user.fillValidationCode(obj);
					   _randomCode.val('');
				   }
				   eoc.user.loginNum++;
				   _account.val(_account.val());
				   _password.val(_password.val());
				   // eoc.alert('提示',data.errorMsg);
				   $("#loginErrorMsg").html(data.errorMsg);
			   }
		   }
	   });
};
/*
 * 应用ie11下支持input框的placeholder属性;
 * */
eoc.user.applyPlaceholder = function(){
	
	var userAgent = navigator.userAgent.toLowerCase(); 
	//var isIELT10 = /msie/.test(userAgent) && !/opera/.test(userAgent);
	var isNOTIELT11 = ((userAgent.indexOf("webkit")!=-1) | 
			(userAgent.indexOf("firefox")!=-1) | 
			(userAgent.indexOf("chrome")!=-1) | 
			(userAgent.indexOf("msie")==-1));
	
	// /msie/i.test(browserName) && !/opera/.test(browserName)
	// /firefox/i.test(browserName)
	// /chrome/i.test(browserName) && /webkit/i.test(browserName) && /mozilla/i.test(browserName)
	// /opera/i.test(browserName)
	//some browser version info; include the IE11 and lt IE11 and chrome and firefox and safari and opera and so on.
	/*IE11
	mozilla/5.0 (windows nt 6.1; wow64; trident/7.0; slcc2; .net clr 2.0.50727; .net clr 3.5.30729; .net clr 3.0.30729; media center pc 6.0; rv:11.0) like gecko
	IE11以下
	mozilla/4.0 (compatible; msie 7.0; windows nt 6.1; wow64; trident/7.0; slcc2; .net clr 2.0.50727; .net clr 3.5.30729; .net clr 3.0.30729; media center pc 6.0)
	chrome
	mozilla/5.0 (windows nt 6.1; wow64) applewebkit/537.36 (khtml, like gecko) chrome/33.0.1750.117 safari/537.36 
	firefox
	mozilla/5.0 (windows nt 6.1; wow64; rv:27.0) gecko/20100101 firefox/27.0
	safari
	4mozilla/5.0 (windows nt 6.1; wow64) applewebkit/534.57.2 (khtml, like gecko) version/5.1.7 safari/534.57.2
	opera
	mozilla/5.0 (windows nt 6.1; wow64) applewebkit/537.36 (khtml, like gecko) chrome/32.0.1700.107 safari/537.36 opr/19.0.1326.63 */
	
	var support = (function(input) { 
        return function(attr) { return attr in input ;} ;
   })(document.createElement('input'));          
   if ( !(support('placeholder') && isNOTIELT11) ) {               
        $('input[placeholder],textarea[placeholder]').placeholder({ 
             useNative: false,  
             hideOnFocus: false,  
             style: {  
                  textShadow: 'none'  
             }  
        }); 
   } 
   
   if ( !support('autofocus') ) { 
        $('input[autofocus]').focus() ;
   }
};
/*
 * 填充验证码输入框
 * 如果验证码输入框存在，就刷新验证码
 * 不存在就填入输入框
 * 
 * */
eoc.user.fillValidationCode = function(obj){
	//根据登录按钮找到所在的父节点form
	var _form =  $("#loginForm");// $(obj).parent().parent();
	var _randomCode = _form.find('input#randomCode');
	//在父节点查找验证码的是否存在
	//存在就点击刷新验证码
	//不存在就把验证码输入框填充到form中
	if(!eoc.isEmpty(_randomCode) && _randomCode.length>0){
		_randomCode.next().click();
	}else{
		var validationCode = '<div class="uinArea mb10">';
		validationCode += '<input type="hidden" id="hdrandomCode"/>';
		validationCode += '<input type="text" id="randomCode" autocomplete="off"  placeholder="验证码" name="randomCode" style="width:65%;" />&nbsp;';
		validationCode += '<img title="点击更换" onclick="javascript:refresh(this);" src="'+base+'/user/CheckCode/">';
		validationCode += '</div>';
		$(validationCode).insertBefore($(obj).parent());
		var _loginConDiv = $('div.loginCon');
		_loginConDiv.height(_loginConDiv.height()+50);
		eoc.user.applyPlaceholder();
	}
};
/*
 * 判断是否为空
 * 
 * */
eoc.isEmpty = function(str){
	return str == null || str == "" || str == undefined;
};

//得到字节长度
String.prototype.len = function() {
    return this.replace(/[^\x00-\xff]/g, 'xx').length;
};

