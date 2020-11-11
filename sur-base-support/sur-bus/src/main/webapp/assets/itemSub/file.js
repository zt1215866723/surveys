layui.use(['form', 'admin', 'ax', 'laydate', 'layer'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;
    var layer = layui.layer;

    var subId =  Feng.getUrlParam("subId");

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var prompt = layer.msg('文件同步中...', {
            icon: 16,
            shade: 0.2,
            time: false
        });
        var formData = new FormData($('#itemSubForm')[0]);
        $.ajax({
            url: Feng.ctxPath + "/fileView/uploadFolder",
            data : formData,
            type: 'post',
            cache: false,
            processData: false,
            contentType: false,
            async:false,
            success: function (data) {
                $.ajax({
                    url: Feng.ctxPath + "/itemSub/editItem",
                    data : {
                        id : subId,
                        filePath : data.data
                    },
                    type: 'post',
                    success: function (data) {
                        layer.close(prompt);
                    }
                });
            }
        });
        return false
    });
});
