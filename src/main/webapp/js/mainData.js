
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
	        {"data": ""},
	        {"data": "xm"},
	        {"data": "xbdm"},
	        {"data": "mzdm"},
	        {"data": "rxnd"},
	        {"data": "xzbmc"},
	        {"data": "jgmc"},
	        {"data": "jgc"}
	    ]
	});
});