layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 静探表管理
     */
    var StaticTest = {
        tableId: "staticTestTable"
    };

    /**
     * 初始化表格的列
     */
    StaticTest.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'itemId', sort: true, title: '工程id'},
            {field: 'holeCode', sort: true, title: '钻孔编号'},
            {field: 'depth', sort: true, title: '试探点深度（米）'},
            {field: 'jtlx', sort: true, title: '静探类型'},
            {field: 'length', sort: true, title: '试验段长（米）'},
            {field: 'jtbgrzl', sort: true, title: '比贯入阻力(MPa)'},
            {field: 'jtztzl', sort: true, title: '锥头阻力(MPa)'},
            {field: 'jtcmz', sort: true, title: '侧壁摩阻力(kPa)'},
            {field: 'jtmzb', sort: true, title: '摩阻比(%)'},
            {field: 'jtkxyl', sort: true, title: '孔隙水压力(kPa)'},
            {field: 'cy', sort: true, title: '是否参与统计1参与0不参与'},
            {field: 'jtbgrzlx', sort: true, title: ''},
            {field: 'jtbjrzltjx', sort: true, title: ''},
            {field: 'jtztzlx', sort: true, title: ''},
            {field: 'jtztzltjx', sort: true, title: ''},
            {field: 'jtcmzx', sort: true, title: ''},
            {field: 'jtcmztjx', sort: true, title: ''},
            {field: 'jtmzbx', sort: true, title: ''},
            {field: 'jtmzbtjx', sort: true, title: ''},
            {field: 'jtkxylx', sort: true, title: ''},
            {field: 'jtkxyltjx', sort: true, title: ''},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    StaticTest.search = function () {
        var queryData = {};


        table.reload(StaticTest.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    StaticTest.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/staticTest/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    StaticTest.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/staticTest/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    StaticTest.exportExcel = function () {
        var checkRows = table.checkStatus(StaticTest.tableId);
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
    StaticTest.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/staticTest/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(StaticTest.tableId);
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
        elem: '#' + StaticTest.tableId,
        url: Feng.ctxPath + '/staticTest/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: StaticTest.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        StaticTest.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    StaticTest.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        StaticTest.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + StaticTest.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            StaticTest.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            StaticTest.onDeleteItem(data);
        }
    });
});
