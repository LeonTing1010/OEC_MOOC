/**
 * author: dongs. time:2014-1-20 09:42:44.
 */
$(function($) {
	$("html").css("overflow-y", "scroll");
	
	$("a[id^=item]").each(function() {
		$(this).click(function() {
			var id = $(this).attr("value");
			$("#idInput").val(id);
			$("#typeInput").val("1");
			$("#pageNoInput").val("1");
			$("#pageSizeInput").val("5");
			$(".pageMore span").text("- 查看更多 -");
			$("#questionUl").html("");
			getMoreQuestion(0);
			var val = $(this).text();
			$(".subCrumbs #titleItem").empty();
			$(".subCrumbs #titleSubMenu").html(val);
		});
	});

	$("dt[id^=subMenu]").each(
			function() {
				$(this).click(
						function() {
							var id = $(this).attr("value");
							$("#idInput").val(id);
							$("#typeInput").val("2");
							$("#pageNoInput").val("1");
							$("#pageSizeInput").val("5");
							$(".pageMore span").text("- 查看更多 -");
							$("#questionUl").html("");
							getMoreQuestion(0);
							var val = $(this).text();
							var itemVal = $(this).parents(".subMenu").siblings(
									"a[id^=item]").html();
							var itemId = $(this).parents(".subMenu").siblings(
							"a[id^=item]").attr("value");
							$(".subCrumbs #titleItem").html(itemVal);
							$(".subCrumbs #titleSubMenu").html(val);
							$(".subCrumbs #titleItem").attr("itemId",itemId);
						});

			});

	$(".pageMore").click(function() {
		$("#pageSizeInput").val("5");
		var flag = $("#flag").val();
		getMoreQuestion(flag);

	});

	
	$(".subCrumbs #titleItem").click(function(){
		var id=$(".subCrumbs #titleItem").attr("itemId");
		$("#item"+id).click();
	});
	
	
	
	/*$("span[id=^slide]").each(function() {
		$(this).click(function() {
			$(this).parent("p").html($(this).attr("strtext"));
		});

	});*/

	
});


$("a[id^=item]").each(function() {
	$(this).click(function() {
		var id = $(this).attr("value");
		$("#idInput").val(id);
		$("#typeInput").val("1");
		$("#pageNoInput").val("1");
		$("#pageSizeInput").val("5");
		$(".pageMore span").text("- 查看更多 -");
		$("#questionUl").html("");
		getMoreQuestion(0);
		var val = $(this).text();
		$(".subCrumbs #titleItem").empty();
		$(".subCrumbs #titleSubMenu").html(val);
	});
});


function morequestion(proId,protypes){
	if(proId!=0){
		$("#idInput").val(proId);
		$("#typeInput").val("1");
		$("#pageNoInput").val("1");
		$("#pageSizeInput").val("5");
		$(".pageMore span").text("- 查看更多 -");
		$("#questionUl").html("");
		getMoreQuestion(protypes);
		var val = $('#item'+proId).text();
		$(".subCrumbs #titleItem").empty();
		$(".subCrumbs #titleSubMenu").html(val);
	}
}

function expandContent(span){
	$(span).parent("p").hide();
	$(span).parent("p").next("label").show();
	
}


//加载更多问题 0-表示点击加载更多 1-表示从首页最新更多进入 2-表示从首页最热更多进入
function getMoreQuestion(flag) {
	var url = base + "/qacenter/tosortquestionlist/";
	var type = $("#typeInput").val();
	var id = $("#idInput").val();
	var pageSize = $("#pageSizeInput").val();
	var pageNo = $("#pageNoInput").val();
	$.ajax({
		url : url,
		data : {
			"type" : type,
			"id" : id,
			"pageSize" : pageSize,
			"pageNo" : pageNo,
			"flag" : flag

		},
		error : function(data) {
			alert("错误");
		},
		success : function(data) {
			if (data != "") {
				$("#questionUl").append(data);
				pageNo = Number($.trim(pageNo)) + 1;
				$("#pageNoInput").val(pageNo);
			} else {
				
			}
		}

	});

}
