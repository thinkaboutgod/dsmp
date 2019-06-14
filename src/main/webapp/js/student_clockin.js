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
var stuId;//学员id
var subId;//科目id
var type;//打卡类型
var video = document.getElementById("video");
var canvas = document.getElementById("canvas");
var context = canvas.getContext("2d");
var mediaStreamTrack=null;
$(function() {
	$("#studentSelect").val(0);

$("#begin").click(function() {//开始打卡启动摄像头
	closeVideo();//先关闭摄像头
	type = "begin";
	var arr = $("#studentSelect").val().split("_")
	var stuId = arr[0];//学员id
	var subId = arr[1];//学员科目id
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
	$.ajax({//判断能否开始打卡
		async:true,
		type:"POST",
		url:path+"/coach/beginStudyJud.action",
		data:{stuId:stuId,
			subId :subId
			},
		dataType : "text",
		success:function(data){
			var result = JSON.parse(data);
			if (result!=null) {
				var myResult = result.myresult;
				if (myResult=="outOfTime") {//不在打卡时间内
					var beginTime = result.data.split("_")[0]+":00";
					var endTime = result.data.split("_")[1]+":00";
					layer.msg("当前时间不在打卡允许时间内,请在"+beginTime+"至"+endTime+"时间内打卡",{
						time:10000,
						btn:["知道了"]
					})
				}else if (myResult=="haveNotEnd") {
					layer.msg("今天有打卡记录还未结束打卡，请先结束打卡",{
						time:10000,
						btn:["知道了"]
					})
				}else if (myResult=="timeEnough") {
					layer.msg("今日打卡时长已满足上限"+result.data+"小时，无法继续打卡",{
						time:10000,
						btn:["知道了"]
					})
				}else if (myResult=="allow") {//满足条件可以打卡
					openUserMedia();//开启摄像头
				}
			}
			
		},
		error:function(){
			alert("服务器繁忙");
		}
		})
})//点击开始打卡

//结束打卡启动摄像头
$("#end").click(function() {
	closeVideo();//先关闭摄像头
	var arr = $("#studentSelect").val().split("_");
	stuId = arr[0];//学员id
	type = "end";
	if (stuId==0) {
		layer.msg("请选择学员");
		return ;
	};
	$.ajax({
		async:true,
		type:"POST",
		url:path+"/coach/endStudyJud.action",
		data:{stuId:stuId,
			subId :subId
			},
		dataType : "text",
		success:function(data){
			var result = JSON.parse(data);
			if (result.myresult=="doNotEnd") {
				layer.msg("今天没有需要结束打卡的记录，请先进行打卡学习",{
					time:10000,
					btn:["知道了"]
				})
			}else if (result.myresult=="allowEnd") {//有需要结束打卡
				openUserMedia();//开启摄像头
			}
			
		},
		error:function(){
			layer.msg("服务器繁忙");
		}
		})
})


$("#cancle").click(function() {//取消，关闭摄像头
	 closeVideo();
})

$("#studentSelect").change(function() {//监听下拉框变化
	if ($(this).val()==0) {
		$("#info").text("（暂未选择）");
		$("#subject").text("（暂无）");
		$("#time").text("（暂无）");
		$("#time2").text("（暂无）");
		return;
	}
	$("#info").text($(this).find("option:selected").text());
	$("#subject").text($(this).val().split("_")[2]);
	stuId = $(this).val().split("_")[0];
	subId = $(this).val().split("_")[1];
	var subTime = $(this).val().split("_")[3];
	
	$.ajax({
		async:true,
		type:"POST",
		url:path+"/coach/searchStudentStudyTime.action",
		data:{stuId:stuId,
			subId :subId
			},
		dataType : "text",
		success:function(data){
			var result = JSON.parse(data);
			if (result!=null) {
				$("#timeAll").text(subTime+"小时");
				$("#timeNow").text(result.sum+"小时");
				allTime = subTime;
				nowTime = result.sum;
			}
			
		},
		error:function(){
			layer.msg("服务器繁忙");
		}
		})
	
})
//点击确认打卡
$("#submit").on("click",function(){
	var base = getFace(context);
	var index = layer.load();
	$.ajax({//人脸识别判断
		type:"POST",
		url:path+"/coach/makeClock.action",
		data:{base:base,
			stuId : stuId,
			subId : subId
		},
		dataType : "text",
		success:function(data){
			var result = JSON.parse(data);
			if (result.data=="failed") {
				layer.msg("打卡失败，请检查网络连接，若网络连接无问题，请联系管理员",
						{time: 5000, //3s后自动关闭
						btn: [ '知道了']})
				return;
			}
			result = JSON.parse(result.myresult);
//			if (result.result!=null) {//打印分数
//				alert(result.result.score);
//			}
			if (result.error_code==0) {
				if (result.result.score>=85) {//是本人打卡
					switch (type) {
					case "begin"://属于开始打卡
						beginClockInsert(index);//开始打卡记录插入
						break;
					case "end"://属于结束打卡
						endClockInsert(index);//结束打卡记录插入
						break;
					}
					
				}else {
					layer.close(index);
					layer.msg("人脸识别失败，请确保是本人", {
			    	    time: 5000, //5s后自动关闭
			    	    btn: [ '知道了']
			    	  });
				}
			}else {
				layer.close(index);
				layer.msg("请本人正面对准摄像头，以便系统正确识别", {
		    	    time: 5000, //5s后自动关闭
		    	    btn: [ '知道了']
		    	  });
			}
		},
		error:function(){
			layer.close(index);
			layer.msg("服务器繁忙");
		}
		})
	
})
})

//关闭摄像头
function closeVideo() {
	offUserMedia();
	buttonHide();
	video.srcObject=null;
}

//开始打卡记录插入
function beginClockInsert(index) {
	$.ajax({//发送插入打卡记录信息
		async:true,
		type:"POST",
		url:path+"/coach/studyRecord.action",
		data:{stuId:stuId,
			subId :subId
			},
		dataType : "text",
		success:function(data){
			var result = JSON.parse(data);
			if (result!=null) {
				layer.close(index);
				switch (result.myresult) {
				case "success":
					layer.msg("开始打卡成功", {
			    	    time: 5000, //5s后自动关闭
			    	    btn: [ '知道了']
			    	  });
					break;
				case "failed":
					layer.msg("系统异常，打卡失败，请联系管理员", {
			    	    time: 5000, //5s后自动关闭
			    	    btn: [ '知道了']
			    	  });
					break;
				}
			}
			setTimeout(() => {
				 closeVideo();//关闭摄像头
			}, 1000);
		},
		error:function(){
			layer.close(index);
			layer.msg("服务器繁忙");
		}
		})
}
//结束打卡记录插入
function endClockInsert(index) {
	$.ajax({//发送结束打卡记录信息插入
		async:true,
		type:"POST",
		url:path+"/coach/endStudyRecord.action",
		data:{stuId:stuId,
			subId :subId
			},
		dataType : "text",
		success:function(data){
			var result = JSON.parse(data);
			if (result!=null) {
				layer.close(index);
				switch (result.myresult) {
				case "timeOk":
					var realTime = parseFloat(result.data);
					layer.msg("结束打卡成功，本次打卡时长为："+realTime+"小时", {
			    	    time: 5000, //3s后自动关闭
			    	    btn: [ '知道了']
			    	  });
					break;
				case "timeTOShort":
					var shorttime = parseFloat(result.data)*60;
					layer.msg("本次打卡持续时间小于"+shorttime+"分钟,记录无效", {
			    	    time: 5000, //3s后自动关闭
			    	    btn: [ '知道了']
			    	  });
					break;
				case "timeTOLong":
					var longtime = parseFloat(result.data.split("_")[0]);
					var realTime = parseFloat(result.data.split("_")[1]);
					layer.msg("今日打卡持续时间超出每天打卡上限"+longtime+"小时,本次有效记录"+realTime+"小时", {
			    	    time: 5000, //3s后自动关闭
			    	    btn: [ '知道了']
			    	  });
					break;
				}
			}
			setTimeout(() => {
				 closeVideo();//关闭摄像头
			}, 1000);
		},
		error:function(){
			layer.close(index);
			layer.msg("服务器繁忙");
		}
		})
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
        buttonShow();//显示确认打卡按钮
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
function  offUserMedia() {//关闭摄像头
    if(mediaStreamTrack!=null){
    	mediaStreamTrack.getTracks()[0].stop();
    }
    
}

function buttonShow() {//显示确认和取消提交按钮
	$("#submit").attr("type","button");
	$("#cancle").attr("type","button");
}

function buttonHide() {//关闭确认取消按钮
	$("#submit").attr("type","hidden");
	$("#cancle").attr("type","hidden");
}

function hideBeginButton() {
	$("#end").attr("disabled",true);
}
