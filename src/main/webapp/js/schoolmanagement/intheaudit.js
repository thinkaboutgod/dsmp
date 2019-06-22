layui.use("layer",function(){
	var layer=layui.layer;	
})

$(function() {
	$.extend($.fn.dataTable.defaults, dataTableSeetings); // 公共初始化设置	
	datatable_otherSet = {
			"ajax" : {
						"url": "plateform/intheauditmsg.action",
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
						"data":"schAudit",
						"orderable": false,
					},
					{
						"data":null,
						"orderable": false,
						"render": function(data) {
							return data = '<button  class="btn btn-danger btn-sm audit" >审核</button>';
						}
					},
					],
		
	};	
	var table = $("#school").DataTable(datatable_otherSet); //初始化
	//弹框内容
	$(document).on("click",".audit",function(){
		var data = table.row($(this).parent().parent()).data();
		var schid=data.schId;
		var touxiang=$("#image");
		$("#schName").val(data.schName);
		$("#schType").val(data.schType);
		$("#schRegistercapital").val(data.schRegistercapital);
		$("#schCreditcode").val(data.schCreditcode);
		$("#getschid").val(data.schId);
		$("#schoolDetail").modal("show");
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
	//标记被点击的行
	$('tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			
		} else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});
	//审核
	$(document).on("click",".getaudit",function(){
		var schid=$("#getschid").val();
		var auditresult=$(this).text();
		layer.confirm('你确定修改吗？', {
			btn : [ '确定', '取消' ]
		// 按钮
		},function(index){
			$.ajax({
				url:"plateform/schoolaudit.action",
				async:true,
				type:"POST",
				data:"schId="+schid+"&auditResult="+auditresult,
				dataType:"json",
				success:function(result){
					if(result=="fail"){
						layer.msg("审核不准");
						$("#schoolDetail").modal("hide");
						
					}else{
						layer.msg("审核通过");
						//改变状态
						$("#schoolDetail").modal("hide");
						$(".selected").children().eq(4).text("已审核");
						$(".selected").children().eq(5).children().text("已审核");
						$(".selected").children().eq(5).children().attr('disabled',true);
					}
				},
				error:function(){
					layer.msg("网络出错！请检查网络！");
				
				},
			});
		},function(){
			
		});

		
	})

})