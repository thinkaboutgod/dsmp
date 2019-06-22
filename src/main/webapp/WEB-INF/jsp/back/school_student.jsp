<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>学员管理</title>
		<%
	String path = request.getContextPath();
%>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<link type="text/css" href="<%=path %>/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
		<link type="text/css" href="<%=path %>/adminlte/css/font-awesome.min.css" rel="stylesheet">
		<link type="text/css" href="<%=path %>/adminlte/css/ionicons.min.css" rel="stylesheet">
		<link type="text/css" href="<%=path %>/bootstrap-datatable/css/dataTables.bootstrap.min.css" rel="stylesheet">
		<link type="text/css" href="<%=path %>/adminlte/css/adminlte.min.css" rel="stylesheet">
		<link type="text/css" href="<%=path %>/adminlte/css/all-skins.min.css" rel="stylesheet">
		<link href=<%=path+ "/css/apply/findpass.css" %> type="text/css" rel="stylesheet">
		<link rel="stylesheet" href=<%=path+ "/css/login/reset.css" %>>
		<link rel="stylesheet" href=<%=path+ "/css/apply/registration.css" %>>
		<link rel='stylesheet' href=<%=path+ "/layui/css/layui.css" %> media="all">
	</head>

	<body class="hold-transition skin-red sidebar-mini">
		<div class="wrapper">
			<jsp:include page="header.jsp"></jsp:include>
			<jsp:include page="sliderbar.jsp"></jsp:include>
			<div class="content-wrapper">
				<section class="content">
					<div class="row">
						<div class="col-md-12">
							<div class="box-header">
								<h3 class="box-title"><strong>学员管理</strong></h3>
							</div>
							<div class="box-body">
								<div class="tabbable" id="tabs-643545">
									<div class="tab-content">
										<div class="tab-pane active" id="panel-139674">
											<div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
												<div class="row">
													<div class="col-sm-12">
														<input type="hidden" id="schId" value="${schId}">
														<div class="input-group">
															<input type="text" class=" form-control" id="sname" placeholder="用户姓名">
														</div>
														<div class="input-group">
															<input type="text" class="form-control" placeholder="账号" id="saccount">
														</div>
														<div class="input-group">
															<span>报名时间：</span>
														</div>
														<div class="input-group">
															<input type="date" class=" form-control" name="dno" id="begintime" placeholder="起始时间">
														</div>
														<div class="input-group">
															<span>至</span>
														</div>
														<div class="input-group">
															<input type="date" class=" form-control" name="dno" id="endtime" placeholder="终止时间">
														</div>
														<div class="input-group">
															<button class="button btn-primary btn-sm " id="buttonsearch">搜索</button>
														</div>
														<div class="input-group">
															<button class="button btn-primary btn-sm" id="btn_addStudent">新增学员</button>
														</div>
														<table id="studentTable" class="table table-bordered table-hover">
															<thead>
																<tr role="row">
																	<th>账号</th>
																	<th>姓名</th>
																	<th>性别</th>
																	<th>报名时间</th>
																	<th>所属教练</th>
																	<th>当前科目</th>
																	<th>当前状态</th>
																	<th>审核状态</th>
																	<th>操作</th>
																</tr>
															</thead>
															<tbody></tbody>

														</table>
													</div>
												</div>
											</div>
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
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
		<!-- 对应整个页面 -->

		<!-- 模态框 -->
		<div class="modal fade" id="studentDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="myModalLabel">学员信息</h4>
					</div>
					<div class="modal-body">
						<div class="form-horizontal">
							<div class="form-group">
								<label for="ed_name" class="col-sm-2 control-label">姓名：</label>
								<div class="col-sm-10">
									<input class="form-control" id="name" type="text" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="ed_phone" class="col-sm-2 control-label">电话：</label>
								<div class="col-sm-10">
									<input class="form-control" id="phone" type="text" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="ed_phone" class="col-sm-2 control-label">地址：</label>
								<div class="col-sm-10">
									<input class="form-control" id="address" type="text" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="ed_phone" class="col-sm-2 control-label">教练姓名：</label>
								<div class="col-sm-10">
									<input class="form-control" id="coachName" type="text" readonly="readonly">
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="center-block">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- 对应详情模态框 -->

		<!-- 模态框 -->
		<div class="modal fade" id="studentCheck" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="myModalLabel">学员审核</h4>
					</div>
					<div class="modal-body">
						<div class="form-horizontal">
							<input type="hidden" id="stuId" />
							<div class="form-group">
								<label for="nameCheck" class="col-sm-2 control-label">姓名：</label>
								<div class="col-sm-10">
									<input class="form-control" id="nameCheck" type="text" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="phoneCheck" class="col-sm-2 control-label">电话：</label>
								<div class="col-sm-10">
									<input class="form-control" id="phoneCheck" type="text" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="addressCheck" class="col-sm-2 control-label">地址：</label>
								<div class="col-sm-10">
									<input class="form-control" id="addressCheck" type="text" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="coachNameCheck" class="col-sm-2 control-label">教练姓名：</label>
								<div class="col-sm-10">
									<input class="form-control" id="coachNameCheck" type="text" readonly="readonly">
								</div>
							</div>
							<div class="form-group" >
								<label class="col-sm-2 control-label">选择教练</label>
								<div class="col-sm-10">
									<select id="coachs" class="form-control"></select>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="center-block">
							<button type="button" id="btn_check_yes" class="btn btn-success">审核通过</button>
							<button type="button" id="btn_check_no" class="btn btn-danger">审核失败</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- 对应审核模态框 -->

		<div class="modal fade" id="addStudent" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 1000px;">
				<div class="modal-content">
					<div class="registration">
						<article class="login-guide-main">
							<div class="content">
								<div class="findtype">
									<strong>学员在线报名</strong>
								</div>
								<!-- 学员注册框 -->
								<div class="d1" style="width:500px">
									<div id="layerregspan" class="layui-layer-content" style="height: 600px;">
										<div class="reg layui-layer-wrap" style="width: 500px;" id="reg" lay-filter="reg">
											<div class="layui-form layui-form-pane" id="form_reg">
												<div class="layui-upload">
													<label class="layui-form-label">身份证图片:</label>
													<div class="layui-upload layui-input-block">
														<button class="layui-btn layui-btn-danger" id="idCard" type="button">
											<i class="layui-icon"></i>上传身份证
										</button>
													</div>
												</div>
												<div class="layui-form-item" style="margin-top: 20px;">
													<label class="layui-form-label">真实姓名</label>
													<div class="layui-input-block">
														<input type="text" name="real_name" lay-verify="required" id="real_name" lay-vertype="tips" autocomplete="off" placeholder="识别身份证图片获取" class="layui-input" disabled="disabled">
													</div>
												</div>
												<div class="layui-form-item" style="margin-top: 20px;">
													<label class="layui-form-label">身份证号码</label>
													<div class="layui-input-block">
														<input type="text" name="idnum" lay-verify="idnum" id="idnum" lay-vertype="tips" autocomplete="off" placeholder="识别身份证图片获取" class="layui-input" disabled="disabled">
													</div>
												</div>
												<div class="layui-form-item" style="margin-top: 20px;">
													<label class="layui-form-label">家庭住址</label>
													<div class="layui-input-block">
														<input type="text" name="student_address" lay-verify="student_address" id="student_address" lay-vertype="tips" autocomplete="off" placeholder="识别身份证图片获取" class="layui-input" disabled="disabled">
													</div>
												</div>
												<div class="layui-form-item ">
													<label class="layui-form-label">性别</label>
													<div class="layui-input-block">
														<input type="text" name="sex" id="sex" lay-vertype="tips" autocomplete="off" placeholder="识别身份证图片获取" class="layui-input" disabled="disabled" style="width: 190px;">
													</div>
												</div>

												<div class="layui-form-item" style="" id="teacher_div">
													<label class="layui-form-label">选择教练</label>
													<div class="layui-input-inline">
														<select name="teachers" id="teachers" lay-search="" lay-verify="required" s lay-filter="teachers">
															<option></option>
														</select>
													</div>
												</div>

												<div class="layui-form-item" style="margin-top: 20px;">
													<label class="layui-form-label">手机号码</label>
													<div class="layui-input-block">
														<input type="text" name="student_phone" lay-verify="phone" id="student_phone" lay-vertype="tips" autocomplete="off" placeholder="请输入手机号码" class="layui-input" >
													</div>
												</div>
												<div class="layui-form-item">
													<div class="layui-input-block">
														<button class="layui-btn layui-btn-normal" lay-submit="" id="btn_add" lay-filter="btn_add">新增</button> &nbsp;&nbsp;&nbsp;&nbsp;
														<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
													</div>
												</div>
											</div>
										</div>
									</div>
									<span class="layui-layer-setwin"><button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>/span>
								</div>
								<div class="d2">
									<h1>拍照上传头像</h1>
									<button type="button" class="layui-btn layui-btn-warm" id="open">开启摄像头</button>
									<button type="button" class="layui-btn layui-btn-warm" id="photograph">点击拍照</button>
									<video id="video" style="width:370px;height: 220px;"></video>
									<canvas id="canvas" width="350" height="250"></canvas>
								</div>
							</div>
						</article>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript" src=<%=path+ "/js/jquery-3.3.1.js" %>
		>
	</script>
	<script type="text/javascript" src=<%=path+ "/bootstrap-3.3.7-dist/js/bootstrap.min.js"%>
		>
	</script>

	<script type="text/javascript" src=<%=path+ "/bootstrap-datatable/js/jquery.dataTables.min.js"%>
		>
	</script>
	<script type="text/javascript" src=<%=path+ "/bootstrap-datatable/js/dataTables.bootstrap.min.js"%>
		>
	</script>
	<!-- FastClick -->
	<script type="text/javascript" src=<%=path+ "/adminlte/js/jquery.slimscroll.min.js"%>
		>
	</script>
	<!-- AdminLTE App -->
	<script type="text/javascript" src=<%=path+ "/adminlte/js/app.min.js"%>
		>
	</script>
	<!-- AdminLTE for demo purposes -->

	<script type="text/javascript" src=<%=path+ "/adminlte/js/fastclick.js"%>
		>
	</script>
	<script type="text/javascript" src=<%=path+ "/adminlte/js/demo.js"%>
		>
	</script>
	<script type="text/javascript" src=<%=path + "/layer/layer.js"%>
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
	<script src=<%=path+ "/layui/layui.js" %>
		charset = "utf-8" >
	</script>
	<script src=<%=path+ "/js/mylayui.js" %>
		>
	</script>
	<script src=<%=path+ "/js/apply/vercode.js" %>
		>
	</script>
	<script src=<%=path+ "/js/apply/searchCoach.js" %>
		>
	</script>
	<script src=<%=path+ "/js/apply/face.js" %>
		>
	</script>
	<script type="text/javascript" src=<%=path+ "/js/school_student.js" %>
		>
	</script>

</html>