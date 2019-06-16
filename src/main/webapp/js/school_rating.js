	layui.use('element', function(){
	  var $ = layui.jquery
	  ,element = layui.element;});
	var haoping=0;
	var zhongping=0;
	var chaping=0;
	var zongshu=0;	
	$(document).ready( function () {
		$.ajax({
			url:"selectSchoolRating.action",
			async:true,
			type:"POST",
			data:"chooserating=所有评价",
			dataType:"json",
			success:function(date){
				if(date!==null){
					$.each(date,function(i,val){
						if(val.ratType=="好评"){
							haoping+=1;
						};
						if(val.ratType=="中评"){
							zhongping+=1;
						};
						if(val.ratType=="差评"){
							chaping+=1;
						};
						var time=val.ratTime;
						time = new Date(time).format("yyyy-MM-dd hh:mm:ss")
						$("#rating").append(
								"<dt>"+val.ratType+":"+time+"</dt><dd>"+val.ratContent+"</dd>"
							);
					});
					
					  var bar_data = {
						      data: [[],["好评", haoping], ["中评", zhongping], ["差评", chaping]],
						      color: "blue"
						    };
						 $.plot("#bar-chart", [bar_data], {
						      grid: {
//						        borderWidth: 2,
						        borderColor: "#f3f3f3",
						        tickColor: "#f3f3f3"
						      },
						      series: {
						        bars: {
						          show: true,
						          barWidth: 0.3,
						          align: "center"
						        }
						      },
						      xaxis: {
						        mode: "categories",
						        tickLength: 0
						      }
						    });
						 zongshu=haoping+zhongping+chaping;
						 var hao=haoping/zongshu;
						 var zhong=zhongping/zongshu;
						 var cha=chaping/zongshu;
						    var donutData = [
						      {label: "好评", data: hao, color: "green"},
						      {label: "中评", data: zhong, color: "yellow"},
						      {label: "差评", data: cha, color: "red"}
						    ];
						    $.plot("#donut-chart", donutData, {
						      series: {
						        pie: {
						          show: true,
						          radius: 1,
						          innerRadius: 0.5,
						          label: {
						            show: true,
						            radius: 2 / 3,
						            formatter: labelFormatter,
						            threshold: 0.1
						          }
						        }
						      },
						      legend: {
						        show: false
						      }
						    });
						    function labelFormatter(label, series) {
						        return '<div style="font-size:13px; text-align:center; padding:2px; color: #fff; font-weight: 600;">'
						            + label
						            + "<br>"
						            + Math.round(series.percent) + "%</div>";
						      }	
				}
			},
			error:function(){
				alert("网络出错！请检查网络！");
				
			},
		});
	});
	
	/*选择评价类型*/
	$(function() {
		$(".chooserating").click(function() {
			var choose=$(this).text();
			alert(choose)
			var schId = $("#schId").val();
			$.ajax({
				url:"selectSchoolRating.action",
				data:"chooserating="+choose+"&schId="+schId,
				async:true,
				type:"POST",
				dataType:"json",
				success:function(date){
					if(date!==null){
						$("#rating").empty();
						$.each(date,function(i,val){
							var time=val.ratTime;
							time = new Date(time).format("yyyy-MM-dd hh:mm:ss")
							$("#rating").append(
									"<dt>"+val.ratType+":"+time+"</dt><dd>"+val.ratContent+"</dd>"
								);
						});
					}
				},
				error:function(){
					alert("网络出错！请检查网络！");
				},
			});
		});
	})