<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = application.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>教练</title>
<link rel='stylesheet' href=<%=path+"/layui/css/layui.css" %> media="all">	
<link rel="stylesheet" href=<%=path + "/css/home/bootstrap.css"%>>
<link rel="stylesheet" href=<%=path + "/css/allcoach/footer.css"%>>
<link rel="stylesheet" href=<%=path + "/css/allcoach/jllist_all.css"%>>
<link rel="stylesheet" href=<%=path + "/css/home/comm_style.css"%>>
<link href=<%=path + "/css/home/css.css"%> rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href=<%=path + "/css/home/animate.css"%>
	type="text/css">
<link href=<%=path + "/css/home/green.css"%> title="" rel="stylesheet"
	type="text/css" />
	
<script src=<%=path+"/layui/layui.js" %> ></script>
<script src=<%=path+"/js/coach/coachlayui.js" %> ></script>
<script type="text/javascript"
	src=<%=path + "/js/home/jquery1.42.min.js"%>></script>
<script type="text/javascript"
	src=<%=path + "/js/home/jquery.superslide.2.1.1.js"%>></script>
	<script type="text/javascript" src=<%=path + "/js/home/aos.js"%>></script>
</head>
<body>
<input type="hidden" id="path" value=<%=path %>>
	<jsp:include page="head.jsp"></jsp:include>
		<div class="jll-wrap">
			<div class="top">
				位置：
				<a rulname="全部" urlpath="/" href="">全部教练</a>
			</div>
			<div class="content">
				<div class="left">
					<div class="line"></div>
					<div class="list-warp">
						<!-- 教练列表 -->
						<div class="list-warp" id="coachList">							
						</div>
						<!-- 分页按钮 -->
						<div id="paging">					
						</div>
					</div>
					<input type="hidden" id="cenrruy" value="">
				</div>
				<div class="right">					
					<div class="rec-coach">						
						<div class="rec-title">
							推荐教练
						</div>
						<!--全国-->
						<div class="rec-title-line"></div>
						<div class="rec-each" data-track="JXEDT_JLLB_GG_1">
							<div class="rec-img-seat">
								<img src=<%=path+"/images/school/jp_xwyd.png" %>
								 alt="">
							</div>
						</div>
						<div class="rec-each" data-track="JXEDT_JLLB_GG_2">
							<div class="rec-img-seat">
								<img src=<%=path+"/images/school/jp_xwyd.png" %>
								 alt="">
							</div>
						</div>
						<div class="rec-each" data-track="JXEDT_JLLB_GG_3">
							<div class="rec-img-seat">
								<img src=<%=path+"/images/school/jp_xwyd.png" %>
								 alt="">
							</div>
						</div>
						<div class="rec-each" data-track="JXEDT_JLLB_GG_4">
							<div class="rec-img-seat">
								<img src=<%=path+"/images/school/jp_xwyd.png" %>
								 alt="">
							</div>
						</div>
						<div class="rec-each" data-track="JXEDT_JLLB_GG_5">
							<div class="rec-img-seat">
								<img src=<%=path+"/images/school/jp_xwyd.png" %>
								 alt="">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
			
	
	<jsp:include page="tail.jsp"></jsp:include>
</body>
</html>