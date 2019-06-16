<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学员个人中心</title>
<%
	String path = request.getContextPath();
%>
</head>
<body>
	<a href=<%=path+"/personal/coachRating.action" %>>教练评价</a><br>
	<a href=<%=path+"/personal/schoolRating.action" %>>驾校评价</a><br>
	<a href=<%=path+"/personal/examSchedule.action" %>>考试安排</a><br>
</body>
</html>