layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    //获取类型的下拉框
    $.ajax({
        url: Feng.ctxPath + "/dict/listDictsA",
        data:{
            dictTypeId : '1295976306245627905'
        },
        dataType: 'json',
        type: 'post',
        success: function (data) {
            console.log(data)
            $.each(data.data, function (index, item) {
                $('#type').append(new Option(item.name, item.dictId));
            });
            form.render("select");
        }
    });

    //获取项目类型的下拉框
    $.ajax({
        url: Feng.ctxPath + "/itemType/getItemTypeList",
        dataType: 'json',
        type: 'post',
        success: function (data) {
            $.each(data, function (index, item) {
                $('#itemType').append(new Option(item.name, item.id));
            });
            form.render("select");
        }
    });

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        data.field.isShow = data.field.isShow == "on" ? "0" : "1";

        var ajax = new $ax(Feng.ctxPath + "/focus/addItem", function (data) {
            Feng.success("添加成功！");
            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);
            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
        return false;
    });

});