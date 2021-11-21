layui.use(['table', 'layer', 'form', 'jquery', 'jquery_cookie'], function () {
    var $ = layui.jquery,
        table = layui.table,
        form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })

    form.on('submit(addOrUpdateCusDevPlan)', function (data) {
        // 得到所有表单元素 的值
        var index = top.layer.msg("数据提交中，请稍后", {
            icon: 16, // 图标
            time: false,  // 不关闭
            shade: 0.8 // 设置遮罩透明度
        });
        var formData = data.field;
        var url = ctx + "/cus_dev_plan/add";
        // 判断主键是否为空不为空表示 更新 不为空 表示更新
        if ($('[name="id"]').val()) {
            url = ctx + "/cus_dev_plan/update"
        }
        $.post(url, formData, function (result) {
            console.log(formData)
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


})