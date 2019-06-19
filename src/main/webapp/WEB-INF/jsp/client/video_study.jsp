<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>视频学习</title>
<%
	String path = application.getContextPath();
%>
<link rel="stylesheet" type="text/css" href=<%=path + "/css/jx_video_detail.css"%>>
<link rel="stylesheet" type="text/css" href=<%=path + "/css/video-js.css"%>>
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
<style>
ul li{
list-style:none;}
video{cursor: pointer}
</style>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<input type="hidden" id="path" value=<%=path%>>
<div class="video-detail-page">
	<p>当前选择：<a href="javascript:void(0);">学车视频</a>>>
	<label >${subName}>></label>
  <label for="" id="vidTitle">${vidTitle}</label>
	</p>
	<div class="video-play-detail">
		<div class="detail-video">
		<video id="my-video" class="video-js" controls  width="800" height="500" poster=<%=path%>${vidImgpath }>
		<source id="my-sourse"  src=<%=path%>${vidPath} type="video/mp4" >
		</video>
		</div>
		<div class="detail-content">
			<div class="detail-header">${subName}其它视频</div>
			<div class="detail-header-border"></div>
  		<ul>
  			<c:forEach items="${videoList }" begin="0" step="1" var="i">
  				<li>
<!--   					<div class="circle"></div> -->
  					<input type="hidden"  value="${i.vidImgpath}">
           		    <input type="hidden"  value="${i.vidPath}">
  					<a class="changeVideo" href="javascript:void(0);">${i.vidTitle}</a>
  				</li>
  			</c:forEach>
  		</ul>
    </div>
	</div>
</div>
<jsp:include page="tail.jsp"></jsp:include>	
<script type="text/javascript" src=<%=request.getContextPath()+"/js/jquery-3.3.1.js" %>></script>
<script src=<%=path+"/js/video_study.js" %>></script>
</body>
</html>
