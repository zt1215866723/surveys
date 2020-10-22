layui.use(['element', 'table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var element = layui.element;

    var ajax = new $ax(Feng.ctxPath + "/drilling/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    $('#holeCode').html(result.data.holeCode);
    $('#type').html(result.data.type);
    $('#zkx').html(result.data.zkx);
    $('#zky').html(result.data.zky);
    $('#zkbg').html(result.data.zkbg);
    $('#zkzj').html(result.data.zkzj);
    $('#depth').html(result.data.depth);
    $('#zkksrq').html(result.data.zkksrq);
    $('#zkzzrq').html(result.data.zkzzrq);

    var ajax1 = new $ax(Feng.ctxPath + "/item/detail?id=" + result.data.itemId);
    var result1 = ajax1.start();
    $('#itemName').html(result1.data.itemName);
    $('#itemCode').html(result1.data.itemCode);

    /**
     * 地层信息管理
     */
    var Standard = {
        tableId: "standardTable"
    };

    // 返回按钮点击事件
    $('#btnReturn').click(function () {
        location.href = "/item/itemDetail?id=" + result.data.itemId
    });

    /**
     * 初始化表格的列
     */
    Standard.initColumn = function () {
        return [[
            {field: 'depth', sort: true, title: '层底深度（米）'},
            {field: 'mainCode', sort: true, title: '主层编号'},
            {field: 'secondaryCode', sort: true, title: '亚层编号'},
            {field: 'thirdCode', sort: true, title: '次亚层编号'},
            // {field: 'tcdzsd', sort: true, title: '地质时代'},
            // {field: 'tcdzcy', sort: true, title: '地质成因'},
            // {field: 'tcdchd', sort: true, title: '自然层厚度(m)'},
            {field: 'tchd', sort: true, title: '地层厚度(m)'},
            {field: 'type', sort: true, title: '岩土类别'},
            {field: 'name', sort: true, title: '岩土名称'},
            // {field: 'tcmdh', sort: true, title: '土名代号'},
            // {field: 'tcymc', sort: true, title: '亚岩土名称'},
            // {field: 'tcys', sort: true, title: '土层颜色'},
            // {field: 'tcmsd', sort: true, title: '密实度'},
            // {field: 'tcsid', sort: true, title: '湿度'},
            // {field: 'tcksx', sort: true, title: '可塑性'},
            // {field: 'tchyd', sort: true, title: '浑圆度'},
            // {field: 'tcjyx', sort: true, title: '均匀性'},
            // {field: 'tcfhcd', sort: true, title: '风化程度'},
            // {field: 'tcysqx', sort: true, title: '岩层倾向(度)'},
            // {field: 'tcysqj', sort: true, title: '岩层倾角(度)'},
            // {field: 'tckwcf', sort: true, title: '矿物成分'},
            // {field: 'tcjggz', sort: true, title: '结构构造'},
            // {field: 'tcbhw', sort: true, title: '包含物'},
            // {field: 'tcqw', sort: true, title: '气味'},
            {field: 'tcms', sort: true, title: '土层描述'}
            // {field: 'tcztx', sort: true, title: '完整程度'},
            // {field: 'tcjycd', sort: true, title: '坚硬程度'},
            // {field: 'tcpl', sort: true, title: '破碎程度'},
            // {field: 'tcjlfy', sort: true, title: '节理发育'},
            // {field: 'tcdcms', sort: true, title: '地层描述'},
            // {field: 'tcjljj', sort: true, title: '节理间距(cm)'},
            // {field: 'tcpxjd', sort: true, title: '视倾角(度)'},
            // {field: 'tcqttz', sort: true, title: '其他特征'},
            // {field: 'tczrzd', sort: true, title: '天然重度(kN/m3)'},
            // {field: 'tcnjl', sort: true, title: '粘聚力(kPa)'},
            // {field: 'tcnmcj', sort: true, title: '内摩擦角(°)'},
            // {field: 'tcczl', sort: true, title: '承载力(kPa)'},
            // {field: 'tcysml', sort: true, title: '压缩模量(MPa)'},
            // {field: 'tcyzzCzltzz', sort: true, title: '预制桩侧阻力特征值(kPa)'},
            // {field: 'tcyzzDzltzz', sort: true, title: '预制桩端阻力特征值(kPa)'},
            // {field: 'tcyzzCzlbzz', sort: true, title: '预制桩极限侧阻力标准值(kPa)'},
            // {field: 'tcyzzDzlbzz', sort: true, title: '预制桩极限端阻力标准值(kPa)'},
            // {field: 'tcckzCzltzz', sort: true, title: '冲(钻)孔桩侧阻力特征值(kPa)'},
            // {field: 'tcckzDzltzz', sort: true, title: '冲(钻)孔桩端阻力特征值(kPa)'},
            // {field: 'tcckzCzlbzz', sort: true, title: '冲(钻)孔桩极限侧阻力标准值(kPa)'},
            // {field: 'tcckzDzlbzz', sort: true, title: '冲(钻)孔桩极限端阻力标准值(kPa)'},
            // {field: 'tcuserdefine1', sort: true, title: '自定义字段1'},
            // {field: 'tcuserdefine2', sort: true, title: '自定义字段2'},
            // {field: 'tcuserdefine3', sort: true, title: '自定义字段3'},
            // {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Standard.tableId,
        url: Feng.ctxPath + '/standard/list',
        where: {
            itemId : result.data.itemId,
            holeCode : result.data.holeCode
        },
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Standard.initColumn()
    });

    /**
     * 静探信息管理
     */
    var Static = {
        tableId: "staticTable"
    };

    /**
     * 初始化表格的列
     */
    Static.initColumn = function () {
        return [[
            {field: 'depth', sort: true, title: '试探点深度（米）'},
            {field: 'jtlx', sort: true, title: '静探类型'},
            {field: 'length', sort: true, title: '试验段长（米）'},
            {field: 'jtbgrzl', sort: true, title: '比贯入阻力(MPa)'},
            {field: 'jtztzl', sort: true, title: '锥头阻力(MPa)'},
            {field: 'jtcmz', sort: true, title: '侧壁摩阻力(kPa)'},
            {field: 'jtmzb', sort: true, title: '摩阻比(%)'},
            {field: 'jtkxyl', sort: true, title: '孔隙水压力(kPa)'}
        ]];
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Static.tableId,
        url: Feng.ctxPath + '/staticTest/list',
        where: {
            itemId : result.data.itemId,
            holeCode : result.data.holeCode
        },
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Static.initColumn()
    });

    /**
     * 标贯信息管理
     */
    var Penetration = {
        tableId: "penetrationTable"
    };

    /**
     * 初始化表格的列
     */
    Penetration.initColumn = function () {
        return [[
            {field: 'depth', sort: true, title: '试验点底深度（米）'},
            {field: 'bglx', sort: true, title: '标贯类型'},
            {field: 'bgtzz', sort: true, title: '特征值'},
            {field: 'length', sort: true, title: '杆长（米）'},
            {field: 'bgyzcd', sort: true, title: '一阵击数的长度(m)'},
            {field: 'bgyzjs', sort: true, title: '一阵击数(击)'},
            {field: 'bgjs', sort: true, title: '标贯击数(击/30cm)'},
            {field: 'bgxs', sort: true, title: '标贯修正系数'},
            {field: 'bgxzjs', sort: true, title: '修正后的标贯击数(击/30cm)'},
        ]];
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Penetration.tableId,
        url: Feng.ctxPath + '/standardPenetration/list',
        where: {
            itemId : result.data.itemId,
            holeCode : result.data.holeCode
        },
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Penetration.initColumn()
    });

    /**
     * 水位信息管理
     */
    var Water = {
        tableId: "waterTable"
    };

    /**
     * 初始化表格的列
     */
    Water.initColumn = function () {
        return [[
            {field: 'depth', sort: true, title: '深度（米）'},
            {field: 'swlx', sort: true, title: '是否稳定', templet: function (d) {
                if (d.swlx == 1){
                    return "是"
                }else if (d.swlx == 0){
                    return "不是"
                }
        }},
            {field: 'swch', sort: true, title: '是否为地下水位', templet: function (d) {
                    if (d.swch == 1){
                        return "是"
                    }else if (d.swch == 0){
                        return "不是"
                    }
                }},
            {field: 'swcsrq', sort: true, title: '测水日期'},
            {field: 'swdxsw', sort: true, title: '地下水温(度)'},
            {field: 'swfw', sort: true, title: '水位范围'},
            {field: 'swxz', sort: true, title: '地下水类型'},
        ]];
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Water.tableId,
        url: Feng.ctxPath + '/waterLevel/list',
        where: {
            itemId : result.data.itemId,
            holeCode : result.data.holeCode
        },
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Water.initColumn()
    });
});
