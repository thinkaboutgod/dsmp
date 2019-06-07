<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目一模拟试卷</title>
<%
	String path = request.getContextPath();
%>
<style type="text/css">
	.optionClass{
		list-style:none;
	}
</style>
<script type="text/javascript" src=<%=path+"/js/jquery-3.3.1.js" %>></script>

</head>
<body>
	
	<div>
		<form action="" method="post">
				<ol type="1">
					<c:forEach begin="0" step="1" items="${topicList}" var="i">				
							<li>${i.topTopic }
							
							</li>
						<ol class="optionClass">
							<c:forEach begin="0" step="1" items="${i.options}" var="j">
								<li><input id="${j.optId }" type="radio" name="top${i.topId }" value="${j.optStatus }">${j.optOption }</li>
								
							</c:forEach>
								
						</ol>					
					</c:forEach>
				</ol>
		</form>
	</div>
</body>
</html>