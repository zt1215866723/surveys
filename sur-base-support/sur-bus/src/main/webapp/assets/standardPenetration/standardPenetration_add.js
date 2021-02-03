/**
 * 添加或者修改页面
 */
var StandardPenetrationInfoDlg = {
    data: {
        id: "",
        itemId: "",
        holeCode: "",
        depth: "",
        bglx: "",
        bgtzz: "",
        length: "",
        bgyzcd: "",
        bgyzjs: "",
        bgjs: "",
        bgxs: "",
        bgxzjs: "",
        bgsxz: "",
        cy: "",
        bgxjsx: "",
        bgxjstjx: "",
        bgjsx: "",
        bgjstjx: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;







    $("#itemId").val(Feng.getUrlParam("itemId"))
    $("#holeCode").val(Feng.getUrlParam("holeCode"))













































    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/standardPenetration/addItem", function (data) {
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