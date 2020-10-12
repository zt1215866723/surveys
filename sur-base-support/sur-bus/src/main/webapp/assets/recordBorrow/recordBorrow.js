layui.use(['table', 'admin', 'ax', 'func', 'laydate'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var laydate = layui.laydate;

    /**
     * 借阅记录表管理
     */
    var RecordBorrow = {
        tableId: "recordBorrowTable"
    };

    //渲染时间选择框
    laydate.render({
        elem: '#timeLimit',
        trigger: 'click',
        range: true,
        type: 'date'
    });

    /**
     * 初始化表格的列
     */
    RecordBorrow.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'borrower', sort: true, title: '借阅人'},
            {field: 'documentName', sort: true, title: '文档'},
            {field: 'addTime', sort: true, title: '借出时间'},
            {field: 'returnTime', sort: true, title: '归还时间'},
            {field: 'administratorName', sort: true, title: '管理人'},
            {field: 'note', sort: true, title: '备注'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + RecordBorrow.tableId,
        url: Feng.ctxPath + '/recordBorrow/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: RecordBorrow.initColumn(),
        limit: 20
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        var queryData = {
            borrower: $("#borrower").val(),
            documentName: $("#documentName").val(),
            timeLimit: $("#timeLimit").val()
        };

        table.reload(RecordBorrow.tableId, {
            where: queryData, page: {curr: 1}
        });
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        func.open({
            title: '添加借阅记录',
            content: Feng.ctxPath + '/recordBorrow/add',
            tableId: RecordBorrow.tableId
        });
    });

    // 导出excel
    $('#btnExp').click(function () {
        var checkRows = table.checkStatus(RecordBorrow.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    });

    // 工具条点击事件
    table.on('tool(' + RecordBorrow.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            func.open({
                title: '修改借阅记录',
                content: Feng.ctxPath + '/recordBorrow/edit?id=' + data.id,
                tableId: RecordBorrow.tableId
            });
        } else if (layEvent === 'delete') {
            var operation = function () {
                var ajax = new $ax(Feng.ctxPath + "/recordBorrow/delete", function (data) {
                    Feng.success("删除成功!");
                    table.reload(RecordBorrow.tableId);
                }, function (data) {
                    Feng.error("删除失败!" + data.responseJSON.message + "!");
                });
                ajax.set("id", data.id);
                ajax.start();
            };
            Feng.confirm("是否删除?", operation);
        } else if (layEvent === 'return') {
            func.open({
                title: '文档归还',
                content: Feng.ctxPath + '/recordBorrow/returnPage?id=' + data.id,
                tableId: RecordBorrow.tableId
            });
        }
    });
});
