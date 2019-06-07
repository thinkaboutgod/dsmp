<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	String path = request.getContextPath();
%>
</head>
<body>
<%-- 		<a href=<%=path+"/menu/findTopic.action?top_id=1"%>>查询一道题目</a> --%>
		<a href=<%=path+"/menu/findManyTopic.action"%>>查询多道题目</a>
</body>
</html>