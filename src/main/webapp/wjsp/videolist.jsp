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

</head>
<body>
  <input type="hidden" id="path" value=<%=path%>>
	<ul class="car-video">
        <li>
            <a class="subject" href="javascript:void(0);"title="1">科目一</a>
        </li>
        <li>
            <a class="subject" href="javascript:void(0);"title="2">科目二</a>
        </li>
        <li>
            <a class="subject" href="javascript:void(0);"title="3">科目三</a>
        </li>
        <li>
            <a class="subject" href="javascript:void(0);"title="4">科目四</a>
        </li>
    </ul>
    <div id="videoDiv" style="width: 1000px;float: left;">
 	</div>
 	<div style="text-align:center;clear:both;">
		 <ul id="pageoption"></ul>
	</div>
</body>
<script type="text/javascript" src=<%=request.getContextPath()+"/js/jquery-3.3.1.js" %>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-3.3.7-dist/js/bootstrap.min.js"%>></script>

<script type="text/javascript" src=<%=path + "/layer/layer.js"%>></script>
<script type="text/javascript" src=<%=path+"/js/video_util.js"%> ></script>
<script type="text/javascript" src=<%=path+"/js/videolist.js"%> ></script>
<script type="text/javascript" src=<%=path + "/js/bootstrap-paginator.min.js"%>></script>
</html>