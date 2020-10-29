layui.use(['form', 'admin', 'ax', 'laydate'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;

    //存放选择的工程类型,工程进度
    var itemTypes, itemPlans;
    // 图例的展开与隐藏
    var flag = 1;
    $('#rightArrow').click(function () {
        if (flag == 1) {
            $("#floatDivBoxs").animate({right: '-200px'}, 300);
            $(this).animate({right: '10px'}, 300);
            $(this).css('background-position', '-39px 0');
            flag = 0;
        } else {
            $("#floatDivBoxs").animate({right: '0'}, 300);
            $(this).animate({right: '200px'}, 300);
            $(this).css('background-position', '0px 0');
            flag = 1;
        }
    });

    // 项目数据列表
    var tempData = [];
    // 百度地图API功能
    var map = new BMap.Map("allmap", {enableMapClick: false});    // 创建Map实例
    map.centerAndZoom(new BMap.Point(116.719041, 39.518128), 14);  // 初始化地图,设置中心点坐标和地图级别
    var point = new BMap.Point(116.719041, 39.518088);
    var myIcon = new BMap.Icon("/assets/picture/local.png", new BMap.Size(36, 70));
    var marker = new BMap.Marker(point, {icon: myIcon});  // 创建标注
    map.addOverlay(marker);
    var label = new BMap.Label("勘察院测绘处", {
        offset: new BMap.Size(15, -25)
    });
    //消除底图兴趣点标记
    map.setMapStyle({
        styleJson: [
            {
                "elementType": "labels.icon",
                "stylers": {
                    "weight": "8",
                    "lightness": -70,
                    "saturation": 1,
                    "visibility": "off"
                }
            }
        ]
    });
    label.setStyle({
        color: '#ffffff',
        background: '#a15cc8',
        border: '1px solid "#ff8355"',
        borderRadius: "5px",
        height: "22px",
        lineHeight: "22px",
        padding: "0 10px"
    });
    marker.setLabel(label);
//添加地图类型控件(地图/混合)
    map.addControl(new BMap.MapTypeControl({
        mapTypes: [
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]
    }));
    map.setCurrentCity("廊坊市");          // 设置地图显示的城市 此项是必须设置的
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩

//获取项目
    $.ajax({
        url: Feng.ctxPath + "/item/getItemOnTheMap",
        type: 'post',
        async: false,
        success: function (data) {
            tempData = data.data
        }
    });

    //搜索按钮点击事件
    $('#search').click(function () {
        $.ajax({
            url: Feng.ctxPath + "/item/getItemOnTheMap",
            data: {
                itemName: $("#nickName").val(),
                itemTypes: itemTypes,
                itemPlans: itemPlans
            },
            dataType: 'json',
            type: 'post',
            success: function (data) {
                tempData = data.data;
                $("#searchList").empty();
                var iContent1, iContent2, iContent3;
                if (data.data.length != 0) {
                    data.data.forEach(function (item, index) {
                        iContent1 =
                            "<div class='searchItem' onclick='dianji(" + JSON.stringify(item) + ")'>" +
                            "<div style='float: left'>";
                        if (item.type == 1303502789608460289) {
                            iContent2 =
                                "<img src='/assets/picture/kancha.png'>";
                        } else if (item.type == 1303502842829983746) {
                            iContent2 =
                                "<img src='/assets/picture/diji.png'>";
                        } else if (item.type == 1303502895028097025) {
                            iContent2 =
                                "<img src='/assets/picture/celiang.png'>";
                        } else if (item.type == 1303502953022738433) {
                            iContent2 =
                                "<img src='/assets/picture/jikeng.png'>";
                        } else {
                            iContent2 =
                                "<img src='/assets/picture/qita.png'>";
                        }
                        iContent3 =
                            "</div>" +
                            "<div style='float: left;padding:0 5px 0 10px;width: 230px'>" +
                            "<strong style='margin-left: 5px;line-height: 25px'>项目名称：" + item.itemName + "</strong>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>项目编号：" + item.itemCode + "</p>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>项目类型：" + item.typeName + "</p>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>地址：" + item.location + "</p>" +
                            "</div>" +
                            "</div>";
                        $("#searchList").append(iContent1 + iContent2 + iContent3);
                    })
                } else {
                    <!--搜索结果为空-->
                    iContent =
                        "<div style='margin-top: 20px;text-align: center'>" +
                        "<p> 没有找到匹配的项目！</p>" +
                        "</div>";
                    $("#searchList").append(iContent);
                }
                $("#searchList").css('display', 'block');
            }
        });
    })

    var xmPoint = new Array(); //存放标注点经纬信息的数组
    var xmMarker = new Array(); //存放标注点对象的数组
    var info = new Array(); //存放提示信息窗口对象的数组
    tempData.forEach(function (item, index) {
        xmPoint[index] = new window.BMap.Point(item.xaxis, item.yaxis); //循环生成新的地图点
        var myIcon1 = new BMap.Icon("/assets/picture/celiang.png", new BMap.Size(24, 24));
        var myIcon2 = new BMap.Icon("/assets/picture/diji.png", new BMap.Size(24, 24));
        var myIcon3 = new BMap.Icon("/assets/picture/jikeng.png", new BMap.Size(24, 24));
        var myIcon4 = new BMap.Icon("/assets/picture/kancha.png", new BMap.Size(24, 24));
        var myIcon5 = new BMap.Icon("/assets/picture/qita.png", new BMap.Size(24, 24));
        if (item.type == 1303502789608460289) {
            var xmLabel = new window.BMap.Label(item.itemName, {offset: new window.BMap.Size(20, -10)});
            xmLabel.setStyle({
                color: '#ffffff',
                background: '#ff151d',
                borderRadius: "5px",
                border: 'none',
                textAlign: "center",
                height: "20px",
                lineHeight: "20px",
                padding: "0 10px",

            });
            xmMarker[index] = new window.BMap.Marker(xmPoint[index], {icon: myIcon4}); //按照地图点坐标生成标记
        } else if (item.type == 1303502842829983746) {
            var xmLabel = new window.BMap.Label(item.itemName, {offset: new window.BMap.Size(20, -10)});
            xmLabel.setStyle({
                color: '#ffffff',
                background: '#FF7100',
                borderRadius: "5px",
                border: 'none',
                textAlign: "center",
                height: "20px",
                lineHeight: "20px",
                padding: "0 10px"
            });
            xmMarker[index] = new window.BMap.Marker(xmPoint[index], {icon: myIcon2}); //按照地图点坐标生成标记
        } else if (item.type == 1303502895028097025) {
            var xmLabel = new window.BMap.Label(item.itemName, {offset: new window.BMap.Size(20, -10)});
            xmLabel.setStyle({
                color: '#ffffff',
                background: '#2EB5B1',
                borderRadius: "5px",
                border: 'none',
                textAlign: "center",
                height: "20px",
                lineHeight: "20px",
                padding: "0 10px"
            });
            xmMarker[index] = new window.BMap.Marker(xmPoint[index], {icon: myIcon1}); //按照地图点坐标生成标记
        } else if (item.type == 1303502953022738433) {
            var xmLabel = new window.BMap.Label(item.itemName, {offset: new window.BMap.Size(20, -10)});
            xmLabel.setStyle({
                color: '#ffffff',
                background: '#0cD500',
                borderRadius: "5px",
                border: 'none',
                textAlign: "center",
                height: "20px",
                lineHeight: "20px",
                padding: "0 10px"
            });
            xmMarker[index] = new window.BMap.Marker(xmPoint[index], {icon: myIcon3}); //按照地图点坐标生成标记
        } else {
            var xmLabel = new window.BMap.Label(item.itemName, {offset: new window.BMap.Size(20, -10)});
            xmLabel.setStyle({
                color: '#ffffff',
                background: '#807B76',
                borderRadius: "5px",
                border: 'none',
                textAlign: "center",
                height: "20px",
                lineHeight: "20px",
                padding: "0 10px"
            });
            xmMarker[index] = new window.BMap.Marker(xmPoint[index], {icon: myIcon5}); //按照地图点坐标生成标记
        }

        map.addOverlay(xmMarker[index]);

        xmMarker[index].setLabel(xmLabel);
        var sContent =
            "<div style='width: 200px;height: 150px;position:relative'>" +
            "<h4 style='margin:0 0 5px 0;padding:0.2em 0;color: #1E9FFF;font-weight: bold'>" + item.itemName + "</h4>" +
            "<p class='map-card-p' style=''>项目编号：" + item.itemCode + "</p>" +
            "<p class='map-card-p'>项目类型：" + item.typeName + "</p>" +
            // "<p class='map-card-p'>项目地址："+item.location+"</p>" +
            // "<p class='map-card-p'>起止时间：<span>"+item.beginDate.slice(0,10)+" 至 "+item.endDate.slice(0,10)+"</span></p>" +
            // "<p class='map-card-p'>项目负责人："+item.head+"</p>" +
            // "<div style='width: 300px;height: 1px;border-top:1px dashed #333333;margin: 10px 0px'></div>"+
            // "<p class='map-card-p'>钻孔数量：<span style=''>25 个</span></p>" +
            // "<p class='map-card-p'>档案位置：<span style='color: red;'>档案室内某柜子</span></p>" +
            "<div style='position:absolute; bottom: 0;display: flex;width: 100%'>" +
            "<a style='color: #1668ff;font-weight: bold;margin: 0 auto;'  href='/drilling/drillingMap?itemId=" + item.id + "&xaxis=" + item.xaxis + "&yaxis=" + item.yaxis + "' >工程钻孔</a>" +
            // "<div style='width: 34% '><a style='color: #1668ff;font-weight: bold' target='_blank' href='/item/document?itemId="+item.id+"' >进入工程文档</a></div>" +
            // "<div style='width: 33% '><a style='color: #1668ff;font-weight: bold' id='lookTJ' >查看项目统计</a></div>" +
            "</div>" +
            "</div>";
        info[index] = new window.BMap.InfoWindow(sContent); // 创建信息窗口对象

        addInfo(info[index], xmMarker[index]);
    })
    // for (var i = 0; i < tempData.length; i ++) {
    //     addInfo(info[index],xmMarker[index]);
    // }
    function addInfo(info, xmMarker) {
        xmMarker.addEventListener("click", function () {
            this.openInfoWindow(info);
        });
    }

    //点击工程搜索结果
    window.dianji = function (item) {
        //根据点击的项目Id获取经纬度
        //在这里写调接口
        //根据经纬度定位中心点
        var point = new BMap.Point(item.xaxis, item.yaxis);
        map.centerAndZoom(point, 19);
        var opts =
            "<div style='width: 200px;height: 150px;position:relative'>" +
            "<h4 style='margin:0 0 5px 0;padding:0.2em 0;color: #1E9FFF;font-weight: bold'>" + item.itemName + "</h4>" +
            "<p class='map-card-p' style=''>项目编号：" + item.itemCode + "</p>" +
            "<p class='map-card-p'>项目类型：" + item.typeName + "</p>" +
            "<div style='position:absolute; bottom: 0;display: flex;width: 100%'>" +
            "<a style='color: #1668ff;font-weight: bold;margin: 0 auto;'  href='/drilling/drillingMap?itemId=" + item.id + "&xaxis=" + item.xaxis + "&yaxis=" + item.yaxis + "' >工程钻孔</a>" +
            "</div>" +
            "</div>";
        var infoWindow = new BMap.InfoWindow(opts);
        map.openInfoWindow(infoWindow, point);
    };
    //点击工程搜索结果
    window.sousuo = function () {
        // $("#selectList").empty();
        // var iContent;
        // iContent =
        //     "<form class='layui-form' action=''>" +
        //     "<div class='layui-form-item'>" +
        //     "<strong style='line-height: 20px;font-size: 0.8rem '>工程类型</strong><br>" +
        //     "<input type='checkbox' class='typeItem' lay-filter='switchType' lay-skin='primary' value='1303502895028097025'  title='测量工程'>" +
        //     "<input type='checkbox' class='typeItem' lay-filter='switchType' lay-skin='primary' value='1303502789608460289'  title='勘察工程'>" +
        //     "<input type='checkbox' class='typeItem' lay-filter='switchType' lay-skin='primary' value='1303502953022738433'  title='基坑工程'>" +
        //     "<input type='checkbox' class='typeItem' lay-filter='switchType' lay-skin='primary' value='1303502842829983746'  title='地基处理'>" +
        //     "</div>" +
        //     "<div class='layui-form-item'>" +
        //     "<strong style='line-height: 20px;font-size: 0.8rem '>工程进度</strong><br>" +
        //     "<input type='checkbox' class='planItem' lay-filter='switchPlan' lay-skin='primary' value='1303594236575612929'  title='签订合同'>" +
        //     "<input type='checkbox' class='planItem' lay-filter='switchPlan' lay-skin='primary' value='1303594301717348354'  title='进场实施'>" +
        //     "<input type='checkbox' class='planItem' lay-filter='switchPlan' lay-skin='primary' value='1303594357925216257'  title='报告完成'>" +
        //     "<input type='checkbox' class='planItem' lay-filter='switchPlan' lay-skin='primary' value='1303594444642451458'  title='验槽'>" +
        //     "<input type='checkbox' class='planItem' lay-filter='switchPlan' lay-skin='primary' value='1303594532169187329'  title='竣工'>" +
        //     "</div>" +
        //     "</form>"
        // $("#selectList").append(iContent);
        $("#selectList").css('display', 'block');
        // form.render()
    };

    //监听多选框点击事件  主要是通过 lay-filter="switchTest"  来监听
    form.on('checkbox(switchType)', function (data) {
        itemTypes = ''
        for (var i = 0; i < $('.typeItem').length; i++) {
            if ($('.typeItem')[i].checked) {
                itemTypes += $('.typeItem')[i].value+ ','
            }
        }
        $.ajax({
            url: Feng.ctxPath + "/item/getItemOnTheMap",
            data: {
                itemName: $("#nickName").val(),
                itemTypes: itemTypes,
                itemPlans: itemPlans
            },
            dataType: 'json',
            type: 'post',
            success: function (data) {
                tempData = data.data;
                $("#searchList").empty();
                var iContent1, iContent2, iContent3;
                if (data.data.length != 0) {
                    data.data.forEach(function (item, index) {
                        iContent1 =
                            "<div class='searchItem' onclick='dianji(" + JSON.stringify(item) + ")'>" +
                            "<div style='float: left'>";
                        if (item.type == 1303502789608460289) {
                            iContent2 =
                                "<img src='/assets/picture/kancha.png'>";
                        } else if (item.type == 1303502842829983746) {
                            iContent2 =
                                "<img src='/assets/picture/diji.png'>";
                        } else if (item.type == 1303502895028097025) {
                            iContent2 =
                                "<img src='/assets/picture/celiang.png'>";
                        } else if (item.type == 1303502953022738433) {
                            iContent2 =
                                "<img src='/assets/picture/jikeng.png'>";
                        } else {
                            iContent2 =
                                "<img src='/assets/picture/qita.png'>";
                        }
                        iContent3 =
                            "</div>" +
                            "<div style='float: left;padding:0 5px 0 10px;width: 230px'>" +
                            "<strong style='margin-left: 5px;line-height: 25px'>项目名称：" + item.itemName + "</strong>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>项目编号：" + item.itemCode + "</p>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>项目类型：" + item.typeName + "</p>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>地址：" + item.location + "</p>" +
                            "</div>" +
                            "</div>";
                        $("#searchList").append(iContent1 + iContent2 + iContent3);
                    })
                } else {
                    <!--搜索结果为空-->
                    iContent =
                        "<div style='margin-top: 20px;text-align: center'>" +
                        "<p> 没有找到匹配的项目！</p>" +
                        "</div>";
                    $("#searchList").append(iContent);
                }
                $("#searchList").css('display', 'block');
            }
        });
    });
    //监听多选框点击事件  主要是通过 lay-filter="switchTest"  来监听
    form.on('checkbox(switchPlan)', function (data) {
        itemPlans = ''
        for (var i = 0; i < $('.planItem').length; i++) {
            if ($('.planItem')[i].checked) {
                itemPlans += $('.planItem')[i].value+ ','
            }
        }
        $.ajax({
            url: Feng.ctxPath + "/item/getItemOnTheMap",
            data: {
                itemName: $("#nickName").val(),
                itemTypes: itemTypes,
                itemPlans: itemPlans
            },
            dataType: 'json',
            type: 'post',
            success: function (data) {
                tempData = data.data;
                $("#searchList").empty();
                var iContent1, iContent2, iContent3;
                if (data.data.length != 0) {
                    data.data.forEach(function (item, index) {
                        iContent1 =
                            "<div class='searchItem' onclick='dianji(" + JSON.stringify(item) + ")'>" +
                            "<div style='float: left'>";
                        if (item.type == 1303502789608460289) {
                            iContent2 =
                                "<img src='/assets/picture/kancha.png'>";
                        } else if (item.type == 1303502842829983746) {
                            iContent2 =
                                "<img src='/assets/picture/diji.png'>";
                        } else if (item.type == 1303502895028097025) {
                            iContent2 =
                                "<img src='/assets/picture/celiang.png'>";
                        } else if (item.type == 1303502953022738433) {
                            iContent2 =
                                "<img src='/assets/picture/jikeng.png'>";
                        } else {
                            iContent2 =
                                "<img src='/assets/picture/qita.png'>";
                        }
                        iContent3 =
                            "</div>" +
                            "<div style='float: left;padding:0 5px 0 10px;width: 230px'>" +
                            "<strong style='margin-left: 5px;line-height: 25px'>项目名称：" + item.itemName + "</strong>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>项目编号：" + item.itemCode + "</p>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>项目类型：" + item.typeName + "</p>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>地址：" + item.location + "</p>" +
                            "</div>" +
                            "</div>";
                        $("#searchList").append(iContent1 + iContent2 + iContent3);
                    })
                } else {
                    <!--搜索结果为空-->
                    iContent =
                        "<div style='margin-top: 20px;text-align: center'>" +
                        "<p> 没有找到匹配的项目！</p>" +
                        "</div>";
                    $("#searchList").append(iContent);
                }
                $("#searchList").css('display', 'block');
            }
        });
    });
});