var path = $("#path").val();
var type;//参数类型
$(function() {
$.extend($.fn.dataTable.defaults, dataTableSeetings);// 公共初始化设置
	
	datatable_otherSet = {
			"ajax" : path+"/plateform/searchParameter.action",
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
						"data" : "parName",
						"orderable" : false,
							
					},{
						"data" : "parType",
						"orderable" : false, 
					},{
						"data" : "parValue",
						"orderable" : false, 
					},{
						"data" : "capId",
						"orderable" : false, 
						"render" : function(data, type, full, meta) {
							return data = '<button  class="btn btn-danger btn-sm change" >修改</button>';
						}
					} 
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
	
var table = $("#parTable").DataTable(datatable_otherSet);//初始化

//$("#buttonsearch").click(function() {
//	table.ajax.reload(null,false);
//})



//选择行
$('tbody').on('click', 'tr', function() {
	if ($(this).hasClass('selected')) {
		$(this).removeClass('selected');
	} else {
		table.$('tr.selected').removeClass('selected');
		$(this).addClass('selected');
	}
});


$(document).on("click",".change",function(){
	var data = table.row($(this).parent().parent()).data();
	type = data.parType;
	$("#parId").val(data.parId);
	$("#parName").val(data.parName);
	$("#parType").val(data.parType);
	$("#parValue").val(data.parValue);
	$("#changePar").modal("show");
	
})

$("#change").click(function() {
	var parValue = $("#parValue").val();
	if (type=="时间") {
		var re = /^[0-9]*$/;
		if (!re.test(parValue)) {
			layer.msg("请输入大于0小于24小时的正整数");
			return;
		}
		if (parValue>24) {
			layer.msg("请输入大于0小于24小时的正整数");
			return;
		}
		parValue = parValue+":00"
	}else if (type=="时长") {
		var re = /^[0-9]*$/;
		if (!re.test(parValue)) {
			layer.msg("请输入大于0小于10小时的正整数");
			return;
		}
		if (parValue>10) {
			layer.msg("请输入大于0小于10小时的正整数");
			return;
		}
	}
	
	var parId = $("#parId").val();
	data = {parId:parId,
		parValue : parValue
		};
	$.ajax({
		url : path + "/plateform/changeParameter.action",
		ansyc : true,
		type : "POST",
		data : JSON.stringify(data),
		dataType : "text",
		contentType : "application/json;charset=utf-8", //如果采用requestbody那么一定要加
		success : function(data) {
			var result = JSON.parse(data);
			if (result.myresult == "success") {
				layer.msg("修改成功");
//				table.row('.selected').remove().draw(false);// 删除某一行数据
				table.ajax.reload(null,false);
				$("#changePar").modal("hide");
			}else if (result.myresult == "fialed"){
				layer.msg("修改失败");
			}
		},
		error : function() {
			layer.msg("服务器繁忙");
		}
	})
})

})