<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/header.jsp"%>
<body class="easyui-layout">
<div data-options="region:'north',border:false,href:'page/top.jsp'" style="height:91px;background:#B3DFDA;padding:0px">north region</div>
<div data-options="region:'west',split:true,title:'菜单信息',href:'page/left.jsp'" id="cms_left" style="width:200px;">west content</div>
<div data-options="region:'center'" id="cms_content">
    <div title="欢迎">
        <div class="bg"></div>
    </div>
</div>
<%@ include file="/page/common/footer.jsp"%>
<script type="text/javascript">
	eoc.cms.main();
</script>
</body>
</html>