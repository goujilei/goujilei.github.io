layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    form.on('submit(addModule)', function (data) {
        // 弹出loading
        var index = top.layer.msg("数据提交中，请稍后", {icon: 16, time: false, shade: 208});
        $.post(ctx + "/module/add", data.field, function (res) {
            if (res.code == 200) {
                setTimeout(function () {
                    top.layer.close(index);
                    top.layer.msg("操作成功");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                }, 500);
            } else {
                layer.msg(res.msg, {icon: 5})
            }
        })
        return false;
    });
    /**
     * 关闭弹出层
     */
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })

});