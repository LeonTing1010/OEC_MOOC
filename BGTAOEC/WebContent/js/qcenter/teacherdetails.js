/**
 * 教师详情页 CREATE BY: DONGS TIME:2014-1-21 13:17:25
 */

$(function($) {

	
	$("#questionTab").click(function() {
		var content = $("#questionUl").text();
		if (isNull(content)) {
			getMoreQuestion();
		}else if(isNull($.trim(content))){
			getMoreQuestion();
		}
	});
	$("#questionBtn").click(function() {
		getMoreQuestion();
	});

	$("#courseBtn").click(function() {
		getMoreCourse();
	});
	$("#courseTab").click(function() {
		var content = $("#courseUl").text();
		if (isNull(content)) {
			getMoreCourse();
		}else if(isNull($.trim(content))){
			getMoreCourse();
		}
	});

});

function getMoreQuestion() {
	var content = $("#questionUl").text();
	if (isNull(content)) {
		$("#questionToPage").val("1");
	}else if(isNull($.trim(content))){
		$("#questionToPage").val("1");
	}
	var url = base + "/qacenter/teacherdetail/questionlist/?code="+Math.random();
	var teacherId =$("#teacherId").val();// $("div .curTabCons").attr("teacherId");
	var toPage = $("#questionToPage").val();//$("#questionUl").attr("toPage");
	// $("#questionUl").load(url, {
	// "teacherId" : teacherId,
	// "toPage" : toPage
	// });
	$.ajax({
		url : url,
		data : {
			"teacherId" : teacherId,
			"toPage" : toPage
		},
		error : function() {

		},
		success : function(data) {
			if (!isNull(data)) {
				// //pageSize = pageSize + 1;
				// var text = $("#questionUl").html();
				// text+=data;
				// $("#questionUl").html('');
				// $("#questionUl").html(text);

				$("#questionUl").append(data);
				toPage = Number($.trim(toPage)) + 1;
				 $("#questionToPage").val(toPage);
				//$("#questionUl").attr("toPage", toPage);
			} else {
				//$("#questionBtn span").text("  ");
			}
		}
	});

}

function getMoreCourse() {
	var content = $("#courseUl").text();
	if (isNull(content)) {
		$("#courseToPage").val("1");
	}else if(isNull($.trim(content))){
		$("#courseToPage").val("1");
	}
	var url = base + "/qacenter/teacherdetail/courselist/?code="+Math.random();
	var teacherId =$("#teacherId").val();
	var toPage = $("#courseToPage").val();
	$.ajax({
		url : url,
		data : {
			"teacherId" : teacherId,
			"toPage" : toPage
		},
		error : function() {

		},
		success : function(data) {
			if (!isNull(data)) {
				$("#courseUl").append(data);
				toPage = Number($.trim(toPage)) + 1;
				$("#courseToPage").val(toPage);
				//$("#courseUl").attr("toPage", toPage);
			} else {
				//$("#courseBtn span").text("没有更多的数据");
			}
		}
	});

}
