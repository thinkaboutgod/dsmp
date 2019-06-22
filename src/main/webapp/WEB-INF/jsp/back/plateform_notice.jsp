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
<body class="hold-transition skin-red sidebar-mini">
<input type="hidden" id="path" value=<%=path%>>
<div class="wrapper">
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="sliderbar.jsp"></jsp:include>
	<div class="content-wrapper">
	  	<section class="content">
	  	 	<div class="row">
	  	 		<div class="col-md-12">
					<div class="box-header">
		              	<h3 class="box-title"><strong>发布管理</strong></h3>

		           	 </div><br>
                 <div class="box"><br>
           			<div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
                    <div class="row">
                      <div class="col-sm-12">
                        <div class="box-header">
							 <div class = "input-group">
                                 <button  class = "button btn-info btn-sm"   id="change">修改</button>
                            </div>
                            <div class = "input-group">
                                 <button  class = "button btn-danger btn-sm"   id="delete">删除</button>
                            </div>
                            <div class = "input-group">
                                 <button  class = "button btn-success btn-sm"   id="add">新增</button>
                            </div>
                          <hr style="height:1px;border:none;border-top:1px solid #555555;" />
                          <div class="box-body">
                          <table id="notTable" class=" table table-bordered cell-border table-hover ">
		                      <thead>
		                        <tr role="row">
		                        <th >序号</th>
		                        <th >标题</th>
		                        <th >路径</th>
		                        <th >类型</th>
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
	  	 		</div>  <!-- /.box-body -->
	  	 	</div>
	  	</section>

	  </div><!-- 对应右侧主界面 -->
    <!-- 导入页脚 -->
    <jsp:include page="footer.jsp"></jsp:include>
</div><!-- 对应整个页面 -->
<!-- 模态框 -->
<div class="modal fade" id="modalA" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">新增</h4>
            </div>
				<div class="modal-body">
					<div class="form-horizontal">
						<input id="parId" type="hidden" >
						<div class="form-group">
							<label for="title" class="col-sm-2 control-label">标题：</label>
							<div class="col-sm-10">
								<input class="form-control" id="title" type="text" >
							</div>
						</div>
						<div class="form-group">
							<label for="typeId" class="col-sm-2 control-label">类型：</label>
							<div class="col-sm-10">
							 <select class="form-control " id="typeId">
							 <option value="0">--请选择发布类型--</option>
		                      <c:forEach items="${typelList}" begin="0" step="1" var="i">
		                        <option value="${i.ntyId}">${i.ntyName}</option>
		                      </c:forEach>
		                    </select>
		                    </div>	
						</div>
						<div class="form-group">
							<label for="describe" class="col-sm-2 control-label">内容：</label>
							<div class="col-sm-10">
<!-- 								<textarea class="form-control"  id="describe" rows="6" cols="60" style="resize: none" placeholder="最长输入300个汉字" maxlength="200"></textarea> -->
								 <script id="editor" type="text/plain"></script>
							</div>	
						</div>
						<div class="form-group" id="pathInput">
							<label for="Path" class="col-sm-2 control-label">路径：</label>
							<div class="col-sm-10">
								<input class="form-control" id="Path" type="text">
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="center-block">
					 <button type="button" class="btn btn-default" id="sub">提交</button>
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

<!-- umeditor编辑器 -->
<script type="text/javascript" src=<%=path+"/utf8-jsp/ueditor.config.js"%> ></script>
<script type="text/javascript" src=<%=path+"/utf8-jsp/ueditor.all.min.js"%> ></script>

<script type="text/javascript" src=<%=path+"/js/plateform_notice.js"%> ></script>
</html>
