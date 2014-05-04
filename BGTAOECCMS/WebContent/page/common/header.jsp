<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>国泰安网络教育管理系统</title>
	<link rel="stylesheet" type="text/css" href="${applicationScope.ctx}/css/themes/easyui.css">
	<link rel="stylesheet" type="text/css" href="${applicationScope.ctx}/css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${applicationScope.ctx}/css/index.css">
	<link rel="stylesheet" type="text/css" href="${applicationScope.ctx}/css/popWindows.css">
	<link rel="stylesheet" type="text/css" href="${applicationScope.ctx}/css/general.css">
	<link rel="stylesheet" type="text/css" href="${applicationScope.ctx}/css/themes/demo.css">
	<script type="text/javascript" src="${applicationScope.ctx}/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${applicationScope.ctx}/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${applicationScope.ctx}/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		var ctx = '${applicationScope.ctx}';
		var cache='${applicationScope.ResourcesWebSite}';
	</script>
</head>