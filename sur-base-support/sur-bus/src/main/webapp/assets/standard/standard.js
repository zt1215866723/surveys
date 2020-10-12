layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 地层信息管理
     */
    var Standard = {
        tableId: "standardTable"
    };

    /**
     * 初始化表格的列
     */
    Standard.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'itemId', sort: true, title: '工程id'},
            {field: 'holeCode', sort: true, title: '钻孔编号'},
            {field: 'depth', sort: true, title: '层底深度（米）'},
            {field: 'tcxh', sort: true, title: ''},
            {field: 'mainCode', sort: true, title: '主层编号'},
            {field: 'secondaryCode', sort: true, title: '亚层编号'},
            {field: 'thirdCode', sort: true, title: '次亚层编号'},
            {field: 'tcdzsd', sort: true, title: '地质时代'},
            {field: 'tcdzcy', sort: true, title: '地质成因'},
            {field: 'tcdchd', sort: true, title: '自然层厚度(m)'},
            {field: 'tchd', sort: true, title: '地层厚度(m)'},
            {field: 'type', sort: true, title: '岩土类别'},
            {field: 'name', sort: true, title: '岩土名称'},
            {field: 'tcmdh', sort: true, title: '土名代号'},
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
            {field: 'desc', sort: true, title: '土层描述'},
            {field: 'tcztx', sort: true, title: '完整程度'},
            {field: 'tcjycd', sort: true, title: '坚硬程度'},
            {field: 'tcpl', sort: true, title: '破碎程度'},
            {field: 'tcjlfy', sort: true, title: '节理发育'},
            {field: 'tcdcms', sort: true, title: '地层描述'},
            {field: 'tcjljj', sort: true, title: '节理间距(cm)'},
            {field: 'tcpxjd', sort: true, title: '视倾角(度)'},
            {field: 'tcqttz', sort: true, title: '其他特征'},
            {field: 'tczrzd', sort: true, title: '天然重度(kN/m3)'},
            {field: 'tcnjl', sort: true, title: '粘聚力(kPa)'},
            {field: 'tcnmcj', sort: true, title: '内摩擦角(°)'},
            {field: 'tcczl', sort: true, title: '承载力(kPa)'},
            {field: 'tcysml', sort: true, title: '压缩模量(MPa)'},
            {field: 'tcyzzCzltzz', sort: true, title: '预制桩侧阻力特征值(kPa)'},
            {field: 'tcyzzDzltzz', sort: true, title: '预制桩端阻力特征值(kPa)'},
            {field: 'tcyzzCzlbzz', sort: true, title: '预制桩极限侧阻力标准值(kPa)'},
            {field: 'tcyzzDzlbzz', sort: true, title: '预制桩极限端阻力标准值(kPa)'},
            {field: 'tcckzCzltzz', sort: true, title: '冲(钻)孔桩侧阻力特征值(kPa)'},
            {field: 'tcckzDzltzz', sort: true, title: '冲(钻)孔桩端阻力特征值(kPa)'},
            {field: 'tcckzCzlbzz', sort: true, title: '冲(钻)孔桩极限侧阻力标准值(kPa)'},
            {field: 'tcckzDzlbzz', sort: true, title: '冲(钻)孔桩极限端阻力标准值(kPa)'},
            {field: 'tcuserdefine1', sort: true, title: '自定义字段1'},
            {field: 'tcuserdefine2', sort: true, title: '自定义字段2'},
            {field: 'tcuserdefine3', sort: true, title: '自定义字段3'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Standard.search = function () {
        var queryData = {};


        table.reload(Standard.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Standard.openAddDlg = function () {
        func.open({
            title: '添加地层信息',
            content: Feng.ctxPath + '/standard/add',
            tableId: Standard.tableId
        });
    };

     /**
      * 点击编辑
      *
      * @param data 点击按钮时候的行数据
      */
      Standard.openEditDlg = function (data) {
          func.open({
              title: '修改地层信息',
              content: Feng.ctxPath + '/standard/edit?id=' + data.id,
              tableId: Standard.tableId
          });
      };


    /**
     * 导出excel按钮
     */
    Standard.exportExcel = function () {
        var checkRows = table.checkStatus(Standard.tableId);
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
    Standard.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/standard/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Standard.tableId);
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
        elem: '#' + Standard.tableId,
        url: Feng.ctxPath + '/standard/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Standard.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Standard.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    Standard.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Standard.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Standard.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Standard.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Standard.onDeleteItem(data);
        }
    });
});
