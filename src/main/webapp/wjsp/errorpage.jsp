<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>出错啦</title>
<!--meta tags -->
<%
	String path = request.getContextPath();
%>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="Stamp error page Widget a Flat Responsive Widget,Login form widgets, Sign up Web forms , Login signup Responsive web form,Flat Pricing table,Flat Drop downs,Registration Forms,News letter Forms,Elements" />
<!-- Meta tag Keywords -->
<%-- <link rel="stylesheet" href="<%=path %>/css/errorstyle.css" type="text/css” media="all" /> <!-- Style-CSS --> --%>
<link type="text/css" href="<%=path %>/css/errorstyle.css" rel="stylesheet">
<!-- <link href="//fonts.googleapis.com/css?family=Slabo+27px&amp;subset=latin-ext" rel="stylesheet"> -->
<!-- <link href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet"> -->

</head>

<body>
<div class="bubble"></div>
<div class="bubble"></div>
<div class="bubble"></div>
<div class="bubble"></div>
<div class="bubble"></div>
<div class="main">
  <h1>发生错误啦！</h1>
  <p>发生了一些未知错误<br/></p>
  <button type="button"><a href=<%=path+"/home/main.action?"%>>返回主页</a></button>
  <div class="copyright">
			
		</div>
  </div>
</body>

</html>