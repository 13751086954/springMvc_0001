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
                    label: '资源表ID',
                     width: 100
                    , hide: true
               },    
               {
                    name: 'cascadeid',
                    label: '节点语义ID',
                     width: 100
               },    
               {
                    name: 'Key',
                    label: '',
                     width: 100
               },    
               {
                    name: 'name',
                    label: '名称',
                     width: 100
               },    
               {
                    name: 'parentid',
                    label: '父节点流水号',
                     width: 100
                     ,type: 'select',
                    align: 'center',
                    items: [{ '0': '默认' }, { '1': '状态1' }],
               },    
               {
                    name: 'status',
                    label: '当前状态',
                     width: 100
                     ,type: 'select',
                    align: 'center',
                    items: [{ '0': '默认' }, { '1': '状态1' }],
               },    
               {
                    name: 'sortno',
                    label: '排序号',
                     width: 100
                     ,type: 'select',
                    align: 'center',
                    items: [{ '0': '默认' }, { '1': '状态1' }],
               },    
               {
                    name: 'categoryid',
                    label: '资源分类标识',
                     width: 100
                     ,type: 'select',
                    align: 'center',
                    items: [{ '0': '默认' }, { '1': '状态1' }],
               },    
               {
                    name: 'description',
                    label: '描述',
                     width: 100
               },    
            ],
            dataUrl: '<%=path%>/resourcemanager/load.do?categoryid=' + selectedId,
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
        $.getJSON('<%=path%>/categorymanager/loadfortree.do', function (json) {
            var zTreeObj = $.fn.zTree.init($('#maintree'), setting, json);
            zTreeObj.expandAll(true);
        });
    }

    //删除
    function delResource() {
        var selected = getSelected('#maingrid',2);
        if (selected == null) return;
        
        $.getJSON('<%=path%>/resourcemanager/delete.do?id=' + selected, function (data) {
            if (data.statusCode == "200")
                loadDataGrid();
            else {
                $(this).alertmsg('warn', data.message);
            }
        });
    }

    //自定义的编辑按钮
    function editResource() {
        var selected = getSelected('#maingrid',2);
        if (selected == null) return;

        $(this).dialog({
            id: 'editDialog',
            url: '<%=path%>/resourcemanager/add.do?id=' + selected,
            title: '编辑',
            onClose:function() {
                refreshResourceGrid();
            }
        });

    }

    function refreshResourceGrid() {
        $('#maingrid').datagrid('refresh');
       // loadDataGrid();
    }
    //@@ sourceURL=ResourceManagerIndex.js
</script>

