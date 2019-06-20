$(function(){
	var map = new BMap.Map("container");        //在container容器中创建一个地图,参数container为div的id属性;
    var point = new BMap.Point(120.2,30.25);    //创建点坐标
    map.centerAndZoom(point, 14);                //初始化地图，设置中心点坐标和地图级别
    map.enableScrollWheelZoom();                //激活滚轮调整大小功能
    map.addControl(new BMap.NavigationControl());    //添加控件：缩放地图的控件，默认在左上角；
    map.addControl(new BMap.MapTypeControl());        //添加控件：地图类型控件，默认在右上方；
    map.addControl(new BMap.ScaleControl());        //添加控件：地图显示比例的控件，默认在左下方；
    map.addControl(new BMap.OverviewMapControl());  //添加控件：地图的缩略图的控件，默认在右下方； TrafficControl    
    var search = new BMap.LocalSearch("中国", {
      onSearchComplete: function(result){
        if (search.getStatus() == BMAP_STATUS_SUCCESS){
          var res = result.getPoi(0);
          var point = res.point;
          map.centerAndZoom(point, 11);
        }
      },renderOptions: {  //结果呈现设置，
        map: map,  
        autoViewport: true,  
        selectFirstResult: true
      } ,onInfoHtmlSet:function(poi,html){//标注气泡内容创建后的回调函数，有了这个，可以简单的改一下返回的html内容了。
         html.innerHTML = '<table style="overflow:hidden;table-layout:fixed;width:100%;font:12px arial,simsun,sans-serif" cellspacing="0"><tbody><tr><td style="vertical-align:top;width:38px;white-space:nowrap;word-break:keep-all">地址：&nbsp;</td><td style="line-height:16px">福建省厦门市思明区软件园二期观日路56号1楼1&nbsp;</td></tr><tr><td style="vertical-align:top;width:38px;white-space:nowrap;word-break:keep-all">电话：&nbsp;</td><td style="line-height:16px">0592-8888888&nbsp;</td></tr></tbody></table>';                 
      }//这一段可以不要，只不过是为学习更深层次应用而加入的。
    });
    function setCity(){
      search.search(document.getElementById("cityName").value);
    }
   search.search(document.getElementById("cityName").value);
})
    
