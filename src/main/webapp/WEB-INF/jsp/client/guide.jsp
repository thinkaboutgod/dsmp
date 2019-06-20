<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = application.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学车指南</title>
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
<link href=<%=path + "/css/guide/jxwz.css"%> title="" rel="stylesheet"
	type="text/css" />
	
<script type="text/javascript"
	src=<%=path + "/js/home/jquery1.42.min.js"%>></script>
<script type="text/javascript"
	src=<%=path + "/js/home/jquery.superslide.2.1.1.js"%>></script>
<script type="text/javascript" src=<%=path + "/js/home/aos.js"%>></script>
</head>
<body>
<input type="hidden" id="path" value=<%=path %>>
<jsp:include page="head.jsp"></jsp:include>
	<div class="car-article-wrap" style="margin-top:50px;">
			<div class="car-article">
			<c:if test="${notice != null }">
				<p>位置：<a href="javascript:;"><span>${notice.tbNoticeType.ntyName }</span></a>
					&gt;<span>${notice.notTitle }</span>
				</p>
				<!--文章-->
				<div class="car-article-left">
					<div class="article-header">
						<div class="article-title">${notice.notTitle }</div>
						<div class="article-author">
							<div class="source">来源：JX1811班项目组</div>
						</div>
						<div class="article-num">
							<div class="comment">时间：<fmt:formatDate value="${notice.notTime}" pattern="YYYY-MM-dd" /></div>
						</div>
					</div>
					<div class="article-border"></div>
					<div class="article-detail">
						${notice.notContent}
					</div>
					<div class="article-border"></div>
				</div>
			</c:if>				
			</div>
	</div>
<jsp:include page="tail.jsp"></jsp:include>
</body>
</html>