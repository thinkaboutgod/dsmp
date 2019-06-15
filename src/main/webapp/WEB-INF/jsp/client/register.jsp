<%@page import="org.springframework.context.ApplicationContext"%>
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
<link rel="stylesheet" href=<%=path+"/css/home/comm_style.css"%>>
<link rel="stylesheet" href=<%=path+"/css/register/reset.css"%>>
<link rel="stylesheet" href=<%=path+"/css/register/registration.css"%>>
</head>
<body>
	<input type="hidden" id="path" value=<%=path %>>
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
						<a class="right-text" href="login.html" onclick="clickLog('from=JXEDT_HEADER_LOGIN')">登录</a>
						<a class="right-text" href="http://user.jxedt.com/registerNew/xy" target="_blank" onclick="clickLog('from=JXEDT_HEADER_SIGNIN')">注册</a>
						<a class="right-text" href="http://user.jxedt.com/registerNew/jx">驾校入驻</a>
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
				<div class="register-form jx-register-form">
				    <form action="" autocomplete="off" id="registerfrom">
				        <input type="hidden" id="successToken" name="successToken">
				        <input type="hidden" id="sessionId" name="sessionId" value="a207326a2a6e4f08b20d0d1d24730db7">
				        <input type="hidden" id="version" name="version" value="4.0.1">
				        <input type="hidden" id="phonerid" name="phonerid">
				        <input type="hidden" id="callback" name="callback" value="cross">
				        <input type="hidden" id="usertype" name="usertype" value="">
				        <input type="hidden" id="userTypeEng" name="userTypeEng" value="xy">
				        <h4>学员注册</h4>
				        <div class="div-wrap">
				            <input type="text" name="mobile" class="register-phone" placeholder="请输入手机号">
				            <p class="iconfont icon-jinggao">
				            </p>
				            <i></i>
				        </div>
				        <div class="div-wrap">
				            <input type="password" name="password" class="register-pwd" placeholder="请输入密码 (字母、数字或者符号，最短6位)">
				            <p class="iconfont icon-jinggao">
				            </p>
				            <i></i>
				        </div>
				        <div class="code-block div-wrap">
				            <input type="text" name="indexccode" class="register-code" placeholder="请输入验证码">
				            <input type="button" class="register-code-btn" value="发送验证码">
				            <p class="iconfont icon-jinggao">
				            </p>
				            <i></i>
				        </div>
				        <div class="captcha-block">
				            <div id="captcha"><input type="hidden" name="NECaptchaValidate" value="" class="yidun_input"></div>
				            <p class="iconfont icon-jinggao"></p>
				        </div>
				        <div>
				            <span class="register-submit">注册</span>
				        </div>
				        <div>
				            <label for="register_checkbox">
				                <input type="checkbox" name="" id="register_checkbox" class="register-checkbox" checked="checked">
				                <span>我已阅读并接受<a href="javascript:;">《注册条款》</a>                                </span>
				            </label>
				        </div>
				    </form>
				</div>
				</div>
			</article>
			<footer>
				<p>厦门传一科技有限公司 &nbsp; 闽ICP备09002072号</p>
			</footer>
		</div>

		<script type="text/javascript">
			var sessionid = "aef87879cf2547e8a3e801b11dd7f3ed";
			var cross = function(url) {
				alert(url);
			}
		</script>
		<script src=<%=path+"/js/jquery-3.3.1.js"%> charset="utf-8"></script>
		<script src=<%=path+"/js/register/register.js"%> charset="utf-8"></script>
		<script src=<%=path+"/js/register/load.js"%> charset="utf-8"></script>
</body>
</html>