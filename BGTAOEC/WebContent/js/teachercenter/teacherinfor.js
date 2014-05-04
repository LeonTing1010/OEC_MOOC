
/*******************************************************************************
 * function:教师信息修改 Add By:缪佳 Add Date:2014-01-21
 ******************************************************************************/
var uploadResult=1; //上传图片结果

// 初始化
$(function($) {
	$("textarea[name=introduce]").keyup(function() {
		var content=$(this).text();
		var maxlength=500;
		if (content.length>maxlength) {
			content=content.substr(0,maxlength);
			$(this).text(content);
		}
		//$(this).siblings("label").children("b").text(content.length);
		
	});
});

// SelectItem 选中事件
function SelectItemByValue(objSelect, objItemText) {
	for (var i = 0; i < objSelect.options.length; i++) {
		if (objSelect.options[i].value == objItemText) {
			objSelect.options[i].selected = true;
			break;
		}
	}
}

// 更新老师信息到数据库
function UpdateTeacherInfor(element) {
	if (formverify()) {
		$(".flag").each(function(){
			$(this).remove();
		})
		var params = $('#presonInfor').serialize();
		$.ajax({
			url : base + '/teachercenter/saveteacherInfor/',
			type : 'post',
			dataType : 'json',
			data : params,
			error : function(data) {
				eoc.error("错误", "请求错误", null);
			},
			success : function(data) {
				update_page(data,element);
				if (data.result) {
				$(".teacher-name").children("a:first").text(data.userMode.userName);
					if (isNull(data.userMode.userName)) {
						$('a[name="topMenuUserName"]').html(data.userMode.userEmail);
					}else {
						$('a[name="topMenuUserName"]').html(data.userMode.userName);
					}
				}			        
			}
		});
	}
}

// 更新数据后页面显示的效果
function update_page(json,element) {
	if (json.result) {
		//eoc.success('温馨提示', '教师个人资料修改成功！', function() {});
		$(element).siblings().remove();
		$(element).after("<span style='color:#8dce1a;margin-left:15px'>教师个人资料修改成功！</span>");
		setTimeout(function() {loadTeacherInfor();},1000);
		
	} else {
		//eoc.error('错误提示', "教师个人资料修改失败！" + json.errorMsg, function() {});
		$(element).siblings().remove();
		$(element).after("<span style='color:red;margin-left:15px'>"+json.errorMsg+"！</span>");
	}
	
	
}

// 弹出页面信息
function alertinfo() {
	// 显示弹出层
	var obj = document.getElementById("alertdiv");
	var W = screen.width; // 取得屏幕分辨率宽度
	var H = screen.height; // 取得屏幕分辨率高度
	var yScroll; // 取滚动条高度
	if (self.pageYOffset) {
		yScroll = self.pageYOffset;
	} else if (document.documentElement && document.documentElement.scrollTop) {
		yScroll = document.documentElement.scrollTop;
	} else if (document.body) {
		yScroll = document.body.scrollTop;
	}
	obj.style.top = (H / 2 - 90 - 225 + yScroll) + "px";
	obj.style.display = "block";
	// 情况Div值 更新DIV值
//	ClearAlertContent();
//	GetNoChooseInfor();
//	GetChooseInfor();
}

// 关闭分组
function closediv(id) {
	// 关闭弹出层
	document.getElementById(id).style.display = "none";
	var scrollstyle = scrolls();
	scrollstyle.style.overflowY = "auto";
	scrollstyle.style.overflowX = "hidden";
	$("#groupNameTr").hide();
	$("#groupNameEmpty").hide();
}

function scrolls() {
	// 取浏览器类型
	var temp_h1 = document.body.clientHeight;
	var temp_h2 = document.documentElement.clientHeight;
	var isXhtml = (temp_h2 <= temp_h1 && temp_h2 != 0) ? true : false;
	var htmlbody = isXhtml ? document.documentElement : document.body;
	return htmlbody;
}

// 左右框互移
sortitems = 1;
function move(fbox, tbox) {
	for (var i = 0; i < fbox.options.length; i++) {
		if (fbox.options[i].selected && fbox.options[i].value != "") {
			var no = new Option();
			no.value = fbox.options[i].value;
			no.text = fbox.options[i].text;
			tbox.options[tbox.options.length] = no;
			fbox.options[i].value = "";
			fbox.options[i].text = "";
		}
	}
	BumpUp(fbox);
	if (sortitems)
		SortD(tbox);
}

function BumpUp(box) {
	for (var i = 0; i < box.options.length; i++) {
		if (box.options[i].value == "") {
			for (var j = i; j < box.options.length - 1; j++) {
				box.options[j].value = box.options[j + 1].value;
				box.options[j].text = box.options[j + 1].text;
			}
			var ln = i;
			break;
		}
	}
	if (ln < box.options.length) {
		box.options.length -= 1;
		BumpUp(box);
	}
}

function SortD(box) {
	var temp_opts = new Array();
	var temp = new Object();
	for (var i = 0; i < box.options.length; i++) {
		temp_opts[i] = box.options[i];
	}

	for (var x = 0; x < temp_opts.length - 1; x++) {
		for (var y = (x + 1); y < temp_opts.length; y++) {
			if (temp_opts[x].text > temp_opts[y].text) {
				temp = temp_opts[x].text;
				temp_opts[x].text = temp_opts[y].text;
				temp_opts[y].text = temp;
				temp = temp_opts[x].value;
				temp_opts[x].value = temp_opts[y].value;
				temp_opts[y].value = temp;
			}
		}
	}

	for (var i = 0; i < box.options.length; i++) {
		box.options[i].value = temp_opts[i].value;
		box.options[i].text = temp_opts[i].text;
	}
}

/**教师中心修改密码**/
function pwdverify(element) {
	var flag = true;
	$("[tag]:visible").each(function() {
		$(this).nextAll(".Okspan").remove();
		var $name = $(this).attr('name');
		if ($name == 'oldpwd' || $name == 'newpwd' || $name == 'submitpwd') {
			if (!verify($(this))) {
				TipMessage(this);
				flag = false;
				$(element).siblings().remove();
			}
		}

	});
	if (flag) {
		var params = $('#pwdupdate').serialize();
		$.ajax({
			url : base + "/teacherCenter/updatepwd/?code="+Math.random(),
			data : params,
			success : function(data) {
				if (data == "true") {
					$(element).siblings().remove();
					$(element).after("<span style='color:#8dce1a;margin-left:15px'>密码修改成功!</span>");
					
					$('#pwdupdate .validation').parent().remove();
					$('#pwdupdate input').val('');
					setTimeout(function(){
						$('#fourthGrid span').last().remove();
					},1000);
					
				} else {
					$(element).siblings().remove();
					$(element).after(errorSpan('原始密码错误'));
				}
			}
		});
		return flag;
	} else {
		return flag;
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
/**上传教师头像**/
function uploadImage()
{
	$('#resultInfo').html("");
	var imageFile =  $('input[name=imagefile]').val();
    if(checkImg(imageFile)){
    	$('#imgForm').submit();
    }
}

/**上传头像成功后返回**/
function uOk(result, userHeadPic) {
	if(result==1){
		$('#headPicPreview').attr('src',cache+userHeadPic);
		$('input[name=imgpre]').attr('value',userHeadPic)
	}
	uploadResult=result;

}
/**保存教师头像信息**/
function imgSub() {
	var imageUrl=$('input[name=imgpre]').val();
	if(!checkImg(imageUrl)){
		return;
	}
	if(uploadResult==1){
		$.ajax({
			url:base+"/teachercenter/updateteacherimage/",
			data:{imageUrl:imageUrl},
			type:'post',
			success:function(data){
				if(data.result){
					$('#resultInfo').html("<i class='ok-icon'></i><span class='ok'>"+data.messageString+"</span>");
					$('.teacher-headImg').children("img").attr("src",cache + data.userHeadPic);
					$('img[name="topMenuUserPic"]').attr('src',cache + data.userHeadPic);	
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

// 获取用户图片信息 Add By:缪佳 2014-02-10
function getTeacherImage() {
	$.ajax({
		url : base + "/teachercenter/getteacherimage/?" + "&code="
				+ Math.random(),
		type : 'post',
		dataType : 'json',
		data : null,
		success : function(data) {
			if (data.flag == 'right') {
				$('#headPicPreview').attr('src',cache + data.userHeadPic);
				$('#imgpre').attr('value',data.userHeadPic);
			}
		}
	});
}

// 获取待选领域信
function GetNoChooseInfor() {
	$.ajax({
		url : base + '/teachercenter/getnochooseinfor/',
		type : "post",
		dataType : 'json',
		data : null,
		success : function(data) {
			if (data.result != "null") {
				$(data.result).each(function(i) {
					var varObj = document.getElementById("listInfor");
					var no = new Option();
					no.value = data.result[i]["id"];
					no.text = data.result[i]["name"];
					varObj.options[varObj.options.length] = no;
				});
			}
		}
	});
}

// 清空Select中的数据
function ClearAlertContent() {
	$("#listInfor").empty();
	$("#listYes").empty();
}

// 获取已经选择的数据
function GetChooseInfor() {
	$.ajax({
		url : base + '/teachercenter/getchooseinfor/',
		type : "post",
		dataType : 'json',
		data : null,
		success : function(data) {
			if (data.result != "null") {
				$(data.result).each(function(i) {
					var varObj = document.getElementById("listYes");
					var no = new Option();
					no.value = data.result[i]["id"];
					no.text = data.result[i]["name"];
					varObj.options[varObj.options.length] = no;
				});
			}
		}
	});
}

function ShowGetChooseInfor() {
	$.ajax({
		url : base + '/teachercenter/getchooseinfor/',
		type : "post",
		dataType : 'json',
		data : null,
		success : function(data) {
			if (data.result != "null") {
				var varInfor = null;
				$(data.result).each(function(i) {
					if (i == 0) {
						varInfor = varInfor + data.result[i]["name"];
					} else {
						varInfor = varInfor + "," + data.result[i]["name"];
					}
				});
				$("#showSelectInfor").html(varInfor);
			}
		}
	});
}

// 保存修改的数据到数据库 删除原有教师的数据插入新的数据
function SaveChooseInfor() {
	var varListBox = document.getElementById("listYes");
	var varSaveID = null;
	for (var i = 0; i < varListBox.options.length; i++) {
		if (varSaveID == null) {
			varSaveID = varListBox.options[i].value;
		} else {
			varSaveID = varSaveID + "," + varListBox.options[i].value;
		}
	}
	$.ajax({
		url : base + '/teachercenter/savechooseinfor/',
		type : "post",
		dataType : 'json',
		data : {
			"pjid" : varSaveID
		},
		error : function() {
			eoc.error('错误提示', '选择失败！', function() {
			});
		},
		success : function(data) {
			if (data.result) {
				$('#alertdiv').hide();
				eoc.success('温馨提示', '选择成功！', function() {

				});
			} else {
				$('#alertdiv').hide();
				eoc.error('错误提示', data.errorMsg, function() {
				});
			}
		}
	});
}

function showPicture() {
	var path = document.imgForm.file.value;
	document.getElementById("image").src = path;
}
/**
 * function: 教师擅长领域弹出层设计.
 * author :dongs
 */
function popupteachershine() {
	$("#alertDiv").empty();
	$("#alertDiv").load( base + "/teachercenter/teacherinforedit/initteachershine.ajax",function(){
		// 显示弹出层
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
		var top="-"+(H/2+yScroll/3)+"px";
		$("#alertDiv").css({"left":"0%","top":top,"display":"block","z-index":"900","position":"relative"});
		$("#alertDiv").find(".close").click(function() {
			$("#alertDiv").attr('style','display:none;');
		});
		$("#alertDiv").find("dt").click(function(evenet) {
			$(this).parent("dl").toggleClass("slideUp");		
			$(this).toggleClass("current");
		});
		$("#alertDiv").find("dd").click(function(evenet) {
			$(this).toggleClass("current");
		});
		$("#alertDiv .centerBox").find("input[type=button]").eq(0).click(function(evenet) {
			var $leftBox=$("#alertDiv .leftBox");
			var $rightBox=$("#alertDiv .rightBox");
			var $leftddObjs=$leftBox.find("dd[class=current]");
			var $rightdlObjs=$rightBox.find("dl");
			$leftddObjs.each(function(){
				var $leftddObj=$(this).removeClass("current");
				var leftLang = $leftddObj.parent("dl").attr("lang");
				var flag=false; //查找右边是否有该dl
				$rightdlObjs.each(function() {
					if ($leftddObj.parent("dl").attr("lang")==$(this).attr("lang")) {
						var $leftDl=$leftddObj.parent("dl");
						$(this).append($leftddObj);//append一个jquery对象是移动,而不是复制.
						var  leftNum=$leftDl.find("dd").length;
						$leftDl.find("span").html(leftNum);
						var rightNum=$leftddObj.parent("dl").find("dd").length;
						$leftddObj.siblings("dt").children("span").html(rightNum);
						flag=true;
					}
				});
				if(!flag){
					var $newDl = $leftddObj.parent("dl").clone();
					$newDl.find("dd").remove();
					$newDl.append($leftddObj);
					$newDl.find("dt").children("span").html('1');
					$(".rightBox .boxCon").append($newDl);
				}
			});
			var selectNum=$rightBox.find("dd").length;
			$("#selectNum").html(selectNum);
		//	evenet.stopPropagation(); 
		});
		$("#alertDiv .centerBox").find("input[type=button]").eq(1).click(function(evenet) {
			var $leftBox=$("#alertDiv .leftBox");
			var $rightBox=$("#alertDiv .rightBox");
			var $rightddObjs=$rightBox.find("dd[class=current]");
			var $leftdlObjs=$leftBox.find("dl");
			$rightddObjs.each(function(){
				var $parentDl = $(this).parent();
				var $rightddObj=$(this).removeClass("current");
				$leftdlObjs.each(function() {
					if ($rightddObj.parent("dl").attr("lang")==$(this).attr("lang")) {
						var $rightDl=$rightddObj.parent("dl");
						$(this).append($rightddObj);//append一个jquery对象是移动,而不是复制.
						var  rightNum=$rightDl.find("dd").length;
						$rightDl.find("span").html(rightNum);
					    var	leftNum=$rightddObj.parent("dl").find("dd").length;
					    if(rightNum==0){
					    	$parentDl.remove();
					    }
						$rightddObj.siblings("dt").children("span").html(leftNum);
					}
				});
			});
			var selectNum=$rightBox.find("dd").length;
			$("#selectNum").html(selectNum);
		
			//evenet.stopPropagation(); 
		});
		$("#alertDiv").find(".btn-primary").click(function(evenet) {
			var size=$("#alertDiv .rightBox").find("dd").size();
			if(size>3 || size<=0){
				eoc.alert("警告","请选择1到3个擅长领域",null);
			}else{
				var IDs="";
				$("#selectedShines").html("已选择擅长领域: ");
				$("#alertDiv .rightBox").find("dd").each(function(){
					$("#selectedShines").append($(this).html()+", ");
					IDs=IDs+$(this).siblings("dt").attr("title")+"|"+$(this).attr("id")+",";
				});
				$("#jobGroupIdsInput").val(IDs);
				$("#alertDiv").attr('style','display:none;');
			}
			
		});
		
	
	});
	
}

function isNull(obj){
	if(null==obj || ''==obj || undefined==obj){
		return true;
	}
	return false;
}
