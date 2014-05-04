var eoc = eoc || {};
eoc.cms = eoc.cms || {};
eoc.cms.main = function() {
	// init the main page
	$('#cms_content').tabs({
		select : '0'
	});
};

/**
 * tabs's width and height
 */
eoc.cms.main.tabsVolume = {};

/**
 * 打开主界面的tab标签页
 */
eoc.cms.main.openMainTab = function(node) {
	var _cms_content = $('#cms_content');
	var _cms_left = $('#cms_left');
	
	eoc.cms.main.tabsVolume['width'] = _cms_content.width();
	eoc.cms.main.tabsVolume['height'] = _cms_left.height()-10;
	
	var flag = false;
	$(".tabs-inner").each(function(index, obj) {
		var text = $(obj).text();
		if (text == node.text) {
			$('#cms_content').tabs('select', node.text);
			flag = true;
			return;
		}
	})
	if (flag) {
		return;
	}
	//注册全局的ajax事件，来检查是否会话过期,过期就重新登录
	_cms_content.ajaxError(function(event, request, settings){
		if(request.responseText){
			if(request.responseText!=null && request.responseText.length>0){
				//绑定如果是跳转到login页面，来检查是否会话过期,过期就重新登录
				var _fm = $(request.responseText).find("input#isLogin");
				if(_fm!=null && _fm.length>0){
					$.messager.alert('确认','会话过期，请重新登录!','error',function(r){
						//window.location.href=window.location.href;
						window.location.reload();
					});
				}else{
					var d = $('<div id="errorExceptionDiv"></div>').appendTo('body');
					$('#errorExceptionDiv').dialog({
					    title: '异常',
					    width: 800,
					    height: 600,
					    closed: false,
					    cache: false,
					    content: request.responseText,
					    modal: true,
					    buttons:[{
							text:'确定',
							handler:function(){
								$('#errorExceptionDiv').dialog('close');
							}
						}]
					});
				}
			}
		}
    });
	
	$('#cms_content').tabs('add', {
		title : node.text,
		// fit : true,
		href : eoc.cms.util.context + node.url,
		closable : true
	});
	// resize the tabs layout.
	eoc.cms.main.resizeTabsLayout();
}

eoc.cms.main.resizeTabsLayout = function(){
	var _cms_content = $('#cms_content');
	var _cms_left = $('#cms_left');
	_cms_content.height(_cms_left.height()+21);/* +21 */
}

/**
 * 初始化分页信息
 */
eoc.cms.main.createPagination = function(pageInfo,tableJqObj){
	var _callBack = pageInfo.callBack || function(){}; 
	if(eoc.cms.main.isEmpty(pageInfo) | eoc.cms.main.isEmpty(tableJqObj)){
		return ;
	}
	var separateKeyValue = function(str){
		if(eoc.cms.main.isEmpty(pageInfo)){
			return ;
		}
		if(str.indexOf('=')==-1){
			return ;
		}
		return str.split('=');
	};
	try{
		var p = tableJqObj.datagrid('getPager');  
	    $(p).pagination({  
	        pageSize : eoc.cms.main.isEmpty(pageInfo.pageSize) == true ? 20 : pageInfo.pageSize,// 每页显示的记录条数，默认为10 
																								// 10
	        pageList : eoc.cms.main.isEmpty(pageInfo.pageList) == true ? [5,10,15,20,50] : pageInfo.pageList,// 可以设置每页记录条数的列表 
																												// [5,10,15]
	        beforePageText : '第',// 页数文本框前显示的汉字 
	        afterPageText : '页   共 {pages} 页',  
	        displayMsg : '当前显示 {from} - {to} 条记录  共 {total} 条记录',
			onSelectPage : function(pPageIndex, pPageSize){
		        var queryParams = tableJqObj.datagrid('options').queryParams;  
		        queryParams.pageIndex = pPageIndex;  
		        queryParams.pageSize = pPageSize;
		        if(!eoc.cms.main.isEmpty(pageInfo.params)){
		        	var _params = pageInfo.params;
		        	if(_params.indexOf('&')!=-1){
		        		var _paramsArr = _params.split('&');
		        		for(var i = 0; i < _paramsArr.length; i ++){
		        			var _kv = separateKeyValue(_paramsArr[i]);
		        			queryParams[_kv[0]] = _kv[1];
			        	}
		        	}else{
		        		var _kv = separateKeyValue(_params);
		        		queryParams[_kv[0]] = _kv[1];
		        	}
		        }
		        jQuery.isFunction(_callBack) && _callBack();
		        // 重新加载datagrid的数据
		        tableJqObj.datagrid('reload');
			}  
	    });
	}catch(err){
		throw err;
	}
}

/**
 * 判断是否为空值
 */
eoc.cms.main.isEmpty = function(val){
	return val == null || val == "" || val == undefined;
}

/**
 * 判断是否为数字
 */
function isNum(num) {
	  if (isNull(num)) {
		return false;
	   }
	   var reg=/^[0-9]*$/;
	  if (reg.test(num)) {
		   return true;
	   }
	  return false;
}

/**
 * 判断是否为空值
 */
function isNull(exp){
	if (exp==null|| typeof(exp)!="undefined" && exp=="")
	{
	    return true;;
	}
	return false;
}

/**
 * 判断是否为小数
 */
function isFloat(float) {
	  if (isNull(float)) {
		return false;
	   }
	   var reg=/^[0-9]*\.?[0-9]*$/;
	  if (reg.test(float)) {
		    return true;
	   }
	  return false;
}

eoc.cms.main.test = function(node) {
	// alert('main_test');
};
