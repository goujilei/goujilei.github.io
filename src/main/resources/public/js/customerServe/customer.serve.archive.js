layui.use(['table', 'jquery', 'layer'], function () {
    var $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        table = layui.table;


    //表格渲染
    table.render({
        elem: "#customerServeList",
        url: ctx + "/customer_serve/list?state=fw_005",
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limit: 10,
        toolbar: "#toolbarDemo",
        cols: [[
            {type: "checkbox", fixed: "center"},
            {type: "numbers", title: "编号", fixed: "true"},
            {field: "customer", title: "客户名", fixed: "true"},
            {field: "dicValue", title: "服务类型", fixed: "true"},
            {field: "overview", title: "概要信息", fixed: "true"},
            {field: "assignTime", title: "分配时间", fixed: "true"},
            {field: "createPeople", title: "创建人", fixed: "true"},
            {field: "serviceProcePeople", title: "处理人", fixed: "true"},
            {field: "serviceProce", title: "处理内容", fixed: "true"},
            {field: "serviceProceTime", title: "处理时间", fixed: "true"},
            {field: "serviceProceResult", title: "处理结果", fixed: "true"},
            {field: "myd", title: "满意度", fixed: "true"},
            {field: "createDate", title: "创建时间", fixed: "true"},
            {field: "updateDate", title: "更新时间", fixed: "true"},
        ]]
    });

    //多条件搜索
    $(".search_btn").on("click", function () {
        table.reload("customerServeList", {
            page: {
                curr: 1
            },
            where: {
                customerName: $("input[name ='customer']").val(),
                serveType: $("#type").val()
            }
        })
    });
});