//显示提示框，目前三个参数(txt：要显示的文本；time：自动关闭的时间（不设置的话默认1500毫秒）；status：默认0为错误提示，1为正确提示；)
function showTips(txt, time, status) {
	var htmlCon = '';
	if (txt != '') {
		if (status != 0 && status != undefined) {
			htmlCon = '<div class="tipsBox" style="width:220px;padding:10px;background-color:#4AAF33;border-radius:4px;-webkit-border-radius: 4px;-moz-border-radius: 4px;color:#fff;box-shadow:0 0 3px #ddd inset;-webkit-box-shadow: 0 0 3px #ddd inset;text-align:center;position:fixed;top:25%;left:50%;z-index:999999;margin-left:-120px;"><img src="images/ok.png" style="vertical-align: middle;margin-right:5px;" alt="OK，"/>'
					+ txt + '</div>';
		} else {
			htmlCon = '<div class="tipsBox" style="width:220px;padding:10px;background-color:#D84C31;border-radius:4px;-webkit-border-radius: 4px;-moz-border-radius: 4px;color:#fff;box-shadow:0 0 3px #ddd inset;-webkit-box-shadow: 0 0 3px #ddd inset;text-align:center;position:fixed;top:25%;left:50%;z-index:999999;margin-left:-120px;"><img src="images/err.png" style="vertical-align: middle;margin-right:5px;" alt="Error，"/>'
					+ txt + '</div>';
		}
		$('body').prepend(htmlCon);
		if (time == '' || time == undefined) {
			time = 1500;
		}
		setTimeout(function() {
			$('.tipsBox').remove();
		}, time);
	}
}

$(function() {
	var path = $("#path").val();

	/**
	 * 验证码校验
	 */
	function enterCode() {
		var code = $.trim($(".verifyCode").val());
		if (code == "" || code == $(".verifyCode").attr("placeholder")) {
			showTips('请输入验证码~');
			return false;
		} else if ($(".verifyCode").val().length != 4) {
			showTips('短信验证码只能四位~');
			return false;
		}
		return true;
	}

	/**
	 * 驾校入驻新密码校验
	 */
	function enterPassword() {
		var pwd = $(".passwd").val();
		var entpwd = /^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$/;
		if (pwd == "" || pwd == $(".passwd").attr("placeholder")) {
			if (navigator.appName == "Microsoft Internet Explorer"
					&& navigator.appVersion.match(/9./i) == "9.") {
				showTips('请输入密码~');
			} else {
				showTips('请输入密码~');
			}
			return false;
		} else if (!entpwd.test($(".passwd").val())) {
			if (navigator.appName == "Microsoft Internet Explorer"
					&& navigator.appVersion.match(/9./i) == "9.") {
				showTips('密码格式有误(字母、数字或者符号,最短6位)~');
			} else {
				showTips('密码格式有误(字母、数字或者符号,最短6位)~');
			}
			return false;
		}
		return true;
	}

	/**
	 * 驾校入驻密码二次校验
	 */
	function confirmPassword1() {
		var pwd = $(".passwd").val();
		var confirmPwd = $(".passwd2").val();
		if (pwd != confirmPwd) {
			if (navigator.appName == "Microsoft Internet Explorer"
					&& navigator.appVersion.match(/9./i) == "9.") {
				showTips('两次密码不一致，请重新输入~');
			} else {
				showTips('两次密码不一致，请重新输入~');
			}
			return false;
		}
		return true;
	}

	/**
	 * 手机号判断
	 */
	function enterPhone() {
		var reg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/; // 修改与2018年07月23日
		var phone = $('.phone').val();
		if (!reg.test(phone) || phone == $(".phone").attr("placeholder")) {
			showTips('请输入有效手机号~');
			return false;
		}
		return true;
	}
	/**
	 * 获取手机验证码
	 */
	$(".enter-code-btn").click(function() {
		if (!enterPhone()) {
			return false;
		}
		if (!enterPassword()) {
			return false;
		}
		if (!confirmPassword1()) {
			return false;
		}
		var phone = $.trim($(".phone").val());
		$.ajax({
			dataType : 'json',
			type : 'POST',
			url : path + '/student/verificationCode.action?',
			data : {
				"mobile" : phone,
			},
			success : function success(res) {
				if (res.code == 0) {
					layer.msg("发送成功，请注意查收。");	
					var n = 60;
					$(".enter-code-btn").attr("disabled", "disabled");
					$(".enter-code-btn").css({
						"background-color" : "#F2F2F2",
						"color" : "#00C356",
						"cursor" : "default"
					});
					window.timer = setInterval(function() {
						if (n <= '1') {
							clearInterval(timer);
							$(".enter-code-btn").css({
								"background-color" : "#00C356",
								"color" : "#FFFFFF",
								"cursor" : "pointer",
							});
							$(".enter-code-btn").val('获取验证码');
							$(".enter-code-btn").removeAttr("disabled");
						} else {
							$(".enter-code-btn").val(--n + '秒后重新获取');
						}
					}, 1000);
				} else {
					$(".enter-code-btn").val('获取验证码');
					$(".enter-code-btn").css({
						"background-color" : "#00C356",
						"color" : "#FFFFFF",
						"cursor" : "pointer"
					});
				}
			}
		});
	});
	//AJAX提交以及验证表单
	$('#nextBtn').click(
			function() {
				if (!enterPhone()) {
					return false;
				}
				if (!enterPassword()) {
					return false;
				}
				if (!confirmPassword1()) {
					return false;
				}
				if (!enterCode()) {
					return false;
				}
				var phone = $.trim($(".phone").val());
				var code = $.trim($(".verifyCode").val());
				$.ajax({
					url : path + "/school/verifyCode.action?",
					async : true,
					type : "POST",
					data : {
						"phone" : phone,
						"verifyCode" : code
					},
					dataType : "text",
					success : function(msg) {
						var msge = JSON.parse(msg);
						if (msge.myresult == "success") {
							showTips('验证成功~ 即将进入下一步', 2000, 1);
							setTimeout(function() {
								$('.processorBox li').removeClass('current')
										.eq(1).addClass('current');
								$('.step').fadeOut(300).eq(1).fadeIn(500);
							}, 2000);
						} else if (msge.myresult == "codeErr") {
							showTips("手机验证码填写有误!");
						} else if (msge.myresult == "phoneErr") {
							showTips("手机号码错误!");
						} else if (msge.myresult == "pastDue") {
							showTips("该验证码已经失效！");
						}
					},
					error : function() {
						alert("操作失败！");
					}
				});
			});

	//切换步骤（目前只用来演示）
//	$('.processorBox li').click(
//		function() {
//			var i = $(this).index();
//			alert(i);
//			$('.processorBox li').removeClass('current').eq(i).addClass('current');
//			$('.step').fadeOut(300).eq(i).fadeIn(500);	
//		}
//	);	
	//切换步骤（目前只用来演示）
	$('.up').click(
		function() {
			$('.processorBox li').removeClass('current').eq(0).addClass('current');
			$('.step').fadeOut(300).eq(0).fadeIn(500);	
		}
	);
	//切换步骤（目前只用来演示）
	$('.upDown').click(
		function() {
			$('.processorBox li').removeClass('current').eq(1).addClass('current');
			$('.step').fadeOut(300).eq(1).fadeIn(500);	
		}
	);
	
	//切换步骤（目前只用来演示）
	$('.next').click(
		function() {
			var sch_creditcode = $("#sch_creditcode").val();
			var sch_name = $("#sch_name").val();
			var sch_type = $("#sch_type").val();
			var sch_address = $("#sch_address").val();
			var sch_bossname = $("#sch_bossname").val();
			var sch_registerCapital = $("#sch_registerCapital").val();
			var sch_charge = $("#sch_charge").val();
			var school_introduce = $("#school_introduce").val();
			if(sch_creditcode=="" || sch_name=="" || sch_type=="" || sch_address=="" || sch_bossname=="" || sch_registerCapital=="" || sch_charge=="" || school_introduce==""){
				
			}else{
				$('.processorBox li').removeClass('current').eq(2).addClass('current');
				$('.step').fadeOut(300).eq(2).fadeIn(500);
			}			
		}
	);	
});