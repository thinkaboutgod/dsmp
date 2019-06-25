layui.use('element', function(){
  var $ = layui.jquery
  ,element = layui.element;});
layui.use(['layer', 'form'], function(){
	  var layer = layui.layer
	  ,form = layui.form;
});
var table2;
var table3;
$(function() {

	$.extend($.fn.dataTable.defaults, dataTableSeetings);// 公共初始化设置	

	datatable_otherSet = {
			"autoWidth" : false,
			"scrollY": "",
			"ajax" : {
						"url": "coach/selectthetestmsg.action",
						"type":"post"
						
					},
			"columns" : [
					{
						"data" : "exsTime",
						"orderable" : false
					},
					{
						"data" : "exsAddress"
						
					},

					{
						"data" : "tbSubject.subName",
						

					},
					{
						"data": "exsEndtime",
						"render" : function(data, type, full, meta) {
							return data = new Date(data).format("yyyy-MM-dd hh:mm:ss");},
						"orderable" : false
					},
					{
						"data": "exsSignupnum",
						"orderable" : false
					},
					{
						"data" : "exsTotalnum",
						"orderable" : false, // 禁用排序
					},
	
					],
		}; 
	var a;
	$(".subject").click(function() {
		a=$(this).text();		
	})

	datatable_otherSet2 = {
			"autoWidth" : false,
			"scrollY": "",
			"ajax" : {
						"url": "coach/testappointment.action",
						"type":"post"
						
					},
			"columns" : [
					{
						"data" : "stuName",						
						
					},
					{
						"data" : "stuAccount",						
						
					},
					{
						"data" : "tbSubject.subName",
						"orderable" : false
						
					},

					{
						"data" : "stuAddress",
						"orderable" : false
					},

					{
						"data": null,
						"orderable" : false,
						"render" : function(data) {
							data = ' <button  class="yuyue btn btn-primary btn-sm" >预约考试</button>';
						return data;}

					
					},	
					],
					"fnServerParams" : function(aoData) {//设置参数
						aoData._rand = Math.random();
						aoData.push({
							"name" : "thesubject",
							"value" : a
						});
					},
		}; 
	datatable_otherSet3 = {
			"autoWidth" : false,
			"scrollY": "",
			"ajax" : {
						"url": "coach/haveappointment.action",
						"type":"post"
						
					},
			"columns" : [
					{
						"data" : "stuName",						
						
					},
					{
						"data" : "tbSubject.subName",
						
					},

					{
						"data" : "tbExamscheduleandstudent.easSeatnum",
						"orderable" : false
					},
					{
						"data": "tbExamscheduleandstudent.tbExamschedule.exsAddress",
						"orderable" : false
					},
					{
						"data": "tbExamscheduleandstudent.tbExamschedule.exsTime",
						"orderable" : false
					},			
					],
		};	
	var table = $("#thetestmsg").DataTable(datatable_otherSet);//初始化
	table2= $("#arrangethetest").DataTable(datatable_otherSet2);
	table3= $("#haveappointment").DataTable(datatable_otherSet3);



	$(".subject").on("click",function(){
		table2.ajax.reload(null, true);// 刷新数据方法,false代表保持当前页,提交ajax
	});	
	$(document).on("click", ".yuyue", function() { 
		var da = table2.row($(this).parent().parent()).data();
		
		var stuid=da.stuId;
		var subName=da.tbSubject.subName;
		var strTime=parseInt(da.tbStudyrecord.strTime);
		
		var subTime=parseInt(da.tbSubject.subTime);
				
		layer.open({
			title: '预约',
			type: 2,
			area: ['700px', '500px'],
			shadeClose: true, //点击遮罩关闭
			content: ['coach/gotobookingbounced.action?stuId='+stuid+"&subName="+subName,'no']
		});

	});	
	$("#subone").click();	
})
function refreshtable2() {
	table2.ajax.reload(null, true);
	table3.ajax.reload(null, true);
}