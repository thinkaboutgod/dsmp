<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	String path = request.getContextPath();
%>
<meta charset="UTF-8">
<title>学员预约考试</title>
<script type="text/javascript" src=<%=request.getContextPath()+"/js/jquery-3.3.1.js" %>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-datatable/js/jquery.dataTables.min.js"%>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-datatable/js/dataTables.bootstrap.min.js"%>></script>
<script src=<%=path+"/layui/layui.js"%> charset="utf-8"></script>
<script type="text/javascript" src=<%=path+"/js/Date.js" %>></script>
<script type="text/javascript" src=<%=path+"/js/datatables_setting.js" %>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-3.3.7-dist/js/bootstrap.min.js"%>></script>
<script type="text/javascript" src=<%=path+"/js/testappointment.js" %>></script>
<link type="text/css" href="<%=path %>/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path %>/layui/css/layui.css"  media="all">
<link type="text/css" href="<%=path %>/bootstrap-datatable/css/dataTables.bootstrap.min.css" rel="stylesheet">
</head>
	<style>
		#main{
		    width:700px;
		    height:500px;
 		    background-color:#ecf0f5; 
		    display: block;
		    margin:0 auto;
		}
	</style>
<body>
	<div id="main">
		<!-- body -->		<div class="row">
								<div class="col-sm-12">
				  	 				<div class="box-body">
										<div id="testappointment_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">

											<table id="testappointment" class="table table-bordered table-hover">
												<thead>
													<tr role="row">
														<th >考试时间</th>
														<th >考试地点</th>
														<th >科目</th>
														<th >报名截止时间</th>
														<th >已报名人数</th>
														<th >总人数</th>
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
</body>
</html>