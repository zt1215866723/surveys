layui.use(['form', 'admin', 'ax', 'laydate'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;


    //项目数据列表
    var tempData=  [];
// 百度地图API功能
    var map = new BMap.Map("allmap");    // 创建Map实例
    map.centerAndZoom(new BMap.Point(116.719041,39.518128), 14);  // 初始化地图,设置中心点坐标和地图级别
    var point = new BMap.Point(116.719041,39.518088);
    var myIcon = new BMap.Icon("/assets/item/local.png", new BMap.Size(36,70));
    var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
    map.addOverlay(marker);
    var label = new BMap.Label("勘察院测绘处", {
        offset: new BMap.Size(15, -25)
    });
    label.setStyle({
        color: '#ffffff',
        background: '#a15cc8',
        border: '1px solid "#ff8355"',
        borderRadius: "5px",
        height: "22px",
        lineHeight: "22px",
        padding:"0 10px"
    });
    marker.setLabel(label);
//添加地图类型控件(地图/混合)
    map.addControl(new BMap.MapTypeControl({
        mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]}));
    map.setCurrentCity("廊坊市");          // 设置地图显示的城市 此项是必须设置的
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩

//获取项目
    $.ajax({
        url: Feng.ctxPath + "/item/getItemOnTheMap",
        type: 'post',
        async : false,
        success: function (data) {
            tempData = data.data
            console.log(data.data)
        }
    });

// 搜索按钮点击事件
    $('#btnSearch').click(function () {
        $.ajax({
            url: Feng.ctxPath + "/item/getItemOnTheMap",
            data:{
                itemName : $("#nickName").val()
            },
            dataType: 'json',
            type: 'post',
            success: function (data) {
                tempData = data.data
            }
        });
    });

    var xmPoint= new Array(); //存放标注点经纬信息的数组
    var xmMarker = new Array(); //存放标注点对象的数组
    var info = new Array(); //存放提示信息窗口对象的数组
    tempData.forEach(function (item,index) {
        xmPoint[index] = new window.BMap.Point(item.xaxis,item.yaxis); //循环生成新的地图点
        var myIcon1 = new BMap.Icon("/assets/item/celiang.png", new BMap.Size(24,24));
        var myIcon2 = new BMap.Icon("/assets/item/diji.png", new BMap.Size(24,24));
        var myIcon3 = new BMap.Icon("/assets/item/jikeng.png", new BMap.Size(24,24));
        var myIcon4 = new BMap.Icon("/assets/item/kancha.png", new BMap.Size(24,24));
        var myIcon5 = new BMap.Icon("/assets/item/qita.png", new BMap.Size(24,24));
        if (item.type == 1303502789608460289) {
            var xmLabel = new window.BMap.Label(item.itemName, { offset: new window.BMap.Size(20, -10) });
            xmLabel.setStyle({
                color: '#ffffff',
                background: '#ff151d',
                borderRadius: "5px",
                border:'none',
                textAlign: "center",
                height: "20px",
                lineHeight: "20px",
                padding:"0 10px",

            });
            xmMarker[index] = new window.BMap.Marker(xmPoint[index],{icon:myIcon4}); //按照地图点坐标生成标记
        } else if (item.type == 1303502842829983746) {
            var xmLabel = new window.BMap.Label(item.itemName, { offset: new window.BMap.Size(20, -10) });
            xmLabel.setStyle({
                color: '#ffffff',
                background: '#FF7100',
                borderRadius: "5px",
                border:'none',
                textAlign: "center",
                height: "20px",
                lineHeight: "20px",
                padding:"0 10px"
            });
            xmMarker[index] = new window.BMap.Marker(xmPoint[index],{icon:myIcon2}); //按照地图点坐标生成标记
        } else if (item.type == 1303502895028097025) {
            var xmLabel = new window.BMap.Label(item.itemName, { offset: new window.BMap.Size(20, -10) });
            xmLabel.setStyle({
                color: '#ffffff',
                background: '#2EB5B1',
                borderRadius: "5px",
                border:'none',
                textAlign: "center",
                height: "20px",
                lineHeight: "20px",
                padding:"0 10px"
            });
            xmMarker[index] = new window.BMap.Marker(xmPoint[index],{icon:myIcon1}); //按照地图点坐标生成标记
        } else if (item.type == 1303502953022738433) {
            var xmLabel = new window.BMap.Label(item.itemName, { offset: new window.BMap.Size(20, -10) });
            xmLabel.setStyle({
                color: '#ffffff',
                background: '#0cD500',
                borderRadius: "5px",
                border:'none',
                textAlign: "center",
                height: "20px",
                lineHeight: "20px",
                padding:"0 10px"
            });
            xmMarker[index] = new window.BMap.Marker(xmPoint[index],{icon:myIcon3}); //按照地图点坐标生成标记
        } else{
            var xmLabel = new window.BMap.Label(item.itemName, { offset: new window.BMap.Size(20, -10) });
            xmLabel.setStyle({
                color: '#ffffff',
                background: '#807B76',
                borderRadius: "5px",
                border:'none',
                textAlign: "center",
                height: "20px",
                lineHeight: "20px",
                padding:"0 10px"
            });
            xmMarker[index] = new window.BMap.Marker(xmPoint[index],{icon:myIcon5}); //按照地图点坐标生成标记
        }

        map.addOverlay(xmMarker[index]);

        xmMarker[index].setLabel(xmLabel);
        var sContent =
            "<div style='width: 300px;height: 300px;position:relative'>"+
            "<h4 style='margin:0 0 5px 0;padding:0.2em 0;color: #1E9FFF;font-weight: bold'>"+item.itemName+"</h4>" +
            "<p class='map-card-p' style=''>项目编号："+item.itemCode+"</p>" +
            "<p class='map-card-p'>项目类型："+item.typeName+"</p>" +
            "<p class='map-card-p'>项目地址："+item.location+"</p>" +
            "<p class='map-card-p'>起止时间：<span>"+item.beginDate.slice(0,10)+" 至 "+item.endDate.slice(0,10)+"</span></p>" +
            "<p class='map-card-p'>项目负责人："+item.head+"</p>" +
            "<div style='width: 300px;height: 1px;border-top:1px dashed #333333;margin: 10px 0px'></div>"+
            "<p class='map-card-p'>钻孔数量：<span style=''>25 个</span></p>" +
            "<p class='map-card-p'>档案位置：<span style='color: red;'>档案室内某柜子</span></p>" +
            "<div style='position:absolute; bottom: 0;display: flex;width: 100%'>"+
            "<div style='width: 33% '><a style='color: #1668ff;font-weight: bold' target='_blank' href='/drilling/drillingMap?itemId="+item.id+"' >查看项目钻孔</a></div>" +
            "<div style='width: 34% '><a style='color: #1668ff;font-weight: bold' target='_blank' href='/item/document?itemId="+item.id+"' >进入工程文档</a></div>" +
            "<div style='width: 33% '><a style='color: #1668ff;font-weight: bold' id='lookTJ' >查看项目统计</a></div>" +
            "</div>"+
            "</div>";
        info[index] = new window.BMap.InfoWindow(sContent); // 创建信息窗口对象

        addInfo(info[index],xmMarker[index]);
    })
    // for (var i = 0; i < tempData.length; i ++) {
    //     addInfo(info[index],xmMarker[index]);
    // }
    function addInfo(info,xmMarker){
        xmMarker.addEventListener("click", function(){this.openInfoWindow(info);});
    }
});