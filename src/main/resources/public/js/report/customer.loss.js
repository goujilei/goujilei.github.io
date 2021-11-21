layui.use(['form', 'layer', 'table'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    var tableIns = table.render({
        elem: "#customerLossList",
        url: ctx + "/customer_loss/list?state=1",
        cellMinWidth: 95,
        height: "full-125",
        page: true,
        limit: 10,
        cols: [[
            {type: "checkbox", fixed: "center", width: 50},
            {type: "numbers"},
            {field: "cusNo", title: "客户编号", align: "center"},
            {field: "cusName", title: "客户名称", align: "center"},
            {field: "cusManager", title: "客户经理", align: "center"},
            {field: "lastOrderDate", title: "最后下单时间", align: "center"},
            {field: "lossReason", title: "流失原因", align: "center"},
            {field: "confirmLossTime", title: "确认流失时间", align: "center"}
        ]]
    })

    //多条件查询
    $(".search_btn").on("click", function () {
        table.reload("customerLossList", {
            page: {
                curr: 1
            },
            where: {
                customerName: $("input[name='cusName']").val(),
                customerNo: $("input[name='cusNo']").val()
            }
        })
    })
});