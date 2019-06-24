<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	String path = request.getContextPath();
%>
<head>
<meta charset="UTF-8">
<title>学生详情</title>
<script type="text/javascript" src=<%=request.getContextPath()+"/js/jquery-3.3.1.js" %>></script>

<script type="text/javascript" src=<%=path+"/bootstrap-datatable/js/jquery.dataTables.min.js"%>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-datatable/js/dataTables.bootstrap.min.js"%>></script>
<script src=<%=path+"/layui/layui.js"%> charset="utf-8"></script>
<script type="text/javascript" src=<%=path+"/js/datatables_setting.js" %>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-3.3.7-dist/js/bootstrap.min.js"%>></script>
<link type="text/css" href="<%=path %>/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path %>/layui/css/layui.css"  media="all">
<link type="text/css" href="<%=path %>/bootstrap-datatable/css/dataTables.bootstrap.min.css" rel="stylesheet">
</head>
	<style>
		#main{
		    width:700px;
		    height:700px;
 		    background-color:#ecf0f5; 
		    display: block;
		    margin:0 auto;
		}
	</style>
<body>
<div id="main">
	<div class="content-wrapper">
	  	<section class="content-header">
	  		
	  	</section>
	  	<section class="content">
	  	 	<div class="row">
	  	 		<div class="col-md-12">
            <!-- /.box-header -->
            	<div class="box-body">
					<div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">

									                       				 
										<table id="example1" class="table table-bordered table-hover">
											<thead>
												<tr role="row">
													<th >科目</th>
													<th >已打学时</th>
													<th >总学时</th>			
													<th >考试得分</th><!-- 未考试的写无 -->
												</tr>
											</thead>
										</table>

							</div>
						</div>
            <!-- /.box-body -->
          
	  	 		</div>
	  	 		
	  	 	</div>
	  	</section>
	  </div>
</div>	  
	 <script type="text/javascript">
		$(document).ready( function () {		
			$('#example1').DataTable({
		    "processing": true,
		    "searching": false,
			"lengthMenu" : [ 5, 10, 20, 50 ],
			'iDisplayLength': 5, //每页初始显示5条记录
			"pagingType" : "full_numbers",
			"searching" : false, // 是否开启搜索
			"paging" : true,
			"lengthChange" : true,
			"ordering" : true,
			"info" : true,
			"autoWidth" : true,
			"language" : {
				"sProcessing" : "加载中...",
				"sLengthMenu" : "每页显示 _MENU_ 条记录",
				"sZeroRecords" : "没有匹配的结果",
				"sInfo" : "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
				"sInfoEmpty" : "显示第 0 至 0 条结果，共 0条",
				"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
				"sInfoPostFix" : "",
				"sSearch" : "搜索:",

				"sUrl" : "",
				"sEmptyTable" : "无记录",
				"sLoadingRecords" : "载入中...",
				"sInfoThousands" : ",",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "上一页",
					"sNext" : "下一页",
					"sLast" : "末页"
				},

			},
		    "ajax": {
		        "url": "studentparticulars.action",
		    },
		    "columns": [
		        {"data": "subName"},
		        {
		        	
		        	"data": "strTime","orderable" : false,
		        	"render" : function(data) {
		        		if(data==null){
		        			data="还未打卡";
		        		}
		        		return data;
		        	}
		        	
		        	
		        },
		        {"data": "subTime","orderable" : false},
		        {
		        	"data": "susScore",
		        	"orderable" : false,
					"render" : function(data, type, full, meta) {
						
						if (data == null) {
							data = "未考试";
						} else {
							data = data;
						}
						return data;
						}
		        },
		       
		  	  ]
			});
		});
	 </script>
	 
</body>
</html>