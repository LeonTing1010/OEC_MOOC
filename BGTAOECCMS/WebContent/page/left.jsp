<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/header.jsp"%>
<body>
	<div class="easyui-accordion" data-options="fit:true">
		<div title="会员管理" data-options="iconCls:'icon-edit'">
			<ul id="uul" class="easyui-tree"></ul>
			<script type="text/javascript">
				$('#uul').tree({
					data: [/* {
						text: 'DEMO',
						url : '/user/findAJUser/'
					}, */{
						text: '会员管理',
						url : '/user/searchAJUser/'
					}],
					onClick : function(node){
						eoc.cms.main.openMainTab(node);
					}
				});
			</script>
		</div>
		<div title="课程内容管理" data-options="iconCls:'icon-edit'">
			<ul id="cul" class="easyui-tree"></ul>
			<script type="text/javascript">
				$('#cul').tree({
					data: [{
						text: '课程管理',
						url : '/course/findCourseManage/'
					},{
						text: '点评管理',
						url : '/comment/searchComment/'
					}],
					onClick : function(node){
						eoc.cms.main.openMainTab(node);
					}
				});
			</script>
		</div>
		<div title="频道-栏目管理" data-options="iconCls:'icon-edit'">
			<ul id="clul" class="easyui-tree"></ul>
			<script type="text/javascript">
				$('#clul').tree({
					data: [/* {
						text: '首页管理',
						url : '/user/userPageList/'
					}, */{
						text: '职业管理',
						url : '/profession/listAllProfessionInit/'
					},{
						text: '行业图片管理',
						url : '/pro/searchAJProfession/'
					},{
						text: '答疑管理',
						url : '/qacenter/main/'
					},/* {
						text: '考试成绩管理',
						url : '/user/userPageList/'
					}, */{
						text: '学校管理',
						url : '/school/findSchool/'
					}],
					onClick : function(node){
						eoc.cms.main.openMainTab(node);
					}
				});
			</script>
		</div>
		<div title="系统管理" data-options="iconCls:'icon-edit'">
			<ul id="sul" class="easyui-tree">
				<!-- <li  data-options="state:'closed'">
					<span>权限管理</span>
					<ul>
						<li>用户管理</li>
						<li>角色管理</li>
						<li>权限管理</li>
						<li>菜单管理</li>
					</ul>
				</li>
				<li>消息管理</li>
				<li>FAQ管理</li>
				<li>公告管理</li>
				<li>广告管理</li> -->
			</ul>
			<script type="text/javascript">
				$('#sul').tree({
					data: [{
						text: '消息管理',
						url : '/message/searchMessageUI/'
					},{
						text: 'FAQ管理',
						url : '/faq/searchFAQ/'
					},{
						text: '公告管理',
						url : '/user/userPageList/'
					},{
						text: '广告管理',
						url : '/user/userPageList/'
					},{
						text: '意见反馈',
						url : '/feedback/searchFeedback/'
					}],
					onClick : function(node){
						eoc.cms.main.openMainTab(node);
					}
				});
			</script>
		</div>
	</div>
</body>
</html>	