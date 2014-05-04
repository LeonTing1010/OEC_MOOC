/**
 * 
 */

$('#timeFromS').datebox({
	width : 130,
	editable : false,
	buttons : dateboxbuttons

});
$('#timeToS').datebox({
	width : 130,
	editable : false,
	buttons : dateboxbuttons
});

$('#professionIdS').combobox(
		{
			valueField : 'id',
			textField : 'text',
			editable : false,
			url : 'qacenter/getProfessionListJson',
			onSelect : function(record) {
				$('#jobGroupIdS').combobox('reload',
						'qacenter/getJobGroupListJson?id=' + record.id);
			}
		});
$('#jobGroupIdS').combobox({
	valueField : 'id',
	textField : 'text',
	editable : false,
	url : 'qacenter/getJobGroupListJson?id=0',
});
$('#chosenQuestionOrNotS').combobox({
	valueField : 'id',
	textField : 'text',
	editable : false,
	data : [ {
		text : '请选择',
		id : ''
	}, {
		text : '已推荐',
		id : '1'
	}, {
		text : '未推荐',
		id : '0'
	} ]
});
$('#visibleQuestionOrNotS').combobox({
	valueField : 'id',
	textField : 'text',
	editable : false,
	data : [ {
		text : '请选择',
		id : ''
	}, {
		text : '已屏蔽',
		id : '1'
	}, {
		text : '正常',
		id : '0'
	} ]
});
$('#questionTypeS').combobox({
	valueField : 'id',
	textField : 'text',
	editable : false,
	data : [ {
		text : '请选择',
		id : ''
	}, {
		text : '课程问题',
		id : '1'
	}, {
		text : '答疑提问',
		id : '0'
	} ]
});

$('#qacenter_table')
		.datagrid(
				{
					title : '答疑管理列表',
					striped : true,
					border : true,
					collapsible : false,
					url : 'qacenter/findMainPaginationJson',
					remoteSort : false,
					idField : 'questionId',
					checkOnSelect : false,
					selectOnCheck : false,
					singleSelect : true,
					pagination : true,
					rownumbers : true,
					height : eoc.cms.main.tabsVolume.height - $("#p").height(),
					/*
					 * frozenColumns : [ [ { field : 'checkbox', checkbox : true } ] ],
					 */
					toolbar : [ {
						text : '屏蔽',
						iconCls : 'icon-ok',
						handler : function() {
							batchUpdateQuestionInvisible();
						}
					}, {
						text : '取消屏蔽',
						iconCls : 'icon-ok',
						handler : function() {
							batchUpdateQuestionVisible();
						}
					} ],
					columns : [ [
							{
								checkbox : true,
								field : 'questionId',
								title : '编号',
								sortable : true,
								width : 50,
							},
							{
								field : 'userId',
								title : '用户名',
								sortable : true,
								width : 100,
								formatter : function(value, row, index) {
									if (row.userOfAskQuestion) {
										return row.userOfAskQuestion.userName;
									} else {
										return "";
									}
								}
							},
							{
								field : 'questionContent',
								title : '问题名',
								sortable : true,
								width : 580,
								formatter : function(value, row, index) {
									if (value.length > 80) {
										return value.substr(0, 80)
												.concat("...");
									} else {
										return value;
									}
								}
							},
							{
								field : 'courseId',
								title : '课程名称',
								sortable : true,
								width : 100,
								formatter : function(value, row, index) {
									if (row.courseOfQuestion) {
										return row.courseOfQuestion.courseName;
									} else {
										return "";
									}
								}
							},
							{
								field : 'jobId',
								title : '所属岗位群',
								field : 'jobId',
								sortable : true,
								width : 100,
								formatter : function(value, row, index) {
									if (row.jobGroupOfQuestion) {
										return row.jobGroupOfQuestion.jobName;
									} else {
										return "";
									}
								}
							},
							{
								field : 'visibleQuestionOrNot',
								title : '状态',
								sortable : true,
								width : 100,
								formatter : function(value, row, index) {
									if (value == 0) {
										return "正常";
									} else if (value == 1) {
										return "已屏蔽";
									}
								}
							},
							{
								field : 'updateTime',
								title : '时间',
								sortable : true,
								width : 200,
								formatter : function(value, row, index) {
									if (value) {
										var time = new Date(value);
										return time
												.format("yyyy-MM-dd hh:mm:ss");
									}
								}
							},
							{
								field : 'opt',
								title : '操作',
								width : 140,
								formatter : function(value, row, index) {
									var elem1 = "", elem2 = "", elem3 = "";
									elem1 = '<a href="javascript:void(0);" onclick="showQuestionDital('
											+ row.questionId + ');">查看详情</a>';
									if (row.visibleQuestionOrNot == 0) {
										elem2 = '<a href="javascript:void(0);" onclick="changeQuestionInvisible('
												+ row.chosenQuestionOrNot
												+ ','
												+ row.questionId + ');">屏蔽</a>';
										if (row.questionType == 0) {
											if (row.chosenQuestionOrNot == 0) {
												elem3 = '<a href="javascript:void(0);" onclick="changeChosenQuestionOrNot('
														+ row.questionId
														+ ');">推荐</a>';
											} else if (row.chosenQuestionOrNot == 1) {
												elem3 = '<a href="javascript:void(0);" onclick="changeChosenQuestionOrNot('
														+ row.questionId
														+ ');">取消推荐</a>';
											}
										}

									} else if (row.visibleQuestionOrNot == 1) {
										elem2 = '<a href="javascript:void(0);" onclick="changeQuestionVisible('
												+ row.chosenQuestionOrNot
												+ ','
												+ row.questionId
												+ ');">取消屏蔽</a>';
										elem3 = '<a href="javascript:void(0);" onclick="deleteInvisibleQuestion('
												+ row.questionId + ');">删除</a>';
										;

									}
									return elem1 + "&nbsp;&nbsp;" + elem2
											+ "&nbsp;&nbsp;" + elem3;
								}
							} ] ],
					queryParams : {
						'pageIndex' : _page.pageIndex,
						'pageSize' : _page.pageSize
					}
				});

// 设置分页控件
var pageInfo = {
	pageSize : _page.pageSize,
	pageList : _page.pageList,
	callBack : function() {
		$('#qacenter_table').datagrid('clearChecked');
	}// &key1=value1&key2=value2 params : "key=value"
};
eoc.cms.main.createPagination(pageInfo, $('#qacenter_table'));

/*
 * <input class="easyui-datebox" name="timeFrom" id="timeFrom" editable="false"
 * style="width:130px">
 */

// 封装分页参数
function setSearchingParamters() {
	var courseName = $("#searchForm #courseNameS").val();
	var professionId = $("#searchForm #professionIdS").combobox('getValue');
	var jobGroupId = $("#searchForm #jobGroupIdS").combobox('getValue');
	var chosenQuestionOrNot = $("#searchForm #chosenQuestionOrNotS").combobox(
			'getValue');
	var questionContent = $("#searchForm #questionContentS").val();
	var userName = $("#searchForm #userNameS").val();
	var visibleQuestionOrNot = $("#searchForm #visibleQuestionOrNotS")
			.combobox('getValue');
	var questionType = $("#searchForm #questionTypeS").combobox('getValue');
	var timeFrom = $("#searchForm #timeFromS").datetimebox('getValue');
	var timeTo = $("#searchForm #timeToS").datetimebox('getValue');
	$('#qacenter_table').datagrid({
		queryParams : {
			'courseName' : courseName,
			'professionId' : professionId,
			'jobGroupId' : jobGroupId,
			'chosenQuestionOrNot' : chosenQuestionOrNot,
			'questionContent' : questionContent,
			'userName' : userName,
			'visibleQuestionOrNot' : visibleQuestionOrNot,
			'questionType' : questionType,
			'timeFrom' : timeFrom,
			'timeTo' : timeTo,
			'pageIndex' : _page.pageIndex,
			'pageSize' : _page.pageSize
		}
	});
	$('#qacenter_table').datagrid('clearChecked');
	// 设置分页控件
	var pageInfo = {
		pageSize : _page.pageSize,
		pageList : _page.pageList,
		callBack : function() {
			$('#qacenter_table').datagrid('clearChecked');
		}
	};
	eoc.cms.main.createPagination(pageInfo, $('#qacenter_table'));
}

function changeQuestionInvisible(chosenQuestionOrNot, questionId) {
	var msgString;
	if (chosenQuestionOrNot == 1) {
		msgString = "本条问题为推荐问题。建议先取消推荐，屏蔽后会自动取消推荐，请慎重。是否屏蔽?";
	} else {
		msgString = "屏蔽后，该问题将不再显示在前台，您可以在已屏蔽问题中取消屏蔽。是否屏蔽?";
	}
	$.messager.confirm('确认', msgString, function(r) {
		if (r) {
			var url = 'qacenter/changevisibleQuestionOrNot/';
			$.ajax({
				url : url,
				type : 'POST',
				data : {
					"questionId" : questionId,
				},
				error : function(data) {
					$.messager.alert('错误', data.msg);
				},
				success : function(data) {
					if (data.flag) {
						$('#qacenter_table').datagrid('reload');
					} else {
						$.messager.alert('错误', data.msg);
					}
				}
			});
		}
	});

}
function changeQuestionVisible(chosenQuestionOrNot, questionId) {

	var url = 'qacenter/changevisibleQuestionOrNot/';
	$.ajax({
		url : url,
		type : 'POST',
		data : {
			"questionId" : questionId,
		},
		error : function(data) {
			$.messager.alert('错误', data.msg);
		},
		success : function(data) {
			if (data.flag) {
				$('#qacenter_table').datagrid('reload');
			} else {
				$.messager.alert('错误', data.msg);
			}
		}
	});

}
function changeChosenQuestionOrNot(questionId) {
	var url = 'qacenter/changeChosenQuestionOrNot/';
	$.ajax({
		url : url,
		type : 'POST',
		data : {
			"questionId" : questionId,
		},
		error : function(data) {
			$.messager.alert('错误', data.msg);
		},
		success : function(data) {
			if (data.flag) {
				$('#qacenter_table').datagrid('reload');
			} else {
				$.messager.alert('错误', data.msg);
			}

		}
	});

}
function deleteInvisibleQuestion(questionId) {
	$.messager.confirm('确认', '您确认要 删除这条被屏蔽的记录吗？删除后将不能恢复.', function(r) {
		if (r) {
			var url = 'qacenter/deleteInvisibleQuestion/';
			$.ajax({
				url : url,
				type : 'POST',
				data : {
					"questionId" : questionId,
				},
				error : function(data) {
					$.messager.alert('错误', data.msg);
				},
				success : function(data) {
					if (data.flag) {
						$('#qacenter_table').datagrid('reload');
					} else {
						$.messager.alert('错误', data.msg);
					}

				}
			});
		}
	});
}
function batchUpdateQuestionVisible() {
	var selections = $('#qacenter_table').datagrid('getChecked');
	for (var int = 0; int < selections.length; int++) {
		if (selections[int].visibleQuestionOrNot == 0) {
			$.messager.alert('警告', '勾选项中存在未屏蔽问题！');
			return;
		}
	}
	if (eoc.cms.main.isEmpty(selections)) {
		$.messager.alert('警告', '请至少勾选一条记录！', function() {
			return;
		});
	} else {
		$.messager.confirm('确认', '您确认要全部取消屏蔽选中记录吗？', function(r) {
			if (r) {
				var idArray = new Array();
				for (var int = 0; int < selections.length; int++) {
					var array_element = selections[int];
					idArray.push(array_element.questionId);
				}
				var url = 'qacenter/batchUpdateVisibleQuestionOrNot/';
				$.ajax({
					url : url,
					type : 'POST',
					data : {
						"questionIds" : idArray,
						"updateType" : 1,
					},
					error : function(data) {
						$.messager.alert('错误', data.msg);
					},
					success : function(data) {
						if (data.flag) {
							$('#qacenter_table').datagrid('clearChecked');
							$('#qacenter_table').datagrid('reload');
						} else {
							$.messager.alert('错误', data.msg);
						}

					}
				});
			}

		});
	}

}
function batchUpdateQuestionInvisible() {
	var selections = $('#qacenter_table').datagrid('getChecked');
	for (var int = 0; int < selections.length; int++) {
		if (selections[int].visibleQuestionOrNot == 1) {
			$.messager.alert('警告', '勾选项中存在已屏蔽问题！');
			return;
		}
	}
	if (eoc.cms.main.isEmpty(selections)) {
		$.messager.alert('警告', '请至少勾选一条记录！', function() {
			return;
		});
	} else {
		$.messager.confirm('确认', '您确认要全部屏蔽选中记录吗？', function(r) {
			if (r) {
				var idArray = new Array();
				for (var int = 0; int < selections.length; int++) {
					var array_element = selections[int];
					idArray.push(array_element.questionId);
				}
				var url = 'qacenter/batchUpdateVisibleQuestionOrNot/';
				$.ajax({
					url : url,
					type : 'POST',
					data : {
						"questionIds" : idArray,
						"updateType" : 2,
					},
					error : function(data) {
						$.messager.alert('错误', data.msg);
					},
					success : function(data) {
						if (data.flag) {
							$('#qacenter_table').datagrid('clearChecked');
							$('#qacenter_table').datagrid('reload');
						} else {
							$.messager.alert('错误', data.msg);
						}

					}
				});
			}
		});
	}

}
function showQuestionDital(questionId) {
	var url = 'qacenter/showQuestionDital/?questionId=' + questionId;
	$('#popupWindowOfQuestionDital').dialog({
		width : 750,
		height : 600,
		title : '问题详情',
		href : url,
		closable : true,
		collapsible : false,
		minimizable : false,
		maximizable : false,
		doSize : true,
		closed : false,
		resizable : false,
		draggable : true,
		modal : true,
		loadingMessage : "BUFF全开!!努力加载ing..."
	});
}

$(function($) {
	$('#searchBtnS').bind(
			'click',
			function() {
				var timeFromString = $("#timeFromS").datebox('getValue');
				var timeToString = $('#timeToS').datebox('getValue');
				if (!eoc.cms.main.isEmpty(timeFromString)
						&& !eoc.cms.main.isEmpty(timeToString)) {
					var timeFrom = $.fn.datebox.defaults
							.parser(timeFromString);
					var timeTo = $.fn.datebox.defaults
							.parser(timeToString);
					if (timeTo < timeFrom) {
						$.messager.alert('错误', '开始日期不能大于结束日期!');
						return;
					}
				}
				setSearchingParamters();
			});
	$('#resetBtnS').bind(
			'click',
			function() {
				$("#searchForm").form('reset');
				$('#professionIdS').combobox('reload',
						'qacenter/getProfessionListJson');
				$('#jobGroupIdS').combobox('reload',
						'qacenter/getJobGroupListJson?id=0');
			});

});
