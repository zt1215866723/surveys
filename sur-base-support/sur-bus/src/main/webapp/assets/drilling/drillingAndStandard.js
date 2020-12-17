layui.use(['element', 'table', 'admin', 'ax', 'func','form'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;
    var element = layui.element;
    var form = layui.form;
    /**
     * 勘探点数据表管理
     */
    var Drilling = {
        tableId: "drillingTable"
    };

    var itemId = Feng.getUrlParam("itemId");

    // 返回按钮点击事件
    $('#btnReturn').click(function () {
        location.href = "/item/itemDetail?id=" + itemId
    });

    /**
     * 初始化表格的列
     */
    Drilling.initColumn = function () {
        return [
            [
                // {field: 'rowNum',align:'center', title: '序号', rowspan: 2},
                {field: 'holeCode',align:'center', title: '钻孔编号', rowspan: 2, totalRowText: '合计'},
                {field: 'type',align:'center', title: '钻孔类型', rowspan: 2},
                {field: 'depth',align:'center', title: '钻孔深度(m)', rowspan: 2, totalRow: true},
                {field: 'zkbg',align:'center', title: '地面高程(m)', rowspan: 2},
                {title: '坐标',align:'center',colspan: 2},
                {title: '取样个数',align:'center',colspan: 2},
                {field: 'bg',align:'center', title: '标贯(次)', rowspan: 2, totalRow: true},
                {title: '地下稳定水位',align:'center',colspan: 2}
            ],
            [
                {field: 'zkx',align:'center', title: '横坐标'},
                {field: 'zky',align:'center', title: '纵坐标'},
                {field: 'yz',align:'center', title: '原状样', totalRow: true},
                {field: 'rd',align:'center', title: '扰动样', totalRow: true},
                {field: 'ms',align:'center', title: '埋深(m)', edit: 'text'},
                {field: 'gc',align:'center', title: '高程(m)'}
            ]
        ];
    };

    //2020年12月13日 王南翔
    //监听单元格编辑(埋深)
    table.on('edit(drillingTable)', function(obj){
        $("table input").attr("type","number");
        var value = obj.value //得到修改后的值
            ,data = obj.data //得到所在行所有键值
            ,field = obj.field; //得到字段
        if (value != null && isNaN(parseInt(value))) {
            layer.msg('请输入正确的数值');
            table.reload('drillingTable');
            return false;
        }
        $.ajax({
            url:'/waterLevel/editItem',
            type:'POST',
            data:{"id": data.msId,"depth" : value},
            success: function (res) {
                layer.msg('完成将第[' + data.holeCode + ']个孔['+ data.type +'] ' + ' 数值更改为：'+ value, {
                    time: 5000,
                });
            }
        })
    });

    /**
     * 地层信息管理
     */
    var Standard = {
        tableId: "standardTable"
    };

    /**
     * 初始化表格的列
     */
    Standard.initColumn = function () {
        return [[
            {field: 'bh', title: '土层编号'},
            {field: 'name', title: '土层名称'},
            {field: 'tcdzsd', title: '地质时代'},
            {field: 'tcdzcy', title: '地质成因'},
            {field: 'tcms', title: '土层描述',width: 300},
            {field: 'sdfw', title: '深度范围(m)'},
            {field: 'hdfw', title: '厚度范围(m)'},
            {field: 'hdpj', title: '厚度平均值(m)'},
            {field: 'zkSum', title: '备注',width:180, templet: function (d) {
                    return "本层在"+d.zkSum+"个钻孔中揭示"
                }}
        ]];
    };

    // 渲染勘探点一览表格
    var tableResult = table.render({
        elem: '#' + Drilling.tableId,
        url: Feng.ctxPath + '/drilling/selectExplorationPoints',
        page: false,
        totalRow: true,
        where: {
            itemId : itemId
        },
        height: "full-158",
        cellMinWidth: 100,
        cols: Drilling.initColumn()
    });

    // 渲染地层信息统计表格
    var tableResult = table.render({
        elem: '#' + Standard.tableId,
        url: Feng.ctxPath + '/standard/selectStandardByIds',
        page: false,
        where: {
            itemId : itemId
        },
        height: "full-158",
        cellMinWidth: 100,
        cols: Standard.initColumn()
    });

    $('#msUpdateBtn').click(function () {
        let index = layer.open({
            title: '在线调试',
            content: '<form id="msUpdateForm" class="layui-form" action="/waterLevel/updateMs" method="post">\n' +
                '    <div class="layui-form-item">\n' +
                '        <label class="layui-form-label">增减值</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="radio" name="opt" value="+" title="+" checked>\n' +
                '            <input type="radio" name="opt" value="-" title="-">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item">\n' +
                '        <label class="layui-form-label">修正值</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="number" name="depth" required  lay-verify="required" placeholder="请输入修正值" autocomplete="off" class="layui-input">\n' +
                '        </div>\n' +
                '    </div>\n' +
                ' <input type="hidden" name="itemId" value="'+ itemId +'">' +
                '</form>',
            success: function(layero, index){
                form.render()
            },
            closeBtn: 1,
            btn: ['提交', '取消'],
            yes: function(index, layero){
                let msUpdateForm = $(layero).find('#msUpdateForm');
                $.ajax({
                    url: msUpdateForm.attr("action"),
                    type: "POST",
                    data: msUpdateForm.serialize(),
                    success: function () {
                        layer.closeAll();
                        table.reload('drillingTable');
                        layer.msg("修改成功",{
                            time: 3000,
                        })
                    }
                })
            },
            btn2: function(index, layero){

            },
        });
    });
});
