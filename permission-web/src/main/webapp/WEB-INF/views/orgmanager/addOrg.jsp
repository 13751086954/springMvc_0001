<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="bjui-pageContent">
    <form action="<%=path%>/orgmanager/addorg" class="pageForm" data-toggle="validate">
        <table class="table table-condensed table-hover">
            <tbody>

                <tr>
                    <td>
                        <label for="name" class="control-label x90">机构名称：</label>
                        <input type="text" name="name" id="name" value=""
                               data-rule="required" size="20">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input id="parentid" name="parentid" type="hidden">
                        <label for="parentname" class="control-label x90">上级机构：</label>
                        <input type="text" name="parentname" id="parentname" data-toggle="selectztree" size="20" data-tree="#j_select_tree1">
                        <ul id="j_select_tree1" class="ztree hide" data-toggle="ztree"></ul>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="status" class="control-label x85">设置状态：</label>

                        <select name="status" id="status" data-toggle="selectpicker" data-rule="required">
                            <option value="0">正常</option>
                            <option value="1">禁用</option>
                        </select>
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">关闭</button></li>
        <li><button type="submit" class="btn-green">保存</button></li>
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
            check: {
                enable: true,
                chkStyle: "radio",
                radioType: "all"
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
        $.getJSON('<%=path%>/orgmanager/loadorg', function (json) {
            var zTreeObj = $.fn.zTree.init($('#j_select_tree1'), setting, json);
            zTreeObj.expandAll(true);
        });
    }

    function zTreeCheck(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj(treeId),
            nodes = zTree.getCheckedNodes(true);
        var ids = '', names = '';
        for (var i = 0; i < nodes.length; i++) {
            ids += ',' + nodes[i].id;
            names += ',' + nodes[i].name;
        }
        if (ids.length > 0) {  //去掉第一个逗号
            ids = ids.substr(1);
            names = names.substr(1);
        }

        var $from = $('#' + treeId).data('fromObj');
        if ($from && $from.length) $from.val(names);

        $('#parentid').val(ids);
    }
    function zTreeOnClick(event, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj(treeId);
        zTree.checkNode(treeNode, !treeNode.checked, true, true);
        event.preventDefault();
    }
</script>