
/**保存学生提交的试题 by zhangjin**/
function submitPaper(examId,examType){
var params = $('#paperform').serialize();
	$.ajax({
		url:base+"/course/submitPaper/?examId="+examId+"&examType="+examType,
		type:"post",
		data:params,
		dataType:"json",
	    error:function(){
	    },
	    success:function(data){
	    	if(data.result=='1'){
    	    	eoc.alert('提示','选择题未选填完！');
	    	 }
	    	 else if(data.result=='2'){
	    		 eoc.alert('提示','已经提交过,不能重复提交！');
	    	 }else if(data.result=='3'){
	    	     eoc.success('温馨提示','提交成功！',function(){showExamResult(examId);});
	    	 }
	    		
	    }
	});
    
}



