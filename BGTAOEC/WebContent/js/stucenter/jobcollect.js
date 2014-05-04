var jobID;
var pageSize=2;
var type="1";
function getMoreCourse() {
	$.ajax({
		url:base+"/jobcollect/getMoreCourse.ajax",
		data:{type:type,page:pageSize,jobID:jobID},
		error : function(){
			
		},
	    success:function(data){
	    	if (data!="") {
	    		pageSize = pageSize+1;
				var courList = $(".post-body").html()+data;
				$(".post-body").html(courList);
			}else{
			    document.getElementById("courseMore").onclick=null;
				document.getElementById("courseMore").disenable=true;
			}
	    }
	});
}