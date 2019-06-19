<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理端主页</title>
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
</head>
<body class="hold-transition skin-blue sidebar-mini">
<input type="hidden" id="path" value=<%=path%>>
<div class="wrapper">
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="sliderbar.jsp"></jsp:include>
	<div class="content-wrapper">
	  	<section class="content">
	  	 	<div class="row">
	  	 		<div class="col-md-12">
					<div class="box-header">
		              	<h3 class="box-title"><strong>参数管理</strong></h3>
		           	 </div><br>
                 <div class="box"><br>
                   <div class="box-body">
           			<div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
                    <div class="row">
                      <div class="col-sm-12">

                          <%-- <hr style="height:1px;border:none;border-top:1px solid #555555;" /> --%>

                          <table id="parTable" class="table table-bordered table-hover">
		                      <thead>
		                        <tr role="row">
		                        <th >序号</th>
		                        <th >参数名称</th>
		                        <th >参数类型</th>
		                        <th >参数值</th>
		                        <th >操作</th>
		                        </tr>
		                      </thead>
		                      <tbody></tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
                </div>
	  	 		</div>  <!-- /.box-body -->
	  	 	</div>
	  	</section>

	  </div><!-- 对应右侧主界面 -->
    <!-- 导入页脚 -->
    <jsp:include page="footer.jsp"></jsp:include>
</div><!-- 对应整个页面 -->
<!-- 模态框 -->
<div class="modal fade" id="changePar" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">参数修改</h4>
            </div>
				<div class="modal-body">
					<div class="form-horizontal">
						<input id="parId" type="hidden" >
						<div class="form-group">
							<label for="parName" class="col-sm-2 control-label">参数名称：</label>
							<div class="col-sm-10">
								<input class="form-control" id="parName" type="text" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="parType" class="col-sm-2 control-label">参数类型：</label>
							<div class="col-sm-10">
								<input class="form-control" id="parType" type="text" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="parValue" class="col-sm-2 control-label">参数值：</label>
							<div class="col-sm-10">
								<input class="form-control" id="parValue" type="text">
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="center-block">
					 <button type="button" class="btn btn-default" id="change">提交修改</button>
               		 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                	</div>
           	   </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- 对应模态框 -->
</body>
<script type="text/javascript" src=<%=path+"/js/jquery-3.3.1.js" %>></script>
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
<script type="text/javascript" src=<%=path+"/js/datatables_setting.js" %>></script>
<script type="text/javascript" src=<%=path+"/js/plateform_parameter.js"%> ></script>
</html>
