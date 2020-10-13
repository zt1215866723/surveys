layui.use(['form', 'admin', 'ax', 'laydate'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;
    // 百度地图API功能
    var map = new BMap.Map("allmap");
    if(Feng.getUrlParam("xaxis") != '' && Feng.getUrlParam("yaxis") != ''){
        var point = new BMap.Point(Feng.getUrlParam("xaxis"),Feng.getUrlParam("yaxis"));
        var myIcon = new BMap.Icon("/assets/item/local.png", new BMap.Size(36,70));
        var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
        map.addOverlay(marker);
        console.log( decodeURI(decodeURI(Feng.getUrlParam("itemName"))))
        var label = new BMap.Label(decodeURI(decodeURI(Feng.getUrlParam("itemName"))), {
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
        map.centerAndZoom(point, 18);
    }else{
        map.centerAndZoom("廊坊", 18);
    }
    map.enableScrollWheelZoom();
});