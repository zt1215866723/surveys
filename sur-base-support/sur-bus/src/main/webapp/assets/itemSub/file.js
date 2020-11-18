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
        if (data.field.folder ==''){
            Feng.error("请选择文件!");
            return false
        }
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
                        Feng.success("上传成功!");
                        //传给上个页面，刷新table用
                        admin.putTempData('formOk', true);
                        //关掉对话框
                        admin.closeThisDialog();
                        parent.layui.table.reload("itemSubTable");
                    }
                });
            }
        });
        return false
    });
});
