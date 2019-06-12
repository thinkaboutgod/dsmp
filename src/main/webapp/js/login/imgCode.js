
$(function() {
	$("#getCode").click(function(){
	var address = $("#getCode").attr("src");
	var time = new Date().getTime();
	$("#getCode").attr("src",address + "?time="+time);
});
})
