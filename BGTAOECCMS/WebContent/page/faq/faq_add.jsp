<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<style type="text/css">
   #dlg-buttons{
   	margin-top: 20px;
   	margin-left: 500px;
   }
   #faq_fm{
   	margin-left: 7px;
   }
</style>
<link rel="stylesheet" href="${applicationScope.ctx}/js/kindeditor/themes/default/default.css" />
<script type="text/javascript" src="${applicationScope.ctx}/js/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="${applicationScope.ctx}/js/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript">
//kindeditor加载配置
var editor;
editor = KindEditor.create('textarea[name="faqContent"]', {
	allowPreviewEmoticons : false,
	allowImageUpload : true,          //是否开启本地上传
	basePath : 'js/kindeditor/',    //重要的一点
	resizeType:0,
    allowUpload : true, //允许上传图片
	cssPath : '${ctx}/js/kindeditor/plugins/code/prettify.css',
	uploadJson : '${ctx}/js/kindeditor/jsp/upload_json.jsp',
	fileManagerJson : '${ctx}/js/kindeditor/jsp/file_manager_json.jsp',
	allowFileManager : true,
	afterCreate : function() {
		this.sync();
	},
	afterBlur : function() {
		this.sync();
	},                                          /*  增加这一段后可以获取值 */
	afterChange : function() {
		this.sync();
	},
	items : [
		'bold', 'italic', 'underline',
		'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
		'insertunorderedlist', '|',  'image']
});

</script>	
		<div class="ftitle">FAQ信息</div>
		<form id="faq_fm" name="faq_fm" method="post" novalidate>
			<input type="hidden" name="faqID" value="${faqID}" />
			<div class="fitem">
				<label>标题</label> 
				<input class="easyui-validatebox" name="faqTitle" value="${faqTitle}"/>
				<label>选择节点</label>
				<!-- <input class="easyui-validatebox" name="faqTitle" required="true" > -->
				<input class="easyui-combobox" id="faqPID" name="faqTitle2" style="width: 150px;" />
				<script type="text/javascript">
		            function selectInit(id){
		            	   $("#"+id).combobox({
				            	url:'faq/findTitle',  
		                  	  	valueField:'faqID',    //value
		                    	textField:'faqTitle',
		                   	 	panelHeight:'auto',
		                   	 	editable:false,
		                   	 onLoadSuccess: function (data) {   //解决默认选中的问题  onLoadSuccess加载远程数据执行成功时调用的方法
		                         if (data) {
		                             $('#faqPID').combobox('setValue','${faqTitle}');
		                         }
		                     }
			            });
		            }
		            $(document).ready(function(){   //调用下拉框的方法
		            	selectInit("faqPID"); 
		            });
		        </script>
				<p>FAQ内容：</p>
				<label></label>
				<textarea id="editor_id" name="faqContent" style="width:700px;height:300px;">${faqContent}</textarea>
			</div>
		</form>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveFAQ(this)">保存</a> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#faq_panel').dialog('close')">取消</a>
	</div>
</body>
</html>