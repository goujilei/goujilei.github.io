layui.use(['table', 'form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        table = layui.table,
        $ = layui.jquery;


    table.render({
        elem: "#customerServeList",
        url: ctx + "/customer_serve/list?state=fw_003",
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
            {field: "assigner", title: "分配人", fixed: "true"},
            {field: "serviceProcePeople", title: "处理人", fixed: "true"},
            {field: "serviceProceTime", title: "处理时间", fixed: "true"},
            {field: "createDate", title: "创建时间", fixed: "true"},
            {field: "updateDate", title: "更新时间", fixed: "true"},
            {title: "操作", minWidth: 150, templet: "#customerServeListBar", fixed: "right", align: "center"}
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
    // 服务反馈类型
    table.on('tool(customerServes)', function (data) {
        if (data.event == 'back') {
            // 打开添加修改页面 信息对话框
            openCustomerServeFeedBackDialog(data.data.id);
        }

    })

    // 打开对话框
    function openCustomerServeFeedBackDialog(id) {
        var title = "<h3>服务管理--- 服务分配</h3>"
        var url = ctx + "/customer_serve/toCustomerServeFeedBackPage?id=" + id;
        layui.layer.open({
            type: 2,
            title: title,
            area: ['700px', '500px'],
            content: url,
            shadeClose: true,//点击遮罩关闭层
            // 可以最大化 最小化
            maxmin: true
        });
    }
});