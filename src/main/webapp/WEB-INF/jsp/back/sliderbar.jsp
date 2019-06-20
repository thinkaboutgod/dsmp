<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = application.getContextPath();
%>
<aside class="main-sidebar  ">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
    <ul class="sidebar-menu">
     
       <c:forEach items="${menuMap}" begin="0" step="1" var="i">
     	<li class="treeview">
          <a href="#">
            <i class="fa ${i.key.menImg}"></i> <span>${i.key.menName}</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
          	<c:forEach items="${i.value}" begin="0" step="1" var="t">
            	<li><a href=<%=path%>${t.menUrl}><i class="fa fa-circle-o"></i> ${t.menName}</a></li>
            </c:forEach>
          </ul>
        </li>
      </c:forEach>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>