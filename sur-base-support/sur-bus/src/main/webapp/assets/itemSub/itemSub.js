layui.use(['table', 'admin', 'ax', 'func', 'laydate', 'form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var laydate = layui.laydate;
    var form = layui.form;

    //渲染时间选择框
    laydate.render({
        elem: '#timeLimit',
        trigger: 'click',
        range: true,
        type: 'date'
    });

    /**
     * 项目勘察文件表管理
     */
    var ItemSub = {
        tableId: "itemSubTable"
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + ItemSub.tableId,
        url: Feng.ctxPath + '/itemSub/list',
        page: true,
        height: "full-158",
        limit: 20,
        cols: [[
            {field: 'id', hide: true, title: '主键'},
            {field: 'itemName', title: '项目名'},
            {field: 'surName', title: '文档名'},
            {field: 'location', title: '位置'},
            {field: 'surNum', title: '勘察号'},
            {field: 'levelName', title: '资质等级'},
            {field: 'stageName', title: '勘察阶段'},
            // {field: 'writeName', title: '编写'},
            // {field: 'reviewName', title: '复核'},
            // {field: 'checkName', title: '审核'},
            // {field: 'examName', title: '审定'},
            {field: 'chargeName', title: '负责人'},
            // {field: 'enginName', title: '总工'},
            // {field: 'legalName', title: '法人'},
            {field: 'writeTime', title: '完成日期', templet: function(d){
                    return d.writeTime == ""? "":d.writeTime.slice(0,10);
            }},
            {align: 'center', toolbar: '#tableBar', title: '操作', width:350}
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

    getDict('1303870720255148034','surLevel');
    getDict('1303870848382746625','surStage');

    function getDict(typeId, id){
        $.ajax({
            url: Feng.ctxPath + "/dict/listDicts",
            data:{
                dictTypeId : typeId
            },
            dataType: 'json',
            type: 'post',
            success: function (data) {
                $.each(data.data, function (index, item) {
                    $('#' + id).append(new Option(item.name, item.dictId));
                });
                form.render("select");
            }
        });
    }

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        var queryData = {
            itemName : $("#itemName").val(),
            itemType : $("#itemType").val(),
            surNum : $("#surNum").val(),
            surLevel : $("#surLevel").val(),
            surStage : $("#surStage").val(),
            surName : $("#surName").val()
        };
        table.reload(ItemSub.tableId, {
            where: queryData, page: {curr: 1}
        });
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        func.open({
            title: '添加文件',
            content: Feng.ctxPath + '/itemSub/add',
            tableId: ItemSub.tableId
        });
    });

    // 工具条点击事件
    table.on('tool(' + ItemSub.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            func.open({
                title: '修改文件信息',
                content: Feng.ctxPath + '/itemSub/edit?id=' + data.id,
                tableId: ItemSub.tableId
            });
        } else if (layEvent === 'delete') {
            var operation = function () {
                var ajax = new $ax(Feng.ctxPath + "/itemSub/delete", function (data) {
                    Feng.success("删除成功!");
                    table.reload(ItemSub.tableId);
                }, function (data) {
                    Feng.error("删除失败!" + data.responseJSON.message + "!");
                });
                ajax.set("id", data.id);
                ajax.start();
            };
            Feng.confirm("是否删除?", operation);
        } else if (layEvent === 'check') {
            layer.open({
                type: 2,
                area: ['100%', '100%'],
                content: Feng.ctxPath + '/itemSub/itemSubDetail?subId=' + data.id + '&surName=' + data.surName
            });
        } else if (layEvent === 'borrow') {
            layer.open({
                type: 2,
                title: data.surName,
                area: ['60%', '75%'],
                content: Feng.ctxPath + '/recordBorrow/add?subId=' + data.id
            });
        } else if (layEvent === 'upload') {
            layer.open({
                type: 2,
                title: data.surName,
                area: ['60%', '75%'],
                content: Feng.ctxPath + '/itemSub/file?subId=' + data.id
            });
        }
    });
});
