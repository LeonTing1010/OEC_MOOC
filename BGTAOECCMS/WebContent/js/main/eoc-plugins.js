var eoc = eoc || {};
eoc.cms = eoc.cms || {};
eoc.cms.plugins = function(){
	
};

/** 
 * 时间对象的格式化 
 * format="yyyy-MM-dd hh:mm:ss"; 
 */
Date.prototype.format = function(format) {
	/* 
	 * format="yyyy-MM-dd hh:mm:ss"; 
	 */
	var o = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S" : this.getMilliseconds()
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

//在日期选择器中添加清除按钮.add by:dongs.(日期时间选择器如需添加清除按钮,请模仿次模块重新添加)
var dateboxbuttons = $.extend([], $.fn.datebox.defaults.buttons);
dateboxbuttons.splice(1, 0, {
	text : '清除',
	handler : function(target){
		$(target).datebox('clear');
	},
});

/** 注册全局的加载onComplete事件，来检查是否会话过期,过期就重新登录 */
$.extend($.parser, {
	onComplete: function(context){
		if(context!=null && context.length>0){
			//绑定如果是跳转到login页面，来检查是否会话过期,过期就重新登录
			var _fm = $(context[0]).find("input#isLogin");
			if(_fm!=null && _fm.length>0){
				$.messager.alert('确认','会话过期，请重新登录!','error',function(r){
					//window.location.href=window.location.href;
					window.location.reload();
				});
			}
		}
		return context;
	}
});