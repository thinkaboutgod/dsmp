
$(function(){
	var video = document.getElementById("video");
	var canvas = document.getElementById("canvas");
	var context = canvas.getContext("2d");
	var mediaStreamTrack=null;
	var path = $("#path").val();
	$("#open").click(function(){
		openUserMedia();
	})
	
	$("#photograph").click(function(){
		alert("拍照了");
		getFace(context);
	})
	
	$("#student_reg").click(function(){
		//获取浏览器页面的画布对象		
		//以下开始编 数据
		//将图像转换为base64数据	
		var imageBase64 = canvas.toDataURL();
		var blob = dataURItoBlob(imageBase64);
		var name = $("#real_name").val();
		var idCard = $("#idnum").val();
		var address = $("#student_address").val();
		var sex = $("#sex").val();
		var school = $("#schools").val();
		var coach = $("#teachers").val();
		var phone = $("#student_phone").val();
		var code = $("#message").val();
		var fd = new FormData(document.forms[0]);
		// 上一步中的函数					
		fd.append("the_file", blob);
		fd.append("filename",phone+'.jpg');
		fd.append("name", name);
		fd.append("idCard",idCard);
		fd.append("address", address);
		fd.append("sex",sex);
		fd.append("school",school);
		fd.append("coach", coach);
		fd.append("code",code);
		fd.append("phone",phone);
		if(name == "" || idCard=="" || address=="" || sex=="" || school=="" || code==""){

		}else if(isCanvasBlank(canvas)){
			layer.msg("请上传一张头像，用于刷脸打卡计时！");
		}else{							
			$.ajax({
				type : "POST",
				url : path+"/student/studentApply.action?",
				processData : false,
				// 必须	      
				contentType : false,
				// 必须	      
				data : fd,
				datatype : "text",
				success : function(msg) {
					if(msg.myresult == "success"){
						layer.msg("提交成功，正在前往缴费...");	
						setTimeout(function () {
							window.open(path+'/student/payment.action?schId='+school);
	                    }, 3000);				
					}else if(msg.myresult == "codeErr"){
						layer.msg("报名失败，验证码错误!");
					}else if(msg.myresult == "phoneErr"){
						layer.msg("报名失败，手机号码错误!");
					}else if(msg.myresult == "already"){
						layer.msg("报名失败，您已经报名过了,请勿重复提交!");
					}else if(msg.myresult == "pastDue"){
						layer.msg("报名失败，验证码已经失效！");
					}
				},
				error : function() {
					//请求出错处理	
					layer.msg("操作失败！");
				}
			}); 
		
		}
	}) 
	
	function dataURItoBlob(base64Data) {
		var byteString;
		if (base64Data.split(',')[0].indexOf('base64') >= 0)
			byteString = atob(base64Data.split(',')[1]);
		else
			byteString = unescape(base64Data.split(',')[1]);
		var mimeString = base64Data.split(',')[0].split(':')[1].split(';')[0];
		var ia = new Uint8Array(byteString.length);
		for (var i = 0; i < byteString.length; i++) {
			ia[i] = byteString.charCodeAt(i);
		}
		return new Blob([ ia ], {
			type : mimeString
		});
	}
	//验证canvas画布是否为空函数
	function isCanvasBlank(canvas) {
	    var blank = document.createElement('canvas');//系统获取一个空canvas对象
	    blank.width = canvas.width;
	    blank.height = canvas.height;
	    return canvas.toDataURL() == blank.toDataURL();//比较值相等则为空
	}
	function success(stream){
	    // 兼容webkit核心浏览器
	    // var CompatibleURL = window.URL || window.webkitURL;
	    // 将视频流转化为video的源
	    mediaStreamTrack=stream;
	    try {
	        // video.src = CompatibleURL.createObjectURL(stream);
	        video.srcObject=stream;
	        video.play();// 播放视频

	        // 将视频绘制到canvas上
	    }catch (e) {
	    	layer.msg("开启摄像头失败，请刷新网页重试", {
	    	    time: 20000, //20s后自动关闭
	    	    btn: [ '知道了']
	    	  });
//	    	alert("开启摄像头失败，请刷新网页重试");
	    }

	   
	}
	// 错误回调函数
	function error(error) {
		buttonHide();
		layer.msg("您刚刚选择了禁止浏览器启动摄像头，请刷新网页并允许浏览器调用摄像头，来完成验证", {
		    time: 20000, //20s后自动关闭
		    btn: [ '知道了']
		  });
//		alert("您选择了禁止浏览器启动摄像头，请刷新网页并允许浏览器调用摄像头，来完成验证")
	}
	function getUserMediaToPhoto(constraints,success,error) {
	    if(navigator.mediaDevices.getUserMedia){
	        // 最新标准API
	        navigator.mediaDevices.getUserMedia(constraints).then(success).catch(error);
	    }else if (navigator.webkitGetUserMedia) {
	        // webkit核心浏览器
	        navigator.webkitGetUserMedia(constraints,success,error);
	    }else if(navigator.mozGetUserMedia){
	        // firefox浏览器
	        navigator.mozGetUserMedia(constraints,success,error);
	    }else if(navigator.getUserMedia){
	        // 旧版API
	        navigator.getUserMedia(constraints,success,error);
	    }
	}
	//获取图片
	function getFace(context) {
	        context.drawImage(video,0,0,350,220);
//	        this.img=canvas.toDataURL('image/jpg')
//	       // 获取完整的base64编码
//	        this.img=img.split(',')[1];
//	        return this.img;
	}
	//打开摄像头
	function openUserMedia() {
	    if(navigator.mediaDevices.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.getUserMedia){
	        getUserMediaToPhoto({video:{width:370,height:250,facingMode: "user"}},success,error);
	    }else{
	    	layer.msg("您的浏览器不支持访问用户媒体设备", {
	    	    time: 20000, //20s后自动关闭
	    	    btn: [ '知道了']
	    	  });
//	        alert('你的浏览器不支持访问用户媒体设备');
	    }
	}
	
	function  offUserMedia() {//关闭摄像头
	    if(mediaStreamTrack!=null){
	    	mediaStreamTrack.getTracks()[0].stop();
	    }
	    
	}

	//关闭摄像头
	function closeVideo() {
		offUserMedia();
		video.srcObject=null;
	}
	
})