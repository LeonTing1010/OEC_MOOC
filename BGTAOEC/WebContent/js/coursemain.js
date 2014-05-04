$(function($) {
	if (!isNull(tab)){
		if(tab==8){
			checkExamIsSubmit('',examId);
		}
		else if(tab==9){
			showExamResult(examId,examType);
		}else{
			switchLoad(tab);
		}
		
	}else {		
		 loadCourseInfo();
	}
})

function checkCurrent(id){
	$('.curDetailSide a').removeClass('DetailSide_current');
	$('#c'+id).addClass('DetailSide_current');
}
function switchLoad(id){
	oec.login.isLogin(function() {
		 switch (parseInt(id)) {
		 case 1:
			 checkCurrent(id)
			 loadCourseInfo();
		     break;
		 case 2:
			 checkCurrent(id)
			 loadCourseOutline();
			 break;
		 case 3:
			 checkCurrent(id)
			 loadCourseCriterion();
			 break;
		 case 5:
			 checkCurrent(id)
			 loadCourseNotes();
			 break;
		 case 6:  
			 checkCurrent(id)
			 loadcourseExamList(); //课堂作业列表
			 break;
		 case 7:  
			 checkCurrent(id)
			 loadcoursePracticeList(); //课堂练习列表
			 break;
		 case 8:  
			 checkCurrent(id)
			 checkExamIsSubmit($('input[name=courseId]').val(),''); //课堂考试
			 break;
		 case 9:  
			 showExamResult(); //查看答案
			 break;
		 default:
		 	break;
		 }
	});

}
//加载课程信息
function loadCourseInfo(){
	checkCurrent('1');
	var courseId = $('input[name=courseId]').val();
	$('#courseMain').load(base+"/course/courseInfo/?courseId="+courseId+"&code="+Math.random());
}
//加载课程大纲
function loadCourseOutline(){
	var courseId = $('input[name=courseId]').val();
	$('#courseMain').load(base+"/course/courseOutline/?courseId="+courseId+"&code="+Math.random());
}
//加载课程评分方式
function loadCourseCriterion()
{
	var courseId = $('input[name=courseId]').val();
	$('#courseMain').load(base+"/course/courseCriterion/?courseId="+courseId+"&code="+Math.random());
}
//加载课程笔记
function loadCourseNotes()
{
	var courseId = $('input[name=courseId]').val();
	$('#courseMain').load(base+"/course/courseNotes/?courseId="+courseId+"&code="+Math.random());
}

//课后作业列表
function loadcourseExamList()
{	
	var courseId = $('input[name=courseId]').val();
	$('#courseMain').load(base+"/course/courseExamList/?courseId="+courseId+"&code="+Math.random());
}

//课后练习列表
function loadcoursePracticeList(){
	
	var courseId = $('input[name=courseId]').val();
	$('#courseMain').load(base+"/course/coursePracticeList/?courseId="+courseId+"&code="+Math.random());
}



/*******************--检查考试是否提交 --****************************/
function checkExamIsSubmit(courseId,examId){
	$.ajax({
		url:base+"/course/checkExamIsSubmit/?courseId="+courseId+"&examId="+examId,
		type:"post",
	    error:function(){
	    },
	    success:function(data){
	    	if(data.result=="1"){
	    		showExamResult(data.examId,examType);
	    	}else{
	    		courseExamPage(data.examId,examType);
	    	}
	    	
	      }
	   })
}
//进入 作业/练习/试卷 题目
function courseExamPage(examId,type)
{
	if(type==1){
		checkCurrent('8');
	}else if(type==2){
		checkCurrent('7');
	}else if(type==3){
		checkCurrent('6');
	}else{}
	$('#courseMain').load(base+"/course/courseExamPage/?examId="+examId+"&code="+Math.random());
}
//查看 作业/练习/试卷 答案
function showExamResult(examId,type){
	if(type==1){
		checkCurrent('8');
	}else if(type==2){
		checkCurrent('7');
	}else if(type==3){
		checkCurrent('6');
	}else{}
	$('#courseMain').load(base+"/course/showExamResult/?examId="+examId+"&code="+Math.random()); 
}