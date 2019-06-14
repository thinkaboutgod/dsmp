layui.use(['layer', 'form'], function(){
	  var layer = layui.layer
	  ,form = layui.form;
});
$(function() {
	$.extend($.fn.dataTable.defaults, dataTableSeetings);// 公共初始化设置	
	datatable_booking = {
			"autoWidth" : false,
			"ajax" : {
						"url": "selectthestudenttestmsg.action",
						"type":"post"					
					},
			"columns" : [
					{
						"data" : "exsTime",
						"orderable" : false,
						"render" : function(data, type, full, meta) {
							return data = new Date(data).format("yyyy-MM-dd hh:mm:ss");},						
					},
					{
						"data" : "exsAddress"
						
					},

					{
						"data" : "tbSubject.subName",
						

					},
					{
						"data": "exsEndtime",
						"render" : function(data, type, full, meta) {
							return data = new Date(data).format("yyyy-MM-dd hh:mm:ss");},
						"orderable" : false
					},
					{
						"data": "exsSignupnum",
						"orderable" : false
					},
					{
						"data" : "exsTotalnum",
						"orderable" : false, // 禁用排序
					},
					{
						"data":null,
						"orderable" : false,
						"render" : function(data) {
							data = ' <button  class="yuyuecichang btn btn-primary btn-sm" >预约此场</button>';
						return data;}	
					}
					],
		}; 
	var table = $("#testappointment").DataTable(datatable_booking);//初始化
	$(document).on("click", ".yuyuecichang", function() { 
		var da = table.row($(this).parent().parent()).data();
		var exsid=da.exsId;
		var subname=da.tbSubject.subName;
		
		$.ajax({
			type: 'POST',
			async : true,
			url: 'testbooking.action',//发送请求
			data: "exsId="+exsid+"&subName="+subname,
			dataType : "json",
			success: function(result) {
//				var obj=JSON.parse(result);
//				alert(obj);
				var index = parent.layer.getFrameIndex(window.name); 
				alert(result);
				if(result=="fail"){
					alert("每科目预约人数最多只能五个");
					parent.layer.close(index); //再执行关闭
				}else if(result=="success"){
					alert("预约成功");
					parent.$('#haveappointment').DataTable({
						    "ajax" : {
							"url": "coach/haveappointment.action",
							"type":"post"
							},
							"destroy": true,
							"autoWidth" : false,
						      "columns" : [
							{
								"data" : "stuName",						
								
							},
							{
								"data" : "tbSubject.subName",
								
							},

							{
								"data" : "tbExamscheduleandstudent.easSeatnum",
								"orderable" : false
							},
							{
								"data": "tbExamscheduleandstudent.tbExamschedule.exsAddress",
								"orderable" : false
							},
							{
								"data": "tbExamscheduleandstudent.tbExamschedule.exsTime",
								"orderable" : false,
								"render" : function(data, type, full, meta) {
									return data = new Date(data).format("yyyy-MM-dd hh:mm:ss");}
							},
						         
						        ]
						   });

					parent.layer.close(index); //再执行关闭
					//刷新数据
//					$.each(result,function(i,val){
//						
//						
//						var aaa=parent.$("#test").val("改变");					
//						parent.$("#haveappointment").append(
//								"<tr><td>"+val.stuName+"</td>"+
//								"<td>"+val.tbSubject.subName+"</td>"+
//								"<td>"+val.tbExamscheduleandstudent.easSeatnum+"</td>"+
//								"<td>"+val.tbExamscheduleandstudent.tbExamschedule.exsAddress+"</td>"+
//								"<td>"+val.tbExamscheduleandstudent.tbExamschedule.exsAddress+"</td></tr>"
//						)												
//					});
					parent.$('#arrangethetest').DataTable({
						   	"ajax" : {
								"url": "coach/testappointment.action",
								"type":"post"
							},
							"destroy": true,
							"autoWidth" : false,
							"columns" : [
								{
									"data" : "stuName",						
									
								},
								{
									"data" : "tbSubject.subName",
									
								},

								{
									"data" : "tbStudyrecord.strTime",
									"orderable" : false
								},
								{
									"data": "tbSubject.subTime",
									"orderable" : false
								},
								{
									"data": null,
									"orderable" : false,
									"render" : function(data) {
										data = ' <button  class="yuyue btn btn-primary btn-sm" >预约考试</button>';
									return data;}

								
								},	
								],
						
					})
				}
			}
		});
	});
});