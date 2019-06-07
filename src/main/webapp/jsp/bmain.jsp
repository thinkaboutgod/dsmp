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
	String path = application.getContextPath();
%>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<%-- 	<link type="text/css" href="<%=request.getContextPath() %>/bootstrap-3.3.7-dist/css/bootstrap-theme.css" rel="stylesheet"> --%>
    <link type="text/css" href="<%=path %>/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/font-awesome.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/ionicons.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/bootstrap-datatable/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/adminlte.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/all-skins.min.css" rel="stylesheet">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
		<jsp:include page="header.jsp"></jsp:include>
		<jsp:include page="sliderbar.jsp"></jsp:include>
	
	  <div class="content-wrapper">
	  	<section class="content-header">
	  		
	  	</section>
	  	<section class="content">
	  	 	<div class="row">
	  	 		<div class="col-md-12">
	  	 			 
            <div class="box-header">
              <h3 class="box-title">查看学员</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
							<div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">

								<div class="row">
									<div class="col-sm-12">
										<div class = "input-group" > 
<!--                               				  <span class="input-group-addon text-center"></span> -->
                              				  <input type = "text" class=" form-control" name="dno"
                               					 id="sname" placeholder="学员姓名">
                           				 </div>
                           				 <div class = "input-group">
<!--                                				 <span class = "input-group-addon"></span> -->
                               				 <input type = "text" class = "form-control"  placeholder="账号" name="userName"
                               				 id="saccount">
                           				 </div>
                           				 <div class = "input-group" > 
<!--                               				  <span class="input-group-addon text-center"></span> -->
												<span>注册时间：</span>
                              				  <input type = "date" class=" form-control" name="dno"
                               					 id="begintime" placeholder="起始时间">
                           				 </div>
                           				 <div class = "input-group" > 
<!--                               				  至<span class="input-group-addon text-center"></span> -->
                              				  <input type = "date" class=" form-control" name="dno"
                               					 id="endtime" placeholder="终止时间">
                           				 </div>
										<div class = "input-group">
                               				 
                               				 <input type = "button"  value="搜索"
                               				 id="buttonsearch">
                           				 </div>
										<table id="example1" class="table table-bordered table-hover">
											<thead>
												<tr role="row">
<!-- 													<th >选择</th> -->
													<th >账号</th>
													<th >姓名</th>
													<th >报名时间</th>
													<th >电话</th>
													<th >住址</th>
													<th >性别</th>
													<th >所属科目</th>
													<th >查看详情</th>
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
</div>
<div class="modal fade" id="changeData" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">修改用户信息</h4>
            </div>
				<div class="modal-body">
					<div class="form-horizontal">
						<div class="form-group">
							<label for="ed_account" class="col-sm-2 control-label">账号：</label>
							<div class="col-sm-10">
								<input class="form-control" id="ed_account" type="text" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="ed_name" class="col-sm-2 control-label">姓名：</label>
							<div class="col-sm-10">
								<input class="form-control" id="ed_name" type="text">
							</div>
						</div>
						<div class="form-group">
							<label for="ed_phone" class="col-sm-2 control-label">电话：</label>
							<div class="col-sm-10">
								<input class="form-control" id="ed_phone" type="text">
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="center-block">
               		 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                	 <button type="button" class="btn btn-success">提交更改</button>
                	</div>
           	   </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
</body>
<script type="text/javascript" src=<%=request.getContextPath()+"/js/jquery-3.3.1.js" %>></script>
<%-- <script type="text/javascript" src=<%=request.getContextPath()+"/js/jquery-2.2.3.min.js" %>></script> --%>
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
<script type="text/javascript" src=<%=path+"/js/mainData.js" %>></script>
<script type="text/javascript" src=<%=path+"/js/datatables_setting.js" %>></script>
</html>