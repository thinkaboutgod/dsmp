<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>驾校考试安排</title>
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
		<script type="text/javascript" src=<%=path+ "/js/school_examarrangement.js" %>
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
						<ul class="layui-tab-title">
							<li class="layui-this">
								<h3>考试安排记录</h3></li>
							<li class="">
								<h3>新增考试</h3></li>
							<li class="">
								<h3>查看已预约学员</h3></li>
						</ul>
						<div class="layui-tab-content" style="height: 100px;">
							<div class="layui-tab-item layui-show">
								<!-- body -->
								<div class="row">
									<div class="col-sm-12">
										<input type="hidden" id="schId" value="${schId}">
										<div class="box-body">
											<div id="thetestmsg_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">

												<table id="examSchedule" class="table table-bordered table-hover">
													<thead>
														<tr role="row">
															<th>考试时间</th>
															<th>考试地点</th>
															<th>科目</th>
															<th>报名截止时间</th>
															<th>已报名人数</th>
															<th>总人数</th>
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
								<div class="box-body" style="padding-left:30%;">
									<article class="login-guide-main">
										<div class="content">
											<div id="layerregspan" class="layui-layer-content">
												<div class="reg layui-layer-wrap" id="reg" lay-filter="reg">
													<div class="layui-form layui-form-pane" id="form_reg">
														<div class="layui-form-item" style="margin-top: 20px;">
															<label class="layui-form-label">考试科目</label>
															<div class="layui-input-block">
																<div class="layui-input-inline">
																	<select name="sub_id" id="sub_id" lay-search="" lay-verify="required" lay-filter="sub_id" style="width: 200px;height: 40px;">
																		<option value="0">请选择考试科目</option>
																		<option value="1">科目一</option>
																		<option value="2">科目二</option>
																		<option value="3">科目三</option>
																		<option value="4">科目四</option>
																	</select>
																</div>
															</div>
														</div>
														<div class="layui-form-item" style="margin-top: 20px;">
															<label class="layui-form-label">考试地点</label>
															<div class="layui-input-block">
																<input type="text" name="exs_address" lay-verify="required" id="exs_address" lay-vertype="tips" autocomplete="off" placeholder="请输入考点" style="width: 200px;height: 40px;">
															</div>
														</div>
														<div class="layui-form-item ">
															<label class="layui-form-label">人数上限</label>
															<div class="layui-input-block">
																<input type="number" name="totalNum" id="totalNum" lay-verify="number|required" placeholder="请输入人数上限" style="width: 200px;height: 40px;">
															</div>	
														</div>

														<div class="layui-form-item">
															<label class="layui-form-label">考试日期</label>
															<div class="layui-input-inline">
																<input type="date" id="exs_date" lay-verify="required" lay-filter="exs_date" style="width: 200px;height: 40px;">
															</div>
														</div>
														<div class="layui-form-item">
															<label class="layui-form-label">考试时间段</label>
															<div class="layui-input-inline">
																<select name="exs_time" id="exs_time" lay-verify="required" lay-filter="exs_time" style="width: 200px;height: 40px;">
																	<option value="0">请选择时间段</option>
																	<option value="8:30~9:30">8:30~9:30 </option>
																	<option value="9:30~10:30">9:30~10:30</option>
																	<option value="10:30~11:30">10:30~11:30</option>
																	<option value="14:00~15:00">14:00~15:00</option>
																	<option value="15:00~16:00">15:00~16:00</option>
																	<option value="16:00~17:00">16:00~17:00</option>
																</select>
															</div>
														</div>

														<div class="layui-form-item">
															<div class="layui-input-block">
																<button class="layui-btn layui-btn-normal" lay-submit="" id="btn_add" lay-filter="btn_add">新增</button> &nbsp;&nbsp;&nbsp;&nbsp;
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</article>

								</div>
							</div>
						</div>
						<div class="layui-tab-item">
							<!-- body -->
							<div class="row">
								<div class="col-sm-12">
									<div class="box-body">
										<div id="haveappointment_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">

											<table id="haveappointment" class="table table-bordered table-hover">
												<thead>
													<tr role="row">
														<th>姓名</th>
														<th>科目</th>
														<th>座位号</th>
														<th>考试地点</th>
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