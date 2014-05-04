$(function () {
    $("[tag]").blur(function () {
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
    
    $("#pwd").focus(function(){
    	$("#pwd2").attr("disabled",false);
        $("#passButton").attr("disabled",false);
    })
});


/**返回正确提示**/
function rightSpan(){
	return "<span class='Okspan'><span class='validation'><i class='ok-icon'></i><span class='ok'>&nbsp;</span></span></span>"
}
/**返回错误提示**/
function errorSpan(errorMessage){
	return  "<span class='errspan'><span class='validation'><i class='error-icon'></i><span class='tip'>"+errorMessage+"</span></span></span>"
}


errmessage = "";
//添加错误提示信息
function TipMessage(obj) {
  if ($(obj).nextAll(".verifytip").length == 0) {
      $(obj).nextAll(".errspan").remove();
      var curTr=$(obj).parent().parent().next("tr")
      if (curTr && curTr.attr("class") == "trdisplay") {
          $(curTr).remove();
      }
      $(obj).nextAll(".Okspan").remove();
      if (errmessage == "*") {
          $(obj).after(errorSpan('该项为必填项'));
      }
      else {
      	$(obj).after(errorSpan(errmessage));      	
      }
  }
  else {
      $(obj).nextAll(".verifytip").html("<span style='color:Red; font-size:13px; vertical-align: middle;'>" + errmessage + "</span>");
  }
}


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
            case "*": //非空
                return true;
				
            case "6-12": //6-12个字符
                var rgexp = /^[\w]{6,12}$/;
                errmessage = "应为6-12个字符";
                return rgexp.test(vals);
            case "ptp"://验证密码是否一致
                var pwd1 = $("#pwd").val();
                var pwd2 = $("#pwd2").val();
                if (pwd1 == pwd2) {
                    return true;
                } else{
                    errmessage = "两次的密码输入不一致";
                    return false;
                }
            case "mobile":// 手机号码
    			var rgexp = /^1[3-9]\d{9}$/;
    			errmessage = "应为手机号码";
    			return rgexp.test(vals);
            case "telormob": // 手机电话号码
    			var rgexp = /(^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$)|((^(1[3-9]\d{9})?$))/;
    			errmessage = "应为有效的电话号码";
    			return rgexp.test(vals);
            case "email":// 邮箱
    			var rgexp = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,3}$/;
    			errmessage = "应为邮箱";
    			return rgexp.test(vals);
    			
            case "nameCheck": // 名称验证
    			//var rgexp = /^([\u4E00-\u9FA5\uF900-\uFA2D]|\w){1,7}$/;
    			errmessage = "<span style='color:red;'>1~7个字符，可使用字母、数字、下划线</span>";
    			return (vals.len()>=1 && vals.len()<=14);
    			
            case "CheckExistUser": // 名称验证
    			checkUserName();
    			return true;
    			
            case "CheckExistEmail": // 邮箱验证
    			checkEmail();
    			if ($("#hiduseremail").val()) {
    				errmessage = "电子邮箱已存在";
    				$("#hiduseremail").val("");
    				return false;
    			}
    			return true;
            case "isSame":  //校验新旧密码是否相同
            	var oldPass=$("#oldpwd").val();
            	var newPass=$("#pwd").val();
            	if(oldPass!=newPass){
            		$("#pwd2").attr("disabled",false);
            		$("#passButton").attr("disabled",false);
            		return true;
            	}else{
            		errmessage = "新密码与原密码相同";
            		$("#pwd2").attr("disabled",true);
            		$("#passButton").attr("disabled",true);
                    return false;
            	}
            
            default:
                return false;
        }
    }
}

function checkEmail() {
	var userTemp = $("#emailID").val();
	$
			.ajax({
				url : base + "/user/checkusereamil.ajax",
				data : {
					userEamil : userTemp
				},
				success : function(data) {
					if (data.result==true) {
//						$('#emailID').parent().find('.Okspan').remove();
						$('#emailID').parent().find('.errspan').remove();
						
					}else if(data.result==false){
						$('#emailID').parent().find('.Okspan').remove();
						$('#emailID').parent().find('.errspan').remove();
						$('#emailID').after(errorSpan('邮箱已注册'));
	
					}
				}
			});
}

function verify(obj) {
    var flag = true;
    var types = obj.attr("tag"); //获取验证对象需要进行何种验证处理
    //var objValue = $.trim(obj.attr("value")); //获取验证对象的值  
    var objValue = $.trim( obj.context.value); //获取验证对象的值  
    var typestr = new Array(); //定义一个数组，用来存放用户验证处理需求点
    typestr = types.split(",");
    for (var i = 0; i < typestr.length; i++) {
        var type = typestr[i].toString();
        if (tagcheck(type, objValue)) {//满足验证要求   
        }
        else {           
            flag = false;
            break;
        }
    }
	$(obj).after(rightSpan());   
	return flag;
}

function delMessage(obj) {
    if ($(obj).nextAll(".verifytip").length == 0) {
        var curTr = $(obj).next("tr")
        if (curTr && curTr.attr("class") == "errspan") {
            $(curTr).remove();
        }
        $(obj).nextAll(".errspan").remove();
    }
    else {
        $(obj).nextAll(".verifytip").html("");
    }
}

function pwdverify(element) {
    var flag = true;
    $("[tag]").each(function () {
    	$(this).nextAll(".Okspan").remove();
    	var $name = $(this).attr('name');
    	if($name == 'oldpwd' || $name == 'newpwd' || $name == 'submitpwd'){
	        if (!verify($(this))) {
	            TipMessage(this);
	            flag = false;
	            $(element).siblings().remove();
	        }
    	}
    });
    if(flag){
    	 var params = $('#pwdupdate').serialize();
    		$.ajax({
    			url : base+"/studentCenter/myinfo/updatepwd/?code="+Math.random(),
    			data : params,
    			success : function(data){
    				if(data=="right"){
    					$(element).siblings().remove();
    					$(element).after("<span style='color:#8dce1a;margin-left:15px'>密码修改成功!</span>");
    					
    					$('#pwdupdate .validation').parent().remove();
    					$('#pwdupdate input').val('');
    					setTimeout(function(){
    						$('#fourthGrid span').last().remove();
    					},1000);
    					
    				}else{
    					$(element).siblings().remove();
    					$(element).after(errorSpan('原始密码错误'));
    				}
    				
    		    }
    		});
    		return flag;
    }else{
    	return flag;
    }
}

function checkUserName() {
	var userTemp = $("#userName").val();
	$.ajax({
			url : base + "/user/checkusername.ajax?code="+Math.random(),
				data : {
					username : userTemp
				},
				success : function(data) {
					if (data.result) {
						$('#userName').parent().find('.Okspan').remove();
						$('#userName').parent().find('.errspan').remove();
						$('#userName').after(errorSpan('用户名已存在'));
					}
				}
			});
}

function formverify() {
	var flag = true;
	$("[tag]:visible").each(function() {
		$(this).nextAll(".Okspan").remove();
		if (!verify($(this))) {
			TipMessage(this);
			flag = false;
		}
	});
	//去除必填标识
	$(".flag").each(function(){
		$(this).remove();
	})
	
	//flag = flag && notExitsUserName && notExitsEmail && randomCodeRight;
	return flag;
}

/**得到字节长度**/
String.prototype.len=function(){
	return this.replace(/[^\x00-\xff]/g,'xx').length;
}