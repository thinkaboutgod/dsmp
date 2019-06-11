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
<%-- 		<a href=<%=path+"/topic/findTopic.action?top_id=1"%>>查询一道题目</a> --%>
		<a href=<%=path+"/topic/findManyTopic.action?stu_id=2&sub_id=1"%>>查询多道题目(已登陆)</a>
		<a href=<%=path+"/topic/findManyTopic.action"%>>查询多道题目(未登录情况下:不会产生错题集，不计入学习时长)</a>
		<a href=<%=path+"/topic/findAllTopic.action"%>>查询所有题目，但页面上是一题题显示(未登录情况下)</a>
		<a href=<%=path+"/topic/findMistakeTopic.action?stu_id=2&sub_id=1"%>>查询学员做错题目(已登陆)</a>
		<a href=<%=path+"/topic/findMistakeTopic.action"%>>查询学员做错题目(未登录情况下)</a>
</body>
</html>