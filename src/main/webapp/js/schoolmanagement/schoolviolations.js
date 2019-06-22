layui.use('layer', function(){
	var layer = layui.layer;
});  
$(function() {
	$.extend($.fn.dataTable.defaults, dataTableSeetings); // 公共初始化设置	
	datatable_otherSet = {

			"ajax" : {
						"url": "plateform/punishviolationsmsg.action",
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
						"data": null,
						"orderable": false,
						"render" : function(data, type, full, meta) {
							return data = '<button  class="btn btn-danger btn-sm details" >查看详情</button>';
						}						
					},
					{
						"data":"schSignupstatus",
						"orderable": false, // 禁用排序
					},
					{
						"data":"schSignupstatus",
						"orderable": false,
						"render": function(data) {
							state=data;
							if(state == "允许报名" ){
								data='<button  class="btn btn-primary btn-sm signupstatus" >禁止学员报名</button>';
							}else{
								data = ' <button  class="btn  btn-success btn-sm signupstatus">允许学员报名</button>';
							}
							
							return data;

						}

					},
					{
						"data":"schOperativestatus",
						"orderable": false
					},
					{
						"data":"schOperativestatus",
						"orderable": false,
						"render": function(data) {
							state=data;
							if(state =="允许运营"){
								data = ' <button  class="btn btn-primary btn-sm operativestatus" >禁止运营</button>';
							}else{
								data = ' <button  class="btn  btn-success btn-sm operativestatus" >允许运营</button>';
							}
							
							return data;

						}
					},
					],

		"fnServerParams": function(aoData) { //设置参数
			aoData._rand = Math.random();
			aoData.push({
				"name": "schName",
				"value": $("#sname").val()
			}, {
				"name": "schAccount",
				"value": $("#saccount").val()
			}, {
				"name": "schBossname",
				"value": $("#sboss").val()
			});
		},
	};	
	var table = $("#allschool").DataTable(datatable_otherSet); //初始化
	$("#buttonsearch").on("click", function() {
		table.ajax.reload(null, false); // 刷新数据方法,false代表保持当前页,提交ajax
	});
	//弹框内容
	$(document).on("click",".details",function(){
		var data = table.row($(this).parent().parent()).data();
		$("#schName").val(data.schName);
		$("#schType").val(data.schType);
		$("#schRegistercapital").val(data.schRegistercapital);
		$("#schCreditcode").val(data.schCreditcode);
		$("#schoolDetail").modal("show");
		
	})
	//启用禁用驾校的学生报名
	$(document).on("click",".signupstatus",function(){
		var data = table.row($(this).parent().parent()).data();
		var schid=data.schId;
		var schSignupstatus=data.schSignupstatus;
		var nowfield=$(this);
		var nowfieldmsg=$(this).text();
		var nowstate=$(this).parent().parent().children().eq(5).text();//
		layer.confirm('你确定修改吗？', {
			btn : [ '确定', '取消' ]
		// 按钮
		},function(index){
			$.ajax({
				url : "plateform/changeschSignupstatus.action",
				ansyc : true,
				type : "POST",
				data : "schId="+schid+"&schSignupstatus="+schSignupstatus,
				dataType : "json",
				success : function(result) {
//					result = JSON.parse(result);
					if(result=="success"){
						if(nowfieldmsg=="允许学员报名"){
							nowfield.text("禁止学员报名");
							nowfield.css("background-color","#3c8dbc");
						}else if(nowfieldmsg=="禁止学员报名"){
							nowfield.text("允许学员报名");
							nowfield.css("background-color","#00a65a");
						}
						if(nowstate=="允许报名"){
							nowfield.parent().parent().children().eq(5).text("禁止报名")
						}else if(nowstate=="禁止报名"){
							nowfield.parent().parent().children().eq(5).text("允许报名")
						}
						layer.msg('修改成功');
					}
					
				},
				error : function() {
					layer.msg("服务器繁忙");
				}
				
			});
			
		},function(){
			
		});
	})
	//启用禁用驾校的运营
	$(document).on("click",".operativestatus",function(){
		var data = table.row($(this).parent().parent()).data();
		var schid=data.schId;
		var schOperativestatus=data.schOperativestatus;
		var nowfield=$(this);
		var nowfieldmsg=$(this).text();
		var nowstate=$(this).parent().parent().children().eq(7).text();//
		layer.confirm('你确定修改吗？', {
			btn : [ '确定', '取消' ]
		// 按钮
		},function(index){
			$.ajax({
				url :  "plateform/changeschOperativestatus.action",
				ansyc : true,
				type : "POST",
				data : "schId="+schid+"&schOperativestatus="+schOperativestatus,
				dataType : "json",
				success : function(result) {
					if(result=="success"){
						if(nowfieldmsg=="允许运营"){
							nowfield.text("禁止运营");
							nowfield.css("background-color","#3c8dbc");
						}else if(nowfieldmsg=="禁止运营"){
							nowfield.text("允许运营");
							nowfield.css("background-color","#00a65a");
						}
						if(nowstate=="允许运营"){
							nowfield.parent().parent().children().eq(7).text("禁止运营")
						}else if(nowstate=="禁止运营"){
							nowfield.parent().parent().children().eq(7).text("允许运营")
						}
						layer.msg('修改成功');
					}
					
				},
				error : function() {
					layer.msg("服务器繁忙");
				}
				
			});
			
		},function(){
			
		});
	})

})