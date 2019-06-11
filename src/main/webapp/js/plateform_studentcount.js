
$(function() {
	$.extend($.fn.dataTable.defaults, dataTableSeetings);// datatable公共初始化设置
	$.fn.dataTable.ext.errMode = function(s,h,m){//表格数据加载错误提示
		layer.msg("加载数据出现异常");
	};
	var path = $("#path").val();
	//柱状图按驾校搜索
	$("#search").click(function() {
		var schId = $("#schoolSelect").val();
		var dateId = $("#dateSelect").val(); 
		if (schId == "0") {
			layer.msg("请选择驾校");
			return;
		}
		if (dateId == "0") {
			layer.msg("请选查询时间段");
			return;
		}
		$("#schoolName").text("当前数据所属驾校("+$("#schoolSelect").find("option:selected").text()+")");
		$.ajax({
			url : path + "/plateform/countStudentBySchool.action",
			async : true,
			type : "POST",
			data : {
				schId : schId,
				dateId : dateId
			},
			dataType : "text",
			success : function(msg) {
				var data = JSON.parse(msg);
				forBar(data);
			},
			error : function() {
				layer.msg("服务器繁忙");
			}
		})
	})
	//表格设置
	datatable_otherSet = {
		"ajax" : path + "/plateform/countStudentByDate.action",
		"autoWidth" : false,
//		"dom": '<"top"l>rt<"bottom"ip><"clear">',
		"columns" : [ {
			"data" : "name"
		}, {
			"data" : "phone"
		}, {
			"data" : "data"
		}, {
			"data" : "data",
			"render" : function(data, type, full, meta) {
				return data = data*100;
			}
		}, ],

		"fnServerParams" : function(aoData) {// 设置参数
			aoData._rand = Math.random();
			aoData.push({
				"name" : "month",
				"value" : $("#monthSelect").val()
			}
			);
		},

	};
	
	var table = $("#countTable").DataTable(datatable_otherSet);//初始化datatable
	//表格按月份搜索
	$("#searchByDate").on("click", function() {
		if ($("#monthSelect").val()=="0") {
			
			layer.msg("请选择月份");
			return;
		}
		$("#datelName").text("当前显示的月份为("+$("#monthSelect").find("option:selected").text()+")");
		table.ajax.reload(null, false);// 刷新数据方法,false代表保持当前页
	})
})
//统计图表
function forBar(msg) {
	$("#bar-chart").empty();//清空用于重绘
	// BAR CHART
	var bar = new Morris.Area({
		element : 'bar-chart',
		resize : true,
		data : msg,
		barColors : [ '#00a65a' ],
		xkey : "name",
		ykeys : [ "data" ],
		labels : [ '报名人数' ],
		hideHover : 'auto'
	});
}