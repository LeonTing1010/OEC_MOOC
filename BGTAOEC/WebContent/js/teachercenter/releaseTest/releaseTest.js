 /**-上传试题文件  by zhangjin-**/
 function uploadFile(type) {
	    var selectUl = null;
	    var fileId = null;
	    var tempExcelId = null;
	    var liId = null;
	    
	 	if(type==1){        //练习选择题
	 		selectUl = "lxSelectUl";
	 		fileId = "lxfile";
	 		liId = "lxliId";
	 		tempExcelId = "lxTempExcel";
			
	 	}else if(type==2){  //作业选择题
	 		selectUl = "zySelectUl";
	 		fileId = "zyfile";
	 		liId = "zyliId";
	 		tempExcelId = "zyTempExcel";
	 		
	 	}else if(type==3){  //考试选择题
	 		selectUl = "ksSelectUl";
	 		fileId = "ksfile";
	 		liId = "ksliId";
	 		tempExcelId = "ksTempExcel";
	 	}
	 	
	    $.ajaxFileUpload({url:base+"/teacherCenter/upload/",
		    secureuri:false,
		    dataType: 'json',   //数据类型  
		    fileElementId:fileId,
		    error:function(){
		    	
		    },
		    success:function(data){

		    	var excelName = data.newfileName;
		    	var soursefilename = data.soursefilename;
		    	
		    	lens=soursefilename.length; 
		    	extname=soursefilename.substring(soursefilename.lastIndexOf("\\")+1,lens);  

		    	var dt = new Date();
		  	    var nowDate = dt.format("yyyyMMddhhmmss");
		    	
		        var s=document.getElementById(selectUl);
		        var t=s.childNodes.length;
		        var li= document.createElement("li");
		        li.id=nowDate;
		        li.className="item";
		        document.getElementById(selectUl).innerHTML = "";
		        li.innerHTML="<span class=\"title\">"+extname+"</span>"+
		        "<span class=\"operate delet\" onClick=\"delExcel("+nowDate+",\'"+tempExcelId+"\')\"></span>"+
		        "<input type=\"hidden\" id=\""+liId+"\" name=\""+liId+"\" value=\""+nowDate+"\"/>"+
		        "<input type=\"hidden\" id=\""+tempExcelId+"\" name=\""+tempExcelId+"\" value=\""+excelName+"\"/>";
		        s.appendChild(li);
		        
		        if(type==1){
		        	 $('#lxExcelCheck').html('<i class="ok-icon"></i><span class="ok">&nbsp</span>');
		        }else if(type==2){
		        	$('#zyExcelCheck').html('<i class="ok-icon"></i><span class="ok">&nbsp</span>');
		        }else if(type==3){
		        	$('#ksExcelCheck').html('<i class="ok-icon"></i><span class="ok">&nbsp</span>');
		        }
		       

		    }
		});
}
 
 

 /**--删除上传的excel试题 by zhangjin-- **/
function delExcel(liId,tempExcelId){
 	
 	if(confirm("确定删除此题吗？"))
 	 {
         $("#"+tempExcelId+"").val("");
         $("#"+liId).remove()();
        
 	 }
 }

/**--获取所选课程下的章节 by zhangjin-- **/
function getSection(name){

	var courseId = $("#courseId").val();
	removePoint($("#courseId").parent());
	rightSpan().appendTo($("#courseId").parent())
	
	if(courseId==""){
		clearSel(document.getElementById("toZ")); //清空章
		var toZ = document.getElementById("toZ");
		toZ.options.add(new Option("请选择章",""))
		return (false);  
	}
	
	if(name=='z' ){ //课程章
		clearSel(document.getElementById("toZ")); //清空章
		$.ajax({
			url:base+"/teacherCenter/getSectionZ/",
			type:"post",
			data:{courseId:courseId},
			dataType:"json",
		    error:function(){
		    },
		    success:function(result){
		    	setSectionZ(result); 
		    }
		});
   }else if(name=='j'){ //课程章下面的节
	   removePoint($("#toJ").parent());
	  
	   clearSel(document.getElementById("toJ")); //清空节
	   if($('#toZ').val()==''){
		   $('#toJ').append("<option value=''>请选择节</option>");
	   }
	   var secId = $("#toZ").val();
		$.ajax({
			url:base+"/teacherCenter/getSectionJ/",
			type:"post",
			data:{courseId:courseId,secId:secId},
			dataType:"json",
		    error:function(){
		    },
		    success:function(result){
		    	setSectionJ(result); 
		    }
		});
   }else{
	   removePoint($("#toJ").parent());
	   rightSpan().appendTo($("#toJ").parent())
   }
 }


/**--改变课程时设置章 by zhangjin-- **/
function setSectionZ(result){

	var toZ = document.getElementById("toZ");
	toZ.options.add(new Option("请选择章",""))
	jQuery.each(result.data, function(i, section){
		var value = section.id;
		var text = section.name;
        var option = new Option(text,value);
        toZ.options.add(option);
		}); 
}

/**--改变课程时设置节 by zhangjin-- **/
function setSectionJ(result){
	
	var toJ = document.getElementById("toJ");
	toJ.options.add(new Option("请选择节",""));
	jQuery.each(result.data, function(i, section){
		var value = section.id;
		var text = section.name;
        var option = new Option(text,value);
        toJ.options.add(option);
		}); 
}

/**--清空下拉列表 by zhangjin-- **/
function clearSel(oSelect){

	while(oSelect.childNodes.length>0){
		oSelect.removeChild(oSelect.childNodes[0]);
	}
	
}

/**--添加问答题题目 by zhangjin-- **/
function addQuestion(type){
	
	var quesContent = null;
	var quesAnswer = null;
	var quesScore = null;
	var quesContentName = null;
	var quesAnswerName = null;
	var quesScoreName = null;
	var ulType = null;

	/***************--作业主观题--********************/
		if(type=='task'){  //作业题目
			
			quesContent = $("#taskContent").val();
			var $ques = $("#taskContent").parent();
			if(quesContent==""){
				removePoint($ques);
				errorSpan('请填写题目').appendTo($ques);
				return (false); 
			}
			
			quesAnswer = $("#taskAnswer").val();
			var $ans = $("#taskAnswer").parent()
			if(quesAnswer==""){
				removePoint($ans);
				errorSpan('请填写答案').appendTo($ans);
				return (false); 
			} 
			
			quesScore = $("#taskScore").val();
			var $score = $("#taskScore").parent()
			if(quesScore==""){
				removePoint($score);
				errorSpan('请填写分数').appendTo($score);
				return (false); 
			} 
			
			quesContentName = "taskQuesContent";
			quesAnswerName = "taskQuesAnswer";
			quesScoreName = "taskQuesScore";
			ulType = "ulTwo";
			//题目发布成功后清空输入框的值
			$("#taskContent").val('');
			$("#taskAnswer").val('');
			$("#taskScore").val('');
			removePoint($ques);
			removePoint($ans);
			removePoint($score);
		}
		
		/***************--考试主观题--********************/
		else if(type=='exam'){ //考试题目
			
			quesContent = $("#examContent").val();
			var $ques = $("#examContent").parent();
			if(quesContent==""){
				removePoint($ques);
				errorSpan('请填写题目').appendTo($ques);
				return (false); 
			}
			
			quesAnswer = $("#examAnswer").val();
			var $ans = $("#examAnswer").parent()
			if(quesAnswer==""){
				removePoint($ans);
				errorSpan('请填写答案').appendTo($ans);
				return (false); 
			} 
			
			quesScore = $("#examScore").val();
			var $score = $("#examScore").parent()
			if(quesScore==""){
				removePoint($score);
				errorSpan('请填写分数').appendTo($score);
				return (false); 
			} 
			
			quesContentName = "examQuesContent";
			quesAnswerName = "examQuesAnswer";
			quesScoreName = "examQuesScore";
			ulType = "ulThree";
			//题目发布成功后清空输入框的值
			$("#examContent").val('');
			$("#examAnswer").val('');
			$("#examScore").val('');
			removePoint($ques);
			removePoint($ans);
			removePoint($score);
		}
		
		
		  var dt = new Date();
		  var nowDate = dt.format("yyyyMMddhhmmss");
		
		  var s=document.getElementById(ulType);
		  var t=s.childNodes.length;
		  var li= document.createElement("li");
		  li.id=nowDate;
		  li.className="item";
		  li.innerHTML="<span class=\"title\">"+quesContent+"</span><span class=\"operate delet\" onClick=\"deltask("+nowDate+")\"></span>" +
		  "<div class=\"itemCon\">" +
		  "<div>"+quesContent+"</div>" +
		  "<a href=\"javascript:del(this);\"><span class=\"operate delet\"></span></a></span></div></span>" +
		  "<span class=\"operate look\"><div class=\"itemCon\">"+
		  "<div>问题："+quesContent+"</div><p>解答："+quesAnswer+"</p></div></span>"+
		  "<input type=\"hidden\" name=\""+quesAnswerName+"\" value=\""+quesAnswer+"\"/>" +
		  "<input type=\"hidden\" name=\""+quesContentName+"\" value=\""+quesContent+"\"/>"+
		  "<input type=\"hidden\" name=\""+quesScoreName+"\" value=\""+quesScore+"\"/>";
		  s.appendChild(li);
}


/**--删除题目 by zhangjin-- **/
function deltask(liId){
	
	if(confirm("确定删除此题吗？"))
	 {
        $("#"+liId).remove()();
	 }
}


/**--验证文本域是否超过字数限制 by zhangjin-- **/
$(function(){
      
      function maxLimit(){
          var num=$(this).val().substr(0,500);
          $(this).val(num);
          $(this).next().children("span").text($(this).val().length+"/500字");
      };
	    $(".large").keyup(maxLimit);
	    $(".large").focus(function(){
	            $(this).addClass("focus").next().show();
	            if($(this).val() == $(this).attr("title")){
	                    $(this).val("");
	            }
	    });
	    $(".large").blur(function(){
	            if($(this).val().length > 0){
	                    $(this).addClass("focus").next().show();
	            }else{
	                  //  $(this).removeClass("focus").next().hide();
	            }
	            if($(this).val() == ""){
	                    $(this).val($(this).attr("title"));
	            }
	    });
	    
});

/**错误提示**/
function errorSpan(msg) {
	var $i =$('<i>').addClass('error-icon');
	var $pspan = $('<span>').addClass('validation');
	var $espan = $('<span>').addClass('error').attr('style','color:red;').html(msg);
	$i.appendTo($pspan);
	$espan.appendTo($pspan);
	return $pspan;
}

/**正确提示**/
function rightSpan(){
	var $i =$('<i>').addClass('ok-icon');
	var $pspan = $('<span>').addClass('validation');
	var $espan = $('<span>').addClass('ok');
	$espan.html('&nbsp');
	$i.appendTo($pspan);
	$espan.appendTo($pspan);
	return $pspan;
}

/**去除提示**/
function removePoint($obj) {
	$obj.find('.validation').remove();
}

/**验证空值**/
function isNull(obj){
	if(obj=='' || null==obj ||obj==undefined){
		return true;
	}
	return false;
}
/**校验作业名称是否填写**/
function checkTaskName(){
	var taskName = $('input[name=taskName]').val();
	var $p = $('input[name=taskName]').parent();	
	removePoint($p);
	if (isNull(taskName)) {
		errorSpan('请填写作业名称').appendTo($p);
		return false;
	}
	rightSpan().appendTo($p);	
	return true;
}
/**检验是否填写题目**/
function checkWork(type){
	var quesConent=null;
	var $p;
	if(type=='task'){
		quesContent = $("#taskContent").val();
		$p=$("#taskContent").parent();
	}else if(type='exam'){
		quesContent = $("#examContent").val();
		$p=$("#examContent").parent();
	}
	removePoint($p);
	if (isNull(quesContent)) {
		errorSpan('请填写题目').appendTo($p);
		return false;
	}
	rightSpan().appendTo($p);	
	return true;
	
}

/**检验是否填写答案**/
function checkAnswer(type){
	var ansConent=null;
	var $p;
	if(type=='task'){
		ansConent = $("#taskAnswer").val();
		$p=$("#taskAnswer").parent();
	}else if(type='exam'){
		ansConent = $("#examAnswer").val();
		$p=$("#examAnswer").parent();
	}
	removePoint($p);
	if (isNull(ansConent)) {
		errorSpan('请填写答案').appendTo($p);
		return false;
	}
	rightSpan().appendTo($p);	
	return true;
}

/**校验是否填写分数**/
function checkScore(type){
	var scoreConent=null;
	var $p;
	if(type=='task'){
		scoreConent = $("#taskScore").val();
		$p=$("#taskScore").parent();
	}else if(type='exam'){
		scoreConent = $("#examScore").val();
		$p=$("#examScore").parent();
	}
	removePoint($p);
	if (isNull(scoreConent)) {
		errorSpan('请填写分数').appendTo($p);
		return (false);
	}
	rightSpan().appendTo($p);	
	return true;
}
/**--验证发布试题的必填内容是否都填了 by zhangjin-- **/
function checkIsFill(){
	var examType=document.getElementsByName("examType");
	var strExamType = null;;
	var tempExcel = null;
    
	for(var i=0;i<examType.length;i++)
    {
     if(examType.item(i).checked){
    	 strExamType=examType.item(i).getAttribute("value");  
      break;
    }else{
     continue;
     }
    }
    
	var courseId = $("#courseId").val();
	var $courSelect=$("#courseId").parent();

	if(courseId==""){
		removePoint($courSelect)
		//eoc.alert('提示','请选择课程!');
		errorSpan('请选择课程').appendTo($courSelect);
		return (false);  
	}
	
	
	/**************--课堂考试--*******************/
	if(strExamType=="1"){  
		
		 tempExcel= $("#ksTempExcel").val();
		  if(tempExcel=="")  
		   {  
			  $('#ksExcelCheck').html('<i class="error-icon"></i><span class="error" style="color:red;">请点击上传模板，选择您要上传的文件!</span>');
		      theform.theFile.focus;  
		      return (false);  
		   }  
		   else  
		   {  
		      str= tempExcel;
		      strs=str.toLowerCase();  
		      lens=strs.length;  
		      extname=strs.substring(lens-4,lens);  
		     if(extname!=".xls"&&extname!="xlsx")  
		      {  
		    	 $('#ksExcelCheck').html('<i class="error-icon"></i><span class="error" style="color:red;">请选择Excel文件!</span>');
		         return (false);  
		      }  
		      
		   } 
		  
		  var jqObj = $("#ulThree");
			 if(!jqObj.has('li').length){ 
				   eoc.alert('提示','请保存主观题！');
				   return (false);  
			 } 
			 
		$.ajax({
			url:base+"/teacherCenter/checkExamIsExist/?courseId="+courseId,
			type:"post",
		    error:function(){
		    },
		    success:function(data){
		    	if(data.result=="1"){
		    		eoc.alert('提示','该课程考试已经存在！');
		    		return (false);  
		    	}else{
		    		checkExcelIsRight(tempExcel,strExamType);
		    	}
		    	
		      }
		   })
		   
	  }
	
	/**************--课堂练习--*******************/
	 else if(strExamType=="2"){  //试题练习才分章节
	  
	    var toZ = $("#toZ").val();
	    var $toZTip=$("#toZ").parent();
	    var $toJTip=$("#toZ").parent();
	    
		if(toZ==""){
			removePoint($toZTip);
			errorSpan('请选择章').appendTo($toZTip);
			return (false);  
		}
		var toJ = $("#toJ").val();
		if(toJ==""){
			removePoint($toJTip);
			errorSpan('请选择节').appendTo($toJTip);
			return (false);  
		 }
		
		tempExcel= $("#lxTempExcel").val();
		  if(tempExcel=="")  
		   {  
		      //eoc.alert('提示','请点击上传模板，选择您要上传的文件!');
			  $('#lxExcelCheck').html('<i class="error-icon"></i><span class="error" style="color:red;">请点击上传模板，选择您要上传的文件!</span>');
		      theform.theFile.focus;  
		      return (false);  
		   }  
		   else  
		   {  
		      str= tempExcel;
		      strs=str.toLowerCase();  
		      lens=strs.length;  
		      extname=strs.substring(lens-4,lens);  
		     if(extname!=".xls"&&extname!="xlsx")  
		      {  
		    	 $('#lxExcelCheck').html('<i class="error-icon"></i><span class="error" style="color:red;">请选择Excel文件!</span>');
		       return (false);  
		      }  
		      
		   } 
		
		$.ajax({
			url:base+"/teacherCenter/checkPracticeIsExist/?courseId="+courseId+"&toJ="+toJ,
			type:"post",
		    error:function(){
		    },
		    success:function(data){
		    	if(data.result=="1"){
		    		eoc.alert('提示','该课程章节下的练习已经存在！');
		    		return (false);  
		    	}else{
		    		checkExcelIsRight(tempExcel,strExamType);
		    	}
		    	
		      }
		   })
		
	   }
	 /**************--课堂作业--*******************/
	  else if(strExamType=="3"){  //课堂作业
	  
		     var taskName = $("#taskName").val();
			 if(taskName==""){
				removePoint($("#taskName").parent());
				errorSpan('请填写作业名称').appendTo($("#taskName").parent());
				return (false);  
			 }

			 var taskLastTime = $("#taskLastTime").val();
			 if(taskLastTime==""){
				eoc.alert('提示','请选择截止时间！');
				return (false);  
			 }
			 
			 tempExcel= $("#zyTempExcel").val();
			  if(tempExcel=="")  
			   {  
				  $('#zyExcelCheck').html('<i class="error-icon"></i><span class="error" style="color:red;">请点击上传模板，选择您要上传的文件!</span>');
			      theform.theFile.focus; 
			      return (false);  
			   }  
			   else  
			   {  
			      str= tempExcel;
			      strs=str.toLowerCase();  
			      lens=strs.length;  
			      extname=strs.substring(lens-4,lens);  
			     if(extname!=".xls"&&extname!="xlsx")  
			      {  
			    	 $('#zyExcelCheck').html('<i class="error-icon"></i><span class="error" style="color:red;">请选择Excel文件!</span>');
			       return (false);  
			      }  
			      
			   } 
			  var jqObj = $("#ulTwo");
				 if(!jqObj.has('li').length){ 
					    eoc.alert('提示','请保存主观题！');
						return (false);  
				 } 
				 
			 checkExcelIsRight(tempExcel,strExamType);
	   }    
		
} 

/**--验证excel试题的数据格式是否正确 by zhangjin-- **/
function checkExcelIsRight(tempExcel,strExamType){
	
	var params = $('#releaseTestForm').serialize();
	$.ajax({
		url:base+"/teacherCenter/checkExcelIsRight/?tempExcel="+tempExcel+"&examType="+strExamType,
		type:"post",
		data: params,
	    error:function(){
	    },
	    success:function(data){
	    	 
	    	 if(data.message=="验证成功"){
	    		//验证通过后，发布试题
	    		saveTest(strExamType,tempExcel)
	    	 }else{
	    		 //验证不通过提示
	    		 eoc.alert('提示',data.message);
	    	 }
	      }
	   })
}


/**--保存发布的试题 by zhangjin-- **/
function saveTest(strExamType,tempExcel){
		var params = $('#releaseTestForm').serialize();
		$.ajax({
			url:base+"/teacherCenter/releasetestSave/",
			type:"post",
			data:params,
		    error:function(){
		    	eoc.alert('提示','上传失败，试题数据类型错误！');
		    },
		    success:function(data){
		    		eoc.success('温馨提示','试题发布成功！！',function(){
		    			releaseTestIndex(strExamType)
		    		});
		    		var selectUl = null;
		    		var tempExcelId = null;
		    		if(strExamType=="1"){
		    			selectUl = "ksSelectUl";
		    			tempExcelId = "ksTempExcel";
		    		}else if(strExamType=="2"){
		    			selectUl = "lxSelectUl";
		    			tempExcelId = "lxTempExcel";
		    		}else if(strExamType=="3"){
		    			selectUl = "zySelectUl";
		    			tempExcelId = "zyTempExcel";
		    		}
		    	    //发布成功后清空excel文件
		    	    document.getElementById(selectUl).innerHTML = "<input type=\"hidden\" id="+tempExcelId+" />";
		    }
		});
}


/**--选择文件页面显示上传的Excel文件名 by zhangjin-- **/
function change(){
	str= releaseTestForm.file.value;  
	lens=str.length;  
	extname=str.substring(str.lastIndexOf("\\")+1,lens);  
    var s=document.getElementById('lxSelectUl');
    var t=s.childNodes.length;
    var li= document.createElement("li");
    li.id="exId";
    li.className="item";
    li.innerHTML="<span class=\"title\">"+extname+"</span><a href=\"javascript:del(this);\"><span class=\"operate delet\"></span></a>";
    s.appendChild(li);
}

/**--删除页面的Excel信息 by zhangjin-- **/
function del(tempExcel) {    
	$("#"+tempExcel).remove()();

}  


/**日期的格式化 by zhangjin**/
Date.prototype.format =function(format)
{
    var o = {
            "M+" : this.getMonth()+1, //month
            "d+" : this.getDate(), //day
            "h+" : this.getHours(), //hour
            "m+" : this.getMinutes(), //minute
            "s+" : this.getSeconds(), //second
            "q+" : Math.floor((this.getMonth()+3)/3), //quarter
            "S" : this.getMilliseconds() //millisecond
    };
    if(/(y+)/.test(format)) 
    {    
        format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4- RegExp.$1.length));
    }
    for(var k in o)
    {
        if(new RegExp("("+ k +")").test(format))
        {
            format = format.replace(RegExp.$1,RegExp.$1.length==1? o[k] :("00"+ o[k]).substr((""+ o[k]).length));
        }
    }
    return format;
};




