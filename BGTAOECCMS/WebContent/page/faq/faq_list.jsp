<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/header.jsp"%>
<body>
<div class="easyui-panel" title="FAQ管理" style="width:1250px">
        <div style="padding: 10px" >
        <form id="promanage" method="post" buttons="#prodlg-buttons">
            <table>
                <tr>
                    <td class="t">标题：</td>
                    <td>
                    	<input class="easyui-validatebox" type="text" id="faqTitle" id="faqTitle" style="width:210px;height:20px"></input>
                    </td>
                    <td>
                	<div id="prodlg-buttons">
                		<a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-undo" onclick="resetForm()">重置</a>
                		<a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-search" onclick="queryForm()">查询</a>
                	</div>	
                	</td>
                </tr>
            </table>
        </form>
        </div>
</div>
	<div id="faq_panel"></div>
	<table id="faq_DataGrid" cellspacing="0" cellpadding="0"></table>
	<!-- DataGrid -->
	<script type="text/javascript">
	//多条件查询
	function queryForm(){
		seachParams();
	}
	
	//重置form
	function resetForm(){
		$('#promanage').form('reset');
	}
	
	//封装分页参数
	function seachParams(){

	var faqTitle=$("#faqTitle").val();
	var _queryParams = $('#faq_DataGrid').datagrid('options').queryParams;
	    $('#faq_DataGrid').datagrid({
	    	queryParams: {
	    		faqTitle_gp: faqTitle,
	    		
	    		//分页参数
	    		pageIndex : _queryParams.pageIndex,
	    		pageSize : _queryParams.pageSize 
	    	}
	    });
		
	}	
	

	//显示数据
	var _page = '${page}';
	_page = jQuery.parseJSON(_page);
	var _h = $("#promanage").height();  
	//datagrid初始化  
    $('#faq_DataGrid').datagrid({
        //title:'职业管理列表',  
        iconCls:'icon-edit',//图标  
/*      width: eoc.cms.main.tabsVolume.width,  
        height: eoc.cms.main.tabsVolume.height, */
 		width: eoc.cms.main.tabsVolume.width,  
        height: eoc.cms.main.tabsVolume.height-_h-50,  
        nowrap: false,  
        striped: true,  
        border: true,  
        collapsible:false,//是否可折叠的  
        url:'faq/findFAQ',  
        remoteSort:false,   
        singleSelect:true,//是否单选  
        pagination:true,//分页控件  
        rownumbers:true,//行号  
		columns:[[
			{field:'faqTitle',title:'标题', width:200},
	  		{field:'faqContent',title:'内容', width:550},
	  		{field:'faqCTime',title:'创建时间', width:200
	  		},
	  		{field:'opt',title:'操作',width:250,formatter : function(value,row,index){
	  			var _row = JSON.stringify(row);
	  			var s = "<a href='javascript:void(0);' onclick='modFunc("+_row+")'>编辑</a> ";  
	  			var e = "<a href='javascript:void(0);' onclick='delFunc("+_row+")'>删除</a> ";
              	return s+e;
	  		}}
		]],
		queryParams: {
			'pageIndex': _page.pageIndex,
			'pageSize': _page.pageSize
		},  
        toolbar: [{  
            text: '添加',  
            iconCls: 'icon-add',  
            handler: function() {  
				addFunc(); 
            } 
		}],  
    });
	//设置分页控件
	var pageInfo = {pageSize: _page.pageSize,pageList: _page.pageList,params:"key=value"};//&key1=value1&key2=value2
	eoc.cms.main.createPagination(pageInfo,$('#faq_DataGrid'));
	
	var url;
	function addFunc(){
		url='faq/addFAQ'; 
		$('#faq_panel').dialog({    
		    title: '新增FAQ信息',    
		    width: 730,    
		    height: 550,    
		    closed: false,    
		    cache: false,    
		    href: 'page/faq/faq_add.jsp',    
		    modal: true   
		});    
		
	}
	
	function modFunc(row){
		$('#faq_panel').dialog({    
		    title: '修改FAQ信息',    
		    width: 730,    
		    height: 550,    
		    closed: false,    
		    cache: false,    
		    href: 'faq/disposeFAQ?faqTitle='+row.faqTitle+'&faqContent='+row.faqContent+'&faqID='+row.faqID,    
		    modal: true   
		});
        $('#faqPID').combobox('setValue',row.faqTitle);      /* 把获取到的值赋给下拉框  在此处价格   在此处加个 / 即可实现绑定下拉框 */ 
		url='faq/updateFAQ';
	}


	
	function saveFAQ(obj){
		/* var title2Check = $('#faqPID').combobox('getValue');
  		if(title2Check!=null && title2Check!=''){ */
		$('#faq_fm').form('submit',{
	        url: url,
	        onSubmit: function(){
	            return $(this).form('validate');
	        },
	        success: function(result){
	        	$('#faq_panel').dialog('close');        // close the dialog
	            var result = eval('('+result+')');
	            if (result.success){
	            	seachParams();                    //再次调用刷新数据
	                $.messager.show({
	                    title: 'Success',
	                    msg: '保存成功！'
	                });
	            } else {
	            	$.messager.show({
	                    title: 'Error',
	                    msg: '保存失败！'
	                });
	            }
	            $(obj).linkbutton('enable');
	        }
	    });
  		/* }else{
  			$.messager.alert('提醒','请选择要操作的节点名称!');
  		} */
	}
	
	function delFunc(row){
		 $.messager.confirm('Confirm','確定要刪除？',function(r){
	            if (r){
	                $.post('faq/delFAQ',{faqID:row.faqID},function(result){
	                    if (result.success){
	                        $('#faq_DataGrid').datagrid('reload'); 
	                        $.messager.show({
	    	                    title: 'Success',
	    	                    msg: '删除成功!'
	    	                });
	                    } else {
	                        $.messager.show({    // show error message
	                            title: 'Error',
	                            msg: '该节点下面还有子节点，不能被删除！'
	                        });
	                    }
	                },'json');
	            }
	        });
	}
	
	
</script>
</body>
</html>