<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学员个人中心</title>
<%
	String path = request.getContextPath();
%>
    <link type="text/css" href="<%=path %>/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/font-awesome.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/ionicons.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/bootstrap-datatable/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/adminlte.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/all-skins.min.css" rel="stylesheet">
<!--     星星评价相关 -->
    <link rel="stylesheet" href=<%=path+"/f-star-rating/css/font-awesome.min.css"%>>
    <link href=<%=path+"/f-star-rating/css/star-rating.css"%> media="all" rel="stylesheet" type="text/css"/>
    
</head>
<body class="hold-transition skin-blue sidebar-mini">
<%-- 	<a href=<%=path+"/personal/coachRating.action" %>>教练评价</a><br> --%>
<%-- 	<a href=<%=path+"/personal/schoolRating.action" %>>驾校评价</a><br> --%>
<%-- 	<a href=<%=path+"/personal/examSchedule.action" %>>考试安排</a><br> --%>
	<c:if test="${visitRes=='needLogin' }">进入个人中心需要登录</c:if>
	<c:if test="${visitRes=='needRegister' }">进入个人中心需是已报名</c:if>
	<c:if test="${visitRes=='success' }">
		<div class="wrapper">
	<%-- 	<jsp:include page="header.jsp"></jsp:include> --%>
	<%-- 	<jsp:include page="sliderbar.jsp"></jsp:include> --%>
		<div class="content-wrapper">
		  	<section class="content">
		  	 	<div class="row">
		  	 		<div class="col-md-12">
	           			 <div class="box-header">
			              	<h3 class="box-title"><strong>学员个人中心</strong></h3>
			           	 </div>
			           	 <div class="box-body">
	
			           	 	<div class="tabbable" id="tabs-643545">
								<ul class="nav nav-tabs">
									<li  class="active"><a href="#panel-139674" data-toggle="tab">教练评价</a></li>
									<li><a href="#panel-185679" data-toggle="tab">驾校评价</a></li>
									
									<li><a href="#panel-185680" data-toggle="tab">考试安排</a></li>
									<li><a href="#panel-185681" data-toggle="tab">科目成绩</a></li>
									<li><a href="#panel-185682" data-toggle="tab">科目二学时查看</a></li>
									<li><a href="#panel-185683" data-toggle="tab">科目三学时查看</a></li>
								
								</ul><br>
								<div class="tab-content">
									<div class="tab-pane active" id="panel-139674">
	<%-- 									<jsp:include page="coachRating.jsp"></jsp:include> --%>
												<div class="container">
												    <div class="page-header">
												        <h2>教练评价
												            <small>welcome</small>
												        </h2>
												    </div>
												
												    <form action="" method="post"> <!--action="addCoachRating.action"-->
												    	<input id="coa_id" type="hidden" name="coa_id" value="${coa_id }">
												    	<input id="stu_id" type="hidden" name="stu_id" value="${stu_id }">
												        <input id="input-21e" name="starNum" value="0" type="text" class="rating" data-min=0 data-max=5 data-step=1 data-size="xs"
												               title="">
												        <textarea id="ratingContent" name="ratingContent" rows="" cols=""></textarea>
												      
												        <div class="form-group" style="margin-top:10px">
												            <button id="coachSubBtn" type="button" class="btn btn-primary">Submit</button>
	<!-- 											            <button type="submit" class="btn btn-primary">Submit</button> -->
												            <button id="reset" type="reset" class="btn btn-default">Reset</button>
												<!--             <button type="button" class="btn btn-danger">Destroy</button> -->
												<!--             <button type="button" class="btn btn-success">Create</button> -->
												        </div>
												    </form>
												    <hr>
												
												</div>
									</div>
									
									<div class="tab-pane " id="panel-185679">
	<%-- 									<jsp:include page="schoolRating.jsp"></jsp:include> --%>
																				<div class="container">
										    <div class="page-header">
										        <h2>驾校评价
										            <small>welcome</small>
										        </h2>
										    </div>
										
										    <form action="" method="post"><!-- addSchoolRating.action -->
										    	<input id="sch_id" type="hidden" name="sch_id" value="${sch_id }">
										    	<input id="stu_id" type="hidden" name="stu_id" value="${stu_id }">
										        <input id="input-21e" name="shcoolStarNum" value="0" type="text" class="rating" data-min=0 data-max=5 data-step=1 data-size="xs"
										               title="">
										        <textarea id="shcoolRatingContent" name="ratingContent" rows="" cols=""></textarea>
										      
										        <div class="form-group" style="margin-top:10px">
										            <button id="schoolSubBtn" type="button" class="btn btn-primary">Submit</button>
										            <button id="shcoolReset" type="reset" class="btn btn-default">Reset</button>
										<!--             <button type="button" class="btn btn-danger">Destroy</button> -->
										<!--             <button type="button" class="btn btn-success">Create</button> -->
										        </div>
										    </form>
										    <hr>
										
										</div>
									</div>
									<div class="tab-pane " id="panel-185680">
										<c:if test="${examVisit=='noExam' }">无考试安排</c:if>
										<c:if test="${examVisit=='seeExam' }">
											<jsp:include page="examschedule.jsp"></jsp:include>
										</c:if>
									</div>
									<div class="tab-pane " id="panel-185681">
											<jsp:include page="subjectScore.jsp"></jsp:include>
										
									</div>
									<div class="tab-pane " id="panel-185682">
											<jsp:include page="studyRecord2.jsp"></jsp:include>
										
									</div>
									<div class="tab-pane " id="panel-185683">
											<jsp:include page="studyRecord3.jsp"></jsp:include>
										
									</div>
								</div>
							</div>
	
						</div>
		  	 		</div>
	            <!-- /.box-body -->
	          </div>
		  	</section>
		  </div>
	    <!-- 对应右侧主界面 -->
	    <!-- 导入页脚 -->
	<%--     <jsp:include page="footer.jsp"></jsp:include> --%>
	</div>
</c:if>
<!-- 对应整个页面 -->
<!-- 模态框 -->

</body>
<script type="text/javascript" src=<%=path+"/js/jquery-3.3.1.js" %>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-3.3.7-dist/js/bootstrap.min.js"%>></script>

<script type="text/javascript" src=<%=path+"/bootstrap-datatable/js/jquery.dataTables.min.js"%>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-datatable/js/dataTables.bootstrap.min.js"%>></script>
<!-- FastClick -->
<script type="text/javascript" src=<%=path+"/adminlte/js/jquery.slimscroll.min.js"%>></script>
<!-- AdminLTE App -->
<script type="text/javascript" src=<%=path+"/adminlte/js/app.min.js"%>></script>
<!-- AdminLTE for demo purposes -->

<script type="text/javascript" src=<%=path+"/adminlte/js/fastclick.js"%>></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/demo.js"%>></script>
<script type="text/javascript" src=<%=path + "/layer/layer.js"%>></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/menucontrol.js"%> ></script>
<script type="text/javascript" src=<%=path+"/js/datatables_setting.js" %>></script>

<script type="text/javascript" src=<%=path+"/js/stu_personal.js" %>></script>
<!-- 日期格式 -->
<script type="text/javascript" src=<%=path+"/js/Date.js" %>></script>
<!-- 星星评价相关 -->
<script src=<%=path+"/f-star-rating/js/star-rating.js"%> type="text/javascript"></script>
</html>