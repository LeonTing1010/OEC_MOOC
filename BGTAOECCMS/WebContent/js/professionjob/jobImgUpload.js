function ajaxFileUploadJobProfession(type){
		var id=null;
		var jobForm=null;
		if(type==1){
			//添加
			jobForm = $('#fmJobProfession');
			id='jobFile';
		}else{
			//修改
			jobForm = $('#addSaveOrUpdateJobForm');
		} 
 		
 		if(type==2){
 			id='editjobFile';
 			var fileCheck=jobForm.find('input[id='+id+']').val();
 	 		if(fileCheck==null || fileCheck==""){
 	 			alert('请选择图片!');
 	 			return;
 	 		}
 	 		var fileNamed="";
 	 		//已上传的图片路径
 	 		var jobImgObj=jobForm.find("input[id=jobDevelpping_op]");
 	 		 fileNamed=jobImgObj.val();
 			//已保存数据库的图片路径, 上传不删除保存数据库的图片
 			var jobProspect= jobForm.find("input[id=jobProspectUrl]").val();
 			if(jobProspect == jobImgObj.val()){
 				 fileNamed="";
 			} 
 			$.ajaxFileUpload({
 		 		url:'profession/uploadjobProspectImg',
 		 		secureuri:false,
 		 		fileElementId:id,
 				dataType:'json',
 				data: {'fileNamed': fileNamed},  
 		 		success:function(date,status){
 		 			if(date.userFileName!=null){
 				 		var url=cache+date.urlPath+date.userFileName;
 				 		jobForm.find("div[id=showProspectImg]").html("<img id='"+date.userFileName+"' src='"+url+"' height='70' width='80'></img>&nbsp;");
 				 		jobImgObj.val(date.urlPath+date.userFileName);	
 				 		$.messager.show({
 		                    title: '上传成功',
 		                    msg:'上传成功'
 		                });
 		 			}else{
 		 				$.messager.show({
 		 					title:date.title,
 		                    msg:date.message
 		                });
 		 	 		} 
 		 		},
 		 		error:function(date,status,e){
 		 			alert(e);
 		 		}
 		 		});
 		}  
 		
 		if(type==3){
 			id='jobImgFiles';
 			var fileCheck=jobForm.find('input[id='+id+']').val();
 	 		if(fileCheck==null || fileCheck==""){
 	 			alert('请选择图片!');
 	 			return;
 	 		}
 	 		var fileNamed="";
 	 		//已上传的图片路径
 	 		var jobImgObj=jobForm.find("input[id=jobImage_op]");
 	 		 fileNamed=jobImgObj.val();
 			//已保存数据库的图片路径, 上传不删除保存数据库的图片
 			var jobProspect= jobForm.find("input[id=jobimgPath]").val();
 			if(jobProspect == jobImgObj.val()){
 				 fileNamed="";
 			}
 			$.ajaxFileUpload({
 		 		url:'profession/uploadJobImg',
 		 		secureuri:false,
 		 		fileElementId:id,
 				dataType:'json',
 				data: {'fileNamed': fileNamed},  
 		 		success:function(date,status){
 		 			if(date.userFileName!=null){
 				 		var url=cache+date.urlPath+date.userFileName;
 				 		jobForm.find("div[id=showJobimg]").html("<img id='"+date.userFileName+"' src='"+url+"' height='70' width='80'></img>&nbsp;");
 				 		jobImgObj.val(date.urlPath+date.userFileName);	
 				 		$.messager.show({
 		                    title: '上传成功',
 		                    msg:'上传成功'
 		                });
 		 			}else{
 		 				$.messager.show({
 		 					title:date.title,
 		                    msg:date.message
 		                });
 		 	 		} 
 		 		},
 		 		error:function(date,status,e){
 		 			alert(e);
 		 		}
 		 		});
 		}
 		return false;
 	}
 	
 	function delimg(id,type){
 		var userForm=null;
 		if(type==1){
			//添加
			userForm = $('#fmJobProfession');
		}else{
			//修改
			userForm = $('#addSaveOrUpdateJobForm');
		 
		}
 	     $.messager.confirm('Confirm','确定删除图片？',function(r){
	            if (r){
	                $.post('user/delUserImg',
	                		{fileName:id},
	                	function(result){
	                    if (result.success){
	                    	userForm.find("td[id=showProspectImg]").empty();
	                    	userForm.find("input[id=showProspectImg]").val("");	
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