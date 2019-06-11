var path = $("#path").val();
var videoPath = path+ "/plateform/searchVideoBySubect.action";
$(function() {

	$(".subject").click(function() {
		var subject = $(this).attr("title");
		searchVideo(subject,videoPath);//从video_util.js中的方法
	})
	
	$(document).on("mouseenter",".myImg",function(){//鼠标进入
		$(this).css({"width":"290px","height":"190px"});
	})
	$(document).on("mouseleave",".myImg",function(){//鼠标移除
		$(this).css({"width":"280px","height":"180px"});
	})
})


//组装视频标签
function videoDiv(result) {
	div = "";
	var di = "";
	var len = result.list.length
	for (var i = 0; i < len; i++) {
		di = '<div class="Product">'
		+'<a target="_blank" href="'+path+'/video/toSearchVideo.action?vidImgpath='+result.list[i].vidImgpath
		+'&vidPath='+result.list[i].vidPath+'&suject='+ result.list[i].subId+'&vidTitle='+ result.list[i].vidTitle+'&subName='+ result.list[i].tbSubject.subName+'">'	
		+'<img class="myImg" style="width:280px;height:180px;" src="'+path+ result.list[i].vidImgpath+'" alt=""><br>'
		+'<input  id="vidPath" type="hidden" val="'+path+ result.list[i].vidPath+'" >'
		+'<laber>'+ result.list[i].vidTitle+ '</laber>'
		+'</a>'
		+ '</div>';
		div += di;
	}
	return div;
}
