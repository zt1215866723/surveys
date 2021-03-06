window.onload = function () {
    layui.use(['table', 'ax', 'func', 'form', 'layer'], function () {
        var $ = layui.$;
        var table = layui.table;
        var $ax = layui.ax;
        var func = layui.func;
        var form = layui.form;
        var layer = layui.layer;

        //钻孔分类
        $.ajax({
            url: Feng.ctxPath + "/console/drillingsByType",
            dataType: 'json',
            type: 'post',
            success: function (data) {
                $("#zks").html(data.data[0].value)
                $("#yzy").html(data.data[1].value)
                $("#rdy").html(data.data[2].value)
                $("#bgs").html(data.data[3].value)
            }
        });

        //工程分类（工程类型查询时候写死了）
        $.ajax({
            url: Feng.ctxPath + "/console/itemsByType",
            dataType: 'json',
            type: 'post',
            success: function (data) {
                var count = Number(data.data[0].value) + Number(data.data[1].value) + Number(data.data[2].value) + Number(data.data[3].value)
                $("#kcItem").html(data.data[0].value)
                $("#clItem").html(data.data[1].value)
                $("#ytItem").html(data.data[2].value)
                $("#qtItem").html(data.data[3].value)
                $("#kcItem1").html(Math.round(data.data[0].value/count*100)+" <small>%</small>")
                $("#clItem1").html(Math.round(data.data[1].value/count*100)+" <small>%</small>")
                $("#ytItem1").html(Math.round(data.data[2].value/count*100)+" <small>%</small>")
                $("#qtItem1").html(100-Math.round(data.data[0].value/count*100)-Math.round(data.data[1].value/count*100)-Math.round(data.data[2].value/count*100)+" <small>%</small>")
            }
        });

        //工程进度（工程进度被写死了）
        $.ajax({
            url: Feng.ctxPath + "/console/itemsByProgram",
            dataType: 'json',
            type: 'post',
            success: function (data) {
                var iContent, iContent1, iContent2, iContent3, iContent4
                $.each(data.data[0], function (index, item) {
                    iContent =
                        "<div class='row'>" +
                        "<span class='col'>" + item.itemCode + "</span>" +
                        " <span class='col'>" + item.itemName + "</span>" +
                        "<span class='icon-dot'></span>" +
                        "</div>";
                    $("#qtgc").append(iContent);
                });
                $.each(data.data[1], function (index, item) {
                    iContent1 =
                        "<div class='row'>" +
                        "<span class='col'>" + item.itemCode + "</span>" +
                        " <span class='col'>" + item.itemName + "</span>" +
                        "<span class='icon-dot'></span>" +
                        "</div>";
                    $("#shishi").append(iContent1);
                });
                $.each(data.data[2], function (index, item) {
                    iContent2 =
                        "<div class='row'>" +
                        "<span class='col'>" + item.itemCode + "</span>" +
                        " <span class='col'>" + item.itemName + "</span>" +
                        "<span class='icon-dot'></span>" +
                        "</div>";
                    $("#bgwc").append(iContent2);
                });
                $.each(data.data[3], function (index, item) {
                    iContent3 =
                        "<div class='row'>" +
                        "<span class='col'>" + item.itemCode + "</span>" +
                        " <span class='col'>" + item.itemName + "</span>" +
                        "<span class='icon-dot'></span>" +
                        "</div>";
                    $("#yancao").append(iContent3);
                });
                $.each(data.data[4], function (index, item) {
                    iContent4 =
                        "<div class='row'>" +
                        "<span class='col'>" + item.itemCode + "</span>" +
                        " <span class='col'>" + item.itemName + "</span>" +
                        "<span class='icon-dot'></span>" +
                        "</div>";
                    $("#jungong").append(iContent4);
                });
            }
        });

        //工程热度
        $.ajax({
            url: Feng.ctxPath + "/console/itemHot",
            dataType: 'json',
            type: 'post',
            success: function (data) {
                var iContent =
                        "<li ew-href='item'>" +
                        "<i class='icon-cup1' style='color: #d93f36;'></i>" +
                        data.data[0].itemCode +" "+ data.data[0].itemName +
                        "</li>"+
                        "<li ew-href='item'>" +
                        "<i class='icon-cup2' style='color: #68d8fe;'></i>" +
                        data.data[1].itemCode +" "+ data.data[1].itemName +
                        "</li>"+
                        "<li ew-href='item'>" +
                        "<i class='icon-cup3' style='color: #4c9bfd;'></i>" +
                        data.data[2].itemCode +" "+ data.data[2].itemName +
                        "</li>";
                $("#itemHot").append(iContent);
            }
        });

        //地图
        var map = echarts.init(document.getElementById('map'));
        var mapO = {
            tooltip: {
                trigger: 'item',
                formatter: '{b}<br/>{c}'
            },
            visualMap: {
                min: 0,
                max: 2,
                text:['High','Low'],
                realtime: false,
                calculable: true,
                inRange: {
                    color: ['#c2e6ff','#53a4f5', '#1376f8','#0a55a6','#063a74']
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
                        areaColor: '#4ac8e8'
                    }
                },
            }
        };
        // map.setOption(mapO);
        // $.ajaxSettings.async = false;
        //左下角的图和中间的地图
        $.getJSON('/assets/item/langfang.json', function(data){
            echarts.registerMap( 'hebei', data);
            var mapData = [];
            for( var i=0;i<data.features.length;i++ ){
                mapData.push({
                    name:data.features[i].properties.name,
                    value:0

                })
            }
            var date = new Date();
            //AJAX获取城市区域的工程数量统计
            $.ajax({
                url:Feng.ctxPath + '/item/getList',
                method:"post",
                success:function (data) {
                    $("#itemSum").html(data.data.length)
                    //本年新增
                    var count = 0
                    $.each(data.data,function (index,item) {
                        if (item.itemCode.slice(0, 4) == date.getFullYear()) {
                            count++
                        }
                        // 百度地图API功能
                        var point = new BMap.Point(item.xaxis,item.yaxis);
                        var gc = new BMap.Geocoder();
                        gc.getLocation(point, function(rs){
                            var addComp = rs.addressComponents;
                            $.each(mapData,function (index1,item1) {
                                if (addComp.district === item1.name) {
                                    item1.value++
                                    myechart.setOption(option, true);
                                }
                            })
                            mapO.series.data = mapData
                            map.setOption(mapO,true);
                        });
                    })
                    $("#yearAdd").html(count)
                    var option = {
                        // 控制提示
                        tooltip: {
                            // 非轴图形，使用item的意思是放到数据对应图形上触发提示
                            trigger: 'item',
                            // 格式化提示内容：
                            // a 代表图表名称 b 代表数据名称 c 代表数据  d代表  当前数据/总数据的比例
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        // 控制图表
                        series: [
                            {
                                // 图表名称
                                name: '地区',
                                // 图表类型
                                type: 'pie',
                                // 南丁格尔玫瑰图 有两个圆  内圆半径10%  外圆半径70%
                                // 百分比基于  图表DOM容器的半径
                                radius: ['10%', '70%'],
                                // 图表中心位置 left 50%  top 50% 距离图表DOM容器
                                center: ['50%', '50%'],
                                // 半径模式，另外一种是 area 面积模式
                                roseType: 'radius',
                                // 数据集 value 数据的值 name 数据的名称
                                data: mapData,
                                //文字调整
                                label: {
                                    fontSize: 10
                                },
                                //引导线
                                labelLine: {
                                    length: 8,
                                    length2: 10
                                }
                            }
                        ],
                        color: ['#006cff', '#60cda0', '#ed8884', '#ff9f7f', '#0096ff', '#9fe6b8', '#32c5e9', '#1d9dff']
                    };
                    myechart.setOption(option, true);
                }
            })
        });

        /*青 岛 研 锦 网 络 科 技 有 限 公 司   版权所有*/
//自调用函数
        (function () {
            // 1、页面一加载就要知道页面宽度计算
            var setFont = function () {
                // 因为要定义变量可能和别的变量相互冲突，污染，所有用自调用函数
                var html = document.documentElement;// 获取html
                // 获取宽度
                var width = html.clientWidth;

                // 判断
                if (width < 1024) width = 1024
                if (width > 1920) width = 1920
                // 设置html的基准值
                var fontSize = width / 80 + 'px';
                // 设置给html
                html.style.fontSize = fontSize;
            }
            setFont();
            // 2、页面改变的时候也需要设置
            // 尺寸改变事件
            window.onresize = function () {
                setFont();
            }
        })();

        (function () {
            //事件委托
            $('.monitor').on('click', ' a', function () {
                //点击当前的a 加类名 active  他的兄弟删除类名
                $(this).addClass('active').siblings().removeClass('active');
                //获取一一对应的下标
                var index = $(this).index();
                //选取content 然后狗日对应下标的 显示   当前的兄弟.content隐藏
                $('.content').eq(index).show().siblings('.content').hide();
            });
            //滚动
            //原理：把marquee下面的子盒子都复制一遍 加入到marquee中
            //      然后动画向上滚动，滚动到一半重新开始滚动
            //因为选取的是两个marquee  所以要遍历
            $('.monitor .marquee').each(function (index, dom) {
                //将每个 的所有子级都复制一遍
                var rows = $(dom).children().clone();
                //再将新的到的加入原来的
                $(dom).append(rows);
            });

        })();
        var myechart = echarts.init($('.pie')[0]);
        (function () {
            option = {
                // 控制提示
                tooltip: {
                    // 非轴图形，使用item的意思是放到数据对应图形上触发提示
                    trigger: 'item',
                    // 格式化提示内容：
                    // a 代表图表名称 b 代表数据名称 c 代表数据  d代表  当前数据/总数据的比例
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                // 控制图表
                series: [
                    {
                        // 图表名称
                        name: '地区',
                        // 图表类型
                        type: 'pie',
                        // 南丁格尔玫瑰图 有两个圆  内圆半径10%  外圆半径70%
                        // 百分比基于  图表DOM容器的半径
                        radius: ['10%', '70%'],
                        // 图表中心位置 left 50%  top 50% 距离图表DOM容器
                        center: ['50%', '50%'],
                        // 半径模式，另外一种是 area 面积模式
                        roseType: 'radius',
                        // 数据集 value 数据的值 name 数据的名称
                        data: [
                            {value: 20, name: '云南'},
                            {value: 5, name: '北京'},
                            {value: 15, name: '山东'},
                            {value: 25, name: '河北'},
                            {value: 20, name: '江苏'},
                            {value: 35, name: '浙江'},
                            {value: 30, name: '四川'},
                            {value: 40, name: '湖北'}
                        ],
                        //文字调整
                        label: {
                            fontSize: 10
                        },
                        //引导线
                        labelLine: {
                            length: 8,
                            length2: 10
                        }
                    }
                ],
                color: ['#006cff', '#60cda0', '#ed8884', '#ff9f7f', '#0096ff', '#9fe6b8', '#32c5e9', '#1d9dff']
            };
            myechart.setOption(option);
        })();

        //总进尺
        (function () {
            var option = {
                //鼠标提示工具
                tooltip: {
                    trigger: 'axis'
                },
                xAxis: {
                    // 类目类型
                    type: 'category',
                    data: ['1月', '2月', '3月', '4月'],
                    axisTick: {
                        show: false//去除刻度线
                    },
                    axisLabel: {
                        color: '#fff'//文本颜色
                    },
                    axisLine: {
                        show: false//去除轴线
                    },
                    boundaryGap: false//去除轴内间距
                },
                yAxis: {
                    // 数据作为刻度文字
                    type: 'value',
                    axisTick: {
                        show: false//去除刻度线
                    },
                    axisLabel: {
                        color: '#fff'//文本颜色
                    },
                    axisLine: {
                        show: false//去除轴线
                    },
                    boundaryGap: false//去除轴内间距
                },
                //图例组件
                legend: {
                    textStyle: {
                        color: '#fff' // 图例文字颜色

                    },
                    right: '10%'//距离右边10%
                },
                // 设置网格样式
                grid: {
                    show: true,// 显示边框
                    top: '20%',
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    borderColor: '#012f4a',// 边框颜色
                    containLabel: true // 包含刻度文字在内
                },
                series: [{
                    // 数据
                    data: [],
                    // 图表类型
                    type: 'line',
                    // 圆滑连接
                    smooth: true,
                    itemStyle: {
                        color: '#00f2f1'  // 线颜色
                    }
                }]
            };
            var myechart = echarts.init($('.line')[0]);
            myechart.setOption(option);

            //点击效果
            var datas = {
                year: [],
                quarter: []
            }
            var q=[],y=[],qq=[],yy=[];
            $.ajax({
                url:Feng.ctxPath + '/console/selectBySeasontime',
                method:"post",
                async:false,
                success:function (data) {
                    $.each(data.data, function (index, item) {
                        q.push(item.footage)
                        qq.push(item.seasontime)
                    })
                    datas.quarter.push(q)
                }
            })
            $.ajax({
                url:Feng.ctxPath + '/console/selectByYeartime',
                method:"post",
                async:false,
                success:function (data) {
                    $.each(data.data, function (index, item) {
                        y.push(item.footage)
                        yy.push(item.yeartime)
                    })
                    datas.year.push(y)
                }
            })

            $('.sales ').on('click', '.caption a', function () {
                $(this).addClass('active').siblings('a').removeClass('active');
                //option series   data
                //获取自定义属性值
                var key = $(this).attr('data-type');
                //取出对应的值
                key = datas[key];
                //将值设置到 图表中
                option.series[0].data = key[0];
                //再次调用才能在页面显示
                myechart.setOption(option);
            });
            //定时器
            var index = 0;
            var timer = setInterval(function () {
                index++;
                if (index > 1) {
                    index = 0;
                }
                if (index == 0){
                    option = {
                        //鼠标提示工具
                        tooltip: {
                            trigger: 'axis'
                        },
                        xAxis: {
                            // 类目类型
                            type: 'category',
                            data: yy,
                            axisTick: {
                                show: false//去除刻度线
                            },
                            axisLabel: {
                                color: '#fff'//文本颜色
                            },
                            axisLine: {
                                show: false//去除轴线
                            },
                            boundaryGap: false//去除轴内间距
                        },
                        yAxis: {
                            // 数据作为刻度文字
                            type: 'value',
                            axisTick: {
                                show: false//去除刻度线
                            },
                            axisLabel: {
                                color: '#fff'//文本颜色
                            },
                            axisLine: {
                                show: false//去除轴线
                            },
                            boundaryGap: false//去除轴内间距
                        },
                        //图例组件
                        legend: {
                            textStyle: {
                                color: '#fff' // 图例文字颜色

                            },
                            right: '10%'//距离右边10%
                        },
                        // 设置网格样式
                        grid: {
                            show: true,// 显示边框
                            top: '20%',
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            borderColor: '#012f4a',// 边框颜色
                            containLabel: true // 包含刻度文字在内
                        },
                        series: [{
                            // 数据
                            data: [],
                            // 图表类型
                            type: 'line',
                            // 圆滑连接
                            smooth: true,
                            itemStyle: {
                                color: '#00f2f1'  // 线颜色
                            }
                        }]
                    };
                    myechart.setOption(option);

                } else if (index == 1) {
                    option = {
                        //鼠标提示工具
                        tooltip: {
                            trigger: 'axis'
                        },
                        xAxis: {
                            // 类目类型
                            type: 'category',
                            data: qq,
                            axisTick: {
                                show: false//去除刻度线
                            },
                            axisLabel: {
                                color: '#fff'//文本颜色
                            },
                            axisLine: {
                                show: false//去除轴线
                            },
                            boundaryGap: false//去除轴内间距
                        },
                        yAxis: {
                            // 数据作为刻度文字
                            type: 'value',
                            axisTick: {
                                show: false//去除刻度线
                            },
                            axisLabel: {
                                color: '#fff'//文本颜色
                            },
                            axisLine: {
                                show: false//去除轴线
                            },
                            boundaryGap: false//去除轴内间距
                        },
                        //图例组件
                        legend: {
                            textStyle: {
                                color: '#fff' // 图例文字颜色

                            },
                            right: '10%'//距离右边10%
                        },
                        // 设置网格样式
                        grid: {
                            show: true,// 显示边框
                            top: '20%',
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            borderColor: '#012f4a',// 边框颜色
                            containLabel: true // 包含刻度文字在内
                        },
                        series: [{
                            // 数据
                            data: [],
                            // 图表类型
                            type: 'line',
                            // 圆滑连接
                            smooth: true,
                            itemStyle: {
                                color: '#00f2f1'  // 线颜色
                            }
                        }]
                    };
                    myechart.setOption(option);
                }
                $('.sales .caption a').eq(index).click();
            }, 2000);
        })();
        (function () {
            var option = {
                series: [
                    {
                        type: 'pie',
                        radius: ['130%', '150%'],  // 放大图形
                        center: ['50%', '80%'],    // 往下移动  套住75%文字
                        label: {
                            show: false,
                        },
                        startAngle: 180,
                        hoverOffset: 0,  // 鼠标经过不变大
                        data: [
                            {
                                value: 100,
                                itemStyle: { // 颜色渐变#00c9e0->#005fc1
                                    color: {
                                        type: 'linear',
                                        x: 0,
                                        y: 0,
                                        x2: 0,
                                        y2: 1,
                                        colorStops: [
                                            {offset: 0, color: '#00c9e0'},
                                            {offset: 1, color: '#005fc1'}
                                        ]
                                    }
                                }
                            },
                            {value: 100, itemStyle: {color: '#12274d'}}, // 颜色#12274d

                            {value: 200, itemStyle: {color: 'transparent'}}// 透明隐藏第三块区域
                        ]
                    }
                ]
            };
            myechart.setOption(option);
        })();
    });
}