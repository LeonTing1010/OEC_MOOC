/**
 * @author jianhua.huang
 * @since 2014-03-29 17:30
 * @description 行业，岗位群，岗位信息操作类
 */
var eoc = eoc || {};
eoc.cms = eoc.cms || {};
eoc.cms.professionJob = function() {
	// init page
};
// 格式化操作
eoc.cms.professionJob.formatterOperator = function(value,row,index){
	if(eoc.cms.main.isEmpty(row.jobID)){
		var retStr = '';
		var s = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.viewProfessionFunc('+row.proID+');">查看</a> ';
	    var d = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.modifyProfessionFunc('+row.proID+');">编辑</a> ';
	    var e = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.addJobGroupFunc('+row.proID+')">添加岗位群</a> ';
	    var f = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.deleteProfessionFunc('+row.proID+')">删除</a> ';
	    var g = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.setVisableOrHideProfessionFunc('+row.proID+','+row.proIsVisible+')">隐藏</a>';
	    var h = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.setVisableOrHideProfessionFunc('+row.proID+','+row.proIsVisible+')">显示</a>';
	    retStr = d+"&nbsp;&nbsp;"+s+"&nbsp;&nbsp;"+e;
	    if(row.proIsVisible == '0'){
	    	retStr += "&nbsp;&nbsp;"+g;
	    }else{
	    	retStr += "&nbsp;&nbsp;"+h;
	    }
	    retStr += "&nbsp;&nbsp;"+f;
	    return retStr;
	}else if(!eoc.cms.main.isEmpty(row.jobPID)){
		var retStr = '';
		var s = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.modifyJobFunc('+row.jobID+');">编辑</a> ';  
	    var d = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.viewJobFunc('+row.jobID+')">查看</a> ';
	    var e = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.recommendJob('+row.jobID+','+row.jobRecommend+')">推荐</a> ';
	    var c = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.recommendJob('+row.jobID+','+row.jobRecommend+')">取消推荐</a> ';
	    var f = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.deleteJobFunc('+row.jobID+')">删除</a> ';
	    var g = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.setVisableOrHideJobFunc('+row.jobID+','+row.jobPID+','+row.jobIsVisible+')">隐藏</a>';
	    var h = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.setVisableOrHideJobFunc('+row.jobID+','+row.jobPID+','+row.jobIsVisible+')">显示</a>';
	    if(row.jobRecommend=='1'){
	    	retStr = s+"&nbsp;&nbsp;"+d+"&nbsp;&nbsp;"+c;
	    }else{
	    	retStr = s+"&nbsp;&nbsp;"+d+"&nbsp;&nbsp;"+e;
	    }
	    if(row.jobIsVisible == '0'){
	    	retStr += "&nbsp;&nbsp;"+g;
	    }else{
	    	retStr += "&nbsp;&nbsp;"+h;
	    }
	    retStr += "&nbsp;&nbsp;"+f;
	    return retStr;
	}else{
		var retStr = '';
		var s = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.modifyJobGroupFunc('+row.jobID+');">编辑</a> ';
		var e = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.viewJobGroupFunc('+row.jobID+')">查看</a> ';
		var d = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.addChildJobFunc('+row.realProID+','+row.jobID+')">添加岗位</a> ';
	    var f = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.deleteJobFunc('+row.jobID+')">删除</a> ';
	    var g = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.recommendJob('+row.jobID+')">推荐</a> ';
	    var h = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.setVisableOrHideJobFunc('+row.jobID+','+row.jobPID+','+row.jobIsVisible+')">隐藏</a>';
	    var i = '<a href="javascript:void(0);" onclick="eoc.cms.professionJob.setVisableOrHideJobFunc('+row.jobID+','+row.jobPID+','+row.jobIsVisible+')">显示</a>';
	    if(row.jobRecommend=='1'){
	    	retStr = s+"&nbsp;&nbsp;"+e+"&nbsp;&nbsp;"+d;
	    }else{
	    	retStr = s+"&nbsp;&nbsp;"+e+"&nbsp;&nbsp;"+d;
	    }
	    if(row.jobIsVisible == '0'){
	    	retStr += "&nbsp;&nbsp;"+h;
	    }else{
	    	retStr += "&nbsp;&nbsp;"+i;
	    }
	    retStr += "&nbsp;&nbsp;"+f;
	    return retStr;
	}
};
// 格式化是行业或者岗位群或者岗位描述
eoc.cms.professionJob.formatterProDesc = function(value,row,index){
	if(!eoc.cms.main.isEmpty(row.proDescription)){
		return row.proDescription;
	}else if(!eoc.cms.main.isEmpty(row.jobDescription)){
		return row.jobDescription;
	}else if(!eoc.cms.main.isEmpty(row.jobDetail)){
		return row.jobDetail;
	}else{
		return value;
	}
};
// 格式化是行业或者岗位群或者岗位名称
eoc.cms.professionJob.formatterProName = function(value,row,index){
	if(eoc.cms.main.isEmpty(row.proName)){
		return row.jobName;
	}else{
		return value;
	}
};
// 格式化是隐藏：
eoc.cms.professionJob.formatterProIsVisible = function(value,row,index){
	// 0:公开.1:隐藏
	var _val = '';
	if(row.proIsVisible==undefined || row.proIsVisible == null){
		_val = row.jobIsVisible;
	}else{
		_val = row.proIsVisible;
	}
	if (_val=='1'){
		return "隐藏中";
	} else if(_val=='0'){
		return "正常";
	} else {
		return "-";
	}
};
// 格式化是否推荐：1.推荐 ; 其他，不推荐
eoc.cms.professionJob.formatterRecommend = function(value,row,index){
	if(row.jobID==undefined){
		return " - ";
	}
	if(row.jobID!=undefined && row.jobPID <= 0){
		return " - ";
	}
	var _val = '';
	if(eoc.cms.main.isEmpty(row.proRecommend)){
		_val = row.jobRecommend;
	}else{
		_val = row.proRecommend;
	}
	if (_val=='1'){
		return "推荐";
	} else {
		return "不推荐";
	}
};
// 格式化utime
eoc.cms.professionJob.formatterUTime = function(value,row,index){
	var _val = '';
	if(!eoc.cms.main.isEmpty(row.jobUtime)){
		_val = row.jobUtime;
	}else if(!eoc.cms.main.isEmpty(row.proUtime)){
		_val = row.proUtime;
	}else{
		_val = value;
	}
	if(_val){
			return formatDate(_val);  
	}else{
		return _val;
	}
};
// 格式化ctime
eoc.cms.professionJob.formatterCTime = function(value,row,index){
	var _val = '';
	if(!eoc.cms.main.isEmpty(row.jobCtime)){
		_val = row.jobCtime;
	}else if(!eoc.cms.main.isEmpty(row.proCtime)){
		_val = row.proCtime;
	}else{
		_val = value;
	}
	if(_val){
			return formatDate(_val);  
	}else{
		return _val;
	}
};
// 行业分类右击context menu
eoc.cms.professionJob.onContextMenu = function (e, row){
	e.preventDefault();
    $(this).treegrid('select', row.proID);
    $('#contextmenuDiv').menu('show',{
        left: e.pageX,
        top: e.pageY
    });
};
// 查询行业分类
eoc.cms.professionJob.queryForm = function(){
	var _pform = $("form#professionForm");
	var _proName_gp = _pform.find("input[name=proName_gp]").val();
	var _proDescription_gp = _pform.find("input[name=proDescription_gp]").val();
	var _startDate_gte = _pform.find("input[name=startDate_gte]").val();
	var _endDate_lt = _pform.find("input[name=endDate_lt]").val();
	
	var timeFromString = _pform.find("input[name=startDate_gte]").val();
	var timeToString = _pform.find("input[name=endDate_lt]").val();
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
	
	var _queryParams = $('#AllProfessionJobTreeGrid').treegrid('options').queryParams;
    $('#AllProfessionJobTreeGrid').treegrid({
    	queryParams: {
    		'proName_gp' : _proName_gp,
    		'proDescription_gp' : _proDescription_gp,
    		'startDate_gte' : _startDate_gte,
    		'endDate_lt' : _endDate_lt,
    		'pageIndex' : _page.pageIndex,
    		'pageSize' : _page.pageSize 
    	}
    });
    
    // 设置分页控件 {pageSize: _page.pageSize,pageList: _page.pageList};
    setPaginationComponent(_page);
};
// 重置form
eoc.cms.professionJob.resetForm = function(){
	$('form#professionForm').form('reset');
};
// 初始化Dialog
eoc.cms.professionJob.initDialogFunc = function(options){
	var _id = options.Did;
	var _width = options.width || 600 ;
	var _height = options.height || 500 ;
	var _saveCallBack = options.saveCallBack || function(){};
	var _closeCallBack = options.closeCallBack || function(){};
	var _buttons = options.buttons || [{
		text:'保存',
		handler:function(){
			var _validate = _saveCallBack && _saveCallBack();
			if(_validate){
				_self.dialog('close');
			}
		}
	},{
		text:'关闭',
		handler:function(){
			_closeCallBack && _closeCallBack();
			_self.dialog('close');
		}
	}];
	var _self = $('#'+_id);
	if(_self && _self.children().length>0){
		_self.dialog('open');
		_self.dialog('refresh', options.href);
	}else{
		_self.dialog({
		    title: options.title,
		    width: _width,
		    height: _height,
		    closed: false,
		    cache: false,
		    href: options.href,
		    modal: true,
			buttons:_buttons
		});
	}
	return _self;
};
// 设置岗位群和岗位的隐藏或者显示功能
eoc.cms.professionJob.setVisableOrHideJobFunc = function(jobID,jobPID,jobIsVisible){
	// 设置值
	var setValue = function(){
		// 是否隐藏.0:公开.1:隐藏
		var _val = '0';
		if(jobIsVisible=='1'){
			_val = '0';
		}else{
			_val = '1';
		}
		return _val;
	};
	// 与后台交互的callback
	var retCallback = function(result){
		var result = eval('('+result+')');
        if (result.success){
        	self.treegrid('reload');// reload the tree data
            $.messager.show({
                title: '成功',
                msg: result.retMsg
            });
        } else {
        	$.messager.show({
                title: '失败',
                msg: result.errorMsg
            });
        }
	};
	var isNotNull = function(str){
		return str!=null&&str!=undefined&&str!='';
	};
	// 进行ajax操作
	var ajaxOperate = function(){
		var url = '';
		if(!isNotNull(jobPID) && isNotNull(jobID)){// 岗位群
			url = "profession/setJobIsVisable?jobID="+jobID+"&jobIsVisible="+setValue();
		}else if(isNotNull(jobPID) && isNotNull(jobID)){// 岗位
			url = "profession/setJobIsVisable?jobID="+jobID+"&jobPID="+jobPID+"&jobIsVisible="+setValue();
		}
    	$.post(url,function(result){retCallback(result);});
	};
	var _popMsg = "";
	if(jobIsVisible=='1'){
		_popMsg = "确认要进行显示操作?";
	}else{
		if(jobPID!=null&&jobPID!=undefined&&jobPID!=''){
			_popMsg = "隐藏后，该岗位群以及其包含岗位都会被隐藏?";
		}else if(jobID!=null&&jobID!=undefined&&jobID!=''){
			_popMsg = "隐藏后，该岗位都会被隐藏?";
		}
	}
	$.messager.confirm('操作', _popMsg, function(r){
    	if (r){
        	ajaxOperate();
        }
    });
};
// 设置行业的隐藏或者显示功能
eoc.cms.professionJob.setVisableOrHideProfessionFunc = function(proID,proIsVisible){
	// 设置值
	var setValue = function(){
		// 是否隐藏.0:公开.1:隐藏
		var _val = '0';
		if(proIsVisible=='1'){
			_val = '0';
		}else{
			_val = '1';
		}
		return _val;
	};
	// 与后台交互的callback
	var retCallback = function(result){
		var result = eval('('+result+')');
        if (result.success){
        	self.treegrid('reload');// reload the tree data
            $.messager.show({
                title: '成功',
                msg: result.retMsg
            });
        } else {
        	$.messager.show({
                title: '失败',
                msg: result.errorMsg
            });
        }
	};
	// 进行ajax操作
	var ajaxOperate = function(){
		var url = "profession/setProfessionIsVisable?proID="+proID+"&proIsVisible="+setValue();
    	$.post(url,function(result){retCallback(result);});
	};
	var _popMsg = "";
	if(proIsVisible=='1'){
		_popMsg = "确认要进行显示操作?";
	}else{
		_popMsg = "隐藏行业后，该行业下包含的所有岗位群以及其包含岗位都会被隐藏?";
	}
	$.messager.confirm('操作', _popMsg, function(r){
    	if (r){
        	ajaxOperate();
        }
    });
};
// 删除行业
eoc.cms.professionJob.deleteProfessionFunc = function(proID){
	var url = 'profession/deleteProfession?proID='+proID;
	$.post(url,
        function (result) {
    		var result = eval('('+result+')');
            if (result.success){
            	var _msg = '';
            	if(result.children>0){
            		_msg = '有子节点'+result.children+'个, 确认是否删除这些信息?';
            	}else{
            		_msg = '确认是否删除该信息?';
            	}
                $.messager.confirm('删除', _msg, function(r){
                    if (r){
                        var url = 'profession/deleteProfession?proID='+proID+"&deleteFlag=true";
                    	$.post(url,function(result){
                    		var result = eval('('+result+')');
        		            if (result.success=='true'){
        		            	self.treegrid('reload');// reload the tree data
        		                $.messager.show({
        		                    title: '成功',
        		                    msg: '删除成功!'
        		                });
        		            } else {
        		            	$.messager.show({
        		                    title: '失败',
        		                    msg: result.errorMsg
        		                });
        		            }
                    	});
                    }
                });
            }else{
            	$.messager.show({
                    title: '失败',
                    msg: result.errorMsg
                });
            }
    	});
};
// 删除岗位群和岗位信息
eoc.cms.professionJob.deleteJobFunc = function(jobID){
	var url = 'profession/deleteJob?jobID='+jobID;
	$.post(url,
        function (result) {
    		var result = eval('('+result+')');
            if (result.success){
            	var _msg = '';
            	if(result.children>0){
            		_msg = '有子节点'+result.children+'个, 确认是否删除这些信息?';
            	}else{
            		_msg = '确认是否删除该信息?';
            	}
                $.messager.confirm('删除', _msg, function(r){
                    if (r){
                        var url = 'profession/deleteJob?jobID='+jobID+"&deleteFlag=true";
                    	$.post(url,function(result){
                    		var result = eval('('+result+')');
        		            if (result.success){
        		            	self.treegrid('reload');// reload the tree data
        		                $.messager.show({
        		                    title: '成功',
        		                    msg: '删除成功!'
        		                });
        		            } else {
        		            	$.messager.show({
        		                    title: '失败',
        		                    msg: result.errorMsg
        		                });
        		            }
                    	});
                    }
                });
            }else{
            	$.messager.show({
                    title: '失败',
                    msg: result.errorMsg
                });
            }
    	});
};
// 增加岗位信息
eoc.cms.professionJob.addChildJobFunc = function(ProID,jobID){
	var _options = {
		'Did':'professionJob_Add_Dialog',
		'title':'信息',
		'href':'profession/addInitJob?jobPID='+jobID+"&proID="+ProID,
		'width': 600,
		'height': 600,
		'saveCallBack':function(){
			var url = "profession/saveOrUpdateJob";
			var _addForm = $('form#addSaveOrUpdateJobForm');
			var _val = _addForm.form('validate');
			var _jobName = _addForm.find("input[name=jobName_op]").val();
			var _jobCollectCount = _addForm.find("input[name=jobCollectCount_op]").val();
			var _jobDetail = _addForm.find("textarea[name=jobDetail_op]").val();
			/*
			 * var _jobDevelpping =
			 * _addForm.find("textarea[name=jobDevelpping_op]").val();
			 */
			var _jobDevelpping = _addForm.find("input[name=jobDevelpping_op]").val();
			var _jobImage = _addForm.find("input[name=jobImage_op]").val();
			var _jobDescription = _addForm.find("textarea[name=jobDescription_op]").val();
			var _jobDuty = _addForm.find("textarea[name=jobDuty_op]").val();
			var _jobDemand = _addForm.find("textarea[name=jobDemand_op]").val();
			var _jobWage = _addForm.find("input[name=jobWage_op]").val();
			var _jobLevel = _addForm.find("input[name=jobLevel_op]").val();
			var _
			// var _jobRecommend =
			// _addForm.find("input[name=jobRecommend]").val();
			var _f = _val; 
			/*
			 * && !eoc.cms.main.isEmpty(_jobName) &&
			 * !eoc.cms.main.isEmpty(_jobCollectCount) &&
			 * !eoc.cms.main.isEmpty(_jobDetail) &&
			 * !eoc.cms.main.isEmpty(_jobDevelpping) &&
			 * !eoc.cms.main.isEmpty(_jobDescription) &&
			 * !eoc.cms.main.isEmpty(_jobDuty) &&
			 * !eoc.cms.main.isEmpty(_jobDemand) &&
			 * !eoc.cms.main.isEmpty(_jobWage) &&
			 * !eoc.cms.main.isEmpty(_jobLevel);
			 */
			if(!_f){				
				return false;
			}
			_addForm.form('submit',{
		        url: url,
		        onSubmit: function(){
		            return true;
		        },
		        success: function(result){
		            var result = eval('('+result+')');
		            if (result.success){
		            	self.treegrid('reload');// reload the tree data
		                $.messager.show({
		                    title: '成功',
		                    msg: '保存成功!'
		                });
		            } else {
		            	$.messager.show({
		                    title: '失败',
		                    msg: result.errorMsg
		                });
		            }
		        }
		    });
			return true;
		}
	};
	eoc.cms.professionJob.initDialogFunc(_options);
};
// 推荐岗位信息
eoc.cms.professionJob.recommendJob = function(jobID,jobRecommend){
	// 设置值
	var setValue = function(){
		// 是否隐藏.0:推荐.1:取消推荐
		var _val = '0';
		if(jobRecommend=='1'){
			_val = '0';
		}else{
			_val = '1';
		}
		return _val;
	};
	if(jobRecommend=='1'){
		var _popMsg = '确认是否取消推荐该岗位?';
	}else{
		var _popMsg = '确认是否推荐该岗位?';
	}
	$.messager.confirm('岗位推荐', _popMsg, function(r){
        if (r){
            // alert('confirmed: '+r+" jobID: "+jobID);
            var url = 'profession/recommendJob?jobID='+jobID+"&jobRecommend="+setValue();
			$.post(
		    	url,
		        function (result) {
		    		var result = eval('('+result+')');
		            if (result.success){
		            	self.treegrid('reload');// reload the tree data
		                $.messager.show({
		                    title: '成功',
		                    msg: '操作成功!'
		                });
		            } else {
		            	$.messager.show({
		                    title: '失败',
		                    msg: result.errorMsg
		                });
		            }
		    	});
        }
    });
};

// 查看岗位信息
eoc.cms.professionJob.viewJobFunc = function(jobID){
	var _d = null;
	var _options = {
		'Did':'professionJob_View_Dialog',
		'width': 600,
		'height': 600,
		'title':'信息',
		'href':'profession/viewJob?jobID='+jobID,
		'buttons' : [{
			text:'关闭',
			handler:function(){
				_d.dialog('close');
			}
		}]
	};
	_d = eoc.cms.professionJob.initDialogFunc(_options);
};
// 修改岗位信息
eoc.cms.professionJob.modifyJobFunc = function(jobID){
	var _options = {
		'Did':'professionJob_Add_Dialog',
		'title':'信息',
		'href':'profession/addInitJob?jobID='+jobID,
		'width': 600,
		'height': 600,
		'saveCallBack':function(){
			var url = "profession/saveOrUpdateJob";
			var _addForm = $('form#addSaveOrUpdateJobForm');
			var _val = _addForm.form('validate');
			var _jobName = _addForm.find("input[name=jobName_op]").val();
			var _jobCollectCount = _addForm.find("input[name=jobCollectCount_op]").val();
			var _jobDetail = _addForm.find("textarea[name=jobDetail_op]").val();
			var _jobDevelpping = _addForm.find("textarea[name=jobDevelpping_op]").val();
			var _jobDescription = _addForm.find("textarea[name=jobDescription_op]").val();
			var _jobDuty = _addForm.find("textarea[name=jobDuty_op]").val();
			var _jobDemand = _addForm.find("textarea[name=jobDemand_op]").val();
			var _jobWage = _addForm.find("input[name=jobWage_op]").val();
			var _jobLevel = _addForm.find("input[name=jobLevel_op]").val();
			// var _jobRecommend =
			// _addForm.find("input[name=jobRecommend]").val();
			var _f = _val; 
			/*
			 * && !eoc.cms.main.isEmpty(_jobName) &&
			 * !eoc.cms.main.isEmpty(_jobCollectCount) &&
			 * !eoc.cms.main.isEmpty(_jobDetail) &&
			 * !eoc.cms.main.isEmpty(_jobDevelpping) &&
			 * !eoc.cms.main.isEmpty(_jobDescription) &&
			 * !eoc.cms.main.isEmpty(_jobDuty) &&
			 * !eoc.cms.main.isEmpty(_jobDemand) &&
			 * !eoc.cms.main.isEmpty(_jobWage) &&
			 * !eoc.cms.main.isEmpty(_jobLevel);
			 */
			if(!_f){				
				return false;
			}
			_addForm.form('submit',{
		        url: url,
		        onSubmit: function(){
		            return true;
		        },
		        success: function(result){
		            var result = eval('('+result+')');
		            if (result.success){
		            	self.treegrid('reload');// reload the tree data
		                $.messager.show({
		                    title: '成功',
		                    msg: '保存成功!'
		                });
		            } else {
		            	$.messager.show({
		                    title: '失败',
		                    msg: result.errorMsg
		                });
		            }
		        }
		    });
			return true;
		}
	};
	eoc.cms.professionJob.initDialogFunc(_options);
};
// 查看岗位信息
eoc.cms.professionJob.viewJobGroupFunc = function(jobID){
	var _d = null;
	var _options = {
		'Did':'professionJob_View_Dialog',
		'width': 600,
		'height': 400,
		'title':'信息',
		'href':'profession/viewJob?jobID='+jobID,
		'buttons' : [{
			text:'关闭',
			handler:function(){
				_d.dialog('close');
			}
		}]
	};
	_d = eoc.cms.professionJob.initDialogFunc(_options);
};
// 修改岗位群信息
eoc.cms.professionJob.modifyJobGroupFunc = function(jobID){
	var _options = {
			'Did':'professionJob_Add_Dialog',
			'title':'信息',
			'href':'profession/editJobGroup?jobID='+jobID,
			'width': 600,
			'height': 400,
			'saveCallBack':function(){
				var url = "profession/saveOrUpdateJob";
				var _addForm = $('form#addSaveOrUpdateJobForm');
				var _val = _addForm.form('validate');
				var _jobName = _addForm.find("input[name=jobName_op]").val();
				var _jobDetail = _addForm.find("textarea[name=jobDetail_op]").val();
				var _f = _val && !eoc.cms.main.isEmpty(_jobName);
				// && !eoc.cms.main.isEmpty(_jobDetail);
				if(!_f){				
					return false;
				}
				_addForm.form('submit',{
			        url: url,
			        onSubmit: function(){
			            return true;
			        },
			        success: function(result){
			            var result = eval('('+result+')');
			            if (result.success){
			            	self.treegrid('reload');// reload the tree data
			                $.messager.show({
			                    title: '成功',
			                    msg: '保存成功!'
			                });
			            } else {
			            	$.messager.show({
			                    title: '失败',
			                    msg: result.errorMsg
			                });
			            }
			        }
			    });
				return true;
			}
		};
		eoc.cms.professionJob.initDialogFunc(_options);
};
// 增加岗位群信息
eoc.cms.professionJob.addJobGroupFunc = function(proID){
	var _options = {
		'Did':'professionJob_Add_Dialog',
		'title':'信息',
		'href':'profession/addInitJobGroup?proID='+proID,
		'width': 600,
		'height': 400,
		'saveCallBack':function(){
			var url = "profession/saveOrUpdateJob";
			var _addForm = $('form#addSaveOrUpdateJobForm');
			var _val = _addForm.form('validate');
			var _jobName = _addForm.find("input[name=jobName_op]").val();
			var _jobDetail = _addForm.find("input[name=jobDetail_op]").val();
			var _f = _val && !eoc.cms.main.isEmpty(_jobName);// &&
																// !eoc.cms.main.isEmpty(_jobDetail);
			if(!_f){				
				return false;
			}
			_addForm.form('submit',{
		        url: url,
		        onSubmit: function(){
		            return true;
		        },
		        success: function(result){
		            var result = eval('('+result+')');
		            if (result.success){
		            	self.treegrid('reload');// reload the tree data
		                $.messager.show({
		                    title: '成功',
		                    msg: '保存成功!'
		                });
		            } else {
		            	$.messager.show({
		                    title: '失败',
		                    msg: result.errorMsg
		                });
		            }
		        }
		    });
			return true;
		}
	};
	eoc.cms.professionJob.initDialogFunc(_options);
};
// 增加行业分类
eoc.cms.professionJob.addProfessionFunc = function(){console.log(this);
	var _options = {
		'Did':'profession_View_Dialog',
		'title':'信息',
		'href':'profession/addInitProfession',
		'width': 450,
		'height': 400,
		'saveCallBack':function(){
			var url = "profession/saveOrUpdateProfession";
			var _addForm = $('form#addSaveOrUpdateProfessionForm');
			var _val = _addForm.form('validate');
			var _PFN = _addForm.find("input[name=proName]").val();
			var _PFD = _addForm.find("textarea[name=proDescription]").val();
			var _f = _val && !eoc.cms.main.isEmpty(_PFN);
			if(!_f){				
				return false;
			}
			_addForm.form('submit',{
		        url: url,
		        onSubmit: function(){
		            return true;
		        },
		        success: function(result){
		            var result = eval('('+result+')');
		            if (result.success){
		            	self.treegrid('reload');// reload the user data
		                $.messager.show({
		                    title: '成功',
		                    msg: '保存成功!'
		                });
		            } else {
		            	$.messager.show({
		                    title: 'Error',
		                    msg: result.errorMsg
		                });
		            }
		        }
		    });
			return true;				
		}
	};
	eoc.cms.professionJob.initDialogFunc(_options);
};
// 查看，展示行业信息
eoc.cms.professionJob.viewProfessionFunc = function(id){
	var _d = null;
	var _options = {
		'Did':'professionJob_View_Dialog',
		'width': 450,
		'height': 400,
		'title':'信息',
		'href':'profession/viewProfession?proID='+id+"&modify=false",
		'buttons' : [{
			text:'关闭',
			handler:function(){
				_d.dialog('close');
			}
		}]
	};
	_d = eoc.cms.professionJob.initDialogFunc(_options);
};
// 修改行业信息
eoc.cms.professionJob.modifyProfessionFunc = function(id){
	var _options = {
		'Did':'professionJob_Modify_Dialog',
		'width': 450,
		'height': 400,
		'title':'信息',
		'href':'profession/viewProfession?proID='+id+"&modify=true",
		'saveCallBack':function(){
			var url = "profession/saveOrUpdateProfession";
			var _addForm = $('form#editSaveOrUpdateProfessionForm');
			var _val = _addForm.form('validate');
			var _PFN = _addForm.find("input[name=proName]").val();
			var _PFD = _addForm.find("textarea[name=proDescription]").val();
			var _f = _val && !eoc.cms.main.isEmpty(_PFN);// &&
															// !eoc.cms.main.isEmpty(_PFD);
			if(!_f){				
				return false;
			}
			_addForm.form('submit',{
		        url: url,
		        onSubmit: function(){
		            return true;
		        },
		        success: function(result){
		            var result = eval('('+result+')');
		            if (result.success){
		            	self.treegrid('reload');// reload the user data
		                $.messager.show({
		                    title: '成功',
		                    msg: '保存成功!'
		                });
		            } else {
		            	$.messager.show({
		                    title: 'Error',
		                    msg: result.errorMsg
		                });
		            }
		        }
		    });
			return true;
		}
	};
	eoc.cms.professionJob.initDialogFunc(_options);
};
// 初始化推荐信息by options
eoc.cms.professionJob.initProCommentCommboxFuncOption = function(options){
	var _Did = options.id;
	if(eoc.cms.main.isEmpty(_Did)){
		return ;
	}
	var _onLoadSuccessFunc = options.onLoadSuccess || function (data) {
		if (data) {
			$('#'+id).combobox('setValue',data[0].value);
		}
	};
	var _onSelectFunc = options.onSelect || function(record){
		$('#'+id).combobox('setValue',record.value);
	};
	var _data = options.data || [{
		label: '---请选择---',
		value: ''
	},{
		label: '推荐',
		value: '1'
	},{
		label: '不推荐',
		value: '0'
	}];
	
	$("#"+_Did).combobox({
		valueField: 'value',
		textField: 'label',
		panelHeight:'auto',
		width: 190,
		editable:false,
		onLoadSuccess : _onLoadSuccessFunc,
		onSelect : _onSelectFunc,
		data : _data
	});
}
// 初始化推荐信息
eoc.cms.professionJob.initProCommendCommboxFunc = function(id){
	if(eoc.cms.main.isEmpty(id)){
		return ;
	}
	$("#"+id).combobox({
		valueField: 'value',
		textField: 'label',
		panelHeight:'auto',
		width: 190,
		editable:false,
		onLoadSuccess: function (data) {
			if (data) {
				$('#'+id).combobox('setValue',data[0].value);
			}
		},
		onSelect:function(record){
			$('#'+id).combobox('setValue',record.value);
		},
		data: [{
			label: '---请选择---',
			value: ''
		},{
			label: '推荐',
			value: '1'
		},{
			label: '不推荐',
			value: '0'
		}]
	});
};
// 初始化日期box
eoc.cms.professionJob.initDatebox = function(){
	$('input[name=startDate_gte]').datebox({
		width : 130,
		editable : false,
		buttons : dateboxbuttons

	});
	$('input[name=endDate_lt]').datebox({
		width : 130,
		editable : false,
		buttons : dateboxbuttons
	});
};
// ********初始化信息***********/
// tree grid
var _panelHeight = 80;
var self = $('#AllProfessionJobTreeGrid').treegrid({
	iconCls:'icon-edit',  
	title:'职业分类管理',
	width: eoc.cms.main.tabsVolume.width,  
	height: eoc.cms.main.tabsVolume.height-_panelHeight,  
    url:'profession/listAllProfession',  
    idField:'proID',
    rownumbers: false,
    pagination: true,
    treeField:'proName',
    striped : true,
    collapsible : true,
    animate : true,
    onContextMenu : eoc.cms.professionJob.onContextMenu,
    columns:[[  
        {field:'proName',title:'名称',width:220,align:'left',formatter: eoc.cms.professionJob.formatterProName},  
        {field:'proDescription',title:'描述',width:350,align:'left',formatter : eoc.cms.professionJob.formatterProDesc},   
        {field:'proRecommend',title:'状态',width:120,align:'center',formatter: eoc.cms.professionJob.formatterRecommend},
        {field:'proIsVisible',title:'是否隐藏',width:120,align:'center',formatter: eoc.cms.professionJob.formatterProIsVisible},
        {field:'proCtime',title:'创建时间',width:160,align:'center',formatter: eoc.cms.professionJob.formatterCTime},
        /*
		 * {field:'proUtime',title:'更新时间',width:160,align:'center',formatter:
		 * eoc.cms.professionJob.formatterUTime},
		 */
        {field:'opt',title:'操作',width:200,formatter : eoc.cms.professionJob.formatterOperator}
    ]],
    onBeforeLoad: function(row,param){
    	if (row) {
    		param.proID_gp = row.proID;
	    	param.action_gp = 'children'
	    	if(!eoc.cms.main.isEmpty(row.jobID)){
	    		param.proID_gp = row.realProID;
	    		param.jobPID_gp = row.jobID;
	    	}
    	}
	},
	rowStyler: function(row,index){
		if(!eoc.cms.main.isEmpty(row.proID)&&eoc.cms.main.isEmpty(row.jobID)){// 行业
			return 'background-color:#eee;';// background-color:#EA0000;
		}else if(!eoc.cms.main.isEmpty(row.jobID)&&eoc.cms.main.isEmpty(row.jobPID)){// 岗位群
			return 'background-color:#eee;';// background-color:#004B97; #FFE6D9
		}else if(!eoc.cms.main.isEmpty(row.jobID)&&!eoc.cms.main.isEmpty(row.jobPID)){// 岗位
			return 'background-color:#d4e6eb;';// background-color:#BB3D00;
		}else{
			return '';// #eee,#f2f2f2,选中：#d4e6eb 13467555432
		}
	},
	toolbar: [{  
       text: '添加行业',  
       iconCls: 'icon-add',  
       handler: function() {  
			eoc.cms.professionJob.addProfessionFunc();
       }  
    }],    	
	queryParams: {
		'pageIndex': _page.pageIndex,
		'pageSize': _page.pageSize
	}
});
// 初始化行业推荐combox
eoc.cms.professionJob.initProCommendCommboxFunc('proRecommend_Combox');
// 搜索框
$("#searchFormPanel").panel({
	width : eoc.cms.main.tabsVolume.width,  
	height : _panelHeight, 
	title : "职业分类查询"
});
eoc.cms.professionJob.initDatebox();
// 设置分页控件
function setPaginationComponent(_page){
	var _apjtgPageInfo = {pageSize: _page.pageSize,pageList: _page.pageList};
	var _tableJqObj = $('#AllProfessionJobTreeGrid');
	var _apjtgP = _tableJqObj.treegrid('getPager');  
	$(_apjtgP).pagination({  
	    pageSize : eoc.cms.main.isEmpty(_apjtgPageInfo.pageSize) == true ? 20 : _apjtgPageInfo.pageSize,// 每页显示的记录条数，默认为10 
																										// 10
		pageList : eoc.cms.main.isEmpty(_apjtgPageInfo.pageList) == true ? [5,10,15,20,50] : _apjtgPageInfo.pageList,// 可以设置每页记录条数的列表 
																														// [5,10,15]
		beforePageText : '第',// 页数文本框前显示的汉字 
		afterPageText : '页   共 {pages} 页',  
		displayMsg : '当前显示 {from} - {to} 条记录  共 {total} 条记录',
		onSelectPage : function(pPageIndex, pPageSize){
			var queryParams = _tableJqObj.treegrid('options').queryParams;  
	        queryParams.pageIndex = pPageIndex;  
	        queryParams.pageSize = pPageSize;
	        // 重新加载treegrid的数据
	        _tableJqObj.treegrid('reload');
		}
	});
}
setPaginationComponent(_page);
// 收起tree节点
function collapse(){
    var node = self.treegrid('getSelected');
    if (node){
    	self.treegrid('collapse', node.proID);
    }
}
// 展开tree节点
function expand(){
    var node = self.treegrid('getSelected');
    if (node){
    	self.treegrid('expand', node.proID);
    }
}
// 格式化日期
function formatDate(value){
	var time =new Date(value); 
	return time.format("yyyy-MM-dd hh:mm:ss");  
}
