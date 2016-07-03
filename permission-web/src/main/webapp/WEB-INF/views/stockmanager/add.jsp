<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<div class="bjui-pageContent">
    <form action="<%=path %>/stockManager/add.do" class="pageForm" data-toggle="validate">
        <table class="table table-condensed table-hover">
            <tbody>
                   <tr>
                    <td>
                         <input type="hidden" value="${model.id}" name="id">
                    </td>
                   </tr>
                   <tr>
                    <td>
                        <label for="name" class="control-label x120">产品名称：</label>
                        <input type="text" name="name" id="name" value="${model.name}"
                               data-rule="required" size="20">
                    </td>
                   </tr>
                   <tr>
                    <td>
                        <label for="number" class="control-label x120">产品数量：</label>
                        <input type="text" name="number" id="number" value="${model.number}"
                               data-rule="required" size="20">
                    </td>
                   </tr>
                   <tr>
                    <td>
                        <label for="price" class="control-label x120">产品单价：</label>
                        <input type="text" name="price" id="price" value="${model.price}"
                               data-rule="required" size="20">
                    </td>
                   </tr>
                   <tr>
                    <td>
                        <label for="status" class="control-label x120">出库/入库：</label>
                        <input type="text" name="status" id="status" value="${model.status}"
                               data-rule="required" size="20">
                    </td>
                   </tr>
                  
                   <tr>
                    <td>
                        <label for="time" class="control-label x120">操作时间：</label>
                        <input type="text" name="time" id="time" value="${model.time}"
                               data-rule="required" size="20">
                    </td>
                   </tr>
                   <tr>
                    <td>
                         <input type="hidden" value="${model.orgid}" name="orgid">
                          <c:choose>
						   <c:when test="${model.id == 0}">  
						          //这个只用于显示使用，并不会进行提交处理，真正提交的是OrgId
                            <label for="orgname" class="control-label x120">所属部门：</label>
                            <input type="text" name="orgname" id="orgname"
                                   data-toggle="selectztree" size="20" data-tree="#j_select_tree1" value="">
                            <ul id="j_select_tree1" class="ztree hide" data-toggle="ztree"></ul>
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
		                            $.getJSON('<%=path%>/orgmanager/loadfortree.do', function (json) {
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
		                             $('#orgid').val(ids);
		                        }
		                        function zTreeOnClick(event, treeId, treeNode) {
		                            var zTree = $.fn.zTree.getZTreeObj(treeId);
		                            zTree.checkNode(treeNode, !treeNode.checked, true, true);
		                            event.preventDefault();
		                        }
		                    </script>  
						   </c:when>
						   <c:otherwise> 							    
						   </c:otherwise>
						</c:choose>
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

