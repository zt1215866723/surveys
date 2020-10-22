layui.use(['table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    var itemIds = Feng.getUrlParam("itemIds");
    var holeCode = Feng.getUrlParam("holeCode");

    var arr = [];

    refreshData();

    function refreshData(data){
        var index = layer.load(1);
        $.ajax({
            url: Feng.ctxPath + "/staticTest/staticTestCharts",
            dataType: 'json',
            type: 'post',
            data: {
                "itemIds" : itemIds,
                "holeCode": holeCode
            },
            success: function (data) {
                layer.close(index);
                arr = data.data;
                var option = myChart.getOption();
                var yAxis = [];
                var xAxis = [];
                // var xAxis1 = [];
                $.each(data.data, function (index, item) {
                    yAxis.push(item.depth);
                    var obj = new Object();
                    obj.name = "比贯入阻力(MPa)";
                    obj.value = item.jtbgrzl;
                    xAxis.push(obj)
                    // var obj = new Object();
                    // obj.name = "锥头阻力(MPa)";
                    // obj.value = item.jtztzl;
                    // xAxis1.push(obj)
                });
                option.yAxis[0].data = yAxis;
                option.series[0].data = xAxis;
                // option.series[1].data = xAxis1;
                myChart.resize();
                myChart.setOption(option,true);
            }
        });
    }
    var myChart = echarts.init(document.getElementById('main'));

    window.addEventListener("resize",function(){
        myChart.resize();
    });
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            },
            formatter : function(e) {
                return "<span style='display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:#58c26b;'></span>" +"深度(m):" +e[0].axisValue + '<br>' + e[0].marker + e[0].data.name + ":" +e[0].data.value
            }

        },
        dataZoom: [
            {
                xAxisIndex: 0,
                type: 'inside',
                disabled : true,
                realtime: true
            },
            {
                yAxisIndex:0,
                type: 'inside',
                realtime: true
            }
        ],
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [
            {
                name:"(MPa)",
                position : 'top',
                max:20,
                axisLine:{
                    lineStyle : {
                        color:"#000000"
                    }
                }
            }
            // ,
            // {
            //     name:"(MPa)",
            //     position : 'top',
            //     max:200,
            //     offset:30,
            //     axisLine:{
            //         lineStyle : {
            //             color:"#ff0000"
            //         }
            //     }
            // }
        ],
        yAxis: {
            name: '(m)',
            type: 'category',
            inverse: "true",
            spitNumber:5,
            data : []
        },
        series: [
            {
                name: '比贯入阻力(MPa)',
                type: 'line',
                xAxisIndex:0,
                data: [],
                lineStyle : {
                    color:"#000000"
                }
            }
            // ,
            // {
            //     name: '锥头阻力(MPa)',
            //     type: 'line',
            //     xAxisIndex:1,
            //     data: [],
            //     lineStyle : {
            //         color:"#ff0000"
            //     }
            // }
        ]

    };
    myChart.setOption(option);
});
