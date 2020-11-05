layui.use(['form', 'admin', 'ax', 'laydate'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;

    //渲染时间选择框
    laydate.render({
        elem: '#beginDate',
        trigger: 'click',
        type: 'date'
    });

    //渲染时间选择框
    laydate.render({
        elem: '#endDate',
        trigger: 'click',
        type: 'date'
    });

    // 百度地图API功能
    var map = new BMap.Map("allmap");
    map.centerAndZoom("廊坊", 18);
    map.enableScrollWheelZoom();

    //add click
    function showInfo(e) {
        map.clearOverlays();
        document.getElementById("xaxis").value = e.point.lng;
        document.getElementById("yaxis").value = e.point.lat;
        var mk = new BMap.Marker(e.point);
        map.addOverlay(mk);
        map.panTo(e.point);
    }
    map.addEventListener("click", showInfo);

    $('#btnSearch').click(function () {
        // 创建地址解析器实例
        var local = new BMap.LocalSearch(map, {
            onSearchComplete: function () {
                var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果

                if (pp) {
                    map.clearOverlays();
                    map.centerAndZoom(pp, 18);
                    map.addOverlay(new BMap.Marker(pp));    //添加标注
                    document.getElementById("xaxis").value = pp.lng;
                    document.getElementById("yaxis").value = pp.lat;
                } else {
                    alert("您输入的地址在地图中未找到，请重新输入地址!");
                }
            }
        });
        // 将地址解析结果显示在地图上,并调整地图视野
        local.search(document.getElementById("address").value );
    })

    //获取项目类型的下拉框
    $.ajax({
        url: Feng.ctxPath + "/itemType/getItemTypeList",
        dataType: 'json',
        type: 'post',
        success: function (data) {
            $.each(data, function (index, item) {
                $('#type').append(new Option(item.name, item.id));
            });
            form.render("select");
        }
    });

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

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/item/addItem", function (data) {
            if(data.success){
                Feng.success("添加成功！");
                //传给上个页面，刷新table用
                admin.putTempData('formOk', true);
                //关掉对话框
                admin.closeThisDialog();
            }else{
                Feng.error(data.message);
            }
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
        return false;
    });
});
