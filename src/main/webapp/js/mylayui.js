
layui.use(['upload','form'], function(){
  var $ = layui.jquery
  ,upload = layui.upload,form = layui.form;
  
  //事件所监听
  form.on('select(schools)', function(data){
	  console.log(data);
	  var path = $("#path").val();
	  var childArr = $("#teachers").children();
		for(var i=0;i<childArr.length;i++){
			if(i!=0){
				childArr[i].remove();
			}
		}
		var selectSchool = $("#schools").val();
		$.ajax({
			url:path+"/school/selectCoach.action?",
			async:true,
			data:{"selectSchool":selectSchool},
			dataType:"text",
			success:function(res){
				var arr = JSON.parse(res);
				for (var i = 0; i < arr.length; i++) {
					console.log(arr[i].coaName);
					$("#teachers").append("<option value="+arr[i].coaId+">"+arr[i].coaName+"</option>");
				}	
				form.render('select');//刷新select选择框渲染
			},
			error:function(){
				alert("操作失败！");
			}
		})
	});  
//普通图片上传
  var uploadInst = upload.render({
    elem: '#test1'
    ,url: '/upload/'
    ,before: function(obj){
      //预读本地文件示例，不支持ie8
      obj.preview(function(index, file, result){
        $('#demo1').attr('src', result); //图片链接（base64）
      });
    }
    ,done: function(res){
      //如果上传失败
      if(res.code > 0){
        return layer.msg('上传失败');
      }
      //上传成功
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
  
  //多图片上传
  upload.render({
    elem: '#test2'
    ,url: '/upload/'
    ,multiple: true
    ,before: function(obj){
      //预读本地文件示例，不支持ie8
      obj.preview(function(index, file, result){
        $('#demo2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
      });
    }
    ,done: function(res){
      //上传完毕
    }
  });
  
 
  
  //设定文件大小限制
  upload.render({
    elem: '#test7'
    ,url: '/upload/'
    ,size: 2222222 //限制文件大小，单位 KB
    ,done: function(res){
      console.log(res)
    }
  });
  
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
    ,url: path+'/idCard/idCard.action'
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
                        layer.msg("识别身份证中，请稍等....");
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
    	  
    	  $("#real_name").val(res.name);
          $("#idnum").val(res.IDCard);
          $("#student_address").val(res.address);
          $("#sex").val(res.sex);
          layer.msg("识别成功！");
      }else{
    	  layer.msg("请选择身份证图片进行识别！",{time:5000,btn:["知道了"]});
      }
     
    }
  });
  
  
  
  //拖拽上传
  upload.render({
    elem: '#test10'
    ,url: '/upload/'
    ,done: function(res){
      console.log(res)
    }
  });
  
  //多文件列表示例
  var demoListView = $('#demoList')
  ,uploadListIns = upload.render({
    elem: '#testList'
    ,url: '/upload/'
    ,accept: 'file'
    ,multiple: true
    ,auto: false
    ,bindAction: '#testListAction'
    ,choose: function(obj){   
      var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
      //读取本地文件
      obj.preview(function(index, file, result){
        var tr = $(['<tr id="upload-'+ index +'">'
          ,'<td>'+ file.name +'</td>'
          ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
          ,'<td>等待上传</td>'
          ,'<td>'
            ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
            ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
          ,'</td>'
        ,'</tr>'].join(''));
        
        //单个重传
        tr.find('.demo-reload').on('click', function(){
          obj.upload(index, file);
        });
        
        //删除
        tr.find('.demo-delete').on('click', function(){
          delete files[index]; //删除对应的文件
          tr.remove();
          uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
        });
        
        demoListView.append(tr);
      });
    }
    ,done: function(res, index, upload){
      if(res.code == 0){ //上传成功
        var tr = demoListView.find('tr#upload-'+ index)
        ,tds = tr.children();
        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
        tds.eq(3).html(''); //清空操作
        return delete this.files[index]; //删除文件队列已经上传成功的文件
      }
      this.error(index, upload);
    }
    ,error: function(index, upload){
      var tr = demoListView.find('tr#upload-'+ index)
      ,tds = tr.children();
      tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
      tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
    }
  });
  
  //绑定原始文件域
  upload.render({
    elem: '#test20'
    ,url: '/upload/'
    ,done: function(res){
      console.log(res)
    }
  });
  
});
