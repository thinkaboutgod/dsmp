<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = application.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>找回密码</title>
<link href=<%=path+"/css/findpwd.css" %> type="text/css" rel="stylesheet">	
<link href=<%=path+"/css/apply/findpass.css" %> type="text/css" rel="stylesheet">
<link rel="stylesheet" href=<%=path+"/css/login/reset.css" %>>
<link rel="stylesheet" href=<%=path+"/css/resetpwd/registration.css" %>>
<script src=<%=path+"/js/jquery-3.3.1.js"%> charset="utf-8"></script>
<script type="text/javascript" charset="UTF-8" async="" src=<%=path+"/js/retrievePass.js" %>></script>
</head>
<body>
<input type="hidden" id="path" value=<%=path%>>
	<div class="registration">
		<article class="login-guide-main">
			<div class="content">
				<div class="findtype">
					<strong>通过手机找回密码</strong>
				</div>
				<ul>
					<div class="content">
						<div class="web-width">
							<!--for-liucheng/-->
							<input type="hidden" id="xcodesid"
								value="d65b4bff186041bf9e11df02c37b1731"> <input
								type="hidden" id="successToken">
							<div id="captcha">
								<input type="hidden" name="NECaptchaValidate" value=""
									class="yidun_input">
							</div>

							<article class="verification_code">
								<div>
									<input type="text" id="phone" placeholder="请输入手机号"
										autocomplete="off">
									<p class="iconfont icon-jinggao"></p>
									<i></i>
								</div>
								<!--<div class="verification_code_prompt">已发送动态码到手机，收到前请勿关闭此页面</div>-->
								<div>
									<input type="password" id="newPassword"
										placeholder="请输入密码 (字母、数字或者符号，最短6位)" autocomplete="off">
									<p class="iconfont icon-jinggao"></p>
									<i></i>
								</div>
								<div>
									<input type="password" id="confirmPassword"
										placeholder="请再次输入新密码" autocomplete="off">
									<p class="iconfont icon-jinggao"></p>
									<i></i>
								</div>
								<div class="code-wrap">
									<input class="print_code" type="text" placeholder="请输入验证码"
										autocomplete="off">
									<input type="button" class="obtain_btn" value="获取手机验证码">
									<p class="iconfont icon-jinggao"></p>
									<i></i>
								</div>
								<div>
									<button id="findpass_button_2">确认</button>
								</div>
							</article>
						</div>
					</div>
				</ul>
			</div>
		
		</article>
		<footer>
			<p>厦门传一信息科技有限公司 &nbsp; 闽ICP备09002072号</p>
		</footer>
	</div>
</body>
</html>