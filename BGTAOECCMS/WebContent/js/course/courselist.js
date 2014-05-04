var secType = '2';
var params = {
	bgcolor : '#FFF',
	allowFullScreen : true,
	allowScriptAccess : 'always',
	wmode:'transparent'
};// 这里定义播放器的其它参数如背景色（跟flashvars中的b不同），是否支持全屏，是否支持交互
var attributes = {
	
};
var flashvars = {
		s:'0',
		c:'0',
	    e:'0',
	    x:'',
	    v:'80',
    	p:'1',
	    m:'1',
	    wh:'',
	    ct:'2',
	    my_url : encodeURIComponent(window.location.href)
};
function playCkplayer() {
	// flashvars.f='rtmp://vod.gtadata.com/vod/20140320171441368.flv';
	flashvars.f = vodServer+ $('#video').attr('data-src');
	//flashvars.i = img;
	
	swfobject.embedSWF(ctx+'/player/ckplayer.swf', 'video',$('#video').width(), $('#video').height(), '10.0.0',ctx+'/player/expressInstall.swf', flashvars, params, attributes);
}
function playMediaPlayer() {
	flashvars = 
	{
	    sourceUrl:vodServer + $('#video').attr('data-src'),// 播放地址
	    previewImageUrl:img,
	    autoPlay:"false"
	};
  	params.quality = "high";
  	params.bgcolor = "#ffffff";
  	params.allowscriptaccess = "sameDomain";
 	params.allowfullscreen = "true";
	swfobject.embedSWF(ctx+'MediaPlayer/GPlayer.swf', 'video',$('#video').width(), $('#video').height(), '10.0.0',ctx+'MediaPlayer/playerProductInstall.swf', flashvars, params, attributes,null);
}


//
function getUri() {
	var url = window.location.href;
	var arr = url.split("//");
	url = arr[1].substring(arr[1].indexOf("/"));
	return url;
}