<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学员考试安排</title>
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

<script type="text/javascript" src=<%=path+"/adminlte/js/menucontrol.js"%> ></script>
<script type="text/javascript" src=<%=path+"/js/datatables_setting.js" %>></script>
<script type="text/javascript" src=<%=path+"/js/Date.js" %>></script>
<script type="text/javascript" src=<%=path+"/js/examarrangement.js" %>></script>

    <link type="text/css" href="<%=path %>/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/font-awesome.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/ionicons.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/bootstrap-datatable/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/adminlte.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/all-skins.min.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=path %>/layui/css/layui.css"  media="all">
</head>
<style>

</style>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<jsp:include page="header.jsp"></jsp:include>
		<jsp:include page="sliderbar.jsp"></jsp:include>
		<div class="content-wrapper">
			<section class="content">
				<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
 					<ul class="layui-tab-title">
			     	<li class="layui-this"><h3>可预约场次</h3></li>
  				 	<li class=""><h3>学员考试安排</h3></li>
  				 	<li class=""><h3>查看已预约学员</h3></li>  				 
 				 	</ul>
 				 	<div class="layui-tab-content" style="height: 100px;">
   						<div class="layui-tab-item layui-show">
							<!-- body -->
				  	 		<div class="box-body">
								<div id="thetestmsg_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
									<div class="row">
										<div class="col-sm-12">
											<table id="thetestmsg" class="table table-bordered table-hover">
												<thead>
													<tr role="row">
														<th >考试时间</th>
														<th >考试地点</th>
														<th >科目</th>
														<th >报名截止时间</th>
														<th >已报名人数</th>
														<th >总人数</th>			
													</tr>
												</thead>
												<tbody></tbody>
											</table>							
										</div>
									</div>
								</div>
							</div>
							<!-- /body -->  						
   						</div>
    					<div class="layui-tab-item">
							<!-- body -->
							<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
 								<ul class="layui-tab-title">
  								<li class="subject layui-this">科目一学员</li>
  			 					<li class="subject">科目二学员</li>
   								<li class="subject">科目三学员</li>
   				 				<li class="subject">科目四学员</li>   				 
 				 				</ul>
 				 
							</div> 
				  	 		<div class="box-body">
								<div id="arrangethetest_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
									<div class="row">
										<div class="col-sm-12">
											<table id="arrangethetest" class="table table-bordered table-hover">
												<thead>
													<tr role="row">
														<th >姓名</th>
														<th >科目</th>
														<th >当前学时</th>
														<th >总学时</th>
														<th>预约</th>		
													</tr>
												</thead>
												<tbody></tbody>
											</table>							
										</div>
									</div>
								</div>
							</div>
							<!-- /body -->    					   					
    					</div>
    					<div class="layui-tab-item">
    						<!-- body -->
 				  	 		<div class="box-body">
								<div id="haveappointment_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
									<div class="row">
										<div class="col-sm-12">
										<input type="button" id="test" value="测试"/>
											<table id="haveappointment" class="table table-bordered table-hover">
												<thead>
													<tr role="row">
														<th >姓名</th>
														<th >科目</th>
														<th >座位号</th>
														<th >考试地点</th>
														<th>考试时间</th>	
													</tr>
												</thead>
												<tbody></tbody>
											</table>							
										</div>
									</div>
								</div>
							</div>
							<!-- /body -->     						   					
    					</div>
 					</div>				 
				</div>			
			</section>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>