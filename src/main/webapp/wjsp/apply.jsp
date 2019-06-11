<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script src=<%=path+"/layui/layui.js" %> charset="utf-8"></script>
<script src=<%=path+"/js/mylayui.js" %>></script>
</head>
<body>
	<div class="registration">
		<article class="login-guide-main">
			<div class="content">
				<div class="findtype">
					<strong>学员在线报名</strong>
				</div>
				<!-- 学员注册框 -->
				<div>
					<div id="layerregspan" class="layui-layer-content"
						style="height: 600px;">
						<div class="reg layui-layer-wrap" style="width: 500px;" id="reg"
							lay-filter="reg">
							<form class="layui-form layui-form-pane" id="form_reg">
								<div class="layui-upload">
									<label class="layui-form-label">身份证图片:</label>
									<div class="layui-upload layui-input-block">
										<button class="layui-btn layui-btn-danger" id="test8"
											type="button">
											<i class="layui-icon"></i>上传图片
										</button>
										<button type="button" class="layui-btn layui-btn-warm"
											id="upload_btn">识别图片</button>
									</div>
								</div>
								<div class="layui-form-item" style="margin-top: 20px;">
									<label class="layui-form-label">真实姓名</label>
									<div class="layui-input-block">
										<input type="text" name="real_name" lay-verify="real_name"
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
										<input type="radio" name="student_sex" value="男" title="男">
										<div class="layui-unselect layui-form-radio">
											<i class="layui-anim layui-icon"></i>
											<div>男</div>
										</div>
										<input type="radio" name="student_sex" value="女" title="女"
											checked="checked">
										<div
											class="layui-unselect layui-form-radio layui-form-radioed">
											<i class="layui-anim layui-icon"></i>
											<div>女</div>
										</div>
									</div>
								</div>
								<div class="layui-form-item ">
									<label class="layui-form-label">选择驾校</label>
									<div class="layui-input-inline">
										<select name="schools" id="schools" lay-verify="required"
											lay-search="" lay-filter="schools">
											<option value=""></option>
											<option selected="selected">驾校一</option>
											<option>潮州市潮安区庚信源汽车贸易有限公司</option>
											<option>蛇皮</option>
											<option>33</option>
											<option>蛇皮</option>
											<option>蛇皮</option>
											<option>蛇皮</option>
											<option>蛇皮</option>
											<option>蛇皮</option>
											<option>传一科技</option>
											<option>腾讯驾校</option>
											<option>苹果驾校</option>
										</select>
										<div class="layui-form-select">
											<div class="layui-select-title">
												<input type="text" placeholder="请选择" value="驾校一"
													class="layui-input"><i class="layui-edge"></i>
											</div>
											<dl class="layui-anim layui-anim-upbit">
												<dd lay-value="" class="layui-select-tips">请选择</dd>
												<dd lay-value="驾校一" class="layui-this">驾校一</dd>
												<dd lay-value="潮州市潮安区庚信源汽车贸易有限公司" class="">潮州市潮安区庚信源汽车贸易有限公司</dd>
												<dd lay-value="蛇皮" class="">蛇皮</dd>
												<dd lay-value="33" class="">33</dd>
												<dd lay-value="蛇皮" class="">蛇皮</dd>
												<dd lay-value="蛇皮" class="">蛇皮</dd>
												<dd lay-value="蛇皮" class="">蛇皮</dd>
												<dd lay-value="蛇皮" class="">蛇皮</dd>
												<dd lay-value="蛇皮" class="">蛇皮</dd>
												<dd lay-value="传一科技" class="">传一科技</dd>
												<dd lay-value="腾讯驾校" class="">腾讯驾校</dd>
												<dd lay-value="苹果驾校" class="">苹果驾校</dd>
											</dl>
										</div>
									</div>
								</div>
								<div class="layui-form-item" style="" id="teacher_div">
									<label class="layui-form-label">选择教练</label>
									<div class="layui-input-inline">
										<select name="teachers" id="teachers" lay-search=""
											lay-filter="teachers">
											<option selected="selected"></option>
											<option>教练一</option>
											<option>教练二</option>
										</select>
										<div class="layui-form-select">
											<div class="layui-select-title">
												<input type="text" placeholder="请选择" class="layui-input"><i
													class="layui-edge"></i>
											</div>
											<dl class="layui-anim layui-anim-upbit">
												<dd lay-value="" class="layui-select-tips">请选择</dd>
												<dd lay-value="教练一" class="">教练一</dd>
												<dd lay-value="教练二" class="">教练二</dd>
											</dl>
										</div>
									</div>
								</div>

								<div class="layui-form-item" style="margin-top: 20px;">
									<label class="layui-form-label">手机号码</label>
									<div class="layui-input-block">
										<input type="text" name="student_phone" lay-verify="phone"
											id="student_phone" lay-vertype="tips" autocomplete="off"
											placeholder="请输入手机号码" class="layui-input">
									</div>
								</div>
								<div class="layui-form-item" style="margin-top: 20px;">
									<label class="layui-form-label">短信验证</label>
									<div class="layui-input-block">
										<input type="text" name="message" lay-verify="message"
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
										<button class="layui-btn layui-btn-normal" lay-submit=""
											lay-filter="student_reg">报名</button>
										&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
											class="layui-btn layui-btn-normal" value="返回" id="reg_back">
									</div>
								</div>
							</form>
						</div>
					</div>
					<span class="layui-layer-setwin"><a
						class="layui-layer-ico layui-layer-close layui-layer-close1"
						href="javascript:;"></a></span>
				</div>
			</div>
		</article>
		<footer>
			<p>厦门传一科技有限公司 &nbsp; 闽ICP备09002072号</p>
		</footer>
	</div>
</body>
</html>