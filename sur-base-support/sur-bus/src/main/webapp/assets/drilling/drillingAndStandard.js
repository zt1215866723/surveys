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
                // {field: 'rowNum',align:'center', title: '序号', rowspan: 2},
                {field: 'holeCode',align:'center', title: '钻孔编号', rowspan: 2, totalRowText: '合计'},
                {field: 'type',align:'center', title: '钻孔类型', rowspan: 2},
                {field: 'depth',align:'center', title: '钻孔深度(m)', rowspan: 2, totalRow: true},
                {field: 'zkbg',align:'center', title: '地面高程(m)', rowspan: 2},
                {title: '坐标',align:'center',colspan: 2},
                {title: '取样个数',align:'center',colspan: 2},
                {field: 'bg',align:'center', title: '标贯(次)', rowspan: 2, totalRow: true},
                {title: '地下稳定水位',align:'center',colspan: 2}
            ],
            [
                {field: 'zkx',align:'center', title: '横坐标'},
                {field: 'zky',align:'center', title: '纵坐标'},
                {field: 'yz',align:'center', title: '原状样', totalRow: true},
                {field: 'rd',align:'center', title: '扰动样', totalRow: true},
                {field: 'ms',align:'center', title: '埋深(m)'},
                {field: 'gc',align:'center', title: '高程(m)'}
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
            {field: 'bh', title: '土层编号'},
            {field: 'name', title: '土层名称'},
            {field: 'tcdzsd', title: '地质时代'},
            {field: 'tcdzcy', title: '地质成因'},
            {field: 'tcms', title: '土层描述',width: 300},
            {field: 'sdfw', title: '深度范围(m)'},
            {field: 'hdfw', title: '厚度范围(m)'},
            {field: 'hdpj', title: '厚度平均值(m)'},
            {field: 'zkSum', title: '备注',width:180, templet: function (d) {
                    return "本层在"+d.zkSum+"个钻孔中揭示"
                }}
        ]];
    };

    // 渲染勘探点一览表格
    var tableResult = table.render({
        elem: '#' + Drilling.tableId,
        url: Feng.ctxPath + '/drilling/selectExplorationPoints',
        page: false,
        totalRow: true,
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
