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
            
            <span style="padding-left: 30px;">permission</span>
			权限管理系统，使用技术：B-JUI(bootstrap)、jquery、spring 、springmvc、shiro、mybatis、maven、dubbo、zookeeper、redis、fastdfs、kafka
			
		    <span style="padding-left: 30px;">开发环境</span>
			myeclipse-2015-stable-2.0、jdk1.7、tomcat 8.0、maven-3.3.9、mysql5+
			
			MyEclipse+Tomcat+MAVEN+SVN项目完整环境搭建
			http://blog.csdn.net/zhshulin/article/details/30779873
			
			SSM框架——详细整合教程（Spring+SpringMVC+MyBatis）
			http://blog.csdn.net/zhshulin/article/details/37956105
			
			fastdfs-client-java操作fastdfs5.0.4
			http://my.oschina.net/chaun/blog/467450 
			
			分布式服务框架 Zookeeper -- 管理分布式环境中的数据
			http://www.ibm.com/developerworks/cn/opensource/os-cn-zookeeper/
			
			Dubbo与Zookeeper、SpringMVC整合和使用（负载均衡、容错）
			http://blog.csdn.net/congcong68/article/details/41113239
			
			permission.sql为mysql数据库初始化脚本文件
			
			启动permission-serviceImpl项目下的MainLoader注册服务提供者；
			
			<span style="padding-left: 30px;">开发框架</span>
			B-JUI (Best jQuery UI) 前端管理框架
			http://b-jui.com/
			
			Dubbo
			http://dubbo.io/
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
