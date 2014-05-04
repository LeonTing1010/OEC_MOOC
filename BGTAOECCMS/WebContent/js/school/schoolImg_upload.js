


function ajaxFileUploadSch(){
 		var fileCheck=$("#schFile").val();
 		if(fileCheck==null || fileCheck==""){
 			alert('请选择图片!');
 			return;
 		}
 		var schoolImgObj=$("#schLogo");
 		var fileNamed=schoolImgObj.val();
 		$("#loading").ajaxStart(function (){
 		$(this).show();
 		}).ajaxComplete(function(){
 		$(this).hide();
 		});
 		
 		$.ajaxFileUpload({
 		url:'school/updateSchoolImg',
 		secureuri:false,
 		fileElementId:'schFile',
		dataType:'json',
		data: {'fileNamed': fileNamed},  
 		success:function(date,status){
 			if(date.schoolFileName!=null){
		 		var url=date.urlPath+date.schoolFileName;
		 		$("#showSchImg").html("<img id='"+date.schoolFileName+"' src='"+url+"' height='70' width='80'></img><a id='"+date.schoolFileName+"tr' href='#' onclick=delimg('" +date.schoolFileName+"')>delete</a> &nbsp &nbsp");
		 		schoolImgObj.val(date.schoolFileName);	
		 		$.messager.show({
                    title: 'Success',
                    msg:'上传成功'
                });
 			}else{
 				$.messager.show({
                    title: 'fail',
                    msg:'上传失败'
                });
 	 		} 
 		},
 			error:function(date,status,e){
 			alert(e);
 			}
 		})
 		return false;
 	}
	
 	
 	function delimg(id){
 	     $.messager.confirm('Confirm','确定删除图片？',function(r){
	            if (r){
	                $.post('school/delSchoolImg',
	                		{fileName:id},
	                	function(result){
	                    if (result.success){
	                    	$("#showSchImg").empty();
	                    	$("#schLogo").val("");	
	                        $('#schoollist_data').datagrid('reload');    // reload the user data
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