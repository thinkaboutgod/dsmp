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
                               <div class = "input-group" >
                                       <input type = "text" class=" form-control"  id="cname" placeholder="用户姓名">
                                   </div>
                                   <div class = "input-group">
                                       <input type = "text" class = "form-control"  placeholder="账号"  id="caccount">
                                   </div>
                                   <div class = "input-group" >
                         <span>注册时间：</span>
                                   </div>
                                   <div class = "input-group" >
                                       <input type = "date" class=" form-control" name="dno"
                                        id="begintime" placeholder="起始时间">
                                   </div>
                                   <div class = "input-group" >
                         <span>至</span>
                                   </div>
                                   <div class = "input-group" >
                                       <input type = "date" class=" form-control" name="dno"
                                          id="endtime" placeholder="终止时间">
                                   </div>
                           <div class = "input-group">
                                        <button  class="button btn-primary btn-sm bt_qi"  id="buttonsearch">搜索</button>
                                   </div>
									<table id="coachTable" class="table table-bordered table-hover">
										<thead>
											<tr role="row">
<!-- 													<th >选择</th> -->
											<th >账号</th>
											<th >姓名</th>
											<th >性别</th>
											<th >住址</th>
											<th >执教资质</th>
											<th >用户状态</th>
											<th >操作</th>
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
	        <!-- BAR CHART表 -->
	       
	  	</section>

	  </div><!-- 对应右侧主界面 -->
    <!-- 导入页脚 -->
    <jsp:include page="footer.jsp"></jsp:include>
</div><!-- 对应整个页面 -->

<!-- 模态框 -->
<div class="modal fade" id="changeData" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">查看教练信息</h4>
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
</div><!-- 对应模态框 -->
</body>
<script type="text/javascript" src=<%=request.getContextPath()+"/js/jquery-3.3.1.js" %>></script>
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
<script type="text/javascript" src=<%=path+"/js/school_coach.js" %>></script>
<script type="text/javascript" src=<%=path+"/js/datatables_setting.js" %>></script>
<!-- Morris.js charts -->
<script type="text/javascript" src=<%=path+"/adminlte/js/raphael-min.js"%> ></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/morris.js"%> ></script>


</html>
