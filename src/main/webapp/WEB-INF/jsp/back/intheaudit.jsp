<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>入驻审核</title>
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
<script type="text/javascript" src=<%=path+"/js/schoolmanagement/intheaudit.js" %>></script>
    <link type="text/css" href="<%=path %>/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/font-awesome.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/ionicons.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/bootstrap-datatable/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/adminlte.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/all-skins.min.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=path %>/layui/css/layui.css"  media="all">
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<jsp:include page="header.jsp"></jsp:include>
		<jsp:include page="sliderbar.jsp"></jsp:include>
		<div class="content-wrapper">
			<section class="content">
				<div><h1>入驻审核</h1></div>
				<!-- body -->
				<div class="row">
					<div class="col-sm-12">
	  	 				<div class="box-body">
							<div id="school_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">

								<table id="school" class="table table-bordered table-hover">
									<thead>
										<tr role="row">
											<th >驾校名称</th>
											<th >驾校账号</th>
											<th >驾校地址</th>
											<th >驾校法人</th>
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
				<!-- /body -->
			</section>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	<!-- 模态框 -->
	<div class="modal fade" id="schoolDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">驾校审核</h4>
            </div>
				<div class="modal-body">
					<div class="form-horizontal">
						<div class="form-group">
							<label for="ed_name" class="col-sm-2 control-label">驾校名：</label>
							<div class="col-sm-10">
								<input class="form-control" id="schName" type="text" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="ed_phone" class="col-sm-2 control-label">公司类型：</label>
							<div class="col-sm-10">
								<input class="form-control" id="schType" type="text" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="ed_phone" class="col-sm-2 control-label">注册资本：</label>
							<div class="col-sm-10">
								<input class="form-control" id="schRegistercapital" type="text" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="ed_phone" class="col-sm-2 control-label">信用代码：</label>
							<div class="col-sm-10">
								<input class="form-control" id="schCreditcode" type="text" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="ed_phone" class="col-sm-2 control-label">头像：</label>
							<div class="col-sm-10">
								<img alt="50x50" width="100" height="100" id="image"/>
							</div>
						</div>
						<div class="form-group" style="text-align:center;">
							
							<div class="col-sm-10">
								<input type="text" style="display:none" id="getschid"/>
								<button  class="btn btn-danger btn-sm getaudit" >审核不准</button>　　　
								<button  class="btn btn-primary btn-sm getaudit" >审核通过</button>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="center-block">
               		 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                	</div>
           	   </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- 对应模态框 -->
</body>
</html>