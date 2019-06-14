//
//$(document).ready( function () {
//	
//	$('#example1').DataTable({
//	    "processing": true,
////	    "searching": true,
////	    "serverSide": true,
////		"autoWidth" : false,
//	    "ajax": {
//	        "url": "belongtocoach.action",
//	    },
//	    "columns": [
//	        {"data": "stuAccount"},
//	        {"data": "stuName"},
//	        {"data": "stuSignuptime"},
//	        {"data": "stuAccount"},
//	        {"data": "stuAddress"},
//	        {"data": "stuSex"},
//	        {"data": "tbSubject.subName"},
//	        {"data": "subId"}
//	    ]
//	});
//});

layui.use('element', function(){
  var $ = layui.jquery
  ,element = layui.element;});

 layui.use(['layer', 'form'], function(){
	  var layer = layui.layer
	  ,form = layui.form;
});
$(function() {
	var a;
	$.extend($.fn.dataTable.defaults, dataTableSeetings);// 公共初始化设置	
	$(".subject").click(function() {
		a=$(this).text();
		
	})
	datatable_otherSet = {
			"ajax" : {
						"url": "coach/belongtocoach.action",
						"type":"post"
					},
			"columns" : [
					{
						"data" : "stuAccount",
						"orderable" : false
					},
					{
						"data" : "stuName"
						
					},

					{
						"data" : "stuSignuptime",
						"render" : function(data, type, full, meta) {
							return data = new Date(data).format("yyyy-MM-dd hh:mm:ss");}

					},
					{
						"data": "stuAccount",
						"orderable" : false
					},
					{
						"data": "stuAddress",
						"orderable" : false
					},
					{
						"data" : "stuSex",
						"orderable" : false, // 禁用排序
					},
					{"data": "tbSubject.subName",
						"orderable" : false
					},

					{
						"data": null,
						"orderable" : false,
						"render" : function(data) {
								data = ' <button  class="detail btn btn-primary btn-sm" >查看详情</button>';
							return data;

						}
						
					}
					],

			"fnServerParams" : function(aoData) {//设置参数
				aoData._rand = Math.random();
				aoData.push({
					"name" : "name",
					"value" : $("#sname").val()
				}, {
					"name" : "account",
					"value" : $("#saccount").val()
				},{
					"name" : "beginTime",
					"value" : $("#begintime").val()
				}, {
					"name" : "belongSubject",
					
					"value": a
				},{
					"name" : "endTime",
					"value" : $("#endtime").val()
				});
			},

		}; 
	
	var table = $("#example1").DataTable(datatable_otherSet);//初始化
	
	$("#buttonsearch").on("click", function() {
		table.ajax.reload(null, false);// 刷新数据方法,false代表保持当前页,提交ajax
	});
	$(".subject").on("click",function(){
		table.ajax.reload(null, true);// 刷新数据方法,false代表保持当前页,提交ajax
	});	
	$(document).on("click", ".detail", function() { 
		var da = table.row($(this).parent().parent()).data();
		var vv=da.stuId;		
		layer.open({
			title: '学员详情',
			type: 2,
			area: ['700px', '500px'],
			shadeClose: true, //点击遮罩关闭
			content: ['coach/tostudentparticulars.action?stuId='+vv,'no']
		});
//		$.ajax({
//	    	  type: 'GET',
//	    	  url: 'tostudentparticulars.action',//发送请求
//	    	  data: vv,
//	    	  dataType : "json/text",
//	    	  success: function(result) {
//	    		  var htmlCont = result;//返回的结果页面
//	    		  layer.open({
//			          type: 1,//弹出框类型
//			          title: '学生详情',
//			          shadeClose: false, //点击遮罩关闭层
//			          area : ['700px', '500px'],
//			          shift:1,
//			          content: htmlCont,//将结果页面放入layer弹出层中
//			          success: function(layero, index){
//			        	  
//			          }
//			      });
//	    	  }
//	    	});
	});
})
$(function() {

})
