function searchVideo(subject,videoPath) {//视频分页
	
	var dt = $("#videoDiv");// 装视频的div
	$.ajax({
		url : videoPath,
		ansyc : true,
		type : "POST",
		data : {
			subject : subject,
			page : 1
		},
		dataType : "text",
		success : function(data) {
			$("#info").text("当前选择科目为：" + $("#subjectSelect").find("option:selected").text())
			dt.empty();// 清空原页面视频
			var result = JSON.parse(data);
			var div = videoDiv(result);// 组装视频标签

			$("#videoDiv").append(div);// 添加视频

			var currentPage = result.pageIndex;
			var totalPages = result.totalPage;
			// 按钮
			var options = {
				bootstrapMajorVersion : 3,
				currentPage : currentPage,
				totalPages : totalPages,
				
				numberOfPages : totalPages,
				itemContainerClass : function(type,
						page, current) {
					return (page === current) ? "active"
							: "";
				},
				alignment : "center",
				// shouldShowPage : true,
				itemTexts : function(type, page,
						current) {
					switch (type) {
					case "first":

						return "首页";
					case "prev":

						return "上一页";
					case "next":

						return "下一页";
					case "last":

						return "末页";
					case "page":

						return page;

					}
				},
				// 给每个按钮挂载监听
				onPageClicked : function(event,originalEvent, type, page) {
					dt.empty();
					$.ajax({
						async : true,
						url : videoPath,
						type : "POST",
						dataType : "text",
						data : {
							subject : subject,
							page : page
						},
						success : function(data) {
							dt.empty();
							var result = JSON.parse(data);
							var div = videoDiv(result);

							$("#videoDiv").append(div);

						}/* success */
					});

				}
			}

			$('#pageoption').bootstrapPaginator(options);

		},
		error : function() {
			layer.msg("服务器繁忙");
		}
	})
}