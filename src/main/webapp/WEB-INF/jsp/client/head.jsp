<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = application.getContextPath();
%>
	<nav class="comment-header-nav">
			<!-- 联系 -->
			<div class="comment-header-contact">
				<div class="comment-header-left">
					<span class="comment-header-phone-describe">您好，欢迎来到机动车驾驶培训公众服务平台！</span>
				</div>
				<div class="comment-header-right">
				<c:choose>
					<c:when test="${student != null}">
						<a class="right-text" href=""><span>欢迎：${student.stuName}</span></a>	
						<a class="right-text" href=<%=path+"/home/logout.action" %>><span>注销</span></a>						
					</c:when>
					<c:otherwise>
						<a class="right-text" href=<%=path+"/student/login.action"%>					
						onclick="clickLog('from=JXEDT_HEADER_LOGIN')">登录</a> <a
						class="right-text" href=<%=path + "/student/register.action"%>
					    onclick="clickLog('from=JXEDT_HEADER_SIGNIN')">注册</a>
					</c:otherwise>
				</c:choose>								
					<a class="right-text" href=<%=path+"/school/schoolEnterPage.action" %>  target="_blank">驾校入驻</a>
					<a class="right-text" href=<%=path + "/admin/admin.action"%>>管理后台</a>
				</div>
			</div>
		</nav>
	</div>

	<div class="toptwo m_m">
		<div class="logo fl slideInDown animated">
			<a href="javascript:;"><img src=<%=path + "/images/home/logo.jpg"%> /></a>
		</div>
		<div class="phone fr slideInDown animated">
			<em>服务热线&nbsp;Service Hotline</em>
			<p>0592-8888888&nbsp;/&nbsp;8888888</p>
		</div>
	</div>
	<!--nav-->
	<div class="navBar bounce animated">
		<ul class="nav clearfix m_m">
			<li class="m cur">
				<h3>
					<a href=<%=path+"/home/main.action?"%>>首页</a>
				</h3>
			</li>

			<li class="m ">
				<h3>
					<a href=" javascript:;">驾校</a>
				</h3>
				<ul class="sub">
					<li><a target="_blank" href=<%=path+"/school/allSchoolPage.action" %>>所有驾校</a></li>					
				</ul>
			</li>
			<li class="m ">
				<h3>
					<a href=" javascript:;">教练</a>
				</h3>
				<ul class="sub">
					<li><a target="_blank" href=<%=path+"/tbcoach/allCoachPage.action" %>>所有教练</a></li>					
				</ul>
			</li>
			<li class="m ">
				<h3>
					<a href="javascript:;">学员在线报名</a>
				</h3>
				<ul class="sub">
					<li><a href=<%=path+"/student/apply.action?" %>>报考驾校</a></li>					
				</ul>
			</li>
			
			<li class="m ">
				<h3>
					<a href=" javascript:;">个人中心</a>
				</h3>
				<ul class="sub">
					
					<li><a target="_blank" href=<%=path+"/personal/toStuPersonal.action"%>>个人中心</a></li>
					
<%-- 					<li><a target="_blank" href=<%=path+"/personal/coachRating.action"%>>教练评价</a></li> --%>
<%-- 					<li><a target="_blank" href=<%=path+"/personal/schoolRating.action"%>>驾校评价</a></li> --%>
<%-- 					<li><a target="_blank" href=<%=path+"/personal/examSchedule.action"%>>考试安排</a></li> --%>


				</ul>
			</li>
			<li class="m">
				<h3>
					<a href=" javascript:;">联系我们</a>
				</h3>
				<ul>

				</ul>
			</li>
		</ul>
	</div>
	
	<script type="text/javascript">
		jQuery(".nav").slide({
			type : "menu", //效果类型
			titCell : ".m", // 鼠标触发对象
			targetCell : ".sub", // 效果对象，必须被titCell包含
			effect : "slideDown", //下拉效果
			delayTime : 300, // 效果时间
			triggerTime : 0, //鼠标延迟触发时间
			returnDefault : true
		//返回默认状态
		});
	</script>