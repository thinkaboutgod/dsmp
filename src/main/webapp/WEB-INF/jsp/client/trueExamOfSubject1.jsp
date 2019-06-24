<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目一模拟试卷(正式考)</title>
<%
	String path = request.getContextPath();
%>
<link rel="stylesheet" type="text/css" href=<%=path+"/bootstrap-3.3.7-dist/css/bootstrap.css" %>>
    <link href=<%=path+"/css/exam/main.css" %> rel="stylesheet" type="text/css" />
    <link href=<%=path+"/css/exam/iconfont.css" %> rel="stylesheet" type="text/css" />
    <link href=<%=path+"/css/exam/test.css" %> rel="stylesheet" type="text/css" />
<style type="text/css">
		*{
			margin: 0;
			padding: 0;
		}
		div{
			  /*border: 1px solid black;*/
			  box-sizing: border-box;
			}
	input[type='button']{
		margin:10px 60%;
		width: 55px;
 		height: 25px;
 		color:white;
		background-color: green;
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
 	    background-color: #CCFF9A;
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
 		
 		 #leftDiv > ol > li {
		    width: 100%;
		    border-bottom: 5px solid #efefef;
		    padding-top: 10px;
		}
 		
</style>
<script type="text/javascript" src=<%=path+"/js/jquery-3.3.1.js" %>></script>
<script type="text/javascript" src=<%=path+"/layer/layer.js" %>></script><%--引入layer+jquery就可以实现漂亮的弹框 --%>
<script type="text/javascript" src=<%=path+"/bootstrap-3.3.7-dist/js/bootstrap.js" %>></script>
<script type="text/javascript" src=<%=path+"/js/map.js" %>></script>

<script type="text/javascript">
		
	$(function(){
		var passScore = $("#passScore").val();//通过考试最低分数（90分）
		//倒计时：
		
		var leftDateLong = 45*60*1000;//考试时间设置成45min=45*60*1000
		function countTime(){
			if(leftDateLong>0){
				leftDateLong = leftDateLong-1000;//每执行一次方法剩余时间减去1秒
				
				var leftMinuteInt = Math.floor(leftDateLong/1000/60%60);
				var leftSecondStr = Math.floor(leftDateLong/1000%60);
				var countNode = document.getElementById("countTime");
				countNode.innerText=leftMinuteInt+"分"+leftSecondStr+"秒";
				
			}else{//如果剩余时间小于等于零，则自动提交试卷
// 				alert('时间到');
				submitClick();
				clearInterval(t);//清除定时器
			}
			
		}
		var t = setInterval(countTime,1000);//1秒执行一次

		
		//取到学员id
		var stuId = $("#stuId").val();
		//取到科目号
		var subId = $("#subId").val();
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
		function submitClick(){
// 			alert('提交试卷');			
			layer.msg('提交试卷？', {
				  time: 0 //不自动关闭
				  ,btn: ['确定', '取消']
				  ,yes: function(index){
				    layer.close(index);
				    

			
			
		    	var totalScore="0";
		    	totalScore=parseInt(totalScore);//转化成数值型才能加减
		    examResultMap.each(function(key,value,index){//key表示数据库题目表的topId，value表示学员所选答案是'yes'还是'no'  
		        	var topicOrder = $("input[name='"+key+"']").attr("class");//通过topId找到页面的题目号，yes给这个题目鸟瞰图变成绿色,no红
//			        	alert('页面题号:'+topicOrder);
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
//				alert('得分：'+totalScore);
			$("#resultDiv").append("<h4 style='color:red'>成绩："+totalScore+"分</h4>");
			leftDateLong=0;//剩余时间设置成0
			clearInterval(t);//清除定时器
			$("#countTime").html("已交卷");
			$("input[type='radio']").attr("disabled","disabled");//提交完不能再点击选项
			$("#submitBtn").attr("disabled","disabled");//提交按钮设置成不可点击
// 			$(".answerClass").attr("type","text");//答案由hidden变成text
			$(".answerDetailClass").attr("style","display:block");
			
			//利用Ajax把examResultMap把key(题号topId)和学员此题所选答案对错结果value，还有学员id传给控制类
			var exresMap2json=JSON.stringify(examResultMap);//map转成json字符串
			if(subId==1){//如果session里有科目id，且是1（科目一），则允许提交：
// 				alert('passScore:'+passScore+';totalScore:'+totalScore);
			
				if(totalScore>=passScore){//正式过了科目一（成绩大于90分）
					$.ajax({
						url:"addSubject1Score.action",
						async:true,
						type:"post",
						datatype:"text",
						data:{"stuId":stuId,"subId":subId,"totalScore":totalScore},
						success:function(data){
							if(data=='addSuccess'){//插入成绩成功！
// 								alert('恭喜您！过了科目一！');
								layer.alert('恭喜您！成绩【'+totalScore+'】分，过了科目一！', {icon: 6});//【+'totalScore'+】
								pass();//要把科目一改成科目二，预约状态改成"未预约"
								
							}
							
						},
						error:function(){
							alert("没访问到服务器，可能是路径问题！");
						}
					});
					
				}else{
// 					alert('暂时未过科目一！');
					layer.msg('成绩低于【'+passScore+'】分，没过科目一！已不能再考，等待教练给你安排下一次考试！', {icon: 5});//【+'totalScore'+】
					noPass();//要把学员的科目状态改成"未预约"，不让其继续考试，要等待教练的安排
				}
				
			}
			
				  }
			});
			
		}
		//没通过，科目状态改成"可预约"
		function noPass(){
// 			alert('noPass');
			$.ajax({
				url:"noPass.action",
				async:true,
				type:"post",
				datatype:"text",
				data:{"stuId":stuId,"subId":subId},
				success:function(data){
					if(data=='success'){//状态由"已预约"改成"可预约",等教练再次给他预约考试
// 						alert('只能等待教练再次安排考试！');
						
					}
					
				},
				error:function(){
					alert("没访问到服务器，可能是路径问题！");
				}
			});
		}
		//通过，科目状态改成"未预约"，科目id改成2
		function pass(){
// 			alert('pass');
			$.ajax({
				url:"pass.action",
				async:true,
				type:"post",
				datatype:"text",
				data:{"stuId":stuId,"subId":subId},
				success:function(data){
					if(data=='success'){//状态由"已预约"改成"未预约",科目id改成2
// 						alert('已经通过科目一，不用再考！');
					}
					
				},
				error:function(){
					alert("没访问到服务器，可能是路径问题！");
				}
			});
		}
		//点击了提交试卷按钮，统计一下选对了几道题，计分
		$("#submitBtn").on({
			"click":function(){
				submitClick();				
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
		<c:if test="${topicList==null||topicList==''}">
			<h4>考试条件未达到或未登录！</h4>			
		</c:if>
		<c:if test="${topicList!=null&&topicList!=''}">
			<div class="main">
				<div class="test_main">
					<div class="nr_left">
						<div class="test">
						
			<form action="" method="post">
				<input id="passScore" type="hidden" name="passScore" value="${passScore}"/><%-- 考试通过应达到的分数 --%>
				<input id="stuId" type="hidden" name="stuId" value="${student.stuId}"/>			
	<%-- 			<input id="subId" type="hidden" name="subId" value="${sub_id}"/>		 --%>
				<input id="subId" type="hidden" name="subId" value="${student.subId}"/>		
				<div id="leftDiv" class="test_content_nr">
				
						<div class="test_content">
                            <div class="test_content_title">
                                <h2>科目一考试(单选题)</h2>
                                <p>
<%--                                     <span>共</span><i class="content_lit">${fn:length(topicList)}</i><span>分，</span><i class="content_fs">${fn:length(topicList)*0.9}</i><span>分通过</span> --%>
                                    <span>共</span><i class="content_lit">${fn:length(topicList)}</i><span>分，</span><i class="content_fs">${passScore}</i><span>分通过</span>
                                </p>
                            </div>
                        </div>
				
							<ol type="1" class="topicClass">
								<c:forEach begin="0" step="1" items="${topicList}" var="i" varStatus="topicStatus">				
										<li id="qu_0_0">
										<div class="test_content_nr_tt">
										<i>${topicStatus.count}</i><span>(1分)</span><font>${i.topTopic}</font><!-- ${topicStatus.count}是为了拿到题目序号（页面显示的） -->
										</div>
										</li>
										<div class="test_content_nr_main">
<!-- 										<li> -->
	<%-- 									<img alt="" src=<%=path+"/images/hai.jpg" %>>									 --%>
										<c:if test="${i.topImg!=null&&''!=i.topImg}">
<%-- 											<img alt="" src=<%=path%>${i.topImg}>									 --%>
											<img alt="" src=${topicImgFilePath }${i.topImg}>									
										
										</c:if>
<!-- 										</li> -->
									<ol class="optionClass">
										<c:forEach begin="0" step="1" items="${i.options}" var="j" varStatus="status">
											<li class="option">
											<input id="${j.optId }" class="${topicStatus.count}" type="radio" name="${i.topId }" value="${j.optStatus }">
											<label for="${j.optId }">
<%-- 													<c:if test="${status.index==0}">A.</c:if> --%>
<%-- 													<c:if test="${status.index==1}">B.</c:if> --%>
<%-- 													<c:if test="${status.index==2}">C.</c:if> --%>
<%-- 													<c:if test="${status.index==3}">D.</c:if> --%>
                                                    
                                                    <b class="ue" style="display: inline;">${j.optOption }</b>
                                            </label>
											</li>
											
										</c:forEach>
											
									</ol>
<%-- 									<input class="answerClass" type="hidden" value="答案：${i.topAnswer}.${i.topAnswerDetail}" disabled></input> --%>
									<div class="answerDetailClass" style="display: none">答案：${i.topAnswer}.${i.topAnswerDetail}</div>		
									</div>				
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
				<div id="resultDiv">
				<input id="submitBtn" type="button" value="提交试卷"></input>			
				</div>
					
					<div id="ruleDiv" style="background-color: yellow">
					
						<div><p style="color: red">考试规则:</p></div>
						<div>1.考试时间45分钟，时间到会自动提交。</div>
						<div>2.考试${passScore}分及以上才通过</div>
						<div>3.考试通过则过科目一，学员科目变成科目二，科目状态变成'未预约'</div>
						<div>4.考试没通过则科目状态变回'可预约'，需要等待教练重新安排一场考试 </div>
					
					
					</div>	
					
				</div>

			</form>
					</div>
					</div>
				</div>
			</div>
		</c:if>
</body>
</html>