layui.use('element', function() {
	var $ = layui.jquery,
		element = layui.element;
});
layui.use(['layer', 'form'], function() {
	var layer = layui.layer,
		form = layui.form;
});

$(function() {

	$.extend($.fn.dataTable.defaults, dataTableSeetings); // 公共初始化设置

	var table

	datatable_otherSet = {
		"ajax": {
			"url": "../student/selectStudentScore.action",
			"type": "POST"
		},
		"columns": [{
				"data": "stuName",
				"orderable": true
			},
			{
				"data": "tbCoach.coaName"
			},
			{
				"data": "scoreList.0.susScore",

				"render": function(data, type, full, meta) {
					if(data == null) {
						data = "无"
					}
					return data;
				}
			},
			{
				"data": "scoreList.1.susScore",
				"render": function(data, type, full, meta) {
					if(data == null) {
						data = "无"
					}
					return data;
				}
			},
			{
				"data": "scoreList.1.subId",
				"sDefaultContent": '',
				"sWidth": "",
				"orderable": false, // 禁用排序
				"render": function(data, type, row, meta) {
					data = "";
					if(row.scoreList[0].susScore >= 90 &&
						row.scoreList[1] == null&&row.stuBookingstate=="已预约") {
						data = '<button class="btn btn-default btn-sm add2">录入</button>';
					}
					if(row.scoreList[1] != null &&
						row.scoreList[1].susScore >= 0&&row.stuBookingstate=="已预约") {
						data = '<button class="btn btn-default btn-sm update2">修改</button>';
					}
					return data;
				}
			},
			{
				"data": "scoreList.2.susScore",
				"render": function(data, type, full, meta) {
					if(data == null) {
						data = "无"
					}
					return data;
				}
			},
			{
				"data": "scoreList.2.subId",
				"sDefaultContent": '',
				"sWidth": "",
				"orderable": false, // 禁用排序
				"render": function(data, type, row, meta) {
					data = "";
					if(row.scoreList[1].susScore >= 80 &&
						row.scoreList[2] == null&&row.stuBookingstate=="已预约") {
						data = '<button  class="btn btn-default btn-sm add3">录入</button>';

					}
					if(row.scoreList[2] != null &&
						row.scoreList[2].susScore >= 0&&row.stuBookingstate=="已预约") {
						data = '<button  class="btn btn-default btn-sm update3" >修改</button>';
					}
					return data;
				}
			}, {
				"data": "scoreList.3.susScore",
				"render": function(data, type, full, meta) {
					if(data == null) {
						data = "无"
					}
					return data;
				}
			}

		],
		columnDefs: [{
			"targets": [3, 5],
			createdCell: function(cell, cellData, rowData, rowIndex, colIndex) {
				$(cell)
					.click(
						function() {
							$(this)
								.html(
									'<input type="text" size="16" style="width: 100%"/>');
							var aInput = $(this).find(":input");
							aInput.focus().val(cellData);
						});
				$(cell).on("blur", ":input", function() {
					var text = $(this).val();
					$(cell).html(text);
					table.cell(cell).data(text)
				})
			}
		}],
		"fnServerParams": function(aoData) { // 设置参数
			aoData._rand = Math.random();
			aoData.push({
				"name": "schId",
				"value": $("#schId").val()
			});
		},
	}

	table = $("#score").DataTable(datatable_otherSet); // 初始化

	$(document).on("click", ".add2", function() {
		var stuId = table.row($(this).parent().parent()).data().stuId;
		alert(stuId)
		var subId = "2";
		var score = $(this).parent().prev().text();
		if(score < 0 || score > 100) {
			layer.msg("请输入有效成绩");
		} else {
			$.ajax({
				type: "POST",
				async: true,
				url: "../school/addScore.action",
				data: {
					stuId: stuId,
					subId: subId,
					score: score
				},
				dataType: "text",
				success: function(data) {
					var msg = JSON.parse(data);
					if(msg.myresult == "success") {
						layer.msg("添加科目二成绩成功，成绩不合格，需重新预约考试");
					} else if(msg.myresult == "next") {
						layer.msg("添加科目二成绩成功，成绩合格，进入下一阶段");
					} else {
						layer.msg("添加科目二成绩失败");

					}
					table.ajax.reload(null, false);
				},
				error: function() {
					// 请求出错处理
					layer.msg("操作失败！");
					table.ajax.reload(null, false);
				}
			});
		}
	})

	$(document).on("click", ".update2", function() {
		alert("luru")
		var stuId = table.row($(this).parent().parent()).data().stuId;
		var susId = table.row($(this).parent().parent()).data()[1].susId;
		var subId = "2";
		var score = $(this).parent().prev().text();
		if(score < 0 || score > 100) {
			layer.msg("请输入有效成绩");
		} else {
			$.ajax({
				type: "POST",
				async: true,
				url: "../school/addScore.action",
				data: {
					stuId: stuId,
					subId: subId,
					score: score,
					susId: susId
				},
				dataType: "text",
				success: function(data) {
					var msg = JSON.parse(data);
					if(msg.myresult == "success") {
						layer.msg("修改科目二成绩成功，成绩不合格，需重新预约考试");
					} else if(msg.myresult == "next") {
						layer.msg("修改科目二成绩成功，成绩合格，进入下一阶段");
					} else {
						layer.msg("修改科目二成绩失败");

					}
					table.ajax.reload(null, false);
				},
				error: function() {
					// 请求出错处理
					layer.msg("操作失败！");
					table.ajax.reload(null, false);
				}
			});
		}
	})

	$(document).on("click", ".add3", function() {
		var stuId = table.row($(this).parent().parent()).data().stuId;
		alert(stuId)
		var subId = "3";
		var score = $(this).parent().prev().text();
		if(score < 0 || score > 100) {
			layer.msg("请输入有效成绩");
		} else {
			$.ajax({
				type: "POST",
				async: true,
				url: "../school/addScore.action",
				data: {
					stuId: stuId,
					subId: subId,
					score: score
				},
				dataType: "text",
				success: function(data) {
					var msg = JSON.parse(data);
					if(msg.myresult == "success") {
						layer.msg("添加科目三成绩成功，成绩不合格，需重新预约考试");
					} else if(msg.myresult == "next") {
						layer.msg("添加科目三成绩成功，成绩合格，进入下一阶段");
					} else {
						layer.msg("添加科目三成绩失败");

					}
					table.ajax.reload(null, false);
				},
				error: function() {
					// 请求出错处理
					layer.msg("操作失败！");
					table.ajax.reload(null, false);
				}
			});
		}
	})

	$(document).on("click", ".update3", function() {
		var stuId = table.row($(this).parent().parent()).data().stuId;
		var susId = table.row($(this).parent().parent()).data()[2].susId;
		var subId = "3";
		var score = $(this).parent().prev().text();
		if(score < 0 || score > 100) {
			layer.msg("请输入有效成绩");
		} else {
			$.ajax({
				type: "POST",
				async: true,
				url: "../school/updateScore.action",
				data: {
					stuId: stuId,
					subId: subId,
					score: score,
					susId: susId
				},
				dataType: "text",
				success: function(data) {
					var msg = JSON.parse(data);
					if(msg.myresult == "success") {
						layer.msg("修改科目三成绩成功，成绩不合格，需重新预约考试");
					} else if(msg.myresult == "next") {
						layer.msg("修改科目三成绩成功，成绩合格，进入下一阶段");
					} else {
						layer.msg("修改科目三成绩失败");

					}
					table.ajax.reload(null, false);
				},
				error: function() {
					// 请求出错处理
					layer.msg("操作失败！");
					table.ajax.reload(null, false);
				}
			});
		}
	})

})