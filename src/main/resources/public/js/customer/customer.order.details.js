layui.use(['table', 'layer'], function () {
    var table = layui.table,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    var tableIns = table.render({
        elem: '#orderDetailList', //绑定table id
        height: 'full-125',
        url: ctx + '/order_detail/list?orderId=' + $("input[name='id'] ").val(),
        page: true,
        toolbar: '#toolbarDemo',
        cellMinWidth: 95, // 单元格最小宽度
        cols: [[ // 表头
            {type: 'checkbox'},
            {type: "numbers"},
            {field: "goodsName", title: "商品名称", align: "center"},
             {field: "goodsNum", title: "商品数量", align: "center"},
            {field: "unit", title: "单位", align: "center"},
            {field: "price", title: "单价(￥)", align: "center"},
            {field: "sum", title: "总价(￥)", align: "center"},
            {field: "createDate", title: "创建时间", align: "center"},
            {field: "updateTime", title: "更新时间", align: "center"}
        ]]
    });

});