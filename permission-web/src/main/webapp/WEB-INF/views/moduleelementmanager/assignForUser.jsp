<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String _prefix = "assignForUser";
String _treeId = _prefix + "Tree";
String _gridId = _prefix + "Grid";
String _treeDetail = _prefix + "Detail";
%>

<div class="bjui-pageHeader">
    <div class="bjui-searchBar">
        <input style="display: none" id="userid" value="${userid}" />
        <div class="pull-right">
            <div class="alert alert-info search-inline">
                <i class="fa fa-info-circle"></i> 点击行为单选，点击复选框可多选统一授权
            </div>&nbsp;
            <button type="button" class="btn-green" data-num="1" data-icon="plus" onclick="assign()">
                授权选中项目
            </button>&nbsp;
        </div>
    </div>
</div>

<div class="bjui-pageContent tableContent">
    <div class="clearfix">
        <div style="float: left; width: 220px; overflow: auto;" class="table table-bordered">
            <ul id="<%=_treeId%>" class="ztree"></ul>
        </div>

        <div id="<%=_treeDetail%>" style="margin-left: 225px;">
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
        $('#<%=_treeDetail%>').empty()
            .append('<table id="<%=_gridId%>" class="table table-bordered table-hover table-striped table-top"></table>');

        $('#<%=_gridId%>').datagrid({
            showToolbar: false,
            filterThead: false,
            columns: [
                  {
                      name: 'id',
                      label: '元素名称',
                      hide: true
                  },
               {
                   name: 'name',
                   label: '元素名称',
                   width: 80
               },
               {
                   name: 'modulename',
                   label: '所属模块',
                   width: 80
               },
               {
                   name: 'accessed',
                   label: '是否已经授权',
                   type: 'select',
                   align: 'center',
                   items: [{ 'false': '未授权', 'true': '已授权' }],
                   width: 80
               }
            ],
            dataUrl: '<%=path%>/moduleelementmanager/loadforuser.do?orgid=' + selectedId + '&userid=' + $('#userid').val(),
            fullGrid: true,
            showLinenumber: true,
            showCheckboxcol: true,
            paging: true,
            filterMult: false,
            showTfoot: false
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
        $.getJSON('<%=path%>/modulemanager/loadforuser.do?userid=' + $('#userid').val(), function (json) {
            var zTreeObj = $.fn.zTree.init($('#<%=_treeId%>'), setting, json);
            zTreeObj.expandAll(true);
        });
    }

    //授权选中的
    function assign() {
        var selected = getSelectedMany('#<%=_gridId%>', 2);
        if (selected == null) return;

        $.post("<%=path%>/moduleelementmanager/assignforuser.do",
        {
            userid: $('#userid').val(),
            moduleId:selectedId,
            menuIds: selected
        },
       function (data) {
           refreshGrid();
       });
    }

    function refreshGrid() {
        $('#<%=_gridId%>').datagrid('refresh');
        // loadDataGrid();
    }
    //@@ sourceURL=ModuleManagerIndex.js
</script>
