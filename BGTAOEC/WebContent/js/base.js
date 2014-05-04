var Check={
		checkEmail:function(email){
			var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
			return reg.test(email);
		}
}
function isNull(exp) {
	if (exp==null|| typeof(exp)!="undefined" && exp=="")
	{
	    return true;;
	}
	return false;
}
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
//根据数字获取大写
function Uppercase(num) {
	var result;
	switch (parseInt(num)) {
	case 1:
		result="一";
		break;
	case 2:
		result="二";
		break;
	case 3:
		result="三";
		break;
	case 4:
		result="四";
		break;
	case 5:
		result="五";
		break;
	case 6:
		result="六";
		break;
	case 7:
		result="七";
		break;
	case 8:
		result="八";
		break;
	case 9:
		result="九";
		break;
	case 10:
		result="十";
		break;
	case 11:
		result="十一";
		break;
	case 12:
		result="十二";
		break;
	case 13:
		result="十三";
		break;
	case 14:
		result="十四";
		break;
	case 15:
		result="十五";
		break;
	default:
		break;
	}
	return result;
}
var eoc={
	
	alert:function(title,msg,callback){
		this.win(title,msg,callback,1);
	},
	success:function(title,msg,callback){
		this.win(title,msg,callback,2);
	},
	error:function(title,msg,callback){
		this.win(title,msg,callback,3);
	},
	confirm:function(title,msg,callback){
		this.win(title,msg,callback,4);
	},
	win:function(title,msg,callback,type){
		$('.msgWindow').remove();
		var $button = $("<button>").addClass('btn btn-primary btn-small').html('确定').click(function(){
			$(this).parents('.popWindow').remove();
			if (callback) {
				callback();
			}
			return true;
		});
		var img;
		switch (type) {
		case 1:
			img = base + '/images/base/information-white.png';
			break;
		case 2:
			img = base +'/images/base/tick.png';
			break;
		case 3:
			img = base +'/images/base/cross.png';
			break;
		default:
			img = base +'/images/base/information-white.png';
			break;
		}
		var $divB = $('<div>').addClass('button');
		var $img = $('<img>').attr('src',base+img).attr('width','16px').attr('height','16px')
		var $pimg = $('<div>').addClass('con');
		var $pointOut =  $('<div>').addClass('popContent pointOut');
		var $title =  $('<div>').addClass('title').html("<span class='close'></span>"+title);
				
		var W = screen.width;//取得屏幕分辨率宽度 
		var H = screen.height;//取得屏幕分辨率高度 
		var yScroll;//取滚动条高度 
		if (self.pageYOffset) { 
		yScroll = self.pageYOffset; 
		} else if (document.documentElement && document.documentElement.scrollTop){ 
		yScroll = document.documentElement.scrollTop; 
		} else if (document.body) {
		yScroll = document.body.scrollTop; 
		} 
		var $popWin =  $('<div>').addClass('popWindow').css({'margin-left':"-100px",left:"50%",top:H/2+yScroll-250+"px"}).css({'z-index':891208});
		$popWin.addClass('msgWindow');

		$img.appendTo($pimg);
		$('<span>').html(msg).appendTo($pimg);
		$pimg.appendTo($pointOut);	
		$title.appendTo($popWin);
     	//如果是确认框，添加 确定 取消按钮
		if (type==4) {
			 var $confirmButton = $("<button>").addClass('btn btn-primary btn-small').html("确定").click(function(){
				 $(this).parents('.popWindow').remove();
					if (callback) {
						callback(true);
					}
			 });
			 var $cacelButton = $("<button>").addClass('btn btn-default btn-small').css({'margin-left':"15px"}).html("取消").click(function(){
				 $(this).parents('.popWindow').remove();
					if (callback) {
						callback(false);
					}
			 });
			 var $buttonDiv = $("<div>").addClass("button");
			 $confirmButton.appendTo($buttonDiv);
			 $cacelButton.appendTo($buttonDiv);
			 $buttonDiv.appendTo($pointOut);
	 	}else {
	 		$button.appendTo($divB);
	 		$divB.appendTo($pointOut);
		}

		
		$pointOut.appendTo($popWin);
		$popWin.find('.close').click(function(){
			$(this).parents('.popWindow').remove();
			if (callback) {
				callback;
			}
		});
		$popWin.appendTo($("body"));
		$popWin.show();
	}
	
}