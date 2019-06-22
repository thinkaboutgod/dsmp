<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目三的学习记录查看</title>
</head>
<body>
		
	<table class="table table-hover">
			<tr>
				<td>序号</td>
				<td>科目</td>
				<td>学员</td>
				<td>开始学习时间</td>
				<td>结束学习时间</td>
				<td>时长</td>
			</tr>
		<c:forEach begin="0" items="${studyRecord3List }" var="i" varStatus="status">
			<tr class="success">
				<td>${status.index+1}</td>
				<td>${i.tbSubject.subName }</td>
				<td>${i.tbStudent.stuName }</td>
				<td>${i.strBegintime }</td>
				<td>${i.strEndtime }</td>
				<td>${i.strTime }</td>
			</tr>
		</c:forEach>
  
	</table>
</body>
</html>