$(function() {
	
	$.extend($.fn.dataTable.defaults, dataTableSeetings);// 公共初始化设置
	
	datatable_otherSet = {
			"ajax" : "../car/selectCarsByCondition.action",
			"columns" : [
					{
						"data" : "carPlatenum"
					},
					{
						"data" : "carStyle"
					},
					{
						"data" : "carColor"
					},
					{
						"data" : "tbCoach.coaName",
						"orderable" : false, // 禁用排序
					},
					{
						"data" : "carUsedTime",
					},
//					{
//						"data" : "tbCoach.coaName",
//						"orderable" : false, // 禁用排序
//					},
//					{
//						"data" : "tbSubject.subName",
//						"orderable" : false, // 禁用排序
//					},
					{
						"data" : "carStatus",
						"orderable" : false, // 禁用排序
					},
					{
						"data" : "carUsedTime",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : '',
						"sWidth" : "",
						"render" : function(data, type, full, meta) {
							usedtime = data;
							data="";
							var year = parseInt(usedtime.split("年")[0])
							if (year >= 10) {
								data = '<button  class="btn btn-danger btn-sm bt_scrap" >报废</button>';
							} 
							data = data + ' <button  class="detail btn btn-primary btn-sm " >查看信息</button>';
							return data;
						}
					},  ],
					
				
			"fnServerParams" : function(aoData) {//设置参数
				aoData._rand = Math.random();
				aoData.push({
					"name" : "carPlateNum",
					"value" : $("#carPlateNum").val()
				}, {
					"name" : "coachName",
					"value" : $("#coachName").val()
				}, {
					"name" : "schId",
					"value" : $("#schId").val()
				},{
					"name" : "beginTime",
					"value" : $("#begintime").val()
				}, {
					"name" : "endTime",
					"value" : $("#endtime").val()
				}

				);
			},

		};
	
	var table = $("#studentTable").DataTable(datatable_otherSet);//初始化
	
	// 选择行,两个表格公用
	$('tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});
	
	
	//自定义搜索
	$("#buttonsearch").on("click", function() {
		table.ajax.reload(null, false);// 刷新数据方法,false代表保持当前页
	})
	
	
	// 车辆报废
	$(document).on("click", ".bt_ban", function() { 
		//此处拿到选择行的数据中的id 
		var id = table.row($(this).parent().parent()).data().carId;  
		
		var button = $(this);
		var preText = button.parent().prev().text();
		var text = $(this).text();
//		var id = table.rows('.selected').data()[0].cuid;
		var state;
		if ("启用" == text) {
			state="start"
		} else if ("禁用" == text) {
			state="forbid"
		};
		$.ajax({
			url : "../car/scrapCar.action",
			async : true,
			type : "POST",
			data :  {stuId : id,state:state,preText:preText}  ,
			dataType : "text",
			success : function(data) {
				var result = JSON.parse(data);
				if (result.myresult=="success") {
					layer.msg("修改成功");
					switch (text) {
					case "启用":
						button.parent().prev().text("启用");
						button.text("禁用");
						button.removeClass("btn-success");
						button.addClass("btn-danger");
						break;
					case "禁用":
						button.parent().prev().text("禁用");
						button.text("启用");
						button.removeClass("btn-danger");
						button.addClass("btn-success");
						break;
					
					}
				}else if (result.myresult=="failed") {
					layer.msg("修改失败");
				}
			},
			error : function() {
				layer.msg("服务器繁忙");
			}
		})
	})
	
	// 查看详细信息
	$(document).on("click", ".detail", function() { 
		//此处拿到选择行的数据中的id 
		var da = table.row($(this).parent().parent()).data();
		$("#carPlateNumDe").val(da.carPlatenum);
		$("#carStyleDe").val(da.carStyle);
		$("#carColorDe").val(da.carColor);
		$("#coachNameDe").val(da.tbCoach.coaName);
		$("#carStartTimeDe").val(da.carStartTime);
		$("#carUsedTimeDe").val(da.carUsedTime);
		$("#carDetail").modal("show");
	})
	
})