layui.use(['table', 'layer'], function () {
    var table = layui.table,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    /**
     * 订单列表展示
     */
    var tableIns = table.render({
        elem: '#customerOrderList', //绑定table id
        height: 'full-125',
        url: ctx + '/order/list?cusId=' + $("input[name='id'] ").val(),
        page: true,
        cellMinWidth: 95, // 单元格最小宽度
        toolbar: '#toolbarDemo',
        cols: [[ // 表头
            {type: 'checkbox'},
            {type: "numbers"},
            {field: "orderNo", title: "订单编号", align: "center"},
            {field: "orderDate", title: "下单日期", align: "center"},
            {field: "address", title: "收货地址", align: "center"},
            {
                field: "state", title: "支付状态", align: "center", templet: function (d) {
                    if (d.state == 1) {
                        return '已支付';
                    } else {
                        return '未支付';
                    }
                }
            },
            {field: "createDate", title: "创建日期", align: "center"},
            {field: "updateDate", title: "更新日期", align: "center"},
            {title: '操作', fixed: 'right', align: 'center', minWidth: 150, templet: "#customerOrderListBar"}
        ]]
    });

    /**
     * 监听行工具栏
     */
    table.on('tool(customerOrders)', function (data) {
        if (data.event == 'info') {
            // 打开订单详情页面
            var title = "<h3>客户管理 ---- 查看订单详情</h3>";
            var url = ctx + "/order/toOrderDetailPage?orderId=" + data.data.id;
            layui.layer.open({
                type: 2,
                title: title,
                area: ['700px', '400px'],
                content: url,
                maxmin: true
            })
        }
    });
});