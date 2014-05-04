var sectionCount = 1;
$(function() {
	// 生成章节号.
	var len = $('.classList-box dl').length;
	if (len == 0) {
		var $dl = $('.form-bodyTab dl').last().clone();
		$dl.attr('data-id', sectionCount);
		sectionCount++;
		$dl.find('label').first().html(
				"第" + eoc.cms.util.uppercase(len + 1) + "章：");
		$dl.show();
		$dl.insertBefore($('.classList-box p'));
	}
});
// 删除章
function deleteSection(tag) {
	$.messager.confirm('提示', '确定要删除该章吗?', function(result) {
		if (result) {
			if ($(tag).parents('.classList-box').find('dl').length == 1) {
				$.messager.alert('提示', '不允许删除所有的章！', 'info');
				return;
			}
			if ($(tag).parents('dl').find('dd li').length > 0) {
				$.messager.alert('提示', '当前章存在小节,请先删除小节才能删除本章！', 'info');
				return;
			}
			var id = $(tag).parents('dt').attr('data-id');
			if (!eoc.cms.main.isEmpty(id)) {
				var $input = $('<input>').attr("name", "deleteSection").attr(
						"value", id).attr("type", "hidden");
				$input.appendTo($('#page2from'));
			}
			$(tag).parents('.creatList').remove();
			reduceSection();
		}
	}

	);
}
// 删除节
function deletePart(tag) {
	$.messager.confirm('提示', '确定要删除该章/节吗?', function(result) {
		if (result) {
			if ($(tag).parents('.classList-box').find('dl').length == 1
					&& $(tag).parents('dl').find('dd li').length == 1) {
				$.messager.alert('提示', '不允许删除第一章的第一节！', 'info');
				return;
			}
			var id = $(tag).parents('li').attr('data-id');
			if (!eoc.cms.main.isEmpty(id)) {
				$.ajax({
					url : "course/ajaxGetResourceInPartOfSectionNum/",
					data : {
						"courseId" : $("#page2from input[name=updateCourseId]")
								.val(),
						"partOfSectionId" : id
					},
					type : 'post',
					success : function(data) {
						if (data.flag) {
							if (data.result) {
								$.messager
										.alert('错误', "该小节下存在内容,不能删除.", 'info');
								return;
							} else {
								// $('#quickNav li').eq(1).click();
								var $input = $('<input>').attr("name",
										"deletePart").attr("value", id).attr(
										"type", "hidden");
								$input.appendTo($('#page2from'));

								var $ol = $(tag).parents('ol');
								$(tag).parents('li').remove();
								reducePart($ol);
							}
						} else {
							$.messager.alert('错误', data.msg, 'info');
							return;
						}
					}
				});
			} else {
				var $ol = $(tag).parents('ol');
				$(tag).parents('li').remove();
				reducePart($ol);
			}

		}

	});
}
// 校验章/节
function checkSection(section) {
	if (eoc.cms.main.isEmpty(section)) {
		return false;
	}
	var secArray = section.split(":");
	if (secArray.length == 1) {
		section = secArray[0];
	}
	if (secArray.length == 2) {
		section = secArray[1];
	}
	if (section.length > 80) {
		return false;
	}
	return true;
}

// 增加章
function addSection(tag) {
	var length = $('.classList-box dl').length;
	var $dl = $('.form-bodyTab dl').last().clone();
	$dl.attr('data-id', sectionCount);
	sectionCount++;
	$dl.find('label').first().html(
			"第" + eoc.cms.util.uppercase(length + 1) + "章：");
	$dl.show();
	$dl.insertAfter($('.classList-box dl').last());
}
// 增加节
function addPart(tag) {
	var $li = $('.form-bodyTab li').last().clone();
	var length = $(tag).parents('ol').find('li').length;
	var dlId = $(tag).parents('dl').attr('data-id');
	// 判断新增的节类型 1.在原有章的基础上增加节 2.在新增的章的基础上增加节
	if (eoc.cms.main.isEmpty(dlId)) {
		$li.removeAttr('data-id');
		$li.find('input[type=text]').attr('onchange', 'addPartValue(this)');
		$li.find('input[type=hidden]').attr('name', 'addPart');
		$li.attr('data-pid', $(tag).parents('dl').find('dt').attr('data-id'));
	} else {
		$li.attr('data-id', length + 1);
	}
	$li.find('label').first().html(
			"第" + eoc.cms.util.uppercase(length + 1) + "节：");
	$li.appendTo($(tag).parents('ol'));
}
// 减少章的时候触发
function reduceSection() {
	$('.classList-box').find('dt').each(function(i) {
		$(this).find('label').html("第" + eoc.cms.util.uppercase(i + 1) + "章：");
	});
}
// 减少节的时候触发
function reducePart($ol) {
	$ol.find('li').each(function(i) {
		$(this).find('label').html("第" + eoc.cms.util.uppercase(i + 1) + "节：");
	});
}
// 改变章名称
function sectionChangeValue(tag) {
	var id = $(tag).attr('data-id');
	$('#section' + id).val(id + ':' + $(tag).val());
}
// 改变节名称
function partChangeValue(tag) {
	var id = $(tag).attr('data-id');
	if (eoc.cms.main.isEmpty($(tag).val())) {
		$('#part' + id).val('');
	} else {
		$('#part' + id).val(id + ':' + $(tag).val());
	}

}
// 章原有的章的基础上增加节
function addPartValue(tag) {
	$(tag).next().val(
			$(tag).parents('li').attr('data-pid') + ":" + $(tag).val());
}
// 章新的章的基础上增加节
function addNewPartValue(tag) {
	$(tag).next()
			.val(
					$(tag).parents('dl').attr('data-id') + ":"
							+ $(tag).parents('li').attr('data-id') + ":"
							+ $(tag).val());
}
// 保存修改
function savePage2() {
	var flag = true;
	var errorMsg = '';
	$('#fourthGrid').hide();
	$('input[name=changeSection]').each(function(index) {
		if (!eoc.cms.main.isEmpty($(this).val())) {
			if (!checkSection($(this).val())) {
				flag = false;
				errorMsg = "课程章/节长度不能超过80字";
			} else {
				$(this).appendTo($('#page2from'));
			}
		}
	});

	$('input[name=changePart]').each(function(index) {
		if (!eoc.cms.main.isEmpty($(this).val())) {
			if (!checkSection($(this).val())) {
				flag = false;
				errorMsg = "课程章/节长度不能超过80字";
			} else {
				$(this).appendTo($('#page2from'));
			}
		}
	});

	var sLen = $('input[name=addSection]').length;
	$('input[name=addSection]').each(
			function(index) {
				if (index < (sLen - 1)) {
					var val = $(this).val();
					if (eoc.cms.main.isEmpty($(this).val())) {
						flag = false;
					} else if (!checkSection($(this).val())) {
						flag = false;
						errorMsg = "课程章/节长度不能超过80字";
					} else {
						$(this).clone().attr('style', 'display:none;')
								.appendTo($('#page2from'));
					}
				}

			});

	var apLen = $('input[name=addPart]').length;
	$('input[name=addPart]').each(function(index) {
		if (index < apLen) {
			var val = $(this).val();
			if (eoc.cms.main.isEmpty(val)) {
				flag = false;
			} else if (!checkSection($(this).val())) {
				flag = false;
				errorMsg = "课程/章节长度不能超过80字";
			} else {
				$(this).appendTo($('#page2from'));
			}
		}
	});

	var anpLen = $('input[name=addNewPart]').length;
	$('input[name=addNewPart]').each(function(index) {

		if (index < (anpLen - 1)) {
			var val = $(this).val();
			if (eoc.cms.main.isEmpty(val)) {
				flag = false;
			} else if (!checkSection($(this).val())) {
				flag = false;
				errorMsg = "课程/章节长度不能超过80字";
			} else {
				$(this).appendTo($('#page2from'));
			}
		}
	});

	if (!flag) {
		$('#fourthGrid').show();
		if (eoc.cms.main.isEmpty(errorMsg)) {
			$.messager.alert('提示', '章/节不允许为空！', 'info');
		} else {
			$.messager.alert('提示', errorMsg, 'info');
		}
		return;
	} else {

		$.ajax({
			url : "course/updatePublishedCourseStep2/",
			data : $('#page2from').serialize(),
			type : 'post',
			success : function(data) {
				if (data.result) {
					$('#courseAmendTab').tabs('select', 2);
				}
			}
		});
	}
}

function returnStepOne(){
	$('#courseAmendTab').tabs('select', 0);
}
