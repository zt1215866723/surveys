layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 取样数据表管理
     */
    var Sample = {
        tableId: "sampleTable"
    };

    /**
     * 初始化表格的列
     */
    Sample.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'itemId', sort: true, title: '项目Id'},
            {field: 'holeCode', sort: true, title: '钻孔编号'},
            {field: 'qybh', sort: true, title: '取样编号'},
            {field: 'qysd', sort: true, title: '取样顶深度(m)'},
            {field: 'qyhd', sort: true, title: '取样长度(m)'},
            {field: 'qydc', sort: true, title: '所在地层'},
            {field: 'qylx', sort: true, title: '取样类型 0：原状样 1：扰动样'},
            {field: 'qyzlmd', sort: true, title: '质量密度ρ(g/cm^3)'},
            {field: 'qybz', sort: true, title: '土粒比重Gs'},
            {field: 'qyhsl', sort: true, title: '含水量ω(%)'},
            {field: 'qyyx', sort: true, title: '液限ωL(%)'},
            {field: 'qysx', sort: true, title: '塑限ωP(%)'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Sample.search = function () {
        var queryData = {};

        queryData['id'] = $('#id').val();
        queryData['itemId'] = $('#itemId').val();
        queryData['holeCode'] = $('#holeCode').val();
        queryData['qybh'] = $('#qybh').val();
        queryData['qysd'] = $('#qysd').val();
        queryData['qyhd'] = $('#qyhd').val();

        table.reload(Sample.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Sample.openAddDlg = function () {
        func.open({
            title: '添加取样数据表',
            content: Feng.ctxPath + '/sample/add',
            tableId: Sample.tableId
        });
    };

     /**
      * 点击编辑
      *
      * @param data 点击按钮时候的行数据
      */
      Sample.openEditDlg = function (data) {
          func.open({
              title: '修改取样数据表',
              content: Feng.ctxPath + '/sample/edit?id=' + data.id,
              tableId: Sample.tableId
          });
      };


    /**
     * 导出excel按钮
     */
    Sample.exportExcel = function () {
        var checkRows = table.checkStatus(Sample.tableId);
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
    Sample.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/sample/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Sample.tableId);
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
        elem: '#' + Sample.tableId,
        url: Feng.ctxPath + '/sample/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Sample.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Sample.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    Sample.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Sample.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Sample.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Sample.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Sample.onDeleteItem(data);
        }
    });
});
