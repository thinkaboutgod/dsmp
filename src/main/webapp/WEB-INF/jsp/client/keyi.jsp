<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = application.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目一刷题</title>
<link href=<%=path+"/css/keyi/main.css" %> rel="stylesheet">
<link rel="stylesheet" href=<%=path + "/css/home/comm_style.css"%>>
<link href=<%=path + "/css/home/css.css"%> rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href=<%=path + "/css/home/animate.css"%>
	type="text/css">
<link href=<%=path + "/css/home/green.css"%> title="" rel="stylesheet"
	type="text/css" />
<style type="text/css">
	.layer_notice {
	    float: left;
	    height: 75px;
	    width: 330px;
	    overflow: hidden;
	    background: #5FB878;
	    padding: 10px;
}
</style>
<!-- <script type="text/javascript" -->
<%-- 	src=<%=path + "/js/home/jquery1.42.min.js"%>></script>这原先是文志引入的jQuery，但是版本太低，导致layer弹窗不能用，故注释掉换成下面的 jquery-3.3.1.js--%>
<script type="text/javascript" src=<%=path+"/js/jquery-3.3.1.js" %>></script>
<script type="text/javascript"
	src=<%=path + "/js/home/jquery.superslide.2.1.1.js"%>></script>
	<script type="text/javascript" src=<%=path+"/layer/layer.js" %>></script><%--引入layer+jquery就可以实现漂亮的弹框 --%>
	<script type="text/javascript" src=<%=path + "/js/home/aos.js"%>></script>
	<script type="text/javascript" src=<%=path + "/js/student/keyi.js"%>></script>

</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<script type="text/javascript">
		jQuery(".nav").slide({
			type : "menu", //效果类型
			titCell : ".m", // 鼠标触发对象
			targetCell : ".sub", // 效果对象，必须被titCell包含
			effect : "slideDown", //下拉效果
			delayTime : 300, // 效果时间
			triggerTime : 0, //鼠标延迟触发时间
			returnDefault : true
		//返回默认状态
		});

	</script>
	<div class="hiddenDiv">
		<input id="path" type="hidden" value=<%=path %>>
		<input id="passScore" type="hidden" name="passScore" value="${passScore}"/><%-- 考试通过应达到的分数 --%>
		<input id="timeLengthLimit" type="hidden" name="timeLengthLimit" value="${timeLengthLimit}"/><%-- 答题时间间隔限制 --%>
		<input id="stuId" type="hidden" value="${student.stuId}">
		<input id="subId" type="hidden" value="${student.subId}">
		<input id="coaId" type="hidden" value="${student.coaId}">
		<input id="stuBookingstate" type="hidden" value="${student.stuBookingstate}">
	</div>

	<div class="main">
			<div class="ks-right pull-right">
				<div class="ks-right-main">
					<div class="ks-right-main-title title-hf">
						<h2>小车</h2>
						<a href="" class="on">科目一</a>
						<a href="">科目四</a>
					</div>
					<div class="ks-right-main-content">
						<div class="ks-right-note clear">
							（新科目一驾校一点通模拟考试c1 ，适用车型：C1/C2照）
						</div>
						<div class="ks-right-main-links clear">
							<div class="item">
								<div class="item-logo">
									<a class="fill-color fill-yellow" href=<%=path+"/topic/findAllTopic.action?stu_id=2&sub_id=1"%> target="blank">
										<div class="lg"></div>
									</a>
								</div>
								<div class="item-txt">
									<a href="">顺序练习</a>
								</div>
							</div>
							<div class="item">
								<div class="item-logo">
									<a class="fill-color fill-orange" href="">
										<div class="lg"></div>
									</a>
								</div>
								<div class="item-txt">
									<a href="">随机练习</a>
								</div>
							</div>
							<div class="item">
								<div class="item-logo">
<%-- 									<a class="fill-color fill-green" href=<%=path+"/topic/findMistakeTopic.action"%> target="blank"><!-- ?stu_id=2&sub_id=1 --> --%>
									<a class="fill-color fill-green" id="mistakeTopicId" target="blank"><!-- ?stu_id=2&sub_id=1 -->
										<div class="lg"></div>
									</a>
								</div>
								<div class="item-txt">
									<a href="">错题练习</a>
								</div>
							</div>
							<div class="item">
								<div class="item-logo">
									<a class="fill-color fill-blue" href="">
										<div class="lg"></div>
									</a>
								</div>
								<div class="item-txt">
									<a href="">章节练习</a>
								</div>
							</div>
							
							<div class="item">
								<div class="item-logo">
									<a class="fill-color fill-rose" href="">
										<div class="lg"></div>
									</a>
								</div>
								<div class="item-txt">
									<a href="">专项练习</a>
								</div>
							</div>
							<div class="item">
								<div class="item-logo">
<%-- 									<a class="fill-color fill-pink" id="toExamId" href=<%=path+"/topic/findManyTopic.action"%> target="blank"><!-- ?stu_id=2&sub_id=1 --> --%>
									<a class="fill-color fill-pink" id="toExamId" target="blank"><!-- ?stu_id=2&sub_id=1 -->
										<div class="lg"></div>
									</a>
								</div>
								<div class="item-txt">
									<a href="">模拟考试</a>
								</div>
							</div>
							
						</div>
						<div class="ks-right-vip-link clear">
<%-- 							<a class="ks-right-vip-btn" id="toStuPersonalId" href=<%=path+"/topic/trueFindManyTopic.action"%> target="blank">进入模拟仿真考场</a> --%>
							<a class="ks-right-vip-btn" id="toTrueExamId" target="blank">进入模拟仿真考场</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
 <jsp:include page="tail.jsp"></jsp:include> 

</body>
</html>