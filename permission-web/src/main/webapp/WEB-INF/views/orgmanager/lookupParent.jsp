<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="bjui-pageContent">
    <div style="float: left; width: 100%; height: 240px; overflow: auto;" class="table table-bordered">
        <ul id="lookupTree" class="ztree"></ul>
    </div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>

        <li>
            <a href="javascript:;" id="btnSelected" data-toggle="lookupback"
               data-args=""
               class="btn btn-blue" title="选择本项" data-icon="check">选择</a>
        </li>
       
    </ul>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        Init();
    });
    function Init() {
        var setting = {
            view: {
                selectedMulti: false
            },
            data: {
                key: {
                    name: 'name',
                    title: 'name'
                },
                simpleData: {
                    enable: true,
                    idKey: 'id',
                    pIdKey: 'parentid',
                    rootPId: 'null'
                }
            },
            callback: {
                onClick: zTreeOnClick
            }
        };
        $.getJSON('<%=path%>/orgmanager/loadorg', function (json) {
            var zTreeObj = $.fn.zTree.init($('#lookupTree'), setting, json);
            zTreeObj.expandAll(true);
        });
    }

    function zTreeOnClick(event, treeId, treeNode) {
        var selected = "{parentname:'" + treeNode.name + "', parentid:" + treeNode.id + "}";
        $("#btnSelected").attr("data-args",selected);
    }

    //@@ sourceURL=lookupParent.js
</script>

