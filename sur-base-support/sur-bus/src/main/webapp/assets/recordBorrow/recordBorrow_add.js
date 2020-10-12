layui.use(['form', 'admin', 'ax', 'laydate'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;

    var subId =  Feng.getUrlParam("subId");

    //渲染时间选择框
    laydate.render({
        elem: '#addTime',
        trigger: 'click',
        value: new Date(),
        type: 'datetime'
    });

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/recordBorrow/addItem", function (data) {
            if(data.success){
                Feng.success("操作成功！");
                //先得到当前iframe层的索引
                var index = parent.layer.getFrameIndex(window.name);
                //刷新父级页面的表格
                parent.layui.table.reload('itemSubTable');
                //关掉对话框
                admin.closeThisDialog();
            }else{
                Feng.error(data.message);
            }
        }, function (data) {
            Feng.error("操作失败！" + data.responseJSON.message)
        });
        data.field.documentId = subId;
        ajax.set(data.field);
        ajax.start();
        return false;
    });
});
