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
        ajax.set(data.field);
        ajax.start();
        return false;
    });

});