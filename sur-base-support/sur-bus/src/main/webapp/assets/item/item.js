layui.use(['table', 'ax', 'func', 'form', 'layer'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var func = layui.func;
    var form = layui.form;
    var layer = layui.layer;

    /**
     * 项目表管理
     */
    var Item = {
        tableId: "itemTable"
    };

    //获取项目类型的下拉框
    $.ajax({
        url: Feng.ctxPath + "/dict/listDicts",
        data:{
            dictTypeId : '1303502589535965185'
        },
        dataType: 'json',
        type: 'post',
        success: function (data) {
            $.each(data.data, function (index, item) {
                $('#type').append(new Option(item.name, item.dictId));
            });
            form.render("select");
        }
    });

    //获取进度的下拉框
    $.ajax({
        url: Feng.ctxPath + "/dict/listDicts",
        data:{
            dictTypeId : '1303593897935896578'
        },
        dataType: 'json',
        type: 'post',
        success: function (data) {
            $.each(data.data, function (index, item) {
                $('#progress').append(new Option(item.name, item.dictId));
            });
            form.render("select");
        }
    });

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Item.tableId,
        url: Feng.ctxPath + '/item/list',
        page: true,
        toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
        ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
            title: '提示'
            ,layEvent: 'LAYTABLE_TIPS'
            ,icon: 'layui-icon-tips'
        }],
        height: "full-158",
        limit: 20,
        cols: [[
            {field: 'itemName', title: '项目名称',align:'center',width:250, templet: function (d) {
                    var url = Feng.ctxPath + '/item/itemDetail?id=' + d.id;
                    return '<a style="color: #01AAED;" href="' + url + '">' + d.itemName + '</a>';
                }},
            {field: 'itemCode', title: '项目编号',align:'center'},
            {field: 'typeName', title: '项目类型',align:'center'},
            {field: 'location', title: '项目地点',align:'center'},
            {field: 'head',  title: '项目负责人',align:'center'},
            {field: 'beginDate', title: '开始日期',align:'center', templet: function(d){
                    return d.beginDate == ""? "":d.beginDate.slice(0,10);
            }},
            {field: 'endDate', title: '结束日期',align:'center', templet: function(d){
                    return d.endDate == ""? "":d.endDate.slice(0,10);
            }},
            {field: 'processName', title: '项目进度',align:'center'},
            {align: 'center', toolbar: '#tableBar', title: '操作', width:270}
        ]]
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        var queryData = {
            itemName:$("#itemName").val(),
            itemCode:$("#itemCode").val(),
            location: $("#location").val(),
            type: $("#type").val(),
            progress: $("#progress").val()
        };
        table.reload(Item.tableId, {
            where: queryData, page: {curr: 1}
        });
    });

    //头工具栏事件
    table.on('toolbar(' + Item.tableId + ')', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'getCheckData':
                var data = checkStatus.data;
                layer.alert(JSON.stringify(data));
                break;
            case 'getCheckLength':
                var data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
                break;
            case 'isAll':
                layer.msg(checkStatus.isAll ? '全选': '未全选');
                break;

            //自定义头工具栏右侧图标 - 提示
            case 'LAYTABLE_TIPS':
                layer.alert('这是工具栏右侧自定义的一个图标按钮');
                break;
        };
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        func.open({
            title: '添加项目',
            content: Feng.ctxPath + '/item/add',
            tableId: Item.tableId
        });
    });

    // 工具条点击事件
    table.on('tool(' + Item.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'edit') {
            func.open({
                title: '修改项目',
                content: Feng.ctxPath + '/item/edit?id=' + data.id,
                tableId: Item.tableId
            });
        } else if (layEvent === 'delete') {
            var operation = function () {
                var ajax = new $ax(Feng.ctxPath + "/item/delete", function (data) {
                    Feng.success("删除成功!");
                    table.reload(Item.tableId);
                }, function (data) {
                    Feng.error("删除失败!" + data.responseJSON.message + "!");
                });
                ajax.set("id", data.id);
                ajax.start();
            };
            Feng.confirm("是否删除?", operation);
        } else if (layEvent === 'document') {
            layer.open({
                type: 2,
                area: ['100%', '100%'],
                content: Feng.ctxPath + '/item/document?itemId=' + data.id
            });
        } else if (layEvent === 'synchronous') {
            synchronous(data.id, 0, 0);
        }
    });

    function synchronous(itemId, isDataCover, isItemCover){
        var prompt= layer.msg('文件同步中...', {
            icon: 16,
            shade: 0.2,
            time: false
        });
        $.ajax({
            url: Feng.ctxPath + "/item/synchronous",
            data:{
                itemId : itemId,
                isDataCover : isDataCover,
                isItemCover : isItemCover
            },
            dataType: 'json',
            type: 'post',
            success: function (data) {
                layer.close(prompt);
                if(data.success){
                    Feng.success("操作成功！");
                }else{
                    if(data.code == 3){
                        layer.confirm(data.message, function(index){
                            synchronous(itemId, 0, 1);
                            layer.close(index);
                        });
                    }else if(data.code == 4){
                        layer.confirm(data.message, function(index){
                            synchronous(itemId, 1, 0);
                            layer.close(index);
                        });
                    }else{
                        Feng.error(data.message)
                    }
                }
            }
        });
    }
});
