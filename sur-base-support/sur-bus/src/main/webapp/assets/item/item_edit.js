layui.use(['form', 'admin', 'ax', 'laydate'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/item/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('itemForm', result.data);
    $('#beginDate').val(result.data.beginDate.slice(0,10));
    $('#endDate').val(result.data.endDate.slice(0,10));

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
        async: false,
        success: function (data) {
            var html = "";
            $.each(data.data, function (index, item) {
                html += " <div class='layui-row'><div class='layui-form-item'><label class='layui-form-label'>";
                if(item.type == 0){
                    html += !item.isNecessary ?
                        item.name + "(" + item.unit + ")</label><div class='layui-input-block'>" +
                        "<input id=" + item.id + " type='number' class='layui-input' autocomplete='off'/>"
                        :
                        item.name + "(" + item.unit + ")<span style='color: red;'>*</span></label>" +
                        "<div class='layui-input-block'>" +
                        "<input id=" + item.id + " type='number' class='layui-input' lay-verify='required' required autocomplete='off'/>";
                }else{
                    html += !item.isNecessary ?
                        item.name + "</label><div class='layui-input-block'>" +
                        "<select id=" + item.id + " lay-filter='monitor' lay-search><option value=''>请选择</option>"
                        :
                        item.name + "<span style='color: red;'>*</span></label><div class='layui-input-block'>" +
                        "<select id=" + item.id + " lay-filter='monitor' lay-verify='required' required lay-search><option value=''>请选择</option>"
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

    var focus = {}
    form.on('select(monitor)', function(data){
        console.log(focus[data.elem.id])
        console.log(data.value)
        focus[data.elem.id] = data.value;
    });

    $("#focus").find("input").each(function () {
        if($(this).prop("id") != ""){
            document.getElementById($(this).prop("id")).addEventListener('input',function(){
                focus[$(this).prop("id")] = $(this).prop("value");
            });
        }
    })

    //赋值
    $.ajax({
        url: Feng.ctxPath + "/itemSub/getList",
        dataType: 'json',
        async: false,
        data: {
            id : result.data.id
        },
        type: 'post',
        success: function (res) {
            $.each(res.data, function (index, item) {
                if(item.nouValue != null && item.nouValue != ""){
                    $("#" + item.focusId).val(item.nouValue);
                }
                if(item.strValue != null && item.strValue != ""){
                    $("#" + item.focusId).val(item.strValue);
                }
                document.getElementById(item.focusId).name= item.id;
            })
            form.render();
        }
    });

    //渲染时间选择框
    laydate.render({
        elem: '#endDate',
        trigger: 'click',
        type: 'date'
    });

    //获取工程类型的下拉框
    $.ajax({
        url: Feng.ctxPath + "/itemType/getItemTypeList",
        dataType: 'json',
        type: 'post',
        success: function (data) {
            $.each(data, function (index, item) {
                $('#type').append(new Option(item.name, item.id));
            });
            $('#type').val(result.data.type);
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
            $('#progress').val(result.data.progress);
            form.render("select");
        }
    });

    // 百度地图API功能
    var map = new BMap.Map("allmap");
    if(result.data.xaxis != '' && result.data.yaxis != ''){
        var point = new BMap.Point(result.data.xaxis,result.data.yaxis);
        map.centerAndZoom(point, 18);
    }else{
        map.centerAndZoom("廊坊", 18);
    }
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

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/item/editItem", function (data) {
            if(data.success){
                Feng.success("更新成功！");
                window.location.href = Feng.ctxPath + '/item'
            }else{
                Feng.error(data.message);
            }
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        data.field.focus = focus;
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $("#cancel").click(function () {
        location.href = Feng.ctxPath + '/item'
    });
});