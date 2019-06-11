/*
在用getUserMediaToPhoto之前要写两个回调函数，一个success 一个 error
格式：
 function success(stream){
 }
//失败回调函数
function error(error) {
}
*/
var path = $("#path").val();
var nowTime;//已学时长
var allTime;//共需时长
$(function() {
	$("#studentSelect").val(0);
	var video = document.getElementById("video");
var canvas = document.getElementById("canvas");
var context = canvas.getContext("2d");
var mediaStreamTrack=null;
$("#begin").click(function() {//开始打卡启动摄像头
	var stuId = $("#studentSelect").val();
	if (stuId==0) {
		layer.msg("请选择学员");
		return;
	}
	if (parseFloat(nowTime)>=parseFloat(allTime)) {
		layer.msg("您该科目学时已达标，无需继续打卡", {
    	    time: 20000, //20s后自动关闭
    	    btn: [ '知道了']
    	  });
		return;
	}
	openUserMedia();
	
})

$("#end").click(function() {//结束打卡启动摄像头
	openUserMedia();
})

$("#cancle").click(function() {//取消，关闭摄像头
	offUserMedia();
	buttonHide();
	video.srcObject=null;
})

$("#studentSelect").change(function() {
	if ($(this).val()==0) {
		$("#info").text("（暂未选择）");
		$("#subject").text("（暂无）");
		$("#time").text("（暂无）");
		$("#time2").text("（暂无）");
		return;
	}
	$("#info").text($(this).find("option:selected").text());
	$("#subject").text($(this).val().split("_")[2]);
	var stuId = $(this).val().split("_")[0];
	var subId = $(this).val().split("_")[1];
	var subTime = $(this).val().split("_")[3];
	$.ajax({
		async:true,
		type:"POST",
		url:path+"/searchStudentStudyTime.action",
		data:{stuId:stuId,
			subId :subId
			},
		dataType : "text",
		success:function(data){
			var result = JSON.parse(data);
			if (result!=null) {
				$("#time2").text(subTime+"小时");
				$("#time").text(result.sum+"小时");
				allTime = subTime;
				nowTime = result.sum;
			}
			
		},
		error:function(){
			alert("服务器繁忙");
		}
		})
	
})

$("#submit").on("click",function(){
	var base = getFace(context);
	
	$.ajax({
		type:"POST",
		url:"../../forFace.action",
		data:{base:base},
		dataType : "text",
		success:function(data){
			alert(data);
			var result = JSON.parse(data);
			result = JSON.parse(result);
			if (result.result!=null) {
				alert(result.result.score);
			}
			if (result.error_code==0) {
				if (result.result.score>=80) {
					alert("验证成功");
				}else {
					alert("验证失败，请确保是本人");
				}
			}else {
				alert("请本人正面对准摄像头，以便系统正确识别");
			}
		},
		error:function(){
			alert("服务器繁忙");
		}
		})
	
})
})


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
        buttonShow();
    }catch (e) {
    	layer.msg("开启摄像头失败，请刷新网页重试", {
    	    time: 20000, //20s后自动关闭
    	    btn: [ '知道了']
    	  });
//    	alert("开启摄像头失败，请刷新网页重试");
    }

   
}
// 错误回调函数
function error(error) {
	buttonHide();
	layer.msg("您刚刚选择了禁止浏览器启动摄像头，请刷新网页并允许浏览器调用摄像头，来完成验证", {
	    time: 20000, //20s后自动关闭
	    btn: [ '知道了']
	  });
//	alert("您选择了禁止浏览器启动摄像头，请刷新网页并允许浏览器调用摄像头，来完成验证")
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

function getFace(context) {
        context.drawImage(video,0,0,200,150);
        this.img=canvas.toDataURL('image/jpg')
       // 获取完整的base64编码
        this.img=img.split(',')[1];
        return this.img;
}
function openUserMedia() {
    if(navigator.mediaDevices.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.getUserMedia){
        getUserMediaToPhoto({video:{width:480,height:320,facingMode: "user"}},success,error);
    }else{
    	layer.msg("您的浏览器不支持访问用户媒体设备", {
    	    time: 20000, //20s后自动关闭
    	    btn: [ '知道了']
    	  });
//        alert('你的浏览器不支持访问用户媒体设备');
    }
}
function  offUserMedia() {
    if(mediaStreamTrack!=null)
    mediaStreamTrack.getTracks()[0].stop();
}

function buttonShow() {
	$("#submit").attr("type","button");
	$("#cancle").attr("type","button");
}

function buttonHide() {
	$("#submit").attr("type","hidden");
	$("#cancle").attr("type","hidden");
}