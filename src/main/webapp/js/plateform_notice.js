var path = $("#path").val();
var rowData=-1;////用于判断有无选中数据
var type;//用于区分修改还是新增
$(document).ready(function() {
	
	$.extend($.fn.dataTable.defaults, dataTableSeetings);// 公共初始化设置
	
	datatable_otherSet = {
			"ajax" : path+"/plateform/searchAllNotice.action",
			"searching":true,
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
						"data" : "notTitle",
						"orderable" : false,
							
					},
					{
						"data" : "notPath",
						"orderable" : false, 
					},
					{
						"data" : "tbNoticeType.ntyName",
						"orderable" : false, 
					}
//					{
//						"data" : "capId",
//						"orderable" : false, 
//						"render" : function(data, type, full, meta) {
//							return data = '<button  class="btn btn-danger btn-sm change" >修改</button>';;
//						}
//					} 
					],

//			"fnServerParams" : function(aoData) {//设置参数
//				aoData._rand = Math.random();
//				aoData.push({
//					"name" : "parName",
//					"value" : $("#parName").val(),
//				},{
//					"name" : "parType",
//					"value" : $("#parType").val(),
//				}
//				);
//			},

		};
	
var table = $("#notTable").DataTable(datatable_otherSet);//初始化
//选择行
$('tbody').on('click', 'tr', function() {
	if ($(this).hasClass('selected')) {
		$(this).removeClass('selected');
		$(this).css('background-color','');
		rowData = -1;
	} else {
		table.$('tr.selected').css('background-color','');
		table.$('tr.selected').removeClass('selected');
		
		$(this).addClass('selected');
		$(this).css('background-color','#00ACD6');
		rowData = table.row($(this)).data();//拿到本行数据
	}
});



//删除数据
$("#delete").click(function() {
	if (rowData == -1) {
		layer.msg("请选中需要修改的数据");
		return;
	}
	layer.confirm('是否确认删除该数据？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function(index) {
		$.ajax({
			url : path + "/plateform/deleteNotice.action",
			ansyc : true,
			type : "POST",
			data : {
				notId : rowData.notId,
			},
			dataType : "text",
			success : function(data) {
				var result = JSON.parse(data);
				if (result.myresult == "success") {
					layer.msg("删除成功");
					table.row('.selected').remove().draw(false);// 删除某一行数据
				}else if (result.myresult == "fialed"){
					layer.msg("删除失败");
				}
			},
			error : function() {
				layer.msg("服务器繁忙");
			}
		})
	}, function() {
	});
})
//增加链接
$("#add").click(function() {
	clear();
	type = "add";
	$("#myModalLabel").text("添加");
	$("#modalA").modal("show");
})

//修改数据
$("#change").click(function() {
	
	$("#myModalLabel").text("新增");
	if (rowData == -1) {
		layer.msg("请选中需要修改的数据");
		return;
	}
	$("#title").val(rowData.notTitle);
	$("#typeId").val(rowData.tbNoticeType.ntyId);
	$("#describe").val(rowData.notContent);
	$("#Path").val(rowData.notPath);
	$("#modalA").modal("show");
	type = "change";
})

function clear(){
	$("#title").val("");
	$("#typeId").val("0");
	$("#describe").val("");
	$("#Path").val("");
}
//点击提交
$("#sub").click(function() {
	var url = "";
	var notTitle = $("#title").val();
	var ntyId = $("#typeId").val();
	var notContent = $("#describe").val();
	var notPath = $("#Path").val();
	if ($.trim(notTitle)==""||$.trim(notPath) == ""||ntyId == "0") {
		layer.msg("请将信息填写完整");
		return;
	}
	if (type=="change") {
		url = path + "/plateform/changeNotice.action"
		data={
			notId:rowData.notId,
			notTitle : notTitle,
			ntyId :ntyId,
			notContent :notContent,
			notPath :notPath,
		}
	}else if (type=="add") {
		url = path + "/plateform/addNotice.action"
		data={
				notTitle : notTitle,
				ntyId :ntyId,
				notContent :notContent,
				notPath :notPath,
			}
	}
	$.ajax({
		url : url,
		ansyc : true,
		type : "POST",
		data : JSON.stringify(data),
		dataType : "text",
		contentType : "application/json;charset=utf-8", //如果采用requestbody那么一定要加
		success : function(data) {
			var result = JSON.parse(data);
			if (result.myresult == "success") {
				switch (type) {
				case "change":
					layer.msg("修改成功");
					break;

				case "add":
					layer.msg("添加成功");
					break;
				}
				table.ajax.reload(null,false);// 重新加载
				$("#modalA").modal("hide");
			}else if (result.myresult == "fialed"){
				layer.msg("删除失败");
			}
		},
		error : function() {
			layer.msg("服务器繁忙");
		}
	})
})
})