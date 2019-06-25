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
							data = ' <button  class="yuyuecichang btn btn-primary btn-sm" type="button">预约此场</button>';
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

				var index = parent.layer.getFrameIndex(window.name); 

				if(result=="fail"){

					alert("每科目预约人数最多只能五个");
					parent.layer.close(index); //再执行关闭
				}else if(result=="success"){
					alert("预约成功");
					parent.layer.close(index); //再执行关闭
					//刷新数据					
					window.parent.refreshtable2();
				}
			}
		});
	});
});