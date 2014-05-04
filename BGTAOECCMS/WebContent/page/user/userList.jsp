<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/header.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List Page</title>
</head>
<body>
<h1>User List Page : ${listMessage}</h1>&nbsp;== ${ctx} &nbsp;<button onclick="testAsyncLoadJs();">testAsyncLoadJs</button>
<table border="1px;">
<tr>
	<td>userId</td>
	<td>roleID</td>
	<td>userName</td>
	<td>userMobile</td>
	<td>userEmail</td>
	<td>password</td>
	<td>userType</td>
	<td>userHeadPic</td>
	<td>userLoginInfo</td>
	<td>userResetPwdCode</td>
</tr>
<c:forEach var="user" items="${users}" varStatus="index">
<tr>
	<td>${user.userId}</td>
	<td>${user.roleID}</td>
	<td>${user.userName}</td>
	<td>${user.userMobile}</td>
	<td>${user.userEmail}</td>
	<td>${user.password}</td>
	<td>${user.userType}</td>
	<td>${user.userHeadPic}</td>
	<td>${user.userLoginInfo}</td>
	<td>${user.userResetPwdCode}</td>
</tr>                           
</c:forEach>
</table>
<!-- common footer include js file -->
<%@ include file="/page/common/footer.jsp"%>
<script type="text/javascript">
eoc.cms.main.test();
eoc.cms.util.test();
function testAsyncLoadJs(){
	eoc.cms.util.loadJs(ctx+'/js/main/eoc-test.js',null);
}
</script>
<!-- common footer include js file -->
</body>
</html>