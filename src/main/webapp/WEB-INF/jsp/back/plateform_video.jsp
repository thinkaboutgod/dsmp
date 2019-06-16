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
	<link rel="stylesheet" type="text/css" href=<%=path + "/css/fileinput.min.css"%>>
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
		              	<h3 class="box-title"><strong>视频管理</strong></h3>
		           	 </div>
		           	 <div class="col-xs-2" >
                    <select class="form-control " id="subjectSelect">
                      <option value="0">--请选择科目--</option>
                      <c:forEach items="${subList}" begin="0" step="1" var="i">
                        <option value="${i.subId}">${i.subName}</option>
                      </c:forEach>
                    </select>
                    </div>
                    <div class="col-xs-3" >
                    <button class="btn btn-info" type="button" id="search" name="button">查看视频</button>
                 	<button class="btn btn-success fa fa-plus-square" type="button" id="addVideo" name="button">&nbsp;新增视频</button>
                 	</div><br>
                 	<hr style="height:1px;border:none;border-top:1px solid #555555;" />
                 	<label id="info">当前选择科目为：（暂未选择）</label>
           			<div id="videoDiv" style="width: 1000px;float: left;">
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
<!-- 模态框 修改标题-->
<div class="modal fade" id="forTitle" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">修改标题</h4>
            </div>
				<div class="modal-body">
					<div class="form-horizontal">
						<div class="form-group">
							<label for="ed_name" class="col-sm-2 control-label">标题：</label>
							<div class="col-sm-10">
								<input class="form-control" id="title" type="text" >
							</div>
							<input class="form-control" id="vidId" type="hidden" >	
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="center-block">
					<button type="button" class="btn btn-default" id="changeTitle">提交</button>
               		 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                	</div>
           	   </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- 对应模态框 -->
<!-- 模态框上传视频 -->
<div class="modal fade" id="addVideoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true"  data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">增加视频</h4>
            </div>
				<div class="modal-body">
					<div class="form-horizontal">
						<div class="form-group">
							<label for="title" class="col-sm-2 control-label">标题：</label>
							<div class="col-sm-10">
								<input class="form-control" id="upTitle" type="text" >
							</div>
						</div>
						<div class="form-group">
							<label for="forSubject" class="col-sm-2 control-label">所属科目：</label>
							<div class="col-sm-10">
								<select class="form-control " id="subjectUp">
                      				<option value="0">--请选择科目--</option>
                    			   <c:forEach items="${subList}" begin="0" step="1" var="i">
                       			    <option value="${i.subId}">${i.subName}</option>
                                   </c:forEach>
                                </select>
							</div>
						</div>
						<div class="form-group">
							<label for="upVideo" class="col-sm-2 control-label">视频文件：</label>
							<div class="col-sm-10">
								<input class=" file-loading"  name="file" id="upVideo" type="file" contentEditable="false">
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="center-block">
					<button type="button" class="btn btn-default" id="next">下一步</button>
               		 <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                	</div>
           	   </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- 对应模态框 -->

<!-- 模态框上传视频图片 -->
<div class="modal fade" id="addVideoImgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true"  data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">增加视频</h4>
            </div>
				<div class="modal-body">
					<div class="form-horizontal">
						<div class="form-group">
							<label for="ed_name" class="col-sm-2 control-label">请选择视频图片：</label>
							<div class="col-sm-10">
								<input class=" file-loading"  name="fileImg" id="upVideoImg" type="file" contentEditable="false">
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="center-block">
					<button type="button" class="btn btn-default" id="up">提交</button>
               		 <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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
<script type="text/javascript" src=<%=path + "/js/fileinput.min.js"%>></script>
<script type="text/javascript" src=<%=path + "/js/zh.js"%>></script>
<script type="text/javascript" src=<%=path+"/js/video_util.js"%> ></script>
<script type="text/javascript" src=<%=path+"/js/plateform_video.js"%> ></script>
<script type="text/javascript" src=<%=path + "/js/bootstrap-paginator.min.js"%>></script>
</html>
