

layui.use(['laypage', 'layer'], function(){
  var laypage = layui.laypage
  ,layer = layui.layer; 
  var path = document.getElementById('path').value;  
  $.ajax({
		url:path+"/school/selectAllSchool.action?",
		async:true,
		type:"POST",
		data:{},
		dataType:"json",
		success:function(data){
			 //调用分页
			  laypage.render({
			    elem: 'paging'
			    ,count: data.length
			    ,limit:5
			    ,jump: function(obj){
			      //模拟渲染
			      document.getElementById('schoolList').innerHTML = function(){
			        var arr = []
			        ,thisData = data.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
			        layui.each(thisData, function(index, item){			        	
			        	var str = '<div class="row jx-list-detail-page">\n'+
			        			  '<div class="col-md-2">\n'+
			        			  '<a href="" ><img src='+path+'/images/school/jx_school.jpg alt="广粤驾校">\n'+
			        			  '</a>\n'+
			        			  '</div>\n'+
			        			  '<div class="col-md-6 col-md-offset-1 jx-info">\n'+
			        			  '<p class="jx-list-info" style="margin-top:10px;">\n'+
			        			  '<span><a href="" style="font-size: 18px;">'+item.schName+'</a></span>\n'+
			        			  '</p>\n'+
			        			  '<p class="jx-list-address" style="margin-top:10px;"><span>驾校地址：</span><span>'+item.schAddress+'</span></p>\n'+
			        			  '<p class="jx-list-address" style="margin-top:10px;"><span>车型：</span><span>C1/C2</span></p>\n'+
			        			  '</div>\n'+
			        			  '<div class="col-md-2 jx-distance">\n'+
			        			  '<p class="jx-list-money">￥'+item.schCharge+'</p>\n'+
			        			  '</div>\n'+
			        			  '</div>';
			        	arr.push(str);
			        });
			        return arr.join('');
			      }();
			    }
			  });
		},
		error:function(){
			layer.msg("操作失败!");
		}
	});
  
});



	
										