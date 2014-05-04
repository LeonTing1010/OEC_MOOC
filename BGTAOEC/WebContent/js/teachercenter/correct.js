var pageNo=1;
var examId=$('input[name=examId]').val();  //考试Id
var examStuId=$('input[name=examStuId]').val(); //学生考试Id
var flag=true;   //校验数据是否正确
var isLast=false; //是否是最后一位未批阅学生
var unCorrCount=0;

function nextOne(correctStatus){
	pageNo=pageNo+1;
	$.ajax({
		url:base+"/teacherCenter/correctIndex/getExamStu/",
		data:{examId:examId,pageNo:pageNo,correctStatus:correctStatus,code:Math.random()},
	    error:function(){
	    	
	    },
	    success:function(data){
	    	if (data.result) {
	    		$(".name").html("学生姓名："+data.studentName);
	    		examStuId=data.examStuId;
	    		enable('previous');
	    		loadAnswer(examId,data.examStuId,correctStatus);
			}else{
				pageNo=pageNo-1;
				eoc.success('温馨提示','已经是最后一位学生了!',function(){failure('next')});
			}
	    }
	});
}
function preOne(correctStatus){
	pageNo=pageNo-1;
	$.ajax({
		url:base+"/teacherCenter/correctIndex/getExamStu/",
		data:{examId:examId,pageNo:pageNo,correctStatus:correctStatus,code:Math.random()},
	    error:function(){
	    	
	    }, 
	    success:function(data){
	    	if (data.result) {
	    		$(".name").html("学生姓名："+data.studentName);
	    		examStuId=data.examStuId;
	    		enable('next');
	    		loadAnswer(examId,data.examStuId,correctStatus);
			}else{
				pageNo=pageNo+1;
				eoc.success('温馨提示','已经是第一位学生了!',function(){failure('previous')});
			}
	    }
	});
}

//加载试题和学生回答
function loadAnswer(examId,examStudentId,correctStatus)
{
	$('.examAnswer').load(base+"/teacherCenter/correctIndex/getAnswer/?examId="+examId+"&examStuId="+examStudentId+"&correctStatus="+correctStatus+"&code="+Math.random());
}

//保存试题分数
function inputScore()
{
//	if(isLast){
//		eoc.success('温馨提示',"已全部批阅!");
//		failure('input');
//		return;
//	}
	//如果数据正确
	if(flag){
		var correctData=$("#correctForm").serialize()
		$.ajax({
			url:base+"/teacherCenter/correctIndex/inputScore/?examStuId="+examStuId,
			type:"post",
			data:correctData,
			dataType:"json",
		    error:function(){    
		    	
		    }, 
		    success:function(data){
		    	if (data.result) {
		    		$(".grade").html("主观题得分："+data.totalScore+"分");
		    		pageNo=pageNo-1;
		    		unCorrCount--;
		    		if(unCorrCount>0){
		    			backMsg();
		    		}else{
		    			eoc.success('温馨提示',"已全部批阅完成，查看批阅结果!",function(){correctResult(examId,examType)});
		    		}
		    			
				}else{
					eoc.error('温馨提示','成绩未能保存，请稍候再试!');
				}
		    }
		});
	}else{
		eoc.error('温馨提示','请输入正确的分数!');
	}
}
var time =3;
var intv;
function backTime(){
	time--;
	if(time==0){
		clearInterval(intv);
		nextOne(2);
		return;
	}
	$('#backMsg .con span').html("批阅成功, "+time+"秒后将跳入下一位!");
	
}
function backMsg(){
	time = 3;
	intv = setInterval("backTime()",1000);
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
	$('#backMsg .con span').html("批阅成功, "+time+"秒后将跳入下一位!");
	$('#backMsg').css({'margin-left':"-100px",left:"50%",top:H/2+yScroll-250+"px"}).css({'z-index':1000});
    $('#backMsg').show();
}

//校验输入的分数是否正确
function check(value,queScore){
	var value=parseInt(value);
	var queScore=parseInt(queScore);
	flag=true;
	if(value!=0)
		flag=isFloat(value);	
	if(flag){
		if(value<0 || value>queScore){  
			flag=false;
			eoc.error('温馨提示','请输入正确的分数!');
		}
	}else{
		eoc.error('温馨提示','请输入正确的分数!');
	}
	return flag;
}

//计算未批阅数
function countUnCorrect(){
	$.ajax({
		url:base+"/teacherCenter/correctIndex/countUnCorrect/",
		data:{examId:examId},
	    error:function(){
	    	
	    }, 
	    success:function(data){
	    	unCorrCount=data.unCorrectCount
	    }
	});
}

//使按钮失效
function failure(id){
	$("#"+id).attr("disabled", true);
	$("#"+id).removeClass("btn-primary");
	$("#"+id).addClass("btn-disabled");
}

//启用按钮
function enable(id){
	$("#"+id).removeAttr("disabled");
	$("#"+id).removeClass("btn-disabled");
	$("#"+id).addClass("btn-primary");
}


