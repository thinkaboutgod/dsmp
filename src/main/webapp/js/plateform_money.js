var path = $("#path").val();
$(function() {
$.extend($.fn.dataTable.defaults, dataTableSeetings);// 公共初始化设置
$( "#begintime,#endtime" ).datepicker({
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
			"ajax" : path+"/plateform/searchMoneyRecord.action",
			"columns" : [
				{
					"data" : null,
					"orderable" : false,
						
				},
			        {
						"data" : "capOrderNumber",
							
					},{
						"data" : "tbStudent.stuName",
						"orderable" : false, 
					},{
						"data" : "tbSchool.schName",
						"orderable" : false, 
					},{
						"data" : "capTime",
						"render" : function(data, type, full, meta) {
							return data = new Date(data).format("yyyy-MM-dd hh:mm:ss");
						}
					},{
						"data" : "capMoney",
					},{
						"data" : "capFeetype",
						"orderable" : false, 
					}, 
					],

			"fnServerParams" : function(aoData) {//设置参数
				aoData._rand = Math.random();
				aoData.push({
					"name" : "capOrderNumber",
					"value" : $("#capOrderNumber").val(),
				},{
					"name" : "stuName",
					"value" : $("#stuName").val(),
				},{
					"name" : "schName",
					"value" : $("#schname").val(),
				},{
					"name" : "beginTime",
					"value" : $("#begintime").val(),
				},{
					"name" : "endTime",
					"value" : $("#endtime").val(),
				}
				);
			},
			
			

		};
	
var table = $("#moneyTable").DataTable(datatable_otherSet);//初始化

table.on('order.dt search.dt',function() {
	table.column(0, {
        search: 'applied',
        order: 'applied'
    }).nodes().each(function(cell, i) {
        cell.innerHTML = i + 1;
    });
}).draw();

$("#buttonsearch").click(function() {
	table.ajax.reload(null,false);
})

})