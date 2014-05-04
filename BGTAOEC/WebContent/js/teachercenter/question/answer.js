/**
 * 
 */
// kindeditor加载配置
$(function($) {
	var editor = KindEditor.create('#answtextarea', {
		themeType : 'qq',
		resizeType : 0,
		afterCreate : function() {
			this.sync();
			$('#allTextNum').val(this.count('text'));
		},
		afterBlur : function() {
			this.sync();
			$('#allTextNum').val(this.count('text'));
		},
		afterChange : function() {
			$('#allTextNum').val(this.count('text'));
		},
		items : [ 'bold', 'italic', 'underline']
	});
		
	//editor.toolbar.remove();
	//editor.statusbar.remove();
});

// 提交我来回答
function submitAnswer() {
	var answContent = $('#answtextarea').val();
	if(answContent.length<=0){
		
		$(".validation").html("<i class=\"tip-icon\"></i><span class=\"tip\" style=\"color:red;\">必填</span>");
		return false;
	}else{
		
		var quesID = $('#quesID').val();
		var quesAddID = $('#quesAddID').val();
		$.ajax({
			url : base + "/questionCenter/saveTeacherAnswer.ajax",
			data : {
				"quesID" : quesID,
				"quesAddID" : quesAddID,
				"answContent" : answContent
			},

			success : function(data) {
				if (data.flag == 0) {
					switchLoad(8);
					$(".unsolvedQuesNum").html(data.unsolvedQuesNum);
					if(data.unsolvedQuesNum>0){
						$("#topUnsolvedQuesNum").html("+"+data.unsolvedQuesNum);
					}else {
						$("#topUnsolvedQuesNum").html("");
					}
					
				} else if (data.flag == 1) {
					window.location.href = base + '/user/login/';
				}
			},

		});
		return true;
	}
}
