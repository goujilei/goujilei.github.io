layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;
    /*
     * 关闭弹出层
     */
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })
    /**
     * 表单添加 取消
     */
    form.on('submit(addOrUpdateRole)', function (data) {
        //  数据加载层
        var index = top.layer.msg("数据提交中，请稍后", {
            icon: 16, // 图标
            time: true,  // 不关闭
            shade: 0.8 // 设置遮罩透明度
        });
        var formData = data.field;
        var url = ctx + "/role/add";
        // 判断隐藏域中是否存在id
        var roleId = $("[name='id']").val();
        if (roleId != null && roleId != '') {
            url = ctx + "/role/update";
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