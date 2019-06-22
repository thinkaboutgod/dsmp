$(function() {

	layui.use(['upload', 'form'], function() {
		var $ = layui.jquery,
			upload = layui.upload,
			form = layui.form;

		var schId = $("#schId").val();
		alert(schId);
		$.ajax({
			url: "../school/selectCoach.action?",
			async: true,
			data: {
				"selectSchool": schId
			},
			dataType: "text",
			success: function(res) {
				var arr = JSON.parse(res);
				for(var i = 0; i < arr.length; i++) {
					console.log(arr[i].coaName);
					$("#teachers").append("<option value=" + arr[i].coaId + ">" + arr[i].coaName + "</option>");
				}
				form.render('select'); // 刷新select选择框渲染
			},
			error: function() {
				alert("操作失败！");
			}
		})
		upload.render({
			elem: '#idCard',
			url: '../idCard/idCard.action',
			auto: false
				// ,multiple: true
				,
			choose: function(obj) { // 上传前选择回调方法
				var flag = true;
				obj.preview(function(index, file, result) {
					console.log(file); // file表示文件信息，result表示文件src地址
					var img = new Image();
					img.src = result;
					img.onload = function() { // 初始化夹在完成后获取上传图片宽高，判断限制上传图片的大小。
						if(img.width <= 3500 && img.height <= 3500) {
							obj.upload(index, file); // 满足条件调用上传方法
							layer.msg("识别身份证中，请稍等....");
						} else {
							flag = false;
							layer.msg("您上传的图片分辨率必须小于3500*3500！");
							return false;
						}
					}
					return flag;
				});
			},
			done: function(res) {
				console.log(res);
				if(res.name != "") {
					$("#real_name").val(res.name);
					$("#idnum").val(res.IDCard);
					$("#student_address").val(res.address);
					$("#sex").val(res.sex);
					layer.msg("识别成功！");
				} else {
					layer.msg("请选择身份证图片进行识别！", {
						time: 5000,
						btn: ["知道了"]
					});
				}

			}
		});
	})

	$.extend($.fn.dataTable.defaults, dataTableSeetings); // 公共初始化设置

	datatable_otherSet = {
		"ajax": "../student/searchAllStudent.action",
		"columns": [{
				"data": "stuAccount"
			},
			{
				"data": "stuName"
			},
			{
				"data": "stuSex",
				"orderable": false, // 禁用排序
			},
			{
				"data": "stuSignuptime",
				"render": function(data, type, full, meta) {
					return data = new Date(data).format("yyyy-MM-dd hh:mm:ss");
				}
			},
			{
				"data": "tbCoach.coaName",
				"orderable": false, // 禁用排序
			},
			{
				"data": "tbSubject.subName",
				"orderable": false, // 禁用排序
			},
			{
				"data": "stuStatus",
				"orderable": false, // 禁用排序
			},
			{
				"data": "stuVerifystatus",
				"orderable": false, // 禁用排序
			},
			{
				"data": "stuId",
				"orderable": false, // 禁用排序
				"sDefaultContent": '',
				"sWidth": "",
				"render": function(data, type, row, meta) {
					data = "";
					state = row.stuStatus;
					if(state == '启用') {
						data = '<button  class="btn btn-danger btn-sm bt_qi" >禁用</button>&nbsp;';
					} else if(state == '禁用') {
						data = '<button  class="btn btn-success btn-sm bt_qi" >启用</button>&nbsp;';
					}
					stuVerifystatus = row.stuVerifystatus;
					if(stuVerifystatus == '未审核') {
						data = data + '<button  class="btn btn-danger btn-sm check" >审核</button>&nbsp;';
					}
					data = data + '<button  class="detail btn btn-primary btn-sm " >查看信息</button>';
					return data;
				}
			},
		],
		"fnServerParams": function(aoData) { // 设置参数
			aoData._rand = Math.random();
			aoData.push({
				"name": "name",
				"value": $("#sname").val()
			}, {
				"name": "account",
				"value": $("#saccount").val()
			}, {
				"name": "schId",
				"value": $("#schId").val()
			}, {
				"name": "beginTime",
				"value": $("#begintime").val()
			}, {
				"name": "endTime",
				"value": $("#endtime").val()
			});
		},

	};

	var table = $("#studentTable").DataTable(datatable_otherSet); // 初始化

	// 
	$('tbody').on('click', 'tr', function() {
		if($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});

	// 自定义搜索
	$("#buttonsearch").on("click", function() {
		table.ajax.reload(null, false); // 刷新数据方法,false代表保持当前页
	})

	// 启用禁用方式
	$(document).on("click", ".bt_qi", function() {
		// 此处拿到选择行的数据中的id
		var id = table.row($(this).parent().parent()).data().stuId;

		var button = $(this);
		var preText = button.parent().prev().prev().text();
		var text = $(this).text();
		// var id = table.rows('.selected').data()[0].cuid;
		var state;
		if("启用" == text) {
			state = "start"
		} else if("禁用" == text) {
			state = "forbid"
		};
		$.ajax({
			url: "../student/changeStudentState.action",
			async: true,
			type: "POST",
			data: {
				stuId: id,
				state: state,
				preText: preText
			},
			dataType: "text",
			success: function(data) {
				var result = JSON.parse(data);
				if(result.myresult == "success") {
					layer.msg("修改成功");
					switch(text) {
						case "启用":
							button.parent().prev().prev().text("启用");
							button.text("禁用");
							button.removeClass("btn-success");
							button.addClass("btn-danger");
							break;
						case "禁用":
							button.parent().prev().prev().text("禁用");
							button.text("启用");
							button.removeClass("btn-danger");
							button.addClass("btn-success");
							break;
					}
				} else if(result.myresult == "failed") {
					layer.msg("修改失败");
				}
			},
			error: function() {
				layer.msg("服务器繁忙");
			}
		})
	})

	// 查看审核信息
	$(document).on("click", ".check", function() {
		// 此处拿到选择行的数据中的
		var da = table.row($(this).parent().parent()).data();
		console.log(da)
		$("#stuId").val(da.stuId);
		$("#nameCheck").val(da.stuName);
		$("#phoneCheck").val(da.stuAccount);
		$("#addressCheck").val(da.stuAddress);
		$("#coachNameCheck").val(da.tbCoach.coaName);
		var schId = $("#schId").val();
		alert(schId);
		$.ajax({
			url: "../school/selectCoach.action?",
			async: true,
			data: {
				"selectSchool": schId
			},
			dataType: "text",
			success: function(res) {
				alert("获取教练")
				var arr = JSON.parse(res);
				$("#coachs").append("<option value='0'>请选择教练</option>");
				for(var i = 0; i < arr.length; i++) {
					console.log(arr[i].coaName);
					$("#coachs").append("<option value=" + arr[i].coaId + ">" + arr[i].coaName + "</option>");
				}
			},
			error: function() {
				alert("操作失败！");
			}
		})
		$("#studentCheck").modal("show");
		$("#btn_check_yes").click(function() {
			alert("审核通过")
			var stuId = $("#stuId").val();
			var coaId = $("#coachs").val();
			if($("#coachNameCheck").val() != null) {
				$.ajax({
					url: "../student/checkStudent.action",
					async: true,
					type: "POST",
					data: {
						stuId: stuId,
						coaId: coaId,
						stuVerifystatus: "已审核"
					},
					dataType: "text",
					success: function(data) {
						var result = JSON.parse(data);
						if(result.myresult == "success") {
							layer.msg("审核成功");
							$("#studentCheck").modal('hide');
							table.ajax.reload(null, false);
						} else if(result.myresult == "failed") {
							layer.msg("审核失败");
						}
					},
					error: function() {
						layer.msg("服务器繁忙");
					}
				})
			} else {
				if(coaId != 0) {
					$.ajax({
						url: "../student/checkStudent.action",
						async: true,
						type: "POST",
						data: {
							stuId: stuId,
							coaId: coaId,
							stuVerifystatus: "已审核"
						},
						dataType: "text",
						success: function(data) {
							var result = JSON.parse(data);
							if(result.myresult == "success") {
								layer.msg("审核成功");
								$("#studentCheck").modal('hide');
								table.ajax.reload(null, false);
							} else if(result.myresult == "failed") {
								layer.msg("审核失败");
							}
						},
						error: function() {
							layer.msg("服务器繁忙");
						}
					})
				} else {
					layer.msg("请为学员分配教练");
				}
			}

		})

		$("#btn_check_no").click(function() {
			alert("审核失败")
			var stuId = $("#stuId").val();
			var coaId = 0;
			$.ajax({
				url: "../student/checkStudent.action",
				async: true,
				type: "POST",
				data: {
					stuId: stuId,
					coaId: coaId,
					stuVerifystatus: "审核不通过"
				},
				dataType: "text",
				success: function(data) {
					var result = JSON.parse(data);
					if(result.myresult == "success") {
						layer.msg("审核成功");
						$("#studentCheck").modal('hide');
						table.ajax.reload(null, false);
					} else if(result.myresult == "failed") {
						layer.msg("审核失败");
					}
				},
				error: function() {
					layer.msg("服务器繁忙");
				}
			})
		})
	})

	// 查看详细信息
	$(document).on("click", ".detail", function() {
		// 此处拿到选择行的数据中的id
		var da = table.row($(this).parent().parent()).data();
		$("#name").val(da.stuName);
		$("#phone").val(da.stuAccount);
		$("#address").val(da.stuAddress);
		$("#coachName").val(da.tbCoach.coaName);
		$("#studentDetail").modal("show");
	})

	$(document).on("click", "#btn_addStudent", function() {
		$("#addStudent").modal("show");
	})

	var video = document.getElementById("video");
	var canvas = document.getElementById("canvas");
	var context = canvas.getContext("2d");
	var mediaStreamTrack = null;

	$("#open").click(function() {
		openUserMedia();
	})

	$("#photograph").click(function() {
		alert("拍照了");
		getFace(context);
	})

	$("#btn_add").click(function() {
		// 获取浏览器页面的画布对象
		// 以下开始编 数据
		// 将图像转换为base64数据
		alert("点击添加学员")
		var imageBase64 = canvas.toDataURL();
		var blob = dataURItoBlob(imageBase64);
		var name = $("#real_name").val();
		var idCard = $("#idnum").val();
		var address = $("#student_address").val();
		var sex = $("#sex").val();
		var schId = $("#schId").val();
		var coaId = $("#teachers").val();
		var phone = $("#student_phone").val();
		var fd = new FormData(document.forms[0]);
		// 上一步中的函数
		fd.append("file", blob);
		fd.append("filename", phone + '.jpg');
		fd.append("name", name);
		fd.append("idCard", idCard);
		fd.append("address", address);
		fd.append("sex", sex);
		fd.append("schId", schId);
		fd.append("coaId", coaId);
		fd.append("phone", phone);
		if(name == "" || idCard == "" || address == "" || sex == "" || coaId == "" || phone == "") {

		} else if(isCanvasBlank(canvas)) {
			layer.msg("请上传一张头像，用于刷脸打卡计时！");
		} else {
			$.ajax({
				type: "POST",
				url: "../student/addStudent.action?",
				processData: false,
				// 必须
				contentType: false,
				// 必须
				data: fd,
				datatype: "text",
				success: function(msg) {
					if(msg.myresult == "success") {
						layer.msg("添加学员成功");
						table.ajax.reload(null, false);
					} else if(msg.myresult == "already") {
						layer.msg("添加学员失败，该账号已经报名!");
					} else {
						layer.msg("添加学员失败");
					}
				},
				error: function() {
					// 请求出错处理
					layer.msg("操作失败！");
				}
			});
		}
	})

	function dataURItoBlob(base64Data) {
		var byteString;
		if(base64Data.split(',')[0].indexOf('base64') >= 0)
			byteString = atob(base64Data.split(',')[1]);
		else
			byteString = unescape(base64Data.split(',')[1]);
		var mimeString = base64Data.split(',')[0].split(':')[1].split(';')[0];
		var ia = new Uint8Array(byteString.length);
		for(var i = 0; i < byteString.length; i++) {
			ia[i] = byteString.charCodeAt(i);
		}
		return new Blob([ia], {
			type: mimeString
		});
	}
	// 验证canvas画布是否为空函数
	function isCanvasBlank(canvas) {
		var blank = document.createElement('canvas'); // 系统获取一个空canvas对象
		blank.width = canvas.width;
		blank.height = canvas.height;
		return canvas.toDataURL() == blank.toDataURL(); // 比较值相等则为空
	}

	function success(stream) {
		// 兼容webkit核心浏览器
		// var CompatibleURL = window.URL || window.webkitURL;
		// 将视频流转化为video的源
		mediaStreamTrack = stream;
		try {
			// video.src = CompatibleURL.createObjectURL(stream);
			video.srcObject = stream;
			video.play(); // 播放视频

			// 将视频绘制到canvas上
		} catch(e) {
			layer.msg("开启摄像头失败，请刷新网页重试", {
				time: 20000, // 20s后自动关闭
				btn: ['知道了']
			});
			// alert("开启摄像头失败，请刷新网页重试");
		}

	}
	// 错误回调函数
	function error(error) {
		buttonHide();
		layer.msg("您刚刚选择了禁止浏览器启动摄像头，请刷新网页并允许浏览器调用摄像头，来完成验证", {
			time: 20000, // 20s后自动关闭
			btn: ['知道了']
		});
		// alert("您选择了禁止浏览器启动摄像头，请刷新网页并允许浏览器调用摄像头，来完成验证")
	}

	function getUserMediaToPhoto(constraints, success, error) {
		if(navigator.mediaDevices.getUserMedia) {
			// 最新标准API
			navigator.mediaDevices.getUserMedia(constraints).then(success).catch(error);
		} else if(navigator.webkitGetUserMedia) {
			// webkit核心浏览器
			navigator.webkitGetUserMedia(constraints, success, error);
		} else if(navigator.mozGetUserMedia) {
			// firefox浏览器
			navigator.mozGetUserMedia(constraints, success, error);
		} else if(navigator.getUserMedia) {
			// 旧版API
			navigator.getUserMedia(constraints, success, error);
		}
	}
	// 获取图片
	function getFace(context) {
		context.drawImage(video, 0, 0, 350, 220);
	}
	// 打开摄像头
	function openUserMedia() {
		if(navigator.mediaDevices.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.getUserMedia) {
			getUserMediaToPhoto({
				video: {
					width: 370,
					height: 250,
					facingMode: "user"
				}
			}, success, error);
		} else {
			layer.msg("您的浏览器不支持访问用户媒体设备", {
				time: 20000, // 20s后自动关闭
				btn: ['知道了']
			});
			// alert('你的浏览器不支持访问用户媒体设备');
		}
	}

	function offUserMedia() { // 关闭摄像头
		if(mediaStreamTrack != null) {
			mediaStreamTrack.getTracks()[0].stop();
		}

	}

	// 关闭摄像头
	function closeVideo() {
		offUserMedia();
		video.srcObject = null;
	}

})