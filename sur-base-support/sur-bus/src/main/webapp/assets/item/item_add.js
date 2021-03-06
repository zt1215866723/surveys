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

    //关注项
    $.ajax({
        url: Feng.ctxPath + "/focus/getList",
        dataType: 'json',
        type: 'post',
        success: function (data) {
            var html = "";
            $.each(data.data, function (index, item) {
                html += " <div class='layui-row'><div class='layui-form-item'><label class='layui-form-label'>";
                if(item.type == 0){
                    html += !item.isNecessary ?
                        item.name + "(" + item.unit + ")</label><div class='layui-input-block'>" +
                        "<input id=" + item.id + " name=" + item.id + " type='number' class='layui-input' autocomplete='off'/>"
                        :
                        item.name + "(" + item.unit + ")<span style='color: red;'>*</span></label>" +
                        "<div class='layui-input-block'>" +
                        "<input id=" + item.id + " name=" + item.id + " type='number' class='layui-input' lay-verify='required' required autocomplete='off'/>";
                }else{
                    html += !item.isNecessary ?
                        item.name + "</label><div class='layui-input-block'>" +
                        "<select name=" + item.id + "  id=" + item.id + " lay-search><option value=''>请选择</option>"
                        :
                        item.name + "<span style='color: red;'>*</span></label><div class='layui-input-block'>" +
                        "<select name=" + item.id + "  id=" + item.id + " lay-verify='required' required lay-search><option value=''>请选择</option>"
                    $.ajax({
                        url: Feng.ctxPath + "/dict/listDictsByParent",
                        dataType: 'json',
                        async: false,
                        data: {
                            parentId : item.type
                        },
                        type: 'post',
                        success: function (res) {
                            $.each(res.data, function (sort, value) {
                                html += "<option value=" + value.dictId + ">" + value.name + "</option>";
                            })
                        }
                    });
                    html += "</select>";
                }
                html += "</div></div></div>";
            });
            $('#focus').append(html);
            form.render();
        }
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
                window.location.href = Feng.ctxPath + '/item'
            }else{
                Feng.error(data.message);
            }
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });

        var focus = {}
        $("#focus").find("input").each(function () {
            if($(this).prop("id") != ""){
                focus[$(this).prop("id")]=$(this).prop("value");
            }
        })
        $("#focus").find("select").each(function () {
            focus[$(this).prop("id")]=$(this).prop("value");
        })
        data.field.focus = focus;
        ajax.set(data.field);
        ajax.start();
        return false;
    });

    $("#cancel").click(function () {
        location.href = Feng.ctxPath + '/item'
    });
});
