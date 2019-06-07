<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<% 
	String path = application.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>机动车驾驶员计时培训服务平台</title>
<link rel="stylesheet" href=<%=path+"/css/home/bootstrap.css"%>>
<link rel="stylesheet" href=<%=path+"/css/home/comm_style.css"%>>
<link rel="stylesheet" href=<%=path+"/css/home/jx_index.css"%>>
<link href=<%=path+"/css/home/css.css"%> rel="stylesheet" type="text/css" />
<link href=<%=path+"/css/home/css3.css"%> rel="stylesheet" type="text/css" />
<link rel="stylesheet" href=<%=path+"/css/home/aos.css"%> type="text/css" />
<link rel="stylesheet" href=<%=path+"/css/home/animate.css"%> type="text/css">
<link href=<%=path+"/css/home/green.css"%> title="" rel="stylesheet" type="text/css" />
<script type="text/javascript" src=<%=path+"/js/home/jquery1.42.min.js"%>></script>
<script type="text/javascript" src=<%=path+"/js/home/jquery.superslide.2.1.1.js"%>></script>

</head>
<body>
	<div class="dowebok-explain">您的浏览器不支持CSS3属性，所以您看不到更好的效果，请使用
		Firefox、Chrome 或 IE10及以上版本，来体验更好的效果</div>
	<!--提示版本end-->
	<div class="nav-wrap">
		<nav class="comment-header-nav">
			<!-- 联系 -->
			<div class="comment-header-contact">
				<div class="comment-header-left">
					<!--<span class="VTargetSpan"></span>-->
					<!--<img class="comment-header-vip-img" src="" alt="">-->
					<span class="comment-header-phone-describe">您好，欢迎来到厦门机动车驾培公众服务平台！</span>
				</div>
				<div class="comment-header-right">
					<a class="right-text" href=<%=path+"/wjsp/login.jsp"%>
						onclick="clickLog('from=JXEDT_HEADER_LOGIN')">登录</a> <a
						class="right-text" href="http://user.jxedt.com/registerNew/xy"
						target="_blank" onclick="clickLog('from=JXEDT_HEADER_SIGNIN')">注册</a>
					<a class="right-text" href="http://user.jxedt.com/registerNew/jx">驾校入驻</a>
					<a class="right-text" href="jiaxiao.html">管理后台</a>
				</div>
			</div>
		</nav>
	</div>

	<div class="toptwo m_m">
		<div class="logo fl slideInDown animated">
			<a href="javascript:;"><img src=<%=path+"/images/home/logo.jpg"%> /></a>
		</div>
		<div class="phone fr slideInDown animated">
			<em>服务热线&nbsp;Service Hotline</em>
			<p>0896-8734688834&nbsp;/&nbsp;8734688834</p>
		</div>
	</div>
	<!--nav-->
	<div class="navBar bounce animated">
		<ul class="nav clearfix m_m">
			<li class="m cur">
				<h3>
					<a href="javascript:;">首页</a>
				</h3>
			</li>
			<li class="m "">
				<h3>
					<a href=" javascript:;">模拟考试</a>
				</h3>
				<ul class="sub">

					<li><a href="javascript:;">科目一</a></li>

					<li><a href="javascript:;">科目四</a></li>

				</ul>
			</li>
			<li class="m "">
				<h3>
					<a href=" javascript:;">驾校</a>
				</h3>
				<ul class="sub">

					<li><a href="javascript:;">第一系列</a></li>

					<li><a href="javascript:;">第二系列</a></li>

					<li><a href="javascript:;">第三系列</a></li>

					<li><a href="javascript:;">第四系列</a></li>

					<li><a href="javascript:;">第五系列</a></li>

					<li><a href="javascript:;">第六系列</a></li>

				</ul>
			</li>
			<li class="m "">
				<h3>
					<a href=" javascript:;">教练</a>
				</h3>
				<ul class="sub">

					<li><a href="javascript:;">公司动态</a></li>

					<li><a href="javascript:;">行业新闻</a></li>

					<li><a href="javascript:;">常见问题</a></li>

				</ul>
			</li>
			<li class="m "">
				<h3>
					<a href=" javascript:;">学员在线报名</a>
				</h3>
				<ul>

				</ul>
			</li>
			<li class="m "">
				<h3>
					<a href=" javascript:;">荣誉资质</a>
				</h3>
				<ul>

				</ul>
			</li>
			<li class="m "">
				<h3>
					<a href=" javascript:;">人才招聘</a>
				</h3>
				<ul>

				</ul>
			</li>
			<li class="m "">
				<h3>
					<a href=" javascript:;">联系我们</a>
				</h3>
				<ul>

				</ul>
			</li>
		</ul>
	</div>
	<script type="text/javascript">
		jQuery(".nav").slide({
			type : "menu", //效果类型
			titCell : ".m", // 鼠标触发对象
			targetCell : ".sub", // 效果对象，必须被titCell包含
			effect : "slideDown", //下拉效果
			delayTime : 300, // 效果时间
			triggerTime : 0, //鼠标延迟触发时间
			returnDefault : true
		//返回默认状态
		});
	</script>
	<!-- banner -->
	<div class="focusBox clear pulse animated">
		<ul class="pic">
			<li><a href="javascript:;" target="_blank"
				style="background: url(<%=path+"/images/home/title1.png"%>) no-repeat center"></a></li>
			<li><a href="javascript:;" target="_blank"
				style="background: url(+2) no-repeat center"></a></li>
			<li><a href="javascript:;" target="_blank"
				style="background: url(<%=path+"/images/home/1-1P52Q1113R51.jpg"%>) no-repeat center"></a></li>
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
					<li class="km1li"><a href="video.html" target="_blank"
						onclick="clickLog('from=JXEDT_HOME_XCLC_KM1')">
							<div class="km1"></div>
							<div>
								<p>科目一</p>
								<p>真实模拟，通过率高</p>
							</div>
					</a></li>
					<li class="km2li"><a href="keer.html" target="_blank"
						onclick="clickLog('from=JXEDT_HOME_XCLC_KM2')">
							<div class="km2"></div>
							<div>
								<p>科目二</p>
								<p>桩考、小路，知识及技巧</p>
							</div>
					</a></li>
					<li class="km3li"><a
						href="http://tv.jxedt.com/?kemuType=kemu3" target="_blank"
						onclick="clickLog('from=JXEDT_HOME_XCLC_KM3')">
							<div class="km3"></div>
							<div>
								<p>科目三</p>
								<p>大路，知识及技巧</p>
							</div>
					</a></li>
					<li class="km4li"><a href="http://mnks.jxedt.com/ckm4/"
						target="_blank" onclick="clickLog('from=JXEDT_HOME_XCLC_KM4')">
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
						<li><a href="javascript:;">独家 | 传滴滴即将接入ofo，共享单车大战格局或生变</a><span>[2018/05/28]</span></li>
						<li><a href="javascript:;">点击在线求助，应答的却都是机器人，这样真的好吗？</a><span>[2018/05/28]</span></li>
						<li><a href="javascript:;">美业信息化规模将现，门庭管店为他们提供了一套SaaS管理软件</a><span>[2018/05/28]</span></li>
						<li><a href="javascript:;">腾讯500万美元领投AI创企ObEN，进一步布局社交AI</a><span>[2018/05/28]</span></li>

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
				<form name="formsearch" method="get" action="/so.php">
					<input type="hidden" name="kwtype" value="0" /> <input
						class="search-input" id="q" name="q" ng-model="keyword"
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
					href="javascript:;" target="_blank">+&nbsp;更多/MORE</a>
			</div>
			<div class="content p_t15">
				<div class="jx_pl_rank">
					<div class="rank_card_jx">
						<div class="rank_card_header">驾校排行榜</div>
						<!-- <div class="rank_card_body">
					<div class="rank_first">
					<div class="rank_data">
					<div class="rank_first_jx">
					<a href="http://jiaxiao.jxedt.com/1116249710575800322/" target="_blank">
					<img src="%E5%8E%A6%E9%97%A8%E9%A9%BE%E6%A0%A1_%E5%8E%A6%E9%97%A8%E9%A9%BE%E6%A0%A1%E4%B8%80%E7%82%B9%E9%80%9A_%E5%8E%A6%E9%97%A8%E5%AD%A6%E8%BD%A6_%E5%8E%A6%E9%97%A8%E9%99%AA%E9%A9%BE_%E5%8E%A6%E9%97%A8%E9%99%AA%E7%BB%83_files/n_v2763ac63a14e443f1b88f8e51216ebdac.png" alt="">
					</a>
					</div>
					<div class="info">
					<div class="jx-info-name"><a href="http://jiaxiao.jxedt.com/1116249710575800322/" title="和胜驾校岛内分校" target="_blank">和胜驾校岛内分校</a></div>
					<div>
					<img src="%E5%8E%A6%E9%97%A8%E9%A9%BE%E6%A0%A1_%E5%8E%A6%E9%97%A8%E9%A9%BE%E6%A0%A1%E4%B8%80%E7%82%B9%E9%80%9A_%E5%8E%A6%E9%97%A8%E5%AD%A6%E8%BD%A6_%E5%8E%A6%E9%97%A8%E9%99%AA%E9%A9%BE_%E5%8E%A6%E9%97%A8%E9%99%AA%E7%BB%83_files/star5.png" alt="">
					</div>
					<div>
					<span>41点评</span>
					</div>
					</div>
					</div>
					</div>
					<ul class="rank_first_jx_ul">
					<li>
					    <a href="http://jiaxiao.jxedt.com/1079618949718528000/" title="鹭盈盛驾校集美分校" target="_blank"><div>鹭盈盛驾校集美分校</div><div>
					<img src="%E5%8E%A6%E9%97%A8%E9%A9%BE%E6%A0%A1_%E5%8E%A6%E9%97%A8%E9%A9%BE%E6%A0%A1%E4%B8%80%E7%82%B9%E9%80%9A_%E5%8E%A6%E9%97%A8%E5%AD%A6%E8%BD%A6_%E5%8E%A6%E9%97%A8%E9%99%AA%E9%A9%BE_%E5%8E%A6%E9%97%A8%E9%99%AA%E7%BB%83_files/star5.png" alt=""></div>
					<div>66点评</div>    </a>
					</li>
					</ul>
					</div> -->
						<div class="rank_card_footer">
							<a href="http://jiaxiao.jxedt.com/xiamen" target="_blank">查看更多</a>
						</div>
					</div>
					<div class="rank_card_jl">
						<div class="rank_card_header">教练排行榜</div>
						<!-- <div class="rank_card_body">
					<div class="rank_first">
					<div class="rank_data">
					<div class="rank_first_jl">
					<a href="http://jl.jxedt.com/1024269994067775488/" title="罗少华" target="_blank">
					<img src="%E5%8E%A6%E9%97%A8%E9%A9%BE%E6%A0%A1_%E5%8E%A6%E9%97%A8%E9%A9%BE%E6%A0%A1%E4%B8%80%E7%82%B9%E9%80%9A_%E5%8E%A6%E9%97%A8%E5%AD%A6%E8%BD%A6_%E5%8E%A6%E9%97%A8%E9%99%AA%E9%A9%BE_%E5%8E%A6%E9%97%A8%E9%99%AA%E7%BB%83_files/n_v25639b3f253d04c3e9ff3140aa97d7388_0806078df64b9c19.jpg" alt="">
					</a>
					</div>
					<div class="info">
					<div>
					<div class="info-name"><a href="http://jl.jxedt.com/1024269994067775488/" title="罗少华" target="_blank">罗少华</a></div>
					<div class="info-old">7年驾龄</div>
					</div>
					<div>
					<span>所属驾校：勋联鸿驾校</span>
					</div>
					<div>
					<img src="%E5%8E%A6%E9%97%A8%E9%A9%BE%E6%A0%A1_%E5%8E%A6%E9%97%A8%E9%A9%BE%E6%A0%A1%E4%B8%80%E7%82%B9%E9%80%9A_%E5%8E%A6%E9%97%A8%E5%AD%A6%E8%BD%A6_%E5%8E%A6%E9%97%A8%E9%99%AA%E9%A9%BE_%E5%8E%A6%E9%97%A8%E9%99%AA%E7%BB%83_files/star5.png" alt="">
					</div>
					</div>
					</div>
					</div>
					<ul class="rank_first_jl_ul">
					<li>
					<a href="http://jl.jxedt.com/3173204679622066258/" title="石金泉" target="_blank"><div>石金泉</div><div>
					<img src="%E5%8E%A6%E9%97%A8%E9%A9%BE%E6%A0%A1_%E5%8E%A6%E9%97%A8%E9%A9%BE%E6%A0%A1%E4%B8%80%E7%82%B9%E9%80%9A_%E5%8E%A6%E9%97%A8%E5%AD%A6%E8%BD%A6_%E5%8E%A6%E9%97%A8%E9%99%AA%E9%A9%BE_%E5%8E%A6%E9%97%A8%E9%99%AA%E7%BB%83_files/star5.png" alt=""></div>
					</a>
					</li>
					</ul>
					</div> -->
						<div class="rank_card_footer">
							<a href="http://jl.jxedt.com/xiamen" target="_blank">查看更多</a>
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
					<li>
						<div class="pic">
							<a href="javascript:;" target="_blank"><img
								src=<%=path+"/images/home/jx_xwyd.png"%> alt="产品名称五" /></a>
						</div>
						<div class="title">
							<a href="javascript:;" target="_blank">XXXX驾校</a>
						</div>
					</li>
					<li>
						<div class="pic">
							<a href="javascript:;" target="_blank"><img
								src=<%=path+"/images/home/jx_xwyd.png"%> alt="产品名称四" /></a>
						</div>
						<div class="title">
							<a href="javascript:;" target="_blank">XXXX驾校</a>
						</div>
					</li>
					<li>
						<div class="pic">
							<a href="javascript:;" target="_blank"><img
								src=<%=path+"/images/home/jx_xwyd.png"%> alt="产品名称三" /></a>
						</div>
						<div class="title">
							<a href="javascript:;" target="_blank">XXXX驾校</a>
						</div>
					</li>
					<li>
						<div class="pic">
							<a href="javascript:;" target="_blank"><img
								src=<%=path+"/images/home/jx_xwyd.png"%> alt="产品名称二" /></a>
						</div>
						<div class="title">
							<a href="javascript:;" target="_blank">XXXX驾校</a>
						</div>
					</li>

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

	<!--产品展示-->
	<div class="productshow">
		<h1>产品中心&nbsp;/&nbsp;PRDUCTS DISPLAY</h1>
	</div>
	<div class="productbox m_m p_t20">
		<div class="prod_left fl" aos="fade-right">
			<span>产品分类</span>
			<ul>

				<li><a href="javascript:;">第一系列</a></li>

				<li><a href="javascript:;">第二系列</a></li>

				<li><a href="javascript:;">第三系列</a></li>

				<li><a href="javascript:;">第四系列</a></li>

				<li><a href="javascript:;">第五系列</a></li>

				<li><a href="javascript:;">第六系列</a></li>

			</ul>
			<a href="javascript:;" target="_blank" class="classmore">+&nbsp;查看更多</a>
		</div>
		<div class="showcp fr zoomimg" aos="fade-left">
			<ul>
				<li><a href="javascript:;" target="_blank"><img
						src="images/1-1P52Q05Z20-L.jpg" alt="产品名称六" /></a>
					<p>
						<a href="javascript:;" target="_blank">产品名称六</a>
					</p></li>
				<li><a href="javascript:;" target="_blank"><img
						src="images/1-1P52Q05S70-L.jpg" alt="产品名称五" /></a>
					<p>
						<a href="javascript:;" target="_blank">产品名称五</a>
					</p></li>
				<li><a href="javascript:;" target="_blank"><img
						src="images/1-1P52Q05Q60-L.jpg" alt="产品名称四" /></a>
					<p>
						<a href="javascript:;" target="_blank">产品名称四</a>
					</p></li>
				<li><a href="javascript:;" target="_blank"><img
						src="images/1-1P52Q05K30-L.jpg" alt="产品名称三" /></a>
					<p>
						<a href="javascript:;" target="_blank">产品名称三</a>
					</p></li>
				<li><a href="javascript:;" target="_blank"><img
						src="images/1-1P52Q05I10-L.jpg" alt="产品名称二" /></a>
					<p>
						<a href="javascript:;" target="_blank">产品名称二</a>
					</p></li>
				<li><a href="javascript:;" target="_blank"><img
						src="images/1-1P52Q055470-L.jpg" alt="产品名称一" /></a>
					<p>
						<a href="javascript:;" target="_blank">产品名称一</a>
					</p></li>
			</ul>
		</div>
	</div>
	<!--横幅-->
	<div class="hf" aos="flip-left">
		<a href="http://user.jxedt.com/registerNew/jx"
			onclick="clickLog('from=JXEDT_HOME_DF_GG_RZJX')" target="_blank">
			<img src=<%=path+"/images/home/jxrz_index.png"%> alt="" srcset="">
		</a>

	</div>
	<!--case-->
	<div class="caseshow">
		<h1>
			客户案例<i>&nbsp;/&nbsp;CASE</i>
		</h1>
	</div>
	<div class="casebox m_m zoomimg" aos="flip-up" aos-delay="200">
		<ul>
			<li><a href="javascript:;" target="_blank"><img
					src=<%=path+"/images/home/1-1P52Q105160-L.jpg"%> alt="案例展示八" /></a>
				<p>案例展示八</p> <a href="javascript:;" target="_blank" id="casemore">+&nbsp;查看详情</a>
			</li>
			<li><a href="javascript:;" target="_blank"><img
					src="<%=path+"/images/home/1-1P52Q104550-L.jpg"%>" alt="案例展示七" /></a>
				<p>案例展示七</p> <a href="javascript:;" target="_blank" id="casemore">+&nbsp;查看详情</a>
			</li>
			<li><a href="javascript:;" target="_blank"><img
					src="images/1-1P52Q104330-L.jpg" alt="案例展示六" /></a>
				<p>案例展示六</p> <a href="javascript:;" target="_blank" id="casemore">+&nbsp;查看详情</a>
			</li>
			<li><a href="javascript:;" target="_blank"><img
					src="images/1-1P52Q104130-L.jpg" alt="案例展示五" /></a>
				<p>案例展示五</p> <a href="javascript:;" target="_blank" id="casemore">+&nbsp;查看详情</a>
			</li>
			<li><a href="javascript:;" target="_blank"><img
					src="images/1-1P52Q103560-L.jpg" alt="案例展示四" /></a>
				<p>案例展示四</p> <a href="javascript:;" target="_blank" id="casemore">+&nbsp;查看详情</a>
			</li>
			<li><a href="javascript:;" target="_blank"><img
					src="images/1-1P52Q103350-L.jpg" alt="案例展示三" /></a>
				<p>案例展示三</p> <a href="javascript:;" target="_blank" id="casemore">+&nbsp;查看详情</a>
			</li>

		</ul>
	</div>
	<!--文字栏-->
	<div class="wordbox">
		<div class="wordnr m_m zoomimg">
			<dl class="m_r30">
				<div class="wzlm">
					<span><em>行业动态</em></span><a href="javascript:;">+&nbsp;更多/MORE</a>
				</div>
				<dt>
					<img src="image/1-1P5261G92Wb.jpg" alt="公司动态" />
				</dt>
				<dd>
					<a href="javascript:;">王健林又悄悄卖了几家万达广场！保险、信托接…</a>
				</dd>
				<dd>
					<a href="javascript:;">刮着大风的人工智能，躺着赚钱的自动驾驶</a>
				</dd>
				<dd>
					<a href="javascript:;">美业信息化规模将现，门庭管店为他们提供了一…</a>
				</dd>
				<dd>
					<a href="javascript:;">腾讯500万美元领投AI创企ObEN，进一步布局社交…</a>
				</dd>
				<dd>
					<a href="javascript:;">无人便利店风口正当时，“便利家”获联创永宣…</a>
				</dd>

			</dl>
			<dl class="m_r31">
				<div class="wzlm">
					<span><em>行业新闻</em></span><a href="javascript:;">+&nbsp;更多/MORE</a>
				</div>
				<dt>
					<img src="image/1-1P5261GZ6458.jpg" alt="行业新闻" />
				</dt>
				<dd>
					<a href="javascript:;">腾讯游戏营收比重连续两个季度下降，支付、云…</a>
				</dd>
				<dd>
					<a href="javascript:;">获 3800 万元 A+ 轮投资，乐摇摇科技利用抓娃娃…</a>
				</dd>
				<dd>
					<a href="javascript:;">借网生大势赚了两年快钱后，考拉娱乐开始切入…</a>
				</dd>
				<dd>
					<a href="javascript:;">独家 | 传滴滴即将接入ofo，共享单车大战格局或…</a>
				</dd>
				<dd>
					<a href="javascript:;">点击在线求助，应答的却都是机器人，这样真的…</a>
				</dd>

			</dl>
			<dl class="m_r32">
				<div class="wzlm">
					<span><em>政策法规</em></span><a href="javascript:;">+&nbsp;更多/MORE</a>
				</div>
				<dt>
					<img src="image/1-1P5261GU1252.jpg" alt="常见问题" />
				</dt>
				<dd>
					<a href="javascript:;">共享，正从风口到风险</a>
				</dd>
				<dd>
					<a href="javascript:;">在人工智能炒热机器人时，也被人把风带进了教…</a>
				</dd>
				<dd>
					<a href="javascript:;">阿里影业为未来增持淘票票，但眼下“烧钱”依…</a>
				</dd>
				<dd>
					<a href="javascript:;">比特币价格回稳，但考验才刚刚开始……</a>
				</dd>
				<dd>
					<a href="javascript:;">谈谈用户体验与风控的平衡性</a>
				</dd>

			</dl>
		</div>
	</div>
	<!--foot-->
	<section class="fadeInUp animated">
		<!--友情链接-->
		<div class="linkbox">
			<div class="linknr m_m">
				<span>友情链接：</span>
				<p>
					<a href='javascript:;' target='_blank'>代码库CMS</a> <a
						href='javascript:;' target='_blank'>百度</a> <a href='javascript:;'
						target='_blank'>新浪</a> <a href='javascript:;' target='_blank'>搜狐</a>
					<a href='javascript:;' target='_blank'>网易</a> <a
						href='javascript:;' target='_blank'>代码库模板</a> <a
						href='javascript:;' target='_blank'>代码库教程</a> <a
						href='javascript:;' target='_blank'>虚拟主机</a>
				</p>
			</div>
		</div>
		<div class="footend">
			<p class="m_m">
				版权所有：Copyright ? 2006-2014 dmakucms.com <a href="javascript:;"
					target="_blank">Power by dmakuCms</a>&nbsp;&nbsp;&nbsp;&nbsp;技术支持：<a
					href="javascript:;" target="_blank">传一科技</a>&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="javascript:;" target="_blank">闽ICP备8734688834号</a>
			</p>
		</div>
	</section>
	<script type="text/javascript" src=<%=path+"/js/home/aos.js"%>></script>
	<script type="text/javascript">
			AOS.init({
				easing: 'ease-out-back',
				duration: 1000
			});
		</script>
	<script>
			$('.hero__scroll').on('click', function(e) {
				$('html, body').animate({
					scrollTop: $(window).height()
				}, 1200);
			});
		</script>
</body>
</html>