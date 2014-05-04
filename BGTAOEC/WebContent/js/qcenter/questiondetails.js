/**
 * 
 * Fucntion:更新赞数量
 * Add By:刘祚家
 * Add Date:2014-01-16
 */
function updateAnswPraiseCount(answerId)
{
	if(answerId!=""){
		oec.login.isLogin(function(){
		$.ajax({
			url:base+"/questionCenter/updateAnswPraiseCount.ajax",
			data:{"answerId":answerId},

		    success:function(data){
				if(data.flag==0){
					if(data.answerVo!=null && data.answerVo.flag==0){
			    		$("#praiseSign"+answerId).html("已赞"); 	
			    		$("#praiseCount"+answerId).html(data.answerVo.answPraiseCount); 		
		    		}else if(data.answerVo!=null && data.answerVo.flag==1){
		    			$("#praiseSign"+answerId).html("赞"); 
		    			$("#praiseCount"+answerId).html(data.answerVo.answPraiseCount);
		    		}
				}else if(data.flag==1){
					window.location.href=base+'/user/loginIndex/';
				}
		    }
			
		});
		});
	}
}

/**
 * 
 * Fucntion:更新关注问题
 * Add By:刘祚家
 * Add Date:2014-01-20
 */
function updateAttentionQuestion(quesID){
	if(quesID!=""){
		oec.login.isLogin(function(){
		$.ajax({
			url:base+"/questionCenter/updateAttentionQuestion.ajax",
			data:{"quesID":quesID},
		    success:function(data){
				if(data.flag==0){
			    	if(data.attentionVo!=null && data.attentionVo.flag==0){
			    		$("#attentionSign"+quesID).html("已关注"); 
			    		$("#attentionCount"+quesID).html(data.attentionVo.attentionNum); 
		    		}else if(data.attentionVo!=null && data.attentionVo.flag==1){
		    			$("#attentionSign"+quesID).html("+ 关注问题"); 
		    			$("#attentionCount"+quesID).html(data.attentionVo.attentionNum);
		    		}
				}else if(data.flag==1){
					window.location.href=base+'/user/loginIndex/';
				}
		    }
		});
		});
	}
}


/**
 * 
 * Fucntion:加载更多回答
 * Add By:刘祚家
 * Add Date:2014-01-23
 */
function getMoreAnswer() {
	// 页数+1
	var page =$('#page').val();
	page=parseInt(page)+1;
	$('#page').val(page);
	
	var pageSize =$('#pageSize').val();
	var quesID =$('#quesID').val();
	$.ajax({
		url:base+"/qacenter/getMoreAnswer.ajax",
		data:{"quesID":quesID,"page":page,"pageSize":pageSize},
	    success:function(data){
	    	if (data!="") {
	    		var liList = $(".hotQuestionCon-ul").html()+data;
	    		$(".hotQuestionCon-ul").html(liList);
	    		
	    		//$("#answerListNum").html($(".hotQuestionCon-ul li").size());
	    		
			}else{
			  
			    $(".i-icon1").text("暂无更多回答");
				document.getElementById("showMoreAnswer").onclick=null;
				document.getElementById("showMoreAnswer").style.backgroundColor="gray";
			}
	    }
	});
}                            





