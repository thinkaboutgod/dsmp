var path = $("#path").val();
$(function() {
	
	$.extend($.fn.dataTable.defaults, dataTableSeetings);// 公共初始化设置
	$( "#begintime,#endtime,#begintime2,#endtime2" ).datepicker({//日期输入框设置
		dateFormat: "yy-mm-dd",
		 changeYear: true,
		 changeMonth: true,
		 showButtonPanel: true,
	      beforeShow: function( input ) {
	        setTimeout(function() {
	          var buttonPane = $( input )
	            .datepicker( "widget" )
	            .find( ".ui-datepicker-buttonpane" );
	          
	          $( "<button>", {
	            text: "清空",
	            click: function() {
	              $.datepicker._clearDate( input );
	            }
	          }).appendTo( buttonPane );
	        }, 1 );
	      }
		 
		});
	
	datatable_otherSet = {
			"ajax" : path+"/plateform/searchAllStudent.action",
			"columns" : [
					{
			            sTitle: '序号',
			            data: null,
			            className: 'text-center whiteSpace',
			            render:function(data,type,row,meta) {
			                return meta.row + 1 +
			                meta.settings._iDisplayStart;
			            }
			        },
					{
						"data" : "stuAccount"
					},
					{
						"data" : "stuName"
					},
					{
						"data" : "stuSex",
						"orderable" : false, // 禁用排序
					},
					{
						"data" : "stuRegistertime",
						"render" : function(data, type, full, meta) {
						return data = new Date(data).format("yyyy-MM-dd hh:mm:ss");
						}
					},
					{
						"data" : "tbSchool.schName",
						"orderable" : false, // 禁用排序
					},
					{
						"data" : "stuVerifystatus",
						"orderable" : false, // 禁用排序
					},
					{
						"data" : "stuStatus",
						"orderable" : false, // 禁用排序
					},
					{
						"data" : "stuStatus",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : '',
						"sWidth" : "",
						"render" : function(data, type, full, meta) {
							state = data;
							if (state == '启用') {
								data = '<button  class="btn btn-danger btn-sm bt_qi" >禁用</button>';
							} else if (state == '禁用' || state == '锁定'){
								data = '<button  class="btn btn-success btn-sm bt_qi" >启用</button>';
							}
							data = data + ' <button  class="detail btn btn-primary btn-sm " >查看信息</button>';
							return data;

						}
					},  ],

			"fnServerParams" : function(aoData) {//设置参数
				aoData._rand = Math.random();
				aoData.push({
					"name" : "name",
					"value" : $("#sname").val()
				}, {
					"name" : "account",
					"value" : $("#saccount").val()
				}, {
					"name" : "school",
					"value" : $("#school").val()
				},{
					"name" : "beginTime",
					"value" : $("#begintime").val()
				}, {
					"name" : "endTime",
					"value" : $("#endtime").val()
				}

				);
			},

		};
	
	datatable_otherSet2 = {
			"ajax" : path+"/plateform/searchAllStudent2.action",
			"autoWidth" : false,
			"scrollY": "",
			
			"columns" : [
					{
			            sTitle: '序号',
			            data: null,
			            className: 'text-center whiteSpace',
			            render:function(data,type,row,meta) {
			                return meta.row + 1 +
			                meta.settings._iDisplayStart;
			            }
			        },
					{
						"data" : "stuAccount",
					},
					{
						"data" : "stuRegistertime",
						"render" : function(data, type, full, meta) {
						return data = new Date(data).format("yyyy-MM-dd hh:mm:ss");
						}
					},
					{
						"data" : "stuStatus",
						"width":"100px",
						"orderable" : false, // 禁用排序
					},
					{
						"data" : "stuStatus",
						"width":"100px",
						"orderable" : false, // 禁用排序
						"render" : function(data, type, full, meta) {
							state = data;
							if (state == '启用') {
								data = '<button id="start" class="btn btn-danger btn-sm bt_noSignUp" >禁用</button>';
							} else if (state == '禁用' || state == '锁定'){
								data = '<button id="forbit" class="btn btn-success btn-sm bt_noSignUp" >启用</button>';
							}
							return data;
						}
					},  ],
					
			"fnServerParams" : function(aoData) {//设置参数
				aoData._rand = Math.random();
				aoData.push({
					"name" : "name",
					"value" : $("#sname2").val()
				}, {
					"name" : "account",
					"value" : $("#saccount2").val()
				}, {
					"name" : "beginTime",
					"value" : $("#begintime2").val()
				}, { 
					"name" : "endTime",
					"value" : $("#endtime2").val()
				}

				);
			},
			

		};
	
	var table2 = $("#studentTable2").DataTable(datatable_otherSet2);//初始化
	var table = $("#studentTable").DataTable(datatable_otherSet);//初始化
	
	
	// 选择行,两个表格公用
	$('tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
			$(this).addClass('selected');
		}
	});
	
	
	//自定义搜索
	$("#buttonsearch").on("click", function() {
		table.ajax.reload(null, false);// 刷新数据方法,false代表保持当前页
	})
	//自定义搜索
	$("#buttonsearch2").on("click", function() {
		table2.ajax.reload(null, false);// 刷新数据方法,false代表保持当前页
	})
	
	//未报名启用禁用
	$(document).on("click", ".bt_noSignUp", function() { 
		var button = $(this);
		var id = table2.row($(this).parent().parent()).data().stuId; 
		start_forbit(button,id);
		
	})
	// 已报名启用禁用方式
	$(document).on("click", ".bt_qi", function() { 
		//此处拿到选择行的数据中的id 
		var id = table.row($(this).parent().parent()).data().stuId;  
		var button = $(this);
		start_forbit(button,id);
		
	})
	//两个表格共用启用禁用方法
	function start_forbit(button,id) {
		
		var preText = button.parent().prev().text();
		var text = button.text();
		var state;
		
//		if ("启用" == text) {
//			state="start"
//		} else if ("禁用" == text) {
//			state="forbid"
//		};
		$.ajax({
			url : "../plateform/changeStudentState.action",
			async : true,
			type : "POST",
			data :  {stuId : id,state:text,preText:preText}  ,
			dataType : "text",
			success : function(data) {
				var result = JSON.parse(data);
				if (result.myresult=="success") {
					layer.msg("修改成功");
					switch (text) {
					case "启用":
						button.parent().prev().text("启用");
						button.text("禁用");
						button.removeClass("btn-success");
						button.addClass("btn-danger");
						break;
					case "禁用":
						button.parent().prev().text("禁用");
						button.text("启用");
						button.removeClass("btn-danger");
						button.addClass("btn-success");
						break;
					
					}
				}else if (result.myresult=="failed") {
					layer.msg("修改失败");
				}
			},
			error : function() {
				
				layer.msg("服务器繁忙");

			}
			
		})
	}
	
	
	// 查看详细信息
	$(document).on("click", ".detail", function() { 
		//此处拿到选择行的数据中的id 
		var da = table.row($(this).parent().parent()).data();
		$("#name").val(da.stuName);
		$("#phone").val(da.stuAccount);
		$("#address").val(da.stuAddress);
		$("#coachName").val(da.tbCoach.coaName);
		$("#subName").val(da.tbSubject.subName);
		$("#studentDetail").modal("show");
		
	})
	
})