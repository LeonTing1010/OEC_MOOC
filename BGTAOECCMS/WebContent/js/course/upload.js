var intV;
function startUpload() {
	intV = window.setInterval("getFileUploadProgress()",500);
	showUploadWin();
}
function startUploadLocal() {
	intV = window.setInterval("fileUploadLocalProgress()",500);
	showUploadWin();
}
function endUpload() {
	window.clearInterval(intV);
	$('.form-button-bar').show();
	$('#progress').html("上传成功！");
	setTimeout(function(){
		$('#uWin').hide();
	},1000);
}

function fileUploadLocalProgress() {
	$.ajax({
		url:ctx+"/file/fileUploadLocalProgress",
		type:"get",
		success:function(data){
			if (data.result) {				
				 endUpload();
			}else{
				$('#progress').html(data.progress);
			}
		}
	});
	
}
function getFileUploadProgress() {
	$.ajax({
		url:ctx+"/file/fileUploadProgress",
		type:"get",
		success:function(data){
			if (data.result) {				
				 endUpload();
			}else{
				$('#progress').html(data.progress);
			}
		}
	});
	
}
function showUploadWin(){
	$('#progress').html("0%");
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
	$('#uWin').css({'margin-left':"-100px",left:"50%",top:H/2+yScroll-150+"px"}).css({'z-index':1000});
	$('#uWin').show();
}
$(function(){
	window.clearInterval(intV);
});