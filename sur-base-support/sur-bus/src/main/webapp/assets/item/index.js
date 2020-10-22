layui.use(['table', 'admin', 'ax', 'form', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var form = layui.form;
    var func = layui.func;

    // // 百度地图API功能
    // var point = new BMap.Point(116.681233,38.904405);
    // var gc = new BMap.Geocoder();
    // gc.getLocation(point, function(rs){
    //     var addComp = rs.addressComponents;
    //     alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
    // });

    //获取dom容器
    var myChart1 = echarts.init(document.getElementById('itemChart'));
    //項目簡介
    $.ajax({
        url: Feng.ctxPath + "/item/itemECharts",
        dataType: 'json',
        type: 'post',
        success: function (result) {
            option = {
                title: {
                    text: '项目概况',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)'
                },
                series: [
                    {
                        name: '项目分类',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: result.data,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart1.setOption(option);
        }
    });

    //合同图表点击
    myChart1.on('click', function (params) {
        console.log(params)

        // 搜索按钮点击事件
            var queryData = {
                type: params.data.id
            };
            table.reload(Item.tableId, {
                where: queryData, page: {curr: 1}
            });
    });

    //钻孔簡介
    $.ajax({
        url: Feng.ctxPath + "/drilling/drillingECharts",
        dataType: 'json',
        type: 'post',
        success: function (result) {
            option = {
                title: {
                    text: '钻孔概况',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)'
                },
                series: [
                    {
                        name: '钻孔分类',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: result.data,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };

            //获取dom容器
            var myChart = echarts.init(document.getElementById('zkChart'));
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });

    /**
     * 项目表管理
     */
    var Item = {
        tableId: "itemTable"
    };

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
        page: true
        ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
            title: '提示'
            ,layEvent: 'LAYTABLE_TIPS'
            ,icon: 'layui-icon-tips'
        }],
        height: "full-158",
        limit: 10,
        cols: [[
            {field: 'itemName', title: '项目名称',align:'center',width:250},
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
            {align: 'center', toolbar: '#tableBar', title: '操作'}
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

    // 工具条点击事件
    table.on('tool(' + Item.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'document') {
            layer.open({
                type: 2,
                area: ['100%', '100%'],
                content: Feng.ctxPath + '/item/document?itemId=' + data.id
            });
        }
    });
});
