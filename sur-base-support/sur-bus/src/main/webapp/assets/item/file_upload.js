layui.use(['upload','form', 'admin', 'ax', 'element'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var element = layui.element;
    var upload =layui.upload;

    var itemId =  Feng.getUrlParam("itemId");

    //是否选择了文件
    var isSelected = 0;

    //拖拽上传
    upload.render({
        elem: '#test10'
        ,url: Feng.ctxPath + "/item/fileUpload"
        ,data: {
            itemId : itemId
        }
        ,multiple:false
        ,accept: 'file'
        ,auto: false
        ,bindAction: btnSubmit
        ,before: function(obj){
            isSelected = 1;
            layer.msg('文件上传中...', {
                icon: 16,
                shade: 0.01,
                time: 0
            })
        }
        ,progress: function(n, elem){
            var percent = n + '%';
            element.progress('demo', percent);
        }
        ,done: function(res){
            layer.close(layer.msg());
            if(res.success){
                var prompt = layer.msg('文件同步中...', {
                    icon: 16,
                    shade: 0.2,
                    time: false
                });
                $.ajax({
                    url: Feng.ctxPath + "/item/synchronous",
                    data: {
                        itemId: itemId,
                        fileUrl:res.data
                    },
                    dataType: 'json',
                    type: 'post',
                    success: function (data) {
                        layer.close(prompt);
                        if (data.success) {
                            Feng.success("操作成功,请等待同步完成。");
                            parent.layui.table.reload('itemTable');
                            //关掉对话框
                            admin.closeThisDialog();
                        } else {
                            Feng.error(data.message)
                        }
                    }
                });
                return false;
            }else{
                layer.msg(res.message);
                return false;
            }
        }
        ,error: function(){
            //请求异常回调
            layer.msg('资源服务器连接异常,请稍后重试', {
                icon: 5
            });
        }
    });

    form.on('submit(btnSubmit)', function (data) {
        if(isSelected == 0){
            layer.msg("请先上传文件。");
            return false;
        }
        return false;
    })

});