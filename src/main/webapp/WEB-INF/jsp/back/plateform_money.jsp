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
		              	<h3 class="box-title"><strong>资金记录</strong></h3>
		           	 </div><br>
                  <div class="box"><br>
           			<div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
                    <div class="row">
                      <div class="col-sm-12">
                        <div class="box-header">
                     				<div class = "input-group">
                                       <input type = "text" class=" form-control"  id="capOrderNumber" placeholder="订单编号" maxlength="100">
                                   </div>
                                   <div class = "input-group">
                                       <input type = "text" class = "form-control"  placeholder="报名学员"  id="stuName" maxlength="20">
                                   </div>
                                   <div class = "input-group">
                                       <input type = "text" class = "form-control"  placeholder="所报名驾校"  id="schname" maxlength="20">
                                   </div>
                                   <div class = "input-group" >
                         <span>订单时间：</span>
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
                                        <button  class="button btn-primary btn-sm "  id="buttonsearch">搜索</button>
                                   </div>
                               </div>
                          <%-- <hr style="height:1px;border:none;border-top:1px solid #555555;" /> --%>
                        <div class="box-body">
                          <table id="moneyTable" class="table table-bordered table-hover">
		                      <thead>
		                        <tr role="row">
		                        <th >序号</th>
		                        <th >订单编号</th>
		                        <th >报名学员姓名</th>
		                        <th >所报名驾校</th>
		                        <th >订单时间</th>
		                        <th >金额</th>
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
	  	 		</div>  <!-- /.box-body -->
	  	 	</div>
	  	</section>

	  </div><!-- 对应右侧主界面 -->
    <!-- 导入页脚 -->
    <jsp:include page="footer.jsp"></jsp:include>
</div><!-- 对应整个页面 -->

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
<!-- 日期格式 -->
<script type="text/javascript" src=<%=path+"/js/Date.js" %>></script>
<script type="text/javascript" src=<%=path+"/js/plateform_money.js"%> ></script>
</html>
