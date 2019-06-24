<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = application.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学员在线报名驾校申请</title>

<link href=<%=path+"/css/apply/findpass.css" %> type="text/css" rel="stylesheet">
<link rel="stylesheet" href=<%=path+"/css/login/reset.css" %>>
<link rel="stylesheet" href=<%=path+"/css/apply/registration.css" %>>
<link rel='stylesheet' href=<%=path+"/layui/css/layui.css" %> media="all">	
<script src=<%=path+"/js/jquery-3.3.1.js"%> charset="utf-8"></script>
<script src=<%=path+"/layui/layui.js" %> charset="utf-8"></script>
<script src=<%=path+"/js/mylayui.js" %>></script>
<script src=<%=path+"/js/apply/vercode.js" %>></script>
<script src=<%=path+"/js/apply/searchCoach.js" %>></script>
<script src=<%=path+"/js/apply/face.js" %>></script>
</head>
<body background=<%=path+"/images/1.jpg" %>>
	<input type="hidden" id="path" value=<%=path %>>
	<div class="registration">
		<article class="login-guide-main">
			<div class="content">
				<div class="findtype">
					<strong>学员在线报名</strong>
				</div>
				<!-- 学员注册框 -->
				<div class="d1">
					<div id="layerregspan" class="layui-layer-content"
						style="height: 600px;">
						<div class="reg layui-layer-wrap" style="width: 500px;" id="reg"
							lay-filter="reg">
							<div class="layui-form layui-form-pane" id="form_reg">
								<div class="layui-upload">
									<label class="layui-form-label">身份证图片:</label>
									<div class="layui-upload layui-input-block">
										<button class="layui-btn layui-btn-danger" id="test8"
											type="button">
											<i class="layui-icon"></i>上传身份证
										</button>
									</div>
								</div>
								<div class="layui-form-item" style="margin-top: 20px;">
									<label class="layui-form-label">真实姓名</label>
									<div class="layui-input-block">
										<input type="text" name="real_name" lay-verify="required" 
											id="real_name" lay-vertype="tips" autocomplete="off"
											placeholder="识别身份证图片获取" class="layui-input"
											disabled="disabled">
									</div>
								</div>
								<div class="layui-form-item" style="margin-top: 20px;">
									<label class="layui-form-label">身份证号码</label>
									<div class="layui-input-block">
										<input type="text" name="idnum" lay-verify="idnum" id="idnum"
											lay-vertype="tips" autocomplete="off" placeholder="识别身份证图片获取"
											class="layui-input" disabled="disabled">
									</div>
								</div>
								<div class="layui-form-item" style="margin-top: 20px;">
									<label class="layui-form-label">家庭住址</label>
									<div class="layui-input-block">
										<input type="text" name="student_address"
											lay-verify="student_address" id="student_address"
											lay-vertype="tips" autocomplete="off" placeholder="识别身份证图片获取"
											class="layui-input" disabled="disabled">
									</div>
								</div>
								<div class="layui-form-item ">
									<label class="layui-form-label">性别</label>
									<div class="layui-input-block">
										<input type="text" name="sex" id="sex"
											lay-vertype="tips" autocomplete="off" placeholder="识别身份证图片获取"
											class="layui-input" disabled="disabled" style="width: 190px;">
									</div>
								</div>
								
								<c:choose>
   								 <c:when test="${tbSchool != null}">
										<div class="layui-form-item ">
									<label class="layui-form-label">选择驾校</label>
									<div class="layui-input-inline">
										<select name="schools"  lay-search="" lay-verify="required"
											lay-filter="schools" id="schools">
											<option value="${tbSchool.schId}">${tbSchool.schName}</option>
										</select>									
									</div>
								</div>
								<div class="layui-form-item" style="" id="teacher_div">
									<label class="layui-form-label">选择教练111</label>
									<div class="layui-input-inline">									
										<select name="teachers" id="teachers" lay-search=""
											lay-filter="teachers">
											<option></option>
											<c:forEach begin="0" step="1" items="${coaList}" var="i">
												<option value="${i.coaId}">${i.coaId}  ${i.coaName}</option>
											</c:forEach>
										</select>
									</div><label id="info" style="color:red;">*教练可以后面进入驾校选择</label>
								</div>
   								 </c:when>
   								 <c:otherwise>
									<div class="layui-form-item ">
									<label class="layui-form-label">选择驾校</label>
									<div class="layui-input-inline">
										<select name="schools"  lay-search="" lay-verify="required"
											lay-filter="schools" id="schools" >
											<option value=""></option>
											<c:forEach begin="0" step="1" items="${schList}" var="sch">
												<option value="${sch.schId }">${sch.schName }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="layui-form-item" style="" id="teacher_div">
									<label class="layui-form-label">选择教练</label>
									<div class="layui-input-inline">
										<select name="teachers" id="teachers" lay-search=""
											lay-filter="teachers">
											<option></option>
										</select>
									</div><label id="info" style="color:red;">*教练可以后面进入驾校选择</label>
								</div>
    							</c:otherwise>
								</c:choose>
															
								<div class="layui-form-item" style="margin-top: 20px;">
									<label class="layui-form-label">手机号码</label>
									<div class="layui-input-block">
										<input type="text" name="student_phone" lay-verify="phone"
											id="student_phone" lay-vertype="tips" autocomplete="off"
											placeholder="请输入手机号码" class="layui-input" value="${student.stuAccount }"  disabled="disabled">
									</div>
								</div>
								<div class="layui-form-item" style="margin-top: 20px;">
									<label class="layui-form-label">短信验证</label>
									<div class="layui-input-block">
										<input type="text" name="message" lay-verify="required"
											id="message" lay-vertype="tips" autocomplete="off"
											placeholder="请输入短信验证码" class="layui-input"
											style="width: 160px; float: left"> <input
											type="button" class="layui-btn layui-btn-warm" 
											style="float: left" id="sendmsg" value="获取短信验证码">
									</div>
									<!-- onclick="sendmessage(this,60)"  -->
								</div>

								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn layui-btn-normal" lay-submit="" id="student_reg"
											lay-filter="student_reg">提交</button>
										&nbsp;&nbsp;&nbsp;&nbsp; <a
											class="layui-btn layui-btn-normal" href=<%=path+"/home/main.action?"%>>返回</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="d2">
				<h1>拍照上传头像</h1>
					<button type="button" class="layui-btn layui-btn-warm" id="open">开启摄像头</button>
					<button type="button" class="layui-btn layui-btn-warm" id="photograph">点击拍照</button>
					<video  id="video" style="width:370px;height: 220px;"></video>
					<canvas id="canvas" width="350" height="250" ></canvas>											
				</div>	
			</div>
		</article>
		<footer>
			<p>厦门传一科技有限公司 &nbsp; 闽ICP备09002072号</p>
		</footer>
	</div>
</body>

</html>