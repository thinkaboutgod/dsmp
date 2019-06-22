<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>考试安排表（学员自己看的）</title>
</head>
<body>
	
	<div class="list-group">
  <a href="#" class="list-group-item list-group-item-success">考生：${examscheduleandstudent.tbStudent.stuName }</a>
  <a href="#" class="list-group-item list-group-item-warning">驾校：${examscheduleandstudent.tbExamschedule.tbSchool.schName }</a>
  <a href="#" class="list-group-item list-group-item-info">科目：${examscheduleandstudent.tbExamschedule.tbSubject.subName }</a>
  <a href="#" class="list-group-item list-group-item-danger">座位号：${examscheduleandstudent.easSeatnum }</a>
  <a href="#" class="list-group-item list-group-item-success">考试地址：${examscheduleandstudent.tbExamschedule.exsAddress }</a>
  <a href="#" class="list-group-item list-group-item-info">已报名人数/总人数：${examscheduleandstudent.tbExamschedule.exsSignupnum }/${examscheduleandstudent.tbExamschedule.exsTotalnum }</a>
  <a href="#" class="list-group-item list-group-item-warning">开始考试时间：${examscheduleandstudent.tbExamschedule.exsTime }</a>
  <a href="#" class="list-group-item list-group-item-danger">考试截止时间：${examscheduleandstudent.tbExamschedule.exsEndtime }</a>
</div>
</body>
</html>