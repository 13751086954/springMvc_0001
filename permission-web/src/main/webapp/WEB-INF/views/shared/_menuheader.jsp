<%@ page pageEncoding="utf-8"%>

<div class="bjui-pageHeader">
    <div class="bjui-searchBar">
        <div class="bjui-searchBar">
        <!-- 
            <label>名称：</label><input type="text" value="" name="code" size="10">&nbsp;
                <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
                <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">
                    清空查询
                </a></li>
                 -->
            <div class="pull-right">
            	<c:forEach items="${module.elements}" var="element">
                 <${element.type} data-icon='${element.icon}' id='${element.domid}' class='${element.classify}' onclick='${element.script}' ${element.attr}>
                 ${element.name}
                 </${element.type}>      
	            </c:forEach>               
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function getDatagridRow(gridid) {
        var selected = $(gridid).data('selectedTrs');
        if (selected == null || selected.length == 0) {
            $(this).alertmsg('warn', '至少选择一个对象', {
                displayMode: 'slide',
                title: '重要提示'
            });
            return null;
        }
        return selected;
    }
    //获取勾选的值
    //column:为从0开始的列标识
    function getSelected(gridid, column) {

        var selected = getDatagridRow(gridid);
        if (selected == null) return null;

        var records = new Array();
        $.each(selected, function (i) {
            console.log($(this).children().eq(column).text());
            records[records.length] = $(this).children().eq(column).text();
        });

        return records[0];
    }
    //返回选择的多条记录，用逗号隔开
    function getSelectedMany(gridid, column) {
        var selected = getDatagridRow(gridid);
        if (selected == null) return null;

        var results = '';
        $.each(selected, function (i) {
            console.log($(this).children().eq(column).text());
            results += ',' + $(this).children().eq(column).text();
        });

        results = results.substr(1); //去掉第一个逗号
        return results;
    }
</script>