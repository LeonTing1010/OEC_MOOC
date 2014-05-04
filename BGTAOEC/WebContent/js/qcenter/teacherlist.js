/**
 * author: dongs. time:2014-1-20 09:42:44.
 */
var type = 0;
var profid = 0;
var jobgId = 0;


$(function($) {
	$("html").css("overflow-y", "scroll");
	$("a[id^=item]").each(function() {
		$(this).click(function() {
			type = 1;
			profId = $(this).attr("value");
			jobgId = 0;
			$("#teacherUl").attr("pageNo", "1");
			//$(".pageMore span").text("- 查看更多 -");
			$("#teacherUl").html("");
			getMoreTeacherByProfId(profId);
			var val = $(this).text();
			$(".subCrumbs #titleSubMenu").html(val);
			$(".subCrumbs #titleItem").empty();
		});
	});
	
	$("dt[id^=subMenu]").each(
			function() {
				$(this).click(
						function() {
							type = 2;
							profid = 0;
							jobgId = $(this).attr("value");
							
							$("#teacherUl").attr("pageNo", "1");
							//$(".pageMore span").text("- 查看更多 -");
							$("#teacherUl").html("");
							getMoreTeacherByJobgId(jobgId);
							var val = $(this).text();
							var itemVal = $(this).parents(".subMenu").siblings(
									"a[id^=item]").text();
							var itemId = $(this).parents(".subMenu").siblings(
							"a[id^=item]").attr("value");
							$(".subCrumbs #titleItem").html(itemVal);
							$(".subCrumbs #titleItem").attr("itemId",itemId);
							$(".subCrumbs #titleSubMenu").html(val);
						});

			});

	$(".pageMore").click(function() {
		if (type == 1) {
			getMoreTeacherByProfId(profId);
		}
		if (type == 2) {
			getMoreTeacherByJobgId(jobgId);
		}
		if (type == 0) {
			getMoreTeacher();

		};
	});
	
	
	$(".subCrumbs #titleItem").click(function(){
		var id=$(".subCrumbs #titleItem").attr("itemId");
		$("#item"+id).click();
	});
	
	
	

});

function getMoreTeacherByJobgId(jobgId) {
	var url = base + "/qacenter/teachercontentlist/";

	var pageNo = $("#teacherUl").attr("pageNo");
	$.ajax({
		url : url,
		data : {
			"jobgId" : jobgId,
			"pageNo" : pageNo,
		},
		error : function(data) {
			alert("错误by jgroup");
		},
		success : function(data) {
			if (data != "") {
				$("#teacherUl").append(data);
				pageNo = Number($.trim(pageNo)) + 1;
				$("#teacherUl").attr("pageNo", pageNo);
			} else {
				
			}
		}

	});

}

function getMoreTeacherByProfId(profId){
	var url = base + "/qacenter/teachercontentlist/";

	var pageNo = $("#teacherUl").attr("pageNo");
	$.ajax({
		url : url,
		data : {
			"profId" : profId,
			"pageNo" : pageNo,
		},
		error : function(data) {
			alert("错误by profId");
		},
		success : function(data) {
			if (data != "") {
				$("#teacherUl").append(data);
				pageNo = Number($.trim(pageNo)) + 1;
				$("#teacherUl").attr("pageNo", pageNo);
			} else {
			//	$(".pageMore span").text("没有更多的数据");
			}
		}

	});

}

function getMoreTeacher() {
	var url = base + "/qacenter/teachercontentlist/";

	var pageNo = $("#teacherUl").attr("pageNo");
	$.ajax({
		url : url,
		data : {
			"pageNo" : pageNo,
		},
		error : function(data) {
			alert("错误");
		},
		success : function(data) {
			if (data != "") {
				$("#teacherUl").append(data);
				pageNo = Number($.trim(pageNo)) + 1;
				$("#teacherUl").attr("pageNo", pageNo);
			} else {
				//$(".pageMore span").text("没有更多的数据");
			}
		}

	});

}
