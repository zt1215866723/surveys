/**
 * 添加或者修改页面
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

































































    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/staticTest/addItem", function (data) {
            Feng.success("添加成功！");
            window.location.href = Feng.ctxPath + '/staticTest'
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/staticTest'
    });

});