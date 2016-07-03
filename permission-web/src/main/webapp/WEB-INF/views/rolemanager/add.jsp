<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="bjui-pageContent">
    <form action="<%=path%>/rolemanager/add" class="pageForm" data-toggle="validate">
        <table class="table table-condensed table-hover">
            <tbody>
               
                <tr>
                    <td>
                       <input type="hidden" value="${model.id}" name="id">
                       <input type="hidden" value="${model.parentid}" name="parentid">
                <tr>
                    <td>

                        <label for="name" class="control-label x90">角色名称：</label>
                        <input type="text" name="name" id="name" value="${model.name}"
                               data-rule="required" size="20">
                    </td>
                </tr>
                <tr>
                    <td>

                        <label for="status" class="control-label x90">当前状态：</label>
                        <select name="status" id="status" data-toggle="selectpicker" data-rule="required">
                             <c:choose>
							   <c:when test="${model.status == 0}">  
							       <option value="0" selected="selected" >默认</option>
                                   <option value="1" >状态1</option>   
							   </c:when>
							   <c:otherwise> 
							       <option value="0">默认</option>
                                   <option value="1" selected="selected" >状态1</option>
							   </c:otherwise>
							</c:choose>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>

                        <label for="type" class="control-label x90">角色类型：</label>
                        <select name="type" id="type" data-toggle="selectpicker" data-rule="required">
                             <c:choose>
							   <c:when test="${model.type == 0}">  
							       <option value="0" selected="selected" >默认</option>
                                   <option value="1" >状态1</option>   
							   </c:when>
							   <c:otherwise> 
							       <option value="0">默认</option>
                                   <option value="1" selected="selected" >状态1</option>
							   </c:otherwise>
							</c:choose>
                        </select>
                    </td>
                </tr>


            <tr>
                <td>
                    <label for="orgname" class="control-label x90">所属机构：</label>
                    <input id="orgid" name="orgid" value="" style="display: none"/>
                    <input type="text" name="orgname" id="orgname"
                           data-toggle="selectztree" size="20" data-tree="#j_select_tree1" value="${model.orgname}">
                    <ul id="j_select_tree1" class="ztree hide" data-toggle="ztree"></ul>
                </td>
            </tr>
                 <tr>
                    <td>

                        <label for="orgcascadeid" class="control-label x90">机构级联ID：</label>
                        <input type="text" name="orgcascadeid" id="orgcascadeid" value="${model.orgcascadeid}"
                               data-rule="required" size="20">
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
        var ids = '', names = '', cascadeids='';
        for (var i = 0; i < nodes.length; i++) {
            ids += ',' + nodes[i].id;
            names += ',' + nodes[i].name;
            cascadeids += ',' + nodes[i].cascadeid;
        }
        if (ids.length > 0) {  //去掉第一个逗号
            ids = ids.substr(1);
            names = names.substr(1);
            cascadeids = cascadeids.substr(1);
        }

        var $from = $('#' + treeId).data('fromObj');
        if ($from && $from.length) $from.val(names);

        $('#orgid').val(ids);
        $('#orgcascadeid').val(cascadeids);
    }
    function zTreeOnClick(event, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj(treeId);
        zTree.checkNode(treeNode, !treeNode.checked, true, true);
        event.preventDefault();
    }
</script>
