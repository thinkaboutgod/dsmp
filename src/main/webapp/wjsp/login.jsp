<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	String path = application.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon"
	href="https://img.58cdn.com.cn/jxedt/logos/favicon.ico?v=20180725">

<link rel="stylesheet" href=<%=path+"/css/home/comm_style.css"%>>
<link rel="stylesheet" href=<%=path+"/css/login/reset.css"%>>
<link rel="stylesheet" href=<%=path+"/css/login/registration.css"%>>
<link rel="stylesheet" href=<%=path+"/css/login/login.css"%>>
<link rel="stylesheet" href=<%=path+"/css/home/layui.css"%> media="all">
<script src=<%=path+"/js/layui.js"%> charset="utf-8"></script>
<script src=<%=path+"/js/loginlayui.js"%> charset="utf-8"></script>
</head>
<body>
	<div class="nav-wrap">
		<nav class="comment-header-nav">
			<!-- 联系 -->
			<div class="comment-header-contact">
				<div class="comment-header-left">
					<!--<span class="VTargetSpan"></span>-->
					<!--<img class="comment-header-vip-img" src="" alt="">-->
					<span class="comment-header-phone-describe">您好，欢迎来到厦门机动车驾培公众服务平台！</span>
				</div>
				<div class="comment-header-right">
					<a class="right-text" href="login.html"
						onclick="clickLog('from=JXEDT_HEADER_LOGIN')">登录</a> <a
						class="right-text" href="register.html"
						onclick="clickLog('from=JXEDT_HEADER_SIGNIN')">注册</a> <a
						class="right-text" href="http://user.jxedt.com/registerNew/jx">驾校入驻</a>
					<a class="right-text" target="_blank" href="jiaxiao.html">管理后台</a>
				</div>
			</div>
		</nav>
	</div>
	<div class="registration">
		<article class="login-guide-main">
			<div class="jx-register">
				<div class="jx-register-img">
					<!-- 驾考图片 -->
				</div>
				<div class="register-form login-form">
					<div class="layui-tab" lay-filter="test">
						<ul class="layui-tab-title">
							<li class="layui-this" lay-id="11">学员登录</li>
							<li lay-id="22">教练登录</li>
							<li lay-id="33">驾校登录</li>
						</ul>
						<div class="layui-tab-content">
							<div class="layui-tab-item layui-show">
								<form action="" autocomplete="off" id="loginform">
									<input type="hidden" id="successToken" name="successToken">
									<input type="hidden" id="xcodesessionid" name="xcodesessionid"
										value="aef87879cf2547e8a3e801b11dd7f3ed"> <input
										type="hidden" id="version" name="version" value="4.0.1">
									<input type="hidden" id="phonerid" name="phonerid"> <input
										type="hidden" id="reurl" name="reurl" value=""> <input
										type="hidden" id="isMsg" name="isMsg">
									<h4>学员登录</h4>
									<div class="div-wrap">
										<input type="text" name="phone"
											class="register-phone input-success" placeholder="请输入手机号或用户名"
											autocomplete="off">
										<p class="iconfont icon-jinggao"></p>
										<i></i>
									</div>
									<div class="pwd-login-to div-wrap">
										<input type="password" name="password" class="register-pwd"
											placeholder="请输入密码" autocomplete="off">
										<p class="iconfont icon-jinggao"></p>
										<i></i>
									</div>
									<div class="div-wrap">
										<input type="text" name="yzm" id="yzm"
											class="" placeholder="请输入验证码"
											autocomplete="off">
										<img id="getCode" src=<%=path+"/wjsp/user/loginGetCode.action?"%> title="图片看不清？点击重新得到验证码" style="cursor:pointer;">
										<p class="iconfont icon-jinggao"></p>
										<i></i>
									</div>
									<div class="slider">
										<div id="captcha">
											<input type="hidden" name="NECaptchaValidate" value=""
												class="yidun_input">
										</div>
										<p class="iconfont icon-jinggao"></p>
									</div>
									<div class="toggle-div">
										<p class="forget-pwd">
											<a href="changepwd.html">忘记密码</a>
										</p>
									</div>
									<div>
										<span class="login-submit">登录</span>
									</div>
									<!--<div class="register">-->
									<!--<span>还没有账号？-->
									<!--<a href="/registerGuidance">立即注册</a>-->
									<!--</span>-->
									<!--</div>-->
								</form>
							</div>
							<div class="layui-tab-item">
								<h4>教练登录</h4>
							</div>
							<div class="layui-tab-item">
								<h4>驾校登录</h4>
							</div>
						</div>
					</div>
					、
				</div>
			</div>
		</article>
		<footer>
			<p>厦门传一科技有限公司 &nbsp; 闽ICP备09002072号</p>
		</footer>
	</div>
</body>
</html>