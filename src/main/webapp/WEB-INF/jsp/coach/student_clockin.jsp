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
		              	<h3 class="box-title"><strong>学员打卡</strong></h3>
		           	 </div>
		           	 <div class="col-xs-2" >
                    <select class="form-control " id="studentSelect">
                      <option value="0">--请选择学员--</option>
                      <c:forEach items="${studenstList}" begin="0" step="1" var="i">
                        <option value="${i.stuId}_${i.subId}_${i.tbSubject.subName}_${i.tbSubject.subTime}">${i.stuName}</option>
                      </c:forEach>
                    </select>
                    </div>
                    <div class="col-xs-3" >
                    <button class="btn btn-info" type="button" id="begin" name="button">开始打卡</button>
                 	<button class="btn btn-info " type="button" id="end" name="button">&nbsp;结束打卡</button>
                 	</div><br>
                 	<hr style="height:1px;border:none;border-top:1px solid #555555;" />
                 	当前选择学员为：<label id="info">（暂未选择）</label>
                 	正在学习科目：<label id="subject">（暂未选择）</label>
                 	已打卡学时为：<label id="timeNow">（暂无）/</label>
                 	共需学时：<label id="timeAll">（暂无）</label>
					<div >
						<video  id="video" style="width:480px;height: 320px;margin-left: 280px;"></video>
						<canvas id="canvas" style="display: none;"></canvas>
						
					</div>	
					<div style="margin-left: 300px;">
						<input type="hidden" class="btn btn-info" id="submit" value="确认打卡" />
						<input type="hidden" class="btn btn-info " id="cancle" value="取消" />
					</div>
	  	 		</div>  <!-- /.box-body -->
	  	 	</div>
	  	</section>

	  </div><!-- 对应右侧主界面 -->
    <!-- 导入页脚 -->
    <jsp:include page="footer.jsp"></jsp:include>
</div><!-- 对应整个页面 -->

</body>
<script type="text/javascript" src=<%=request.getContextPath()+"/js/jquery-3.3.1.js" %>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-3.3.7-dist/js/bootstrap.min.js"%>></script>
<!-- FastClick -->
<script type="text/javascript" src=<%=path+"/adminlte/js/jquery.slimscroll.min.js"%>></script>
<!-- AdminLTE App -->
<script type="text/javascript" src=<%=path+"/adminlte/js/app.min.js"%>></script>
<!-- AdminLTE for demo purposes -->

<script type="text/javascript" src=<%=path+"/adminlte/js/fastclick.js"%>></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/demo.js"%>></script>
<script type="text/javascript" src=<%=path + "/layer/layer.js"%>></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/menucontrol.js"%> ></script>
<script type="text/javascript" src=<%=path+"/js/student_clockin.js"%> ></script>
</html>
