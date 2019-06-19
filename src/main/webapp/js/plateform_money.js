var path = $("#path").val();
$(function() {
$.extend($.fn.dataTable.defaults, dataTableSeetings);// 公共初始化设置
	
	datatable_otherSet = {
			"ajax" : path+"/plateform/searchMoneyRecord.action",
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
						"data" : "tbStudent.stuName",
						"orderable" : false, 
					},{
						"data" : "tbSchool.schName",
						"orderable" : false, 
					},{
						"data" : "capTime",
						"render" : function(data, type, full, meta) {
							return data = new Date(data).format("yyyy-MM-dd hh:mm:ss");
						}
					},{
						"data" : "capMoney",
					},{
						"data" : "capFeetype",
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
					"name" : "schName",
					"value" : $("#schname").val(),
				},{
					"name" : "beginTime",
					"value" : $("#begintime").val(),
				},{
					"name" : "endTime",
					"value" : $("#endtime").val(),
				}
				);
			},
			
			

		};
	
var table = $("#moneyTable").DataTable(datatable_otherSet);//初始化

$("#buttonsearch").click(function() {
	table.ajax.reload(null,false);
})

})