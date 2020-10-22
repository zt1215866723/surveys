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

    var ajax = new $ax(Feng.ctxPath + "/item/getItemDetail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    $('#itemCode').html(result.data.itemCode);
    $('#itemName').html(result.data.itemName);
    $('#head').html(result.data.head);
    $('#beginDate').html(result.data.beginDate.slice(0,10));
    $('#endDate').html(result.data.endDate.slice(0,10));
    $('#xaxis').html(result.data.xaxis);
    $('#yaxis').html(result.data.yaxis);
    $('#typeName').html(result.data.typeName);
    $('#processName').html(result.data.processName);
    $('#location').html(result.data.location);
    $('#remarks').html(result.data.remarks);

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Item.tableId,
        url: Feng.ctxPath + '/drilling/list',
        page: true,
        where: {
            itemId : Feng.getUrlParam("id")
        },
        height: "full-158",
        limit: 10,
        cols: [[
            {field: 'holeCode', sort: true, title: '钻孔编号'},
            {field: 'type', sort: true, title: '钻孔类型'},
            {field: 'zkx', sort: true, title: '横坐标'},
            {field: 'zky', sort: true, title: '纵坐标'},
            {field: 'zkbg', sort: true, title: '孔口高程(m)'},
            {field: 'depth', sort: true, title: '勘探深度(m)'},
            {field: 'zkzj', sort: true, title: '钻孔直径(mm)'},
            {align: 'center', toolbar: '#tableBar', title: '操作',width:270}
        ]]
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        var queryData = {
            holeCode:$("#holeCode").val(),
            type:$("#type").val()
        };
        table.reload(Item.tableId, {
            where: queryData, page: {curr: 1}
        });
    });

    // 返回按钮点击事件
    $('#btnReturn').click(function () {
        location.href = "/item"
    });

    // 项目定位按钮点击事件
    $('#projectMap').click(function () {
        func.open({
            title: result.data.itemName+'定位',
            content: Feng.ctxPath + '/item/detailMap?xaxis=' + result.data.xaxis +'&yaxis=' + result.data.yaxis +'&itemName=' + encodeURI(encodeURI(result.data.itemName)),
            tableId: Item.tableId
        });
    });

    // 工具条点击事件
    table.on('tool(' + Item.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'look') {
            location.href = "/drilling/drillingDetail?id=" + data.id
        } else if (layEvent === 'histogram') {
            location.href = "/item/itemDetail?id=" + data.id
        } else if (layEvent === 'edit') {
            func.open({
                title: '修改钻孔',
                content: Feng.ctxPath + '/drilling/edit?id=' + data.id,
                tableId: Item.tableId
            });
        }
    });
});
