

//function sss(){
//	alert("123");
//	var path = $("#path").val();
//	
//	var selectSchool = $("#schools").val();
//	alert(selectSchool);
//	$.ajax({
//		url:path+"/school/selectCoach.action?",
//		async:true,
//		data:{"selectSchool":selectSchool},
//		dataType:"text",
//		success:function(res){
//			
//			var arr = JSON.parse(res);
//			for (var i = 0; i < arr.length; i++) {
//				$("#teachers").append("<option>"+arr[i].did+"</option>");
//			}	
//		},
//		error:function(){
//			alert("操作失败！");
//		}
//	})
//}