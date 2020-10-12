/**
 * 添加或者修改页面
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

































































































































































    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/standard/addItem", function (data) {
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