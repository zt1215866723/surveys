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
     * 修改：2020年10月23日 王南翔
     */
    SurStandard.initColumn = function () {
        return [[
            {title: '地层序号', width: 100, align: "center", templet: '#numbers'},
            {title: '地层编号', width: 100, align: "center", templet: function(d){
                    //组装地层编号数据  格式:"主层编号-亚层编号-次亚层编号"
                    let data = ""
                    data += d.mainCode ? d.mainCode : ""
                    data += d.secondaryCode ? "-" + d.secondaryCode : ""
                    data += d.thirdCode && d.thirdCode !== "0" ? "-" + d.thirdCode : ""
                    return data
                }
            },
            {field: 'mainCode', title: '主层', width: 100, align: "center", sort: true},
            {field: 'secondaryCode', title: '亚层', width: 100, align:" center", sort: true},
            {field: 'thirdCode',title: '次亚层', width: 100, align: "center", sort: true, templet:function (d) {
                    return (d.thirdCode && d.thirdCode !== "0") ? d.thirdCode : ""
                }
            },
            {field: 'tcdzsd', title: '地质时代', width: 100,align: "center", sort: true},
            {field: 'tcdzcy', title: '地质成因', width: 100,align: "center", sort: true},
            {field: 'tcms', title: '地层描述', sort: true},
            {align: 'center', title: '操作', width: 150, toolbar: '#tableBar'}
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
