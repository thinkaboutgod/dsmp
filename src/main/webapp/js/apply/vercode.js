$(function(){
	var path = $("#path").val();
	$("#sendmsg").click(function () {
        var phone = $.trim($("#student_phone").val());
        alert(phone);
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
                	$("#sendmsg").attr("disabled","disabled");
                    $("#sendmsg").css({
                        "background-color": "#F2F2F2",
                        "color": "#00C356",
                        "cursor": "default"
                    });
                    window.timer = setInterval(function () {
                        if (n <= '1') {
                            clearInterval(timer);
                            $("#sendmsg").css({
                                "background-color": "#00C356",
                                "color": "#FFFFFF",
                                "cursor": "pointer",                              
                            });
                            $("#sendmsg").val('获取短信验证码');
                            $("#sendmsg").removeAttr("disabled");
                        } else {
                            $("#sendmsg").val(--n + '秒后重新获取');
                        }
                    }, 1000);
                } else {
                    $("#sendmsg").val('获取短信验证码');
                    $("#sendmsg").css({
                        "background-color": "#00C356",
                        "color": "#FFFFFF",
                        "cursor": "pointer"
                    });
                }
            }
        });
    });
})