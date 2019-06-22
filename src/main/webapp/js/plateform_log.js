var path = $("#path").val();
$(document).ready(function() {
$.extend($.fn.dataTable.defaults, dataTableSeetings);// 公共初始化设置
$( "#beginTime,#endTime" ).datepicker({//日期输入框设置
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
			"ajax" : path+"/plateform/searchLog.action",
//			"autoWidth" : false,
			//导出excel表
			serverSide: false,//分页，取数据等等的都放到服务端去. true为后台分页，每次点击分页时会请求后台数据，false为前台分页

//       		"dom": '<Bf<t>lip>',
       		"dom": '<"top"B>rt<"bottom"lip><"clear">',
        	buttons: [ {
            	extend: 'excelHtml5',
            	text:'导出excel表格',
            	   customize: function( xlsx ) {
//                	var sheet = xlsx.xl.worksheets['sheet1.xml'];
//                	$('row c[r^="C"]', sheet).attr( 's', '2' );
            	}
        	} ],
			
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
						"data" : "logEvent",
						"orderable" : false, // 禁用排序
					},
					{
						"data" : "logTime",
						"render" : function(data, type, full, meta) {
						return data = new Date(data).format("yyyy-MM-dd hh:mm:ss");
						}
					},
					{
						"data" : "logOperatoraccount",
						"orderable" : false, // 禁用排序
					},
					{
						"data" : "logRole",
						"orderable" : false, // 禁用排序
					},
					],

			"fnServerParams" : function(aoData) {//设置参数
				aoData._rand = Math.random();
				aoData.push({
					"name" : "logOperatoraccount",
					"value" : $("#logOperatoraccount").val()
				}, {
					"name" : "logRole",
					"value" : $("#logRole").val()
				}, {
					"name" : "beginTime",
					"value" : $("#beginTime").val()
				}, { 
					"name" : "endTime",
					"value" : $("#endTime").val()
				}

				);
			},
			
		

		};
	
	var table = $("#logTable").DataTable(datatable_otherSet);
	$(".buttons-excel").addClass("btn btn-info");
	$(".bottom div").attr("white-space","nowrap");
	$("#buttonsearch").click(function() {
		table.ajax.reload(null,false);//刷新数据，false保持当前页
	})
})