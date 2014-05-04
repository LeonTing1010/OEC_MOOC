


function ajaxFileUpload_pro(){
 		/*var fileCheck=$("#proFile").val();
 		alert(fileCheck);
 		if(fileCheck==null || fileCheck==""){
 			alert('请选择图片!');
 			return;
 		}*/
		$('#btn').linkbutton('disable');    //禁用保存按钮
 		var proImgObj=$("#proImage");
 		var proImgObj2=$("#proImage2");
 		var fileNamed=proImgObj.val();		//新上传图片的路径
 		var fileNamed2=proImgObj2.val();   //数据库保存的路径
 		$("#loading").ajaxStart(function (){
 		$(this).show();
 		}).ajaxComplete(function(){
 		$(this).hide();
 		});
 		
 		$.ajaxFileUpload({
 		url:'pro/updateProfessionImg',
 		secureuri:false,
 		fileElementId:'proFile',
		dataType:'json',
		data: {'fileNamed': fileNamed,'fileNamed2': fileNamed2},
 		success:function(date,status){
 			if(date.userFileName!=null){
		 		var url=cache+date.urlPath+date.userFileName;
		 		$("#proImgshow").html("<img id='"+date.userFileName+"' src='"+url+"' height='70' width='80'  /> &nbsp &nbsp");
		 		/*<a id='"+row.proImage+"tr' href='#' onclick=delimg('" +row.proImage+"')>delete</a> 需要删除图片时加上*/
		 		proImgObj.val(date.urlPath+date.userFileName);  //新上传图片的路径
		 		proImgObj2.val(fileNamed2);  //新上传图片的路径
		 		$.messager.show({
                    title: 'Success',
                    msg:'上传成功'
                });
 			}else{
 				$.messager.show({
                    title: 'fail',
                    msg:date.message
                });
 	 		} 
 		},
 			error:function(date,status,e){
 			alert(e);
 			}
 		})
 		$('#btn').linkbutton('enable');   //启用保存按钮
 		return false;
 	}
	
 	
 	function delimg(id){
 	     $.messager.confirm('Confirm','确定删除？',function(r){
	            if (r){
	                $.post('pro/delProImg',
	                		{fileName:id},
	                	function(result){
	                    if (result.success){
	                    	$("#proImgshow").empty();
	                    	$("#proFile").val("");	   //删除文件后变成 未选择图片
	                        $('#professionlist_data').datagrid('reload');    // reload the user data
	                        $.messager.show({
	    	                    title: 'Success',
	    	                    msg:'删除成功'
	    	                });
	                    } else {
	                        $.messager.show({    // show error message
	                            title: 'Error',
	                            msg: '删除失败'
	                        });
	                    }
	                },'json');
	            }
	        });
 	}