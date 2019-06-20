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
					<!--<span class="VTargetSpan"></span>-->
					<!--<img class="comment-header-vip-img" src="" alt="">-->
					<span class="comment-header-phone-describe">您好，欢迎来到厦门机动车驾培公众服务平台！</span>
				</div>
				<div class="comment-header-right">
				<c:choose>
					<c:when test="${student != null}">
						<a class="right-text" href=""><span>欢迎：${student.stuName}</span></a>						
					</c:when>
					<c:otherwise>
						<a class="right-text" href=<%=path + "/student/login.action"%>					
						onclick="clickLog('from=JXEDT_HEADER_LOGIN')">登录</a> <a
						class="right-text" href=<%=path + "/student/register.action"%>
					    onclick="clickLog('from=JXEDT_HEADER_SIGNIN')">注册</a>
					</c:otherwise>
				</c:choose>								
					<a class="right-text" href=<%=path+"/school/schoolEnterPage.action" %>>驾校入驻</a>
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
			<p>0896-8734688834&nbsp;/&nbsp;8734688834</p>
		</div>
	</div>
	<!--nav-->
	<div class="navBar bounce animated">
		<ul class="nav clearfix m_m">
			<li class="m cur">
				<h3>
					<a href="javascript:;">首页</a>
				</h3>
			</li>
			<li class="m ">
				<h3>
					<a href=" javascript:;">模拟考试</a>
				</h3>
				<ul class="sub">

					<li><a target="_blank" href=<%=path+"/topic/findManyTopic.action?stu_id=2&sub_id=1"%>>科目一模拟</a></li>
					<li><a target="_blank" href=<%=path+"/topic/findAllTopic.action?stu_id=2&sub_id=1"%>>科目一练习</a></li>
					<li><a target="_blank" href=<%=path+"/topic/findMistakeTopic.action?stu_id=2&sub_id=1"%>>科目一错题集</a></li>

					<li><a href="javascript:;">科目四</a></li>

				</ul>
			</li>
			<li class="m ">
				<h3>
					<a href=" javascript:;">驾校</a>
				</h3>
				<ul class="sub">

					<li><a href="javascript:;">第一系列</a></li>

					<li><a href="javascript:;">第二系列</a></li>

					<li><a href="javascript:;">第三系列</a></li>

					<li><a href="javascript:;">第四系列</a></li>

					<li><a href="javascript:;">第五系列</a></li>

					<li><a href="javascript:;">第六系列</a></li>

				</ul>
			</li>
			<li class="m ">
				<h3>
					<a href=" javascript:;">教练</a>
				</h3>
				<ul class="sub">

					<li><a href="javascript:;">公司动态</a></li>

					<li><a href="javascript:;">行业新闻</a></li>

					<li><a href="javascript:;">常见问题</a></li>

				</ul>
			</li>
			<li class="m ">
				<h3>
					<a href=<%=path+"/student/apply.action?" %>>学员在线报名</a>
				</h3>
				<ul>

				</ul>
			</li>
			<li class="m ">
				<h3>
					<a href=" javascript:;">人才招聘</a>
				</h3>
				<ul>

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
			<li class="m ">
				<h3>
					<a href=" javascript:;">联系我们</a>
				</h3>
				<ul>

				</ul>
			</li>
		</ul>
	</div>