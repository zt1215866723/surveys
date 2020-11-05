layui.use(['form', 'admin', 'ax', 'laydate'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;

    //项目数据列表
    var tempData = [];

// 百度地图API功能
    var map = new BMap.Map("allmap", {enableMapClick: false});    // 创建Map实例
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
    map.centerAndZoom(new BMap.Point(Feng.getUrlParam("xaxis"), Feng.getUrlParam("yaxis")), 19);  // 初始化地图,设置中心点坐标和地图级别
    //添加地图类型控件(地图/混合)
    map.addControl(new BMap.MapTypeControl({
        mapTypes: [
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]
    }));
    map.setCurrentCity("廊坊市");          // 设置地图显示的城市 此项是必须设置的
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩

//获取钻孔信息
    $.ajax({
        url: Feng.ctxPath + "/drilling/selectDrillingByItemId",
        data: {
            itemId: Feng.getUrlParam("itemId")
        },
        dataType: 'json',
        type: 'post',
        async: false,
        success: function (data) {
            $.each(data.data, (i, item) => {
                var pos = new Array();
                var point = new BMap.Point(item.xaxis, item.yaxis);
                pos.push(point);
                var convertor = new BMap.Convertor();
                convertor.translate(pos, 1, 5, function (data) {
                    var xmPoint = new Array(); //存放标注点经纬信息的数组
                    var xmMarker = new Array(); //存放标注点对象的数组
                    var info = new Array(); //存放提示信息窗口对象的数组
                    if (data.status === 0) {
                        item.xaxis = data.points[0].lng
                        item.yaxis = data.points[0].lat
                        xmPoint[i] = new window.BMap.Point(item.xaxis, item.yaxis); //循环生成新的地图点
                        var myIcon = new BMap.Icon(item.typeUrl, new BMap.Size(26, 26));
                        xmMarker[i] = new window.BMap.Marker(xmPoint[i], {icon: myIcon}); //按照地图点坐标生成标记
                        map.addOverlay(xmMarker[i]);
                        var sContent1,sContent2;
                        sContent1 =
                            "<div style='width: 300px;height: 200px;position:relative'>" +
                            "<h4 style='margin:0 0 5px 0;padding:0.2em 0;color: #1E9FFF;font-weight: bold'>" + item.itemName + "</h4>" +
                            "<p class='map-card-p'>钻孔编号：" + item.holeCode + "</p>" +
                            "<p class='map-card-p'>钻孔类型：" + item.type + "</p>" +
                            "<p class='map-card-p'>孔口高程(m)：" + item.zkbg + "</p>" +
                            "<p class='map-card-p'>勘探深度(m)：" + item.depth + "</p>" +
                            "<div style='position:absolute; bottom: 0;display: flex;width: 100%'>";
                            // "<div style='width: 33% '><a style='color: #1668ff;font-weight: bold' href='/item/document?itemId=" + item.id + "' >钻孔柱状图</a></div>" +
                            if (item.type == '静力触探试验孔') {
                                sContent2 =
                                    "<div style='bottom: 0;display: flex;width: 50% '><a style='color: #1668ff;font-weight: bold;margin: 0 auto;' href='/staticTest/staticTestChart?itemIds=" + item.itemId + "&holeCode=" + item.holeCode + "&xaxis=" + item.xaxis + "&yaxis=" + item.yaxis + "' >静探曲线图</a></div>"+
                                    "<div style='bottom: 0;display: flex;width: 50% '><a style='color: #1668ff;font-weight: bold;margin: 0 auto;' href='/standard?itemIds=" + item.itemId + "&holeCode=" + item.holeCode + "&xaxis=" + item.xaxis + "&yaxis=" + item.yaxis + "' >钻孔地层信息</a></div>" +
                                    "</div>" +
                                    "</div>";
                            }else {
                                sContent2 =
                                    "<div style='position:absolute; bottom: 0;display: flex;width: 100%'><a style='color: #1668ff;font-weight: bold;margin: 0 auto;' href='/standard?itemIds=" + item.itemId + "&holeCode=" + item.holeCode + "&xaxis=" + item.xaxis + "&yaxis=" + item.yaxis + "' >钻孔地层信息</a></div>" +
                                    "</div>" +
                                    "</div>";
                            }
                        info[i] = new window.BMap.InfoWindow(sContent1+sContent2); // 创建信息窗口对象

                        addInfo(info[i], xmMarker[i]);
                    }
                });

            })
        }
    });

    // 返回按钮点击事件
    $('#btnBack').click(function () {
        location.href = "/item/map"
    });

// 搜索按钮点击事件
    $('#btnSearch').click(function () {
        $.ajax({
            url: Feng.ctxPath + "/drilling/selectDrillingByItemId",
            data: {
                itemId: Feng.getUrlParam("itemId"),
                holeCode: $("#nickName").val()
            },
            dataType: 'json',
            type: 'post',
            success: function (data) {
                tempData = data.data
                $("#searchList").empty();
                var iContent;
                if (data.data.length != 0) {
                    $.each(data.data, (i, item) => {
                        var pos = new Array();
                        var point = new BMap.Point(item.xaxis, item.yaxis);
                        pos.push(point);
                        var convertor = new BMap.Convertor();
                        convertor.translate(pos, 1, 5, function (data) {
                            if (data.status === 0) {
                                item.xaxis = data.points[0].lng
                                item.yaxis = data.points[0].lat
                                iContent =
                                    "<div class='searchItem' onclick='dianji(" + JSON.stringify(item) + ")'>" +
                                    "<div style='float: left'>" +
                                    "<img src="+item.typeUrl +">" +
                                    "</div>" +
                                    "<div style='float: left;padding:0 5px 0 10px;width: 230px'>" +
                                    "<strong style='margin-left: 5px;line-height: 25px'>钻孔编号：" + item.holeCode + "</strong>" +
                                    " <p style='line-height: 20px;font-size: 0.8rem '>钻孔类型：" + item.type + "</p>" +
                                    "</div>" +
                                    "</div>";
                                $("#searchList").append(iContent);
                            }
                        })
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
            "<div style='width: 300px;height: 200px;position:relative'>" +
            "<h4 style='margin:0 0 5px 0;padding:0.2em 0;color: #1E9FFF;font-weight: bold'>" + item.itemName + "</h4>" +
            "<p class='map-card-p'>钻孔编号：" + item.holeCode + "</p>" +
            "<p class='map-card-p'>钻孔类型：" + item.type + "</p>" +
            "<p class='map-card-p'>孔口高程(m)：" + item.zkbg + "</p>" +
            "<p class='map-card-p'>勘探深度(m)：" + item.depth + "</p>" +
            "<div style='position:absolute; bottom: 0;display: flex;width: 100%'>" +
            "<div style='width: 33% '><a style='color: #1668ff;font-weight: bold' target='_blank' href='/item/document?itemId=" + item.id + "' >钻孔柱状图</a></div>" +
            "<div style='width: 34% '><a style='color: #1668ff;font-weight: bold' target='_blank' href='/item/document?itemId=" + item.id + "' >静探曲线图</a></div>" +
            "<div style='width: 33% '><a style='color: #1668ff;font-weight: bold' target='_blank' href='/standard?itemIds=" + item.itemId + "&holeCode=" + item.holeCode + "' >钻孔地层信息</a></div>" +
            "</div>" +
            "</div>";
        var infoWindow = new BMap.InfoWindow(opts);
        map.openInfoWindow(infoWindow, point);
    };
});