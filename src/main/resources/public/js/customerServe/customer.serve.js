layui.use(['table', 'form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        table = layui.table,
        $ = layui.jquery;


    table.render({
        elem: '#customerServeList',
        url: ctx + "/customer_serve/list",
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
            {field: "createPeople", title: "创建人", fixed: "true"},
            {field: "createDate", title: "创建时间", fixed: "true"},
            {field: "updateDate", title: "更新时间", fixed: "true"}
        ]]
    });

    $(".search_btn").on("click", function () {
        table.reload("customerServeList", {
            page: {
                curr: 1
            }, where: {
                customerName: $("input[name ='customer']").val(),
                serveType: $("#type").val() // 服务类型
            }
        })
    });
    table.on('toolbar(customerServes)', function (data) {

        if (data.event == 'add') {
            openAddCusterServe();
        }
    })

    function openAddCusterServe() {
        var title = "<h3>服务管理----创建服务</h3>"
        var url = ctx + "/customer_serve/openAddCusterServePage";
        layui.layer.open({
            type: 2,
            title: title,
            area: ['700px', '500px'],
            content: url,
            maxmin: true,
            shadeClose: true
        })
    }

});