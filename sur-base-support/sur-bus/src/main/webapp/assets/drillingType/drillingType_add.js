/**
 * 添加或者修改页面
 */
var DrillingTypeInfoDlg = {
    data: {
        id: "",
        name: "",
        url: "",
        status: ""
    }
};

layui.use(['form', 'admin', 'ax','laydate','upload','formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var upload = layui.upload;











    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/drillingType/addItem", function (data) {
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

    //图例上传
    var uploadInst = upload.render({
        elem: '#imgUpload'
        ,url: 'imgUpload'
        ,accept:'images'
        ,exts:'jpg|png|gif|bmp|jpeg'
        ,size:'204800'          //单位 KB
        , before: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#preview').attr('src', result).css("width","15%").css("height","15%"); //图片链接（base64）
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code == 0) {
                return layer.msg('上传失败');
            } else {
                //上传成功
                $("#url").val(res.data.src)
            }

        }
        , error: function () {
            //演示失败状态，并实现重传
            var retry = $('#retry');
            retry.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a >');
            retry.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });
});

