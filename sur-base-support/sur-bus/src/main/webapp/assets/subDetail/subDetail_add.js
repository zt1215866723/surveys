layui.use(['upload','form', 'admin', 'ax', 'element'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var element = layui.element;
    var upload =layui.upload;

    var subId =  Feng.getUrlParam("subId");
    var cataId =  Feng.getUrlParam("cataId");

    //文件存储路径
    let saveUrl = new Set()
    //文件上传结果
    var isSuccess = '';
    //是否选择了文件
    var isSelected = 0;


    form.on('select(cataParentId)', function (data){
        $("#cataId").empty();
        $.ajax({
            url: Feng.ctxPath + "/dict/listDictsByParent",
            data:{
                parentId : data.elem.value
            },
            dataType: 'json',
            type: 'post',
            success: function (data) {
                console.log(data.data)
                $('#cataId').append(new Option());
                $.each(data.data, function (index, item) {
                    $('#cataId').append(new Option(item.name, item.dictId));
                });
                form.render("select");
            }
        });
    })

    form.on('select(cataId)', function (data){
        $("#cataName").val($("#cataId option:selected").text());
    })

    //拖拽上传
    upload.render({
        elem: '#test10'
        ,url: Feng.ctxPath + "/subDetail/fileUpload"
        ,data: {
            subId : subId
        }
        ,multiple:true
        ,accept: 'file'
        ,auto: false
        ,bindAction: btnSubmit
        ,before: function(obj){
            isSelected = 1;
            isSuccess = '';
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
                saveUrl.add(res.data);
                isSuccess = 1;
            }else{
                layer.msg(res.message);
                saveUrl.clear();
                isSuccess = 0;
                return false;
            }
        }
        ,error: function(){
            isSuccess = 0;
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
        judage(data);
        return false;
    })

    var judage = function(data){
        if(isSuccess == 1){
            data.field.subId = subId;
            data.field.saveUrl = [...saveUrl].join(",%,");
            data.field.cataId = cataId;
            //添加
            $.ajax({
                url: Feng.ctxPath + "/subDetail/addItem",
                dataType: 'json',
                data: data.field,
                type: 'post',
                success: function (res) {
                    if(res.success){
                        Feng.success("添加成功！");
                        parent.layui.table.reload('subDetailTable');
                        //关掉对话框
                        admin.closeThisDialog();
                    }else{
                        Feng.error(res.message)
                    }
                }
            });
        }else if(isSuccess === 0){
            return false;
        }else{
            setTimeout(function(){ judage(data);},1000)
        }
    }

});