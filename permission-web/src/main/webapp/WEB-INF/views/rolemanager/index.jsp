<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<%@ include  file="/WEB-INF/views/shared/_bjuilayout.jsp"%>
</head>
  
<body>
 <!--[if lte IE 7]>
        <div id="errorie"><div>您还在使用老掉牙的IE，正常使用系统前请升级您的浏览器到 IE8以上版本 <a target="_blank" href="http://windows.microsoft.com/zh-cn/internet-explorer/ie-8-worldwide-languages">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a href="http://down.tech.sina.com.cn/content/40975.html" target="_blank">谷歌 Chrome</a></div></div>
 <![endif]-->
<%@ include  file="/WEB-INF/views/shared/_menuheader.jsp"%>

<div class="bjui-pageContent tableContent" style="position:relative">
    <div class="clearfix">
        <div style="float: left; width: 220px; overflow: auto;" class="table table-bordered">
            <ul id="maintree" class="ztree"></ul>
        </div>

        <div id="detail" style="margin-left: 225px;">
        </div>
    </div>
</div>


<script type="text/javascript">
    var selectedId = 0;
    $(document).ready(function () {
        initZtree();
        loadDataGrid();
    });
    //加载数据到datagrid
    function loadDataGrid() {
        //b-jui的datagrid需要重新处理HTML
        $('#detail').empty()
            .append('<table id="maingrid" class="table table-bordered table-hover table-striped table-top"></table>');

        $('#maingrid').datagrid({
            showToolbar:false,
            filterThead: false,
            columns: [
               {
                    name: 'id',
                    label: '流水号'
                    , hide: true
               },
               {
                    name: 'name',
                    label: '角色名称',
                   width:100
               },
               {
                    name: 'status',
                    label: '当前状态',
                    type: 'select',
                    align: 'center',
                   items:[{'0':'正常','1':'禁用'}],
                   width:50
               },
               {
                    name: 'type',
                    label: '角色类型',
                    type: 'select',
                    align: 'center',
                    items: [{ '0': '管理员','1':'普通角色' }],
                   width:50
               },
               {
                    name: 'createtime',
                    label: '创建时间'
                    , type: 'date',
                    width: 150,
                    pattern: 'yyyy-MM-dd HH:mm:ss'
               },

               {
                    name: 'orgcascadeid',
                    label: '所属部门节点语义ID',
                   width:100
               },
               {
                    name: 'orgname',
                    label: '所属部门名称',
                   width:100
               }
            ],
            dataUrl: '<%=path%>/rolemanager/load.do?orgid=' + selectedId,
            target:$(this),
            fullGrid: true,
            showLinenumber: true,
            showCheckboxcol: true,
            paging: true,
            filterMult: false,
            showTfoot: false
            //, height: '100%'
        });
    }

    function zTreeOnClick(event, treeId, treeNode) {
        selectedId = treeNode.id;
        loadDataGrid();
    }

    function initZtree() {
        var setting = {
            view: {selectedMulti: false},
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
            callback: {onClick: zTreeOnClick}
        };
        $.getJSON('<%=path%>/orgmanager/loadorg.do', function (json) {
            var zTreeObj = $.fn.zTree.init($('#maintree'), setting, json);
            zTreeObj.expandAll(true);
        });
    }

    //删除
    function delRole() {
        var selected = getSelected('#maingrid', 2);
        if (selected == null) return;

        $.getJSON('<%=path%>/rolemanager/delete.do?id=' + selected, function (data) {
            if (data.statusCode == "200")
                refreshRoleGrid();
            else {
                $(this).alertmsg('warn', data.message);
            }
        });
    }

    //自定义的编辑按钮
    function editRole() {
        var selected = getSelected('#maingrid',2);
        if (selected == null) return;

        $(this).dialog({
            id: 'editDialog',
            url: '<%=path%>/rolemanager/add.do?id=' + selected,
            title: '编辑',
            onClose:function() {
                refreshRoleGrid();
            }
        });
    }

    function refreshRoleGrid() {
        $('#maingrid').datagrid('refresh');
       // loadDataGrid();
    }

    //为角色分配模块
    function assignRoleModule(obj) {

        var selected = getSelected('#maingrid',2);
        if (selected == null) return;

        $(obj).dialog({
            id: 'accessRoleModule',
            url: '<%=path%>/modulemanager/lookupmultiforrole.do',
            title: '为角色分配模块',
            data: {
                roleid: selected
            }
        });
    }

    //为角色分配可见机构
    function assignRoleOrg(obj) {
        var selected = getSelected('#maingrid', 2);
        if (selected == null) return;

        $(obj).dialog({
            id: 'accessRoleOrg',
            url: '<%=path%>/orgmanager/lookupmultiforrole.do',
            title: '为角色分配可见机构',
            data: {
                roleid: selected
            }
        });
    }

    //为角色分配资源
    function openRoleReourceAccess(obj) {
        var selected = getSelected('#maingrid', 2);
        if (selected == null) return;

        $(obj).dialog({
            id: 'accessUserRole',
            url: '<%=path%>/resourcemanager/lookupmultiforrole.do',
            title: '为角色分配资源',
            width: 600,
            height: 380,
            data: {
                roleid: selected
            }
        });
    }

    //为角色分配菜单
    function assignRoleElement(obj) {
        var selected = getSelected('#maingrid', 2);
        if (selected == null) return;

        $(obj).dialog({
            id: 'assignElement',
            url: '<%=path%>/moduleelementmanager/assignforrole.do?roleid=' + selected,
            title: '为角色分配菜单',
            width: 700,
            height:380
        });
    }

    //@@ sourceURL=RoleManagerIndex.js
</script>
