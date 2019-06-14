var path = $("#path").val();
$(function() {
$.extend($.fn.dataTable.defaults, dataTableSeetings);// 公共初始化设置
	
	datatable_otherSet = {
			"ajax" : path+"/plateform/searchMoneyRecord.action",
			"searching" : true,
			"columns" : [
					{
			            sTitle: '序号',
			            data: null,
			            className: 'text-center whiteSpace',
			            render:function(data,type,row,meta) {
			                return meta.row + 1 +
			                meta.settings._iDisplayStart;
			            }
			        },{
						"data" : "capOrderNumber",
							
					},{
						"data" : "stuName",
						"orderable" : false, 
					},{
						"data" : "schName",
						"orderable" : false, 
					},{
						"data" : "capTime",
						"orderable" : false, 
					},{
						"data" : "capMoney",
					},{
						"data" : "capTime",
						"orderable" : false, 
					},{
						"data" : "capFeeType",
						"orderable" : false, 
					}, 
					],

			"fnServerParams" : function(aoData) {//设置参数
				aoData._rand = Math.random();
				aoData.push({
					"name" : "capOrderNumber",
					"value" : $("#capOrderNumber").val(),
				},{
					"name" : "stuName",
					"value" : $("#stuName").val(),
				},{
					"name" : "schname",
					"value" : $("#schname").val(),
				},{
					"name" : "begintime",
					"value" : $("#begintime").val(),
				},{
					"name" : "endtime",
					"value" : $("#endtime").val(),
				}
				);
			},

		};
	
var table = $("#moneyTable").DataTable(datatable_otherSet);//初始化



})