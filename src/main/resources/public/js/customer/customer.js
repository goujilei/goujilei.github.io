layui.use(['table', 'layer', 'layuimini', 'jquery', 'jquery_cookie'], function () {
    var $ = layui.jquery,
        table = layui.table,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    var tableIns = table.render({
        elem: "#customerList",
        url: ctx + "/customer/list",
        cellMinWidth: 95,
        height: "full-125",
        page: true,
        limit: 10,
        toolbar: "#toolbarDemo",
        cols: [[
            {type: "checkbox", fixed: "center"},
            {type: "numbers"},
            {field: "name", title: "客户姓名", align: "center"},
            {field: "fr", title: "法人", align: "center"},
            {field: "khno", title: "客户编号", align: "center"},
            {field: "area", title: "地区", align: "center"},
            {field: "cusManager", title: "客户经理", align: "center"},
            {field: "myd", title: "满意度", align: "center"},
            {field: "level", title: "客户级别", align: "center"},
            {field: "xyd", title: "信用度", align: "center"},
            {field: "address", title: "详细地址", align: "center"},
            {field: "postCode", title: "邮编", align: "center"},
            {field: "webSite", title: "网站", align: "center"},
            {field: "khzh", title: "开户账号", align: "center"},
            {field: "gsdjh", title: "国税", align: "center"},
            {field: "dsdjh", title: "地税", align: "center"},
            {field: "createDate", title: "创建时间", align: "center"},
            {field: "updateDate", title: "更新时间", align: "center"},
            {field: '操作', templet: '#customerListBar', fixed: "right", align: "center", minWidth: 150}
        ]]
    })

    /**
     *搜索功能
     */
    $(".search_btn").click(function () {
        tableIns.reload({
            where: {
                customerName: $("[name='customerName']").val(),
                no: $("[name='no']").val(),
                level: $("[name='level']").val(),
            }, page: {
                curr: 1
            }
        })
    });
    /**
     * 监听头部工具栏
     */
    table.on('toolbar(customers)', function (data) {
        // 判断layevent
        if (data.event == 'add') {
            // 添加客户信息
            openAddOrUpdateCustomerDialog();
        } else if (data.event == 'order') {
            // 客户订单数据查看
            var checkStatus = table.checkStatus(data.config.id)
            // 打开客户订单对话框 传递选中的数据记录
            openCustomerOrderDialog(checkStatus.data);
        } else if (data.event == 'link') {
            var checkStatus = table.checkStatus(data.config.id);
            // 打开联系人
            openOrderLinkDialog(checkStatus.data);
        }
    })

    function openAddOrUpdateCustomerDialog(id) {
        var title = "<h3>客户管理 --- 添加客户</h3>";
        var url = ctx + '/customer/toAddOrUpdateCustomerPage';
        if (id != null && id != '') {
            title = "<h3>客户管理 --- 更新客户</h3>";
            url = ctx + "/customer/toAddOrUpdateCustomerPage?id=" + id;
        }
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

    /**
     * 监听行工具栏
     */
    table.on('tool(customers)', function (data) {
        if (data.event == 'edit') {
            // 打开编辑
            openAddOrUpdateCustomerDialog(data.data.id);
        } else if (data.event == 'del') {
            // 删除客户信息
            openDeleteCustomerDialog(data.data.id);
        }
    })

    /**
     * 删除客户信息
     */
    function openDeleteCustomerDialog(id) {
        layer.confirm("确认删除选中的记录吗", {icon: 3, title: "客户管理"}, function (index) {
            // 关闭确认框
            layer.close(index);
            // 发送请求
            $.ajax({
                type: "post",
                url: ctx + "/customer/delete",
                data: {
                    id: id
                },
                success: function (result) {
                    if (result.code == 200) {
                        // 提示成功
                        layer.msg("删除成功", {icon: 6})
                        tableIns.reload();
                    } else {
                        layer.msg(result.msg, {icon: 5})
                    }
                }
            });
        });

    }

    /**
     * 打开指定客户的订单对话框
     * @param data
     */
    function openCustomerOrderDialog(data) {
        // 判断用户是否选择
        if (data.length == 0) {
            layer.msg("请选择查看的客户", {icon: 5})
            return;
        } else if (data.length > 1) {
            layer.msg("仅支持查看单个用户", {icon: 5})
            return;
        }

        layui.layer.open({
            type: 2,
            title: "<h3>客户管理---订单信息</h3>",
            area: ['700px', '500px'],
            content: ctx + "/customer/toOpenCustomerOrderPage?id=" + data[0].id,
            shadeClose: true,//点击遮罩关闭层
            // 可以最大化 最小化
            maxmin: true
        });
    }

    function openOrderLinkDialog(data) {
        // 判断用户是否选择
        if (data.length == 0) {
            layer.msg("请选择查看的客户", {icon: 5})
            return;
        } else if (data.length > 1) {
            layer.msg("仅支持查看单个用户", {icon: 5})
            return;
        }

        layui.layer.open({
            type: 2,
            title: "<h3>客户管理---联系人查看</h3>",
            area: ['700px', '500px'],
            content: ctx + "/customer/toLinkPage?id=" + data[0].id,
            shadeClose: true,//点击遮罩关闭层
            // 可以最大化 最小化
            maxmin: true
        });
    }
})
