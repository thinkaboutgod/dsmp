$(function() {
	
	$.extend($.fn.dataTable.defaults, dataTableSeetings);// 公共初始化设置
	
	datatable_otherSet = {
			"ajax" : "../coach/selectCoasByCondition.action",
			"autoWidth" : false,
			"columns" : [
					{
						"data" : "coaAccount"
					},
					{
						"data" : "coaName"
					},
					{
						"data" : "coaSex"
					},
					{
						"data" : "coaAddress"
					},
					{
						"data" : "coaLevel"
					},
//					{
//						"data" : "coaRegistertime",
//						"render" : function(data, type, full, meta) {
//						return data = new Date(data).format("yyyy-MM-dd hh:mm:ss");
//						}
//					},
					{
						"data" : "coaStatus",
						"orderable" : false, // 禁用排序
					},
					{
						"data" : "coaStatus",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : '',
						"sWidth" : "",
						"render" : function(data, type, full, meta) {
							state = data;
							if (state == '启用') {
								data = '<button id="start" class="btn btn-danger btn-sm bt_qi" >禁用</button>';
							} else if (state == '禁用' || state == '锁定'){
								data = '<button id="forbit" class="btn btn-success btn-sm bt_qi" >启用</button>';
							}
							data += '<button id="forbit" class="btn btn-success btn-sm detail" >查看详情</button>'
							return data;
						}
					},  ],

			"fnServerParams" : function(aoData) {//设置参数
				aoData._rand = Math.random();
				aoData.push({
					"name" : "name",
					"value" : $("#cname").val()
				}, {
					"name" : "account",
					"value" : $("#caccount").val()
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
	
	
	var table = $("#coachTable").DataTable(datatable_otherSet);//初始化
	
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
	
	
	// 启用禁用方式
	$(document).on("click", ".bt_qi", function() { 
		//此处拿到选择行的数据中的id 
		var id = table.row($(this).parent().parent()).data().stuId;  
		
		var button = $(this);
		var preText = button.parent().prev().text();
		var text = $(this).text();
		var state;
		
		if ("启用" == text) {
			state="start"
		} else if ("禁用" == text) {
			state="forbid"
		};
		$.ajax({
			url : "../coach/changeCoachState.action",
			async : true,
			type : "POST",
			data :  {coaId : id,state:state,preText:preText}  ,
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
		$("#accountDe").val(da.coaAccount);
		$("#nameDe").val(da.coaName);
		$("#address").val(da.coaAddress);
		$("#schoolName").val(da.tbSchool.schName);
		$("#coachDetail").modal("show");
	})
	
})