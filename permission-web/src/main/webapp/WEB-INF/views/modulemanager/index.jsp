<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include  file="/WEB-INF/views/shared/_bjuilayout.jsp"%>
</head>
  
<body>
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
  <!--   dataUrl: '<%=path%>/modulemanager/load.do?orgId=' + selectedId, -->

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
            .append('<table id="maingrid" class="table table-hover table-striped table-top"></table>');

        $('#maingrid').datagrid({
            showToolbar: false,
            filterThead: false,
            target: $(this),
            columns: [
               {
                    name: 'id',
                    label: '功能模块流水号',
                    hide: true
               },    
               {
                    name: 'cascadeid',
                    label: '节点语义ID',
                     width: 80
               },    
               {
                    name: 'name',
                    label: '功能模块名称',
                     width: 80
               },    
               {
                    name: 'url',
                    label: '主页面URL',
                     width: 80
               },    
                 
               {
                    name: 'parentid',
                    label: '父节点流水号',
                    hide:true
               },    
                
               {
                    name: 'iconname',
                     width: 80,
                    label: '节点图标文件名称'
               },    
               {
                    name: 'status',
                     width: 80,
                    label: '当前状态'
               },    
               {
                    name: 'parentname',
                     width: 80,
                    label: '父节点名称'
               },    
               {
                    name: 'vector',
                     width: 80,
                    label: '矢量图标'
               },    
               {
                    name: 'sortno',
                     width: 80,
                    label: '排序号'
               },    
            ],
            dataUrl: '<%=path%>/modulemanager/load.do?orgId=' + selectedId,
            fullGrid: true,
            showLinenumber: true,
            showCheckboxcol: true,
            paging: true,
            filterMult: false,
            showTfoot: false,
            height: '100%'
        });
    }
  
  
    function zTreeOnClick(event, treeId, treeNode) {
        selectedId = treeNode.Id;
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
                    pIdKey: 'parentId',
                    rootPId: 'null'
                }
            },
            callback: {onClick: zTreeOnClick}
        };

        $.getJSON('<%=path%>/modulemanager/loadmodulewithroot.do', function (json) {
             var zTreeObj = $.fn.zTree.init($('#maintree'), setting, json);
             zTreeObj.expandAll(true);
        });
    }

    //删除
    function delModule() {
        var selected = getSelected('#maingrid',2);
        if (selected == null) return;
        
        $.getJSON('<%=path%>/modulemanager/delete.do?Id=' + selected, function (data) {
            if (data.statusCode == "200")
                loadDataGrid();
            else {
                $(this).alertmsg('warn', data.message);
            }
        });
    }

    //自定义的编辑按钮
    function editModule() {
        var selected = getSelected('#maingrid',2);
        if (selected == null) return;

        $(this).dialog({
            id: 'editDialog',
            url: '<%=path%>/modulemanager/add.do?id=' + selected,
            title: '编辑',
            onClose:function() {
                refreshModuleGrid();
            }
        });

    }

    //为模块分配按钮
    function assignButton() {
        var selected = getSelected('#maingrid',2);
        if (selected == null) return;

        $(this).dialog({
            id: 'editDialog',
            width: 1000,
            height: 500,
            mask:true,
            url: '<%=path%>/moduleelementmanager/index.do?id=' + selected,
            title: '为模块分配按钮'
        });

    }

    function refreshModuleGrid() {
        $('#maingrid').datagrid('refresh');
       // loadDataGrid();
    }
</script>
  </body>
</html>
