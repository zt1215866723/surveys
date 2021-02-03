layui.use(['element', 'table', 'admin', 'ax', 'func', 'layer', 'form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var element = layui.element;
    var layer = layui.layer;
    var form = layui.form;

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
            {field: 'tchd', sort: true, title: '地层厚度(m)'},
            {field: 'type', sort: true, title: '岩土类别'},
            {field: 'name', sort: true, title: '岩土名称'},
            {field: 'tcms', sort: true, title: '土层描述'},
            {align: 'center', toolbar: '#tableBar1', title: '操作'}
        ]];
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Standard.tableId,
        url: Feng.ctxPath + '/standard/list',
        where: {
            itemId: result.data.itemId,
            holeCode: result.data.holeCode
        },
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Standard.initColumn()
    });

    // 工具条点击事件
    table.on('tool(' + Standard.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            func.open({
                title: '修改地层信息',
                content: Feng.ctxPath + '/standard/edit?id=' + data.id,
                tableId: Standard.tableId
            });
        } else if (layEvent === 'delete') {
            Standard.onDeleteItem(data);
        }
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
            {field: 'jtkxyl', sort: true, title: '孔隙水压力(kPa)'},
            {align: 'center', toolbar: '#tableBar2', title: '操作'}
        ]];
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Static.tableId,
        url: Feng.ctxPath + '/staticTest/list',
        where: {
            itemId: result.data.itemId,
            holeCode: result.data.holeCode
        },
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Static.initColumn()
    });

    // 工具条点击事件
    table.on('tool(' + Static.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            func.open({
                title: '修改地层信息',
                content: Feng.ctxPath + '/staticTest/edit?id=' + data.id,
                tableId: Static.tableId
            });
        } else if (layEvent === 'delete') {
            Static.onDeleteItem(data);
        }
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
            {align: 'center', toolbar: '#tableBar3', title: '操作'}
        ]];
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Penetration.tableId,
        url: Feng.ctxPath + '/standardPenetration/list',
        where: {
            itemId: result.data.itemId,
            holeCode: result.data.holeCode
        },
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Penetration.initColumn()
    });

    // 工具条点击事件
    table.on('tool(' + Penetration.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            func.open({
                title: '修改标贯数据表',
                content: Feng.ctxPath + '/standardPenetration/edit?id=' + data.id,
                tableId: Penetration.tableId
            });
        } else if (layEvent === 'delete') {
            Penetration.onDeleteItem(data);
        }
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
            {
                field: 'swlx', sort: true, title: '是否稳定', templet: function (d) {
                    if (d.swlx == 1) {
                        return "是"
                    } else if (d.swlx == 0) {
                        return "不是"
                    }else {
                        return ""
                    }
                }
            },
            {
                field: 'swch', sort: true, title: '是否为地下水位', templet: function (d) {
                    if (d.swch == 1) {
                        return "是"
                    } else if (d.swch == 0) {
                        return "不是"
                    }else {
                        return ""
                    }
                }
            },
            {field: 'swcsrq', sort: true, title: '测水日期'},
            {field: 'swdxsw', sort: true, title: '地下水温(度)'},
            {field: 'swfw', sort: true, title: '水位范围'},
            {field: 'swxz', sort: true, title: '地下水类型'},
            {align: 'center', toolbar: '#tableBar4', title: '操作'}
        ]];
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Water.tableId,
        url: Feng.ctxPath + '/waterLevel/list',
        where: {
            itemId: result.data.itemId,
            holeCode: result.data.holeCode
        },
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Water.initColumn()
    });

    // 工具条点击事件
    table.on('tool(' + Water.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            func.open({
                title: '修改水位信息',
                content: Feng.ctxPath + '/waterLevel/edit?id=' + data.id,
                tableId: Water.tableId
            });
        } else if (layEvent === 'delete') {
            Water.onDeleteItem(data);
        }
    });

    /**
     * 取样数据表管理
     */
    var Sample = {
        tableId: "sampleTable"
    };

    /**
     * 初始化表格的列
     */
    Sample.initColumn = function () {
        return [[
            {field: 'qybh', sort: true, title: '取样编号'},
            {field: 'qysd', sort: true, title: '取样顶深度(m)'},
            {field: 'qyhd', sort: true, title: '取样长度(m)'},
            {field: 'qydc', sort: true, title: '所在地层'},
            {
                field: 'qylx', sort: true, title: '取样类型', templet: function (d) {
                    if (d.qylx == 1) {
                        return "扰动样"
                    } else if (d.qylx == 0) {
                        return "原状样"
                    }else {
                        return ""
                    }
                }
            },
            {field: 'qyzlmd', sort: true, title: '质量密度ρ(g/cm^3)'},
            {field: 'qybz', sort: true, title: '土粒比重Gs'},
            {field: 'qyhsl', sort: true, title: '含水量ω(%)'},
            {field: 'qyyx', sort: true, title: '液限ωL(%)'},
            {field: 'qysx', sort: true, title: '塑限ωP(%)'},
            {align: 'center', toolbar: '#tableBar5', title: '操作'}
        ]];
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Sample.tableId,
        url: Feng.ctxPath + '/sample/list',
        where: {
            itemId: result.data.itemId,
            holeCode: result.data.holeCode
        },
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Sample.initColumn()
    });

    // 工具条点击事件
    table.on('tool(' + Sample.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            func.open({
                title: '修改取样数据表',
                content: Feng.ctxPath + '/sample/edit?id=' + data.id,
                tableId: Sample.tableId
            });
        } else if (layEvent === 'delete') {
            Sample.onDeleteItem(data);
        }
    });

    // 添加按钮点击事件
    $('#standardBtn').click(function () {
        func.open({
            content: Feng.ctxPath + '/standard/add?itemId='+result.data.itemId +'&holeCode='+result.data.holeCode,
            tableId: Standard.tableId
        });
    });

    // 添加按钮点击事件
    $('#staticBtn').click(function () {
        func.open({
            content: Feng.ctxPath + '/staticTest/add?itemId='+result.data.itemId +'&holeCode='+result.data.holeCode,
            tableId: Static.tableId
        });
    });

    // 添加按钮点击事件
    $('#penetrationBtn').click(function () {
        func.open({
            content: Feng.ctxPath + '/standardPenetration/add?itemId='+result.data.itemId +'&holeCode='+result.data.holeCode,
            tableId: Penetration.tableId
        });
    });

    // 添加按钮点击事件
    $('#waterBtn').click(function () {
        func.open({
            content: Feng.ctxPath + '/waterLevel/add?itemId='+result.data.itemId +'&holeCode='+result.data.holeCode,
            tableId: Water.tableId
        });
    });

    // 添加按钮点击事件
    $('#sampleBtn').click(function () {
        func.open({
            content: Feng.ctxPath + '/sample/add?itemId='+result.data.itemId +'&holeCode='+result.data.holeCode,
            tableId: Sample.tableId
        });
    });

    //查询["地层数","静探数","标贯数","水位数","取样数"]
    $.ajax({
        url: Feng.ctxPath + "/drilling/drillingCounts",
        dataType: 'json',
        type: 'post',
        data: {
            itemId: result.data.itemId,
            holeCode: result.data.holeCode
        },
        success: function (data) {
            var option = {
                series: [
                    {
                        name: '数量',
                        type: 'bar',
                        data: data.data,
                        barWidth: '50%',

                    },
                    {
                        name: '数量',
                        type: 'line',
                        data: data.data,
                        symbolSize: 10,
                        itemStyle: {
                            normal: {
                                color: "#DDA0DD"
                            }

                        }
                    }
                ]
            };
            myChart.setOption(option);
        }
    });

    var myChart = echarts.init(document.getElementById('main'));

    window.addEventListener("resize", function () {
        myChart.resize();
    });

    var option = {
        title: {
            left: 'left',
            text: '概率',
            show: false
        },
        tooltip: {
            trigger: 'axis',
            formatter: '{a}:{c}',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        grid: {
            show: false,
            top: '30',
            bottom: '60',
            right: '60',
            left: '60'
        },
        xAxis: [
            {
                type: 'category',
                data: ["地层数","取样数","水位数","标贯数","静探数"],
                axisPointer: {
                    type: 'shadow'

                },

                axisTick: {
                    show: true,
                    interval: 0
                },

            }
        ],

//设置两个y轴，左边显示数量，右边显示概率

        yAxis: [
            {
                type: 'value',
                name: '数量',
                show: true,
                interval: 50,
            }
        ],

//每个设备分数量、概率2个指标，只要让他们的name一致，即可通过，legeng进行统一的切换

        series: [
            {
                name: '数量',
                type: 'bar',
                data: [],
                barWidth: '50%',

            },
            {
                name: '数量',
                type: 'line',
                data: [],
                symbolSize: 10,
                itemStyle: {
                    normal: {
                        color: "#DDA0DD"
                    }

                }
            }
        ]
    };
    myChart.setOption(option);
});
