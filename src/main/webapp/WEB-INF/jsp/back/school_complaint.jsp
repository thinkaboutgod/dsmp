<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>申诉渠道</title>
<%
	String path = request.getContextPath();
%>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<script type="text/javascript" src=<%=request.getContextPath()+"/js/jquery-3.3.1.js" %>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-3.3.7-dist/js/bootstrap.min.js"%>></script>
<!-- FastClick -->
<script type="text/javascript" src=<%=path+"/bootstrap-datatable/js/jquery.dataTables.min.js"%>></script>
<script type="text/javascript" src=<%=path+"/bootstrap-datatable/js/dataTables.bootstrap.min.js"%>></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/jquery.slimscroll.min.js"%>></script>
<!-- AdminLTE App -->
<script type="text/javascript" src=<%=path+"/adminlte/js/app.min.js"%>></script>
<!-- AdminLTE for demo purposes -->
<script src=<%=path+"/layui/layui.js"%> charset="utf-8"></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/fastclick.js"%>></script>
<script type="text/javascript" src=<%=path+"/adminlte/js/demo.js"%>></script>

<script type="text/javascript" src=<%=path+"/adminlte/js/menucontrol.js"%> ></script>
<script type="text/javascript" src=<%=path+"/js/datatables_setting.js" %>></script>
<script type="text/javascript" src=<%=path+"/js/Date.js" %>></script>
<script type="text/javascript" src=<%=path+"/js/schoolmanagement/school_complaint.js" %>></script>

    <link type="text/css" href="<%=path %>/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/font-awesome.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/ionicons.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/bootstrap-datatable/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/adminlte.min.css" rel="stylesheet">
    <link type="text/css" href="<%=path %>/adminlte/css/all-skins.min.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=path %>/layui/css/layui.css"  media="all">
</head>
<style>
		#main{
		    width:700px;
		    height:500px;
 		    background-color:#ecf0f5; 
		    display: block;
		    text-align:center;
		    margin:0 auto;
		}
		#headportrait{
			 float:left;
			 width:20%;
			 height:120px;
			 padding-left:40px;
		}
		#reply{
			float:right;
			width:40%;
			height:120px;
		}
		
		#schoolmsg{
			float:right;
			width:40%;
			height:120px;
		}

</style>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<jsp:include page="header.jsp"></jsp:include>
		<jsp:include page="sliderbar.jsp"></jsp:include>
		<div class="content-wrapper">
			<section class="content">
				<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
				  <ul class="layui-tab-title">
				    <li class="layui-this"><h3>申诉维权</h3></li>
				    <li id="activistreply"><h3>维权回复</h3></li>
				  </ul>
				  <div class="layui-tab-content">
				  	    <div class="layui-tab-item layui-show">
							<div>
								<div id="headportrait">
									<img alt="50x50" width="100" height="100" id="image" src=<%=path+request.getAttribute("schHeadimg")%>>
								</div>
								<div id="reply">
								
									
								</div>
								<div id="schoolmsg"><br>
									会员账号：<span><%=request.getAttribute("schAccount")%></span><br>
									驾校名称：<span><%=request.getAttribute("schName")%></span><br>
									法人代表：<span><%=request.getAttribute("schBossname")%></span><br>
									运营状态：<span><%=request.getAttribute("schSignupstatus")%></span><br>
								</div>
				
							</div><hr>				
							<div class="row">
								<div class="col-sm-12">
									<div id="main">
										<form id="mainForm" class="form-horizontal">
											<div class="form-group">
												<label class="col-md-3 control-label">申诉人</label>
												<div class="col-md-6">
													<input type="text" style="width:333px;height:30px;" name="sname">
			<!-- 										<textarea rows="1" cols="48"></textarea> -->
												</div>
											</div>
										    <div class="form-group">
										        <label class="col-md-3 control-label">申诉内容</label>
										        <div class="col-md-6">
										            <textarea class="form-control" name="content" onkeyup="textAreaChange(this)" onkeydown="textAreaChange(this)" rows="5"></textarea>
										            <div class="text-right">
										                <em style="color:red">200</em>/<span>200</span>
										            </div>
										        </div>
										        
										    </div>
										    <div class="form-group">
										        <div class="col-md-offset-3 col-md-6">
										            <button type="button" class="btn btn-danger btn-lg" id="mainConfirm">确认提交</button>
										        </div>
										    </div>
										</form>
						
									</div>
								</div>
							</div>				  	    	
				  	    
				  	    </div>
					    <div class="layui-tab-item">
							<div class="row">
								<div class="col-sm-12">
								<!-- body -->
				  	 				<div class="box-body">
										<div id="complaintmsg_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
											<table id="complaintmsg" class="table table-bordered table-hover">
												<thead>
													<tr role="row">
														<th >申诉时间</th>
														<th >申诉内容</th>
														<th >申诉回复</th>
													</tr>
												</thead>
												<tbody></tbody>
											</table>							
										</div>
									</div>
									<!-- /body -->
								</div>
							</div>
					    	
					    </div>
				  </div>
				</div> 
			</section>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
<script type="text/javascript">
    //显示限制输入字符method
    function textAreaChange(obj){
        var $this = $(obj);
        var count_total = $this.next().children('span').text();
        var count_input = $this.next().children('em');
        var area_val = $this.val();
        if(area_val.len()>count_total){
            area_val = autoAddEllipsis(area_val,count_total);//根据字节截图内容
            $this.val(area_val);
            count_input.text(0);//显示可输入数
        }else{
            count_input.text(count_total - area_val.len());//显示可输入数
        }
    }
    //得到字符串的字节长度
    String.prototype.len = function(){
        return this.replace(/[^\x00-\xff]/g, "xx").length;
    };
    /*
     * 处理过长的字符串，截取并添加省略号
     * 注：半角长度为1，全角长度为2
     * pStr:字符串
     * pLen:截取长度
     * return: 截取后的字符串
     */
    function autoAddEllipsis(pStr, pLen) {
        var _ret = cutString(pStr, pLen);
        var _cutFlag = _ret.cutflag;
        var _cutStringn = _ret.cutstring;
        return _cutStringn;
    }
    /*
     * 取得指定长度的字符串
     * 注：半角长度为1，全角长度为2
     * pStr:字符串
     * pLen:截取长度
     * return: 截取后的字符串
     */
    function cutString(pStr, pLen) {
        // 原字符串长度
        var _strLen = pStr.length;
        var _tmpCode;
        var _cutString;
        // 默认情况下，返回的字符串是原字符串的一部分
        var _cutFlag = "1";
        var _lenCount = 0;
        var _ret = false;
        if (_strLen <= pLen/2){_cutString = pStr;_ret = true;}
        if (!_ret){
            for (var i = 0; i < _strLen ; i++ ){
                if (isFull(pStr.charAt(i))){_lenCount += 2;}
                else {_lenCount += 1;}
                if (_lenCount > pLen){_cutString = pStr.substring(0, i);_ret = true;break;}
                else if(_lenCount == pLen){_cutString = pStr.substring(0, i + 1);_ret = true;break;}
            }
        }
        if (!_ret){_cutString = pStr;_ret = true;}
        if (_cutString.length == _strLen){_cutFlag = "0";}
        return {"cutstring":_cutString, "cutflag":_cutFlag};
    }
    /*
     * 判断是否为全角
     *
     * pChar:长度为1的字符串
     * return: true:全角
     *         false:半角
     */
    function isFull (pChar){
        if((pChar.charCodeAt(0) > 128)){return true;}
        else{return false;}
    }
</script>	
</html>