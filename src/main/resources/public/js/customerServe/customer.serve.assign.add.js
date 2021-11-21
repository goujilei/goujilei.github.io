layui.use(['table', 'form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        table = layui.table,
        $ = layui.jquery;

    /**
     * 表单添加 取消
     */
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });

    form.on('submit(addOrUpdateCustomerServe)', function (data) {
        //  数据加载层
        var index = top.layer.msg("数据提交中，请稍后", {
            icon: 16, // 图标
            time: true,  // 不关闭
            shade: 0.8 // 设置遮罩透明度
        });

        var formData = data.field;
        var url = ctx + "/customer_serve/update";
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

    /**
     * 加载下拉框
     */
    $.ajax({
        type: "get",
        url: ctx + "/user/queryAllCustomerManager",
        data: {},
        success: function (data) {
            // 判断返回的数据是否为空
            if (data != null) {
                for (var i = 0; i < data.length; i++) {
                    // 设置下拉选项
                    // 如果 循环的到的id  等于 隐藏域 id  则表示相等  设置选中
                    var opt = "<option value='" + data[i].id + "'>" + data[i].uname + "</option>";

                    $("#assigner").append(opt);
                }
            }
            //重新渲染下拉框
            layui.form.render("select");
        }
    })
});