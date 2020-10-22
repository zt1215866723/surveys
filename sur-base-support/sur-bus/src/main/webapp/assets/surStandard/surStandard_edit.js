/**
 * 详情对话框
 */
var SurStandardInfoDlg = {
    data: {
        id: "",
        itemId: "",
        mainCode: "",
        secondaryCode: "",
        thirdCode: "",
        tcdzsd: "",
        tcdzcy: "",
        type: "",
        name: "",
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
        tcms: "",
        tcztx: "",
        tcjycd: "",
        tcpl: "",
        tcjlfy: "",
        tcjljj: "",
        tcpxjd: "",
        tcqttz: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;




























































































    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/surStandard/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('surStandardForm', result.data);

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/surStandard/editItem", function (data) {
            Feng.success("更新成功！");
            window.location.href = Feng.ctxPath + '/surStandard'
        }, function (data) {
            Feng.error("更新失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();

        return false;
    });

    $('#cancel').click(function(){
        window.location.href = Feng.ctxPath + '/surStandard'
    });
});