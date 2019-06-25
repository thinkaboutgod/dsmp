<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = application.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>机动车驾驶员计时培训服务平台</title>
<link rel="stylesheet" href=<%=path + "/css/home/bootstrap.css"%>>
<link rel="stylesheet" href=<%=path + "/css/home/comm_style.css"%>>
<link rel="stylesheet" href=<%=path + "/css/home/jx_index.css"%>>
<link href=<%=path + "/css/home/css.css"%> rel="stylesheet"
	type="text/css" />
<link href=<%=path + "/css/home/css3.css"%> rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href=<%=path + "/css/home/aos.css"%>
	type="text/css" />
<link rel="stylesheet" href=<%=path + "/css/home/animate.css"%>
	type="text/css">
<link href=<%=path + "/css/home/green.css"%> title="" rel="stylesheet"
	type="text/css" />
<link href=<%=path + "/css/home/footer.css"%> title="" rel="stylesheet"
	type="text/css" />

<%-- <link type="text/css" href="<%=path %>/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">	 --%>

<link type="text/css" rel="stylesheet" href=<%=path+"/adminlte/css/style.css"%> media="screen"/>


<script type="text/javascript" src=<%=path+"/bootstrap-3.3.7-dist/js/bootstrap.min.js"%>></script>

<script type="text/javascript"
	src=<%=path + "/js/home/jquery1.42.min.js"%>></script>
<script type="text/javascript"
	src=<%=path + "/js/home/jquery.superslide.2.1.1.js"%>></script>
<script type="text/javascript"
	src=<%=path + "/js/school_ranking.js"%>></script>
<script type="text/javascript"
	src=<%=path + "/js/coach_ranking.js"%>></script>
 <script src="http://api.map.baidu.com/api?v=1.4" type="text/javascript"></script>
<script type="text/javascript"
	src=<%=path + "/js/contactusmap.js"%>></script>
<!-- <script type="text/javascript" -->
<%-- 	src=<%=path + "/js/home/head.js"%>></script> --%>
<script type="text/javascript" src=<%=path + "/js/home/common_footer.js"%>></script>
	
<script type="text/javascript" src=<%=path + "/js/jiqiren.js"%> ></script>



</head>
<body>
<input type="hidden" id="path" value=<%=path %>>

<div class="hiddenDiv">
	<input type="hidden" name="" value="">
</div>

	<jsp:include page="head.jsp"></jsp:include>
	<!-- banner -->
	<div class="focusBox clear pulse animated">
		<ul class="pic">
			<li><a href="javascript:;" target="_blank"
				style="background: url(<%=path + "/images/home/title1.jpg"%>) no-repeat center"></a></li>
			<li><a href="javascript:;" target="_blank"
				style="background: url(<%=path + "/images/home/title4.jpg"%>) no-repeat center"></a></li>
			<li><a href="javascript:;" target="_blank"
				style="background: url(<%=path + "/images/home/1-1P52Q1113R51.jpg"%>) no-repeat center"></a></li>			
		</ul>
		<a class="prev" href="javascript:;"></a> <a class="next"
			href="javascript:;"></a>
		<ul class="hd">
			<li></li>
			<li></li>
			<li></li>
		</ul>
		<div class="banner banner_top">
			<div class="banner_process">
				<ul>
					<h3>学车流程</h3>
					<li class="km1li"><a href=<%=path+"/home/keyi.action" %> target="_blank">
							<div class="km1"></div>
							<div>
								<p>科目一</p>
								<p>真实模拟，通过率高</p>
							</div>
					</a></li>
					<li class="km2li"><a href=<%=path+"/home/kemu.action?KeMuNum=2" %> target="_blank">				
							<div class="km2"></div>
							<div>
								<p>科目二</p>
								<p>桩考、小路，知识及技巧</p>
							</div>
					</a></li>
					<li class="km3li"><a
						href=<%=path+"/home/kemu.action?KeMuNum=3" %> target="_blank">
							<div class="km3"></div>
							<div>
								<p>科目三</p>
								<p>大路，知识及技巧</p>
							</div>
					</a></li>
					<li class="km4li"><a href="" target="_blank" >
							<div class="km4"></div>
							<div>
								<p>科目四</p>
								<p>2019题库、题新、题准</p>
							</div>
					</a></li>
				</ul>
				
			</div>
		</div>
	</div>
	<script type="text/javascript">
		/*鼠标移过，左右按钮显示*/
		jQuery(".focusBox").hover(
				function() {
					jQuery(this).find(".prev,.next").stop(true, true).fadeTo(
							"show", 0.2)
				}, function() {
					jQuery(this).find(".prev,.next").fadeOut()
				});
		/*SuperSlide图片切换*/
		jQuery(".focusBox").slide({
			mainCell : ".pic",
			autoPlay : true,
			delayTime : 600,
			trigger : "click"
		});
	</script>

	<!--notice-->
	<div class="noticebox">
		<div class="nbox m_m" aos="fade-right">
			<div class="txtMarquee-left fl">
				<em>最新公告：</em>
				<div class="bd">
					<ul class="noticeList">
						<c:forEach begin="0" step="1" items="${notList}" var="notice">
							<li><a href=<%=path%>${notice.notPath}${notice.notId}  target="_blank">${notice.notTitle}</a><span><fmt:formatDate value="${notice.notTime}" pattern="YYYY-MM-dd" /></span></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<script type="text/javascript">
				jQuery(".txtMarquee-left").slide({
					mainCell : ".bd ul",
					autoPlay : true,
					effect : "leftMarquee",
					vis : 2,
					interTime : 50
				});
			</script>
			<div class="search fr">
				<form name="formsearch" method="get" action=<%=path+"/school/selectSchoolByNamePage.action" %>  target="_blank">
					<input type="hidden" name="kwtype" value="0" /> <input
						class="search-input" id="q" name="q"
						placeholder="搜索驾校" type="text" style="color: gray">
					<button class="btn-search" type="submit"></button>
				</form>
			</div>
		</div>
		<hr>
	</div>
	<!--about us-->
	<div class="aboutkk m_m p_t20">
		<div class="aboutbox fl" aos="fade-right">
			<div class="aboutlm">
				<span><em>驾校教练排行</em>&nbsp;&nbsp;<i>ABOUTUS</i></span><a
					href="javascript:;">+&nbsp;更多/MORE</a>
			</div>
			<div class="content p_t15">
				<div class="jx_pl_rank">
					<div class="rank_card_jx">
					<div class="rank_card_header">驾校排行榜</div>
 					<div class="rank_card_body" id="rank_jx">					
						
 					</div>
						<div class="rank_card_footer">
							<a href=<%=path+"/school/allSchoolPage.action" %> target="_blank">查看更多</a>
						</div>
					</div>
					<div class="rank_card_jl">
						<div class="rank_card_header">教练排行榜</div>
				    <div class="rank_card_body" id="rank_jl">
						
					</div> 
						<div class="rank_card_footer">
							<a href=<%=path+"/tbcoach/allCoachPage.action" %> target="_blank">查看更多</a>
						</div>
					</div>
				</div>

			</div>
		</div>
		<!--HOT products-->

		<div class="hotpic fr" aos="fade-left">
			<h2>驾校推荐</h2>
			<div class="hotshow zoomimg">
				<ul>
					<c:forEach begin="0" end="5" step="1" items="${advList}" var="adv">
						<li>
							<div class="pic">
								<a href=<%=path%>${adv.advPath}${adv.tbSchool.schId} target="_blank"><img
									src="${systemFilePath}${adv.advImgpath}" alt="${adv.tbSchool.schName}" title='${adv.advDescribe}'/></a>
							</div>
							<div class="title">
								<a href=<%=path%>${adv.advPath}${adv.tbSchool.schId} target="_blank">${adv.tbSchool.schName}</a>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
			<script type="text/javascript">
				jQuery(".hotpic").slide({
					titCell : "",
					mainCell : ".hotshow ul",
					autoPage : true,
					effect : "top",
					autoPlay : true,
					vis : 2,
					trigger : "click"
				});
			</script>
		</div>

	</div>
	<!--横幅-->
	<div class="row" style="margin-top:50px;">
	<div class="col-md-12" aos="flip-left" >
		<a href=<%=path+"/school/schoolEnterPage.action" %> target="_blank">
			<img src=<%=path + "/images/home/jxrz_index.png"%> style="width: 100%;">
		</a>

	</div>
	</div>
	<!--文字栏-->
	<div class="wordbox">
		<div class="wordnr m_m zoomimg">
			<dl class="m_r30">
				<div class="wzlm">
					<span><em>行业动态</em></span><a href="javascript:;">+&nbsp;更多/MORE</a>
				</div>
				<dt>
					<img src=<%=path + "/images/home/1-1P5261G92Wb.jpg"%> alt="公司动态" />
				</dt>
					<c:forEach begin="0" step="1" end="4" items="${dynList}" var="dynamic">
					<dd>
						<a href=<%=path%>${dynamic.notPath}${dynamic.notId} target="_blank">${dynamic.notTitle}</a>
					</dd>							
					</c:forEach>			
			</dl>
			<dl class="m_r31">
				<div class="wzlm">
					<span><em>行业新闻</em></span><a href="javascript:;">+&nbsp;更多/MORE</a>
				</div>
				<dt>
					<img src=<%=path + "/images/home/1-1P5261GZ6458.jpg"%> alt="行业新闻" />
				</dt>
				<c:forEach begin="0" step="1"  end="4" items="${newsList}" var="news">
					<dd>
						<a href=<%=path%>${news.notPath}${news.notId} target="_blank">${news.notTitle}</a>
					</dd>							
				</c:forEach>
			</dl>
			<dl class="m_r32">
				<div class="wzlm">
					<span><em>政策法规</em></span><a href="javascript:;">+&nbsp;更多/MORE</a>
				</div>
				<dt>
					<img src=<%=path + "/images/home/1-1P5261GU1252.jpg"%> alt="常见问题" />
				</dt>
				<c:forEach begin="0" step="1"  end="4" items="${lawsList}" var="laws">
					<dd>
						<a href=<%=path%>${laws.notPath}${laws.notId} target="_blank">${laws.notTitle}</a>
					</dd>							
				</c:forEach>
			</dl>
		</div>		
	</div>
	<!-- 智能机器人在线问答 -->
	<div class="productshow">
		<h1>智能客服在线问答&nbsp;/&nbsp;Intelligent chat</h1>
	</div>
	<div style="height:400px;" id="smartchat">
		<div class="entry-thumbnail" id="main">               
            <div>
				<ul id="chat" class="chat-thread" style="width: 100%">
					<li role="me">有什么能帮助您的?</li>											
				</ul>										  
            </div>
            <input id="question" type="text" style="width:80%">
            <input id="robot" style="width:18%;" type="button" class="btn btn-info" value="发送" >
    	</div>
    </div>
	<!--产品展示-->
	<div class="productshow">
		<h1>联系我们&nbsp;/&nbsp;CONTACT US</h1>
	</div>
	<div class="productbox m_m p_t20">
		<input type="hidden" id="cityName" value="厦门传一信息科技有限公司" />
    <div id="container" style="width: 100%;height: 615px;"></div>
      		
	</div>
	<ul class="right-nav-common" style="display: none;">				
				<li class="service">
					<div class="service-icon"></div>
					<div class="service-alert">
						<span>0592-8888888</span>
					</div>										
				</li>
				<li class="gotop">
					<div class="gotop-icon"></div>
					<div class="gotop-alert"><span>回到顶部</span></div>
				</li>
			</ul>
	<jsp:include page="tail.jsp"></jsp:include>
	<script type="text/javascript" src=<%=path + "/js/home/aos.js"%>></script>
	<script type="text/javascript">
		AOS.init({
			easing : 'ease-out-back',
			duration : 1000
		});
	</script>
	
</body>
</html>