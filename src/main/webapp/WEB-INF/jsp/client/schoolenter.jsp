<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = application.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>驾校入驻</title>
<link href=<%=path + "/css/enter/base.css"%> rel="stylesheet">
<link href=<%=path + "/css/enter/layout.css"%> rel="stylesheet">
<link rel='stylesheet' href=<%=path+"/layui/css/layui.css" %> media="all">	

<script src=<%=path + "/js/jquery-3.3.1.js"%>></script>
<script src=<%=path + "/js/enter/viretyCode.js"%>></script>
<script src=<%=path+"/layui/layui.js" %>></script>
<script src=<%=path+"/js/enter/mylayui.js" %>></script>
</head>
<body>
<input type="hidden" value=<%=path %> id="path">
	<header id="header">
		<div id="headBox">
			<span>驾校入驻申请</span>
		</div>
	</header>
	<div class="container w960 mt20">
		<div id="processor">
			<ol class="processorBox oh">
				<li class="current">
					<div class="step_inner fl">
						<span class="icon_step">1</span>
						<h4>账号申请</h4>
					</div>
				</li>
				<li class="">
					<div class="step_inner">
						<span class="icon_step">2</span>
						<h4>填写基本信息</h4>
					</div>
				</li>
				<li class="">
					<div class="step_inner fr">
						<span class="icon_step">3</span>
						<h4>完善申请资料</h4>
					</div>
				</li>
			</ol>
			<div class="step_line"></div>
		</div>
		<div class="content">
			<div id="step1" class="step hide" style="display: block;margin-left: 20%;">
					<div class="frm_control_group">
						<label class="frm_label">手机号</label>
						<div class="frm_controls">
							<input type="text"  class="frm_input phone" maxlength="11"
								value="">
							<p class="frm_tips">请填写真实有效的手机号</p>
						</div>
					</div>
					<div class="frm_control_group">
						<label class="frm_label">密码</label>
						<div class="frm_controls">
							<input type="password" name="" class="frm_input passwd" maxlength="20">
							<p class="frm_tips">字母、数字或者英文符号，最短6位，区分大小写</p>
						</div>
					</div>
					<div class="frm_control_group">
						<label class="frm_label">确认密码</label>
						<div class="frm_controls">
							<input type="password" name="" class="frm_input passwd2" maxlength="20">
						</div>
					</div>
					<div class="frm_control_group">
						<label class="frm_label">验证码</label>
						<div class="frm_controls verifycode">
							<input type="text" name="" class="frm_input verifyCode"
								maxlength="4" value=""> 
							<input type="button" class="btn enter-code-btn" value="发送验证码">	
						</div>
					</div>
					<div class="toolBar">
						<input type="button" id="nextBtn" class="btn btn_primary" value="下一步">	
					</div>
			</div>
			<div id="step2" class="step hide" style="display: none;margin-left: 20%;">
					<div id="div1">
					<div id="layerregspan" class="layui-layer-content"
						style="height: 600px;">
						<div class="reg layui-layer-wrap" style="width: 500px;" id="reg"
							lay-filter="reg">
							<div class="layui-form layui-form-pane" id="form_reg">
								<div class="layui-upload">
									<label class="layui-form-label">营业执照</label>
									<div class="layui-upload layui-input-block">
										<button class="layui-btn layui-btn-danger" id="test8"
											type="button">
											<i class="layui-icon"></i>上传营业执照
										</button>
									</div>
								</div>
								<div class="layui-form-item" style="margin-top: 20px;">
									<label class="layui-form-label">信用代码</label>
									<div class="layui-input-block">
										<input type="text"  lay-verify="required"
											id="sch_creditcode" lay-vertype="tips" autocomplete="off"
											placeholder="识别营业执照获取" class="layui-input"
											disabled="disabled">
									</div>
								</div>
								<div class="layui-form-item" style="margin-top: 20px;">
									<label class="layui-form-label">单位名称</label>
									<div class="layui-input-block">
										<input type="text" lay-verify="required" id="sch_name" 
											lay-vertype="tips" autocomplete="off" placeholder="识别营业执照获取"
											class="layui-input" disabled="disabled">
									</div>
								</div>
								<div class="layui-form-item" style="margin-top: 20px;">
									<label class="layui-form-label">公司类型</label>
									<div class="layui-input-block">
										<input type="text"
											lay-verify="sch_type" id="sch_type" 
											lay-verify="required" autocomplete="off" placeholder="识别营业执照获取"
											class="layui-input" disabled="disabled">
									</div>
								</div>
								<div class="layui-form-item ">
									<label class="layui-form-label">单位地址</label>
									<div class="layui-input-block">
										<input type="text" id="sch_address" 
											lay-verify="required" autocomplete="off" placeholder="识别营业执照获取"
											class="layui-input" disabled="disabled">
									</div>
								</div>
								<div class="layui-form-item ">
									<label class="layui-form-label">法定代表人</label> 
									<div class="layui-input-block">
										<input type="text" id="sch_bossname" 
											lay-verify="required" autocomplete="off" placeholder="识别营业执照获取"
											class="layui-input" disabled="disabled">
									</div>
								</div>
								<div class="layui-form-item ">
									<label class="layui-form-label">注册资本</label>
									<div class="layui-input-block">
										<input type="text" id="sch_registerCapital" 
											lay-verify="required" autocomplete="off" placeholder="识别营业执照获取"
											class="layui-input" disabled="disabled">
									</div>
								</div>
								<div class="layui-form-item ">
									<label class="layui-form-label">学员报名费</label>
									<div class="layui-input-block">
										<input type="text" id="sch_charge"
											lay-verify="number" maxlength="5" autocomplete="off" placeholder="设置学员报名驾校费用"
											class="layui-input">
									</div>
								</div>
								<div class="layui-form-item ">
									<label class="layui-form-label">驾校简介</label>
									<div class="layui-input-block">
										<textarea lay-verify="required" maxlength="50" class="layui-textarea" id="school_introduce" name="schoolapply_introduce" lay-verify="schoolapply_introduce" placeholder="请输入50个字符以内的驾校简介"></textarea>
									</div>
								</div>
								<div class="layui-form-item">
									<div class="layui-input-block" style="top:20px;">
										<input type="button" id="nextBtn"  class="btn up" value="上一步">	
										&nbsp;&nbsp;&nbsp;&nbsp; 
										<input type="button" id="nextBtn"  lay-submit="" class="btn next" value="下一步">	
									</div>									
								</div>
							</div>
						</div>
					</div>
					<span class="layui-layer-setwin"><a
						class="layui-layer-ico layui-layer-close layui-layer-close1"
						href="javascript:;"></a></span>
				</div>
			</div>
			<div id="step3" class="step hide" style="display: none;">
				<div class="layui-upload" style="margin-left: 38%;">
  					<button class="layui-btn" id="test1" type="button">上传驾校头像</button>
  					<div class="layui-upload-list">
   						 <img class="layui-upload-img" id="demo1" style="width: 200px;height: 200px;">
   						 <p id="demoText"></p>
 					</div>
				</div>  
				<div class="layui-form-item" style="margin-top: 20px;">
					<div class="layui-input-block" style="top:30px;margin-left: 37%;">
						<input type="button" id="nextBtn"  class="btn upDown" value="上一步">	
						&nbsp;&nbsp;&nbsp;&nbsp; 
						<input type="button" id="finish"  class="btn next" value="完成">	
					</div>									
				</div>	
			</div>
		</div>
	</div>
	<footer id="footer" class="w960 oh"> </footer>
	</div>
</body>
</html>