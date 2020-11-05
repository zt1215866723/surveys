layui.use(['table', 'admin', 'ax', 'func', 'form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var form = layui.form;

    /**
     * 钻孔类型表管理
     */
    var DrillingType = {
        tableId: "drillingTypeTable"
    };

    /**
     * 初始化表格的列
     */
    DrillingType.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'name', sort: true, title: '名称'},
            {field: 'url', sort: true, title: '图例存储路径'},
            {field: 'status', sort: true, title: '状态', templet: function(d){
                    if(d.url != '') {
                        if(Boolean(d.status)){
                            return '<input type="checkbox" id="'+ d.id +'" name="status" checked=""  lay-skin="switch" lay-filter="statusSwitch" lay-text="启用|禁用">'
                        }
                        return '<input type="checkbox" id="'+ d.id +'" name="status" lay-skin="switch" lay-filter="statusSwitch" lay-text="启用|禁用">'

                    }
                    return ''
                }
            },
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    DrillingType.search = function () {
        var queryData = {};

        queryData['name'] = $('#name').val();
        queryData['url'] = $('#url').val();
        queryData['status'] = $('#status').val();

        table.reload(DrillingType.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    DrillingType.openAddDlg = function () {
        func.open({
            title: '添加钻孔类型表',
            content: Feng.ctxPath + '/drillingType/add',
            tableId: DrillingType.tableId
        });
    };

     /**
      * 点击编辑
      *
      * @param data 点击按钮时候的行数据
      */
      DrillingType.openEditDlg = function (data) {
          func.open({
              title: '修改钻孔类型表',
              content: Feng.ctxPath + '/drillingType/edit?id=' + data.id,
              tableId: DrillingType.tableId
          });
      };


    /**
     * 导出excel按钮
     */
    DrillingType.exportExcel = function () {
        var checkRows = table.checkStatus(DrillingType.tableId);
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
    DrillingType.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/drillingType/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(DrillingType.tableId);
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
        elem: '#' + DrillingType.tableId,
        url: Feng.ctxPath + '/drillingType/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: DrillingType.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        DrillingType.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    DrillingType.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        DrillingType.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + DrillingType.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            DrillingType.openEditDlg(data);
        } else if (layEvent === 'delete') {
            DrillingType.onDeleteItem(data);
        }
    });

    /**
     *  监听开关的点击
     */
    form.on('switch(statusSwitch)', function(data){
        $.ajax({
            url:"/drillingType/editItem",
            type: "GET",
            data: {"id" : this.id, "status": 1 & Number(this.checked)},
            success: function () {
                layer.msg('修改成功')
            }
        })
    });
});