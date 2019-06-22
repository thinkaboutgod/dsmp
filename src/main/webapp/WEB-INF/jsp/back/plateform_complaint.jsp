<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>申诉渠道</title>
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
<script type="text/javascript" src=<%=path+"/js/schoolmanagement/plateformcomplaint.js"%>></script>

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
				<div><h2>申诉管理</h2></div>
				
				<div class="row">
					<div class="col-sm-12">
					<!-- body -->
	  	 				<div class="box-body">
							<div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
								<div class = "input-group" > 

                              		<input type = "text" class=" form-control" name="dno"
                               					 id="sname" placeholder="驾校名称">
                           		</div>
                           		<div class = "input-group">

                               		<input type = "text" class = "form-control"  placeholder="账号" name="userName"
                               				 id="saccount">
                           		</div>　
                           			申请时间：
                           		<div class = "input-group" > 

												
                              		<input type = "date" class=" form-control" name="dno"
                               					 id="begintime" placeholder="起始时间">
                           		</div>
                           		<div class = "input-group" > 
<!--                               				  至<span class="input-group-addon text-center"></span> -->
                              		<input type = "date" class=" form-control" name="dno"
                               					 id="endtime" placeholder="终止时间">
                           		</div>
								<div class = "input-group">
                               				 
                               		<input type = "button"  value="搜索"  class="btn btn-primary btn-sm"
                               				 id="buttonsearch">
                           		</div>
								<table id="schoolmsg" class="table table-bordered table-hover">
									<thead>
										<tr role="row">
											<th >驾校名称</th>
											<th >驾校账号</th>
											<th >驾校法人</th>
											<th >申诉时间</th>
											<th >申诉内容</th>
											<th >回复内容</th>
											<th >回复</th>
										</tr>
									</thead>
									<tbody></tbody>
								</table>							
							</div>
						</div>
						<!-- /body -->
					</div>
				</div>
			</section>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	<!-- 模态框 -->
<div class="modal fade" id="theappealtoreply" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">申诉回复</h4>
            </div>
				<div class="modal-body">
					<div class="form-horizontal">
						<div class="form-group">
							<label for="ed_name" class="col-sm-2 control-label">回复：</label>
							<div class="col-sm-10">
								<textarea rows="10" cols="60" id="replymsg"></textarea>
							</div>
						</div>
						<div class="form-group" style="text-align:center;">
							
							<div class="col-sm-10">
								<input type="text" style="display:none" id="getappid"/>
								<button  class="btn btn-danger btn-sm"  id="replybutton">提交回复</button>　　　
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