<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
	.Product{
	float: left;text-align: center;width: 33%;height: 230px;
	}
	#pageoption a{cursor: pointer}
</style>

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
		              	<h3 class="box-title"><strong>广告管理</strong></h3>
		           	 </div><br>
		           	  <div class="col-xs-2" >
		           	  <label for="level">请选择广告优先级：</label>
		           	  </div>
		           	 <div class="col-xs-2" >
                    <select class="form-control " id="level">
                      <option value="0">所有</option>
                      <c:forEach items="${levelList}" begin="0" step="1" var="i">
                        <option value="${i.adlId}">${i.adlName}</option>
                      </c:forEach>
                    </select>
                    </div>
                    <div class="col-xs-3" >
                    <button class="btn btn-info" type="button" id="search" name="button">查询</button>
                 	<button class="btn btn-success fa fa-plus-square" type="button" id="addAdvertise" name="button">&nbsp;新增广告</button>
                 	</div><br>
                 	<hr style="height:1px;border:none;border-top:1px solid #555555;" />
           			<div id="videoDiv" style="width: 1000px;height:450px;float: left;">
	  	 			</div>
	  	 			<div style="text-align:center;clear:both;">
   			 			<ul id="pageoption"></ul>
					</div>
	  	 		</div>  <!-- /.box-body -->
	  	 	</div>
	  	</section>

	  </div><!-- 对应右侧主界面 -->
    <!-- 导入页脚 -->
    <jsp:include page="footer.jsp"></jsp:include>
</div><!-- 对应整个页面 -->
<!-- 模态框 修改或者新增广告-->
<div class="modal fade" id="forSchoolAdv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
				<div class="modal-body">
					<div class="form-horizontal">
					<input class="form-control" id="advId" type="hidden" >
						<div class="form-group">
							<label for="schoolId" class="col-sm-3 control-label">所属驾校：</label>
							<div class="col-sm-9">
							 <select class="form-control " id="schoolId">
							 <option value="0">--请选择驾校--</option>
		                      <c:forEach items="${schoolList}" begin="0" step="1" var="i">
		                        <option value="${i.schId}">${i.schName}(法人：${i.schBossname})</option>
		                      </c:forEach>
		                    </select>
		                    </div>	
						</div>
						<div class="form-group">
							<label for="toPath" class="col-sm-3 control-label">广告优先级：</label>
							<div class="col-sm-9">
								<select class="form-control " id="newLevel">
			                      <option value="0">--请选择广告优先级--</option>
			                      <c:forEach items="${levelList}" begin="0" step="1" var="i">
			                        <option value="${i.adlId}">${i.adlName}</option>
			                      </c:forEach>
			                    </select>
							</div>
								
						</div>
						<div class="form-group">
							<label for="toPath" class="col-sm-3 control-label">广告跳转路径：</label>
							<div class="col-sm-9">
								<input class="form-control" id="toPath" type="text" >
							</div>
								
						</div>
						<div class="form-group">
							<label for="describe" class="col-sm-3 control-label">广告描述：</label>
							<div class="col-sm-9">
								<textarea class="form-control"  id="describe" rows="4" cols="53" style="resize: none" placeholder="最长输入300个汉字" maxlength="200"></textarea>
							</div>	
						</div>
						<div class="form-group" id="imgDiv">
							<label for="newImg" id="imgLable" class="col-sm-3 control-label"></label>
							<div class="col-sm-9">
							<input  id="newImg" name="newImg" class="button btn-info btn-sm " type="file" ><br>
							<input  id="deletenewImg" class="button btn-info btn-sm " type="button" value="删除选定图片">
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
<%-- <script type="text/javascript" src=<%=path + "/js/zh.js"%>></script> --%>

<script type="text/javascript" src=<%=path+"/js/plateform_advertise.js"%> ></script>
<script type="text/javascript" src=<%=path+"/js/video_util.js"%> ></script>
<script type="text/javascript" src=<%=path+"/js/ajaxfileupload.js"%> ></script>
<script type="text/javascript" src=<%=path + "/js/bootstrap-paginator.min.js"%>></script>
</html>
