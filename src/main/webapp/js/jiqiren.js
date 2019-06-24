

  $(function(){
    $("#robot").click(function(){
        var question = $("#question").val();
       $("#chat").append("<li role='you'>" + question + "</li>");
        $.ajax({
        url: "http://www.tuling123.com/openapi/api",
        type: "post",
        dataType: "json",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {
          "key": "865837da633441698c359ee974b9e7cc",
          "info": question,
        },
        success: function(msg) {

          $("#chat").append("<li role='me'>" + msg.text + "</li>");
          $("#chat").scrollTop($("#chat")[0].scrollHeight);
        },
        error: function() {
          alert("访问失败 路径错误");
        }

      });

    });
    //获得焦点时间点击回车发送信息
    $("#question").focus(function(){
	   $("body").keydown(function(event) {
			if (event.keyCode == "13") {// keyCode=13是回车键
				$("#robot").click();
			}
	   });
    });
  })


