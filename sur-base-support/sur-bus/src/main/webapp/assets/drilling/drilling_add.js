/**
 * 添加或者修改页面
 */
var DrillingInfoDlg = {
    data: {
        id: "",
        itemId: "",
        holeCode: "",
        type: "",
        zkx: "",
        zky: "",
        jzdd: "",
        zkdh: "",
        zklc: "",
        zkpil: "",
        zkbg: "",
        zkhsbg: "",
        depth: "",
        zktjsd: "",
        zkzj: "",
        zkdclc: "",
        zkdcjj: "",
        zkdcqsd: "",
        zkdczsd: "",
        zkdcpm: "",
        zkxn: "",
        zksfcy: "",
        zkjyk: "",
        zkdxsw: "",
        zktcjd: "",
        zkksrq: "",
        zkzzrq: "",
        zkdj: "",
        zkpztsdxs: "",
        zkpzdqyxs: "",
        bz: "",
        zksc: "",
        zkh: "",
        zkv: "",
        zkyhzs: "",
        sxdj: "",
        sxlx: "",
        zkpzqs: "",
        zkpzdj: "",
        zkymshsl: "",
        zkymcsxhsl: "",
        dtable: "",
        ysgcbh: "",
        zkcolor: "",
        zkzbh: "",
        zkszff: "",
        zkzjlx: "",
        zkztdw: "",
        zkgdmc: "",
        zkdmbg: "",
        jyjcgc: "",
        sortikey: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;

    $("#itemId").val(Feng.getUrlParam("itemId"))

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/drilling/addItem", function (data) {
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