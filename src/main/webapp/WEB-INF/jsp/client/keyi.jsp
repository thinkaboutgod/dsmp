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
<script type="text/javascript"
	src=<%=path + "/js/home/jquery1.42.min.js"%>></script>
<script type="text/javascript"
	src=<%=path + "/js/home/jquery.superslide.2.1.1.js"%>></script>
	<script type="text/javascript" src=<%=path + "/js/home/aos.js"%>></script>

</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
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
									<a class="fill-color fill-yellow" href="">
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
									<a class="fill-color fill-green" href="">
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
									<a class="fill-color fill-pink" href="">
										<div class="lg"></div>
									</a>
								</div>
								<div class="item-txt">
									<a href="">模拟考试</a>
								</div>
							</div>
							
						</div>
						<div class="ks-right-vip-link clear">
							<a class="ks-right-vip-btn" href="" target="blank">进入模拟仿真考场</a>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
<jsp:include page="tail.jsp"></jsp:include>
</body>
</html>