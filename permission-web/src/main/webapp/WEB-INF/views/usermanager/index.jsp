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
    var grid;
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
            showToolbar: false,
            filterThead: false,
            target: $(this),
            columns: [
                {
                    name: 'id',
                    label: 'id',
                    attrs: { readonly: 'readonly' },
                    hide: true
                },
                {
                    name: 'account',
                    label: '用户账号',
                    width: 142,
                    attrs: { readonly: 'readonly' }
                },
                {
                    name: 'name',
                    label: '姓名/昵称',
                    width: 226
                },
                {
                    name: 'organizations',
                    label: '所属机构',
                    width: 150
                },
                {
                    name: 'status',
                    label: '状态',
                    type: 'select',
                    align: 'center',
                    width: 80,
                    items: [{ '0': '正常' }, { '1': '禁用' }]
                },
                {
                    name: 'sex',
                    label: '性别',
                    type: 'select',
                    align: 'center',
                    width: 80,
                    items: [{ '0': '男' }, { '1': '女' }]
                },
                {
                    name: 'createtime',
                    label: '登记日期',
                    type: 'date',
                    width: 180,
                    pattern: 'yyyy-MM-dd HH:mm:ss'
                }
            ],
            dataUrl: '<%=path %>/usermanager/load.do?orgid=' + selectedId,
            fullGrid: true,
            showLinenumber: true,
            showCheckboxcol: true,
            paging: true,
            filterMult: false,
            showTfoot: false
            //, height: '100%'  //TODO：这种高度如果记录数较少的时候，看起有点怪，这个可以根据自己爱好修改
        });
    }

    function zTreeOnClick(event, treeId, treeNode) {
        selectedId = treeNode.id;
        loadDataGrid();
    }

    function initZtree() {
        var setting = {
            view: { selectedMulti: false },
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
            callback: { onClick: zTreeOnClick }
        };
        $.getJSON('<%=path %>/orgmanager/loadorg.do', function (json) {
            var zTreeObj = $.fn.zTree.init($('#maintree'), setting, json);
            zTreeObj.expandAll(true);
        });
    }



    //删除
    function delUser() {
        var selected = getSelected('#maingrid', 2);
        if (selected == null) return;

        $.getJSON('<%=path %>/usermanager/delete.do?id=' + selected, function (data) {
            if (data.statusCode == "200")
                refreshGrid();
            else {
                $(this).alertmsg('warn', data.message);
            }
        });
    }

    //自定义的编辑按钮
    function editUser() {
        var selected = getSelected('#maingrid', 2);
        if (selected == null) return;

        $(this).dialog({
            id: 'editDialog',
            url: '<%=path %>/usermanager/add.do?id=' + selected,
            title: '编辑',
            onClose: function () {
                refreshUserGrid();
            }
        });

    }

    function refreshUserGrid() {
        $('#maingrid').datagrid('refresh');
    }

    //用户模块授权按钮
    function openUserModuleAccess(obj) {

        var selected = getSelected('#maingrid', 2);
        if (selected == null) return;

        $(obj).dialog({
            id: 'accessUserModule',
            url: '<%=path %>/modulemanager/lookupmultiforuser.do',
            title: '为用户分配模块',
            data: {
                userid: selected
            }
        });
    }

    //用户可见组织授权按钮
    function openUserOrgAccess(obj) {

        var selected = getSelected('#maingrid', 2);
        if (selected == null) return;

        $(obj).dialog({
            id: 'accessUserOrg',
            url: '<%=path %>/orgmanager/lookupmultiforuser.do',
            title: '为用户分配可见部门',
            data: {
                userid: selected
            }
        });
    }

    //用户角色授权
    function openUserRoleAccess(obj) {
        var selected = getSelected('#maingrid', 2);
        if (selected == null) return;

        $(obj).dialog({
            id: 'accessUserRole',
            url: '<%=path %>/rolemanager/lookupmulti.do',
            title: '为用户分配角色',
            width: 600,
            height: 380,
            data: {
                userid: selected
            }
        });
    }

    //为用户分配资源
    function openUserReourceAccess(obj) {
        var selected = getSelected('#maingrid', 2);
        if (selected == null) return;

        $(obj).dialog({
            id: 'accessUserRole',
            url: '<%=path %>/resourcemanager/lookupmultiforuser.do',
            title: '为用户分配资源',
            width: 600,
            height: 380,
            data: {
                userid: selected
            }
        });
    }

    //分配菜单
    function openAssignUserElement(obj) {
        var selected = getSelected('#maingrid', 2);
        if (selected == null) return;

        $(obj).dialog({
            id: 'assignElement',
            url: '<%=path %>/moduleelementmanager/assignforuser.do?userid=' + selected,
            title: '为用户分配菜单',
            width: 700,
            height: 380
        });
    }
</script>