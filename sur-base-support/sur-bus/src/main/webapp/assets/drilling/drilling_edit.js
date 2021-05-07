/**
 * 详情对话框
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

    //获取详情信息，填充表单
    var ajax = new $ax(Feng.ctxPath + "/drilling/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('drillingForm', result.data);

    $.ajax({
        url: Feng.ctxPath + "/drillingType/getDrillingTypeList",
        dataType: 'json',
        type: 'post',
        success: function (data) {
            $.each(data, function (index, item) {
                $('#type').append(new Option(item.name, item.id));
            });
            $('#type').val(result.data.type);
            form.render("select");
        }
    });

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/drilling/editItem", function (data) {
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