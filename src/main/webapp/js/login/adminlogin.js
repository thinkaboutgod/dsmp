

$(function() {
	
    //回车登录
    $("body").keydown(function(event) {
		if (event.keyCode == "13") {// keyCode=13是回车键
			$("#adminlogin").click();
		}
	});
	
	var path = $("#path").val();	
	$("#adminlogin").click(function(){
		var account = $("#account").val().trim();
		var password = $("#password").val().trim();
		var yzm = $("#yzm").val().trim();
		if(account==""){
			layer.msg("账号不能为空！");
		}else if(password==""){
			layer.msg("密码不能为空！");
		}else if(yzm==""){
			layer.msg("验证码不能为空！");
		}else if(yzm.length<4 || yzm.length>4){
			layer.msg("验证码只能是四位！");
		}else{
			$.ajax({
				url:"/dsmp/admin/adminLogin.action?",
				async:true,
				type:"POST",
				data:{"account":account,"password":password,"yzm":yzm},
				dataType:"text",
				success:function(msg){
					var msge = JSON.parse(msg);
					if(msge.myresult == "success"){
						layer.msg("登录成功!");
						var role = msge.roleId;
						if(role == "1"){
							setTimeout(function () {
								window.location.href = path+'/menu/toManageMain.action?role_id='+role;
		                    }, 2000);
							
						}else if( role == "2"){
							setTimeout(function () {
								window.location.href = path+'/menu/toManageMain.action?role_id='+role;
		                    }, 2000);							
						}											
					}else if(msge.myresult == "failed"){
						layer.msg("登录失败，账号不存在!");
					}else if(msge.myresult == "codeFaild"){
						layer.msg("登录失败，验证码错误!");
						$("#getCode").click();
					}else if(msge.myresult == "pwdErr"){
						layer.msg("登录失败，密码错误!");
					}
				}
			});
		}
		
	});
	
	
});