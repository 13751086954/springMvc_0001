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

        <div id="detail" style="margin-left: 225px; ">
        </div>
    </div>
</div>

<script type="text/javascript">
    var selectedId = 0;
    var grid;
    $(document).ready(function () {
        initZtree();
        LoadOrg();
    });
    //加载数据到datagrid
    function LoadOrg() {
        //b-jui的datagrid需要重新处理HTML
        $('#detail').empty()
            .append('<table id="maingrid" class="table table-bordered table-hover table-striped table-top"></table>');
       grid = $('#maingrid').datagrid({
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
                name: 'parentid',
                label: '上级机构ID',
                attrs: { readonly: 'readonly'},
                hide: true
            },
              {
                  name: 'cascadeid',
                  label: '唯一标识',
                  attrs: { readonly: 'readonly'}
              },
              {
                  name: 'name',
                  label: '机构名称'
              },
              {
                  name: 'parentname',
                  label: '上级机构',
                  edit: false,
                  attrs: { readonly: 'readonly' }
              },
            {
                name: 'status',
                label: '状态',
                type: 'select',
                align: 'center',
                items:[{'0':'正常'}, {'1':'禁用'}]
            },
              {
                  name: 'createtime',
                  label: '登记日期',
                  type: 'date',
                  pattern: 'yyyy-MM-dd HH:mm:ss'
              }
            ],
            dataUrl: '<%=path%>/orgmanager/loadchildren?id=' + selectedId,
            editUrl: '<%=path%>/orgmanager/editorg',
            editMode: 'dialog',
            fullGrid: true,
            showLinenumber: true,
            showCheckboxcol: true,
            paging: false,
            filterMult: false,
            showTfoot: false,
            height:700,
            editCallback: function (delResult) {
                if (delResult.statusCode == "200")
                    Init(selectedId);
                else {
                    $(this).alertmsg('warn', delResult.message);
                }
            }
        });
    }
    function zTreeOnClick(event, treeId, treeNode) {
        selectedId = treeNode.id;
        LoadOrg();
    }

    function initZtree() {
        var setting = {
            view: {
                selectedMulti: false
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
                onClick: zTreeOnClick
            }
        };
        $.getJSON('<%=path%>/orgmanager/loadorg', function (json) {
            var zTreeObj = $.fn.zTree.init($('#maintree'), setting, json);
            zTreeObj.expandAll(true);
        });
    }

    function refreshOrgGrid() {
        $('#maingrid').datagrid('refresh');
    }

    //删除
    function delOrg() {
        var selected = getSelected('#maingrid',2);
        if (selected == null) return;

        $.getJSON('<%=path%>/orgmanager/delorg?id=' + selected, function (data) {
            if (data.statusCode == "200")
                refreshOrgGrid();
            else {
                $(this).alertmsg('warn', data.message);
            }
        });
    }
</script>

</body>
</html>