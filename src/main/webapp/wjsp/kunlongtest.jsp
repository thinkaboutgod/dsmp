<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	String path = request.getContextPath();
%>
<body>
	<a href=<%=path+"/tocoachmain.action?role_id=4"%>>去教练主页</a>
</body>
</html>