/**
 * 详情对话框
 */
var StaticTestInfoDlg = {
    data: {
        id: "",
        itemId: "",
        holeCode: "",
        depth: "",
        jtlx: "",
        length: "",
        jtbgrzl: "",
        jtztzl: "",
        jtcmz: "",
        jtmzb: "",
        jtkxyl: "",
        cy: "",
        jtbgrzlx: "",
        jtbjrzltjx: "",
        jtztzlx: "",
        jtztzltjx: "",
        jtcmzx: "",
        jtcmztjx: "",
        jtmzbx: "",
        jtmzbtjx: "",
        jtkxylx: "",
        jtkxyltjx: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

































































    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/staticTest/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('staticTestForm', result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/staticTest/editItem", function (data) {
            Feng.success("更新成功！");
            window.location.href = Feng.ctxPath + '/staticTest'
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/staticTest'
    });
});