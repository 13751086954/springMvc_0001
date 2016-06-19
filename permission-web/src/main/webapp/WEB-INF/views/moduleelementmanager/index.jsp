<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript">

//删除回调
$('#tableButtons').on('afterdelete.bjui.tabledit', function(e) {
    var $tbody = $(e.relatedTarget);
    console.log('你删除了一条数据，还有['+ $tbody.find('> tr').length +']条数据！');
})
</script>
<div class="bjui-pageHeader">
        <div class="bjui-searchBar">
            <div class="alert alert-info search-inline"><i class="fa fa-info-circle"></i> 双击行可编辑</div>&nbsp;
            <button type="button" class="btn-green" data-toggle="tableditadd" data-target="#tableButtons" 
                    data-num="1" data-icon="plus">
                添加新按钮
            </button>&nbsp;
        </div>
</div>
<div class="bjui-pageContent tableContent" style="position:relative">
    <form id="j_custom_form" class="pageForm" data-toggle="validate" method="post">
        <table id="tableButtons" class="table table-bordered table-hover table-striped table-top"
               data-toggle="tabledit" data-initnum="0" data-action="<%=path %>/moduleelementmanager/addoreditbutton" 
               data-single-noindex="true">
            <thead>
            <tr data-idname="Id">
                <th width="8%" title="元素类型"><input type="text" name="type" data-rule="required" value="button" size="5"></th>
                <th width="10%" title="按钮标识"><input type="text" name="domid" data-rule="required" value="" size="5"></th>
                <th width="10%" title="按钮显示"><input type="text" name="name" data-rule="required" value="" size="5"></th>
                <th width="10%" title="按钮样式"><input type="text" name="classify" data-rule="required" value="btn-green" size="5"></th>
                <th width="8%" title="按钮图标"><input type="text" name="icon" data-rule="required" value="pencil" size="5"></th>
                <th width="10%" title="按钮脚本">
                    <input type="text" name="Script" data-rule="required" value="javascript:;" size="5">
                </th>
                <th width="24%" title="附加属性"><textarea name="attr" data-toggle="autoheight"></textarea></th>
                <th width="10%" title="所属模块ID">
                    <input readonly="readonly" type="text" value="${moduleId}" name="moduleid"/>
                </th>
                <th title="操作" width="10%">
                    <a href="javascript:;" class="btn btn-green" data-toggle="dosave">增加</a>
                    <a href="javascript:;" class="btn btn-red row-del">取消</a>
                </th>
            </tr>
            </thead>
            <tbody>
           <c:forEach items="${modules}" var="element">
                <tr data-id="${element.id}">
                    <td>${element.type}</td>
                    <td>${element.domid}</td>
                    <td>${element.name}</td>
                    <td>${element.classify}</td>
                    <td>${element.icon}</td>
                    <td>${element.script}</td>
                    <td>${element.attr}</td>
                    <td>${moduleId}</td>
                    <td data-noedit="true">
                        <input type="text" style="display: none" value="${element.id}" id="element_${element.id}"/>
                        <button type="button" class="btn-green" data-toggle="doedit">编辑</button>
                        <a href="<%=path %>/moduleelementmanager/delbutton?id={#element_${element.id}}" class="btn btn-red row-del"
                           data-confirm-msg="确定要删除该行信息吗？">删</a>
                    </td>
                </tr>
            </c:forEach> 
           
            </tbody>
        </table>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
    </ul>
</div>