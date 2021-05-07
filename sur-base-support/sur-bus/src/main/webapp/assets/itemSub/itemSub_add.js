layui.use(['form', 'admin', 'ax', 'laydate', 'upload', 'formSelects'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;

    //渲染时间选择框
    laydate.render({
        elem: '#writeTime',
        trigger: 'click',
        type: 'date'
    });

    //点位列表
    $.ajax({
        url: Feng.ctxPath + "/item/getList",
        dataType: 'json',
        type: 'post',
        success: function (data) {
            $.each(data.data, function (index, item) {
                $('#itemId').append(new Option(item.itemName, item.id));
            });
            form.render("select");
        }
    });

    getDict('1303870720255148034','surLevel');
    getDict('1303870848382746625','surStage');

    function getDict(typeId, id){
        $.ajax({
            url: Feng.ctxPath + "/dict/listDicts",
            data:{
                dictTypeId : typeId
            },
            dataType: 'json',
            type: 'post',
            success: function (data) {
                $.each(data.data, function (index, item) {
                    $('#' + id).append(new Option(item.name, item.dictId));
                });
                form.render("select");
            }
        });
    }

    //获取项目大类关注项
    // form.on('select(itemId)', function(data){
    //     layui.layer.msg(data.value);//得到被选中的值
    //
    // })


    /*
     * 工程选择 -> 判断文档是否重复
     * 2020年11月12日
     * 王南翔
     */
    let isRepeat = false
    form.on('select(itemId)', function (data) {
        $.ajax({
            url: Feng.ctxPath + '/itemSub/checkRepeat',
            method: 'get',
            data: {'itemId': data.value},
            success: function (res) {
                console.log(res);
                if(res) {
                    isRepeat = true
                    Feng.error("项目文档已存在！")
                }
            }
        })
    });

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        if(isRepeat) {
            Feng.error("项目文档已存在！")
            return false
        }
        var ajax = new $ax(Feng.ctxPath + "/itemSub/addItem", function (data) {
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