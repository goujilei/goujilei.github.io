layui.use(['table', 'layer', 'form'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    //客户流失列表展示
    var tableIns = table.render({
        elem: '#customerLossList',
        url: ctx + "/customer_loss/list",
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limit: 10,
        toolbar: "#toolbarDemo",
        cols: [[
            {type: "checkbox", fixed: "center"},
            {type: "numbers", title: "编号", fixed: "true"},
            {field: "cusNo", title: "客户编号", fixed: "true"},
            {field: "cusName", title: "客户名称", fixed: "true"},
            {field: "cusManager", title: "客户经理", fixed: "true"},
            {field: "lastOrderTime", title: "最后下单时间", fixed: "true"},
            {field: "lossReason", title: "流失原因", fixed: "true"},
            {field: "confirmLossTime", title: "确认流失时间", fixed: "true"},
            {field: "createDate", title: "创建时间", fixed: "true"},
            {field: "updateDate", title: "更新时间", fixed: "true"},
            {title: "操作", fixed: "true", align: "center", minWidth: 150, templet: "#op"}
        ]]
    });

    /**
     *搜索功能
     */
    $(".search_btn").click(function () {
        tableIns.reload({
            where: {
                customerName: $("[name='cusName']").val(),
                no: $("[name='cusNo']").val(),
                state: $("#state").val(),
            }, page: {
                curr: 1
            }
        })
    });

    /**
     * 监听行工具栏
     */
    table.on('tool(customerLosses)', function (data) {
        if (data.event == "add") {
            // 执行添加暂缓
            openCustomerLossDialog("<h3>客户流失 --- 暂缓管理</h3>", data.data.id);
        } else if (data.event == "info") {
            //执行 打开详情页面
            openCustomerLossDialog("<h3>客户流失 --- 暂缓详情</h3>", data.data.id);
        }

        /**
         * 打开添加暂缓 或者详情页面
         * @param s
         * @param id
         */
        function openCustomerLossDialog(s, id) {
            layui.layer.open({
                type: 2,
                title: s,
                area: ['700px', '400px'],
                content: ctx + "/customer_loss/toCustomerLossPage?id=" + id,
                maxmin: true
            })

        }
    });


});