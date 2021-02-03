/**
 * 添加或者修改页面
 */
var WaterLevelInfoDlg = {
    data: {
        id: "",
        itemId: "",
        holeCode: "",
        depth: "",
        swlx: "",
        swch: "",
        swcsrq: "",
        swdxsw: "",
        swfw: "",
        swxz: "",
        cy: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;




    //渲染时间选择框
    laydate.render({
        elem: '#swcsrq',
        trigger: 'click',
        type: 'date'
    });







    $("#itemId").val(Feng.getUrlParam("itemId"))
    $("#holeCode").val(Feng.getUrlParam("holeCode"))



















    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/waterLevel/addItem", function (data) {
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