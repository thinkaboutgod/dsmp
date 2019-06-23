<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学员个人中心-基本信息</title>
<style type="text/css">
	#leftId{
		float: left;
		width: 50%;
	}
	#rightId{
		float: right;
		width: 40%;
	}
	
</style>
</head>
<body>
		
	<div id="panel-group">
	  				<!-- 3 -->  				
	  	<div id="leftId" class="panel panel-primary">  					
	  		<div class="panel-heading">  						
	  			<h3 class="panel-title">学员基本情况</h3>	  					
	  			
	  		</div>  					
	  		<div class="panel-body">  						
	  		<table class="table">  							
<!-- 	  		<thead>  								 -->
<!-- 	  		<tr>  									 -->
<!-- 	  		<th>#</th>  									 -->
<!-- 	  		<th>产品</th>  									 -->
<!-- 	  		<th>数量</th>  									 -->
<!-- 	  		<th>总金额</th>  									 -->
<!-- 	  		<th>业务员</th>  							 -->
<!-- 	  			</tr>  							 -->
<!-- 	  			</thead>  							 -->
	  			
	  			<tbody>  								
		  			<tr>  									
			  			<th>姓名：</th>  									
			  			<th>${stuDetail.stuName }</th>  																	
		  			</tr>  							
		  			<tr>  									
			  			<th>性别：</th>  									
			  			<th>${stuDetail.stuSex }</th>  																	
		  			</tr>  							
		  			<tr>  									
			  			<th>当前科目：</th>  									
			  			<th>${stuDetail.tbSubject.subName }</th>  																	
		  			</tr>  							
		  			<tr>  									
			  			<th>当前科目状态：</th>  									
			  			<th>${stuDetail.stuBookingstate }</th>  																	
		  			</tr>  							
		  			<tr>  									
			  			<th>当前科目完成学时/任务学时(单位：秒)：</th>  									
			  			<th>${overTimeLength}/${needStudyTime }</th>  																	
		  			</tr>  							
		  			<tr>  									
			  			<th>教练：</th>  									
			  			<th>${stuDetail.tbCoach.coaName }</th>  																	
		  			</tr>  							
		  			<tr>  									
			  			<th>驾校：</th>  									
			  			<th>${stuDetail.tbSchool.schName }</th>  																	
		  			</tr>  
		  			<c:forEach begin="0" items="${subjectScoreList }" var="i" varStatus="status">
		  			<tr>  									
						<th>${i.tbSubject.subName }(单位：分):</th> 
						<th>${i.susScore }</th> 
		  			</tr>  							
					</c:forEach>
		  													  			 																									
 						
	  			</tbody>  					
	  		</table>  				
	  	</div>  			
	  </div>
	
	
	
	
	<!-- 4 -->  			
	<div id="rightId" class="panel panel-primary">  		
		<div class="panel-heading">  	
				<h3 class="panel-title">${stuDetail.tbSubject.subName }学时进度</h3>  	
	  	</div>  					
	  	<div class="panel-body">  						
	  	<button class="btn btn-primary" role="button">${overTimeLength}/${needStudyTime }(单位：秒)</button> 							
	  	<div class="progress">  								
	  	<div class="progress-bar progress-bar-info progress-bar-striped" role="progressbar" aria-valuenow=60 aria-valuemin=0 aria-valuemax=100 style="width: ${percentage}">  								
	  	</div>  							
	  	<span class="sr only">完成 ${percentage}</span>  								
	  	</div>  						
	  								
		</div>   
	

	</div>
	
	
	</div>
</body>
</html>