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

	datatable_otherSet = {
		"ajax": {
			"url": "../school/selectExamschedule.action",
			"type": "POST"
		},
		"columns": [{
				"data": "exsTime",
				"orderable": true
			},
			{
				"data": "exsAddress"
			},

			{
				"data": "tbSubject.subName",
			},
			{
				"data": "exsEndtime",
				"orderable": false
			},
			{
				"data": "exsSignupnum",
				"orderable": false
			},
			{
				"data": "exsTotalnum",
				"orderable": false, // 禁用排序
			},
		],
		
		"fnServerParams": function(aoData) { //设置参数
			aoData._rand = Math.random();
			aoData.push({
				"name": "schId",
				"value": $("#schId").val()
			});
		},
	};
	
	var table = $("#examSchedule").DataTable(datatable_otherSet); //初始化
	
	$("#btn_add").click(function() {
		
		var schId = $("#schId").val();
		var subId = $("#sub_id").val();
		var exsAddress = $("#exs_address").val();
		var exsTotalNum = $("#totalNum").val();
		var exsDate = $("#exs_date").val();
		var exsTime = $("#exs_time").val();
		
		var fd = new FormData(document.forms[0]);
		// 上一步中的函数
		fd.append("schId", schId);
		fd.append("subId", subId);
		fd.append("exsAddress", exsAddress);
		fd.append("exsTotalNum", exsTotalNum);
		fd.append("exsDate", exsDate);
		fd.append("exsTime", exsTime);
		if(subId == "" || exsAddress == "" || exsTotalNum == "" || exsDate == "" || exsTime == "") {

		} else {
			$.ajax({
				type: "POST",
				url: "../school/addExamschedule.action?",
				processData: false,
				// 必须
				contentType: false,
				// 必须
				data: fd,
				datatype: "text",
				success: function(msg) {
					if(msg.myresult == "success") {
						layer.msg("新增考场成功");
						table.ajax.reload(null, false);
					} else {
						layer.msg("新增考场失败");
					}
				},
				error: function() {
					// 请求出错处理
					layer.msg("操作失败！");
				}
			});
		}
	})
	
	
})

