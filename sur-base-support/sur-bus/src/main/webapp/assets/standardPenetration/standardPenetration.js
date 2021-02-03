layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 标贯数据表管理
     */
    var StandardPenetration = {
        tableId: "standardPenetrationTable"
    };

    /**
     * 初始化表格的列
     */
    StandardPenetration.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'itemId', sort: true, title: '工程主键'},
            {field: 'holeCode', sort: true, title: '钻孔编号'},
            {field: 'depth', sort: true, title: '试验点底深度（米）'},
            {field: 'bglx', sort: true, title: '标贯类型'},
            {field: 'bgtzz', sort: true, title: '特征值'},
            {field: 'length', sort: true, title: '杆长（米）'},
            {field: 'bgyzcd', sort: true, title: '一阵击数的长度(m)'},
            {field: 'bgyzjs', sort: true, title: '一阵击数(击)'},
            {field: 'bgjs', sort: true, title: '标贯击数(击/30cm)'},
            {field: 'bgxs', sort: true, title: '标贯修正系数'},
            {field: 'bgxzjs', sort: true, title: '修正后的标贯击数(击/30cm)'},
            {field: 'bgsxz', sort: true, title: '修正否'},
            {field: 'cy', sort: true, title: '是否参与统计1是0否'},
            {field: 'bgxjsx', sort: true, title: ''},
            {field: 'bgxjstjx', sort: true, title: ''},
            {field: 'bgjsx', sort: true, title: ''},
            {field: 'bgjstjx', sort: true, title: ''},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    StandardPenetration.search = function () {
        var queryData = {};


        table.reload(StandardPenetration.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    StandardPenetration.openAddDlg = function () {
        func.open({
            title: '添加标贯数据表',
            content: Feng.ctxPath + '/standardPenetration/add',
            tableId: StandardPenetration.tableId
        });
    };

     /**
      * 点击编辑
      *
      * @param data 点击按钮时候的行数据
      */
      StandardPenetration.openEditDlg = function (data) {
          func.open({
              title: '修改标贯数据表',
              content: Feng.ctxPath + '/standardPenetration/edit?id=' + data.id,
              tableId: StandardPenetration.tableId
          });
      };


    /**
     * 导出excel按钮
     */
    StandardPenetration.exportExcel = function () {
        var checkRows = table.checkStatus(StandardPenetration.tableId);
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
    StandardPenetration.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/standardPenetration/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(StandardPenetration.tableId);
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
        elem: '#' + StandardPenetration.tableId,
        url: Feng.ctxPath + '/standardPenetration/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: StandardPenetration.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        StandardPenetration.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    StandardPenetration.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        StandardPenetration.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + StandardPenetration.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            StandardPenetration.openEditDlg(data);
        } else if (layEvent === 'delete') {
            StandardPenetration.onDeleteItem(data);
        }
    });
});
