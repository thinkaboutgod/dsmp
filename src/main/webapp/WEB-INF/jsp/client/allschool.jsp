<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = application.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>驾校</title>
<link rel='stylesheet' href=<%=path+"/layui/css/layui.css" %> media="all">	
<link rel="stylesheet" href=<%=path + "/css/home/bootstrap.css"%>>
<link rel="stylesheet" href=<%=path + "/css/allschool/footer.css"%>>
<link rel="stylesheet" href=<%=path + "/css/allschool/jx_list.css"%>>
<link rel="stylesheet" href=<%=path + "/css/home/comm_style.css"%>>
<link href=<%=path + "/css/home/css.css"%> rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href=<%=path + "/css/home/animate.css"%>
	type="text/css">
<link href=<%=path + "/css/home/green.css"%> title="" rel="stylesheet"
	type="text/css" />
	
<script src=<%=path+"/layui/layui.js" %> ></script>
<script src=<%=path+"/js/school/schoollayui.js" %> ></script>
<script type="text/javascript"
	src=<%=path + "/js/home/jquery1.42.min.js"%>></script>
<script type="text/javascript"
	src=<%=path + "/js/home/jquery.superslide.2.1.1.js"%>></script>
<script type="text/javascript" src=<%=path + "/js/home/aos.js"%>></script>
</head>
<body>
<input type="hidden" id="path" value=<%=path %>>
<jsp:include page="head.jsp"></jsp:include>
	<div class="jx-list-wrap">
		<article class="jx-list">
			<div class="jx-list-position">
				<span>位置： <a rulname="全部" urlpath="/" href="">全部驾校</a>
				</span>
			</div>
			<div class="left-nav">
				<div class="jx-list-header">
					<!--驾校列表 start -->
					<div class="row jx-list-page" id="schoolList">
					</div>
					<!-- 分页按钮 -->
					<div id="paging">					
					</div>
				</div>
			</div>
			<div class="right-nav">
				<!--驾校广告位-->
				<div class="jx-list-recommend">
					<p class="jx-recommend">驾校推荐</p>
					<div class="jx-recommend-imgs">
						<a href="javascript:void(0)"> <img
							src=<%=path+"/images/coach/jxc_xwyd.png" %>
							alt="" srcset="">
						</a>
					</div>
					<div class="jx-recommend-imgs">
						<a href="javascript:void(0)"> <img
							src=<%=path+"/images/coach/jxc_xwyd.png" %>
							alt="" srcset="">
						</a>
					</div>
					<div class="jx-recommend-imgs">
						<a href="javascript:void(0)"> <img
							src=<%=path+"/images/coach/jxc_xwyd.png" %>
							alt="" srcset="">
						</a>
					</div>
				</div>
			</div>
		</article>
	</div>
<jsp:include page="tail.jsp"></jsp:include>
</body>
</html>