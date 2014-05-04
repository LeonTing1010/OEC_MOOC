var remandPageIndex=1;  //记录点播页面当前页
var livePageIndex=1;   //记录点播页面当前页
var examType=1; //记录考试类型
var paperState;//记录批阅状态
var updatePageIndex=0;
var liveType=2;  //课程类型，默认点播
$(function($){
	switchLoad(parseInt(tab));	
})
function checkCurrent(id){
    $('.leftCon a').removeClass('current');
	$("#l"+id).addClass('current');
}
function switchLoad(id) {
	$(".leftCon a").removeClass('current');
	oec.login.isLogin(function() {
		switch (parseInt(id)) {
		case 1:
			checkCurrent(id);
			loadDemandbroad();
			break;
		case 2:
			checkCurrent(id);
			loadLivebroad();
			break;
		case 3:
			checkCurrent(id);
			loadUpload(1,null);
			break;
		case 4:
			checkCurrent(id);
			loadOperation(3);
			break;
		case 5:
			checkCurrent(id);
			loadExam(1);
			break;
		case 6:
			checkCurrent(id);
			loadPractice();
			break;
		case 7:
			checkCurrent(id);
			loadTeacherInfor();
			break;	
		case 8:
			checkCurrent(id);
			unanswered();
			break;
		case 9:
			checkCurrent(id);
			answered();
			break;
		case 10:
			break;
		case 11:
			checkCurrent(id);
			releasetest();
			break;
		case 12:
			checkCurrent(id);
			loadIndex();
			break;
		default:
			break;
		}
	});

}
//上传课件
function loadUpload(step,courseId) {
	$('.rightCon').load(base+"/teacherCenter/upload/step"+step+"/?courseId="+courseId+"&code="+Math.random());
}
//发布试题
function releasetest() {
	$('.rightCon').load(base+"/teacherCenter/releasetest/?"+"&code="+Math.random());
}
//直播
function loadLivebroad(){
	$('.rightCon').load(base+"/teacherCenter/tolivebroad/?"+"&code="+Math.random());
}
//点播
function loadDemandbroad(){
	$('.rightCon').load(base+"/teacherCenter/todemandbroad/?"+"&code="+Math.random());
}

function loadOperation(Type){
	examType=Type;
	$('.rightCon').load(base+"/teacherCenter/toManagement/?examType="+Type+"&code="+Math.random());  //作业管理
}
function loadExam(Type){
	examType=Type;
	$('.rightCon').load(base+"/teacherCenter/toManagement/?examType="+Type+"&code="+Math.random()); //考试管理
}

function loadPractice(){
	$('.rightCon').load(base+"/teacherCenter/practiceManagement/?"+"&code="+Math.random()); //练习管理
}
function unanswered(){
	$('.rightCon').load(base+"/teacherCenter/unanswered/?"+"&code="+Math.random());//未解答

}
function answered(){
	$('.rightCon').load(base+"/teacherCenter/answered/?"+"&code="+Math.random());//已解答
}

function answer(quesId){
	checkCurrent(8);
	$('.rightCon').load(base+"/teacherCenter/answer/?quesId="+quesId+"&code="+Math.random());//解答
//	$.ajax({
//		url : base+"/teacherCenter/answer/?code="+Math.random(),
//		data : {
//			"quesId" : quesId
//		},
//		success : function(data) {
//			$(".rightCon").html(data);
//		}
//	});
}

function answerAdd(quesAddId){
	checkCurrent(8);
	$('.rightCon').load(base+"/teacherCenter/answerAdd/?quesAddId="+quesAddId+"&code="+Math.random());//追问解答
}


function loadIndex(){
	$('.rightCon').load(base+"/teacherCenter/teacenterIndex/?"+"&code="+Math.random())  //教师个人中心首页
}
function correct(examId,Type){
	examType=Type;
	if(Type=="1"){
		checkCurrent(5);
	}else if(Type=="3"){
		checkCurrent(4);
	}else{}
	$('.rightCon').load(base+"/teacherCenter/correctIndex/?examId="+examId+"&code="+Math.random());  //教师中心 批阅作业/考试
}

function correctResult(examId,Type){
	if(Type=="1"){
		checkCurrent(5);
	}else if(Type=="3"){
		checkCurrent(4);
	}else{}
	$('.rightCon').load(base+"/teacherCenter/correctResultIndex/?examId="+examId+"&code="+Math.random());  //教师中心  查看已批阅作业、考试
}

//发布点播课程
function releaseRemandCourse(courseId,status)
{
	$.ajax({
		url:base+"/teacherCenter/upload/step4/updateCourseStatus/",
		type:"post",
		data:{courseId:courseId,status:status},
	    error:function(){
	    	
	    },
	    success:function(data){
	    	if (data.result) {
	    		//$('.rightCon').load(base+"/teacherCenter/todemandbroad/?pageNo="+remandPageIndex);
	    		$('#'+courseId+'td1').html("审核中");
	    		$('#'+courseId+'td5').html("");
			}
	    }
	});
}
//发布直播课程
function releaseLiveCourse(courseId,status)
{
	$.ajax({
		url:base+"/teacherCenter/upload/step4/updateCourseStatus/",
		type:"post",
		data:{courseId:courseId,status:status},
	    error:function(){
	    	
	    },
	    success:function(data){
	    	if (data.result) {
	    		//$('.rightCon').load(base+"/teacherCenter/tolivebroad/?pageNo="+livePageIndex);
	    		$('#'+courseId+'td1').html("审核中");
	    		$('#'+courseId+'td6').html("");
			}
	    }
	});
}
//修改课程
function updateCourse(courseId){
	updatePageIndex=0;
	$('.rightCon').load(base+"/teacherCenter/update/?courseId="+courseId);
}
//修改课程--Tab跳转
function updateCourseIndex(courseId,index){
	updatePageIndex=index;
	$('.rightCon').load(base+"/teacherCenter/update/?courseId="+courseId);
}

/**********************************
 * 加载修改教师的信息
 * Add By:缪佳
 * Add Date：2014-001-22
 *********************************/
function loadTeacherInfor()
{
	$('.rightCon').load(base+"/teachercenter/teacherinforedit/?code="+Math.random());

}

//发布试题--Tab跳转  Type= 1为考试、2为练习、3为作业
function releaseTestIndex(type)
{
	checkCurrent(11);
	$('.rightCon').load(base+"/teacherCenter/releasetest/?type="+type+"&code="+Math.random());
}
