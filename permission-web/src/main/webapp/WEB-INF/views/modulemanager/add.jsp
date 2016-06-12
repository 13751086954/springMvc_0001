<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="bjui-pageContent">
    <form action="<%=path%>/modulemanager/add.do" class="pageForm" data-toggle="validate">
        <table class="table table-condensed table-hover">
            <tbody>
             
                <tr>
                    <td>
                        <input type="hidden" value="${model.id}" name="Id">
                        <input type="hidden" value="${model.cascadeid}" name="CascadeId">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="Name" class="control-label x120">功能模块名称：</label>
                        <input type="text" name="Name" id="Name" value="${model.name}"
                               data-rule="required" size="20">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="Url" class="control-label x120">主页面URL：</label>
                        <input type="text" name="Url" id="Url" value="${model.url}"  size="20">
                    </td>
                </tr>
                <tr>
                    <td>
                   
                        <label for="ParentId" class="control-label x120">上级功能模块：</label>
                        <input id="ParentId" name="ParentId" value="${model.parentid}" style="display: none" />
                        <input type="text" name="ParentName" id="ParentName" size="20"
                               value="${model.parentname}"
                              <c:choose>
							   <c:when test="${model.id == 0}">  
							       data-toggle="selectztree" data-tree="#j_select_tree1"
							   </c:when>
							   <c:otherwise> 							    
                                  readonly="readonly"
							   </c:otherwise>
							</c:choose>
                          />
                          <!--  
                            //TODO:修改的时候暂不能修改结构，因为以下：
                            //如果在前台处理，不能处理自身的ID
                            //如果在后台处理，因为算法会找最大的级联ID进行计算，如果每次都执行算法，肯定不行
                            //如果判断前端是否修改组织，再到后台处理，会增加前端的复杂程序
                          -->
                        <ul id="j_select_tree1" class="ztree hide" data-toggle="ztree"></ul>
                    </td>

                </tr>
               
                <tr>
                    <td>
                        <label for="IconName" class="control-label x120">节点图标文件名称：</label>
                        <input type="text" name="IconName" id="IconName" value="${model.iconname}"  size="20">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="Status" class="control-label x120">当前状态：</label>
                        <select name="Status" id="Status" data-toggle="selectpicker" data-rule="required">
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
                        <label for="Vector" class="control-label x120">矢量图标：</label>
                        <input type="text" name="Vector" id="Vector" value="${model.vector}" size="20">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="SortNo" class="control-label x120">排序号：</label>
                        <select name="SortNo" id="SortNo" data-toggle="selectpicker" data-rule="required">
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
                    name: 'Name',
                    title: 'Name'
                },
                simpleData: {
                    enable: true,
                    idKey: 'Id',
                    pIdKey: 'ParentId',
                    rootPId: 'null'
                }
            },
            callback: {
                onClick: zTreeOnClick,
                onCheck: zTreeCheck
            }
        };
        $.getJSON('<%=path%>/modulemanager/loadmodulewithroot.do', function (json) {
            var zTreeObj = $.fn.zTree.init($('#j_select_tree1'), setting, json);
            zTreeObj.expandAll(true);
        });
    }

    function zTreeCheck(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj(treeId),
            nodes = zTree.getCheckedNodes(true);
        var ids = '', names = '';
        for (var i = 0; i < nodes.length; i++) {
            ids += ',' + nodes[i].Id;
            names += ',' + nodes[i].Name;
        }
        if (ids.length > 0) {  //去掉第一个逗号
            ids = ids.substr(1);
            names = names.substr(1);
        }

        var $from = $('#' + treeId).data('fromObj');
        if ($from && $from.length) $from.val(names);

        $('#ParentId').val(ids);
    }
    function zTreeOnClick(event, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj(treeId);
        zTree.checkNode(treeNode, !treeNode.checked, true, true);
        event.preventDefault();
    }
</script>
