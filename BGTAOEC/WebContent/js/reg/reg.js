/*
 * Funciton:密码强度验证
 * Add   BY:缪佳 
 * Add Data:2014-01-13
 *  * */

$(function() {
	$("#pwd").blur(function() {

		CheckPWD();
	});
});
function CheckPWD() {
	var varPwd = $("#pwd").val();
	AuthPasswd(varPwd);
	if(varPwd=="")
	{
		$("#divSaf").width("0%");
	}
}
//强度判断
function AuthPasswd(string) {
	var strongRegex = new RegExp(
			"^(?=.{6,12})((?=.*[A-Z])|(?=.*[a-z]))(?=.*[0-9])(?=.*\\W).*$", "g");
	var mediumRegex = new RegExp(
			"^(?=.{6,12})((((?=.*[A-Z])|(?=.*[a-z]))(?=.*[0-9]))|(((?=.*[A-Z])|(?=.*[a-z]))(?=.*\\W))|((?=.*\\W)(?=.*[0-9]))).*$",
			"g");
	var enoughRegex = new RegExp("(?=.{6,12}).*", "g");
	if (false == enoughRegex.test(string)) {
	} else if (strongRegex.test(string)) {
		noticeAssign(1);
	} else if (mediumRegex.test(string)) {
		noticeAssign(0);
	} else {
		noticeAssign(-1);
	}
}
//强度分析显示
function noticeAssign(num) {
	if (num == 1) {
		$("#divSaf").width("100%");
	}
	if (num == 0) {
		$("#divSaf").width("70%");
	}
	if (num == -1) {
		$("#divSaf").width("40%");
	}
}