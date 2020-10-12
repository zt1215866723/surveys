layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 勘探点数据表管理
     */
    var Drilling = {
        tableId: "drillingTable"
    };

    /**
     * 初始化表格的列
     */
    Drilling.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'itemId', sort: true, title: '工程id'},
            {field: 'holeCode', sort: true, title: '钻孔编号'},
            {field: 'type', sort: true, title: '钻孔类型'},
            {field: 'zkx', sort: true, title: '横坐标'},
            {field: 'zky', sort: true, title: '纵坐标'},
            {field: 'jzdd', sort: true, title: '建筑地段'},
            {field: 'zkdh', sort: true, title: '段号'},
            {field: 'zklc', sort: true, title: '里程（米）'},
            {field: 'zkpil', sort: true, title: '偏移量(m)'},
            {field: 'zkbg', sort: true, title: '孔口高程(m)'},
            {field: 'zkhsbg', sort: true, title: '水面高程(m)'},
            {field: 'depth', sort: true, title: '勘探深度(m)'},
            {field: 'zktjsd', sort: true, title: '探井深度(m)'},
            {field: 'zkzj', sort: true, title: '钻孔直径(mm)'},
            {field: 'zkdclc', sort: true, title: '断层类型'},
            {field: 'zkdcjj', sort: true, title: '断层夹角(度)'},
            {field: 'zkdcqsd', sort: true, title: '断层起始深度(m)'},
            {field: 'zkdczsd', sort: true, title: '断层终止深度(m)'},
            {field: 'zkdcpm', sort: true, title: '断层盘面'},
            {field: 'zkxn', sort: true, title: '虚拟钻孔0不是1是'},
            {field: 'zksfcy', sort: true, title: '是否参与统计1是0否'},
            {field: 'zkjyk', sort: true, title: '已有孔标志'},
            {field: 'zkdxsw', sort: true, title: '地下水温(度)'},
            {field: 'zktcjd', sort: true, title: '探槽角度(度)'},
            {field: 'zkksrq', sort: true, title: '勘探开始日期'},
            {field: 'zkzzrq', sort: true, title: '勘探终止日期'},
            {field: 'zkdj', sort: true, title: '勘探点等级'},
            {field: 'zkpztsdxs', sort: true, title: '土的湿度系数ψw'},
            {field: 'zkpzdqyxs', sort: true, title: '大气影响深度(m)'},
            {field: 'bz', sort: true, title: '备注'},
            {field: 'zksc', sort: true, title: ''},
            {field: 'zkh', sort: true, title: ''},
            {field: 'zkv', sort: true, title: ''},
            {field: 'zkyhzs', sort: true, title: ''},
            {field: 'sxdj', sort: true, title: '湿陷等级'},
            {field: 'sxlx', sort: true, title: '湿陷类型'},
            {field: 'zkpzqs', sort: true, title: '膨胀潜势'},
            {field: 'zkpzdj', sort: true, title: '膨胀等级'},
            {field: 'zkymshsl', sort: true, title: '1m深处含水量ω1'},
            {field: 'zkymcsxhsl', sort: true, title: '1m深处塑限含水量ω1p'},
            {field: 'dtable', sort: true, title: ''},
            {field: 'ysgcbh', sort: true, title: '原始工程编号'},
            {field: 'zkcolor', sort: true, title: ''},
            {field: 'zkzbh', sort: true, title: '总编号'},
            {field: 'zkszff', sort: true, title: '施钻方法'},
            {field: 'zkzjlx', sort: true, title: '钻机类型'},
            {field: 'zkztdw', sort: true, title: '钻探单位'},
            {field: 'zkgdmc', sort: true, title: '工点名称'},
            {field: 'zkdmbg', sort: true, title: '地面高程'},
            {field: 'jyjcgc', sort: true, title: '建议基础高程(m)'},
            {field: 'sortikey', sort: true, title: ''},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Drilling.search = function () {
        var queryData = {};


        table.reload(Drilling.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    Drilling.openAddDlg = function () {
        func.open({
            title: '添加勘探点数据表',
            content: Feng.ctxPath + '/drilling/add',
            tableId: Drilling.tableId
        });
    };

     /**
      * 点击编辑
      *
      * @param data 点击按钮时候的行数据
      */
      Drilling.openEditDlg = function (data) {
          func.open({
              title: '修改勘探点数据表',
              content: Feng.ctxPath + '/drilling/edit?id=' + data.id,
              tableId: Drilling.tableId
          });
      };


    /**
     * 导出excel按钮
     */
    Drilling.exportExcel = function () {
        var checkRows = table.checkStatus(Drilling.tableId);
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
    Drilling.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/drilling/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(Drilling.tableId);
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
        elem: '#' + Drilling.tableId,
        url: Feng.ctxPath + '/drilling/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Drilling.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Drilling.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    Drilling.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        Drilling.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Drilling.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Drilling.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Drilling.onDeleteItem(data);
        }
    });
});
