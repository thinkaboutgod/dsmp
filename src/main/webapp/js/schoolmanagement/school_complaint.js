layui.use('element', function() {
	var $ = layui.jquery,
		element = layui.element;
});
layui.use("layer",function(){
	var layer=layui.layer;
});
$(function() {
	$("#activistreply").click(function() {
		$.extend($.fn.dataTable.defaults, dataTableSeetings); // 公共初始化设置	
		datatable_otherSet = {
				destroy: true,
				"ajax" : {
							"url": "school/activistreply.action",
							"type":"post"
						},
				"columns" : [
				{
					"data": "appTime",
					"render": function(data, type, full, meta) {
						return data = new Date(data).format("yyyy-MM-dd hh:mm:ss");
					}

				},
				{
					"data": "appContent",
					"orderable": false
				},
				{
					"data": "appReply",
					"render": function(data) {
						if(data==null || data== ""){
							data="暂无回复";
						}else{
							data=data;
						}
						return data;
					},
					"orderable": false
				}
			],

		};
		var table = $("#complaintmsg").DataTable(datatable_otherSet); //初始化
	});
	$("#mainConfirm").click(function() {
		$.ajax({
			url:"school/thecomplaintcontent.action",
			async:true,
			type:"POST",
			data:$('#mainForm').serialize(),
			dataType:"json",
			success:function(result){
				if(result=="success"){
					layer.msg("申诉成功，请等待回复！");
//					$(".selected").children().eq(5).children().attr('disabled',true);
					$("#mainConfirm").attr('disabled',true);
					$("#mainConfirm").text("已提交");
				}else if(result=="fail"){
					layer.msg("书写格式错误");
				}
			},	
			error:function(){
			layer.msg("网络出错！请检查网络！");
		
			},
		});			
	});
	var state=$("#state").text();
	if(state=="正常运营"){
		$("#mainConfirm").attr("disabled",'disabled');
		$("#mainConfirm").text("无可申诉");
	}
})