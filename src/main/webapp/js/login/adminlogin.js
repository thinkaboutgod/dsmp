

$(function() {
	var path = $("#path").val();	
	$("#adminlogin").click(function(){
		var account = $("#account").val().trim();
		var password = $("#password").val().trim();
		var yzm = $("#yzm").val().trim();
		if(account==""){
			alert("账号不能为空！");
		}else if(password==""){
			alert("密码不能为空！");
		}else if(yzm==""){
			alert("验证码不能为空！");
		}else if(yzm.length<4 || yzm.length>4){
			alert("验证码只能是四位！");
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
						alert("登录成功!");
						var role = msge.roleId;
						if(role == "1"){
							window.location.href = path+'/menu/toManageMain.action?role_id='+role;
						}else if( role == "2"){
							window.location.href = path+'/menu/toManageMain.action?role_id='+role;
						}											
					}else if(msge.myresult == "failed"){
						alert("登录失败，账号不存在!");
					}else if(msge.myresult == "codeFaild"){
						alert("登录失败，验证码错误!");
					}else if(msge.myresult == "pwdErr"){
						alert("登录失败，密码错误!");
					}
				}
			});
		}
		
	});
	
	
});