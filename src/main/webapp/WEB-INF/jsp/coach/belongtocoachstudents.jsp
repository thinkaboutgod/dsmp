<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>教练端主页</title>
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
	<link rel="stylesheet" href="<%=path %>/layui/css/layui.css"  media="all">
</head>
<body class="hold-transition skin-red sidebar-mini">


<div class="wrapper">
	<jsp:include page="/WEB-INF/jsp/back/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/back/sliderbar.jsp"></jsp:include>
	  <div class="content-wrapper">
	  	<section class="content-header">
	  		
	  	</section>
	  	<section class="content">
	  	 	<div class="row">
	  	 		<div class="col-md-12">
	  	 			 
           		 <div class="layui-tab layui-tab-brief" >
 				 <ul class="layui-tab-title">
			     <li class="subject layui-this"><h3>查看所有学员</h3></li>
  				 <li class="subject"><h3>科目一学员</h3></li>
  			 	 <li class="subject"><h3>科目二学员</h3></li>
   				 <li class="subject"><h3>科目三学员</h3></li>
   				 <li class="subject"><h3>科目四学员</h3></li>   				 
 				 </ul>
 				 
				</div> 
            <!-- /.box-header -->
            <div class="box-body">
							<div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">

<!-- 								<div class="row"> -->
<!-- 									<div class="col-sm-12"> -->
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
                           				 注册时间：
                           				 <div class = "input-group" > 
<!--                               				  <span class="input-group-addon text-center"></span> -->
												
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
                           				 <br><br><br>
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
													<th >当前科目</th>
													<th >查看详情</th>
												</tr>
											</thead>
											<tbody></tbody>
										
										</table>
									</div>
<!-- 								</div> -->
<!-- 							</div> -->
						</div>
            <!-- /.box-body -->
          
	  	 		</div>
	  	 		
	  	 	</div>
	  	</section>
	  </div>
    <!-- 导入页脚 -->
    <jsp:include page="/WEB-INF/jsp/back/footer.jsp"></jsp:include>
</div><!-- 对应整个页面 -->

</body>
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
<%-- <script type="text/javascript" src=<%=path +"/layer/layer.js"%>></script> --%>
<script type="text/javascript" src=<%=path+"/adminlte/js/menucontrol.js"%> ></script>
<script type="text/javascript" src=<%=path+"/js/belongtocoach.js" %>></script>
<script type="text/javascript" src=<%=path+"/js/datatables_setting.js" %>></script>
<script type="text/javascript" src=<%=path+"/js/Date.js" %>></script>
</html>
