<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="bjui-pageContent">
    <input style="display: none" id="roleid" value="${roleid}"/>
    <div style="float: left; width: 220px; height: 240px; overflow: auto;" class="table table-bordered">
        <ul id="lookupTree" class="ztree"></ul>
    </div>
    <!--已经选中的列表-->
    <div style="margin-left: 225px">
        <ul id="selected" class="ztree"></ul>
    </div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <li><button type="button" onclick="save()" class="btn btn-blue" data-icon="check">保存</button></li>
    </ul>
</div>

<script type="text/javascript">
    var moduleIds;
    $(document).ready(function () {
        Init();
        InitSelected();
    });
    function Init() {
        var setting = {
            view: {
                selectedMulti: false
            },
            check: {
                enable: true,
                chkStyle: "checkbox"
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
                onClick: zTreeOnClick,
                onCheck: zTreeCheck
            }
        };
        $.getJSON('<%=path%>/modulemanager/loadfortree.do', function (json) {
            var zTreeObj = $.fn.zTree.init($('#lookupTree'), setting, json);
            zTreeObj.expandAll(true);
        });
    }

    function InitSelected() {
        var setting = {
          
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
                onClick: zTreeOnClick,
                onCheck: zTreeCheck
            }
        };
        $.post('<%=path%>/modulemanager/loadforrole.do', { roleid: $('#roleid').val() },
            function (json) {
                var zTreeObj = $.fn.zTree.init($('#selected'), setting, eval(json));
                zTreeObj.expandAll(true);
        });
    }

    function save() {
        $.post('<%=path%>/modulemanager/assignmoduleforrole.do', { roleid: $('#roleid').val(), moduleIds: moduleIds },
            function (json) {
                var rel = $.parseJSON(json);
                if (rel.statusCode == "200") {
                    $(this).alertmsg('ok', rel.message);
                } else {
                    $(this).alertmsg('error', rel.message);
                }
            });
    }

    function zTreeCheck(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj(treeId),
            nodes = zTree.getCheckedNodes(true);
        var ids = '';
        for (var i = 0; i < nodes.length; i++) {
            ids += ',' + nodes[i].id;
        }
        if (ids.length > 0) {  //去掉第一个逗号
            ids = ids.substr(1);
        }

        moduleIds = ids;
    }
    function zTreeOnClick(event, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj(treeId);
        zTree.checkNode(treeNode, !treeNode.checked, true, true);
        event.preventDefault();
    }

    //@@ sourceURL=lookupMulti.js
</script>
