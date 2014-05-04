	function sendEmail(){
		var b = beforeSendEmail();
		if(b){
			sendEmailAjax();
		}
	}
	// 提交前对表单做验证
	function beforeSendEmail(){
		var b = true;
		var receiveUserEmail = $("#receiveUserEmail").val();
		b = receiveUserEmail.length > 0 ? true : false ;
		if(!b){
			$("#remindInfor").html("邮箱不能为空");
			return b;
		}
		var rgexp = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
		b = rgexp.test(receiveUserEmail);
		if(!b){
			$("#remindInfor").html("邮箱格式不正确！");
			return b;
		}
		return b;
	}
	function sendEmailAjax(){
		$.ajax({
			url:'/user/sendemail/',
			type : 'POST',
			dataType : 'json',
			data : {"userEmail":$("#receiveUserEmail").val()},
			error:errorSendemail,
			success:successSendemail
	  });
	}
	function errorSendemail(data) {}
	function successSendemail(data) {
		if(data.result == 1){
			$("#remindInfor").html("邮件已发送，请查收！");
			$("#hidUserEmail").val($("#receiveUserEmail").val());
		}else if(data.result == 2) {
			$("#remindInfor").html("邮件发送失败，请重新发送！");
		}
	}
	
/**********************************************************/
	function verificateEmailCode(){
		var b = verificateCode();
		if(b){
			verificateEmailCodeAjax();
		}
	}
	function verificateEmailCodeAjax(){
		$.ajax({
			url:'/user/passwordtwo/',
			type : 'POST',
			dataType : 'json',
			data : {"hduserEmail":$("#receiveUserEmail").val(),"emailCode":$('#emailCodeId').val()},
			error:function(data){
				alert(data.result);
			},
			success:function(data){
			   if(data.result == 1){
			   		$("#emailCodeMsg").html("未向邮箱发送验证码！");
				}else if(data.result == 2) {
					$("#emailCodeMsg").html("邮件验证码错误！");
				}else{
					window.location.href = "/user/skipToForgetPassword2/";
				}
			}
	  });
	}
	function verificateCode(){
		var b = true;
		var emailCodeValue = $("#emailCodeId").val();
		if(emailCodeValue == null || emailCodeValue == ""){
			$("#emailCodeMsg").html("请输入验证码！");
			b = false;
		}
		return b;
	}
	function errorVerificateEmailCode(data) {}
	function successVerificateEmailCode(data) {
		if(data.result == 0){
			window.location.href = "/user/skipToForgetPassword2/";
		}else if(data.result == 1){
			$("#emailCodeMsg").html("请输入验证码！");
		}else if(data.result == 2){
			$("#emailCodeMsg").html("邮件验证码错误！");
		}
	}
			