<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = application.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>驾校管理后台登录</title>
<link href=<%=path+"/css/login/app.css" %> rel="stylesheet">
<link rel="stylesheet" href=<%=path+"/css/login/jiaxiaologin.css" %>>
<script type="text/javascript" src=<%=path+"/js/jquery-3.3.1.js"%>></script>
<script type="text/javascript" src=<%=path+"/js/login/adminlogin.js"%>></script>
<script src=<%=path+"/js/login/imgCode.js"%> charset="utf-8"></script>
<script type="text/javascript" src=<%=path + "/layer/layer.js"%>></script>
</head>
<body>
<input type="hidden" id="path" value=<%=path%>>
	<div id="app">
		<div class="login-container">
			<div class="main-left bg-def">
				<div class="header">
					<img src="" style="width: 128px;">
				</div>
			</div>
			<div class="main-right login-reg-box">
				<div class="scroll-box">
					<div class="inner">
						<div class="fssdk-tab">
							<ul class="fssdk-tab-nav">
								<li class="fssdk-tab-item fssdk-tab-item-active">运管/平台管理员登录
								</li>
							</ul>
						</div>
						<div class="fssdk-tab-content">
							<div class="fssdk-tab-content-item">
							<input type="hidden" value="">
								<div class="el-form-item el-form-item--small"
									style="margin-top: 40px;">
									<label for="username" class="el-form-item__label"
										style="width: 65px;">帐号</label>
									<div class="el-form-item__content" style="margin-left: 65px;">
										<div class="el-input el-input--small">
											<input type="text" id="account" autocomplete="off" placeholder="请输入帐号"
												class="el-input__inner">
										</div>
									</div>
								</div>
								<div class="el-form-item el-form-item--small">
									<label for="password" class="el-form-item__label"
										style="width: 65px;">密码</label>
									<div class="el-form-item__content" style="margin-left: 65px;">
										<div class="el-input el-input--small">
											<input type="password" id="password" autocomplete="off" placeholder="请输入密码"
												class="el-input__inner">
										</div>
									</div>
								</div>
								<div id="yzmdiv" class="el-form-item el-form-item--small">
									<label for="password" class="el-form-item__label"
										style="width: 65px;">验证码</label>
									<div class="el-form-item__content" style="margin-left: 65px;">
										<div class="el-input el-input--small">
											<input type="text" name="yzm" id="yzm" class="el-input__inner"
											placeholder="请输入验证码" autocomplete="off"> <img
											id="getCode" src=<%=path+"/user/loginGetCode.action?"%>
											title="图片看不清？点击重新得到验证码" style="cursor: pointer;">
										</div>
									</div>
								</div>
								
								<button type="button" id="adminlogin"
									class="el-button el-button--primary el-button--small"
									style="margin-top: 40px; font-size: 16px; width: 100%; border-radius: 20px;">
									<span>登 录 </span>
								</button>
							</div>
						</div>
					</div>
					<div class="btn-back-wrap">
						<a href=<%=path+"/home/main.action?"%> class="btn-back-index"><i
							class="el-icon-back"></i> 打开官网 </a>
					</div>
				</div>
			</div>
			<div id="captcha">
				<input type="hidden" name="NECaptchaValidate" value=""
					class="yidun_input">
			</div>
		</div>
	</div>
</body>
</html>