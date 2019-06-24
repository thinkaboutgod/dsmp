<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = application.getContextPath();
%>
<header class="main-header">
 <!-- Logo -->
    <a href="#" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
<!--       <span class="logo-mini"><b>A</b>LT</span> -->
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>${title}</b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
 <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button收放目录栏按钮-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">切换导航</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          
          <!-- User Account: style can be found in dropdown.less -->
          <li>
            <a href="#" data-toggle="control-sidebar">欢迎您：${sessionScope.the_name}</a>
          </li>
          
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href=<%=path+"/menu/out.action"%> >退出</a>
          </li>
        </ul>
      </div>
    </nav>
 </header>
 
