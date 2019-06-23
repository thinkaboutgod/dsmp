$(function(){
	 var path = $("#path").val();
	 $.ajax({
         dataType: 'text',
         type: 'POST',
         url: path+'/home/coachRanking.action?',
         data: '',
         success: function success(res) { 
        	var msge = JSON.parse(res);
        	if(msge.length == 0){
        		return false;
        	} 
        	for(var i=0;i<5;i++){ 
        		if(i == 0){
        			var str = '<div class="rank_first">\n'+
        					  '<div class="rank_data">\n'+
        					  '<div class="rank_first_jl">\n'+
        				      '<a href='+path+'/tbcoach/coachInfo.action?coaId='+msge[i].parameterId+' title='+msge[i].name+' target="_blank"><img src='+path+'/images/ranking/untitled.png alt=""></a>'+
        					  '</div>\n'+
        					  '<div class="info">\n'+
        					  '<div>\n'+
        					  '<div class="info-name"><a href='+path+'/tbcoach/coachInfo.action?coaId='+msge[i].parameterId+'  target="_blank" title='+msge[i].name+'>'+msge[i].name+'</a></div>\n'+
        					  '</div>\n'+
        					  '<div style="margin-top:5px;">\n'+
        					  '<span>所属驾校：'+msge[i].phone+'</span>\n'+
        					  '</div>\n'+
        					  '<div >\n'+
        					  '<img style="margin-bottom:20px;" src='+path+'/images/ranking/star'+msge[i].starAvg+'.png alt="">\n'+
        					  '</div></div></div></div>'
        			$("#rank_jl").append(str);
        		}else{
        			var str1 = '<ul class="rank_first_jl_ul">\n'+
        					   '<li>\n'+
        					   '<a href='+path+'/tbcoach/coachInfo.action?coaId='+msge[i].parameterId+' target="_blank" title='+msge[i].name+'><div>'+msge[i].name+'</div><div><img src='+path+'/images/ranking/star'+msge[i].starAvg+'.png alt=""></div></a>'
        					   '</li>\n'+
        					   '</ul>'
        			$("#rank_jl").append(str1);
        		}
        		
        	}
         }
     });
	
});
