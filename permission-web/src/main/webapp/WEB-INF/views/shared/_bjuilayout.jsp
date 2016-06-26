    <%@ page pageEncoding="utf-8"%>
    <base href="<%=basePath%>">
    <base href="<%=path%>">
    
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>OpenAuth,权限管理系统</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=path%>/favicon.ico" media="screen" /> 
    <meta name="Keywords" content="OpenAuth,权限管理系统,milanyangbo" />
    <meta name="Description" content="OpenAuth,权限管理系统,milanyangbo" />
    <!-- bootstrap - css -->
    <link href="<%=path%>/static/BJUI/themes/css/bootstrap.css" rel="stylesheet">
    <!-- core - css -->
    <link href="<%=path%>/static/BJUI/themes/css/style.css" rel="stylesheet">
    <link href="<%=path%>/static/BJUI/themes/blue/core.css" id="bjui-link-theme" rel="stylesheet">
    <!-- plug - css -->
    <link href="<%=path%>/static/BJUI/plugins/kindeditor_4.1.10/themes/default/default.css" rel="stylesheet">
    <link href="<%=path%>/static/BJUI/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
    <link href="<%=path%>/static/BJUI/plugins/niceValidator/jquery.validator.css" rel="stylesheet">
    <link href="<%=path%>/static/BJUI/plugins/bootstrapSelect/bootstrap-select.css" rel="stylesheet">
    <link href="<%=path%>/static/BJUI/themes/css/FA/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=path%>/static/BJUI/plugins/styles/zTreeStyle/zTreeStyle.css" />
    <!--[if lte IE 7]>
    <link href="<%=path%>/static/BJUI/themes/css/ie7.css" rel="stylesheet">
    <![endif]-->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lte IE 9]>
        <script src="<%=path%>/static/BJUI/other/html5shiv.min.js"></script>
        <script src="<%=path%>/static/BJUI/other/respond.min.js"></script>
    <![endif]-->
    <!-- jquery -->
    <script src="<%=path%>/static/BJUI/js/jquery-1.11.3.min.js"></script>
    <script src="<%=path%>/static/BJUI/js/jquery.cookie.js"></script>
    <!--[if lte IE 9]>
    <script src="<%=path%>/static/BJUI/other/jquery.iframe-transport.js"></script>
    <![endif]-->
    <!-- BJUI.all 分模块压缩版 -->
    <script src="<%=path%>/static/BJUI/js/bjui-all.js"></script>

    <!-- plugins -->
    <!-- swfupload for uploadify && kindeditor -->
    <script src="<%=path%>/static/BJUI/plugins/swfupload/swfupload.js"></script>
    <!-- kindeditor -->
    <script src="<%=path%>/static/BJUI/plugins/kindeditor_4.1.10/kindeditor-all.min.js"></script>
    <script src="<%=path%>/static/BJUI/plugins/kindeditor_4.1.10/lang/zh_CN.js"></script>
    <!-- colorpicker -->
    <script src="<%=path%>/static/BJUI/plugins/colorpicker/js/bootstrap-colorpicker.min.js"></script>
    <!-- ztree -->
    <script src="<%=path%>/static/BJUI/plugins/ztree/jquery.ztree.all-3.5.js"></script>
    <!-- nice validate -->
    <script src="<%=path%>/static/BJUI/plugins/niceValidator/jquery.validator.js"></script>
    <script src="<%=path%>/static/BJUI/plugins/niceValidator/jquery.validator.themes.js"></script>
    <!-- bootstrap plugins -->
    <script src="<%=path%>/static/BJUI/plugins/bootstrap.min.js"></script>
    <script src="<%=path%>/static/BJUI/plugins/bootstrapSelect/bootstrap-select.min.js"></script>
    <script src="<%=path%>/static/BJUI/plugins/bootstrapSelect/defaults-zh_CN.min.js"></script>
    <!-- icheck -->
    <script src="<%=path%>/static/BJUI/plugins/icheck/icheck.min.js"></script>
    <!-- dragsort -->
    <script src="<%=path%>/static/BJUI/plugins/dragsort/jquery.dragsort-0.5.1.min.js"></script>
    <!-- other plugins -->
    <script src="<%=path%>/static/BJUI/plugins/other/jquery.autosize.js"></script>
    <link href="<%=path%>/static/BJUI/plugins/uploadify/css/uploadify.css" rel="stylesheet">
    <script src="<%=path%>/static/BJUI/plugins/uploadify/scripts/jquery.uploadify.min.js"></script>
    <script src="<%=path%>/static/BJUI/plugins/download/jquery.fileDownload.js"></script>

    <!---本项目扩展的JS代码-->
    <script src="<%=path%>/static/BJUI/myextension.js"></script>
    <!-- init -->
    <script type="text/javascript">
        $(function () {
            BJUI.init({
                JSPATH: 'BJUI/',         //[可选]框架路径
                PLUGINPATH: 'BJUI/plugins/', //[可选]插件路径
                loginInfo: { url: 'Login', title: '登录', width: 400, height: 200 }, // 会话超时后弹出登录对话框
                statusCode: { ok: 200, error: 300, timeout: 301 }, //[可选]
                ajaxTimeout: 50000, //[可选]全局Ajax请求超时时间(毫秒)
                pageInfo: { total: 'total', pageCurrent: 'pageCurrent', pageSize: 'pageSize', orderField: 'orderField', orderDirection: 'orderDirection' }, //[可选]分页参数
                alertMsg: { displayPosition: 'topcenter', displayMode: 'slide', alertTimeout: 3000 }, //[可选]信息提示的显示位置，显隐方式，及[info/correct]方式时自动关闭延时(毫秒)
                keys: { statusCode: 'statusCode', message: 'message' }, //[可选]
                ui: {
                    windowWidth: 0,    //框架可视宽度，0=100%宽，> 600为则居中显示
                    showSlidebar: true, //[可选]左侧导航栏锁定/隐藏
                    clientPaging: true, //[可选]是否在客户端响应分页及排序参数
                    overwriteHomeTab: false //[可选]当打开一个未定义id的navtab时，是否可以覆盖主navtab(我的主页)
                },
                debug: true,    // [可选]调试模式 [true|false，默认false]
                theme: 'sky' // 若有Cookie['bjui_theme'],优先选择Cookie['bjui_theme']。皮肤[五种皮肤:default, orange, purple, blue, red, green]
            });
        });
    </script>