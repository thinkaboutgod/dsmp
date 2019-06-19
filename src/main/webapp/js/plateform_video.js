var path = $("#path").val();
var videoPath = path+ "/plateform/searchVideoBySubect.action";
var theTitle;//修改视频标题用
var subject;
$(function() {
//	$("#subjectSelect").change(function() {//科目选择框值变化
//		subject = $("#subjectSelect").val();
//	})
	
	
	$("#search").click(function() {
		subject = $("#subjectSelect").val();
		if (subject == 0) {
			layer.msg("请选择科目");
			return;
		}
		searchVideo(subject,videoPath);//从video_util.js中的方法
	})
	
	// 修改标题模态框
	$(document).on("click", ".change", function() {
		$("#vidId").val($(this).attr("id"));
		$("#title").val($(this).prev().prev().text());
		theTitle = $(this).prev().prev();
		$("#forTitle").modal("show");
	})
	// 修改标题提交
	$("#changeTitle").click(function() {
		var vidId = $("#vidId").val();
		var title = $("#title").val();
		if ($.trim(title)=="") {
			layer.msg("标题不得为空");
			return;
		}
		var data = {
			vidId : vidId,
			vidTitle : title
		};
		$.ajax({
			url : path + "/plateform/changeTitleByVidId.action",
			ansyc : true,
			type : "POST",
			contentType : "application/json;charset=utf-8", // 如果采用requestbody那么一定要加

			data : JSON.stringify(data), // 为了控制类获取参数自动注入对象
			dataType : "text",
			success : function(data) {
				var result = JSON.parse(data);
				if (result.myresult == "success") {
					layer.msg("修改成功");
					theTitle.text(title);//修改标题文字
					$("#forTitle").modal("hide");
				}else if (result.myresult == "fialed"){
					layer.msg("修改失败");
				}
			},
			error : function() {
				layer.msg("服务器繁忙");
			}
		})
	})
	// 删除视频
	$(document).on("click", ".delete", function() {
		var vidId = $(this).attr("id");
		var vdiv = $(this).parent();
		layer.confirm('是否确认删除该视频？', {
			btn : [ '确定', '取消' ]
		// 按钮
		}, function(index) {
			$.ajax({
				url : path + "/plateform/deletVideoByVidId.action",
				ansyc : true,
				type : "POST",
				data : {
					vidId : vidId,
				},
				dataType : "text",
				success : function(data) {
					var result = JSON.parse(data);
					if (result.myresult == "success") {
						layer.msg("删除成功");
						setTimeout(() => {
							vdiv.empty();
//							$("#videoDiv").remove(vdiv);
							$("#pageoption .active a").click();//刷新当前页
						}, 1000);
					}else if (result.myresult == "fialed"){
						layer.msg("删除失败");
					}
				},
				error : function() {
					layer.msg("服务器繁忙");
				}
			})
			layer.close(index);
		}, function() {
		});
			
	})
	
	
	
	//打开视频上传模态框
	$("#addVideo").click(function() {
		$("#addVideoModal").modal("show");
	})
	var type = ["mp4"];
	var type2 = ["jpg","png"];
	initFileInput("upVideo", path+"/plateform/uploadVideo.action",type);//初始化视频上传控件
	initFileInput("upVideoImg", path+"/plateform/uploadVideoImg.action",type2);//初始化视频上传控件
	//点击上传下一步
	$("#next").click(function() {
		var upTitle = $("#upTitle").val();
		if ($.trim(upTitle)=="") {
			layer.msg("标题不得为空");
			return false;
		};
		var subject = $("#subjectUp").val();
		
		if (subject == 0) {
			layer.msg("请选择科目");
			return;
		}
		var count = $('#upVideo').fileinput('getFilesCount');//选中的文件个数
		if (count==0) {
			layer.msg("请选择文件");
			return false;
		}
		$("#upVideo").fileinput("upload");//触发上传
	})
	
	$("#up").click(function() {//点击上传图片
		
		if ($("#upVideoImg").val()=="") {
			layer.msg("请选择文件");
			return false;
		}
		var count = $('#upVideoImg').fileinput('getFilesCount');//选中的文件个数
		if (count==0) {
			layer.msg("请选择文件");
			return false;
		}
		$("#upVideoImg").fileinput("upload");//触发上传
	})
	
})
//以上为页面加载完毕执行


// 组装视频标签
	function videoDiv(result) {
		div = "";
		var di = "";
		var len = result.list.length
		for (var i = 0; i < len; i++) {
			di = '<div class="Product">'+ 
			'<video width="280" height="180" controls="controls" poster="'+result.data+ result.list[i].vidImgpath+'">'+ 
			'<source src="'+result.data+ result.list[i].vidPath+ '"  type="video/mp4">'+ 
			'</video><br>'+ 
			'<laber>'+ result.list[i].vidTitle+ '</laber><br>'+ 
			'<button type="button" class="btn btn-info btn-xs change"id="'+ result.list[i].vidId+ '" >修改标题</button>'
			+ '  <button type="button" class="btn btn-danger btn-xs delete"id="'+ result.list[i].vidId + '" >删除</button>' 
			+ '</div>'
			div += di;
		}
		return div;
	}

// result.data+
// result.data+
//文件上传
function initFileInput(ctrlName, uploadUrl,type) {
	var control = $('#' + ctrlName);
	control.fileinput({
		language : 'zh', // 设置语言
		uploadAsync : true,// 默认true异步上传,
		uploadUrl : uploadUrl, // 上传的地址
		allowedFileExtensions : type,// 接收的文件后缀
		showUpload : false, // 是否显示上传按钮
		showUploadedThumbs:false,
		showCaption : true,// 是否显示标题
		browseClass : "btn btn-primary", // 按钮样式
		dropZoneEnabled : false,// 是否显示拖拽区域 
		showCancel : false,
		showPreview : true, //是否显示预览,不写默认为true
		maxFileCount : 1, // 表示允许同时上传的最大文件个数
		enctype : 'multipart/form-data',
		previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
		layoutTemplates : {// 去除缩略图上的上传或者删除按钮
		// actionDelete:'',
			actionUpload:'',
//			preview: '',//是否显示预览
			actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
		},
		uploadExtraData : function() {// 向后台传递参数,文件描述
			var data = {
				subject	: $("#subjectUp").val(),
				vidTitle : $("#upTitle").val(),
			};
			return data;
		},
	})
	// 文件异步上传完成之后发生的事件

	$('#' + ctrlName).on("fileuploaded",
			function(event, data, previewId, index) {
				if (data.response.myresult=="success") {//视频上传成功
					setTimeout(() => {
						$("#addVideoModal").modal("hide");//关闭视频上传模态框
						$(event.target).fileinput('clear').fileinput('unlock') ;// 清空文件和输入框内容						
						clearUpload();
						$("#addVideoImgModal").modal("show");
					}, 800);
		            
				}else if (data.response.myresult=="successUpload") {//图片上传成功
					setTimeout(() => {
						layer.msg("上传成功");
						$("#addVideoImgModal").modal("hide");//关闭视频图片上传模态框
						$(event.target).fileinput('clear').fileinput('unlock') ;// 清空文件和输入框内容						
						$("#pageoption .active a").click();//刷新当前页
					}, 800);
				}else if (data.response.myresult=="failed") {
					layer.msg("上传失败，请重新上传");
				}
				
			});
	
	//异步上传失败返回结果处理
	  $('#' + ctrlName).on('fileerror', function(event, data, msg) {
	      console.log("fileerror");
	      console.log(data);
	      layer.msg("文件上传失败")
	  });
	  //选择文件后
	  $('#' + ctrlName).on("filebatchselected", function(event, files) {
//		  alert($('#' + ctrlName).fileinput('getFilesCount'));
		  var count = $('#' + ctrlName).fileinput('getFilesCount');//选中的文件个数
		  if (count>0) {
			  $("#next").removeAttr("disabled");//提交按钮能否提交
		}else {
			$("#next").attr("disabled",true);
		}
	  });
	function clearUpload() {
		 $("#upTitle").val("");
		$("#forSubject").val("");
	}
}