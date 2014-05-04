


function ajaxFileUpload(type){
		var id=null;
		var userForm=null;
		if(type==1){
			//添加
			userForm = $('#addUserForm');
			id='userFile';
		}else{
			//修改
			userForm = $('#editUser');
			id='editFile';
		} 
 		var fileCheck=userForm.find('input[id='+id+']').val();
 		if(fileCheck==null || fileCheck==""){
 			$.messager.alert('提醒','请选择图片!');
 			return;
 		}
 		var fileNamed="";
 		//已上传的图片路径
 		var userImgObj=userForm.find("input[id=userImgUrl]");
 		 fileNamed=userImgObj.val();
 		if(type==2){
 			//已保存数据库的图片路径, 上传不删除保存数据库的图片
 			var userHeadPic= userForm.find("input[id=userHeadPic]").val();
 			if(userHeadPic == userImgObj.val()){
 				 fileNamed="";
 			} 
 		}  
 		$.ajaxFileUpload({
 		url:'user/uploadUserImg',
 		secureuri:false,
 		fileElementId:id,
		dataType:'json',
		data: {'fileNamed': fileNamed},  
 		success:function(date,status){
 			if(date.userFileName!=null){
		 		var url=cache+date.urlPath+date.userFileName;
		 		userForm.find("td[id=show]").html("<img id='"+date.userFileName+"' src='"+url+"' height='70' width='80'></img>&nbsp;");
		 		userImgObj.val(date.urlPath+date.userFileName);	
		 		$.messager.show({
                    title: '上传成功',
                    msg:'上传成功'
                });
 			}else{
 				$.messager.show({
                    title: date.title,
                    msg:date.message
                });
 	 		} 
 		},
 			error:function(date,status,e){
 			alert(e);
 			}
 		})
 		return false;
 	}
 	
 	function delimg(id,type){
 		var userForm=null;
 		if(type==1){
			//添加
			userForm = $('#addUserForm');
		}else{
			//修改
			userForm = $('#editUser');
		 
		}
 	     $.messager.confirm('Confirm','确定删除图片？',function(r){
	            if (r){
	                $.post('user/delUserImg',
	                		{fileName:id},
	                	function(result){
	                    if (result.success){
	                    	userForm.find("td[id=show]").empty();
	                    	userForm.find("input[id=userImgUrl]").val("");	
	                //        $('#list_user_data').datagrid('reload');    // reload the user data
	                        $.messager.show({
	    	                    title: 'Success',
	    	                    msg:result.message
	    	                });
	                    } else {
	                        $.messager.show({    // show error message
	                            title: 'Error',
	                            msg: result.errorMsg
	                        });
	                    }
	                },'json');
	            }
	        });
 	}