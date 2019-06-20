<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>各个科目成绩</title>
</head>
<body>
	<table class="table table-hover">
			<tr>
				<td>科目</td>
				<td>学员</td>
				<td>成绩</td>
			</tr>
		<c:forEach begin="0" items="${subjectscoreList }" var="i" varStatus="status">
<%-- 			<c:if test=" ${status.index%2==0}"><tr class="active"></c:if> --%>
<%-- 			<c:if test=" ${status.index%2!=0}"><tr class="success"></c:if> --%>
			<tr class="success">
			
				<td>${i.tbSubject.subName }</td>
				<td>${i.tbStudent.stuName }</td>
				<td>${i.susScore }</td>
			</tr>
		</c:forEach>
  
	</table>
</body>
</html>