<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学员报名统计</title>
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
	<link type="text/css" href="<%=path %>/adminlte/css/morris.css" rel="stylesheet">
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
		              	<h3 class="box-title"><strong>学员报名统计</strong></h3>
		           	 </div>
		           	 <div class="box-body">

		           	 	<div class="tabbable" id="tabs-643545">
							<ul class="nav nav-tabs">
								<li  class="active"><a href="#panel-139674" data-toggle="tab">报名人数统计</a></li>
								<li><a href="#panel-185679" data-toggle="tab">驾校费用统计</a></li>
							</ul><br>
							<div class="tab-content">
								<div class="tab-pane active" id="panel-139674">
                  <!-- BAR CHART表 -->
                <div class="box box-success" style="width:1000px;">
                  <div class="box-header with-border">
                    <h3 class="box-title">各驾校报名人数统计</h3>

                    <div class="box-tools pull-right">
                      <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                      </button>
             <!--          <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button> -->
                    </div>
                  </div>
                  <div class="row " >
                    <div class="col-xs-4" >
                    <select class="form-control " id="schoolSelect">
                      <option value="0">--请选择驾校--</option>
                      <c:forEach items="${listSchool}" begin="0" step="1" var="i">
                        <option value="${i.schId}">${i.schName}</option>
                      </c:forEach>
                    </select>
                    </div>
                    <div class="col-xs-4" >
                    <select class="form-control " id="dateSelect">
                      <option value="0">--查询时间--</option>
                      <option value="1">近半年</option> 
                      <option value="2">近30天</option> 
                    </select>
                    </div>
                    <div class="col-xs-3" >
                    <button class="btn btn-info" type="button" id="search" name="button">确定</button>
                 	</div>
                 	<div class="col-xs-5" >
                    	<span  ><strong id="schoolName">当前数据所属驾校(暂未选择)</strong></span>
                 	</div>
                  </div>
                  <div class="box-body chart-responsive">
                    <div class="chart" id="bar-chart" style=" height: 300px;"></div>
                  </div>
                </div><!-- BAR CHART表 -->

								</div>
								<div class="tab-pane " id="panel-185679">
                  <div id="example2_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
                  <div class="row">
                    <div class="col-sm-12">
                       <select class="form-control " id="monthSelect">
                      	<option value="0">--请选择月份--</option>
                     	 <c:forEach items="${dateList}" begin="0" step="1" var="i">
                       		 <option value="${i.name}">${i.name}</option>
                     	 </c:forEach>
                       </select>
                         <div class = "input-group col-xs-5">
                             <button  class="btn btn-info "  id="searchByDate">确定</button>
                         </div>
                         <div class = "input-group col-xs-5">
                            <span  ><strong id="datelName">当前显示的月份为(暂未选择)</strong></span>
                         </div>
                        <hr style="height:1px;border:none;border-top:1px solid #555555;" />
                        <table id="countTable" class="table table-bordered table-striped table-hover">
                        <thead>
                          <tr role="row">
                          <th >序号</th>
                          <th >驾校名称</th>
                          <th >驾校电话</th>
                          <th >报名人数</th>
                          <th >应收费用(元)</th>
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

					</div>
	  	 		</div>
            <!-- /.box-body -->
          </div>
	  	</section>
	  </div>
    <!-- 对应右侧主界面 -->
    <!-- 导入页脚 -->
    <jsp:include page="footer.jsp"></jsp:include>
</div>
<!-- 对应整个页面 -->

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
<script type="text/javascript" src=<%=path+"/js/plateform_studentcount.js" %>></script>
<script type="text/javascript" src=<%=path+"/js/Date.js" %>></script>
<!-- Morris.js charts -->
<script type="text/javascript" src=<%=path+"/adminlte/js/raphael-min.js"%> ></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/morris.js"%> ></script>
</html>
