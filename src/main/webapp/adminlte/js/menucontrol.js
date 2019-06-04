//控制菜单打开的
$(document).ready(function(){
    var url = document.location.href;
    var index1 = url.lastIndexOf('/');
    var page_name = url.substring(index1 + 1, url.length);
    var a_l = $('a');
    a_l.each(function () {
        var that = $(this);
        var url2 = that.attr('href');
        var index2 = url2.lastIndexOf('/');
        var page_name2 = url2.substring(index2 + 1, url2.length);
        if (page_name == page_name2) {
        	console.log($(this).parent());
            $(this).parent().parent().addClass('menu-open');
            $(this).parent().parent().parent().addClass('active');
        }
    });          
});