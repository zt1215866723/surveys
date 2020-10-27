layui.use(['table', 'admin', 'ax', 'form', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var form = layui.form;
    var func = layui.func;

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

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Item.tableId,
        url: Feng.ctxPath + '/item/list',
        page: true,
        height: "285px",
        limit: 5,
        cols: [[
            {field: 'itemName', title: '项目名称',align:'center',width:250},
            {field: 'itemCode', title: '项目编号',align:'center'},
            {field: 'typeName', title: '项目类型',align:'center'},
            {field: 'beginDate', title: '开始日期',align:'center', templet: function(d){
                    return d.beginDate == ""? "":d.beginDate.slice(0,10);
                }},
            {field: 'processName', title: '项目进度',align:'center'}
        ]]
    });

    //地图
    var map = echarts.init(document.getElementById('map'));
    var mapO = {
        title : {
            text: '区域工程统计',
            textStyle:{
                color:"#fff"
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: '{b}<br/>{c}'
        },
        visualMap: {
            min: 800,
            max: 10000,
            text:['High','Low'],
            realtime: false,
            calculable: true,
            inRange: {
                color: ['lightskyblue','yellow', 'orangered']
            },
            textStyle:{
                color:'#fff'
            }
        },
        series: {
            left:'25%',
            top:'10%',
            zoom:1,
            name: 'langfang',
            type: 'map',
            mapType: 'hebei',
            label: {
                normal:{
                    show:true,
                    textStyle:{
                        color:'#fff',
                        fontSize:13
                    }
                },
                emphasis: {
                    show: true,
                    textStyle:{
                        color:'#fff',
                        fontSize:13
                    }
                }
            },
            itemStyle: {
                borderWidth:1,
                borderColor:'#fff',
                normal: {
                    areaColor: '#323c48',
                    borderColor: 'dodgerblue'
                },
                emphasis: {
                    areaColor: '#0f0'
                }
            },
        }
    };
    // map.setOption(mapO);
    // $.ajaxSettings.async = false;
    $.getJSON('/assets/item/langfang.json', function(data){
        echarts.registerMap( 'hebei', data);
        var mapData = [];
        for( var i=0;i<data.features.length;i++ ){
            mapData.push({
                    name:data.features[i].properties.name,
                    value:0

                })
        }
        //AJAX获取城市区域的工程数量统计
        $.ajax({
            url:Feng.ctxPath + '/item/getList',
            method:"post",
            success:function (data) {

                $.each(data.data,function (index,item) {
                    // 百度地图API功能
                    var point = new BMap.Point(item.xaxis,item.yaxis);
                    var gc = new BMap.Geocoder();
                    gc.getLocation(point, function(rs){
                        var addComp = rs.addressComponents;
                        $.each(mapData,function (index1,item1) {
                            // console.log(addComp.district)
                            // console.log(item1.name)
                            if (addComp.district === item1.name) {
                                // console.log(item1.value)
                                item1.value++
                                // console.log(item1.value)
                            }
                        })
                        mapO.series.data = mapData
                        map.setOption(mapO,true);
                    });
                })
            }
        })
    });

});
