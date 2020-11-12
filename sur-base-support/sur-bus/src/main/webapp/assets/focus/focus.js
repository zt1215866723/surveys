layui.use(['table', 'admin', 'ax', 'func', 'form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var form = layui.form;

    /**
     * 关注项管理
     */
    var Focus = {
        tableId: "focusTable"
    };

    //获取项目类型的下拉框
    $.ajax({
        url: Feng.ctxPath + "/itemType/getItemTypeList",
        dataType: 'json',
        type: 'post',
        success: function (data) {
            $.each(data, function (index, item) {
                $('#itemType').append(new Option(item.name, item.id));
            });
            form.render("select");
        }
    });

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Focus.tableId,
        url: Feng.ctxPath + '/focus/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        limit: 20,
        cols:[[
            {field: 'itemName', title: '工程类别'},
            {field: 'name', title: '关注项'},
            {field: 'unit', title: '单位'},
            {field: 'typeName', title: '类型', templet: function(d){
                if(d.typeName == -1){
                    return '文本';
                }else if(d.typeName == 0){
                    return '数值';
                }else{
                    return d.typeName;
                }
            }},
            {field: 'isNecessary', title: '必填项', templet: function(d){
                    return d.isNecessary == 0 ? '否' : '是';
            }},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]]
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        var queryData = {
            name : $("#name").val(),
            itemType : $("#itemType").val()
        };
        table.reload(Focus.tableId, {
            where: queryData, page: {curr: 1}
        });
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        func.open({
            title: '添加关注项',
            content: Feng.ctxPath + '/focus/add',
            tableId: Focus.tableId
        });
    });

    // 工具条点击事件
    table.on('tool(' + Focus.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            func.open({
                title: '修改关注项',
                content: Feng.ctxPath + '/focus/edit?id=' + data.id,
                tableId: Focus.tableId
            });
        } else if (layEvent === 'delete') {
            var operation = function () {
                var ajax = new $ax(Feng.ctxPath + "/focus/delete", function (data) {
                    Feng.success("删除成功!");
                    table.reload(Focus.tableId);
                }, function (data) {
                    Feng.error("删除失败!" + data.responseJSON.message + "!");
                });
                ajax.set("id", data.id);
                ajax.start();
            };
            Feng.confirm("是否删除?", operation);
        }
    });
});
