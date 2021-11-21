layui.use(['table', 'layer', 'form'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;


    var tableIns = table.render({
        elem: '#customerRepList',
        url: ctx + "/customer_rep/list?lossId=" + $("input[name='id']").val(),
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limit: 10,
        toolbar: "#toolbarDemo",
        cols: [[
            {type: "checkbox", fixed: "center"},
            {type: "numbers", title: "编号", fixed: "true"},
            {field: "measure", title: "暂缓措施", fixed: "true"},
            {field: "createDate", title: "创建时间", fixed: "true"},
            {field: "updateDate", title: "更新时间", fixed: "true"},
            {title: '操作', fixed: "right", align: "center", minWidth: 150, templet: "#customerRepListBar"}
        ]]

    });
    /**
     * 监听头部工具栏
     */
    table.on('toolbar(customerReps)', function (data) {

        if (data.event == 'add') {
            //打开添加或者   更新  的页面
            openAddOrUpdateCustomerDialog();
        } else if (data.event == 'confirm') {
            // 更新流失客户的流失状态
            updateCustomerLossState();
        }
    });

    /**
     * 监听行工具栏
     */
    table.on('tool(customerReps)', function (data) {
        if (data.event == "edit") {
            // 修改暂缓操作
            openAddOrUpdateCustomerDialog(data.data.id);
        } else if (data.event == 'del') {
            openDeleteCustomerDialog(data.data.id);
        }
    });

    function openAddOrUpdateCustomerDialog(id) {
        var title = "<h3>暂缓管理--- 添加暂缓 </h3>"
        var url = ctx + "/customer_rep/toAddOrUpdateCustomerPage?lossId=" + $("[name = 'id']").val();
        if (id != null && id != '') {
            title = "<h3>暂缓管理--- 更新暂缓</h3>";
            url += "&id=" + id;
        }
        layui.layer.open({
            type: 2,
            title: title,
            area: ['550px', '300px'],
            content: url,
            maxmin: true,
            shadeClose: true
        })
    }

    function openDeleteCustomerDialog(id) {
        layer.confirm("确认删除嘛", {icon: 5, title: "删除暂缓数据"}, function (index) {
            layer.close(index);
            $.ajax({
                type: "post",
                url: ctx + "/customer_rep/delete",
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
        })
    }

    //  更新客户流失状态
    function updateCustomerLossState() {
        layer.confirm("确认标记当前用户为流失用户嘛", {icon: 3, title: "客户流失管理"}, function (index) {
            layer.close(index);

            layer.prompt({title: "请输入流失原因", formType: 2}, function (text, index) {
                // 关闭输入框
                layer.close(index);
                // 发送请求到后台 更新指定客户的流失状态
                $.ajax({
                    type: "post",
                    url: ctx + "/customer_loss/updateCustomerLossStateById",
                    data: {
                        id: $("[name='id']").val(),
                        lossReason: text
                    },
                    dataType: "json",
                    success: function (result) {
                        if (result.code == 200) {
                            // 判断成功  提示
                            layer.msg("确认成功", {icon: 6});
                            // 关闭加载层
                            layer.close(index);
                            // 关闭弹出层
                            layer.closeAll("iframe");
                            //刷新父窗口   重新加载表格
                            parent.location.reload();
                        } else {
                            // 失败
                            layer.msg(result.msg, {icon: 5})
                        }
                    }
                })
            });
        })
    }

});