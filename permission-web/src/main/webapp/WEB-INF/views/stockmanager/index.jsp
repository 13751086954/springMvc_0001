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
            showToolbar: false,
            filterThead: false,
            target: $(this),
            columns: [
                {
                    name: 'id',
                    label: '数据ID',
                    width: 100,
                    hide: true
                },
                {
                    name: 'name',
                    label: '产品名称',
                    width: 100
                },
                {
                    name: 'number',
                    label: '产品数量',
                    width: 100
                },
                {
                    name: 'price',
                    label: '产品单价',
                    width: 100
                },
                {
                    name: 'status',
                    label: '出库/入库',
                    width: 100
                      , type: 'select',
                    align: 'center',
                    items: [{ '0': '入库' }, { '1': '出库' }],
                },
                {
                    name: 'user',
                    label: '操作人',
                    width: 100
                },
                {
                    name: 'time',
                    label: '操作时间',
                    width: 100
                     , type: 'date',
                    pattern: 'yyyy-MM-dd HH:mm:ss'
                },
                {
                    name: 'orgid',
                    label: '所属部门',
                    width: 100,
                    hide: true
                }
            ],
            dataUrl: '<%=path%>/stockmanager/load.do?parentid=' + selectedId,
            fullGrid: true,
            showLinenumber: true,
            showCheckboxcol: true,
            paging: true,
            filterMult: false,
            showTfoot: false
            //,height: '100%'
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
        $.getJSON('<%=path%>/orgmanager/loadorg.do', function (json) {
            var zTreeObj = $.fn.zTree.init($('#maintree'), setting, json);
            zTreeObj.expandAll(true);
        });
    }

    //删除
    function delStock() {
        var selected = getSelected('#maingrid', 2);
        if (selected == null) return;

        $.getJSON('<%=path%>/stockmanager/delete.do?id=' + selected, function (data) {
            if (data.statusCode == "200")
                loadDataGrid();
            else {
                $(this).alertmsg('warn', data.message);
            }
        });
    }

    //自定义的编辑按钮
    function editStock() {
        var selected = getSelected('#maingrid', 2);
        if (selected == null) return;

        $(this).dialog({
            id: 'editDialog',
            url: '<%=path%>/stockmanager/add.do?id=' + selected,
            title: '编辑',
            onClose: function () {
                refreshStockGrid();
            }
        });

    }

    function refreshStockGrid() {
        $('#maingrid').datagrid('refresh');
        // loadDataGrid();
    }
    //@@ sourceURL=StockManagerIndex.js
</script>
