var path = $("#path").val();
var filePath;
var type;
var Aans;
var Bans;
var Cans;
var Dans;
var A;
var B;
var C;
var D;
var topTopic;
var topAnswerDetail;
var doType;//修改还是增加
$(function() {
	
	$.extend($.fn.dataTable.defaults, dataTableSeetings);// 公共初始化设置
	
	datatable_otherSet = {
			"ajax" : path+"/plateform/searchAllTopic.action",
			"searching" : true,
			"columns" : [
					{
			            sTitle: '序号',
			            data: null,
			            className: 'text-center whiteSpace',
			            render:function(data,type,row,meta) {
			                return meta.row + 1 +
			                meta.settings._iDisplayStart;
			            }
			        },{
						"data" : "topTopic",
							
					},
					
					{
						"data" : "topId",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : '',
						"sWidth" : "",
						"render" : function(data, type, full, meta) {
							
							data = ' <button  class="change btn btn-primary btn-sm " >修改</button>'
								+ ' <button  class="delete btn btn-danger btn-sm " >删除</button>';
							return data;

						}
					},  ],

			"fnServerParams" : function(aoData) {//设置参数
				aoData._rand = Math.random();
				aoData.push({
					"name" : "subId",
					"value" : 1,
				}
				);
			},

		};
	
var table = $("#topicTable").DataTable(datatable_otherSet);//初始化
//查询文件资源访问路径	
$.ajax({
	url : path + "/plateform/searchFilePath.action",
	ansyc : true,
	type : "POST",
	data : "", 
	dataType : "text",
	success : function(data) {
		filePath = data;
	},
	error : function() {
		layer.msg("服务器繁忙");
	}
})




	// 选择行
	$('tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});
	//重新加载表格
	function reload() {
		table.ajax.reload(null, false);// 刷新数据方法,false代表保持当前页
	}
	//点击修改
	$(document).on("click",".change",function(){
		clearInput();
		doType = "change";//操作为修改
		$("#forTopic").modal("show");
		var dataRow = table.row($(this).parent().parent()).data();//获取当前行数据
		var options = dataRow.options;
		$("#topTopic").val(dataRow.topTopic);
		$("#topId").val(dataRow.topId);
		$("#oLength").val(options.length);
		$("#A").val(options[0].optOption);//各选项赋值
		$("#B").val(options[1].optOption);
		$("#Aans").val(options[0].optStatus);//各选项答案
		$("#aoptId").val(options[0].optId);//各选项id
		$("#Bans").val(options[1].optStatus);
		$("#boptId").val(options[1].optId);
		$("#topAnswerDetail").val(dataRow.topAnswerDetail);
		if (options.length == 4) {
			$("#cDiv").css("display","block");//第三四选项出现
			$("#dDiv").css("display","block");
			$("#C").val(options[2].optOption);
			$("#D").val(options[3].optOption);
			$("#Cans").val(options[2].optStatus);
			$("#coptId").val(options[2].optId);
			$("#Dans").val(options[3].optStatus);
			$("#doptId").val(options[3].optId);
			
		}
		if(dataRow.topImg!=""){
			$("#imgDiv").css("display","block");
			$("img").attr("src",filePath+"/images/topic/"+dataRow.topImg);
		};
		if (options.length == 4 && dataRow.topImg=="") {//4个选项，没有图片
			type = 1;
		}else if (options.length == 4 && dataRow.topImg!="") {//4个选项，有图片
			type = 2;
		}else if (options.length == 2 && dataRow.topImg=="") {//2个选项，没有图片
			type = 3;
		}else if (options.length == 2 && dataRow.topImg!="") {//2个选项，有图片
			type = 4;
		}
	})
	
	//新增题目
	$("#addTopic").click(function() {
		clearInput();
		doType = "add";//操作为增加
		var sel = $("#topicType").val();
		if (sel == 0) {
			layer.msg("请选择新增题目类型!");
			return;
		}
		if (sel==1) {//4选项题
			type=1;
			$("#cDiv").css("display","block");
			$("#dDiv").css("display","block");
			$("#addimgDiv").css("display","block");
			
		}else {//2选项题
			type=3;
			$("#addimgDiv").css("display","block");
		}
		$("#forTopic").modal("show");
	})
	
	//删除题目
	$(document).on("click",".delete",function(){
		var topId = table.row($(this).parent().parent()).data().topId;//获取当前行的题目id
		layer.confirm('是否确认删除该数据？', {
			btn : [ '确定', '取消' ]
		// 按钮
		}, function(index) {
			$.ajax({
				url : path + "/plateform/deleteTopic.action",
				ansyc : true,
				type : "POST",
				data : {topId:topId}, 
				dataType : "text",
				success : function(data) {
					var result = JSON.parse(data);
					if (result.myresult == "success") {
						layer.msg("删除成功");
						table.row('.selected').remove().draw(false);// 删除某一行数据
					}else if (result.myresult == "fialed"){
						layer.msg("删除失败");
					}
				},
				error : function() {
					layer.msg("服务器繁忙");
				}
			})
			layer.close(index);
		}, function() {
		});
		
	})
	
	//清空
	function clearInput() {
		$("#topTopic").val("");
		$("#A").val("");
		$("#B").val("");
		$("#C").val("");
		$("#D").val("");
		$("#topAnswerDetail").val("");
		$("#cDiv").css("display","none");
		$("#dDiv").css("display","none");
		$("#imgDiv").css("display","none");
		$("#addimgDiv").css("display","none");
		$("#newImg").val("");
		$("#addnewImg").val("");
		$("#Aans").val("no");
		$("#Bans").val("no");
		$("#Cans").val("no");
		$("#Dans").val("no");
	}

	//修改提交
	$("#submitChange").click(function() {
		Aans = $("#Aans").val();
		Bans = $("#Bans").val();
		Cans = $("#Cans").val();
		Dans = $("#Dans").val();
		A = $("#A").val();
		B = $("#B").val();
		C = $("#C").val();
		D = $("#D").val();
		topTopic = $("#topTopic").val();
		topAnswerDetail = $("#topAnswerDetail").val();
		if (type==1||type==2) {//判断4个选项的
			if(!judgeYes4()){
				
				return;
			}
		}else if (type==3||type==4) {
			if(!judgeYes2()){//判断2个选项的
				return;
			}
		}
		var url = "";
		var fileId = "";
		if (doType == "change") {
			url = path+"/plateform/changeTopic.action";
			fileId = "newImg";
		}else if (doType == "add") {
			url = path+"/plateform/addTopic.action";
			fileId = "addnewImg";
		}
		//判断图片类型
		var fileName=$("#"+fileId).val(); 
		if(fileName!=""){
			var suffixIndex=fileName.lastIndexOf(".");  
		   var suffix=fileName.substring(suffixIndex+1).toUpperCase();  
		   if(suffix!="BMP"&&suffix!="JPG"&&suffix!="JPEG"&&suffix!="PNG"&&suffix!="GIF"){  
		     layer.msg( "图片格式只能为：BMP、JPG、JPEG、PNG、GIF）!");  
		   } 
		}
		//上传携带信息
		var topic = {
				"topId":$("#topId").val(),
				"topTopic" : topTopic,
				"topAnswerDetail":topAnswerDetail,
		}
		var optionA = {
				"optId": $("#aoptId").val(),
				"optOption" : $("#A").val(),
				"optStatus" : $("#Aans").val()
		}
		var optionB = {
				"optId": $("#boptId").val(),
				"optOption" : $("#B").val(),
				"optStatus" : $("#Bans").val()
		}
		var optionC = {
				"optId": $("#coptId").val(),
				"optOption" : $("#C").val(),
				"optStatus" : $("#Cans").val()
		}
		var optionD = {
				"optId": $("#doptId").val(),
				"optOption" : $("#D").val(),
				"optStatus" : $("#Dans").val()
		}
		//添加进map
		var map = {};
		map.topic = topic;
		map.optionA = optionA;
		map.optionB = optionB;
		map.optionC = optionC;
		map.optionD = optionD;
		map.type = type;
		//ajaxFileUpload上传带的参数只能为键值对字符串，不能为json对象
		$.ajaxFileUpload({
			url : url,
			type : "POST",
			secureuri : false, // 一般设置为false
			fileElementId : fileId,// 上传文件的id、name属性名
			dataType : "text",
			data :{
				map:JSON.stringify(map)
		},
			success : function(data) {
				if (data == "success") {
					switch (doType) {
					case "change":
						layer.msg('修改成功', {
							time : 2000
						});
						break;
					case "add":
						layer.msg('添加成功', {
							time : 2000
						});
						break;
					}
					setTimeout(() => {
						$("#forTopic").modal("hide");
						reload();//重新加载
					}, 1000);
					
				} else {
					switch (doType) {
					case "change":
						layer.msg('修改失败,请重试', {
							time : 2000
						});
						break;
					case "add":
						layer.msg('添加失败,请重试', {
							time : 2000
						});
						break;
					}
					$("#forTopic").modal("hide");
				}
			},
			error : function() {
				layer.msg("服务器繁忙");
			}
		})
		
	})
	//判断正确选项数量4个选项的
	function judgeYes4() {
		if ($.trim(A)==""||$.trim(B)==""||$.trim(C)==""||$.trim(D)==""||$.trim(topTopic)==""||$.trim(topAnswerDetail)=="") {
			layer.msg("题目及答案信息输入不得为空");
			return false;
		}
		var num = 0;
		
		if (Aans=="yes") {
			num+=1;
		}
		if (Bans=="yes") {
			num+=1;
		}
		if (Cans=="yes") {
			num+=1;
		}
		if (Dans=="yes") {
			num+=1;
		}
		if (num>1||num==0) {
			layer.msg("必须有一个，且只能有一个正确答案");
			return false;
		};
		
		return true;
	}
	//判断正确选项数量,两个选项的
	function judgeYes2() {
		if ($.trim(A)==""||$.trim(B)==""||$.trim(topTopic)==""||$.trim(topAnswerDetail)=="") {
			layer.msg("题目及答案信息输入不得为空");
			return false;
		}
		var num = 0;
		if (Aans=="yes") {
			num+=1;
		}
		if (Bans=="yes") {
			num+=1;
		}
		if (num>1||num==0) {
			layer.msg("必须有一个，且只能有一个正确答案");
			return false;
		};
		
		return true;
	}
	//去除所选的图片
	$("#deletenewImg").click(function() {
		$("#newImg").val("");
	})
	//去除所选的图片
	$("#deleteaddnewImg").click(function() {
		$("#addnewImg").val("");
	})
	
})
