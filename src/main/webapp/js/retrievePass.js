
$(document).ready(function () {
		var path = $("#path").val();
	
	    $(".print_code").keyup(function () {
	        regCode();
	    });

	    /**
	     * 验证码校验
	     */
	    function regCode() {
	        var code = $.trim($(".print_code").val());
	        if (code == "" || code == $(".print_code").attr("placeholder")) {
	            $(".print_code").next().next().css("visibility", "visible");
	            $(".print_code").next().next().text("请输入验证码");
	            $(".print_code").next().next().next().hide();
	            return false;
	        } else if ($(".print_code").val().length != 4) {
	            $(".print_code").next().next().css("visibility", "visible");
	            $(".print_code").next().next().text("短信验证码只能四位！");
	            $(".print_code").next().next().next().hide();
	            return false;
	        } else {
	            $(".print_code").next().next().css("visibility", "hidden");
	            $(".print_code").next().next().text("");
	            $(".print_code").next().next().next().show();
	        }
	        return true;
	    }

	    $("#newPassword").keyup(function () {
	        regNewPassword();
	    });

	    /**
	     * 新密码校验
	     */
	    function regNewPassword() {
	        var pwd = $("#newPassword").val();
	        var regpwd = /^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$/;
	        if (pwd == "" || pwd == $("#newPassword").attr("placeholder")) {
	            if (navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/9./i) == "9.") {
	                $("#newPassword").next().next().css("visibility", "visible");
	                $("#newPassword").next().next().text("请输入密码");
	                $("#newPassword").next().next().next().hide();
	            } else {
	                $("#newPassword").next().css("visibility", "visible");
	                $("#newPassword").next().text("请输入密码");
	                $("#newPassword").next().next().hide();
	            }

	            return false;
	        } else if (!regpwd.test($("#newPassword").val())) {
	            if (navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/9./i) == "9.") {
	                $("#newPassword").next().next().css("visibility", "visible");
	                $("#newPassword").next().next().text("密码格式有误(字母、数字或者符号,最短6位)");
	                $("#newPassword").next().next().next().hide();
	            } else {
	                $("#newPassword").next().css("visibility", "visible");
	                $("#newPassword").next().text("密码格式有误(字母、数字或者符号,最短6位)");
	                $("#newPassword").next().next().hide();
	            }
	            return false;
	        } else if ($("#newPassword").val().indexOf($("#phone").val()) >= 0) {
	            if (navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/9./i) == "9.") {
	                $("#newPassword").next().next().css("visibility", "visible");
	                $("#newPassword").next().next().text("密码不能包含手机号码");
	                $("#newPassword").next().next().next().hide();
	            } else {
	                $("#newPassword").next().css("visibility", "visible");
	                $("#newPassword").next().text("密码不能包含手机号码");
	                $("#newPassword").next().next().hide();
	            }
	            return false;
	        } else {
	            if (navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/9./i) == "9.") {
	                $("#newPassword").next().next().css("visibility", "hidden");
	                $("#newPassword").next().next().text("");
	                $("#newPassword").next().next().next().show();
	            } else {
	                $("#newPassword").next().css("visibility", "hidden");
	                $("#newPassword").next().text("");
	                $("#newPassword").next().next().show();
	            }
	        }
	        return true;
	    }

	    

	    /**
	     * 密码二次校验
	     */
	    function confirmPassword1() {
	        var pwd = $("#newPassword").val();
	        var confirmPwd = $("#confirmPassword").val();
	        if (pwd != confirmPwd) {
	            if (navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/9./i) == "9.") {
	                $("#confirmPassword").next().next().css("visibility", "visible");
	                $("#confirmPassword").next().next().text("两次密码不一致，请重新输入");
	                $("#confirmPassword").next().next().next().hide();
	            } else {
	                $("#confirmPassword").next().css("visibility", "visible");
	                $("#confirmPassword").next().text("两次密码不一致，请重新输入");
	                $("#confirmPassword").next().next().hide();
	            }
	            return false;
	        }
	        if (navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/9./i) == "9.") {
	            $("#confirmPassword").next().next().css("visibility", "hidden");
	            $("#confirmPassword").next().next().text("");
	            $("#confirmPassword").next().next().next().show();
	        } else {
	            $("#confirmPassword").next().css("visibility", "hidden");
	            $("#confirmPassword").next().text("");
	            $("#confirmPassword").next().next().show();
	        }

	        return true;
	    }
	    
	    $("#confirmPassword").keyup(function () {
	        confirmPassword1();
	    });
	    
	    /**
	     * 手机号判断
	     */
	    $("#phone").keyup(function () {
	        regPhone();
	    });

	    function regPhone() {
	        var reg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/; // 修改与2018年07月23日
	        var phone = $('#phone').val();
	        if (!reg.test(phone) || phone == $("#phone").attr("placeholder")) {
	            $('#phone').next().text("请输入有效的手机号码");
	            // $('#phone').next().next().hide();
	            $('#phone').next().css('visibility', 'visible');
	            return false;
	        } else {
	            $('#phone').next().text("");
	            $('#phone').next().next().show();
	            $('#phone').next().css('visibility', 'hidden');
	        }
	        return true;
	    }
	    /**
	     * 获取手机验证码
	     */
	    $(".obtain_btn").click(function () {
	    	if (!regPhone()) {
	            return false;
	        }
	        if (!regNewPassword()) {
	            return false;
	        }
	        if (!confirmPassword1()) {
	            return false;
	        }
	        var phone = $.trim($("#phone").val());
	        $.ajax({
	            dataType: 'json',
	            type: 'POST',
	            url: path+'/student/verificationCode.action?',
	            data: {
	                "mobile": phone,
	            },
	            success: function success(res) { 
	                if (res.code == 0) {
	                    var n = 60;
	                	$(".obtain_btn").attr("disabled","disabled");
	                    $(".obtain_btn").css({
	                        "background-color": "#F2F2F2",
	                        "color": "#00C356",
	                        "cursor": "default"
	                    });
	                    window.timer = setInterval(function () {
	                        if (n <= '1') {
	                            clearInterval(timer);
	                            $(".obtain_btn").css({
	                                "background-color": "#00C356",
	                                "color": "#FFFFFF",
	                                "cursor": "pointer",                              
	                            });
	                            $(".obtain_btn").val('获取验证码');
	                            $(".obtain_btn").removeAttr("disabled");
	                        } else {
	                            $(".obtain_btn").val(--n + '秒后重新获取');
	                        }
	                    }, 1000);
	                } else {
	                    $(".register-code").next().next().css("visibility", "visible");
	                    $(".register-code").next().next().text(res.msg);
	                    $(".rep_md_panel #keywordsMsg3").css("visibility", "visible");
	                    $(".rep_md_panel #keywordsMsg3").text(res.msg);
	                    $(".obtain_btn").val('获取验证码');
	                    $(".obtain_btn").css({
	                        "background-color": "#00C356",
	                        "color": "#FFFFFF",
	                        "cursor": "pointer"
	                    });
	                }
	            }
	        });
	    });
	    /**
	     * 重置密码确认按钮
	     */
	    $("#findpass_button_2").click(function () {
	    	if (!regPhone()) {
	            return false;
	        }
	    	if (!regNewPassword()) {
	            return false;
	        }
	    	if (!confirmPassword1()) {
	            return false;
	        }
	        if (!regCode()) {
	            return false;
	        }
	        var phone = $.trim($("#phone").val());
	        var newPassword = $.trim($("#newPassword").val());
	        var confirmPassword = $.trim($("#confirmPassword").val());
	        var code = $.trim($(".print_code").val());
	        $.ajax({
				url:path+"/student/changePwd.action?",
				async:true,
				type:"POST",
				data:{"phone":phone,"newPassword":newPassword,"confirmPassword":confirmPassword,"verifyCode":code},
				dataType:"text",
				success:function(msg){
					var msge = JSON.parse(msg);
					if(msge.myresult == "success"){
						$("#findpass_button_2").text("亲，密码修改成功哦~将于3秒后跳转登录页面");
	                    setTimeout(function () {
	                        window.location.href = path+'/student/login.action';
	                    }, 3000);
					}else if(msge.myresult == "codeErr"){
						layer.msg("修改失败，验证码错误!");
					}else if(msge.myresult == "phoneErr"){
						layer.msg("修改失败，手机号码错误!");
					}else if(msge.myresult == "failed"){
						layer.msg("修改失败，请重新提交！");
					}else if(msge.myresult == "pastDue"){
						layer.msg("修改失败，验证码已经失效！");
					}
				},
				error:function(){
					alert("操作失败！");
				}
			});	
	    });
	});