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
    <link href=<%=path+"/css/exam/main.css" %> rel="stylesheet" type="text/css" />
    <link href=<%=path+"/css/exam/iconfont.css" %> rel="stylesheet" type="text/css" />
    <link href=<%=path+"/css/exam/test.css" %> rel="stylesheet" type="text/css" />
<style type="text/css">

 	#leftDiv{ 
/*  		float: left;  */
/*  		width: 70%;  */
 /* 		height: 600px; */ 
 		/*box-sizing: border-box;*/ 
/*   		background-color: #68DFFF;  */
  		background-color: #CCFF9A; 
 	} 
	img{
 			height: 100px;
 			width: 300px;
 		}
 	ol{
		list-style:none;
	}
/* 	.answerDetailId{ */
/* 		width:100%; */
/* 		disabled:disabled; */
		
/* 	} */
	input[type='button']{
/* 		margin:10px 60%; */
		width: 55px;
 		height: 25px;
 		color:white;
		background-color: green;
	}
 	#btnDiv{ 
		width: 500px;
		height: 50px;
 	} 
	
/* 	 #leftDiv > ol > li { */
/* 	    width: 100%; */
/* 	    border-bottom: 5px solid #efefef; */
/* 	    padding-top: 10px; */
/* 	} */
	#topicDiv{
		 width: 100%;
	}
	#conTopDiv {
		margin-left:90px;
	    width: 80%;
	    border-bottom: 5px solid #efefef;
	    padding-top: 10px;
	}
	#replyId{
		margin-left:70px;
	}

</style>
<script type="text/javascript" src=<%=path+"/js/jquery-3.3.1.js" %>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-3.3.7-dist/js/bootstrap.js" %>></script>
<script type="text/javascript">
	$(function(){
		var path = $("#pathId").val();//路径
		var topicImgFilePath = $("#topicImgFilePath").val();//图片路径
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
				$("#topicDiv").append('<div id="conTopDiv" class="test_content_nr_tt"><i><span>'+(topicOrder+1)+'</span></i><font>'+allTopList[topicOrder].topTopic+'</font></div>');
				$("#topicDiv").append('<div id="conDiv" class="test_content_nr_main"></div>');
				if(allTopList[topicOrder].topImg!=null && ''!=allTopList[topicOrder].topImg){
// 					$("#conDiv").append('<img src='+path+allTopList[topicOrder].topImg+'>');//如果该题有图片，则引入
					$("#conDiv").append('<img src='+topicImgFilePath+allTopList[topicOrder].topImg+'>');//如果该题有图片，则引入
				}
				
				$("#conDiv").append('<ol id="optionId" class="optionClass"></ol>');
				var optionList = allTopList[topicOrder].options;
				for(var i=0;i<optionList.length;i++){//每题4个选项找出来
// 					alert(optionList[i].optOption);
					$("#optionId").append('<li id=opt'+optionList[i].optId+' class="option"><input class="radiotop" id='+optionList[i].optId+' name='+allTopList[topicOrder].topId+' type="radio" value='+optionList[i].optStatus+'></li>');//
// 					$("#opt"+optionList[i].optId).append('<label for='+optionList[i].optId+'>'+getABCD(i)+'<b class="ue" style="display: inline;">'+optionList[i].optOption+'</label>');
					$("#opt"+optionList[i].optId).append('<label for='+optionList[i].optId+'><b class="ue" style="display: inline;">'+optionList[i].optOption+'</label>');

					
					
				}
				$("#conDiv").append('<div class="answerDetailClass" style="display: none">答案：'+allTopList[topicOrder].topAnswer+'.'+allTopList[topicOrder].topAnswerDetail+'</div>');
				
			},
			
		});
		function getABCD(i){//根据序号得出abcd
			switch(i){
			case 0:
				return 'A.';
				break;
			case 1:
				return 'B.';
				break;
			case 2:
				return 'C.';
				break;
			case 3:
				return 'D.';
				break;
			}
			
		}
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
				$("#topicDiv").append('<div id="conTopDiv" class="test_content_nr_tt"><i><span>'+(topicOrder+1)+'</span></i><font>'+allTopList[topicOrder].topTopic+'</font></div>');
				$("#topicDiv").append('<div id="conDiv" class="test_content_nr_main"></div>');
				if(allTopList[topicOrder].topImg!=null && ''!=allTopList[topicOrder].topImg){
// 					$("#conDiv").append('<div><img src='+path+allTopList[topicOrder].topImg+'><div>');
					$("#conDiv").append('<div><img src='+topicImgFilePath+allTopList[topicOrder].topImg+'><div>');
				}
				
				$("#conDiv").append('<ol id="optionId" class="optionClass"></ol>');
				var optionList = allTopList[topicOrder].options;
				for(var i=0;i<optionList.length;i++){//每题4个选项找出来
// 					alert(optionList[i].optOption);				
					$("#optionId").append('<li id=opt'+optionList[i].optId+' class="option"><input class="radiotop" id='+optionList[i].optId+' name='+allTopList[topicOrder].topId+' type="radio" value='+optionList[i].optStatus+'></li>');//
// 					$("#opt"+optionList[i].optId).append('<label for='+optionList[i].optId+'>'+getABCD(i)+'<b class="ue" style="display: inline;">'+optionList[i].optOption+'</label>');
					$("#opt"+optionList[i].optId).append('<label for='+optionList[i].optId+'><b class="ue" style="display: inline;">'+optionList[i].optOption+'</label>');
				}
				$("#conDiv").append('<div class="answerDetailClass" style="display: none">答案：'+allTopList[topicOrder].topAnswer+'.'+allTopList[topicOrder].topAnswerDetail+'</div>');
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
			$(".answerDetailClass").attr("style","display:block");

			
			
		});

		//按钮特效：
		//给提交按钮添加鼠标经过变颜色的事件
		$("input[type='button']").on({
			"mouseover":function(){
				$(this).css({
					"color":"black",
					"background-color":"#68DFFF",
				});
			},
			"mouseout":function(){
				$(this).css({
					"color":"white",
					"background-color":"green",
				});
			},
		});
		//图片缩放：
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
		//图片缩放：append的这个才奏效：
		$(document).on("click","img",function(){
			$(this).css({
				"height":"200px",
				"width":"600px",
			});
		});
		$(document).on("mouseout","img",function(){
			$(this).css({
				"height":"100px",
				"width":"300px",
			});
		});
		
			});

</script>
</head>
<body>
			<div class="main">
				<div class="test_main">
					<div class="nr_left">
						<div class="test">
	<form action="" method="post">
		<input id="pathId" type="hidden" name="path" value=<%=path%>/>		
		<input id="topicImgFilePath" type="hidden" name="topicImgFilePath" value=${topicImgFilePath}/>		
		<input id="stuId" type="hidden" name="stuId" value="${stu_id}"/>			
		<input id="subId" type="hidden" name="subId" value="${sub_id}"/>
		<input id="allTopicListId" type="hidden" name="allTopicList" value="${allTopicList}"/>
		<div id="leftDiv">
						<div class="test_content">
                            <div class="test_content_title">
                                <h2>海量练习题</h2>
                                <p>
                                    <span>共</span><i class="content_lit">${fn:length(allTopicList)}</i><i class="content_fs">题</i>
                                </p>
                            </div>
                        </div>
			<div id="topicDiv" class="test_content_nr">
			
				<div id="conTopDiv" class="test_content_nr_tt">
				<i><span>1</span></i><font>${allTopicList[0].topTopic}</font><!-- ${topicStatus.count}是为了拿到题目序号（页面显示的） -->				
				</div>
				<div class="test_content_nr_main">
				<c:if test="${allTopicList[0].topImg!=null&&''!=allTopicList[0].topImg}">
					<img alt="" src=${topicImgFilePath }${allTopicList[0].topImg}>									
									
				</c:if>
<%-- 				<img alt="" src=<%=path+"/images/hai.jpg" %>>	 --%>
				<ol class="optionClass">
					<c:forEach begin="0" step="1" items="${allTopicList[0].options}" var="j" varStatus="status">
						<li class="option">
						<input id="${j.optId }" class="radiotop" type="radio" name="${allTopicList[0].topId }" value="${j.optStatus }">
						<label for="${j.optId }">
<%-- 						<c:if test="${status.index==0}">A.</c:if> --%>
<%-- 						<c:if test="${status.index==1}">B.</c:if> --%>
<%-- 						<c:if test="${status.index==2}">C.</c:if> --%>
<%-- 						<c:if test="${status.index==3}">D.</c:if> --%>
	                                            
	                    <b class="ue" style="display: inline;">${j.optOption }</b>
	                    </label>
						</li>
						
					</c:forEach>
				</ol>
<!-- 				<textarea rows="" cols=""></textarea> -->
<%-- 				<input class="answerDetailId" type="text" disabled="disabled" value="答案：${allTopicList[0].topAnswer}.${allTopicList[0].topAnswerDetail}" /> --%>
				<div class="answerDetailClass" style="display: none">答案：${allTopicList[0].topAnswer}.${allTopicList[0].topAnswerDetail}</div>
				</div>
			</div>
			<div id="btnDiv">
				<input id="upBtn" type="button" value="上一题"></input>			
				<input id="downBtn" type="button" value="下一题"></input>	
			</div>
			
			
		</div>

	</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>