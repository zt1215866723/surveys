layui.use(['element', 'table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var element = layui.element;
    /**
     * 勘探点数据表管理
     */
    var Drilling = {
        tableId: "drillingTable"
    };

    var holeCode = Feng.getUrlParam("holeCode");

    /**
     * 初始化表格的列
     */
    Drilling.initColumn = function () {
        return [
            [
                {field: 'holeCode',align:'center', sort: true, title: '钻孔编号', rowspan: 2},
                {field: 'type',align:'center', title: '钻孔类型', rowspan: 2},
                {title: '坐标',align:'center',colspan: 3},
                {field: 'zkbg',align:'center', sort: true, title: '孔口高程(m)', rowspan: 2},
                {field: 'depth',align:'center', sort: true, title: '勘探深度(m)', rowspan: 2},
                {field: 'zkzj',align:'center', sort: true, title: '钻孔直径(mm)', rowspan: 2},
                {
                    field: 'zkxn',align:'center', title: '虚拟钻孔', rowspan: 2, templet: function (item) {
                        if (item.zkxn == '0') {
                            return "不是";
                        } else if (item.zkxn == '1') {
                            return "是";
                        } else {
                            return "";
                        }
                    }
                },
                {field: 'zkdj',align:'center', sort: true, title: '勘探点等级', rowspan: 2}
            ],
            [
                {field: 'zkx',align:'center', sort: true, title: '横坐标'},
                {field: 'zky',align:'center', sort: true, title: '纵坐标'}
            ]
        ];
    };

    /**
     * 地层信息管理
     */
    var Standard = {
        tableId: "standardTable"
    };

    /**
     * 初始化表格的列
     */
    Standard.initColumn = function () {
        return [[
            {field: 'holeCode', sort: true, title: '钻孔编号'},
            {field: 'depth', sort: true, title: '层底深度（米）'},
            {field: 'mainCode', sort: true, title: '主层编号'},
            {field: 'secondaryCode', sort: true, title: '亚层编号'},
            {field: 'thirdCode', sort: true, title: '次亚层编号'},
            {field: 'tcdzsd', sort: true, title: '地质时代'},
            {field: 'tcdzcy', sort: true, title: '地质成因'},
            {field: 'tcdchd', sort: true, title: '自然层厚度(m)'},
            {field: 'tchd', sort: true, title: '地层厚度(m)'},
            {field: 'type', sort: true, title: '岩土类别'},
            {field: 'name', sort: true, title: '岩土名称'},
            {field: 'tcmdh', sort: true, title: '土名代号'},
            {field: 'tcymc', sort: true, title: '亚岩土名称'},
            {field: 'tcys', sort: true, title: '土层颜色'},
            {field: 'tcmsd', sort: true, title: '密实度'},
            {field: 'tcsid', sort: true, title: '湿度'},
            {field: 'tcksx', sort: true, title: '可塑性'},
            {field: 'tchyd', sort: true, title: '浑圆度'},
            {field: 'tcjyx', sort: true, title: '均匀性'},
            {field: 'tcfhcd', sort: true, title: '风化程度'},
            {field: 'tcysqx', sort: true, title: '岩层倾向(度)'},
            {field: 'tcysqj', sort: true, title: '岩层倾角(度)'},
            {field: 'tckwcf', sort: true, title: '矿物成分'},
            {field: 'tcjggz', sort: true, title: '结构构造'},
            {field: 'tcbhw', sort: true, title: '包含物'},
            {field: 'tcqw', sort: true, title: '气味'},
            {field: 'desc', sort: true, title: '土层描述'},
            {field: 'tcztx', sort: true, title: '完整程度'},
            {field: 'tcjycd', sort: true, title: '坚硬程度'},
            {field: 'tcpl', sort: true, title: '破碎程度'},
            {field: 'tcjlfy', sort: true, title: '节理发育'},
            {field: 'tcdcms', sort: true, title: '地层描述'},
            {field: 'tcjljj', sort: true, title: '节理间距(cm)'},
            {field: 'tcpxjd', sort: true, title: '视倾角(度)'},
            {field: 'tcqttz', sort: true, title: '其他特征'},
            {field: 'tczrzd', sort: true, title: '天然重度(kN/m3)'},
            {field: 'tcnjl', sort: true, title: '粘聚力(kPa)'},
            {field: 'tcnmcj', sort: true, title: '内摩擦角(°)'},
            {field: 'tcczl', sort: true, title: '承载力(kPa)'},
            {field: 'tcysml', sort: true, title: '压缩模量(MPa)'}
        ]];
    };

    // 渲染勘探点一览表格
    var tableResult = table.render({
        elem: '#' + Drilling.tableId,
        url: Feng.ctxPath + '/drilling/selectDrillingByIds',
        page: false,
        where: {
            itemId : "1303984243408805890",
            holeCode: "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27"
        },
        height: "full-158",
        cellMinWidth: 100,
        cols: Drilling.initColumn()
    });

    // 渲染地层信息统计表格
    var tableResult = table.render({
        elem: '#' + Standard.tableId,
        url: Feng.ctxPath + '/standard/selectStandardByIds',
        page: false,
        where: {
            itemId : "1303984243408805890",
            holeCode: "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27"
        },
        height: "full-158",
        cellMinWidth: 100,
        cols: Standard.initColumn()
    });
});
