var uploadResult=1;

function SelectItemByValue(objSelect,objItemText) {  
	if (isNull(objSelect)) {
		return ;
	}
    for(var i=0;i<objSelect.options.length;i++) {  
        if(objSelect.options[i].value == objItemText) {  
            objSelect.options[i].selected = true;  
            break;  
        }  
    }  
}
$(function($){
	if (!isNull(tab)) {
		switchLoad(parseInt(tab));	
	}	
})
function checkCurrent(id){
    $('.leftCon a').removeClass('current');
	$("#c"+id).addClass('current');
}
function switchLoad(id){	
	$(".leftCon a").removeClass('current');
	oec.login.isLogin(function() {
		switch (parseInt(id)) {
		case 1:
			checkCurrent(id)
			document.getElementById("location").innerHTML = "我的作业";
			mytask(); //我的作业
			break;
		case 2:
			document.getElementById("location").innerHTML = "我的笔记";
			checkCurrent(id)
			mynote(); 
			break;
		case 3:
			document.getElementById("location").innerHTML = "我的练习";
			checkCurrent(id)
			mypractice();
			break;
		case 4:
			document.getElementById("location").innerHTML = "职位收藏";
			checkCurrent(id)
			jobcollect();
			break;
		case 5:
			document.getElementById("location").innerHTML = "课程收藏";
			checkCurrent(id)
			coursecollect();
			break;
		case 6:
			document.getElementById("location").innerHTML = "我的考试";
			checkCurrent(id)
			myexam(); 
			break;
		case 7:
			document.getElementById("location").innerHTML = "课程提问";
			checkCurrent(id)
			mycoursequestion(1); 
			break;
		case 8:
			document.getElementById("location").innerHTML = "我的笔记";
			checkCurrent(id)
			mycoursequestion(2); 
			break;
		case 9:
			document.getElementById("location").innerHTML = "个人资料";
			checkCurrent(id)
			personinfo();
			break;
		case 10:
		    document.getElementById("location").innerHTML = "答疑提问";
		    checkCurrent(id)
			myanswerquestion(1);   //所有答题提问
		    $("#tagFlag").val(1);
			break;
		case 11:
			document.getElementById("location").innerHTML = "课程提问";
			checkCurrent(7)
			mycoursequestion(2);   //所有课程提问
			break;
		case 12:
			document.getElementById("location").innerHTML = "答疑提问";
			checkCurrent(10)
			myanswerquestion(2);  //最新动态
			$("#tagFlag").val(2);
			break;
		case 13:
			document.getElementById("location").innerHTML = "我的关注";
			checkCurrent(id)
			myattention(1);  //关注的问题
			break;
		case 14:
			document.getElementById("location").innerHTML = "我的关注";
			checkCurrent(13);
			myattention(2);  
			break;
		defalut:
			break;
		}
	});	
}

function mytask(){
	$('.main').load(base+"/studentCenter/mytask/?"+"&code="+Math.random());
}

function mynote(){
	$('.main').load(base+"/studentCenter/mynote/?"+"&code="+Math.random());
}

function mypractice(){
	$('.main').load(base+"/studentCenter/mypractice/?"+"&code="+Math.random());
}

function jobcollect(){
	$('.main').load(base+"/studentCenter/jobcollect/?"+"&code="+Math.random());
}

function coursecollect(){
	$('.main').load(base+"/studentCenter/coursecollect/?"+"&code="+Math.random());
}

function myexam(){
	$('.main').load(base+"/studentCenter/myexam/?code="+Math.random());
}

function mycoursequestion(type){
	if(type=="2"){
	   hasRead(1);  //更新为已读
	}
	$('.main').load(base+"/studentCenter/mycoursequestion/?type="+type+"&code="+Math.random());
}

function newReceiveAnswer(){
	$('.main').load(base+"/studentCenter/newReceiveAnswer/?code="+Math.random());
}

function myanswerquestion(type){
	
	if(type=="2"){
	   hasRead(0);
	}
	$('.main').load(base+"/studentCenter/myanswerquestion/?type="+type+"&code="+Math.random());
}

function myattention(type){
	$('.main').load(base+"/studentCenter/myattention/?type="+type+"&code="+Math.random());
}


function personinfo(){
	$('.main').load(base+"/studentCenter/myinfo/update/?"+"&code="+Math.random());
}

/**更新个人资料**/
function updateinfo(){
	if(formverify()){
		var params = $('#studentInfor').serialize();
		$.ajax({
			url : base+"/student/myinfo/save/",
			type : 'post',
			dataType : 'json',
			data : params,
			success : updatePage
		});
	} else{
		document.getElementById("combutton").disabled=true;
	}
	document.getElementById("combutton").disabled=false;
}

//验证对象是否符合要求
function verify(obj) {
	var flag = true;
	var types = obj.attr("tag"); // 获取验证对象需要进行何种验证处理
	// var objValue = $.trim(obj.attr("value")); //获取验证对象的值
	var objValue = $.trim(obj.context.value); // 获取验证对象的值
	var typestr = new Array(); // 定义一个数组，用来存放用户验证处理需求点
	typestr = types.split(",");
	for (var i = 0; i < typestr.length; i++) {
		var type = typestr[i].toString();
		if (tagcheck(type, objValue)) {// 满足验证要求
		} else {
			flag = false;
			break;
		}
	}
	$(obj).after("<span class='Okspan'><span class='validation'><i class='ok-icon'></i><span class='ok'>正确</span></span></span>");
	return flag;
}

/**更新成功后提示信息**/
function updatePage(json){
	if(json){
		//eoc.success('温馨提示','个人资料修改成功！',function(){});
		$("#combtton").after("");
		$("#combutton").after("<span style='color:#8dce1a;margin-left:15px'>个人资料修改成功！</span>")
		setTimeout(function() {personinfo();},1000);
	}
	else{
		//eoc.error('错误提示','个人资料修改失败！',function(){});
		$("#combtton").after("");
		$("#combutton").after("<span style='color:red;margin-left:15px'>个人资料修改失败！</span>")
	}
}
/**校验图片**/
function checkImg(imageUrl) {
	if (isNull(imageUrl)) {
    	eoc.alert('提示','图片为空');
    	return false;
	}  
    var str=imageUrl.split(".");
    var format=str[str.length-1].toLowerCase();
    if (format.indexOf('jpg')!=-1　|| format.indexOf('jpeg')!=-1 || format.indexOf('bmp')!=-1 || format.indexOf('png')!=-1) {
    	return true;
	}else{
		alert()
		eoc.alert('提示','图片格式不正确');
    	return false;
	}	
}

/**上传头像**/
function uploadImage(){
	$('#resultInfo').html("");
	
	var imageFile =  $('input[name=imagefile]').val();
	if(checkImg(imageFile)){
		$('#imgForm').submit();
	}
}

/**上传头像返回成功**/
function uOk(result,userHeadPic){
	if(result==1){
		$('#img1').attr('src',cache+userHeadPic);
		$('#preImage').attr('value',userHeadPic);
	}
	uploadResult=result;
}
/**保存用户头像信息**/
function imgSub(){
	var imageUrl=$('#preImage').val();
	if (!checkImg(imageUrl)) {
		 return ;	
	}
	if(uploadResult==1){
		$.ajax({
			url:base+"/studentCenter/updateImg/",
			data:{imageUrl:imageUrl,uploadResult:uploadResult},
			type:'post',
			success:function(data){
				if(data.result){
					$('#resultInfo').html("<i class='ok-icon'></i><span class='ok'>"+data.messageString+"</span>");
					$('.student-headImg').children("img").attr("src",cache+data.userHeadPic);
					$('img[name="topMenuUserPic"]').attr('src',cache+data.userHeadPic);
					
					setTimeout(function(){$('#resultInfo').html('');},1000);
				}else{
					eoc.error('温馨提示',data.messageString);
				}
			}
		});
	}else{
		eoc.error('温馨提示','图片上传失败，请重新上传!');
	}
}


function getStudentImage(){
	$.ajax({
		url : base+"/studentCenter/getStuImg/?"+"&code="+Math.random(),
		type : 'post',
		dataType : 'json',
		data : null,
		success : function(data){
			if(data.flag=='right'){
				$('#imgForm span').eq(0).find('img').attr('src',cache+data.userHeadPic);
				$('#preImage').attr('value',data.userHeadPic);
			}
		}
	});
}

function delCourseInfo(courseId){
	$.ajax({
		url:base+"/studentCenter/courseDelete/",
		type:"post",
		data:{"courseId":courseId},
	    error:function(){
	    	
	    },
	    success:function(data){
	    	if (data.result=="right") {
	    		$("#"+data.cour+"").hide();
	    		coursecollect();
			}
	    }
	});
}

function showExam(examId,examStuId,status){
	//alert(examId+" "+examStuId);
	document.getElementById("location").innerHTML="我的考试&nbsp;&gt;&nbsp;课程考试";
	$('.main').load(base+"/stucenter/showMyExam/?examId="+examId+"&examStuId="+examStuId+"&status="+status+"&code="+Math.random());
}

/**-答案标记为已读 by zhangjin-**/
function hasRead(quesType){
//	$.ajax({
//		url : base+"/studentCenter/hasReadAnswer.ajax",
//		type : 'post',
//		data:{"quesType":quesType},
//		success:function(data){
//			//直接清0
			if(quesType=="0"){
				document.getElementById("quesAnswerCount").innerHTML = "0";
				$("#clearspan").html("");
				
			}else if(quesType=="1"){
				document.getElementById("courAnswerCount").innerHTML = "0";
				$("#clearspan").html("");
			}
//			
//	    }
//	});
}

/**进入课程表**/
function toMyCourse(obj){
	var url = base+"/course/myCourse/";
	oec.login.isLogin(function(){
		//$(obj).attr("href",url);
		window.open(url);
	});
}