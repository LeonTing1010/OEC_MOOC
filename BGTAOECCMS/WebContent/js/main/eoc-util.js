var eoc = eoc || {};
eoc.cms = eoc.cms || {};
eoc.cms.util = function() {
	// init the util
};
eoc.cms.util.context = null;
eoc.cms.util.fullPath = null;
eoc.cms.util.hostPath = null;

eoc.cms.util._parseContext = function() {
	var _hn = location.hostname;// ret web hostname
	var _pn = location.pathname;// ret the pathname
	var _p = location.port;// ret web port （80 or 443）
	var _pl = location.protocol;// ret the web protocol（http:// or https://）
	eoc.cms.util.context = _pn;
	eoc.cms.util.fullPath = _pl + _hn + ":" + _p + _pn;
	eoc.cms.util.hostPath = _pl + _hn + ":" + _p;
};

/**
 * dynamic load the javascript file
 * 
 * @param url[js
 *            url]; callback[run the callback]
 */
eoc.cms.util.loadJs = function(url, callback) {
	var done = false;
	var script = document.createElement('script');
	script.type = 'text/javascript';
	script.language = 'javascript';
	script.src = url;
	script.onload = script.onreadystatechange = function() {
		if (!done
				&& (!script.readyState || script.readyState == 'loaded' || script.readyState == 'complete')) {
			done = true;
			script.onload = script.onreadystatechange = null;
			if (callback) {
				callback.call(script);
			}
		}
	}
	document.getElementsByTagName("head")[0].appendChild(script);
};

eoc.cms.util.test = function() {
	// alert('util_test');
};
eoc.cms.util._parseContext();

// 初始化console,增加了IE11以下不支持console的问题
var console = console || {
	log : function(msg) {
		// alert(msg);
	},
	debug : function(msg) {
		// alert(msg);
	},
	info : function(msg) {
		// alert(msg);
	},
	warn : function(msg) {
		// alert(msg);
	}

};

// 根据数字获取大写.
eoc.cms.util.uppercase = function(num) {
	if (!isNaN(num)) {
		num = parseInt(num);
		// 取余
		remainder = parseInt(num % 10);
		// 取整
		num = parseInt(num / 10);
		var upperNum;
		switch (remainder) {
		case 0:
			upperNum = "〇";
			break;
		case 1:
			upperNum = "一";
			break;
		case 2:
			upperNum = "二";
			break;
		case 3:
			upperNum = "三";
			break;
		case 4:
			upperNum = "四";
			break;
		case 5:
			upperNum = "五";
			break;
		case 6:
			upperNum = "六";
			break;
		case 7:
			upperNum = "七";
			break;
		case 8:
			upperNum = "八";
			break;
		case 9:
			upperNum = "九";
			break;
		default:
			break;
		}
		if (num >= 1) {
			return eoc.cms.util.uppercase(num).concat(upperNum);
		} else {
			return upperNum;
		}
	}
};
