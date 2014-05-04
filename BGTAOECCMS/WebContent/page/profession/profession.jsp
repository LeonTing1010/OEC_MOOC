<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/header.jsp"%>
<body>
<style type="text/css">
    #pfm{
        margin:0;
        padding:10px 30px;
    }
    .ftitle{
        font-size:14px;
        font-weight:bold;
        padding:5px 0;
        margin-bottom:10px;
        border-bottom:1px solid #ccc;
    }
    .fitem{
        margin-bottom:5px;
    }
    .fitem label{
        display:inline-block;
        width:130px;
    }
    
    .fitem input{
    	width:190px;
    }
    .t{
    	padding: 0px 0px 0px 30px;
    }
    .btn-file-input{
	cursor: pointer;
    direction: ltr;
    font-size: 23px;
    margin: 0;
    opacity: 0;
    position: absolute;
    right: 0;
    top: 0;
    transform: translate(-300px, 0px) scale(4);
	height: 30px;
    line-height: 30px;
	filter:alpha(opacity:0);
	}
	.btn-file{
	overflow: hidden;
	display: inline-block;
    position: relative;
	}
	#c{
		margin-bottom: 22px;
	}
</style>
<div class="easyui-panel" title="行页图片管理" style="width:1250px">
        <div style="padding: 10px" >
        <form id="promanage" method="post" buttons="#prodlg-buttons">
            <table>
                <tr>
                    <td class="t">职业名称：</td>
                    <td>
                    	<input class="easyui-validatebox" type="text" id="proName" name="proName" style="width:210px;height:20px"></input>
                    </td>
                    <td class="t">职业描述：</td>
                    <td>
                    	<input class="easyui-validatebox" type="text" id="proDescription" name="proDescription" style="width:210px;height:20px"></input>
                    </td>
                    <td class="t" align="center">是否推荐：</td>
						<td>
							<select name="proRecommend"  id="proRecommend" class="easyui-combobox" data-options="panelHeight:'auto',editable:false" style="width:150px;" >
								<OPTION  value="">-------请选择-------</OPTION>
								<OPTION  value="1">是</OPTION>
								<OPTION  value="0">否</OPTION>
							</select>
						</td>
                    <td class="t">
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




<table id="professionlist_data" cellspacing="0" cellpadding="0"></table>   <!-- DataGrid -->
<div id="pro_dlg" class="easyui-dialog" style="width:450px;height:450px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
    <div class="ftitle">用户信息</div>
    <form id="pfm" method="post" novalidate>
    	<input type="hidden" name="proID"> 
        <div class="fitem">
            <label>职业名称:</label>
         	<input class="easyui-combobox"  id="proID" 
					            			name="proName" style="width:150px;"
					            />
					            <script type="text/javascript">
					            function selectInit(id){
					            	   $("#"+id).combobox({
							            	url:'pro/findName',  
					                  	  	valueField:'proID',    //value
					                    	textField:'proName',
					                   	 	panelHeight:'108',        //设置下拉框高度  auto自适应长度
					                   	 	editable:false,
						            });
					            }
					         
					            $(document).ready(function(){   //调用下拉框的方法
					            	selectInit("proID"); 
					            	//selectInit("proId");
					            	
					            });
					        
					            </script>
        </div>
        <div class="fitem">
            <label id='c'>行业信息图片:</label>
            <span class="btn btn-small btn-default btn-file"> 
            <!-- <input id="proFile" type="file" name="proFile"  onchange="ajaxFileUpload();" required="true"> -->
				<span class="fileupload-new">请选择图片</span>
				<input id="proFile" type="file" name="proFile" class="margin-none btn-file-input"  required="true" onchange="ajaxFileUpload_pro();"/>
			</span>
            <input type="hidden" id="proImage" name="proImage"/>
            <input type="hidden" id="proImage2" name="proImage"/>
            <!-- <img alt="显示错误" src=" http://localhost:8080/Resources/images/promsg/20140416135121518.png"> -->
            <div id="proImg" name="proImg" type="hidden" align="center">
            	<img alt="" src="" />
            </div>
        </div>
        <div id="proImgshow" align="center" >      <!-- 修改HTML内容显示图片 --> 
        </div>
    </form>
</div>
<div id="dlg-buttons">
   <a href="javascript:void(0)" id="btn" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveProfession(this)">保存</a>
   <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#pro_dlg').dialog('close')">取消</a>
</div>

<div id="pro_dlgview" class="easyui-dialog" style="width:450px;height:450px;padding:10px 20px" closed="true">
    <div class="ftitle">职业信息预览</div>
    <form id="pro_fmview" novalidate>
        <div class="fitem">
            <label>行业名称:</label>
            <input name="proName" class="easyui-validatebox" disabled>
        </div>
        <div class="fitem">
            <label>行业描述:</label>
            <input name="proDescription" class="easyui-validatebox" disabled>
        </div>
        <div class="fitem">
            <label>创建时间:</label>
            <input name="proCtime" class="easyui-validatebox" disabled>
        </div>
        <div class="fitem">
            <label>修改时间:</label>
            <input name="proUtime" class="easyui-validatebox" disabled>
        </div>
        <div class="fitem">
            <label>是否推荐:</label>
            <input name="proRecommend" class="easyui-validatebox" disabled>
        </div>
<!--         <div class="fitem">
            <label>行业图片路径:</label>
            <input name="proImage" class="easyui-validatebox" disabled>
        </div> -->
        <div class="fitem">
            <label>行业图片:</label>
            <div id="proImg" name="proImg" type="hidden" align="center" >
            	<img alt="" src=""/>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
	var url;                                        //声明公用url  用于分辨调用哪个方法
	function addFunc(){								//新增
		$('#proImgshow').empty();							//加载form时用来清除图片
		$('#proImg').hide();
		$('#proID').combobox({disabled:false});     //启用下拉框
	    $('#pro_dlg').dialog('open').dialog('setTitle','添加职业信息');
	    $('#pfm').form('clear');
	    url = 'pro/addProfession';
	}
	
	function modFunc(row){                            /*  修改 */
		$('#proImgshow').empty();
		$('#proImg').empty();
		$("#proFile").val("");	   //变成 未选择图片
		//$('#proImg').hide();
	    //var row = $('#professionlist_data').datagrid('getSelected');
	    
	    /* if (row){ */
	    	//$("#proID").attr("disabled","disabled");
	    	//$("#proID").attr("disabled");
	    	$('#proID').combobox({disabled:true});    //设置下拉框禁用
	    	var imgurl = row.proImage; 					 /*  */
	    	if(imgurl!=null && imgurl.length<45){
		    	if(imgurl.charAt(0)=="/"){
		    		imageUrl='${applicationScope.ResourcesWebSite}'+imgurl.substring(1);
		    		$("#proImgshow").html("<img id='"+row.proImage+"' src='"+imageUrl+"' alt='图片加载错误，请检查目录下是否存在该图片' height='70' width='80'> &nbsp &nbsp");
		    		/* <a id='"+row.proImage+"tr' href='#' onclick=delimg('" +row.proImage+"')>delete</a> 需要删除图片时加上  */
		    	}
	    	}else{
	    		$("#proFile").val("");
	    	}				
	        $('#pro_dlg').dialog('open').dialog('setTitle','编辑职业信息');
	        /*$('#proImg img').attr('src',imageUrl);      绑定显示图片的地址 */
	        $('#pfm').form('load',row);
	       	url = 'pro/updateProfession';
	   /*  }else{
	    	$.messager.alert('提醒','请选择一条记录!');
	    } */
	}
	
	function saveProfession(obj){
		var proIDCheck = $('#proID').combobox('getValue');
		var proFile = $('#proImage').val();
		if(proIDCheck=='' || proIDCheck=='0' || proIDCheck==null){
			$.messager.alert('提醒','请选择需要操作的行业!');
			return;
		}
		if((proFile=='' || proFile==null) && url=='pro/addProfession'){
			$.messager.alert('提醒','请选择图片!');
			return;
		}
		$('#pfm').form('submit',{
	        url: url,
	        onSubmit: function(){
	            return $(this).form('validate');
	        },
	        success: function(result){
	        	$('#pro_dlg').dialog('close');        // close the dialog
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
		
	}
	
	function viewProfession(row){
		//var row = $('#professionlist_data').datagrid('getSelected');
		var imgurl = row.proImage;
		var imageUrl;
		if(imgurl!=null&&imgurl!=''){
	    	if(imgurl.charAt(0)=="/"){
	    		imageUrl='${applicationScope.ResourcesWebSite}'+imgurl.substring(1);
	    	}
    	}else{
    		imageUrl=null;
    	}
	    if (row){
	        $('#pro_dlgview').dialog('open').dialog('setTitle','职业信息');
	        $("#proImg img").attr({src:imageUrl,width:200,height:160});    //设置查看时大小
	        $('#pro_fmview').form('load',row);
	    } 
	}
	
	
	//显示数据
	var _page = '${page}';
	_page = jQuery.parseJSON(_page);
	var _h = $("#promanage").height();  
	//datagrid初始化  
    $('#professionlist_data').datagrid({
        //title:'职业管理列表',  
        iconCls:'icon-edit',//图标  
       // width: eoc.cms.main.tabsVolume.width,  
        //height: eoc.cms.main.tabsVolume.height,
 		width: eoc.cms.main.tabsVolume.width,  
        height: eoc.cms.main.tabsVolume.height-_h-50,  
        nowrap: false,  
        striped: true,  
        border: true,  
        collapsible:false,//是否可折叠的  
        url:'pro/findProfession',  
        remoteSort:false,   
        singleSelect:true,//是否单选  
        pagination:true,//分页控件  
        rownumbers:true,//行号  
        /* frozenColumns:[[  
            {field:'ck',checkbox:true}     //设置前面的小框
        ]], */
		columns:[[
	  		{field:'proName',title:'行业名称', width:200},
	  		{field:'proDescription',title:'行业描述', width:300},
	  		{field:'proUtime',title:'修改时间', width:185,
	  			 formatter:function (value,row,index){    //处理时间格式
		  			if(value){
		  				var time =new Date(value);
		  				row.proUtime=time.format("yyyy-MM-dd hh:mm:ss");             //把时间改成这种格式
			  			return time.format("yyyy-MM-dd hh:mm:ss");  
		  			}
	  			}
	  		},
	  		
	  		
	  		{field:'proCtime',title:'创建时间', width:200,
	  			 formatter:function (value,row,index){
		  			if(value){
		  				var time =new Date(value); 
		  				row.proCtime=time.format("yyyy-MM-dd hh:mm:ss");
			  			return time.format("yyyy-MM-dd hh:mm:ss");  
		  			}
	  			}
	  		},
	  		
	  		
	  		{field:'proRecommend',title:'是否推荐', width:160,formatter: function(value,row,index){
	  			if(row.proRecommend=='1'){			//是否推荐  1是  0否
	  				row.proRecommend='是';   		//把是绑定到这个字段上
	  				return '是';                             //到DataGrid上显示是
	  			}else if(row.proRecommend=='0'){
	  				row.proRecommend='否';
	  				return '否';
	  			}else{
	  				return vlaue;
	  			}
	  		}},
	  		{field:'opt',title:'操作',width:120,formatter : function(value,row,index){
	  			var _row = JSON.stringify(row);
	  			var s = "<a href='javascript:void(0);' onclick='viewProfession("+_row+")'>查看</a> ";  
	  			var e = "<a href='javascript:void(0);' onclick='modFunc("+_row+")'>编辑</a> ";
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
	var pageInfo = {
			pageSize: _page.pageSize,
			pageList: _page.pageList,
			params:"key=value"
	};				//&key1=value1&key2=value2
	eoc.cms.main.createPagination(pageInfo,$('#professionlist_data'));
	
	
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

	var proName=$("#proName").val();
	var proDescription=	$("#proDescription").val();
	var proRecommend=$("#proRecommend").combobox('getValue');
	var _queryParams = $('#professionlist_data').datagrid('options').queryParams;
	    $('#professionlist_data').datagrid({
	    	queryParams: {
	    		proName_gp: proName,
	    		proDescription_gp: proDescription,
	    		proRecommend_gp: proRecommend,
	    		
	    		//分页参数
	    		pageIndex : _queryParams.pageIndex,
	    		pageSize : _queryParams.pageSize 
	    	}
	    });
	    var pageInfo = {
	    		pageSize: _page.pageSize,
	    		pageList: _page.pageList,
	    		params:"key=value"
	    };						//&key1=value1&key2=value2
		eoc.cms.main.createPagination(pageInfo,$('#professionlist_data'));     //再次设置一下就不会出现查询后分页控制使用出错
		
	}	
	
	
	
</script>
<!-- 上传图片 -->
<script type="text/javascript" src="js/jquery_plugins/ajaxfileupload.js"></script>
<script type="text/javascript" src="js/profession/professionImg_upload.js"></script>
</body>
</html>