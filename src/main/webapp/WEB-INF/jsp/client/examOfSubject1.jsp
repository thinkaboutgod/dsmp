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
	$(function(){
		
		//答题相关：
		var examResultMap = new Map();     
		//选中了题目，则获取当前页面题目序号，方格子区域变成蓝色表示改题已经选择
		$("input[type='radio']").on({
			"click":function(){
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
			    	var totalScore="0";
			    	totalScore=parseInt(totalScore);
			    examResultMap.each(function(key,value,index){//key表示数据库题目表的topId，value表示学员所选答案是'yes'还是'no'  
			        	var topicOrder = $("input[name='"+key+"']").attr("class");//通过topId找到页面的题目号，yes给这个题目鸟瞰图变成绿色,no红
// 			        	alert('页面题号:'+topicOrder);
			        if('yes'==value){//如果答对了就加1分，且颜色变成绿
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
				$(".answerClass").attr("type","text");
			}
		});
		//点击了重新出卷按钮，到从新出卷的控制类方法那边去
		$("#updateExamBtn").on({
			"click":function(){
				alert('重新出卷');
				window.location.href="findManyTopic.action";
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
						
			<div id="leftDiv">
						<ol type="1" class="topicClass">
							<c:forEach begin="0" step="1" items="${topicList}" var="i" varStatus="topicStatus">				
									<li>
									<span>${topicStatus.count}.</span>${i.topTopic }<!-- ${topicStatus.count}是为了拿到题目序号（页面显示的） -->
									</li>
									<li>
									<img alt="" src=<%=path+"/images/hai.jpg" %>>									
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