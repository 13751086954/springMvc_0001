<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <base href="<%=basePath%>">
     <base href="<%=path%>">
    
    <title>无权访问</title>    
	 <link rel="shortcut icon" type="image/x-icon" href="<%=path%>/favicon.ico" media="screen" /> 
     <script src="<%=path%>/static/BJUI/js/jquery-1.11.3.min.js"></script>
     <script src="<%=path%>/static/BJUI/js/jquery.cookie.js"></script>
     <script src="<%=path%>/static/BJUI/js/sha256.js"></script>
     <link href="<%=path%>/static/BJUI/themes/css/bootstrap.min.css" rel="stylesheet">

  </head>
  
  <body>
    <div class="bjui-pageHeader" style="background:#FFF;">
	 <div style="padding: 0 15px;">
        <h4 style="margin-bottom:20px;">
             您无权访问该页面
        </h4>	     
	    <div class="row" style=" margin-top:10px;">
	       <div class="col-md-6" style="padding:5px;">
	                <div class="alert alert-info" role="alert" style="margin:0 0 5px; padding:5px 15px;">	
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<div class="bjui-pageContent">
	    <div style="margin-top:5px; margin-right:300px; overflow:hidden;">
	        <div class="row" style="padding: 0 8px;">
	            <div class="col-md-12">
	                <div class="panel panel-default">
	                    <div class="panel-heading"><h3 class="panel-title">OpenAuth.Net/GIT</h3></div>
	
	                    <div class="panel-body">
	                        <iframe width="100%" height="100%" class="share_self" frameborder="0" scrolling="no" src="/home/git"></iframe>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
  </body>
</html>
