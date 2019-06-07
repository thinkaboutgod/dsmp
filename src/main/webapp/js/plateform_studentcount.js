$(function() {
	var path = $("#path").val();
	$("#search").click(function() {
		var schId = $("#schoolSelect").val();
		if (schId == "0") {
			layer.msg("请选择驾校");
			return;
		}
		$.ajax({
			url : path+"/StatisticServlet",
			async : true,
			type : "POST",
			data : {schId:schId},
			dataType : "text",
			success : function(msg) {
				
			},
			error : function() {
			layer.msg("服务器繁忙");
			}
		})
	})
})

function forBar(){
	//BAR CHART
    var bar = new Morris.Bar({
      element: 'bar-chart',
      resize: true,
      data: [
        {y: '2006', a: 100, b: 90},
        {y: '2007', a: 75, b: 65},
        {y: '2008', a: 50, b: 40},
        {y: '2009', a: 75, b: 65},
        {y: '2010', a: 50, b: 40},
        {y: '2011', a: 75, b: 65},
        {y: '2012', a: 100, b: 90}
      ],
      barColors: ['#00a65a', '#f56954'],
      xkey: 'y',
      ykeys: ['a', 'b'],
      labels: ['CPU', 'DISK'],
      hideHover: 'auto'
    });
}