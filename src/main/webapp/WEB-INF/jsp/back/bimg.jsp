<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理端主页</title>
<%
	String path = request.getContextPath();
%>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link type="text/css" href="<%=path %>/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/font-awesome.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/ionicons.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/adminlte.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/all-skins.min.css" rel="stylesheet">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="sliderbar.jsp"></jsp:include>
	<div class="content-wrapper">
	  	<section class="content">
	  	 	<div class="row">
	  	 		<div class="col-md-12">
<%--            		<img src=<%=path+"/images/hai.jpg"%> alt=""> --%>
<%-- 					<a href=<%=path+"/jsp/belongtocoachstudent.jsp" %>></a> --%>
						<a href="http://localhost:8080/dsmp/jsp/belongtocoachstudent.jsp" >sjj</a>
	  	 		</div>  <!-- /.box-body -->
	  	 	</div>
	  	</section>

	  </div><!-- 对应右侧主界面 -->
    <!-- 导入页脚 -->
    <jsp:include page="footer.jsp"></jsp:include>
</div><!-- 对应整个页面 -->

</body>
<script type="text/javascript" src=<%=request.getContextPath()+"/js/jquery-3.3.1.js" %>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-3.3.7-dist/js/bootstrap.min.js"%>></script>
<!-- FastClick -->
<script type="text/javascript" src=<%=path+"/adminlte/js/jquery.slimscroll.min.js"%>></script>
<!-- AdminLTE App -->
<script type="text/javascript" src=<%=path+"/adminlte/js/app.min.js"%>></script>
<!-- AdminLTE for demo purposes -->

<script type="text/javascript" src=<%=path+"/adminlte/js/fastclick.js"%>></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/demo.js"%>></script>
<script type="text/javascript" src=<%=path + "/layer/layer.js"%>></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/menucontrol.js"%> ></script>

</html>
