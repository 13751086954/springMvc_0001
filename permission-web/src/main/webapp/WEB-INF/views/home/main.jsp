<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <base href="<%=basePath%>">
 <base href="<%=path%>">
<div class="bjui-pageHeader" style="background:#FFF;">
    <div style="padding: 0 15px;">
        <h4 style="margin-bottom:20px;">
            基于经典DDD架构的权限管理系统　
        </h4>
        
        <div style=" margin-top:22px; padding-left:6px;">

            <span style="padding-left: 30px;">GitHub：</span>
            <a href="https://github.com/milanyangbo/permission" target="_blank">https://github.com/milanyangbo/permission</a>
        </div>
        <div class="row" style=" margin-top:10px;">
            <div class="col-md-6" style="padding:5px;">
              
            </div>
        </div>
    </div>
</div>
<!--  
<div class="bjui-pageContent">
    <div style="margin-top:5px; margin-right:300px; overflow:hidden;">
        <div class="row" style="padding: 0 8px;">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading"><h3 class="panel-title">项目最新动态>></h3></div>
                    <div class="panel-body">
                        <iframe width="100%" height="300" class="share_self" frameborder="0" scrolling="no" src="/Home/Git"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
-->
