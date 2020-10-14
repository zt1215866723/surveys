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
    map.centerAndZoom(new BMap.Point(Feng.getUrlParam("xaxis"),Feng.getUrlParam("yaxis")), 19);  // 初始化地图,设置中心点坐标和地图级别
//添加地图类型控件(地图/混合)
    map.addControl(new BMap.MapTypeControl({
        mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]}));
    map.setCurrentCity("廊坊市");          // 设置地图显示的城市 此项是必须设置的
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩

//获取钻孔信息
    $.ajax({
        url: Feng.ctxPath + "/drilling/selectDrillingByItemId",
        data:{
            itemId : Feng.getUrlParam("itemId")
        },
        dataType: 'json',
        type: 'post',
        async : false,
        success: function (data) {
            tempData = data.data
            console.log(tempData)
        }
    });

// 搜索按钮点击事件
    $('#btnSearch').click(function () {
        $.ajax({
            url: Feng.ctxPath + "/item/selectDrillingByItemId",
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
        var myIcon1 = new BMap.Icon("/assets/picture/celiang.png", new BMap.Size(24,24));
        var myIcon2 = new BMap.Icon("/assets/picture/diji.png", new BMap.Size(24,24));
        var myIcon3 = new BMap.Icon("/assets/picture/jikeng.png", new BMap.Size(24,24));
        if (item.type == "鉴别孔") {
            var xmLabel = new window.BMap.Label(item.holeCode, { offset: new window.BMap.Size(20, -10) });
            xmLabel.setStyle({
                color: '#ffffff',
                background: '#000000',
                borderRadius: "5px",
                border:'none',
                textAlign: "center",
                height: "20px",
                lineHeight: "20px",
                padding:"0 10px",

            });
            xmMarker[index] = new window.BMap.Marker(xmPoint[index],{icon:myIcon1}); //按照地图点坐标生成标记
        } else if (item.type == "静力触探试验孔") {
            var xmLabel = new window.BMap.Label(item.holeCode, { offset: new window.BMap.Size(20, -10) });
            xmLabel.setStyle({
                color: '#ffffff',
                background: '#000000',
                borderRadius: "5px",
                border:'none',
                textAlign: "center",
                height: "20px",
                lineHeight: "20px",
                padding:"0 10px"
            });
            xmMarker[index] = new window.BMap.Marker(xmPoint[index],{icon:myIcon2}); //按照地图点坐标生成标记
        } else if (item.type == "取土标贯钻孔") {
            var xmLabel = new window.BMap.Label(item.holeCode, { offset: new window.BMap.Size(20, -10) });
            xmLabel.setStyle({
                color: '#ffffff',
                background: '#000000',
                borderRadius: "5px",
                border:'none',
                textAlign: "center",
                height: "20px",
                lineHeight: "20px",
                padding:"0 10px"
            });
            xmMarker[index] = new window.BMap.Marker(xmPoint[index],{icon:myIcon3}); //按照地图点坐标生成标记
        }
        map.addOverlay(xmMarker[index]);
        xmMarker[index].setLabel(xmLabel);
        var sContent =
            "<div style='width: 300px;height: 300px;position:relative'>"+
            "<h4 style='margin:0 0 5px 0;padding:0.2em 0;color: #1E9FFF;font-weight: bold'>"+item.itemName+"</h4>" +
            "<p class='map-card-p'>钻孔编号："+item.holeCode+"</p>" +
            "<p class='map-card-p'>钻孔类型："+item.type+"</p>" +
            "<p class='map-card-p'>孔口高程(m)："+item.zkbg+"</p>" +
            "<p class='map-card-p'>勘探深度(m)："+item.depth+"</p>" +
            "<div style='position:absolute; bottom: 0;display: flex;width: 100%'>"+
            "<div style='width: 33% '><a style='color: #1668ff;font-weight: bold' target='_blank' href='/item/document?itemId="+item.id+"' >查看项目钻孔</a></div>" +
            "<div style='width: 34% '><a style='color: #1668ff;font-weight: bold' target='_blank' href='/item/document?itemId="+item.id+"' >进入工程文档</a></div>" +
            "<div style='width: 33% '><a style='color: #1668ff;font-weight: bold' id='lookTJ' >查看项目统计</a></div>" +
            "</div>"+
            "</div>";
        info[index] = new window.BMap.InfoWindow(sContent); // 创建信息窗口对象

        addInfo(info[index],xmMarker[index]);
    })
    function addInfo(info,xmMarker){
        xmMarker.addEventListener("click", function(){this.openInfoWindow(info);});
    }

});