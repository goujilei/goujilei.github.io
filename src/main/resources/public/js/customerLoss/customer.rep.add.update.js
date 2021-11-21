layui.use(['form', 'layer'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        form = layui.form;

    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })


    form.on('submit(addOrUpdateCustomerRep)', function (data) {
        // 得到所有表单元素 的值
        var index = top.layer.msg("数据提交中，请稍后", {
            icon: 16, // 图标
            time: true,  // 不关闭
            shade: 0.8 // 设置遮罩透明度
        });
        var formData = data.field;
        var url = ctx + "/customer_rep/add";
        // 判断主键ID 是否为空 如果不为空则表示更新操作
        if ($("[name = 'id']").val()) {
            // 更新操作
            url = ctx + "/customer_rep/update";
        }

        $.post(url, formData, function (result) {
            // 判断 操作是否返回成功
            if (result.code == 200) {
                // 判断成功  提示
                top.layer.msg("操作成功", {icon: 6});
                // 关闭加载层
                top.layer.close(index);
                // 关闭弹出层
                layer.closeAll("iframe");
                //刷新父窗口   重新加载表格
                parent.location.reload();
            } else {
                // 失败
                layer.msg(result.msg, {icon: 5})
            }
        });
        return false;
    });

});