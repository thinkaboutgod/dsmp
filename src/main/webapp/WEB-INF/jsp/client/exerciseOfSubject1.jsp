<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目一练习题</title>
<%
	String path = request.getContextPath();
%>
<link rel="stylesheet" type="text/css" href=<%=path+"/bootstrap-3.3.7-dist/css/bootstrap.css" %>>
<style type="text/css">
	img{
 			height: 100px;
 			width: 300px;
 		}
 	ol{
		list-style:none;
	}
	.answerDetailId{
		disabled:disabled;
		
	}
</style>
<script type="text/javascript" src=<%=path+"/js/jquery-3.3.1.js" %>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-3.3.7-dist/js/bootstrap.js" %>></script>
<script type="text/javascript">
	$(function(){
		var path = $("#pathId").val();//路径
		//取到学员id
		var stuId = $("#stuId").val();
		//取到科目号
		var subId = $("#subId").val();
		var allTopList = $("#allTopicListId").val();
		var topicOrder = 0;
		$("#upBtn").attr("disabled","disabled");
		$.ajax({
			url:"findAllTopicJs.action",
			type:"post",
			data:{"sub_id":subId},
			success:function(res){
//  				alert(res);
//  				alert(res[0].topTopic);
 				allTopList = res;
				
			},
			error:function(){
				alert("可能路径有问题，未访问到服务器");
			}
		});
		
		
		$("#upBtn").on({
			"click":function(){
// 				alert('点击了上一题');
				topicOrder--;
				if(topicOrder<=0){
					$("#upBtn").attr("disabled","disabled");//禁止点击
				}
				if(topicOrder==allTopList.length-2){
					$("#downBtn").removeAttr("disabled");//解除不可点击按钮
				}
				
				$("#topicDiv").empty();//每次点击下一题先把之前的删除
				$("#topicDiv").append((topicOrder+1)+'.'+allTopList[topicOrder].topTopic);
				if(allTopList[topicOrder].topImg!=null && ''!=allTopList[topicOrder].topImg){
					$("#topicDiv").append('<div><img src='+path+allTopList[topicOrder].topImg+'><div>');
				}
				
				$("#topicDiv").append('<ol id="optionId" class="optionClass"></ol>');
				var optionList = allTopList[topicOrder].options;
				for(var i=0;i<optionList.length;i++){//每题4个选项找出来
// 					alert(optionList[i].optOption);
					$("#optionId").append('<li><input class="radiotop" id='+optionList[i].optId+' name='+allTopList[topicOrder].topId+' type="radio" value='+optionList[i].optStatus+'>'+optionList[i].optOption+'</li>');						
					
				}
				$("#optionId").append('<input class="answerDetailId" type="hidden" disabled="disabled" value="'+allTopList[topicOrder].topAnswer+'.'+allTopList[topicOrder].topAnswerDetail+'">');
				
			},
			
		});

		$("#downBtn").on({
			"click":function(){
// 				alert('点击了下一题'+allTopList[topicOrder].topTopic);
				topicOrder++;
// 				alert("topicOrder:"+topicOrder);
				if(topicOrder==1){
					$("#upBtn").removeAttr("disabled");//解除不可点击按钮
				}
				if(topicOrder==allTopList.length-1){
					$("#downBtn").attr("disabled","disabled");
				}
				$("#topicDiv").empty();//每次点击下一题先把之前的删除
				$("#topicDiv").append((topicOrder+1)+'.'+allTopList[topicOrder].topTopic);
				if(allTopList[topicOrder].topImg!=null && ''!=allTopList[topicOrder].topImg){
					$("#topicDiv").append('<div><img src='+path+allTopList[topicOrder].topImg+'><div>');
				}
				
				$("#topicDiv").append('<ol id="optionId" class="optionClass"></ol>');
				var optionList = allTopList[topicOrder].options;
				for(var i=0;i<optionList.length;i++){//每题4个选项找出来
// 					alert(optionList[i].optOption);
					$("#optionId").append('<li><input class="radiotop" id='+optionList[i].optId+' name='+allTopList[topicOrder].topId+' type="radio" value='+optionList[i].optStatus+'>'+optionList[i].optOption+'</li>');						
					
				}
				$("#optionId").append('<input class="answerDetailId" type="hidden" disabled="disabled" value="答案：'+allTopList[topicOrder].topAnswer+'.'+allTopList[topicOrder].topAnswerDetail+'">');
					},
					
				});
		//点击选项的时候判断对错。
		$(document).on("click",".radiotop",function(){
// 			alert(2332);
			$("input[type='radio']").attr("disabled","disabled");//选完不能再点击选项
			//清空
			$("#replyId").remove();//每次点击下一题先把之前的删除
			//获取学员答错选项（选项表（数据库中）的optStatus为'no'）所属题目id
			var optStatus = $(this).attr("value");
			var topId = $(this).attr("name");
// 			alert('选项对错：'+optStatus);
// 			alert('选项所属数据库题目表id：'+parseInt(topId));
			if('yes'==optStatus){
// 				alert('恭喜回答正确！');
				$("#topicDiv").append('<p id="replyId" style="color:green">恭喜回答正确！</p>');
				//回答正确，则删除错题集中记录（先判断是否已存在错题集）
/*先不实现 				$.ajax({
					url:"delMistakeCollection2exercise.action",
					type:"post",
		 			data:{"studentId":stuId,"subId":subId,"topId":topId},
					success:function(res){
//		  				alert(res);
//		  				alert(res[0].topTopic);
		 				allTopList = res;
						
					},
					error:function(){
						alert("可能路径有问题，未访问到服务器");
					}
				}); */
			}else{
// 				alert('回答错误！');
				$("#topicDiv").append('<p id="replyId" style="color:red">回答错误！</p>');
				//回答错误，则插入错题集（先判断是否已存在错题集）
/* 先不实现				$.ajax({
					url:"addMistakeCollection2exercise.action",
					type:"post",
		 			data:{"studentId":stuId,"subId":subId,"topId":topId},
					success:function(res){
//		  				alert(res);
//		  				alert(res[0].topTopic);
		 				allTopList = res;
						
					},
					error:function(){
						alert("可能路径有问题，未访问到服务器");
					}
				}); */
				
			}
			$(".answerDetailId").attr("type","text");
			
		});

		
			});

</script>
</head>
<body>
	<form action="" method="post">
		<input id="pathId" type="hidden" name="path" value=<%=path%>/>			
		<input id="stuId" type="hidden" name="stuId" value="${stu_id}"/>			
		<input id="subId" type="hidden" name="subId" value="${sub_id}"/>
		<input id="allTopicListId" type="hidden" name="allTopicList" value="${allTopicList}"/>
		<div id="leftDiv">
			<div id="topicDiv">
				
				<span>1.</span>${allTopicList[0].topTopic}
				<div><c:if test="${allTopicList[0].topImg!=null&&''!=allTopicList[0].topImg}">
					<img alt="" src=<%=path%>${allTopicList[0].topImg}>									
									
				</c:if></div>
				<ol class="optionClass">
					<c:forEach begin="0" step="1" items="${allTopicList[0].options}" var="j">
						<li><input id="${j.optId }" class="radiotop" type="radio" name="${allTopicList[0].topId }" value="${j.optStatus }">${j.optOption }</li>
						
					</c:forEach>
				</ol>
				<input class="answerDetailId" type="hidden" disabled="disabled" value="答案：${allTopicList[0].topAnswer}.${allTopicList[0].topAnswerDetail}" />
			</div>
			<input id="upBtn" type="button" value="上一题"></input>
			<input id="downBtn" type="button" value="下一题"></input>	
		</div>
		<div id="rightDiv">
		
		</div>	
	</form>
</body>
</html>