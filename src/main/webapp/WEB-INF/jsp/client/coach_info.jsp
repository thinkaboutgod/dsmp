<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = application.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${tbCoach.coaName}</title>
<link rel='stylesheet' href=<%=path+"/layui/css/layui.css" %>>
<link rel='stylesheet' href=<%=path+"/css/jx_detail.css" %>>
<link rel="stylesheet" href=<%=path + "/css/home/bootstrap.css"%>>
<link rel="stylesheet" href=<%=path + "/css/allschool/footer.css"%>>
<link rel="stylesheet" href=<%=path + "/css/home/comm_style.css"%>>
<link href=<%=path + "/css/home/css.css"%> rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href=<%=path + "/css/home/animate.css"%>
	type="text/css">
<link href=<%=path + "/css/home/green.css"%> title="" rel="stylesheet"
	type="text/css" />
<link href=<%=path + "/css/guide/jxwz.css"%> title="" rel="stylesheet"
	type="text/css" />

<script type="text/javascript"
	src=<%=path + "/js/home/jquery1.42.min.js"%>></script>
<script type="text/javascript"
	src=<%=path + "/js/home/jquery.superslide.2.1.1.js"%>></script>
<script type="text/javascript" src=<%=path + "/js/home/aos.js"%>></script>
<script src=<%=path+"/layui/layui.js" %> charset="utf-8"></script>
<script type="text/javascript" src=<%=path + "/js/school/infolayui.js"%>></script>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div class="jld-wrap" style="padding-top: 50px; padding-bottom: 50px;">
		<p class="top">
			位置： <a rulname="全部" href="">全部教练 </a>&gt;${tbCoach.coaName}
		</p>
		<div class="detail-nav">
			<div class="detail-img">
				<img src=<%=path+"/images/coach/jlhead.jpg" %> alt="">
			</div>
			<div class="detail-info">
				<div class="info-name">
					<font>${tbCoach.coaName}</font>
				</div>
				<div class="info-school">
					班型： <span>c1/c2</span>
				</div>
				<div class="info-phone">
					联系电话： <span>${tbCoach.coaAccount}</span>
				</div>
				<div class="info-address" style="margin-top: 10px;">
					所属驾校： <span>${tbSchool.schName}</span>
				</div>
				<div class="info-address">
					驾校地址： <span>${tbSchool.schAddress}</span>
				</div>				
			</div>
		</div>
		<div class="tab-head">
			<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
				<ul class="layui-tab-title">
					<li class="layui-this">教练简介</li>
					<li>驾校信息</li>
					<li>报名须知</li>
				</ul>
				<div class="layui-tab-content" style="height: 100%;">
					<div class="layui-tab-item layui-show">${tbCoach.coaIntroduction}</div>
					<div class="layui-tab-item">
						<div class="tab-content-each tc-info">
							<table>
								<tr class="tc-info-class-top">
									<td class="tc-top-class">驾校名称</td>
									<td class="tc-top-cost">联系方式</td>
									<td class="tc-top-type">驾校地址</td>
									<td class="tc-top-car">驾照类型</td>
									<td class="tc-top-car">报名费用</td>
								</tr>
								<tr>
									<td>${tbSchool.schName}</td>
									<td>${tbSchool.schAccount}</td>
									<td>${tbSchool.schAddress}</td>
									<td>${tbCoach.coaLevel}</td>
									<td>${tbSchool.schCharge}</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="layui-tab-item">
						<div class="tc-info-rules">
							<p class="rules-title">报名须知:</p>
							<p class="rules-each">1.申请小型汽车、小型自动挡汽车、轻便摩托车准驾车型的，在18周岁以上，70周岁以下。</p>
							<p class="rules-each">2.申请低速载货汽车、三轮汽车、普通三轮摩托车、普通二轮摩托车或者轮式自行机械车准驾车型的，在18周岁以上，60周岁以下。</p>
							<p class="rules-each">3.申请城市公交车、中型客车、大型货车、无轨电车或者有轨电车准驾车型的，在21周岁以上，50周岁以下。</p>
							<p class="rules-each">4.申请牵引车准驾车型的，在24周岁以上，50周岁以下。</p>
							<p class="rules-each">5.申请大型客车准驾车型的，在26周岁以上，50周岁以下。</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="tail.jsp"></jsp:include>
</body>
</html>