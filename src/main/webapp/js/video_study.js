$(function() {
	var path = $("#path").val();	
	$(document).on("click",".changeVideo",function(){
		var vidPath = $(this).prev().val();
		var vidImgpath = $(this).prev().prev().val();
		$("#my-video").attr("poster",vidImgpath);
		$("#my-sourse").attr("src",vidPath);
		$("#vidTitle").text($(this).text());
		document.getElementById("my-video").load();//video视频重新加载
		document.getElementById("my-video").play();
	})
})