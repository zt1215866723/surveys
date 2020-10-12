/**
 * 详情对话框
 */
var StandardInfoDlg = {
    data: {
        id: "",
        itemId: "",
        holeCode: "",
        depth: "",
        tcxh: "",
        mainCode: "",
        secondaryCode: "",
        thirdCode: "",
        tcdzsd: "",
        tcdzcy: "",
        tcdchd: "",
        tchd: "",
        type: "",
        name: "",
        tcmdh: "",
        tcymc: "",
        tcys: "",
        tcmsd: "",
        tcsid: "",
        tcksx: "",
        tchyd: "",
        tcjyx: "",
        tcfhcd: "",
        tcysqx: "",
        tcysqj: "",
        tckwcf: "",
        tcjggz: "",
        tcbhw: "",
        tcqw: "",
        desc: "",
        tcztx: "",
        tcjycd: "",
        tcpl: "",
        tcjlfy: "",
        tcdcms: "",
        tcjljj: "",
        tcpxjd: "",
        tcqttz: "",
        tczrzd: "",
        tcnjl: "",
        tcnmcj: "",
        tcczl: "",
        tcysml: "",
        tcyzzCzltzz: "",
        tcyzzDzltzz: "",
        tcyzzCzlbzz: "",
        tcyzzDzlbzz: "",
        tcckzCzltzz: "",
        tcckzDzltzz: "",
        tcckzCzlbzz: "",
        tcckzDzlbzz: "",
        tcuserdefine1: "",
        tcuserdefine2: "",
        tcuserdefine3: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

































































































































































    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/standard/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('standardForm', result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/standard/editItem", function (data) {
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