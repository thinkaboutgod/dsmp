<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = application.getContextPath();
%>
	<!--foot-->
	<section class="fadeInUp animated">
		<!--友情链接-->
		<div class="linkbox">
			<div class="linknr m_m">
				<span>友情链接：</span>
				<p>
					<c:forEach begin="0" step="1" items="${hotList}" var="hot">
						<a href='${hot.holPath}' target='_blank'>${hot.holTitle}</a>						
					</c:forEach>
				</p>
			</div>
		</div>
		<div class="footend">
			<p class="m_m">
				JX1811班小组项目 --版权所有：Copyright ? 2018-2019 dmakucms.com <a href="javascript:;"
					target="_blank">Power by dmakuCms</a>&nbsp;&nbsp;&nbsp;&nbsp;技术支持：<a
					href="http://www.cymooc.org/" target="_blank">传一科技</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="javascript:;" target="_blank">闽ICP备8734688834号</a>
			</p>
		</div>
	</section>
