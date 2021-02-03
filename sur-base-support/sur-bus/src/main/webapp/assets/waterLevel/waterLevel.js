layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 水位信息管理
     */
    var WaterLevel = {
        tableId: "waterLevelTable"
    };

    /**
     * 初始化表格的列
     */
    WaterLevel.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'itemId', sort: true, title: '工程id'},
            {field: 'holeCode', sort: true, title: '钻孔编号'},
            {field: 'depth', sort: true, title: '深度（米）'},
            {field: 'swlx', sort: true, title: '是否稳定1是 0不是'},
            {field: 'swch', sort: true, title: '是否为地下水位1是 0不是'},
            {field: 'swcsrq', sort: true, title: '测水日期'},
            {field: 'swdxsw', sort: true, title: '地下水温(度)'},
            {field: 'swfw', sort: true, title: '水位范围'},
            {field: 'swxz', sort: true, title: '地下水类型'},
            {field: 'cy', sort: true, title: '是否参与统计1参与0不参与'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    WaterLevel.search = function () {
        var queryData = {};


        table.reload(WaterLevel.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    WaterLevel.openAddDlg = function () {
        func.open({
            title: '添加水位信息',
            content: Feng.ctxPath + '/waterLevel/add',
            tableId: WaterLevel.tableId
        });
    };

     /**
      * 点击编辑
      *
      * @param data 点击按钮时候的行数据
      */
      WaterLevel.openEditDlg = function (data) {
          func.open({
              title: '修改水位信息',
              content: Feng.ctxPath + '/waterLevel/edit?id=' + data.id,
              tableId: WaterLevel.tableId
          });
      };


    /**
     * 导出excel按钮
     */
    WaterLevel.exportExcel = function () {
        var checkRows = table.checkStatus(WaterLevel.tableId);
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
    WaterLevel.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/waterLevel/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(WaterLevel.tableId);
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
        elem: '#' + WaterLevel.tableId,
        url: Feng.ctxPath + '/waterLevel/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: WaterLevel.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        WaterLevel.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    WaterLevel.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        WaterLevel.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + WaterLevel.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            WaterLevel.openEditDlg(data);
        } else if (layEvent === 'delete') {
            WaterLevel.onDeleteItem(data);
        }
    });
});
