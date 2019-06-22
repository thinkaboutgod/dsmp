<%--弃用 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>驾校评价</title>	
<%
	String path = request.getContextPath();
%>	
	<link rel="stylesheet" type="text/css" href=<%=path+"/bootstrap-3.3.7-dist/css/bootstrap.css" %>>
<!--     <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/bootstrap-3.3.4.css"> -->
	
    <link rel="stylesheet" href=<%=path+"/f-star-rating/css/font-awesome.min.css"%>>
    <link href=<%=path+"/f-star-rating/css/star-rating.css"%> media="all" rel="stylesheet" type="text/css"/>
    <!--suppress JSUnresolvedLibraryURL -->
<!--     <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script> -->
	<script type="text/javascript" src=<%=path+"/js/jquery-3.3.1.js" %>></script>
    <script src=<%=path+"/f-star-rating/js/star-rating.js"%> type="text/javascript"></script>
        <script>
        jQuery(document).ready(function () {
            $("#input-21f").rating({
                starCaptions: function (val) {
                    if (val < 3) {
                        return val;
                    } else {
                        return 'high';
                    }
                },
                starCaptionClasses: function (val) {
                    if (val < 3) {
                        return 'label label-danger';
                    } else {
                        return 'label label-success';
                    }
                },
                hoverOnClear: false
            });
            var $inp = $('#rating-input');

            $inp.rating({
                min: 0,
                max: 5,
                step: 1,
                size: 'lg',
                showClear: false
            });

            $('#btn-rating-input').on('click', function () {
                $inp.rating('refresh', {
                    showClear: true,
                    disabled: !$inp.attr('disabled')
                });
            });


            $('.btn-danger').on('click', function () {
                $("#kartik").rating('destroy');
            });

            $('.btn-success').on('click', function () {
                $("#kartik").rating('create');
            });

            $inp.on('rating.change', function () {
                alert($('#rating-input').val());
            });


            $('.rb-rating').rating({
                'showCaption': true,
                'stars': '3',
                'min': '0',
                'max': '3',
                'step': '1',
                'size': 'xs',
                'starCaptions': {0: 'status:nix', 1: 'status:wackelt', 2: 'status:geht', 3: 'status:laeuft'}
            });
            $("#input-21c").rating({
                min: 0, max: 8, step: 0.5, size: "xl", stars: "8"
            });
        });
    </script>
</head>
<body>
	<div class="container">
    <div class="page-header">
        <h2>驾校评价
            <small>welcome</small>
        </h2>
    </div>

    <form action="addSchoolRating.action" method="post">
    	<input id="sch_id" type="hidden" name="sch_id" value="${sch_id }">
    	<input id="stu_id" type="hidden" name="stu_id" value="${stu_id }">
        <input id="input-21e" name="starNum" value="0" type="text" class="rating" data-min=0 data-max=5 data-step=1 data-size="xs"
               title="">
        <textarea id="ratingContent" name="ratingContent" rows="" cols=""></textarea>
      
        <div class="form-group" style="margin-top:10px">
            <button id="schoolSubBtn" type="button" class="btn btn-primary">Submit</button>
            <button id="reset" type="reset" class="btn btn-default">Reset</button>
<!--             <button type="button" class="btn btn-danger">Destroy</button> -->
<!--             <button type="button" class="btn btn-success">Create</button> -->
        </div>
    </form>
    <hr>

</div>
</body>
</html> --%>