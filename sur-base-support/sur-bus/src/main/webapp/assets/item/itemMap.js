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
            $(this).css('background-position', '-25px 0');
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
                var iContent;
                if (data.data.length != 0) {
                    data.data.forEach(function (item, index) {
                        iContent =
                            "<div class='searchItem' onclick='dianji(" + JSON.stringify(item) + ")'>" +
                            "<div style='float: left'>" +
                            "<img src=/image/" + item.typeUrl + ">" +
                            "</div>" +
                            "<div style='float: left;padding:0 5px 0 10px;width: 230px'>" +
                            "<strong style='margin-left: 5px;line-height: 25px'>项目名称：" + item.itemName + "</strong>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>项目编号：" + item.itemCode + "</p>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>项目类型：" + item.typeName + "</p>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>地址：" + item.location + "</p>" +
                            "</div>" +
                            "</div>";
                        $("#searchList").append(iContent);
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
        var myIcon = new BMap.Icon("/image/"+item.typeUrl, new BMap.Size(24, 24));
            var xmLabel = new window.BMap.Label(item.itemName, {offset: new window.BMap.Size(20, -10)});
            xmLabel.setStyle({
                color: '#000000',
                background: '#ffffff',
                borderRadius: "5px",
                border: 'none',
                textAlign: "center",
                height: "20px",
                lineHeight: "20px",
                padding: "0 10px"
            });
            xmMarker[index] = new window.BMap.Marker(xmPoint[index], {icon: myIcon}); //按照地图点坐标生成标记

        map.addOverlay(xmMarker[index]);

        xmMarker[index].setLabel(xmLabel);

        addInfo(item, xmMarker[index]);
    })

    function addInfo(item, xmMarker) {
        xmMarker.addEventListener("click", function () {
            var sContent4 = "";
            //查询该工程的关注项
            $.ajax({
                url: Feng.ctxPath + "/focus/selectFocusByitemId",
                data: {
                    itemId: item.id
                },
                dataType: 'json',
                type: 'post',
                async:false,
                success: function (data) {
                    if (data.data != null && data.data !=""){
                        sContent4 += "<h4 style='margin:0 0 5px 0;padding:0.2em 0;color: #1E9FFF;font-weight: bold'>关注项：</h4>";
                    }
                    $.each(data.data, function (index, item) {
                        if(item.type == 0){
                            sContent4 +="<p class='map-card-p'>"+ item.focusName +" ：" + item.nouValue + "(" + item.unit + ")"+"</p>";
                        }else {
                            sContent4 +="<p class='map-card-p'>"+ item.focusName +" ：" + item.resultName +"</p>";
                        }
                    })
                    sContent4 += "<div style='position:absolute; bottom: 0;display: flex;width: 100%'>";
                }
            });
            var sContent1 =
                "<div class='mapInfo' style='width: 200px;padding-bottom: 30px;position:relative'>" +
                "<div class='mapInfos'>" +

                "<h4 style='margin:0 0 5px 0;padding:0.2em 0;color: #1E9FFF;font-weight: bold'>" + item.itemName + "</h4>" +
                "<p class='map-card-p' style=''>项目编号：" + item.itemCode + "</p>" +
                "<p class='map-card-p'>项目类型：" + item.typeName + "</p>"+
                "</div>";
            var sContent2 = '';
            if (item.type == 2) {
                //勘察工程才有钻孔信息！！！
                sContent2 =
                    "<a style='color: #1668ff;font-weight: bold;margin: 0 auto;'  href='/drilling/drillingMap?itemId=" + item.id + "&xaxis=" + item.xaxis + "&yaxis=" + item.yaxis + "' >工程钻孔</a>";
            }
            var sContent3 =
                "</div>" +
                "</div>";
            var opts = {
                width : 0,
                height :0
            }
            info = new window.BMap.InfoWindow(sContent1 + sContent4 + sContent2 + sContent3,opts); // 创建信息窗口对象
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

        var opts4 = "";
        //查询该工程的关注项
        $.ajax({
            url: Feng.ctxPath + "/focus/selectFocusByitemId",
            data: {
                itemId: item.id
            },
            dataType: 'json',
            type: 'post',
            async:false,
            success: function (data) {
                if (data.data != null && data.data !=""){
                    opts4 += "<h4 style='margin:0 0 5px 0;padding:0.2em 0;color: #1E9FFF;font-weight: bold'>关注项：</h4>";
                }
                $.each(data.data, function (index, item) {
                    if(item.type == 0){
                        opts4 +="<p class='map-card-p'>"+ item.focusName +" ：" + item.nouValue + "(" + item.unit + ")"+"</p>";
                    }else {
                        opts4 +="<p class='map-card-p'>"+ item.focusName +" ：" + item.resultName +"</p>";
                    }
                })
                opts4 += "<div style='position:absolute; bottom: 0;display: flex;width: 100%'>";
            }
        });
        var opts1 =
            "<div class='mapInfo' style='width: 200px;padding-bottom: 30px;position:relative'>" +
            "<div class='mapInfos'>" +
            "<h4 style='margin:0 0 5px 0;padding:0.2em 0;color: #1E9FFF;font-weight: bold'>" + item.itemName + "</h4>" +
            "<p class='map-card-p' style=''>项目编号：" + item.itemCode + "</p>" +
            "<p class='map-card-p'>项目类型：" + item.typeName + "</p>" +
            "</div>";
        var opts2 = '';
        if (item.type == 2) {
            //勘察工程才有钻孔信息！！！
            opts2 =
                "<a style='color: #1668ff;font-weight: bold;margin: 0 auto;'  href='/drilling/drillingMap?itemId=" + item.id + "&xaxis=" + item.xaxis + "&yaxis=" + item.yaxis + "' >工程钻孔</a>";
        }
        var opts3 =
            "</div>" +
            "</div>";

        var opts = {
            width : 0,
            height :0
        }
        var infoWindow = new BMap.InfoWindow(opts1 + opts4 + opts2 + opts3,opts);
        map.openInfoWindow(infoWindow, point);
    };
    //点击工程搜索结果
    window.sousuo = function () {
        $("#selectList").css('display', 'block');
    };

    //监听多选框点击事件  主要是通过 lay-filter="switchTest"  来监听
    form.on('checkbox(switchType)', function (data) {
        itemTypes = ''
        for (var i = 0; i < $('.typeItem').length; i++) {
            if ($('.typeItem')[i].checked) {
                itemTypes += $('.typeItem')[i].value + ','
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
                var iContent;
                if (data.data.length != 0) {
                    data.data.forEach(function (item, index) {
                        iContent =
                            "<div class='searchItem' onclick='dianji(" + JSON.stringify(item) + ")'>" +
                            "<div style='float: left'>" +
                            "<img src=/image/" + item.typeUrl + ">" +
                            "</div>" +
                            "<div style='float: left;padding:0 5px 0 10px;width: 230px'>" +
                            "<strong style='margin-left: 5px;line-height: 25px'>项目名称：" + item.itemName + "</strong>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>项目编号：" + item.itemCode + "</p>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>项目类型：" + item.typeName + "</p>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>地址：" + item.location + "</p>" +
                            "</div>" +
                            "</div>";
                        $("#searchList").append(iContent);
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
                itemPlans += $('.planItem')[i].value + ','
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
                var iContent;
                if (data.data.length != 0) {
                    data.data.forEach(function (item, index) {
                        iContent =
                            "<div class='searchItem' onclick='dianji(" + JSON.stringify(item) + ")'>" +
                            "<div style='float: left'>" +
                            "<img src=/image/" + item.typeUrl + ">" +
                            "</div>" +
                            "<div style='float: left;padding:0 5px 0 10px;width: 230px'>" +
                            "<strong style='margin-left: 5px;line-height: 25px'>项目名称：" + item.itemName + "</strong>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>项目编号：" + item.itemCode + "</p>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>项目类型：" + item.typeName + "</p>" +
                            " <p style='line-height: 20px;font-size: 0.8rem '>地址：" + item.location + "</p>" +
                            "</div>" +
                            "</div>";
                        $("#searchList").append(iContent);
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