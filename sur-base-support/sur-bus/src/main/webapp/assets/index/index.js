layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 索引信息管理
     */
    var Index = {
        tableId: "indexTable"
    };

    /**
     * 初始化表格的列
     */
    Index.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'subId', sort: true, title: '文档id'},
            {field: 'focusId', sort: true, title: '检测项id'},
            {field: 'nouValue', sort: true, title: '数字型结果'},
            {field: 'strValue', sort: true, title: '字符串结果(数据字典)'},
            {field: 'state', sort: true, title: '状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Index.search = function () {
        var queryData = {};
        table.reload(Index.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Index.openAddDlg = function () {
        func.open({
            title: '添加索引信息',
            content: Feng.ctxPath + '/index/add',
            tableId: Index.tableId
        });
    };

     /**
      * 点击编辑
      *
      * @param data 点击按钮时候的行数据
      */
      Index.openEditDlg = function (data) {
          func.open({
              title: '修改索引信息',
              content: Feng.ctxPath + '/index/edit?id=' + data.id,
              tableId: Index.tableId
          });
      };


    /**
     * 导出excel按钮
     */
    Index.exportExcel = function () {
        var checkRows = table.checkStatus(Index.tableId);
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
    Index.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/index/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Index.tableId);
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
        elem: '#' + Index.tableId,
        url: Feng.ctxPath + '/index/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Index.initColumn(),
        limit: 20
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Index.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    Index.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Index.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Index.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Index.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Index.onDeleteItem(data);
        }
    });
});
