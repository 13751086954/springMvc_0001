<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="bjui-pageContent">
    <form action="<%=path %>/usermanager/add.do" class="pageForm" data-toggle="validate">
        <table class="table table-condensed table-hover">
            <tbody>
             
                <tr>
                    <td>
                        <input type="hidden" value="${model.id}" name="id">
                        <input type="hidden" value="${model.createtime}" name="createtime">
                        <label for="account" class="control-label x90">用户账号：</label>
                        <input type="text" name="account" id="account" value="${model.account}"
                               data-rule="required" size="20">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="name" class="control-label x90">姓名/昵称：</label>
                        <input type="text" name="name" id="name" value="${model.name}" size="20">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="organizations" class="control-label x90">所属机构：</label>
                        <input id="organizationids" name="organizationids" value="${model.organizationids}" style="display: none" />
                        <input type="text" name="organizations" id="organizations" 
                               data-toggle="selectztree" size="20" data-tree="#j_select_tree1" value="${model.organizations}">
                        <ul id="j_select_tree1" class="ztree hide" data-toggle="ztree" >
                        </ul>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="status" class="control-label x85">设置状态：</label>
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
                        <label for="sex" class="control-label x85">性别：</label>
                        <select name="sex" id="sex" data-toggle="selectpicker" data-rule="required">
                            <c:choose>
							   <c:when test="${model.sex == 0}">  
							       <option value="0" selected="selected" >男</option>
                                   <option value="1" >女</option>   
							   </c:when>
							   <c:otherwise> 
							       <option value="0">男</option>
                                   <option value="1" selected="selected" >女</option>
							   </c:otherwise>
							</c:choose>
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
                chkStyle: "checkbox",
                chkboxType: { "Y": "", "N": "" } //去掉勾选时级联
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
        $.getJSON('<%=path %>/orgmanager/loadorg.do', function (json) {
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

        $('#organizationids').val(ids);
    }
    function zTreeOnClick(event, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj(treeId);
        zTree.checkNode(treeNode, !treeNode.checked, true, true);
        event.preventDefault();
    }
</script>