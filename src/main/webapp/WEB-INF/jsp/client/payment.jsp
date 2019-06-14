<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	String path = application.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>支付宝电脑网站支付</title>
<link href=<%=path+"/css/pay.css" %> rel="stylesheet">
</head>
<body text=#000000 bgColor="#ffffff" leftMargin=0 topMargin=4 background=<%=path+"/images/pay.jpg" %>>
	<header class="am-header">
	<h1>学员在线报名支付宝支付入口页</h1>
	</header>
	<div id="main">
		<div id="tabhead" class="tab-head">
			<h2 id="tab1" class="selected" name="tab">付款信息</h2>
		</div>
		<form name=alipayment action=<%=path+"/student/alipay.action" %> method=post
			target="_blank">
			<div id="body1" class="show" name="divcontent">
				<dl class="content">
					<dt>商户订单号 ：</dt>
					<dd>
						<input id="WIDout_trade_no" style="border:0px;background:none;" name="WIDout_trade_no" />
					</dd>
					<hr class="one_line">
					<dt>订单名称 ：</dt>
					<dd>
						<input id="WIDsubject" value="${school.schName }" style="border:0px;background:none;" name="WIDsubject" />
					</dd>
					<hr class="one_line">
					<dt>付款金额 ：</dt>
					<dd>
						<input id="WIDtotal_amount" value="${school.schCharge }" style="border:0px;background:none;" name="WIDtotal_amount" />
					</dd>
					<hr class="one_line">
					<dt>商品描述：</dt>
					<dd>
						<input id="WIDbody" style="border:0px;background:none;" name="WIDbody" />
					</dd>
					<hr class="one_line">
					<dt></dt>
					<dd id="btn-dd">
						<span class="new-btn-login-sp">
							<button class="new-btn-login" type="submit"
								style="text-align: center;">付 款</button>
						</span> <span class="note-help">如果您点击“付款”按钮，即表示您同意该次的执行操作。</span>
					</dd>
				</dl>
			</div>
		</form>
		<div id="foot">
			<span>支付宝版权所有 2015-2018 ALIPAY.COM</span>
		</div>
	</div>
</body>
<script language="javascript">
	var tabs = document.getElementsByName('tab');
	var contents = document.getElementsByName('divcontent');
	
	(function changeTab(tab) {
	    for(var i = 0, len = tabs.length; i < len; i++) {
	        tabs[i].onmouseover = showTab;
	    }
	})();
	
	function showTab() {
	    for(var i = 0, len = tabs.length; i < len; i++) {
	        if(tabs[i] === this) {
	            tabs[i].className = 'selected';
	            contents[i].className = 'show';
	        } else {
	            tabs[i].className = '';
	            contents[i].className = 'tab-content';
	        }
	    }
	}
	
	function GetDateNow() {
		var vNow = new Date();
		var sNow = "";
		sNow += String(vNow.getFullYear());
		sNow += String(vNow.getMonth() + 1);
		sNow += String(vNow.getDate());
		sNow += String(vNow.getHours());
		sNow += String(vNow.getMinutes());
		sNow += String(vNow.getSeconds());
		sNow += String(vNow.getMilliseconds());
		document.getElementById("WIDout_trade_no").value = sNow;
	}
	GetDateNow();
</script>
</html>