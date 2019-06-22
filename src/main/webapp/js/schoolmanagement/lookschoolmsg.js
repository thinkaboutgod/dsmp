layui.use('layer', function(){
	var layer = layui.layer;
});  
$(function() {
	$.extend($.fn.dataTable.defaults, dataTableSeetings); // 公共初始化设置	
	datatable_otherSet = {

			"ajax" : {
						"url": "plateform/lookschoolmsg.action",
						"type":"POST"
					},
			"columns" : [
					{
						"data" : "schName",
						"orderable" : false
					},
					{
						"data" : "schAccount"
						
					},


					{
						"data": "schAddress",


					},
					{
						"data": "schBossname",
						"orderable": false
					},
					{
						"data": "schAudit",
						"orderable": false,					
					},
					{
						"data":null,
						"orderable": false,
						"render": function(data) {
							return '<button  class="btn btn-danger btn-sm details" >查看详情</button>';
						}
					}
					],

	};	
	var table = $("#allschool").DataTable(datatable_otherSet); //初始化
	//弹框内容
	$(document).on("click",".details",function(){
		var data = table.row($(this).parent().parent()).data();
		var schid=data.schId;
		$("#schName").val(data.schName);
		$("#schType").val(data.schType);
		$("#schRegistercapital").val(data.schRegistercapital);
		$("#schCreditcode").val(data.schCreditcode);
		$("#schoolDetail").modal("show");	
		var touxiang=$("#image");
		$.ajax({
			url:"plateform/shoolHeadportrait.action",
			async:true,
			type:"POST",
			data:"schId="+schid,
			dataType:"json",
			success:function(result){
				touxiang.attr("src",".."+result);			
			},	
			error:function(){
			layer.msg("网络出错！请检查网络！");
		
			},
		});		
	})
})