layui.use(['table', 'ax', 'func', 'form', 'layer'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var func = layui.func;
    var form = layui.form;
    var layer = layui.layer;

    /**
     * 工程表管理
     */
    var Item = {
        tableId: "itemTable"
    };

    //获取工程类型的下拉框
    $.ajax({
        url: Feng.ctxPath + "/itemType/getItemTypeList",
        dataType: 'json',
        type: 'post',
        success: function (data) {
            $.each(data, function (index, item) {
                $('#type').append(new Option(item.name, item.id));
            });
            form.render("select");
        }
    });

    //获取进度的下拉框
    $.ajax({
        url: Feng.ctxPath + "/dict/listDicts",
        data: {
            dictTypeId: '1303593897935896578'
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
        //  toolbar: '#toolbarDemo'， //开启头部工具栏，并为其绑定左侧模板
        //  defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
        //     title: '提示'
        //     , layEvent: 'LAYTABLE_TIPS'
        //     , icon: 'layui-icon-tips'
        // }],
        height: "full-158",
        limit: 20,
        cols: [[
            {type: 'checkbox'},
            {field: 'itemName', title: '工程名称', align: 'center', width: 250},
            {field: 'itemCode', title: '工程编号', align: 'center'},
            {field: 'typeName', title: '工程类型', align: 'center'},
            {field: 'location', title: '工程地点', align: 'center'},
            // {field: 'head', title: '工程负责人', align: 'center'},
            {field: 'processName', title: '工程进度', align: 'center'},
            {
                title: '地图展示', field: 'isShow',align: 'center', templet: function (d) {
                    var state = "";
                    if (d.isShow == 1) {
                        state = "<input type='checkbox' value='" + d.id + "' id='isShow' lay-filter='stat' checked='checked' name='isShow'  lay-skin='switch' lay-text='展示|隐藏' >";
                    } else {
                        state = "<input type='checkbox' value='" + d.id + "' id='isShow' lay-filter='stat'  name='isShow'  lay-skin='switch' lay-text='展示|隐藏' >";
                    }
                    return state;
                }
            },
            {align: 'center', toolbar: '#tableBar', title: '操作', width: 360}
        ]],
        done: function(res, curr, count) {
            $.each(res.data, function(index,value){
                //外单位提供
                if(res.data[index].isForeign == 1){
                    $("table tbody tr").eq(index).css('color','#1E9FFF');
                }
            });
        }
    });

    //监听开关事件
    form.on('switch(stat)', function (data) {
        var contexts;
        var x = data.elem.checked;//判断开关状态
        if (x == true) {
            contexts = "你确定要展示吗？";
        } else {
            contexts = "你确定要隐藏吗？";
        }
        layer.open({
            content: contexts
            , btn: ['确定', '取消']
            , yes: function (index, layero) {
                var state = 1;
                if (x == true) {
                } else {
                    state = 0;
                }
                var arr = data.value.split(",");
                $.ajax({
                    url: Feng.ctxPath + "/item/editItem",
                    data: {"isShow": state, "id": data.value},
                    dataType: "json",
                    success: function (data) {
                        form.reload(); //删除成功后再刷新
                    },
                    error: function (data) {
                        alert('错误');
                    }
                });
                data.elem.checked = x;
                form.render();
                layer.close(index);
                //按钮【按钮一】的回调
            }
            , btn2: function (index, layero) {
                //按钮【按钮二】的回调
                data.elem.checked = !x;
                form.render();
                layer.close(index);
                //return false 开启该代码可禁止点击该按钮关闭
            }
            , cancel: function () {
                //右上角关闭回调
                data.elem.checked = !x;
                form.render();
                //return false 开启该代码可禁止点击该按钮关闭
            }
        });
        return false;
    });

    // 批量展示
    $('#showItem').on('click', function () {
        var qArray = layui.table.checkStatus('itemTable').data;
        var idArray = new Array();
        for (var i = 0; i < qArray.length; i++) {
            idArray.push(qArray[i].id);
        }
        var ids = idArray.join(",");
        console.log(ids)
        if (ids == "") {
            layer.msg("请选择工程信息！", {icon: 5, time: 1000});
            return;
        }
        layer.confirm('确认要展示吗?', function (index) {
            $.ajax({
                url: Feng.ctxPath + "/item/showAndHiddenItems",
                data: {"ids": ids, type: 0},
                dataType: 'json',
                async: 'false',
                success: function (data) {
                    if (data.success) {
                        layer.close(index);
                        Feng.success("操作批量展示成功!");
                        table.reload('itemTable');
                    } else {
                        layer.close(index);
                        Feng.success("操作批量展示失败!");
                    }
                },
                error: function (response) {
                    layer.close(index);
                    Feng.error("删除失败!" + data.responseJSON.message + "!");
                }
            });
        });
    });

    //批量隐藏
    $('#hiddenItem').on('click', function () {
        var qArray = layui.table.checkStatus('itemTable').data;
        var idArray = new Array();
        for (var i = 0; i < qArray.length; i++) {
            idArray.push(qArray[i].id);
        }
        var ids = idArray.join(",");
        if (ids == "") {
            layer.msg("请选择工程信息！", {icon: 5, time: 1000});
            return;
        }
        layer.confirm('确认要隐藏吗?', function (index) {
            $.ajax({
                url: Feng.ctxPath + "/item/showAndHiddenItems",
                data: {"ids": ids, type: 1},
                dataType: 'json',
                async: 'false',
                success: function (data) {
                    if (data.success) {
                        layer.close(index);
                        Feng.success("操作批量隐藏成功!");
                        table.reload('itemTable');
                    } else {
                        layer.close(index);
                        Feng.success("操作批量隐藏失败!");
                    }
                },
                error: function (response) {
                    layer.close(index);
                    Feng.error("失败!" + data.responseJSON.message + "!");
                }
            });
        });
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        var queryData = {
            itemName: $("#itemName").val(),
            itemCode: $("#itemCode").val(),
            location: $("#location").val(),
            type: $("#type").val(),
            progress: $("#progress").val(),
            isForeign: $("#isForeign").val(),
            isShow: $("#isShow").val()
        };
        table.reload(Item.tableId, {
            where: queryData, page: {curr: 1}
        });
    });

    //头工具栏事件
    table.on('toolbar(' + Item.tableId + ')', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'getCheckData':
                var data = checkStatus.data;
                layer.alert(JSON.stringify(data));
                break;
            case 'getCheckLength':
                var data = checkStatus.data;
                layer.msg('选中了：' + data.length + ' 个');
                break;
            case 'isAll':
                layer.msg(checkStatus.isAll ? '全选' : '未全选');
                break;

            //自定义头工具栏右侧图标 - 提示
            case 'LAYTABLE_TIPS':
                layer.alert('这是工具栏右侧自定义的一个图标按钮');
                break;
        }
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        func.open({
            title: '添加工程',
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
                title: '修改工程',
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
            //查一下项目对应的文档Id
            $.ajax({
                url: Feng.ctxPath + "/itemSub/selectItemSubByItemId",
                data: {
                    itemId: data.id
                },
                dataType: 'json',
                async:false,
                success: function (data) {
                    if (data.filePath!="" && data.filePath !=null){
                        layer.open({
                            type: 2,
                            area: ['100%', '100%'],
                            content: Feng.ctxPath + '/itemSub/itemSubDetail?subId=' + data.id
                        });
                    }else {
                        layer.msg("请先上传工程文档!", {icon: 5, time: 1000});
                    }
                }
            });
        } else if (layEvent === 'synchronous') {
            layer.open({
                type: 2,
                area: ['50%', '80%'],
                content: Feng.ctxPath + '/item/fileUploadPage?itemId=' + data.id
            });
        } else if (layEvent === 'detail') {
            location.href = Feng.ctxPath + '/item/itemDetail?id=' + data.id
        }
    });
});
