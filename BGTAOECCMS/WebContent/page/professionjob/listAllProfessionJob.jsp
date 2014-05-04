<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/header.jsp"%>
<body>
<div id="searchFormPanel" iconCls="icon-search">
   <form id="professionForm" method="post" buttons="#professionJobdlg-buttons">
	<table cellspacing="0" cellpadding="0" class="mt10 ml10 queryTable">
		<tr>
			<td>按职业名称：</td>
			<td><input class="easyui-validatebox " type="text" name="proName_gp" /></td>
			<td>按职业描述：</td>
			<td><input class="easyui-validatebox" type="text" name="proDescription_gp" /></td>
			<!-- <td>行业推荐:</td>
			<td><input class="easyui-combobox" type="text" id="proRecommend_Combox" name="proRecommend_gp" /></td> -->
			<td>按创建日期：</td>
			<td>从&nbsp;&nbsp;<input type="text" name="startDate_gte" />&nbsp;&nbsp;到&nbsp;&nbsp;<input type="text" name="endDate_lt" />
			</td>
			<td>
				<div id="professionJobdlg-buttons" align="right">
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" onclick="eoc.cms.professionJob.resetForm();">重置</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="eoc.cms.professionJob.queryForm();">查询</a>
				</div>
			</td>
		</tr>
	</table>		
   </form>
</div>
<div id="profession_View_Dialog"></div>
<div id="professionJob_View_Dialog"></div>
<div id="professionJob_Add_Dialog"></div>
<div id="professionJob_Modify_Dialog"></div>
<table cellspacing="0" cellpadding="0" id="AllProfessionJobTreeGrid"></table>
<div id="contextmenuDiv" class="easyui-menu" style="width:120px;">
    <div onclick="addProfession();" data-options="iconCls:'icon-add'">增加</div>
    <div onclick="editProfession();" data-options="iconCls:'icon-edit'">修改</div>
    <div onclick="addChild();" data-options="iconCls:'icon-add'">添加子分类</div>
    <div class="menu-sep"></div>
    <div onclick="collapse()">收起</div>
    <div onclick="expand()">展开</div>
</div>
<script type="text/javascript">
	var _page = '${page}';
	_page = jQuery.parseJSON(_page);
</script>
<script type="text/javascript" src="${ctx}/js/professionjob/eoc-professionjob.js"></script>
<%@ include file="/page/common/footer.jsp"%>
</body>
</html>