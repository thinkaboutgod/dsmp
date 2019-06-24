
$(function() {
	$.extend($.fn.dataTable.defaults, dataTableSeetings);// datatable公共初始化设置
	$.fn.dataTable.ext.errMode = function(s,h,m){//表格数据加载错误提示
		layer.msg("加载数据出现异常");
	};
	var path = $("#path").val();
	//折线图按教练搜索
	$("#search").click(function() {
		var coaId = $("#coachSelect").val();
		var dateId = $("#dateSelect").val(); 
		if (coaId == "0") {
			layer.msg("请选择教练");
			return;
		}
		if (dateId == "0") {
			layer.msg("请选查询时间段");
			return;
		}
		$("#coachName").text("当前数据所属教练("+$("#coachSelect").find("option:selected").text()+")");
		$.ajax({
			url : path + "/school/countStudentByCoach.action",
			async : true,
			type : "POST",
			data : {
				coaId : coaId,
				dateId : dateId
			},
			dataType : "text",
			success : function(msg) {
				var data = JSON.parse(msg);
				forArea(data);
			},
			error : function() {
				layer.msg("服务器繁忙");
			}
		})
	})
	
	//柱状图按月份搜索
	$("#searchByMonth").click(function() {
		var schId = $("#schId").val();
		var month = $("#monthSelectForCoach").val(); 
		if (month == "0") {
			layer.msg("请选择月份");
			return;
		}
//		$("#schoolName").text("当前数据所属驾校("+$("#schoolSelect").find("option:selected").text()+")");
		$.ajax({
			url : path + "/school/countAllStudentByDate.action",
			async : true,
			type : "POST",
			data : {
				schId : schId,
				month : month
			},
			dataType : "text",
			success : function(msg) {
				
				var data = JSON.parse(msg);
				var text = "当前显示的月份为("+$("#monthSelectForCoach").find("option:selected").text()+")";
				forBar(msg,text);//柱状图
				forPercent(msg,text);//圆饼百分比图
			},
			error : function() {
				layer.msg("服务器繁忙");
			}
		})
	})
	
	//表格设置
	datatable_otherSet = {
		"ajax" : path + "/school/countStudentByDate.action",
		"autoWidth" : false,
		"scrollY": "",
//		"dom": '<"top"l>rt<"bottom"ip><"clear">',
		"columns" : [ 
			{
	            sTitle: '序号',
	            data: null,
	            className: 'text-center whiteSpace',
	            render:function(data,type,row,meta) {
	                return meta.row + 1 +
	                meta.settings._iDisplayStart;
	            }
	        },
		{
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
			},
			{
				"name" : "schId",
				"value" : $("#schId").val()
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
//统计图表，折线图
function forArea(msg) {
	$("#area-chart").empty();//清空用于重绘
	// BAR CHART
	var bar = new Morris.Area({
		element : 'area-chart',
		resize : true,
		data : msg,
		barColors : [ '#00a65a' ],
		xkey : "name",
		ykeys : [ "data" ],
		labels : [ '报名人数' ],
		hideHover : 'auto'
	});
}
//统计图表，柱状图
function forBar(msg,text) {
	var list = JSON.parse(msg);
	var arrName = new Array();
	var arrValue = new Array();
	for (var i = 0; i < list.length; i++) {
		arrName[i] = list[i].name;
		arrValue[i] = list[i].data;
	}
	var myChart = echarts.init(document.getElementById('bar-chart'));
	option = {
		    color: ['#3398DB'],
		    title : {
		        text: text,
		        subtext: '',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : arrName,
		            axisTick: {
		                alignWithLabel: true
		            }
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'报名人数',
		            type:'bar',
		            barWidth: '30',
		            data:arrValue
		        }
		    ]
		};
	myChart.setOption(option);//渲染数据
}

function forPercent(data,text) {
	var newData = data.replace(/data/g,"value");
	var list = JSON.parse(newData);
	var arrName = new Array();
	for (var i = 0; i < list.length; i++) {
		arrName[i] = list[i].name;
	}
	 var myChart = echarts.init(document.getElementById('percent-chart'));
	option = {
		    title : {
		        text: text,
		        subtext: '',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: arrName
		    },
		    series : [
		        {
		            name: '报名人数',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:list,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
    myChart.setOption(option);//渲染数据
}
