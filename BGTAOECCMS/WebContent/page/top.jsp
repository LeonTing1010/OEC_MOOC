<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/header.jsp"%>
<body>
	<script type="text/javascript">
		function ajaxGetCourseUnauditedNum() {
			var url = "course/ajaxGetCourseUnauditedNum";
			$.ajax({
				url : url,
				success : function(data) {
					if (data.flag) {
						$("#unauditedCourseNum").html(data.num);
					} else {
						$.messager.alert('警告', '获取未审核课程失败');

					}
				}

			});

		}
	</script>


	<script type="text/javascript">
		$(function($) {
			ajaxGetCourseUnauditedNum();
		});
	</script>
	<div class="header">
		<div class="logo">
			<a href="#"></a>
		</div>
		<div class="headBar">
			<span>当前用户：运营管理员</span> <span><a
				href="${applicationScope.ctx}/logout">安全退出</a></span>
		</div>
	</div>
	<div class="messageCenter">
		<span>未审核课程：<!--  <a href="#" id="unauditedCourseNum"></a> -->
		<label id="unauditedCourseNum"></label>
		</span>
	</div>


</body>
</html>