<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>视频学习</title>
<%
	String path = request.getContextPath();
%>
<style>
	.Product{
	float: left;text-align: center;width: 33%;height: 230px;
	}
	#pageoption a{cursor: pointer}
</style>

<link type="text/css" href="<%=path %>/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href=<%=path + "/css/video/jx_video.css"%>>
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
  <input type="hidden" id="path" value=<%=path%>>	
  	<input type="hidden" id="KeMuNum" value="${KeMuNum }">
	<div class="video-page">
			<div class="video-main">
				<p>位置：<a href="javascript:void(0);">学车视频</a></p>
				<!-- 学车视频左侧 -->
				<div class="video-left">
					
					<div class="video-left-bottom" id="jinxuan">
						<ul class="car-video">
							<li>
								<a class="" href="javascript:void(0);" >精选推荐</a>
							</li>
							<li>
								<a class="subject" id="2" href="javascript:void(0);" title="2">科目二</a>
							</li>
							<li>
								<a class="subject" id="3" href="javascript:void(0);" title="3">科目三</a>
							</li>							
						</ul>
						 <div id="videoDiv" style="width: 1000px;float: left;">
 					     </div>
 						 <div style="text-align:center;clear:both;">
		                 <ul id="pageoption"></ul>
	                     </div>
					</div>
				</div>				
			</div>
		</div>	
<jsp:include page="tail.jsp"></jsp:include>			
</body>
<script type="text/javascript" src=<%=request.getContextPath()+"/js/jquery-3.3.1.js" %>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-3.3.7-dist/js/bootstrap.min.js"%>></script>

<script type="text/javascript" src=<%=path + "/layer/layer.js"%>></script>
<script type="text/javascript" src=<%=path+"/js/video_util.js"%> ></script>
<script type="text/javascript" src=<%=path+"/js/videolist.js"%> ></script>
<script type="text/javascript" src=<%=path + "/js/bootstrap-paginator.min.js"%>></script>
</html>