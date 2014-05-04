<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>User List</title>
</head>
<body>
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
	<div style="padding:20px;">${pageStr}</div>
</body>
</html>