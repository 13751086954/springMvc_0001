<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<div class="bjui-pageContent">
    <form action="<%=path%>/categorymanager/add" class="pageForm" data-toggle="validate">
        <table class="table table-condensed table-hover">
            <tbody>
            <tr>
                <td>
                   <input type="hidden" value="${model.id}" name="id">
                   <input type="hidden" value="${model.parentid}" name="parentid">
                </td>
            </tr>

                <tr>
                    <td>
                        <label for="name" class="control-label x120">名称：</label>
                        <input type="text" name="name" id="name" value="${model.name}"
                               data-rule="required" size="20">
                    </td>
                </tr>

                <tr>
                    <td>
                        <label for="status" class="control-label x120">当前状态：</label>
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
                        <label for="sortno" class="control-label x120">排序号：</label>
                        <select name="sortno" id="sortno" data-toggle="selectpicker" data-rule="required">
                             <c:choose>
							   <c:when test="${model.sortno == 0}">  
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
                 <c:choose>
				   <c:when test="${model.id == 0}">  
				    <tr>
                        <td>
                            <label for="parentname" class="control-label x120">父节点流水号：</label>
                            <input type="text" name="parentname" id="parentname"
                                   data-toggle="selectztree" size="20" data-tree="#j_select_tree1" value="">
                            <ul id="j_select_tree1" class="ztree hide" data-toggle="ztree"></ul>
                        </td>
                    </tr>
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
                            $.getJSON('<%=path%>/categorymanager/loadfortree', function (json) {
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
				   </c:when>
				   <c:otherwise> 
				       <tr>
                        <td>
                            <label for="cascadeid" class="control-label x90">节点语义ID：</label>
                            <input type="text" name="cascadeid" id="cascadeid" value="${model.cascadeid}"
                                   data-rule="required" size="20">
                        </td>
                    </tr>
				  </c:otherwise>
				</c:choose>
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
