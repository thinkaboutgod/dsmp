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
		              	<h3 class="box-title"><strong>题库管理</strong></h3>
		           	 </div>
                   <div class="box"><br>
           			<div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
                    <div class="row">
                      <div class="col-sm-12">
                        <div class="box-header">
                     		 <div class = "input-group col-sm-1">
                               <label for="topicType" class=" control-label">题目类型：</label>
                           </div>
                      		<div class = "input-group">
                              <select class="form-control" id="topicType">
                              		<option value="0">--请选择题目类型--</option>
									<option value="3">2选项类型</option>
									<option value="1">4选项类型</option>
								</select>
                            </div>
							<div class = "input-group col-sm-5">
                               <button  class="button btn-info btn-sm "  id="addTopic">增加题目</button>
                           </div>
                           
                           <div class = "input-group col-sm-3">
                               <button  class="button btn-info btn-sm "  id="getTopic">从互联网获取题目</button>
                           </div>
                         </div>
<!--                           <hr style="height:1px;border:none;border-top:1px solid #555555;" /> -->
                          <div class="box-body">
                          <table id="topicTable" class="table table-bordered table-hover">
		                      <thead>
		                        <tr role="row">
		                        <th style="text-align: center;">题目</th>
		                        <th style="text-align: center;">操作</th>
		                        </tr>
		                      </thead>
		                      <tbody ></tbody>
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
<!-- 模态框修改 题目-->
<div class="modal fade" id="forTopic" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static" >
    <div class="modal-dialog" style="width:800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">修改题目</h4>
            </div>
				<div class="modal-body">
					<div class="form-horizontal">
						<input id="topId" type="hidden" >
						<input id="oLength" type="hidden" >
						<div class="form-group">
							<label for="topTopic" class="col-sm-2 control-label">题目：</label>
							<div class="col-sm-10">
							<textarea class="form-control"  id="topTopic" rows="2" cols="80" style="resize: none" placeholder="最长输入300个汉字" maxlength="400"></textarea>
<!-- 								<input class="form-control" id="topTopic" type="text" > -->
							</div>
						</div>
						<div class="form-group">
							<input id="aoptId" type="hidden" >
							<label for="A" class="col-sm-2 control-label">A选项：</label>
							<div class="col-sm-6">
								<input class="form-control" id="A" type="text" placeholder="最长可输入80个字" maxlength="80">
							</div>
							<label for="Aans" class="col-sm-2 control-label">是否正确选项：</label>

							<div class="col-sm-2">
								<select class="form-control" id="Aans">
									<option value="yes">是</option>
									<option value="no">否</option>
								</select>
							</div>
						</div>
						<div class="form-group">
						<input id="boptId" type="hidden" >
							<label for="B" class="col-sm-2 control-label">B选项：</label>
							<div class="col-sm-6">
								<input class="form-control" id="B" type="text" placeholder="最长可输入80个字" maxlength="80">
							</div>
							<label for="Bans" class="col-sm-2 control-label">是否正确选项：</label>

							<div class="col-sm-2">
								<select class="form-control" id="Bans">
									<option value="yes">是</option>
									<option value="no">否</option>
								</select>
							</div>
						</div>
						<div class="form-group" id="cDiv">
						<input id="coptId" type="hidden" >
							<label for="C" class="col-sm-2 control-label">C选项：</label>
							<div class="col-sm-6">
								<input class="form-control" id="C" type="text" placeholder="最长可输入80个字" maxlength="80">
							</div>
							<label for="Cans" class="col-sm-2 control-label">是否正确选项：</label>

							<div class="col-sm-2">
								<select class="form-control" id="Cans">
									<option value="yes">是</option>
									<option value="no">否</option>
								</select>
							</div>
						</div>
						<div class="form-group" id="dDiv">
						<input id="doptId" type="hidden" >
							<label for="D" class="col-sm-2 control-label">D选项：</label>
							<div class="col-sm-6">
								<input class="form-control" id="D" type="text" placeholder="最长可输入80个字" maxlength="80">
							</div>
							<label for="Dans" class="col-sm-2 control-label">是否正确选项：</label>

							<div class="col-sm-2">
								<select class="form-control" id="Dans">
									<option value="yes">是</option>
									<option value="no">否</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="topAnswerDetail" class="col-sm-2 control-label">解析：</label>
							<div class="col-sm-6">
								<textarea class="form-control"  id="topAnswerDetail" rows="4" cols="53" style="resize: none" placeholder="最长输入300个汉字" maxlength="400"></textarea>
							</div>
						</div>
						<div class="form-group" id="imgDiv">
							<label for="" class="col-sm-2 control-label">图片：</label>
							<div class="col-sm-4">
								<img id="img" style="width:250px;height: 200px;"  src=""  >
							</div>
							<label for="" class="col-sm-2 control-label">更换图片：</label>
							<div class="col-sm-2">
							<input  id="newImg" name="newImg" class="button btn-info btn-sm " type="file" value="更换图片"><br>
							<input  id="deletenewImg" class="button btn-info btn-sm " type="button" value="删除选定图片">
							</div>
						</div>
						<div class="form-group" id="addimgDiv">
							<label for="addnewImg" class="col-sm-2  control-label">请选择图片：</label>
							<div class="col-sm-4">
							<input  id="addnewImg" name="addnewImg" class="button btn-info btn-sm " type="file" value="更换图片"><br>
							<input  id="deleteaddnewImg" class="button btn-info btn-sm " type="button" value="删除选定图片">
							</div>

						</div>
					</div>
				</div>
				<div class="modal-footer">
					<div class="center-block">
					<button type="button" class="btn btn-default" id="submitChange">提交</button>
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
<script type="text/javascript" src=<%=path+"/js/ajaxfileupload.js"%> ></script>
<script type="text/javascript" src=<%=path+"/js/topic_control.js"%> ></script>
</html>
