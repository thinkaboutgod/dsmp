		

	$(function(){
		var path = $("#path").val();
		var passScore = $("#passScore").val();//通过考试最低分数（90分）
		var timeLengthLimit = $("#timeLengthLimit").val();//答题时间间隔限制（单位毫秒）
		var timeLengthLimitOfMin = turnMsToMin(timeLengthLimit);//(单位:分钟)
		
		var stuId = $("#stuId").val();
		var subId = $("#subId").val();
		var coaId = $("#coaId").val();
		var stuBookingstate = $("#stuBookingstate").val();
//		alert('stuId:'+stuId);
//		alert('subId:'+subId);
//		alert('coaId:'+coaId);
//		alert('stuBookingstate:'+stuBookingstate);
		
		$("#toTrueExamId").click(function () {//点击了真实考试
			dealAction("/topic/trueFindManyTopic.action","toTrueExam");		
		});
		$("#toExamId").click(function () {//点击了模拟考试
			dealAction("/topic/findManyTopic.action","toExam");		
		});
		$("#mistakeTopicId").click(function () {//点击了错题集
			dealAction("/topic/findMistakeTopic.action","mistakeTopic");		
		});
		//根据点击的各个功能，去查出最新的student,不同的状态给不同的提示或到不同页面（错题集，模拟考，真实考，不同状态提示不一样）
		function dealAction(url,actionType){
			$.ajax({
				url:path+"/topic/findNewStudent.action",//把目前最新的studnet找出来（之前session里面登录时候放的student，当发生报名，学时学分达标，教练安排了考试等情况时，其属性-科目，科目状态等会随之改变）
				async:true,
				type:"post",
				datatype:"text",
//				data:{"stuId":stuId,"subId":subId},
				success:function(data){
//					alert(data.stuName);
					judgeStu(data.stuId,data.subId,data.coaId,data.stuBookingstate,url,actionType);
				},
				error:function(){
					alert("没访问到服务器，可能是路径问题！");
				}
			});	
		}
		//根据学生的属性来判断目前学生登录，报名，选择教练，所处科目等情况
		function judgeStu(stuId,subId,coaId,stuBookingstate,url,actionType){
			if(stuId!=null&&''!=stuId){//说明有登录
				if(subId!=null&&''!=subId) {//说明有报名（但是教练不一定有选）
//					if(student.getStuId()!=null&&student.getCoaId()!=null&&student.getSchId()!=null&&student.getSubId()!=null) {//说明有报名
					if(coaId!=null&&''!=coaId){//说明已选择教练（已报名）
						if(subId==1){//如果是科一
//							if(actionType=='mistakeTopic'||actionType=='toExam'){//如果点击了错题集或模拟考试
//								window.location.href=path+url;//"/topic/trueFindManyTopic.action"
//							}else if(actionType=='toTrueExam'){//如果点击了真实考试
								if(stuBookingstate=='未预约'){//学时与成绩尚未达到可预约要求
									if(actionType=='toExam'){//如果点击了模拟考试，让其进入
										myInfo('进入计学时学习模式:1.学习时长足够并且之后的考试成绩达标的才可以被教练安排仿真模拟考2.答题间隔超过'+timeLengthLimitOfMin+'分钟,期间的学时就不算数，并自动重新出卷。3.考试时间45分钟，时间到会自动提交。4.做错的题目会在错题集显示',url);
//										window.location.href=path+url;//"/topic/trueFindManyTopic.action"
									}else if(actionType=='mistakeTopic'){//如果点击了错题集，让其进入
										myInfo("进入计学时学习模式:1.模拟卷做错的题目都会在这里显示。2.进入开始计学时，提交就计算一段学时。3.做对的题目会从错题集移除",url);
										
										
									}else if(actionType=='toTrueExam'){//如果点击了真实考试
										layer.msg("您的学时与成绩尚未达到可预约要求，请前往'模拟考试'或'错题练习'补成绩或学时！",{time:5000});
										
									}
								}else if(stuBookingstate=='可预约'){//请等待教练给您安排考试
									layer.msg('您学时与模拟分数已足够，请等待教练给您安排考试！', {icon: 1});
								}else if(stuBookingstate=='已预约'){//教练已经给你安排好了考试
									if(actionType=='mistakeTopic'||actionType=='toExam'){//如果点击了错题集或模拟考试
										layer.msg('学时分数已达标，教练已经为您安排考试，请进入考试页面！', {icon: 1});
									}else if(actionType=='toTrueExam'){//如果点击了真实考试，则进入
//										window.location.href=path+url;//"/topic/trueFindManyTopic.action"
										take('考试时间45分钟，'+passScore+'分通过，祝你好运！',url);
									}
									
								}
								
//							}
							
						}else{//不是科一（是科二、三、四）（您已过了科目一，无需再考）
							if(actionType=='mistakeTopic'){//如果点击了错题集
								layer.msg('您已过科目一，无需错题集！', {icon: 1});
							}else if(actionType=='toExam'){//如果点击了模拟考试
								ask("您已过科目一,模拟卷不计入时长！",url);
							}else if(actionType=='toTrueExam'){//如果点击了正式考试
								layer.msg('您已过科目一，无需再考！', {icon: 1});
							}
						}
					}else{//说明没有选择教练（已报名）
//						 layer.msg('您未选择教练', {icon: 1});
//						 layer.msg('账号报名驾校未审核通过', {icon: 1});
//						 ask("您已报名但驾校尚未审核通过,仅能进入不计学时模式！",url);
						 if(actionType=='mistakeTopic'){//如果点击了错题集
							 layer.msg('您已报名但驾校尚未审核通过，无法进入错题集！', {icon: 1});
							}else if(actionType=='toExam'){//如果点击了模拟考试
								ask("您已报名但驾校尚未审核通过,仅能进入不计学时模式！",url);
							}else if(actionType=='toTrueExam'){//如果点击了正式考试
								layer.msg('您已报名但驾校尚未审核通过，无法进入仿真考场！', {icon: 1});
							}
						 
					}
					
				}else{//说明没报名（已登陆）
					ask('您没报名？去报名页面？',"/student/apply.action");
				}
				
			}else{//没登录
//				alert('暂时没登录，要登录吗？');
				ask('未登录，去登录页面？',"/student/login.action");
			}
		}
		
		//各种情况的询问弹窗
		function ask(info,url){
			//询问框
//			layer.confirm(info, {
//			  btn: ['确定','取消'] //按钮
//			}, function(){
////			  layer.msg('点了确定', {icon: 1});
//				window.location.href=path+url;
//			});
			
			//信息框
			layer.msg(info, {
			  time: 0 //不自动关闭
			  ,btn: ['确定', '取消']
			  ,yes: function(index){
			    layer.close(index);
			    window.location.href=path+url;
			  }
			});
		}
		//跳动弹框
		function take(info,url){
			//信息框-例5
			layer.msg(info, function(){
			//关闭后的操作
				window.location.href=path+url;
			});
		}
		//提示信息：
		function myInfo(info,url){
			layer.alert(info, {
				  skin: 'layui-layer-molv' //样式类名
				  ,closeBtn: 0
				}, function(){
					window.location.href=path+url;
				});
		}
		//把毫秒转化成分钟:
		function turnMsToMin(msStr){
			var ms = parseInt(msStr);
			var minute = parseFloat(ms/1000/60);
			minute=minute.toFixed(2);
			return minute;
			
		}
		

	});