layui.use(['table', 'ax', 'func', 'form', 'admin', 'layer', 'steps', 'tableCheckBoxUtil', 'tableCheckBoxMultiColumnUtil'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var form = layui.form;
    var layer = layui.layer;
    var steps = layui.steps;
    var tableCheckBoxUtil = layui.tableCheckBoxUtil;
    var tableCheckBoxMultiColumnUtil = layui.tableCheckBoxMultiColumnUtil;
    steps.prev('demoSteps');  // 上一步
    /**
     * 工程表管理
     */
    var Item = {
        tableId: "itemTable"
    };

    //获取工程类型的下拉框
    $.ajax({
        url: Feng.ctxPath + "/itemType/getItemTypeList",
        dataType: 'json',
        type: 'post',
        success: function (data) {
            $.each(data, function (index, item) {
                $('#type').append(new Option(item.name, item.id));
            });
            form.render("select");
        }
    });

    //获取进度的下拉框
    $.ajax({
        url: Feng.ctxPath + "/dict/listDicts",
        data: {
            dictTypeId: '1303593897935896578'
        },
        dataType: 'json',
        type: 'post',
        success: function (data) {
            $.each(data.data, function (index, item) {
                $('#progress').append(new Option(item.name, item.dictId));
            });
            form.render("select");
        }
    });

    // 渲染表格
    var tableResult = table.render({
        id: 'itemTable',
        elem: '#' + Item.tableId,
        url: Feng.ctxPath + '/item/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        limit: 20,
        cols: [[
            {type: 'checkbox'},
            {field: 'itemName', title: '工程名称', align: 'center', width: 250},
            {field: 'itemCode', title: '工程编号', align: 'center'},
            {field: 'typeName', title: '工程类型', align: 'center'},
            {field: 'location', title: '工程地点', align: 'center'},
            {field: 'processName', title: '工程进度', align: 'center'},
        ]],
        done: function (data) {
            //2.初始化表格行选中状态
            tableCheckBoxMultiColumnUtil.checkedDefault({
                gridId: 'itemTable'
                , fieldName: 'id'
            });

            tableCheckBoxUtil.checkedDefault({
                gridId: 'itemTable'
                , fieldName: 'id'
            });

        }
    });

    //1.初始化分页设置
    tableCheckBoxMultiColumnUtil.init({
        gridId: 'itemTable'
        , filterId: 'itemTable'
        , fieldName: 'id'
        , attachFieldsName: ['itemCode', 'itemName']
    });

    //1.初始化分页设置
    tableCheckBoxUtil.init({
        gridId: 'itemTable'
        , filterId: 'itemTable'
        , fieldName: 'id'
    });

    //筛选
    $('#btn_CheckBoxSearch').on('click', function () {
        //执行重载
        table.reload('itemTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: {
                itemName: $("#itemName").val(),
                itemCode: $("#itemCode").val(),
                location: $("#location").val(),
                type: $("#type").val(),
                progress: $("#progress").val()
            }
        });
    });

    //3.获取选中编号
    $("#btn_CheckBoxTableMultiColumnGetValue").on('click', function () {
        var selectedData = tableCheckBoxMultiColumnUtil.getValue({
            gridId: 'itemTable'
        });
        alert(JSON.stringify(selectedData));
    });
    /*
    判断是否选择工程
 */
    $("#selectType").on('click', function () {
        var selectedData = tableCheckBoxUtil.getValue({
            gridId: 'itemTable'
        });
        if (selectedData.length > 0) {
            var itemIdString="";
            for (var i = 0; i < selectedData.length; i++) {
                itemIdString+=selectedData[i]+","
            }
            itemIdString = itemIdString.substr(0,itemIdString.length-1);
            steps.next('demoSteps');  // 下一步
            //刷新钻孔表格
            table.reload('drillingTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    itemIdString:itemIdString
                }
            });
        } else {
            layer.msg("请选择想要对比的工程！", {icon: 5, time: 1500})
        }
        return false;
    })

    // 渲染表格
    var tableResult = table.render({
        id: 'drillingTable',
        elem: '#drillingTable',
        url: Feng.ctxPath + '/drilling/waterList',
        page: true,
        height: "full-158",
        limit: 10,
        cols: [[
            {type: 'checkbox'},
            {field: 'itemName', sort: true, title: '工程名称'},
            {field: 'itemCode', sort: true, title: '工程编号'},
            {field: 'holeCode', sort: true, title: '钻孔编号'},
            {field: 'type', sort: true, title: '钻孔类型'},
            {field: 'ms', sort: true, title: '埋深(m)'},
            {field: 'gc', sort: true, title: '高程(m)'}
        ]],
        done: function (data) {
            //2.初始化表格行选中状态
            tableCheckBoxMultiColumnUtil.checkedDefault({
                gridId: 'drillingTable'
                , fieldName: 'id'
            });

            tableCheckBoxUtil.checkedDefault({
                gridId: 'drillingTable'
                , fieldName: 'id'
            });
        }
    });

    //1.初始化分页设置
    tableCheckBoxMultiColumnUtil.init({
        gridId: 'drillingTable'
        , filterId: 'drillingTable'
        , fieldName: 'id'
        , attachFieldsName: ['holeCode', 'type']
    });

    //1.初始化分页设置
    tableCheckBoxUtil.init({
        gridId: 'drillingTable'
        , filterId: 'drillingTable'
        , fieldName: 'id'
    });

    //筛选
    $('#btn_CheckBoxSearchs').on('click', function () {
        //执行重载
        table.reload('drillingTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: {
                holeCode: $("#holeCode").val(),
                type: $("#drillingType").val()
            }
        });
    });

    //3.获取选中编号
    $("#btn_CheckBoxTableMultiColumnGetValues").on('click', function () {
        var selectedData = tableCheckBoxMultiColumnUtil.getValue({
            gridId: 'drillingTable'
        });
        alert(JSON.stringify(selectedData));
    });

    /*
判断是否选择钻孔
*/
    $("#selectTem").on('click', function () {
        var selectedData = tableCheckBoxUtil.getValue({
            gridId: 'drillingTable'
        });
        if (selectedData.length > 0) {
            var drillingIdString="";
            for (var i = 0; i < selectedData.length; i++) {
                drillingIdString+=selectedData[i]+","
            }
            drillingIdString = drillingIdString.substr(0,drillingIdString.length-1);
            steps.next('demoSteps');  // 下一步
            //刷新钻孔表格
            table.reload('drillingTables', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    drillingIdString:drillingIdString
                }
            });
        } else {
            layer.msg("请选择想要对比的工程！", {icon: 5, time: 1500})
        }
        return false;
    })

    // 渲染表格
    var tableResult = table.render({
        id: 'drillingTables',
        elem: '#drillingTables',
        url: Feng.ctxPath + '/drilling/waterList',
        page: true,
        height: "full-158",
        limit: 10,
        cols: [[
            {field: 'itemName', sort: true, title: '工程名称'},
            {field: 'itemCode', sort: true, title: '工程编号'},
            {field: 'holeCode', sort: true, title: '钻孔编号'},
            {field: 'type', sort: true, title: '钻孔类型'},
            {field: 'ms', sort: true, title: '埋深(m)'},
            {field: 'gc', sort: true, title: '高程(m)'}
        ]]
    });
});
