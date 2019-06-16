 $(function() {
	 	var path = $("#path").val();
	 
	    $(".register-phone").keyup(function () {
	        jxPhoneReg();
	    });

	    function jxPhoneReg() {
	        var phone = $.trim($(".register-phone").val());
	        var regPhone = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
	        if (phone == "" || phone == $(".register-phone").attr("placeholder")) {
	            $(".register-phone").next().css("visibility", "visible");
	            $(".register-phone").next().text("请输入手机号码");
	            $(".register-phone").next().next().hide();
	            return false;
	        } else if (!regPhone.test($(".register-phone").val())) {
	            $(".register-phone").next().css("visibility", "visible");
	            $(".register-phone").next().text("请输入正确的手机号");
	            $(".register-phone").next().next().hide();
	            return false;
	        } else {
	            $(".register-phone").next().css("visibility", "hidden");
	            $(".register-phone").next().text("");
	            $(".register-phone").next().next().show();
	        }
	        return true;
	    }

	    $(".register-code").keyup(function () {
	        jxCodeReg();
	    });

	    function jxCodeReg() {
	        var code = $.trim($(".register-code").val());
	        if (code == "" || code == $(".register-code").attr("placeholder")) {
	            $(".register-code").next().next().css("visibility", "visible");
	            $(".register-code").next().next().text("请输入验证码");
	            $(".register-code").next().next().next().hide();
	            return false;
	        } else if ($(".register-code").val().length != 4) {
	            $(".register-code").next().next().css("visibility", "visible");
	            $(".register-code").next().next().text("短信验证码错误");
	            $(".register-code").next().next().next().hide();
	            return false;
	        } else {
	            $(".register-code").next().next().css("visibility", "hidden");
	            $(".register-code").next().next().text("");
	            $(".register-code").next().next().next().show();
	        }
	        return true;
	    }

	    $(".register-pwd").keyup(function () {
	        jxPwdReg();
	    });

	    function jxPwdReg() {
	        var pwd = $.trim($(".register-pwd").val());
	        var regpwd = /^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$/;
	        if (pwd == "" || pwd == $(".register-pwd").attr("placeholder")) {
	            if (navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/9./i) == "9.") {
	                $(".register-pwd").next().next().css("visibility", "visible");
	                $(".register-pwd").next().next().text("请输入密码");
	                $(".register-pwd").next().next().next().hide();
	            } else {
	                $(".register-pwd").next().css("visibility", "visible");
	                $(".register-pwd").next().text("请输入密码");
	                $(".register-pwd").next().next().hide();
	            }
	            return false;
	        } else if (!regpwd.test($(".register-pwd").val())) {
	            if (navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/9./i) == "9.") {
	                $(".register-pwd").next().next().css("visibility", "visible");
	                $(".register-pwd").next().next().text("密码格式有误(字母、数字或者符号,最短6位,最长20位)");
	                $(".register-pwd").next().next().next().hide();
	            } else {
	                $(".register-pwd").next().css("visibility", "visible");
	                $(".register-pwd").next().text("密码格式有误(字母、数字或者符号,最短6位,最长20位)");
	                $(".register-pwd").next().next().hide();
	            }
	            return false;
	        } else if ($(".register-pwd").val().indexOf($(".register-phone").val()) >= 0) {
	            if (navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/9./i) == "9.") {
	                $(".register-pwd").next().next().css("visibility", "visible");
	                $(".register-pwd").next().next().text("密码不能包含手机号码");
	                $(".register-pwd").next().next().next().hide();
	            } else {
	                $(".register-pwd").next().css("visibility", "visible");
	                $(".register-pwd").next().text("密码不能包含手机号码");
	                $(".register-pwd").next().next().hide();
	            }
	            return false;
	        } else {
	            if (navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/9./i) == "9.") {
	                $(".register-pwd").next().next().css("visibility", "hidden");
	                $(".register-pwd").next().next().text("");
	                $(".register-pwd").next().next().next().show();
	            } else {
	                $(".register-pwd").next().css("visibility", "hidden");
	                $(".register-pwd").next().text("");
	                $(".register-pwd").next().next().show();
	            }
	        }
	        return true;
	    }

	    $("#register_checkbox").click(function () {
	        jxisCheckedReg();
	    });

	    function jxisCheckedReg() {
	        var isChecked = $('#register_checkbox').is(":checked");
	        if (!isChecked) {
	            $(".register-submit").addClass("register-submit-on").text("您还未接受注册条款");
	            return false;
	        } else {
	            $(".register-submit").removeClass("register-submit-on").text("注册");
	        }
	        return true;
	    }


	    $(".register-code-btn").click(function () {
	        if (!jxPhoneReg()) {
	            return false;
	        }
	        if (!jxPwdReg()) {
	            return false;
	        }
	        if (!jxisCheckedReg()) {
	            return false;
	        }
	        var phone = $.trim($(".register-phone").val());
	        $.ajax({
	            dataType: 'json',
	            type: 'POST',
	            url: path+'/student/verificationCode.action?',
	            data: {
	                "mobile": phone,
	            },
	            success: function success(res) { 
	            	alert(res.code); 
	                if (res.code == 0) {
	                	alert("发送成功了")
	                    var n = 60;
	                	$(".register-code-btn").attr("disabled","disabled");
	                    $(".register-code-btn").css({
	                        "background-color": "#F2F2F2",
	                        "color": "#00C356",
	                        "cursor": "default"
	                    });
	                    window.timer = setInterval(function () {
	                        if (n <= '1') {
	                            clearInterval(timer);
	                            $(".register-code-btn").css({
	                                "background-color": "#00C356",
	                                "color": "#FFFFFF",
	                                "cursor": "pointer",                              
	                            });
	                            $(".register-code-btn").val('获取验证码');
	                            $(".register-code-btn").removeAttr("disabled");
	                        } else {
	                            $(".register-code-btn").val(--n + '秒后重新获取');
	                        }
	                    }, 1000);
	                } else {
	                    $(".register-code").next().next().css("visibility", "visible");
	                    $(".register-code").next().next().text(res.msg);
	                    $(".rep_md_panel #keywordsMsg3").css("visibility", "visible");
	                    $(".rep_md_panel #keywordsMsg3").text(res.msg);
	                    $(".register-code-btn").val('获取验证码');
	                    $(".register-code-btn").css({
	                        "background-color": "#00C356",
	                        "color": "#FFFFFF",
	                        "cursor": "pointer"
	                    });
	                }
	            }
	        });
	    });

	    $("#send_code").click(function () {
	        if ($("#send_code").attr("data-send") === "true" && $("#send_code").text() === "获取验证码") {
	            captchaIns && captchaIns.refresh();
	            captchaIns && captchaIns.popUp();
	        }
	    });	   

	    $(".register-submit").click(function () {
	        //验证手机号
	    	if (!jxPhoneReg()) {
	            return false;
	        }
	    	//验证密码
	        if (!jxPwdReg()) {
	            return false;
	        }
	        //验证手机验证码
	        if (!jxCodeReg()) {
                return false;
            }
	        //验证隐私条约
	        if (!jxisCheckedReg()) {
	            return false;
	        }
	        
	        var phone = $.trim($(".register-phone").val());
	        var pwd = $.trim($(".register-pwd").val());
	        var code = $.trim($(".register-code").val());
	        $.ajax({
				url:path+"/student/studentRegister.action?",
				async:true,
				type:"POST",
				data:{"phone":phone,"pwd":pwd,"verifyCode":code},
				dataType:"text",
				success:function(msg){
					var msge = JSON.parse(msg);
					if(msge.myresult == "success"){
						alert("注册成功!");
						window.location.href = path+'/student/login.action';
					}else if(msge.myresult == "codeErr"){
						alert("注册失败，验证码错误!");
					}else if(msge.myresult == "phoneErr"){
						alert("注册失败，手机号码错误!");
					}else if(msge.myresult == "already"){
						alert("注册失败，该账号已经注册过了！");
					}else if(msge.myresult == "pastDue"){
						alert("注册失败，验证码已经失效！");
					}
				},
				error:function(){
					alert("操作失败！");
				}
			});	        	        
	    });
	});