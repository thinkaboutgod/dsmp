$(function(){
	 var path = $("#path").val();
	 $.ajax({
         dataType: 'text',
         type: 'POST',
         url: path+'/home/schoolRanking.action?',
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
        					  '<div class="rank_first_jx">\n'+
        				      '<a href='+path+'/school/schoolInfo.action?schId='+msge[i].parameterId+' target="_blank" ><img src='+path+'/images/ranking/timg.jpg alt=""></a>\n'+
        					  '</div>\n'+
        					  '<div class="info">\n'+
        					  '<div class="jx-info-name"><a href='+path+'/school/schoolInfo.action?schId='+msge[i].parameterId+' title='+msge[i].name+' target="_blank">'+msge[i].name+'</a></div>\n'+
        					  '<div><img src='+path+'/images/ranking/star'+msge[i].starAvg+'.png alt=""></div>\n'+
        					  '<div><span>'+msge[i].data+'点评</span></div>\n'+
        					  '</div></div></div>'
        			$("#rank_jx").append(str);
        		}else{
        			var str1 = '<ul class="rank_first_jx_ul">\n'+
        					   '<li>\n'+
        					   ' <a href='+path+'/school/schoolInfo.action?schId='+msge[i].parameterId+' target="_blank" title='+msge[i].name+'><div>'+msge[i].name+'</div><div><img src='+path+'/images/ranking/star'+msge[i].starAvg+'.png alt=""></div><div>'+msge[i].data+'点评</div></a>'
        					   '</li>\n'+
        					   '</ul>'
        			$("#rank_jx").append(str1);
        		}
        		
        	}
         }
     });
	
});