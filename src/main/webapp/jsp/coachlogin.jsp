<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>教练登陆</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/coachlogin.action" method="post">
		<input type="text" id="coa_account"  name="coaId">
		<input type="password" id="coa_password" name="coaPassword">
		<input type="submit" value="登陆" >
	</form>
</body>
</html>