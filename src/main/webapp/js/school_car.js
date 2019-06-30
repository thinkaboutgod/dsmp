$(function() {

	$.extend($.fn.dataTable.defaults, dataTableSeetings); // 公共初始化设置

	datatable_otherSet = {
		"ajax": "../car/selectCarsByCondition.action",
		"columns": [{
				"data": "carPlatenum"
			},
			{
				"data": "carStyle"
			},
			{
				"data": "carColor"
			},
			{
				"data": "tbCoach.coaName",
				"render": function(data, type, row, meta) {
					if(data == null) {
						return data = "无";
					}
					return data;
				},
				"orderable": false, // 禁用排序
			},
			{
				"data": "carUsedTime",
			},
			{
				"data": "carStatus",
				"orderable": false, // 禁用排序
			},
			{
				"data": "carUsedTime",
				"orderable": false, // 禁用排序
				"sDefaultContent": '',
				"sWidth": "",
				"render": function(data, type, full, meta) {
					usedtime = data;
					data = "";
					var year = parseInt(usedtime.split("年")[0])
					if(year >= 10) {
						data = '<button  class="btn btn-danger btn-sm bt_scrap" >报废</button> &nbsp;';
					}
					data = data + ' <button  class="detail btn btn-primary btn-sm " >查看信息</button>';
					return data;
				}
			},
		],

		"fnServerParams": function(aoData) { //设置参数
			aoData._rand = Math.random();
			aoData.push({
					"name": "carPlateNum",
					"value": $("#carPlateNum").val()
				}, {
					"name": "coachName",
					"value": $("#coachName").val()
				}, {
					"name": "schId",
					"value": $("#schId").val()
				}, {
					"name": "beginTime",
					"value": $("#begintime").val()
				}, {
					"name": "endTime",
					"value": $("#endtime").val()
				}

			);
		},

	};

	var table = $("#studentTable").DataTable(datatable_otherSet); //初始化

	// 选择行,两个表格公用
	$('tbody').on('click', 'tr', function() {
		if($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});

	//自定义搜索
	$("#buttonsearch").on("click", function() {
		table.ajax.reload(null, false); // 刷新数据方法,false代表保持当前页
	})

	// 车辆报废
	$(document).on("click", ".bt_scrap", function() {
		//此处拿到选择行的数据中的id 
		var id = table.row($(this).parent().parent()).data().carId;

		var button = $(this);
		var preText = button.parent().prev().text();
		var text = $(this).text();
		var state;
		if("报废" == text) {
			state = "已报废";
		};
		layer.confirm('您是否确定要申请报废？', {
			btn: ['确定', '取消'] //按钮
		}, function() {
			if(preText == "已报废") {
				layer.msg("已申请报废，无需再点");
			} else {
				$.ajax({
					url: "../car/scrapCar.action",
					async: true,
					type: "POST",
					data: {
						carId: id,
						state: state,
						preText: preText
					},
					dataType: "text",
					success: function(data) {
						var result = JSON.parse(data);
						if(result.myresult == "success") {
							layer.msg("修改成功");
							button.parent().prev().text("已报废");
						} else if(result.myresult == "failed") {
							layer.msg("修改失败");
						}
					},
					error: function() {
						layer.msg("服务器繁忙");
					}
				})
			}
		}, function() {
			layer.msg("操作已取消");
		});

	})

	// 查看详细信息
	$(document).on("click", ".detail", function() {
		//此处拿到选择行的数据中的id 
		var da = table.row($(this).parent().parent()).data();
		$("#carId").val(da.carId);
		$("#carPlateNumDe").val(da.carPlatenum);
		$("#carImgDe").attr("src", da.carImg);
		$("#carStyleDe").val(da.carStyle);
		$("#carColorDe").val(da.carColor);
		$("#coachNameDe").val(da.tbCoach.coaName);
		$("#carStartTimeDe").val(da.carStartTime);
		$("#carUsedTimeDe").val(da.carUsedTime);
		var schId = $("#schId").val();
		$.ajax({
			url: "../school/selectCoach.action?",
			async: true,
			data: {
				"selectSchool": schId
			},
			dataType: "text",
			success: function(res) {
				var arr = JSON.parse(res);
				$("#coachs").empty();
				$("#coachs").append("<option value='0'>请选择教练</option>");
				for(var i = 0; i < arr.length; i++) {
					$("#coachs").append("<option value=" + arr[i].coaId + ">" + arr[i].coaName + "</option>");
				}
			},
			error: function() {
				alert("操作失败！");
			}
		})

		$("#carDetail").modal("show");

		$("#btn_distribute").click(function() {
			var carId = $("#carId").val();
			var coaId = $("#coachs").val();
			if(coaId > 0) {
				$.ajax({
					url: "../car/distributeCar.action",
					async: true,
					type: "POST",
					data: {
						carId: carId,
						coaId: coaId,
					},
					dataType: "text",
					success: function(data) {
						var result = JSON.parse(data);
						if(result.myresult == "success") {
							layer.msg("车辆分配成功");
							$("#carDetail").modal('hide');
							table.ajax.reload(null, false);
						} else if(result.myresult == "failed") {
							layer.msg("车辆分配失败");
						}
					},
					error: function() {
						layer.msg("服务器繁忙");
					}
				})
			} else {
				layer.msg("分配前，请选择教练");
			}

		})
	})

	// 打开添加车辆模态框
	$(document).on("click", "#btn_addCar", function() {
		$("#addCar").modal("show");
	})

	// 提交添加教练车
	$(document).on("click", "#btn_add", function() {
		var schId = $("#schId").val();
		var carPlateNum = $.trim($("#carPlateNumNew").val());
		var carStyle = $.trim($("#carStyleNew").val());
		var carColor = $.trim($("#carColorNew").val());

		if(carPlateNum == "" || carPlateNum == $("#carPlateNumNew").attr("placeholder")) {
			layer.msg("请输入车牌号");
			return false;
		}

		if(carStyle == "" || carStyle == $("#carStyleNew").attr("placeholder")) {
			layer.msg("请选择车型");
			return false;
		}

		if(carColor == "" || carColor == $("#carColorNew").attr("placeholder")) {
			layer.msg("请输入颜色");
			return false;
		}

		//判断图片类型
		var fileName = $("#carImgNew").val(); 
		if(fileName != "") {
			var suffixIndex = fileName.lastIndexOf(".");  
			var suffix = fileName.substring(suffixIndex + 1).toUpperCase();  
			if(suffix != "BMP" && suffix != "JPG" && suffix != "JPEG" && suffix != "PNG" && suffix != "GIF") {    
				layer.msg("图片格式只能为：BMP、JPG、JPEG、PNG、GIF）!");  
				return false;
			} 
		} else {
			layer.msg("请上传车辆照片");
			return false;
		}

		//ajaxFileUpload上传带的参数只能为键值对字符串，不能为json对象
		$.ajaxFileUpload({
			url: "../car/addCar.action",
			type: "POST",
			secureuri: false, // 一般设置为false
			fileElementId: "carImgNew", // 上传文件的id、name属性名
			dataType: "text",
			data: {
				schId: schId,
				carPlateNum: carPlateNum,
				carStyle: carStyle,
				carColor: carColor,
			},

			success: function(data) {
				if(data == "success") {
					layer.msg('添加成功', {
						time: 2000
					});
					$(".add").val("");
					$("#carImgNew").val("");
					table.ajax.reload(null, false);
				} else {
					layer.msg('添加失败,请重试', {
						time: 2000
					});
				}
			},
			error: function() {
				layer.msg("服务器繁忙");
			}
		})
	})

	//去除所选的图片
	$("#deleteCarImg").click(function() {
		$("#carImgNew").val("");
	})

})