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

	<a href=<%=path+"/menu/toManageMain.action?role_id=3"%>>去驾校的后台主页</a>

</body>
</html>
