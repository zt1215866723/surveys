layui.use(['table', 'admin', 'ax', 'func', 'tree'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var tree = layui.tree;

    var itemId = Feng.getUrlParam("itemId");

    /**
     * 文档的目录详情。管理
     */
    var SubDetail = {
        tableId: "subDetailTable"
    };

    // 初始化树
    var ajax = new $ax(Feng.ctxPath + "/item/getTree", function (data) {
        tree.render({
            elem: '#tree',
            data: data,
            click: function(obj){
                console.log(obj.data); //得到当前点击的节点数据
                console.log(obj.state); //得到当前节点的展开状态：open、close、normal
                console.log(obj.elem); //得到当前节点元素
                var queryData = {
                    itemId:itemId,
                    cataId:''
                };
                if(obj.data.children.length==0){
                    queryData.subId = obj.data.id;
                }
                SubDetail.search(queryData);
            },
            onlyIconControl: true
        });
    }, function (data) {
    });
    ajax.set({
        itemId : itemId
    });
    ajax.start();

    /**
     * 初始化表格的列
     */
    SubDetail.initColumn = function () {
        return [[
            {field: 'cataName', title: '内容'},
            {field: 'remarks', title: '描述'},
            {field: 'pageNum', title: '页数'},
            {field: 'saveUrl', title: '存放路径'},
            {field: 'prepName', title: '编制人'},
            {field: 'revieName', title: '复核人'},
            {field: 'checkName', title: '审核人'},
            {align: 'center', toolbar: '#tableBar', title: '操作', width:250}
        ]];
    };

    /**
     * 查询
     */
    SubDetail.search = function (queryData) {
        table.reload(SubDetail.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    SubDetail.openEditDlg = function (data) {
        func.open({
            title: '编辑信息',
            content: Feng.ctxPath + '/subDetail/edit?id=' + data.id,
            tableId: SubDetail.tableId
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    SubDetail.onDeleteItem = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/subDetail/delete", function (data) {
                Feng.success("删除成功!");
                table.reload(SubDetail.tableId);
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
        elem: '#' + SubDetail.tableId,
        url: Feng.ctxPath + '/subDetail/list',
        page: true,
        where:{
            itemId: itemId
        },
        height: "full-158",
        cols: SubDetail.initColumn(),
        limit: 20
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        SubDetail.openAddDlg();
    });

    // 工具条点击事件
    table.on('tool(' + SubDetail.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            SubDetail.openEditDlg(data);
        } else if (layEvent === 'delete') {
            SubDetail.onDeleteItem(data);
        } else if (layEvent === 'view') {
            var viewerUrl = Feng.ctxPath + "/assets/pdf/web/viewer.html";
            var pdfUrl = Feng.ctxPath + "/fileView/toPdfFile?subDetailId=" + data.id;
            window.open(`${viewerUrl}?file=${encodeURIComponent(pdfUrl)}`);
        } else if (layEvent === 'download') {
            window.open(Feng.ctxPath + "/subDetail/fileDownload?subDetailId=" + data.id, '_blank')
        }
    });
});
$(function () {
    var panehHidden = false;
    if ($(this).width() < 769) {
        panehHidden = true;
    }
    $('#myContiner').layout({initClosed: panehHidden, west__size: 260});
});