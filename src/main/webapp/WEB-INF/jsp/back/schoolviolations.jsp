<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>驾校违规处罚</title>
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
<script type="text/javascript" src=<%=path+"/js/schoolmanagement/schoolviolations.js" %>></script>


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
				
				<!-- body -->
				<div class="row">
					<div class="col-sm-12">
	  	 				<div class="box-body">
							<div id="allschool_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">

								<div class = "input-group" > 

                              		<input type = "text" class=" form-control" name="sname"
                               					 id="sname" placeholder="驾校姓名">
                           		</div>
                           		
                           		<div class = "input-group">

                               		<input type = "text" class = "form-control"  placeholder="驾校账号" name="account"
                               				 id="saccount">
                           		</div>　
                           		
                           		<div class = "input-group">

                               		<input type = "text" class = "form-control"  placeholder="驾校法人" name="boss"
                               				 id="sboss">
                           		</div>　
                           				                           				
 
             
								<div class = "input-group">
                               				 
                               		<input type = "button"  value="搜索" class="btn btn-primary btn-sm"
                               				 id="buttonsearch">
                           		</div>
                           		<br><br><br>								
								
								<table id="allschool" class="table table-bordered table-hover">
									<thead>
										<tr role="row">
											<th >驾校名称</th>
											<th >驾校账号</th>
											<th >驾校地址</th>
											<th >驾校法人</th>
<!-- 											<th >公司类型</th> -->
<!-- 											<th >注册资本</th> -->
<!-- 											<th >信用代码</th> -->
											<th>查看详情</th>				
											<th>运营权限</th>
											<th>操作</th>
											<th >运营状态</th>
											
											<th>操作2</th>
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
                <h4 class="modal-title" id="myModalLabel">驾校信息</h4>
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