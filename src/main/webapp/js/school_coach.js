$(function() {
	
	$.extend($.fn.dataTable.defaults, dataTableSeetings);// 公共初始化设置
	
	datatable_otherSet = {
			"ajax" : "../coach/selectCoasByCondition.action",
			"autoWidth" : false,
			"columns" : [
					{
						"data" : "coaAccount"
					},
					{
						"data" : "coaName"
					},
					{
						"data" : "coaSex"
					},
					{
						"data" : "coaAddress"
					},
					{
						"data" : "coaLevel"
					},
//					{
//						"data" : "coaRegistertime",
//						"render" : function(data, type, full, meta) {
//						return data = new Date(data).format("yyyy-MM-dd hh:mm:ss");
//						}
//					},
					{
						"data" : "coaStatus",
						"orderable" : false, // 禁用排序
					},
					{
						"data" : "coaStatus",
						"orderable" : false, // 禁用排序
						"sDefaultContent" : '',
						"sWidth" : "",
						"render" : function(data, type, full, meta) {
							state = data;
							if (state == '启用') {
								data = '<button id="start" class="btn btn-danger btn-sm bt_qi" >禁用</button>';
							} else if (state == '禁用'){
								data = '<button id="forbit" class="btn btn-success btn-sm bt_qi" >启用</button>';
							}
							data = data+'&nbsp;<button class=" detail btn btn-success btn-sm detail">查看详情</button>'
							return data;
						}
					},  ],

			"fnServerParams" : function(aoData) {//设置参数
				aoData._rand = Math.random();
				aoData.push({
					"name" : "name",
					"value" : $("#cname").val()
				}, {
					"name" : "account",
					"value" : $("#caccount").val()
				}, {
					"name" : "schId",
					"value" : $("#schId").val()
				},{
					"name" : "beginTime",
					"value" : $("#begintime").val()
				}, {
					"name" : "endTime",
					"value" : $("#endtime").val()
				}

				);
			},
		};
	
	
	var table = $("#coachTable").DataTable(datatable_otherSet);//初始化
	
	// 选择行,两个表格公用
	$('tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');

		}
	});
	
	
	//自定义搜索
	$("#buttonsearch").on("click", function() {
		table.ajax.reload(null, false);// 刷新数据方法,false代表保持当前页
	})
	
	
	// 启用禁用方式
	$(document).on("click", ".bt_qi", function() { 
		//此处拿到选择行的数据中的id 
		var id = table.row($(this).parent().parent()).data().coaId;  
		var button = $(this);
		var preText = button.parent().prev().text();
		var text = $(this).text();
		var state;
		
		if ("启用" == text) {
			state="start"
		} else if ("禁用" == text) {
			state="forbid"
		};
		$.ajax({
			url : "../coach/changeCoachState.action",
			async : true,
			type : "POST",
			data :  {coaId : id,state:state,preText:preText}  ,
			dataType : "text",
			success : function(data) {
				var result = JSON.parse(data);
				if (result.myresult=="success") {
					layer.msg("修改成功");
					switch (text) {
					case "启用":
						button.parent().prev().text("启用");
						button.text("禁用");
						button.removeClass("btn-success");
						button.addClass("btn-danger");
						break;
					case "禁用":
						button.parent().prev().text("禁用");
						button.text("启用");
						button.removeClass("btn-danger");
						button.addClass("btn-success");
						break;
					
					}
				}else if (result.myresult=="failed") {
					layer.msg("修改失败");
				}
			},
			error : function() {
				layer.msg("服务器繁忙");
			}
			
		})
	})
	
	// 查看详细信息
	$(document).on("click", ".detail", function() { 
		//此处拿到选择行的数据中的id 
		var da = table.row($(this).parent().parent()).data();
		$("#accountDe").val(da.coaAccount);
		$("#nameDe").val(da.coaName);
		$("#sexDe").val(da.coaSex);
		$("#addressDe").val(da.coaAddress);
		$("#phoneDe").val(da.coaAccount);
		$("#idCardDe").val(da.coaIdcard);
		$("#levelDe").val(da.coaLevel);
		$("#introductionDe").val(da.coaIntroduction);
		$("#carDe").val(da.tbCar.carPlatenum);
		$("#coachDetail").modal("show");
	})
	
	
	// 添加教练
	$(document).on("click", "#btn_addCoach", function() { 
		$("#addCoach").modal("show");
	})
	
	// 添加教练
	$(document).on("click", "#btn_add", function() { 
		var schId = $("#schId").val();
		var account = $.trim($("#accountNew").val());
		var passward = $.trim($("#pwdNew").val());
		var name = $.trim($("#nameNew").val());
		var sex = $("input:radio:checked").val();
		var birthday = $.trim($("#birthdayNew").val());
		var idCard = $.trim($("#idCardNew").val());
		var level = $("#levelNew").val();
		var address = $.trim($("#addressNew").val());
		var introduction = $.trim($("#introductionNew").val());
			
		//验证手机号
	    if (!checkAccount()) {
	        return false;
	     }
	    
		  //验证密码
	     if (!checkPwd()) {
	         return false;
	     }
	     
		if(name==""||name == $("#nameNew").attr("placeholder")){
			layer.msg("请输入姓名");
	        return false;
		}
		
		if(sex==""||sex == $("#sexNew").attr("placeholder")){
			layer.msg("请选择性别");
	        return false;
		}
		
		if(birthday==""||birthday == $("#birthdayNew").attr("placeholder")){
			layer.msg("请输入出生日期");
	        return false;
		}
		
		if(idCard==""||idCard == $("#idCardNew").attr("placeholder")){
			layer.msg("请输入身份证号");
	        return false;
		}
		
		if(level==0||level == $("#levelNew").attr("placeholder")){
			layer.msg("请选择资质");
	        return false;
		}

		if(adress==""||adress == $("#adressNew").attr("placeholder")){
			layer.msg("请输入地址");
	        return false;
		}
		
		if(introduction==""||introduction == $("#introductionNew").attr("placeholder")){
			layer.msg("请输入简介");
	        return false;
		}
		
		$.ajax({
			url : "../coach/addCoach.action",
			async : true,
			type : "POST",
			data : {schId:schId,"account":account,"passward":passward,"name":name,sex:sex,birthday:birthday,
			"idCard":idCard,"introduction":introduction,"address":address,"level":level},
			dataType : "text",
			success : function(data) {
				var result = JSON.parse(data);
				if (result.myresult=="success") {
					layer.msg("添加教练成功");
					$(".add").val("");
					$("#levelNew").val("0");
				}else if (result.myresult=="failed") {
					layer.msg("添加教练失败");
				}
			},
			error : function() {
				layer.msg("服务器繁忙");
			}
			
		})
	})

	    function checkAccount() {
	        var phone = $.trim($("#accountNew").val());
	        var phoneReg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
	        if (phone == "" || phone == $("#accountNew").attr("placeholder")) {
	       		layer.msg("请输入手机号");
	            return false;
	        } else if (!phoneReg.test($("#accountNew").val())) {
	            layer.msg("请输入正确的手机号");
	            return false;
	        } else {
	           return true;
	        }
	    }


	    function checkPwd() {
	        var pwd = $.trim($("#pwdNew").val());
	        var regpwd = /^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$/;
	        if (pwd == "" || pwd == $("#pwdNew").attr("placeholder")) {
	                layer.msg("请输入密码");
	            return false;
	        } else if (!regpwd.test($("#pwdNew").val())) {
	               layer.msg("密码格式有误(字母、数字或者符号,最短6位,最长20位)");
	            return false;
	        } else {
	            return true;
	        }
	        
	    }

	    
	
})