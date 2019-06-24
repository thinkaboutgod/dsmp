var path = $("#path").val();
var imgPath = path+ "/plateform/searchAdvertise.action";
var type;//弹窗类型，change代表修改，add代表新增
$(function() {
	searchVideo(0,imgPath);//在video_utils工具类中，此处0代表搜索所有，因为是调用了视频的工具类，所以名字看起来不符合
	//点击搜索
	$("#search").click(function() {
		searchVideo($("#level").val(),imgPath);//在video_utils工具类中，数字代表类型
	})
	//点击修改按钮
	$(document).on("click",".change",function(){
		$("#myModalLabel").text("修改广告");
		$("#imgLable").text("更换广告图片（可不选）");
		$("#advId").val($(this).attr("id"));
		
		$("#schoolId").val($(this).prev().prev().prev().val());
		$("#schoolId").attr("disabled","disabled");
		$("#toPath").val($(this).prev().prev().val());
		$("#describe").val($(this).prev().val());
		$("#newLevel").val($(this).next().val());//优先级
		$("#adPathDiv").css("display","block");
		$("#forSchoolAdv").modal("show");
		type="change";
	})
	//点击添加
	$("#addAdvertise").click(function() {
		forAdd();
		$("#forSchoolAdv").modal("show");
		type = "add";
	})
	//新增，清空输入框，修改文字
	function forAdd() {
		$("#advId").val("");
		$("#toPath").val("");
		$("#describe").val("");
		$("#myModalLabel").text("新增广告");
		$("#imgLable").text("选择广告图片（必选）");
		$("#schoolId").removeAttr("disabled");
		$("#schoolId").val("0");
		$("#newLevel").val("0");
		$("#newImg").val("");
		$("#toPath").val($("#adPath").val());
		$("#adPathDiv").css("display","none");
	}
	//修改或者新增提交
	$("#sub").click(function() {
		var toPath = $("#toPath").val();
		var describe = $("#describe").val();
		var schId = $("#schoolId").val();
		var fileName = $("#newImg").val();
		var advId = $("#advId").val();
		var adlId = $("#newLevel").val();
		if ($.trim(toPath)==""||$.trim(describe)=="") {
			layer.msg("请将信息填写完整");
			return;
		}
		if (adlId=="0") {
			layer.msg("请选择广告优先级");
			return;
		}
		if (type=="add") {
			if (schId=="0") {
				layer.msg("请选择所属驾校");
				return;
			}
			if ($.trim(fileName)=="") {
				layer.msg("请选择广告图片");
				return;
			}
		}
		//判断图片类型 
		if(fileName!=""){
			var suffixIndex=fileName.lastIndexOf(".");  
		   var suffix=fileName.substring(suffixIndex+1).toUpperCase();  
		   if(suffix!="BMP"&&suffix!="JPG"&&suffix!="JPEG"&&suffix!="PNG"&&suffix!="GIF"){  
		     layer.msg( "图片格式只能为：BMP、JPG、JPEG、PNG、GIF）!"); 
		  	return; 
		   } 
		}
		//ajaxFileUpload上传带的参数只能为键值对字符串，不能为json对象
		$.ajaxFileUpload({
			url : path+"/plateform/forAdvertise.action",
			type : "POST",
			secureuri : false, // 一般设置为false
			fileElementId : "newImg",// 上传文件的id、name属性名
			dataType : "text",
			data :{
				toPath : toPath,
				describe : describe,
				schId : schId,
				advId : advId,//广告id
				adlId : adlId,//广告优先级
				type : type//类型，修改还是新增
			},
			success : function(data) {
				if (data == "success") {
					switch (type) {
					case "change":
						layer.msg('修改成功', {
							time : 2000
						});
						break;
					case "add":
						layer.msg('添加成功', {
							time : 2000
						});
						break;
					}
					
					setTimeout(() => {
						$("#forSchoolAdv").modal("hide");
					}, 1000);
					$("#pageoption .active a").click();//刷新当前页
				} else {
					switch (type) {
					case "change":
						layer.msg('修改失败,请重试', {
							time : 2000
						});
						break;
					case "add":
						layer.msg('添加失败,请重试', {
							time : 2000
						});
						break;
					}
					
					$("#forSchoolAdv").modal("hide");
				}
			},
			error : function() {
				layer.msg("服务器繁忙");
			}
		})//ajax结束
		
		
	})
	
	//删除题目
	$(document).on("click",".delete",function(){
		var advId = $("#advId").val($(this).attr("id"));//获取当前广告的id
		layer.confirm('是否确认删除该数据？', {
			btn : [ '确定', '取消' ]
		// 按钮
		}, function(index) {
			$.ajax({
				url : path + "/plateform/deleteAdvertise.action",
				ansyc : true,
				type : "POST",
				data : {advId:advId}, 
				dataType : "text",
				success : function(data) {
					var result = JSON.parse(data);
					if (result.myresult == "success") {
						layer.msg("删除成功");
						$("#pageoption .active a").click();//刷新当前页
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
	
})

// 组装图片标签
	function videoDiv(result) {
		div = "";
		var di = "";
		var len = result.list.length
		for (var i = 0; i < len; i++) {
			di = '<div class="Product">'+ 
			'<img style="width:260px;height:160px;" src="'+result.data+result.list[i].advImgpath+'" >'+
			'<br><laber>所属驾校：'+ result.list[i].tbSchool.schName+ '</laber><br>'+ 
			'<input type="hidden" value="'+result.list[i].schId+'">'+
			'<input type="hidden" value="'+result.list[i].advPath+'">'+
			'<input type="hidden" value="'+result.list[i].advDescribe+'">'+
			'<button type="button" class="btn btn-info btn-xs change"id="'+ result.list[i].advId+ '" >修改</button>'
			+'<input type="hidden" value="'+result.list[i].adlId+'">'
			+ '  <button type="button" class="btn btn-danger btn-xs delete"id="'+ result.list[i].advId + '" >删除</button>' 
			+ '</div>'
			div += di;
		}
		return div;
	}