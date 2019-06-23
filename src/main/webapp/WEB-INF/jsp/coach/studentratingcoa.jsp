<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生评价教练表</title>
<%
	String path = request.getContextPath();
%>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<script type="text/javascript" src=<%=request.getContextPath()+"/js/jquery-3.3.1.js" %>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-3.3.7-dist/js/bootstrap.min.js"%>></script>
<!-- FastClick -->
<script type="text/javascript" src=<%=path+"/bootstrap-datatable/js/jquery.dataTables.min.js"%>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-datatable/js/dataTables.bootstrap.min.js"%>></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/jquery.slimscroll.min.js"%>></script>
<!-- AdminLTE App -->
<script type="text/javascript" src=<%=path+"/adminlte/js/app.min.js"%>></script>
<!-- AdminLTE for demo purposes -->
<script src=<%=path+"/layui/layui.js"%> charset="utf-8"></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/fastclick.js"%>></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/demo.js"%>></script>
<%-- <script type="text/javascript" src=<%=path +"/layer/layer.js"%>></script> --%>
<script type="text/javascript" src=<%=path+"/adminlte/js/menucontrol.js"%> ></script>
<script type="text/javascript" src=<%=path+"/js/datatables_setting.js" %>></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/jquery.flot.min.js"%> ></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/jquery.flot.resize.min.js"%> ></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/jquery.flot.pie.min.js"%> ></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/jquery.flot.categories.min.js"%> ></script>

<script type="text/javascript" src=<%=path+"/js/Date.js" %>></script>
<script type="text/javascript" src=<%=path+"/js/studentratingcoa.js" %>></script>
    <link type="text/css" href="<%=path %>/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/font-awesome.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/ionicons.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/bootstrap-datatable/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/adminlte.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/all-skins.min.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=path %>/layui/css/layui.css"  media="all">
</head>
<style>
	#tu{
		float:right;width:500px;
	}
	#ratingmsg{
		float:left;width:500px;padding-left:50px;
	}
</style>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
	<jsp:include page="/WEB-INF/jsp/back/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/back/sliderbar.jsp"></jsp:include>
	<div class="content-wrapper">
		<section class="content">
			<div class="row">
	  	 		<div class="col-md-12">
	  	 			<!-- 标题 -->
	  	 			<div class="layui-tab layui-tab-brief">
 				 		<ul class="layui-tab-title">
			   				<li class="chooserating layui-this"><h3>所有评价</h3></li>
		  					<li class="chooserating"><h3>好评</h3></li>
		  			 		<li class="chooserating"><h3>中评</h3></li>
 		  					<li class="chooserating"><h3>差评</h3></li>
  		 					   				 
 						</ul> 				 
					</div> 
					<!-- /标题 -->
					<div  class="container" id="ratingmsg">
						<div class="row clearfix" >
							<div class="col-md-12 column" >
								<dl id="rating">

								</dl>
							</div>
						</div>
					</div >
					<div id="tu">
					<!-- 圈图 -->
					<div class="box box-primary ">
           		    	<div class="box-header with-border">
             				<i class="fa fa-bar-chart-o"></i>
             				<h3 class="box-title">评价统计圈图</h3>
              				<div class="box-tools pull-right">
               					<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                				</button>
<!--                 				<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button> -->
             				</div>
            			</div>
            			<div class="box-body" >
              				<div id="donut-chart" style="height: 300px;"></div>
           				</div>
          			</div>
          			<!-- /圈图 -->
          			<!-- 柱图 -->
          			      
         			<div class="box box-primary" >
            			<div class="box-header with-border">
             				<i class="fa fa-bar-chart-o"></i>

              				<h3 class="box-title">评价统计柱状图</h3>

             				<div class="box-tools pull-right">
                				<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
               					</button>
<!--                 				<button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button> -->
             				</div>
           				</div>
            			<div class="box-body">
              				<div id="bar-chart" style="height: 300px;"></div>
           				</div>
         			</div>
         			<!-- /柱图 -->
         			</div>
	  	 		</div>		
	  	 	</div>	
		</section>
	</div>
    <jsp:include page="/WEB-INF/jsp/back/footer.jsp"></jsp:include>
</div>	
</body>
</html>