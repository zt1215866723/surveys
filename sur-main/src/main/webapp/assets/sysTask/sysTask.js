layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 管理
     */
    var SysTask = {
        tableId: "sysTaskTable"
    };

    /**
     * 初始化表格的列
     */
    SysTask.initColumn = function () {
        return [[
            // {type: 'checkbox'},
            {field: 'id', hide: true, title: ''},
            {field: 'jobName', sort: true, title: '任务名'},
            {field: 'description', sort: true, title: '任务描述'},
            {field: 'cronExpression', sort: true, title: 'cron表达式'},
            {field: 'beanClass', sort: true, title: '任务执行类'},
            {field: 'jobGroup', sort: true, title: '任务分组'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    SysTask.search = function () {
        var queryData = {};
        queryData.jobGroup = $("#jobGroup").val();
        table.reload(SysTask.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    SysTask.jumpAddPage = function () {
        func.open({
            title: '添加定时任务',
            content: Feng.ctxPath + '/sysTask/add',
            tableId: SysTask.tableId
        });
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    SysTask.jumpEditPage = function (data) {
        func.open({
            title: '编辑定时任务',
            content: Feng.ctxPath + '/sysTask/edit?id=' + data.id,
            tableId: SysTask.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    SysTask.exportExcel = function () {
        var checkRows = table.checkStatus(SysTask.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    SysTask.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/sysTask/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(SysTask.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + SysTask.tableId,
        url: Feng.ctxPath + '/sysTask/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: SysTask.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        SysTask.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    SysTask.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        SysTask.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + SysTask.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            SysTask.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            SysTask.onDeleteItem(data);
        }
    });
});
