$(function(){
	if(updatePageIndex == 0){
		$('#quickNav li').first().click();
	}else{
		$('#quickNav li').eq(updatePageIndex).click();
		$('#quickNav li').eq(0).remove();
		$('#quickNav li').eq(0).remove();
	}
})
function updatePage(tag){
	$(tag).addClass('current').siblings('.current').removeClass('current');
	$('.form-bodybox').load(base+'/teacherCenter/update/'+$(tag).attr('data-id')+'/?courseId='+$('input[name=courseId]').val()+"&code="+Math.random());
}
