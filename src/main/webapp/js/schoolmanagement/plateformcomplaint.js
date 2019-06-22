layui.use("layer",function(){
	var layer=layui.layer;	
})
$(function() {
	$.extend($.fn.dataTable.defaults, dataTableSeetings); // 公共初始化设置	
	datatable_otherSet = {
			"ajax" : {
						"url": "plateform/allthecomplaint.action",
						"type":"post"
					},
			"columns" : [
					{
						"data" : "tbSchool.schName",
						"orderable" : false
					},
					{
						"data" : "tbSchool.schAccount"
						
					},
					{
						"data":"tbSchool.schBossname"
					},


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
				"orderable": false,
				"render": function(data) {
					if(data==null || data== ""){
						data="未回复";
					}else{
						data=data;
					}
					return data;
				},
			},
			{
				"data": "appReply",
				"orderable": false,
				"render": function(data) {
					if(data==null || data== ""){
						data= ' <button  class="reply btn btn-primary btn-sm" >回复</button>';
					}else{
						data='<button  class="reply btn btn-primary btn-sm" disabled>已回复</button>';
					}
					
					return data;

				}
			}
		],

		"fnServerParams": function(aoData) { //设置参数
			aoData._rand = Math.random();
			aoData.push({
				"name": "name",
				"value": $("#sname").val()
			}, {
				"name": "account",
				"value": $("#saccount").val()
			}, {
				"name": "beginTime",
				"value": $("#begintime").val()
			}, {
				"name": "endTime",
				"value": $("#endtime").val()
			});
		},

	};
	var table = $("#schoolmsg").DataTable(datatable_otherSet); //初始化
	$("#buttonsearch").on("click", function() {
		table.ajax.reload(null, false); // 刷新数据方法,false代表保持当前页,提交ajax
	});
	$(document).on("click",".reply",function(){
		var data = table.row($(this).parent().parent()).data();
		$("#getappid").val(data.appId);
		$("#theappealtoreply").modal("show");
	
	});
	$(document).on("click","#replybutton",function(){
		var appid=$("#getappid").val();
		var reply=$("#replymsg").val();
		if(reply.length==0||reply==""){
			layer.msg("内容不能为空");
		}else{
			layer.confirm('你确定提交吗？', {
				btn : [ '确定', '取消' ]
			// 按钮
			},function(index){
				$.ajax({
					url:"plateform/respondtoacomplaint.action",
					async:true,
					type:"POST",
					data:"appId="+appid+"&appReply="+reply,
					dataType:"json",
					success:function(result){
						if(result=="fail"){
							layer.msg("提交格式不正确！");
							
							
						}else{
							layer.msg("回复成功！");
							table.ajax.reload(null, false); 
							$("#theappealtoreply").modal("hide");
						}
					},
					error:function(){
						layer.msg("网络出错！请检查网络！");
					
					},
				});
			},function(){
				
			});
		}
		
	})	
})