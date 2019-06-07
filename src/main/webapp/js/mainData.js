
$(document).ready( function () {
	$('#example1').DataTable({
	    "processing": true,
	    "searching": true,
	    "serverSide": true,
	    "ajax": {
	        "url": "../belongtocoach.action",
	        "data": function (d) {
	            var info = $('#example1').DataTable().page.info();
	            d.pageNo = info.page;}
	    },
	    "columns": [
	        {"data": "stuAccount"},
	        {"data": "stuName"},
	        {"data": "stuSignuptime"},
	        {"data": "stuAccount"},
	        {"data": "stuAddress"},
	        {"data": "stuSex"},
	        {"data": "tbSubject.subName"},
	        {"data": ""}
	    ]
	});
});