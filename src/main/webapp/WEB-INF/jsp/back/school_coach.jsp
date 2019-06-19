<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>管理端主页</title>
		<title>Employee DataTable Demos</title>

		<%
	String path = request.getContextPath();
%>

		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<%-- 	<link type="text/css" href="<%=request.getContextPath() %>/bootstrap-3.3.7-dist/css/bootstrap-theme.css" rel="stylesheet"> --%>
		<link type="text/css" href="<%=path %>/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
		<link type="text/css" href="<%=path %>/adminlte/css/font-awesome.min.css" rel="stylesheet">
		<link type="text/css" href="<%=path %>/adminlte/css/ionicons.min.css" rel="stylesheet">
		<link type="text/css" href="<%=path %>/bootstrap-datatable/css/dataTables.bootstrap.min.css" rel="stylesheet">
		<link type="text/css" href="<%=path %>/adminlte/css/adminlte.min.css" rel="stylesheet">
		<link type="text/css" href="<%=path %>/adminlte/css/all-skins.min.css" rel="stylesheet">
		<link type="text/css" href="<%=path %>/adminlte/css/morris.css" rel="stylesheet">
	</head>

	<body class="hold-transition skin-blue sidebar-mini">
		<div class="wrapper">
			<jsp:include page="header.jsp"></jsp:include>
			<jsp:include page="sliderbar.jsp"></jsp:include>
			<div class="content-wrapper">
				<section class="content">
					<div class="row">
						<div class="col-md-12">
							<div class="box-header">
								<h3 class="box-title">教练管理</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
									<div class="row">
										<div class="col-sm-12">
											<input type="hidden" id="schId" value="${schId}">
											<div class="input-group">
												<input type="text" class=" form-control" id="cname" placeholder="用户姓名">
											</div>
											<div class="input-group">
												<input type="text" class="form-control" placeholder="账号" id="caccount">
											</div>
											<div class="input-group">
												<span>注册时间：</span>
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
												<button class="button btn-primary btn-sm" id="buttonsearch">搜索</button>
											</div>
											<div class="input-group">
												<button class="button btn-primary btn-sm" id="btn_addCoach">新增教练</button>
											</div>
											<table id="coachTable" class="table table-bordered table-hover">
												<thead>
													<tr role="row">
														<th>账号</th>
														<th>姓名</th>
														<th>性别</th>
														<th>住址</th>
														<th>执教资质</th>
														<th>用户状态</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody></tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<!-- /.box-body -->
						</div>
					</div>

				</section>

			</div>
			<!-- 对应右侧主界面 -->
			<!-- 导入页脚 -->
			<jsp:include page="footer.jsp"></jsp:include>
		</div>
		<!-- 对应整个页面 -->

		<!-- 模态框 -->
		<div class="modal fade" id="coachDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="myModalLabel">教练信息</h4>
					</div>
					<div class="modal-body">
						<div class="form-horizontal">
							<div class="form-group">
								<label for="accountDe" class="col-sm-2 control-label">账号：</label>
								<div class="col-sm-10">
									<input class="form-control" id="accountDe" type="text" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="nameDe" class="col-sm-2 control-label">姓名：</label>
								<div class="col-sm-10">
									<input class="form-control" id="nameDe" type="text" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="sexDe" class="col-sm-2 control-label">姓名：</label>
								<div class="col-sm-10">
									<input class="form-control" id="sexDe" type="text" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="phoneDe" class="col-sm-2 control-label">电话：</label>
								<div class="col-sm-10">
									<input class="form-control" id="phoneDe" type="text" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="addressDe" class="col-sm-2 control-label">地址：</label>
								<div class="col-sm-10">
									<input class="form-control" id="addressDe" type="text" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="idCardDe" class="col-sm-2 control-label">身份证号：</label>
								<div class="col-sm-10">
									<input class="form-control" id="idCardDe" type="text" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="levelDe" class="col-sm-2 control-label">资质：</label>
								<div class="col-sm-10">
									<input class="form-control" id="levelDe" type="text" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="carDe" class="col-sm-2 control-label">教练车：</label>
								<div class="col-sm-10">
									<input class="form-control" id="carDe" type="text" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label for="introductionDe" class="col-sm-2 control-label">简介：</label>
								<div class="col-sm-10">
									<input class="form-control" id="introductionDe" type="text" readonly="readonly">
								</div>
							</div>
						</div>

						<div class="modal-footer">
							<div class="center-block">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								<button type="button" class="btn btn-success">提交更改</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- 对应查看详情模态框 -->

		<!-- 模态框 -->
		<div class="modal fade" id="addCoach" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h4 class="modal-title" id="myModalLabel">新增教练</h4>
					</div>
					<div class="modal-body">
						<div class="form-horizontal">
							<div class="form-group">
								<label for="accountNew" class="col-sm-2 control-label">账号：</label>
								<div class="col-sm-10">
									<input class="form-control add" id="accountNew" type="text" placeholder="请输入手机号">
								</div>
							</div>
							<div class="form-group">
								<label for="pwdNew" class="col-sm-2 control-label">密码：</label>
								<div class="col-sm-10">
									<input class="form-control add" id="pwdNew" type="text" placeholder="请输入密码">
								</div>
							</div>
							<div class="form-group">
								<label for="nameNew" class="col-sm-2 control-label">姓名：</label>
								<div class="col-sm-10">
									<input class="form-control add" id="nameNew" type="text" placeholder="请输入姓名">
								</div>
							</div>
							<div class="form-group">
								<label for="sexNew" class="col-sm-2 control-label">性别：</label>
								<div class="col-sm-10">
									<input id="sexNew1" name="sex" type="radio" value="男">男&nbsp;&nbsp;&nbsp;
									<input id="sexNew2" name="sex" type="radio" value="女">女
								</div>
							</div>
							<div class="form-group">
								<label for="birthdayNew" class="col-sm-2 control-label">出生日期：</label>
								<div class="col-sm-10">
									<input class="form-control add" id="birthdayNew" type="date" placeholder="请输入出生日期">
								</div>
							</div>
							<div class="form-group">
								<label for="idCardNew" class="col-sm-2 control-label">身份证号：</label>
								<div class="col-sm-10">
									<input class="form-control add" id="idCardNew" type="text" placeholder="请输入身份证号">
								</div>
							</div>
							<div class="form-group">
								<label for="levelNew" class="col-sm-2 control-label">资质：</label>
								<div class="col-sm-10">
									<!--<input class="form-control add" id="levelNew" type="text">-->
									<select class="form-control add" id="levelNew">
										<option value="0">--请选择资质--</option>
										<option value="C1">C1</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="addressNew" class="col-sm-2 control-label">地址：</label>
								<div class="col-sm-10">
									<input class="form-control add" id="addressNew" type="text" placeholder="请输入地址">
								</div>
							</div>
							<div class="form-group">
								<label for="introductionNew" class="col-sm-2 control-label">介绍：</label>
								<div class="col-sm-10">
									<input class="form-control add" id="introductionNew" type="text" placeholder="请输入个人简介">
								</div>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<div class="center-block">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							<button type="button" id="btn_add" class="btn btn-success">新增</button>
						</div>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- 对应新增教练模态框 -->
	</body>
	<script type="text/javascript" src=<%=request.getContextPath()+ "/js/jquery-3.3.1.js" %>
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

	<script type="text/javascript" src=<%=path+ "/js/datatables_setting.js" %>
		>
	</script>
	<!-- Morris.js charts -->
	<script type="text/javascript" src=<%=path+ "/adminlte/js/raphael-min.js"%>
		>
	</script>
	<script type="text/javascript" src=<%=path+ "/adminlte/js/morris.js"%>
		>
	</script>
	<script type="text/javascript" src=<%=path+ "/adminlte/js/menucontrol.js"%>
		>
	</script>
	<script type="text/javascript" src=<%=path+ "/js/school_coach.js" %>
		>
	</script>

</html>