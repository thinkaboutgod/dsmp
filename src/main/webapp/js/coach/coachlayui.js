

layui.use(['laypage', 'layer'], function(){
  var laypage = layui.laypage
  ,layer = layui.layer; 
  var path = document.getElementById('path').value;  
  $.ajax({
		url:path+"/tbcoach/selectAllCoach.action?",
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
			      document.getElementById('coachList').innerHTML = function(){
			        var arr = []
			        ,thisData = data.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
			        layui.each(thisData, function(index, item){			        	
			        	var str = '<div class="list-each">\n'+
			        			  '<div class="list-img">\n'+
			        			  '<a href='+path+'/tbcoach/coachInfo.action?coaId='+item.coaId+' target="_blank"><img src='+path+'/images/coach/coachImg.jpg alt="教练"></a>\n'+			     
			        			  '</div>\n'+
			        			  '<div class="list-info">\n'+
			        			  '<div class="info-top">\n'+
			        			  '<div class="info-name"><a href='+path+'/tbcoach/coachInfo.action?coaId='+item.coaId+' target="_blank">'+item.coaName+'</a></div>\n'+
			        			  '<div class="info-old">金牌教练</div>\n'+
			        			  '</div>\n'+
			        			  '<div class="info-center">\n'+
			        			  '<span>所属驾校：</span>\n'+
			        			  '<a href='+path+'/school/schoolInfo.action?schId='+item.schId+' target="_blank">'+item.tbSchool.schName+'</a>\n'+
			        			  '</div>\n'+
			        			  '</div>\n'+
			        			  '<div class="list-prize">\n'+
			        			  '<div class="prize-money">报名费用￥'+item.tbSchool.schCharge+'</div>\n'+
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

	
										