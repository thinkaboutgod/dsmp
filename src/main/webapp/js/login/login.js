$(document).ready(function () {
	
	var path = $("#path").val();
	
	//登录手机光标移出事件验证
	var flag = true;
    $(".register-phone").keyup(function () {
        if (flag) {
        	codePhoneReg();
        }
    });

    /**
     * 验证码登陆手机号码验证
     */
    function codePhoneReg() {
        var phone = $(".register-phone").val().trim();
        var regPhone = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
        if (phone == "") {
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
        }
        return true;
    }
    //键盘抬起事件验证
    $(".register-pwd").keyup(function () {
        pwdReg();
    });
    /**
     * 密码验证
     */
    function pwdReg() {
        var pwd = $(".register-pwd").val().trim();
        if (pwd == "") {
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
        } else {
            if (navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.match(/9./i) == "9.") {
                $(".register-pwd").next().next().css("visibility", "hidden");
                $(".register-pwd").next().next().text("");
            } else {
            	//如果都符合格式则隐藏警告提示
                $(".register-pwd").next().css("visibility", "hidden");
                $(".register-pwd").next().text("");
                //显示登录验证右边打钩
            }
        }
        return true;
    }
    

   
    //键盘抬起事件验证
    $(".register-yzm").keyup(function () {
    	imgCode();
    });
    /**
     * 验证码验证
     */
    function imgCode() {
        var yzm = $(".register-yzm").val().trim();
        if (yzm == "") {
            $(".register-yzm").next().next().css("visibility", "visible");
            $(".register-yzm").next().next().text("请输入验证码");
            $(".register-yzm").next().next().next().hide();
            return false;
        } else if (($(".register-yzm").val()).length != 4) {
            $(".register-yzm").next().next().css("visibility", "visible");
            $(".register-yzm").next().next().text("验证码只能是4位");
            $(".register-yzm").next().next().next().hide();
            return false;
        } else {
            $(".register-yzm").next().next().css("visibility", "hidden");
            $(".register-yzm").next().next().text("");
        }
        return true;
    }
    
    /**
     * 登陆手机号码验证
     */
	$("#role").click( function() {
		var flag = codePhoneReg();
		if(flag){
			var flag1 = pwdReg();
			if(flag1){
				var flag2 = imgCode();
				if(flag2){
					var role = $("#roleId").val();
					var saccount = $(".register-phone").val().trim();
					var spwd = $(".register-pwd").val().trim();
					var syzm = $(".register-yzm").val().trim();
					$.ajax({
						url:path+"/student/studentLogin.action?",
						async:true,
						type:"POST",
						data:{"account":saccount,"password":spwd,"yzm":syzm,"role":role},
						dataType:"text",
						success:function(msg){
							var msge = JSON.parse(msg);
							if(msge.myresult == "success"){
								layer.msg("登录成功!");	
								switch(role){
								case "5":
									window.location.href = path+'/home/main.action';								
									break;
								case "4":
									window.location.href = path+'/menu/toManageMain.action?role_id='+role;
									break;
								case "3":
									window.location.href = path+'/menu/toManageMain.action?role_id='+role;
									break;
								}														
							}else if(msge.myresult == "failed"){
								layer.msg("登录失败，账号不存在!");
							}else if(msge.myresult == "codeFaild"){
								layer.msg("登录失败，验证码错误!");
							}else if(msge.myresult == "pwdError"){
								layer.msg("登录失败，密码错误！(输入三次错误账号将被锁定)还剩"+msge.errCount+"次机会");
							}else if(msge.myresult == "forbidden"){
								layer.msg("登录失败，您的账号已被禁用，请联系平台解除!");
							}else if(msge.myresult == "lock"){
								layer.msg("登录失败，您的账号已被锁定!");
							}else if(msge.myresult == "passErr"){
								layer.msg("登录失败，密码错误!");
							}else if(msge.myresult == "stopOperatives"){
								layer.msg("登录失败，您的驾校已被暂停运营，请联系平台解除!");
							}
						},
						error:function(){
							layer.msg("操作失败!");
						}
					});
				}else{
					return false;
				}				
			}else{
				return false;
			}
		}else{
			return false;
		}				
    });
	
	
	$("#student").click( function() {
		$("#head").text("学员登录");
		$("#roleId").val("5");
		$(".register-phone").val("");
		$(".register-pwd").val("");
		$(".register-yzm").val("");
	});
	$("#coach").click( function() {
		$("#head").text("教练登录");
		$("#roleId").val("4");
		$(".register-phone").val("");
		$(".register-pwd").val("");
		$(".register-yzm").val("");
	});
	$("#school").click( function() {
		$("#head").text("驾校登录");
		var role = $("#roleId").val("3");
		$(".register-phone").val("");
		$(".register-pwd").val("");
		$(".register-yzm").val("");
	});
});