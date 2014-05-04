 var eoc = eoc || {};
    eoc.util = function(){};
    /**
     * 判断是否是IE版本低于11
     * */
    eoc.util.isNOTIELT11 = function(){
    	var userAgent = navigator.userAgent.toLowerCase(); 
    	//var isIELT10 = /msie/.test(userAgent) && !/opera/.test(userAgent);
    	var isNOTIELT11 = ((userAgent.indexOf("webkit")!=-1) | 
    			(userAgent.indexOf("firefox")!=-1) | 
    			(userAgent.indexOf("chrome")!=-1) | 
    			(userAgent.indexOf("msie")==-1));
    	return isNOTIELT11;
    };
    /**
     * 判断input是否支持传入的属性.
     * */
    eoc.util.supportAttrInInput = (function(input){
    	return function(attr){ return attr in input };
    })(document.createElement('input'));
    
    eoc.util.supportAttributeInInput = function(attr){
    	var _input = document.createElement('input');
    	return attr in _input;
    };
    /**
     * 重新调整div的位置，其他地方要可以覆盖
     * 在方法的作用域里面可以获取到div通过div#layerPhaceholderGta
     * 
     * */
    eoc.util.resizePlaceholder = function(){
    	var $layerPhaceholderGta = $("div#layerPhaceholderGta");
    	var style = $layerPhaceholderGta.attr("style");
    	style += ";PADDING-TOP:10px;";
    	$layerPhaceholderGta.attr("style",style);
    };
    /*
     * 应用ie11下支持input框的placeholder属性;
     * */
    eoc.util.applyPlaceholder = function(){
    	//alert(eoc.util.supportAttributeInInput('placeholder'));
       if ( !(eoc.util.supportAttributeInInput('placeholder') && eoc.util.isNOTIELT11) ) {               
            $('input[placeholder],textarea[placeholder]').placeholder({ 
                 useNative: false,  
                 hideOnFocus: false,  
                 style: {  
                      textShadow: 'none'  
                 }  
            });
       }
       if ( !eoc.util.supportAttributeInInput('autofocus') ) { 
            $('input[autofocus]').focus() ;
       }
    };