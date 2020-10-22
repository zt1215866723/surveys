layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 所有工程土层标准管理
     */
    var SurStandard = {
        tableId: "surStandardTable"
    };

    /**
     * 初始化表格的列
     */
    SurStandard.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'itemId', sort: true, title: '工程id'},
            {field: 'mainCode', sort: true, title: '主层编号'},
            {field: 'secondaryCode', sort: true, title: '亚层编号'},
            {field: 'thirdCode', sort: true, title: '次亚层编号'},
            {field: 'tcdzsd', sort: true, title: '地质时代'},
            {field: 'tcdzcy', sort: true, title: '地质成因'},
            {field: 'type', sort: true, title: '岩土类别'},
            {field: 'name', sort: true, title: '岩土名称'},
            {field: 'tcymc', sort: true, title: '亚岩土名称'},
            {field: 'tcys', sort: true, title: '土层颜色'},
            {field: 'tcmsd', sort: true, title: '密实度'},
            {field: 'tcsid', sort: true, title: '湿度'},
            {field: 'tcksx', sort: true, title: '可塑性'},
            {field: 'tchyd', sort: true, title: '浑圆度'},
            {field: 'tcjyx', sort: true, title: '均匀性'},
            {field: 'tcfhcd', sort: true, title: '风化程度'},
            {field: 'tcysqx', sort: true, title: '岩层倾向(度)'},
            {field: 'tcysqj', sort: true, title: '岩层倾角(度)'},
            {field: 'tckwcf', sort: true, title: '矿物成分'},
            {field: 'tcjggz', sort: true, title: '结构构造'},
            {field: 'tcbhw', sort: true, title: '包含物'},
            {field: 'tcqw', sort: true, title: '气味'},
            {field: 'tcms', sort: true, title: '土层描述'},
            {field: 'tcztx', sort: true, title: '完整程度'},
            {field: 'tcjycd', sort: true, title: '坚硬程度'},
            {field: 'tcpl', sort: true, title: '破碎程度'},
            {field: 'tcjlfy', sort: true, title: '节理发育'},
            {field: 'tcjljj', sort: true, title: '节理间距(cm)'},
            {field: 'tcpxjd', sort: true, title: '视倾角(度)'},
            {field: 'tcqttz', sort: true, title: '其他特征'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    SurStandard.search = function () {
        var queryData = {};


        table.reload(SurStandard.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 跳转到添加页面
     */
    SurStandard.jumpAddPage = function () {
        window.location.href = Feng.ctxPath + '/surStandard/add'
    };

    /**
    * 跳转到编辑页面
    *
    * @param data 点击按钮时候的行数据
    */
    SurStandard.jumpEditPage = function (data) {
        window.location.href = Feng.ctxPath + '/surStandard/edit?id=' + data.id
    };

    /**
     * 导出excel按钮
     */
    SurStandard.exportExcel = function () {
        var checkRows = table.checkStatus(SurStandard.tableId);
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
    SurStandard.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/surStandard/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(SurStandard.tableId);
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
        elem: '#' + SurStandard.tableId,
        url: Feng.ctxPath + '/surStandard/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: SurStandard.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        SurStandard.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    SurStandard.jumpAddPage();

    });

    // 导出excel
    $('#btnExp').click(function () {
        SurStandard.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + SurStandard.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            SurStandard.jumpEditPage(data);
        } else if (layEvent === 'delete') {
            SurStandard.onDeleteItem(data);
        }
    });
});
