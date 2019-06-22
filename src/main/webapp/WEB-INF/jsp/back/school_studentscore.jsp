<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>学员考试成绩</title>
		<%
	String path = request.getContextPath();
%>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<script type="text/javascript" src=<%=request.getContextPath()+ "/js/jquery-3.3.1.js" %>
			>
		</script>
		<script type="text/javascript" src=<%=path+ "/bootstrap-3.3.7-dist/js/bootstrap.min.js"%>
			>
		</script>
		<!-- FastClick -->
		<script type="text/javascript" src=<%=path+ "/bootstrap-datatable/js/jquery.dataTables.min.js"%>
			>
		</script>
		<script type="text/javascript" src=<%=path+ "/bootstrap-datatable/js/dataTables.bootstrap.min.js"%>
			>
		</script>
		<script type="text/javascript" src=<%=path+ "/adminlte/js/jquery.slimscroll.min.js"%>
			>
		</script>
		<!-- AdminLTE App -->
		<script type="text/javascript" src=<%=path+ "/adminlte/js/app.min.js"%>
			>
		</script>
		<!-- AdminLTE for demo purposes -->
		<script src=<%=path+ "/layui/layui.js"%>
			charset = "utf-8" >
		</script>
		<script type="text/javascript" src=<%=path+ "/adminlte/js/fastclick.js"%>
			>
		</script>
		<script type="text/javascript" src=<%=path+ "/adminlte/js/demo.js"%>
			>
		</script>

		<script type="text/javascript" src=<%=path+ "/adminlte/js/menucontrol.js"%>
			>
		</script>
		<script type="text/javascript" src=<%=path+ "/js/datatables_setting.js" %>
			>
		</script>
		<script type="text/javascript" src=<%=path+ "/js/Date.js" %>
			>
		</script>
		<script type="text/javascript" src=<%=path+ "/js/school_studentscore.js" %>
			>
		</script>

		<link type="text/css" href="<%=path %>/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
		<link type="text/css" href="<%=path %>/adminlte/css/font-awesome.min.css" rel="stylesheet">
		<link type="text/css" href="<%=path %>/adminlte/css/ionicons.min.css" rel="stylesheet">
		<link type="text/css" href="<%=path %>/bootstrap-datatable/css/dataTables.bootstrap.min.css" rel="stylesheet">
		<link type="text/css" href="<%=path %>/adminlte/css/adminlte.min.css" rel="stylesheet">
		<link type="text/css" href="<%=path %>/adminlte/css/all-skins.min.css" rel="stylesheet">
		<link rel="stylesheet" href="<%=path %>/layui/css/layui.css" media="all">
	</head>
	<style>

	</style>

	<body class="hold-transition skin-blue sidebar-mini">
		<div class="wrapper">
			<jsp:include page="header.jsp"></jsp:include>
			<jsp:include page="sliderbar.jsp"></jsp:include>
			<div class="content-wrapper">
				<section class="content">
					<div class="layui-tab layui-tab-brief">
						<div class="layui-tab-content" style="height: 100px;">
							<div class="layui-tab-item layui-show">
								<!-- body -->
								<div class="row">
									<div class="col-sm-12">
										<input type="hidden" id="schId" value="${schId}">
										<div class="box-body">
											<div id="thetestmsg_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
												<table id="score" class="table table-bordered table-hover">
													<thead>
														<tr role="row">
															<th>姓名</th>
															<th>教练</th>
															<th>科目一</th>
															<th>科目二</th>
															<th>操作</th>
															<th>科目三</th>
															<th>操作</th>
															<th>科目四</th>
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