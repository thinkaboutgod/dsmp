
layui.use(['upload','form'], function(){
  var $ = layui.jquery
  ,upload = layui.upload,form = layui.form;
    
  
  //同时绑定多个元素，并将属性设定在元素上
  upload.render({
    elem: '.demoMore'
    ,before: function(){
      layer.tips('接口地址：'+ this.url, this.item, {tips: 1});
    }
    ,done: function(res, index, upload){
      var item = this.item;
      console.log(item); //获取当前触发上传的元素，layui 2.1.0 新增
    }
  })
  var path =$("#path").val();
  //选完文件后不自动上传
  upload.render({
    elem: '#test8'
    ,url: path+'/idCard/business.action'
    ,auto: false
    //,multiple: true
    	,choose: function(obj){  //上传前选择回调方法
            var flag = true;
            obj.preview(function(index, file, result){
                console.log(file);            //file表示文件信息，result表示文件src地址
                var img = new Image();
                img.src = result;
                img.onload = function () { //初始化夹在完成后获取上传图片宽高，判断限制上传图片的大小。
                    if(img.width <=3500 && img.height <=3500){                   	 
                        obj.upload(index, file); //满足条件调用上传方法
                        layer.msg("识别营业执照中，请稍等....");
                    }else{
                        flag = false;
                        layer.msg("您上传的图片分辨率必须小于3500*3500！");
                        return false;
                    }
                }
                return flag;
            });
        }
    ,done: function(res){	
      console.log(res);
      if(res.name != ""){  
    	  $("#sch_creditcode").val(res.creditcode);
    	  $("#sch_name").val(res.name);          
          $("#sch_type").val(res.type);
          $("#sch_address").val(res.address);
          $("#sch_bossname").val(res.bossname);
          $("#sch_registerCapital").val(res.registerCapital);        
          layer.msg("识别成功！");
      }else{
    	  layer.msg("识别失败，请选择营业执照进行识别！",{time:5000,btn:["知道了"]});
      }
     
    }
  });
  
  //普通图片上传
  var uploadInst = upload.render({
    elem: '#test1'
    ,url:  path+'/school/schoolEnter.action?'
    ,method: 'POST'
    ,data:{
    	phone: function(){
        return $('.phone').val();
    	},
    	password: function(){
    	    return $('.passwd').val();
    	},
    	sch_creditcode: function(){
    	    return $('#sch_creditcode').val();
    	},
    	sch_name: function(){
    	    return $('#sch_name').val();
    	},
    	sch_type: function(){
    	    return $('#sch_type').val();
    	},
    	sch_address: function(){
    	    return $('#sch_address').val();
    	},
    	sch_bossname: function(){
    		alert($('#sch_bossname').val());
    	    return $('#sch_bossname').val();
    	},
    	sch_registerCapital: function(){
    	    return $('#sch_registerCapital').val();
    	},
    	sch_introduce: function(){
    	    return $('#school_introduce').val();
    	},
    	sch_charge: function(){
    	    return $('#sch_charge').val();
    	}
    }
    ,choose: function(obj){
    	
      //预读本地文件示例，不支持ie8
      obj.preview(function(index, file, result){
        $('#demo1').attr('src', result); //图片链接（base64）
      });
    }
  ,auto: false
  ,bindAction: '#finish'
    ,done: function(res){
      //如果上传失败
      if(res.code > 0){
        return layer.msg('上传失败');
      }
      //上传成功
      if(res.myresult == "success"){
    	  layer.msg('入驻成功,2秒后跳转到登录界面，请稍等...');
    	  setTimeout(function () {
				window.open(path+'/student/login.action?');
          }, 2000);
      }else if(res.myresult == "failed"){
    	  layer.msg('信息有误，请重新填写信息提交');
      }else if(res.myresult == "already"){
    	  layer.msg('入驻失败，该账号已被注册');
      }else if(res.myresult == "fileErr"){
    	  layer.msg('头像上传失败，请重新提交');
      }else if(res.myresult == "creditcodeAlready"){
    	  layer.msg('入驻失败，该社会信用代码已被注册');
      }
      
    }
    ,error: function(){
      //演示失败状态，并实现重传
      var demoText = $('#demoText');
      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
      demoText.find('.demo-reload').on('click', function(){
        uploadInst.upload();
      });
    }
  });

});
