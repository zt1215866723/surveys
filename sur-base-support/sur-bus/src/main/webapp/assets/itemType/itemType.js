layui.use(['table', 'admin', 'ax', 'func', 'form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var form = layui.form;

    /**
     * 项目类型表管理
     */
    var ItemType = {
        tableId: "itemTypeTable"
    };

    /**
     * 初始化表格的列
     */
    ItemType.initColumn = function () {
        return [[
            {hide: true, type: 'checkbox'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'name', sort: true, title: '名称'},
            {field: 'url', sort: true, title: '图例',templet: function(d) {
                    return'<img src="/image/'+ d.url +'" style="max-width: 45px" >'
                }
            },
            {field: 'status', sort: true, title: '状态',templet: function(d) {
                    if(d.url != '') {
                        if(d.status == 0){
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
    ItemType.search = function () {
        var queryData = {};

        queryData['name'] = $('#name').val();
        queryData['status'] = $('input[name="status"]:checked').val();

        table.reload(ItemType.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加对话框
     */
    ItemType.openAddDlg = function () {
        func.open({
            title: '添加项目类型表',
            content: Feng.ctxPath + '/itemType/add',
            tableId: ItemType.tableId
        });
    };

     /**
      * 点击编辑
      *
      * @param data 点击按钮时候的行数据
      */
      ItemType.openEditDlg = function (data) {
          func.open({
              title: '修改项目类型表',
              content: Feng.ctxPath + '/itemType/edit?id=' + data.id,
              tableId: ItemType.tableId
          });
      };


    /**
     * 导出excel按钮
     */
    ItemType.exportExcel = function () {
        var checkRows = table.checkStatus(ItemType.tableId);
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
    ItemType.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/itemType/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(ItemType.tableId);
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
        elem: '#' + ItemType.tableId,
        url: Feng.ctxPath + '/itemType/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: ItemType.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        ItemType.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {

    ItemType.openAddDlg();

    });

    // 导出excel
    $('#btnExp').click(function () {
        ItemType.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + ItemType.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            ItemType.openEditDlg(data);
        } else if (layEvent === 'delete') {
            ItemType.onDeleteItem(data);
        }
    });

    /**
     *  监听开关的点击
     */
    form.on('switch(statusSwitch)', function(data){
        $.ajax({
            url:"/itemType/editItem",
            type: "GET",
            data: {"id" : this.id, "status": this.checked ? 0 : 2},
            success: function () {
                layer.msg('修改成功')
            }
        })
    });
});
