<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目一模拟试卷</title>
<%
	String path = request.getContextPath();
%>
<link rel="stylesheet" type="text/css" href=<%=path+"/bootstrap-3.3.7-dist/css/bootstrap.css" %>>
<style type="text/css">
		*{
			margin: 0;
			padding: 0;
		}
		div{
			  /*border: 1px solid black;*/
			  box-sizing: border-box;
			}
 	.optionClass{
		list-style:none;
	} 
	#leftDiv{
		float: left;
		width: 70%;
/* 		height: 600px; */
		/*box-sizing: border-box;*/
		background-color: #68DFFF;
	}
	#rightDiv{
		float: right;
	    width: 30%;
/* 	    height: 600px; */
/* 	    background-color: #CCFF9A; */
	    box-sizing: border-box;
	    border: 2px solid gray;
	}
      table{
      	margin:auto;
        width: 95%;
/*         height: 300px; */
        text-align:center;
        border:1px solid black;
        border-collapse:collapse;
      }
     	td{
     		border:#000 1px solid;
     		width: 20px;
     		height: 20px;
     	} 
 		img{
 			height: 100px;
 			width: 300px;
 		}
 		#countTime{
 			color: red;
 		}
</style>
<script type="text/javascript" src=<%=path+"/js/jquery-3.3.1.js" %>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-3.3.7-dist/js/bootstrap.js" %>></script>
<script type="text/javascript" src=<%=path+"/js/map.js" %>></script>
<script type="text/javascript">
		//倒计时：
		setInterval("countTime()",1000);//1秒执行一次
		var leftDateLong = 45*60*1000;//考试时间设置成45min
		function countTime(){
			if(leftDateLong>0){
				leftDateLong = leftDateLong-1000;//每执行一次方法剩余时间减去1秒
				
				var leftMinuteInt = Math.floor(leftDateLong/1000/60%60);
				var leftSecondStr = Math.floor(leftDateLong/1000%60);
				var countNode = document.getElementById("countTime");
				countNode.innerText=leftMinuteInt+"分"+leftSecondStr+"秒";
				
			}
			
		}
		//计算百分比：
		function getPercent(num, total) {//用法：getPercent(1,100)；
		    /// <summary>
		    /// 求百分比
		    /// </summary>
		    /// <param name="num">当前数</param>
		    /// <param name="total">总数</param>
		    num = parseFloat(num);
		    total = parseFloat(total);
		    if (isNaN(num) || isNaN(total)) {
		        return "-";
		    }
		    return total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00)+"%";
		}
	$(function(){
		//记录点击先后时间：
		var arr = new Array();//arr[0]=new Date();arr[1]=new Date();
		//偶数点击给arr[0]，奇数给arr[1]
		arr[0]=new Date();//初始给一个时间
		var clickTimes = 0;
		var timeLengthLimit =20*60*1000;//答题时间长度限制（20min） 20*60*1000
		//取到学员id
		var stuId = $("#stuId").val();
		//取到科目号
		var subId = $("#subId").val();
		//答题相关：
		var examResultMap = new Map();     
		//选中了题目，则获取当前页面题目序号，方格子区域变成蓝色表示改题已经选择
		$("input[type='radio']").on({
			"click":function(){
				//根据点击的次数是偶数还是奇数，决定当前时间放arr[0]还是arr[1]
				clickTimes++;
// 				alert("clickTimes:"+clickTimes);
				if(clickTimes%2==0){//偶数
					arr[0] = new Date();
// 					alert('偶数：'+arr[1]);
				}else{//奇数
					arr[1] = new Date();	
// 					alert('奇数：'+arr[0]);
				}
				if(arr[1]>arr[0]){
					var timeLenth = arr[1]-arr[0];
				}else{
					var timeLenth = arr[0]-arr[1];
				}
// 				alert('点击时间间隔:'+timeLenth);//点击的时间间隔超过30分钟就视为挂机(单位：毫秒)
				if(timeLenth>timeLengthLimit){//点击的时间间隔超过30分钟就视为挂机(单位：毫秒)
					alert('答题时间间隔超20分钟，有挂机嫌疑，计时无效！'+timeLenth+'s');
					alert('重新出卷');
					window.location.href="findManyTopic.action?stu_id="+stuId+"&sub_id="+subId;
					return;
				}
				
				
				var topicOrder = $(this).attr("class");//获取当前页面题目序号
// 				alert('选题'+topicOrder);
				$("#view"+topicOrder).css({
					"background-color":"#68DFFF",
				});
				//获取学员答错选项（选项表（数据库中）的optStatus为'no'）所属题目id
				var optStatus = $(this).attr("value");
				var topId = $(this).attr("name");
// 				alert('选项对错：'+optStatus);
// 				alert('选项所属数据库题目表id：'+parseInt(topId));
				examResultMap.put(parseInt(topId),optStatus);//勾选同样的题目，最终只会保存一个题目结果
				  
			    examResultMap.each(function(key,value,index){//key表示数据库题目表的topId，value表示学员所选答案是'yes'还是'no'     
// 			        alert('key:'+key+',value:'+value);
						if('yes'==value){
// 			        		alert(key+'题答对了')
			        	} 
			        
			    });  
			    
				
			},
		});
		//点击了提交试卷按钮，统计一下选对了几道题，计分
		$("#submitBtn").on({
			"click":function(){
				alert('提交试卷');
				//提交试卷的时候跟上一次选择的时候是否间隔过久？
				var commitTime = new Date();
// 				alert(arr.length);
				if(arr.length==1){//说明没有点击过选项
					if(commitTime-arr[0]>timeLengthLimit){
						alert('答题时间间隔超20分钟，有挂机嫌疑，计时无效！'+(commitTime-arr[0])+'ms');
						alert('重新出卷');
						window.location.href="findManyTopic.action?stu_id="+stuId+"&sub_id="+subId;
						return;
					}
				}else if(arr.length==2){
					if(arr[1]>arr[0]){
						if(commitTime-arr[1]>timeLengthLimit){
							alert('答题时间间隔超20分钟，有挂机嫌疑，计时无效！'+(commitTime-arr[1])+'s');
							alert('重新出卷');
							window.location.href="findManyTopic.action?stu_id="+stuId+"&sub_id="+subId;
							return;
						}
					}else{
						if(commitTime-arr[0]>timeLengthLimit){
							alert('答题时间间隔超20分钟，有挂机嫌疑，计时无效！'+(commitTime-arr[0])+'s');
							alert('重新出卷');
							window.location.href="findManyTopic.action?stu_id="+stuId+"&sub_id="+subId;
							return;
						}
					}
				}
				
			    	var totalScore="0";
			    	totalScore=parseInt(totalScore);
			    examResultMap.each(function(key,value,index){//key表示数据库题目表的topId，value表示学员所选答案是'yes'还是'no'  
			        	var topicOrder = $("input[name='"+key+"']").attr("class");//通过topId找到页面的题目号，yes给这个题目鸟瞰图变成绿色,no红
// 			        	alert('页面题号:'+topicOrder);
			        if('yes'==value){//如果答对了就加1分，且颜色变成绿，且错题集要是有这一题就删除掉
			        	totalScore=totalScore+1;
						$("#view"+topicOrder).css({
							"background-color":"green",
						});
						
			        }else if('no'==value){//如果答错了这题就加入错题集,且颜色变成红
						$("#view"+topicOrder).css({
							"background-color":"red",
						});
						
			        }  
			    }); 
// 				alert('得分：'+totalScore);
				$("#resultDiv").append("<p>得分："+totalScore+"</p>");
				leftDateLong=0;//剩余时间设置成0
				$("#countTime").html("已交卷");
				$("input[type='radio']").attr("disabled","disabled");//提交完不能再点击选项
				$("#submitBtn").attr("disabled","disabled");//提交按钮设置成不可点击
				$(".answerClass").attr("type","text");//答案由hidden变成text
				
				//利用Ajax把examResultMap把key(题号topId)和学员此题所选答案对错结果value，还有学员id传给控制类
				var exresMap2json=JSON.stringify(examResultMap);//map转成json字符串
// 				alert("exresMap2json:"+exresMap2json);
				$.ajax({
					url:"addOrDelMistakeCollection.action",
					type:"post",
					datatype:"json",
					data:{"jsonData":exresMap2json,"studentId":stuId,"subId":subId},
					success:function(data){
// 						alert(data);
// 						$.each(data,function(i,val){
// 							alert(i);
// 							alert(val);							
// 						});
						
						if(data==null||data=='') {
// 							alert('您在线超24小时，有挂机嫌疑，本次学习时长不算数。');				
						}else{
	// 						var json2map=JSON.parse(data);
							alert('本次学习合规，计入时长！');				
// 							alert('currTotalTimeLength:'+data["currTotalTimeLength"]);//当前总时长
// 							alert('totalTimeLength:'+data["totalTimeLength"]);//要求总时长
							var currTotalTimeLength = data["currTotalTimeLength"];
							var totalTimeLength = data["totalTimeLength"];
							$("#studyTimeSpan").text(currTotalTimeLength+"/"+totalTimeLength);
// 							$("#percentageId").text((currTotalTimeLength/totalTimeLength)*100+"%");
							var percent = getPercent(currTotalTimeLength, totalTimeLength);
							$("#percentageId").text(percent);//上面写的方法
							$("#percentDiv").attr("style","width:"+percent);//上面写的方法
							
							
						}
						
					},
					error:function(){
						alert("可能路径有问题，未访问到服务器");
					}					
				});
				
			}
		});
		//点击了重新出卷按钮，到从新出卷的控制类方法那边去
		$("#updateExamBtn").on({
			"click":function(){
				alert('重新出卷');
				window.location.href="findManyTopic.action?stu_id="+stuId+"&sub_id="+subId;
			}
		});
		$("img").on({
			"click":function(){
				$(this).css({
					"height":"200px",
					"width":"600px",
				});
			},
			"mouseout":function(){
				$(this).css({
					"height":"100px",
					"width":"300px",
				});
			}
		});
		
	});
</script>
</head>
<body>
	
		<form action="" method="post">
			<input id="stuId" type="hidden" name="stuId" value="${stu_id}"/>			
			<input id="subId" type="hidden" name="subId" value="${sub_id}"/>			
			<div id="leftDiv">
						<ol type="1" class="topicClass">
							<c:forEach begin="0" step="1" items="${topicList}" var="i" varStatus="topicStatus">				
									<li>
									<span>${topicStatus.count}.</span>${i.topTopic}<!-- ${topicStatus.count}是为了拿到题目序号（页面显示的） -->
									</li>
									<li>
<%-- 									<img alt="" src=<%=path+"/images/hai.jpg" %>>									 --%>
									<c:if test="${i.topImg!=null&&''!=i.topImg}">
										<img alt="" src=<%=path%>${i.topImg}>									
									
									</c:if>
									</li>
								<ol class="optionClass">
									<c:forEach begin="0" step="1" items="${i.options}" var="j">
										<li><input id="${j.optId }" class="${topicStatus.count}" type="radio" name="${i.topId }" value="${j.optStatus }">${j.optOption }</li>
										
									</c:forEach>
										
								</ol>
								<input class="answerClass" type="hidden" value="答案：${i.topAnswer}.${i.topAnswerDetail}" disabled></input>					
							</c:forEach>
						</ol>
			</div>
			<div id="rightDiv">
		<%-- 		${fn:length(topicList)} 该份试卷共有几道题 --%>
				剩余时间：<span id="countTime">45分00秒</span>	
<!-- 				进度条 -->
				<p>学习时长/任务时长(单位：秒)：<span id="studyTimeSpan">${currTotalTimeLength}/${totalTimeLength}</span></p>
				<div class="progress">
				  <div id="percentDiv" class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: ${percentage}">
				    <span class="sr-only">45% Complete</span>
				  </div>
				    <span id="percentageId">${percentage}</span>
				</div>
				
				<table>
				<c:forEach begin="0" step="5" items="${topicList}" varStatus="topicStatus">
				<tr>
					<c:forEach begin="${topicStatus.index}" end="${(topicStatus.index)+4}" step="1" items="${topicList}" varStatus="tStatus">
						<td id="view${(tStatus.index)+1}">${(tStatus.index)+1}</td>
							
						
						
					</c:forEach>
					</tr>
					</c:forEach>
					
		<!-- 			<tr>
						<td>1</td>
						<td>2</td>
						<td>3</td>
					</tr>
					<tr>
						<td style="background-color: green">4</td>
						<td>5</td>
						<td>6</td>
					</tr> -->
				</table>
				
				
			</div>
			<div id="resultDiv">
			<input id="submitBtn" type="button" value="提交试卷"></input>
			<input id="updateExamBtn" type="button" value="重新出题"></input>
			
			</div>
		</form>
</body>
</html>