layui.use(['form', 'layer', 'table', 'laydate'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        laydate = layui.laydate;
//设置时间
    laydate.render({
        elem: "#time"
    })

    var tableIns = table.render({
        elem: "#contriList",
        url: ctx + "/customer/queryCustomerContributionByParams",
        cellMinWidth: 95,
        height: "full-125",
        page: true,
        limit: 10,
        cols: [[
            {type: "checkbox", fixed: "center", width: 50},
            {type: "numbers"},
            {field: "name", title: "客户名", align: "center", minWidth: 50},
            {field: "total", title: "总金额", align: "center", minWidth: 50}

        ]]
    })

    //多条件查询
    $(".search_btn").on("click", function () {
        table.reload("contriList", {
            page: {
                curr: 1
            },
            where: {
                customerName: $("input[name='customerName']").val(),
                type: $("input[name='cusNo']").val()
            }
        })
    })

});