layui.use(['table', 'admin', 'ax', 'func', 'tree'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var tree = layui.tree;

    var subId = Feng.getUrlParam("subId");

    var queryData = {
        subId: subId,
        cataId: ''
    };
    // 初始化树
    var ajax = new $ax(Feng.ctxPath + "/itemSub/getTree?id=" + subId, function (data) {
        tree.render({
            elem: '#tree',
            data: data,
            accordion: true,
            showLine: false,
            click: function (obj) {
                //节点高亮
                var nodes = document.getElementsByClassName("layui-tree-txt");
                for (var i = 0; i < nodes.length; i++) {
                    if (nodes[i].innerHTML === obj.data.title)
                        nodes[i].style.color = "red";
                    else
                        nodes[i].style.color = "#555";
                }
                if (obj.data.children.length == 0) {
                    queryData.cataId = '';
                    queryData.cataId = obj.data.href;
                }
            }
        });
    }, function (data) {
    });
    var param = {
        subId: subId
    };
    ajax.set(param);
    ajax.start();

    $('#btnLook').click(function () {
        if (queryData.cataId == '' || queryData.cataId == -1) {
            layer.msg('请从左侧选择需要预览的内容。');
            return false;
        }
        var viewerUrl = Feng.ctxPath + "/assets/pdf/web/viewer.html";
        var pdfUrl = Feng.ctxPath + "/fileView/toPdfFile?path=" + encodeURIComponent(queryData.cataId);
        window.open(`${viewerUrl}?file=${encodeURIComponent(pdfUrl)}`);
        //预览之后删除生成的pdf文件
        var ajax = new $ax(Feng.ctxPath + "/fileView/delPdfFile?path=" + encodeURIComponent(queryData.cataId))
        ajax.start();
    });
    $('#btnDownLoad').click(function () {
        if (queryData.cataId == '' || queryData.cataId == -1) {
            layer.msg('请从左侧选择需要下载的内容。');
            return false;
        }
        window.open(Feng.ctxPath + "/subDetail/fileDownload?path=" + encodeURIComponent(queryData.cataId), '_blank')
    });
});
$(function () {
    var panehHidden = false;
    if ($(this).width() < 769) {
        panehHidden = true;
    }
    $('#myContiner').layout({initClosed: panehHidden, west__size: 400});
});