
/**
 * ===========领域弹出层开始================
 */
function popupTeachershineUI(tFrom){
	var  teacherFrom=$("#"+tFrom);
	var jobGroupIds=teacherFrom.find("input[id=jobGroupIdsInput]").val();
	popupteachershine(jobGroupIds,teacherFrom);
}
//初始化行业岗位弹出层
function popupteachershine(jobGroupIdsInput,teacherFrom) {
	$("#alertDiv").empty();
	$("#alertDiv").load( "user/findTeacherProfession?jobGroupIdsInput="+jobGroupIdsInput,function(){
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
		var top="-"+(H/2+yScroll/2)*1.2+"px";
		var left = W/10+"px";
		$("#alertDiv").css({"left":left,"top":top,"display":"block","z-index":"9500","position":"relative"});
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
				$rightdlObjs.each(function() {
					if ($leftddObj.parent("dl").attr("lang")==$(this).attr("lang")) {
						var $leftDl=$leftddObj.parent("dl");
						$(this).append($leftddObj);//append一个jquery对象是移动,而不是复制.
						var  leftNum=$leftDl.find("dd").length;
						$leftDl.find("span").html(leftNum);
						var rightNum=$leftddObj.parent("dl").find("dd").length;
						$leftddObj.siblings("dt").children("span").html(rightNum);
					}
				});
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
				var $rightddObj=$(this).removeClass("current");
				$leftdlObjs.each(function() {
					if ($rightddObj.parent("dl").attr("lang")==$(this).attr("lang")) {
						var $rightDl=$rightddObj.parent("dl");
						$(this).append($rightddObj);//append一个jquery对象是移动,而不是复制.
						var  rightNum=$rightDl.find("dd").length;
						$rightDl.find("span").html(rightNum);
					    var	leftNum=$rightddObj.parent("dl").find("dd").length;
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
				alert("请选择1到3个擅长领域");
			}else{
				var IDs="";
				teacherFrom.find("span[id=selectedShines]").html("已选择擅长领域: ");
				$("#alertDiv .rightBox").find("dd").each(function(){
					teacherFrom.find("span[id=selectedShines]").append($(this).html()+", ");
					IDs=IDs+$(this).siblings("dt").attr("title")+"|"+$(this).attr("id")+",";
				});
				teacherFrom.find("input[id=jobGroupIdsInput]").val(IDs);
				$("#alertDiv").attr('style','display:none;');
			}
		});
	});
}

/**
 * ===========领域弹出层结束================
 */

/**
 * ===========验证相关===============
 */ 
$.extend($.fn.validatebox.defaults.rules, {
    /*必须和某个字段相等*/
	equalTo: {
			        validator:function(value,param){
			            return $(param[0]).val() == value;
			        },
			        message:'两次输入密码不匹配'
			    },
	schoolValidate:{
					validator:function (value,param){
						var school=$(param[0]).combobox('getValue');
						if(school<1){
							return false;
						}
						return true;
					},
					message:'请选择学校'
				},
	userEmailValidate:{
			
				 	validator:function(value,param){
			 			 $.post('user/checkUserFlag',
					                	{userEmail:value},
					                	function(result){
					                		$("#checkFlag").val(result.success);
					                	},'json');
			 			 var checkFlag=$("#checkFlag").val();
					      if(checkFlag=="true"){
					    	  return false;
					      }else{
					    	  return true;
					      }
				 	},
				 	message:'邮箱已存在'
			 },
			 
	 userNameValidate:{
				 	validator:function(value,param){
						 $.post('user/checkUserFlag',
					                	{userName:value},
					                	function(result){
					                		$("#checkFlag").val(result.success);
					                	},'json');
						 var checkFlag=$("#checkFlag").val();
					      if(checkFlag=="true"){
					    	  return false;
					      }else{
					    	  return true;
					      }
				 	},
				 	message:'用户名已存在'
			},
	//编辑的时候验证用户名是否存在
	userNameEditValidate:{
			 	validator:function(value,param){
			 		var userId=param[0];
					 $.post('user/checkUserFlag',
				                	{userName:value,userId:userId},
				                	function(result){
				                		$("#editValName").val(result.success);
				                	},'json');
					 var editValName=$("#editValName").val();
					 if(editValName ==true||editValName=="true"){
				    	  return false;
				      }else{
				      return true;
				     }
			 	},
			 	message:'用户名已存在'
			},
	maxLength: {
		            validator: function(value, param){
		            			value=	value.replace(/(^\s*)|(\s*$)/g, "");
		            			if(value=="" || value==null){
		            				return false;
		            			}
		                        return param[0]>value.length || param[1] >= value.length;     
	                     }, 
	                   message: '请输入{0} ~ {1}位字符.'    
	                 },
	                 
	  safepass: {
	              validator: function (value, param) {
	            	  return   safePassword(value); 
	                     },  
	                     message: ' 密码由字母和数字组成，至少6 位 ' 
	                 }, 
	userMobileValidate:{
		validator: function (value, param) {
			var tst = /^\d+$/.test(value);
			return tst; 
               },  
               message: ' 请输入正确的电话号码 ' 
           },
	    userNameLengthValiate:{
	    	validator: function (value, param) {
	    		   var len = 0;
	    		    for (var i = 0; i < value.length; i++) {
	    		        if (value[i].match(/[^\x00-\xff]/ig) != null) //全角
	    		            len += 2;
	    		        else
	    		            len += 1;
	    		    };
	    		   
	    		    return len<=param[0];
	    },
           message: ' 请输入的值在1-{0}个字节之间(一个中文字占2个字节)'
	    },
	    nameStringCheck:{
	    	validator:function(value,param){
	    		   return /^[\Α-\￥]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value); 
	    	},
	    	message:'请输入姓名，可以是中文或英文'
	    }
	    
});
var   safePassword =    function  (value) {
    return     !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/.test(value)); 
}
/**
 * ===========验证相关结束===============
 */ 
/**
 * ===初始化相关==============
 */

var _h = $("#seachForm").height()
	 
	//datagrid初始化  
    $('#list_user_data').datagrid({
        title:'会员管理列表',  
        iconCls:'icon-edit',//图标  
        width: eoc.cms.main.tabsVolume.width,  
        height: eoc.cms.main.tabsVolume.height-_h,
        nowrap: false,  
        striped: true,  
        border: true,  
        collapsible:false,//是否可折叠的  
        //fit: true,//自动大小  
        url:'user/searchUser',  
        //sortName: 'createDate',  
       // sortOrder: 'desc',  
        remoteSort:false,   
        idField:'userId',
        singleSelect:true,//是否单选  
        pagination:true,//分页控件  
        rownumbers:true,//行号  
       /*  frozenColumns:[[  
            {field:'ck',checkbox:true}  
        ]], */
		columns:[[
	  		{field:'userType',title:'身份', width:70,align:'center',formatter: function(value,row,index){
				if (row.userType=='1'){//用户类型：1.学生 2.教师 3.后台管理人员
					return '学生';
				} else if(row.userType=='2'){
					return '老师';
				}else if(row.userType=='3'){
					return '后台管理人员';
				}else{
					return value;
				}
			}},
	  		{field:'userName',title:'用户名', width:200},
	  		{field:'education',title:'学历', width:100,formatter:function(value,row,index){
	  				if(row.education==null || row.education==""){
	  					return;
	  				}
	  				//1、本科  2、博士  3、硕士  4、大专  5、其他
	  				var i=parseInt(row.education);
		  			switch (i)
		  			{
		  			case 1:
		  				return "本科";
		  			  break;
		  			case 2:
		  				return "博士";
		  			  break;
		  			case 3:
		  				return "硕士";
		  			  break;
		  			case 4:
		  				return "大专";
		  			default:
		  				return "其他"
		  			}
	  			}
	  		},
	  		{field:'schName',title:'学校名称', width:200},
	  		{field:'userEmail',title:'邮箱', width:200},
	  		{field:'createDate',title:'注册时间', width:200,formatter:function(value,row,index){
	  			if(value){
	  				var time =new Date(value); 
		  			return time.format("yyyy-MM-dd hh:mm:ss");  
	  			}
	  			
	  		}},
	  		{field:'opt',title:'操作',width:140,formatter : function(value,row,index){
	  			 var e ="";
	  			var d="";
	  			 var v="";
	  			var date= $("#list_user_data").datagrid("getData");
	  			var teacheIsrcIsze=date.teahcerIsrcSzie;
	  			if(row.userState=='1'){
	  						e = '<a href="javascript:void(0);" onclick="userStateUpdate(1,'+row.userId+',null,'+row.isRecommended+');">封停</a> ';  
	  			}else{
	  						e = '<a href="javascript:void(0);" onclick="userStateUpdate(2,'+row.userId+',null,'+row.isRecommended+');">恢复</a> ';
	  			}
                if(row.userType=='2'){
	                	if(row.isRecommended==0){
	                		if(teacheIsrcIsze>=10){
	                			d = '<a href="javascript:void(0);"    draggable="true" style="color:#ccc;" >推荐</a> '; 
	                		}else{
	                			d = '<a href="javascript:void(0);"  onclick="userStateUpdate(3,'+row.userId+',&quot;'+row.userState+'&quot;,null);">推荐</a> '; 
	                		}
	                		 
	                	}else{
	                		d = '<a href="javascript:void(0);" onclick="userStateUpdate(4,'+row.userId+',&quot;'+row.userState+'&quot;,null);">取消推荐</a> ';  
	                	}
                			v=  '<a  href ="javascript:void(0);" onclick="upUserTeacher('+row.userId+');">  编辑</a>';
                }
             
                
                return e+" &nbsp;&nbsp;  "+d+"&nbsp; &nbsp;"+v;
	  		}}
  		]],
		queryParams: {                                                                                                                                                                                                                                                                                                                                    
			'pageIndex': _page.pageIndex,
			'pageSize': _page.pageSize
		},  
        toolbar: [{  
            text: '添加',  
            iconCls: 'icon-add',  
            handler: function() {  
				addUserFunc(); 
            }  
		}],  
    });
//设置分页控件
var pageInfo = {
		pageSize: _page.pageSize,
		pageList: _page.pageList,
		params:"key=value"
};//&key1=value1&key2=value2
eoc.cms.main.createPagination(pageInfo,$('#list_user_data'));

	
function userSearch(){
	seachParams();
}
	
	//封装分页参数
	function seachParams(){
		
	var seachForm = $('#seachForm');
	var userNmae=seachForm.find('input[id=userName]').val();
	var education=seachForm.find('select[id=education]').combobox('getValue');
	var schId=seachForm.find('input[id=searchSchId]').combobox('getValue');
	var isRecommended=seachForm.find('select[id=isRecommended]').combobox('getValue');
	var userType=seachForm.find('select[id=userType]').combobox('getValue');
	var userState=seachForm.find('select[id=userState]').combobox('getValue');
	var createDate=seachForm.find('input[id=createDate]').datetimebox('getValue');
	var endDate=seachForm.find('input[id=endDate]').datetimebox('getValue');
	var _queryParams = $('#list_user_data').datagrid('options').queryParams;
	
 
	if (!eoc.cms.main.isEmpty(createDate)
			&& !eoc.cms.main.isEmpty(endDate)) {
		var timeFrom = $.fn.datebox.defaults
				.parser(createDate);
		var timeTo = $.fn.datebox.defaults
				.parser(endDate);
		if (timeTo < timeFrom) {
			$.messager.alert('错误', '开始日期不能大于结束日期!');
			return;
		}
	}
	
	    $('#list_user_data').datagrid({
	    	queryParams: {
	    		userName_gp: userNmae,
	    		education_gp: education,
	    		schId_gp: schId,
	    		isRecommended_gp: isRecommended,
	    		userType_gp: userType,
	    		userState_gp: userState,
	    		createDate_gp: createDate,
	    		endDate_gp: endDate,
	    		
	    		//分页参数
	    		pageIndex_gp: _queryParams.pageIndex,
	    		pageSize_gp: _queryParams.pageSize 
	    	}
	    });
	 // 设置分页控件
		var pageInfo = {
			pageSize : _page.pageSize,
			pageList : _page.pageList,
			// &key1=value1&key2=value2
			params : "key=value"
		};
		eoc.cms.main.createPagination(pageInfo, $('#list_user_data'));
 
	}
	
	$('#createDate').datebox({
	    showSeconds: true,
	    buttons : dateboxbuttons
	    });
	
	$('#endDate').datebox({
	    showSeconds: true,
	    buttons : dateboxbuttons
	    });
 
    $(document).ready(function(){
	       
 	   $("#searchSchId").combobox({
         	url:'user/findSchools',  
       	  	valueField:'schId',
         	textField:'schName',
        	 panelHeight:'300',
        	 editable:false,
 			onLoadSuccess: function (data) {
    			 if (data) {
         		$("#searchSchId").combobox('setValue',data[0].schId);
     		}
			 },
     });
 	   
 });
 	   
	
/**
 * ============初始化结束=================================
 */
	/**
	 * 打开添加老师窗口
	 */
	function addUserFunc(){
			//var addDivForm=$("#divForm");
			//var addUserWin=addDivForm.find("div[id=addUserDiv]");
			$("#addUserDiv").dialog({
			width : 600,
			height :630,
			title : '添加名师',
			href : 'user/addUserUI',
			closable : true,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			doSize : true,
			closed : false,
			resizable : false,
			draggable : true,
			modal:true, 
			loadingMessage : "BUFF全开!!努力加载ing..."
		});
	}
 
	function upUserTeacher(userId){
			var url = 'user/editUserTeacherUI?userId=' + userId;
		//	var divForm=$("#divForm");
			$("#editUserDiv").dialog({
			width : 600,
			height :600,
			title : '编辑老师信息',
			href : url,
			closable : true,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			doSize : true,
			closed : false,
			resizable : false,
			draggable : true,
			modal:true, 
			loadingMessage : "BUFF全开!!努力加载ing..."
		});
	}
	
	
	 /**
	  * 添加老师信息
	  * @param obj
	  */
	function saveUser(obj){
		var proJobs=$("#jobGroupIdsInput").val();
		var userImg=$("#userImgUrl").val();
	 
		$(obj).linkbutton('disable');
	    $('#addUserForm').form('submit',{
	        url: 'user/addUser',
	        onSubmit: function(){
	        	var flag=false;
	        	flag=$(this).form('validate');
	        	if(flag){
	        		if(userImg==null ||userImg==''|| typeof(userImg)=='undefined'){
	            		$.messager.alert('提醒','请上传图片!');
	            		flag= false;
	            	}
	        	}
	        	if(flag){
	        		if(proJobs=="" || typeof(proJobs)=='undefined' ){
	        			$.messager.alert('提醒','请选择擅长领域!');
	        			flag=false;
	        		} 
	        	}
	        	if(!flag){
	        		$(obj).linkbutton('enable');
	        	}	
	        	return flag;
	        },
	        success: function(result){
	        $('#addUserDiv').dialog('close');        // close the dialog
	          var result = eval('('+result+')');
	    
	            if (result.success){
	            	seachParams();  // reload the user data
	                $.messager.show({ 
	                    title: 'Success',
	                    msg: '保存成功!'
	                });
	            } else {
	            	$.messager.show({
	                    title: 'Error',
	                    msg: '保存失败'
	                });
	            }
	          $(obj).linkbutton('enable');
	        }
	    });
	}
	/**
	 * 编辑老师信息
	 */
	function editUser(obj){
		 var userForm = $('#editUser');
		var proJobs=userForm.find('input[id=jobGroupIdsInput]').val();
		var userImg=userForm.find('input[id=userImgUrl]').val();
	 
		$(obj).linkbutton('disable');
	    $('#editUser').form('submit',{
	        url: 'user/editUserTeacher',
	        onSubmit: function(){
	        	var flag=false;
	        	flag=$(this).form('validate');
	        	if(flag){
	        		if(userImg==null ||userImg==''|| typeof(userImg)=='undefined'){
	            		$.messager.alert('提醒','请上传图片!');
	            		flag= false;
	            	}
	        	}
	        	if(flag){
	        		if(proJobs=="" || typeof(proJobs)=='undefined' ){
	        			$.messager.alert('提醒','请选择擅长领域!');
	        			flag=false;
	        		}
	        	}
	        	if(!flag){
	        		$(obj).linkbutton('enable');
	        	}	
	        	return flag;
	        },
	        success: function(result){
	        $('#editUserDiv').dialog('close');        // close the dialog
	          var result = eval('('+result+')');
	    
	            if (result.success){
	            	seachParams();  // reload the user data
	                $.messager.show({
	                    title: 'Success',
	                    msg: '保存成功!'
	                });
	            } else {
	            	$.messager.show({
	                    title: 'Error',
	                    msg: '保存失败'
	                });
	            }
	          $(obj).linkbutton('enable');
	        }
	    });
	}
/**
 * 更改用户状态或者推荐用户
  
 */	

	function userStateUpdate(upType,id,state,isRec){
			var cfMessage="";
			if(upType==1){
				if(isRec==1){
					cfMessage="该用户已推荐,确定封停吗？";
				}else{
					cfMessage="确定封停该用户吗？";
				}
			}else if (upType==2){
				cfMessage="确定恢复该用户？"
			}else if(upType==3){
				if(state=='0'){
					cfMessage="该用户已封停,确定推荐吗？？"
				}else{
					cfMessage="确定推荐该用户吗？"	
				}
				
			} else{
				cfMessage="确定取消该用户推荐？"
			}
		    $.messager.confirm('Confirm',cfMessage,function(r){
		            if (r){
		                $.post('user/userUpdate',{upType:upType,userId:id},function(result){
		                    if (result.success){
		                        $.messager.show({
		    	                    title: 'Success',
		    	                    msg:result.message
		    	                });
		                        seachParams();
		                    } else {
		                        $.messager.show({    // show error message
		                            title: 'Error',
		                            msg: '操作失败!'
		                        });
		                    }
		                },'json');
		            }
		        });
		}
		
		
		//发送密码
		function magEmail(pd){
			 var	email = $("#editUser").find("input[id=userEmail]").val();
			 var userName=$("#editUser").find("input[id=userName]").val();
			 var userId=$("#editUser").find("input[id=userId]").val();
			if(pd==2){
				email = $('#passwordToEmail').val();
				if(null ==email || email==''){
					$.messager.alert('提醒','请输入需要发送的邮箱!!');
					return;
				}
			}
			$.messager.confirm('Confirm','确定发送到'+email+'吗？',function(r){
		           if (r){
				$.post('user/magEmail',{passwordToEmail:email,userName:userName,userId:userId},function(result){
	                   if (result.success){
	                	   if(pd==1){
	                		   $("div[id=toregionPass]").html("<font color='red'>随机密码已发送至"+email+"</font>");
	                	   }else{
	                		   $("div[id=touserPass]").html("<font color='red'>随机密码已发送至"+email+"</font>");
	                	   }
	                   } else {
	                       $.messager.show({    // show error message
	                           title: 'Error',
	                           msg: '发送失败！'
	                       });
	                   }
					},'json');
		           }
		       });
		}
		
