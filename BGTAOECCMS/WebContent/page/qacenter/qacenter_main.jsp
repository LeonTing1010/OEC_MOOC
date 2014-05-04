<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- common header include js file -->
<%@ include file="/page/common/header.jsp"%>
<!-- common header include js file -->
<body>
	<link rel="stylesheet" type="text/css"
		href="${ctx}/css/qacenter/detailedQuestions.css">
	<div id="p" class="easyui-panel" title="查询条件"
		style="padding:15px;background:#fafafa;height:130px"
		data-options="iconCls:'icon-search'">
		<form id="searchForm">
			<table>
				<tbody>
					<tr>
						<td><label for="courseNameS">按课程名:&nbsp;</label><input
							class="easyui-validatebox" type="text" name="courseNameS"
							id="courseNameS" /></td>
						<td><label for="professionIdS">按行业名:&nbsp;</label><input
							id="professionIdS" name="professionIdS" style="width:150px;"></td>
						<td><label for="jobGroupIdS">&nbsp;-&nbsp;</label><input
							id="jobGroupIdS" name="jobGroupIdS" style="width:150px;"></td>
						<td><label for="chosenQuestionOrNotS">按是否推荐:&nbsp;</label><input
							type="text" name="chosenQuestionOrNotS" id="chosenQuestionOrNotS" /></td>
						<td><label for="questionContentS">按问题名:&nbsp;</label><input
							class="easyui-validatebox" type="text" name="questionContentS"
							id="questionContentS" /></td>
					</tr>
					<tr>
						<td><label for="userNameS">按用户名:&nbsp;</label><input
							class="easyui-validatebox" type="text" name="userNameS"
							id="userNameS" /></td>
						<td><label for="visibleQuestionOrNotS">按状态:&nbsp;</label><input
							id="visibleQuestionOrNotS" name="visibleQuestionOrNotS"
							style="width:100px;"></td>
						<td><label for="questionTypeS">按类型:&nbsp;</label> <input
							id="questionTypeS" name="questionTypeS" style="width:100px;">
						</td>
						<td><label for="timeFromS">按创建日期:&nbsp;从&nbsp;</label><input
							id="timeFromS" type="text" /></td>
						<td><label for="timeToS">&nbsp;到&nbsp;</label><input id="timeToS"
							type="text" /></td>
						<td><a id="resetBtnS" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-undo'">重置</a></td>
						<td><a id="searchBtnS" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-search'">查询</a></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div>
		<table id="qacenter_table"></table>
	</div>
	<div id="popupWindowOfQuestionDital" style="padding:10px; "></div>
	<script type="text/javascript">
		var _page = '${page}';
		_page = jQuery.parseJSON(_page);
	</script>
	<script type="text/javascript" src="${ctx}/js/qacenter/qacenter.js"></script>
	<!-- common footer include js file -->
	<%@ include file="/page/common/footer.jsp"%>
	<!-- common footer include js file -->
</body>
</html>