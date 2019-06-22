$(function() {

	//点击了教练评价提交按钮
	$("#coachSubBtn").on({
		"click":function(){
//			alert('点击了教练评价提交按钮');
			var coa_id = $("#coa_id").val();
			var stu_id = $("#stu_id").val();
			var starNum = $("#input-21e").val();
			var ratingContent = $("#ratingContent").val();
			$.ajax({
				url:"addCoachRating.action",//请求发送到哪里
				async:true,//是否异步加载
				type:"POST",//请求方式
				data:{"coa_id":coa_id,"stu_id":stu_id,"starNum":starNum,"ratingContent":ratingContent},//请求的数据
				dataType:"text",//数据类型是文本
				success:function(res){//访问路径成功后的处理
//					alert(res);
					if(res=="success"){
//						alert('评价成功！');
						coaRemind('评价成功！');
						//清空数据：
//						$("#reset").click();
					}else{
//						alert('评价失败！');
						coaRemind('评价失败！');
						
					}
					
					
					
				},
				error:function(){//如下
					alert("没访问到服务器，可能是路径问题！");
				}
				
			});
			
		},
	});
	//点击了驾校评价提交按钮
	$("#schoolSubBtn").on({
		"click":function(){
//			alert('点击了驾校评价提交按钮');
			var sch_id = $("#sch_id").val();
			var stu_id = $("#stu_id").val();
			var starNum = $("input[name='shcoolStarNum']").val();//
			var ratingContent = $("#shcoolRatingContent").val();
//			alert('starNum:'+starNum);
			$.ajax({
				url:"addSchoolRating.action",//请求发送到哪里
				async:true,//是否异步加载
				type:"POST",//请求方式
				data:{"sch_id":sch_id,"stu_id":stu_id,"starNum":starNum,"ratingContent":ratingContent},//请求的数据
				dataType:"text",//数据类型是文本
				success:function(res){//访问路径成功后的处理
//					alert(res);
					if(res=="success"){
//						alert('评价成功！');
						remind('评价成功！');
						//清空数据：
//						$("#shcoolReset").click();
					}else{
//						alert('评价失败！');
						remind('评价失败！');
					}
					
					
					
				},
				error:function(){//如下
					alert("没访问到服务器，可能是路径问题！");
				}
				
			});
			
		},
	});
	//跳动弹框（教练提示）
	function coaRemind(info){
		//信息框-例5
		layer.msg(info, function(){
			//关闭后的操作
			//清空数据：
			$("#reset").click();
		});
	}
	//跳动弹框（驾校提示）
	function remind(info){
		//信息框-例5
		layer.msg(info, function(){
		//关闭后的操作
			$("#shcoolReset").click();
		});
	}

	
});