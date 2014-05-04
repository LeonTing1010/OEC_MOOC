<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ include file="/page/common/header.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title> 


</head>



<body>

<!-- 	
	<script type="text/javascript" src="js/jquery_plugins/ajaxfileupload.js"></script>
	<script type="text/javascript" src="js/user/user_upload.js"></script> -->

<form id="fm" method="post" enctype="multipart/form-data"> 
	<table id="list_data'">
		<tr>
		<td>
			<input type="file" name="userFile" id="userFile"  onchange="ajaxFileUpload();"> <input type="button" value="上传"  onclick="ajaxFileUpload(1);"/>
		 		 <b style="position: absolute; top:10px; left: 100px;">
					<div style ="display: none;"  id ="loading"><img  src ="./js/jquery_plugins/loading.gif"  /></div></b>
			</td>
		</tr>
		<tr>
		
			<td >
				   <ul style="margin:7px 8px; border:1px solid #ccc;" id="show">
			 			
           			</ul>
           				 <div color="#666666">
           				 <input  type="text" id="ss" value="0"/>
		           				 <script type="text/javascript">
		           				$('#ss').spinner({ 
										required:true, 
										increment:10 
										}); 
		           				</script>
           				 </div>
			</td>
			<input  type="hidden"   id="userImgUrl"/>
		</tr>
	</table>
  </form>
  </body>
 
</html>