layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;

    //渲染时间选择框
    laydate.render({
        elem: '#writeTime',
        trigger: 'click',
        type: 'date'
    });

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/itemSub/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('itemSubForm', result.data);
    $("#writeTime").val(result.data.writeTime.slice(0,10));

    //点位列表
    $.ajax({
        url: Feng.ctxPath + "/item/getList",
        dataType: 'json',
        type: 'post',
        success: function (data) {
            $.each(data.data, function (index, item) {
                $('#itemId').append(new Option(item.itemName, item.id));
            });
            $('#itemId').val(result.data.itemId);
            form.render("select");
        }
    });

    getDict('1303870720255148034','surLevel', result.data.surLevel);
    getDict('1303870848382746625','surStage', result.data.surStage);

    function getDict(typeId, id, value){
        $.ajax({
            url: Feng.ctxPath + "/dict/listDicts",
            data:{
                dictTypeId : typeId
            },
            dataType: 'json',
            type: 'post',
            success: function (data) {
                $.each(data.data, function (index, item) {
                    $('#' + id).append(new Option(item.name, item.dictId));
                });
                $('#' + id).val(value);
                form.render("select");
            }
        });
    }

    //关注项
    $.ajax({
        url: Feng.ctxPath + "/focus/getList",
        dataType: 'json',
        type: 'post',
        async: false,
        success: function (data) {
            var html = "";
            $.each(data.data, function (index, item) {
                html += "<div class='layui-inline layui-col-md12'><label class='layui-form-label'>";
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
                html += "</div></div>";
            });
            $('#focus').append(html);
            form.render();
        }
    });

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

    var focus = {}
    form.on('select(monitor)', function(data){
        focus[data.elem.id] = data.value;
    });

    $("#focus").find("input").each(function () {
        if($(this).prop("id") != ""){
            document.getElementById($(this).prop("id")).addEventListener('input',function(){
                focus[$(this).prop("id")] = $(this).prop("value");
            });
        }
    })

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/itemSub/editItem", function (data) {
            Feng.success("更新成功！");
            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);
            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        data.field.focus = focus;
        ajax.set(data.field);
        ajax.start();
        return false;
    });

});