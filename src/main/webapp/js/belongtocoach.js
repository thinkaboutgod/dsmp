
$(document).ready( function () {
	
	$('#example1').DataTable({
	    "processing": true,
//	    "searching": true,
//	    "serverSide": true,
//		"autoWidth" : false,
	    "ajax": {
	        "url": "belongtocoach.action",
	    },
	    "columns": [
	        {"data": "stuAccount"},
	        {"data": "stuName"},
	        {"data": "stuSignuptime"},
	        {"data": "stuAccount"},
	        {"data": "stuAddress"},
	        {"data": "stuSex"},
	        {"data": "tbSubject.subName"},
	        {"data": "subId"}
	    ]
	});
});
//$(function() {
//	
//	$.extend($.fn.dataTable.defaults, dataTableSeetings);// 公共初始化设置	
//	datatable_otherSet = {
//			"ajax" : "../belongtocoach.action",
//			"columns" : [
//					{
//						"data" : "stuAccount"
//					},
//					{
//						"data" : "stuName"
//					},
//
//					{
//						"data" : "stuSignuptime",
//						"render" : function(data, type, full, meta) {
//						return data = new Date(data).format("yyyy-MM-dd hh:mm:ss");
//						}
//					},
//					{
//						"data": "stuAccount"
//					},
//					{
//						"data": "stuAddress"
//					},
//					{
//						"data" : "stuSex",
////						"orderable" : false, // 禁用排序
//					},
//					{"data": "tbSubject.subName"},
//
//					{"data": "subId"}
//					],
//
////			"fnServerParams" : function(aoData) {//设置参数
////				aoData._rand = Math.random();
////				aoData.push({
////					"name" : "name",
////					"value" : $("#sname").val()
////				}, {
////					"name" : "account",
////					"value" : $("#saccount").val()
////				}, {
////					"name" : "school",
////					"value" : $("#school").val()
////				},{
////					"name" : "beginTime",
////					"value" : $("#begintime").val()
////				}, {
////					"name" : "endTime",
////					"value" : $("#endtime").val()
////				});
////			},
//
//		};
//	
//	
//	
//	var tablestudent = $("#example1").DataTable(datatable_otherSet);//初始化
////	var table = $("#studentTable").DataTable(datatable_otherSet);//初始化
//	
//
//
//})
