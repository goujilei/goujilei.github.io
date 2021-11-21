layui.use(['form', 'layer', 'formSelects'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;
    var formSelects = layui.formSelects;

    /**
     * 在 iframe 页面关闭自身时  关闭自身弹出层
     */
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })
    /**
     * 表单添加 取消
     */
    form.on('submit(addOrUpdateUser)', function (data) {
        //  数据加载层
        var index = top.layer.msg("数据提交中，请稍后", {
            icon: 16, // 图标
            time: false,  // 不关闭
            shade: 0.8 // 设置遮罩透明度
        });
        var formData = data.field;
        var url = ctx + "/user/add";
        // 判断ID 是否为空
        var id = $("[name = 'id']").val()
        if (id != null && id != '') {
            var url = ctx + "/user/update";
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

    /**
     * 加载角色下拉框 多选框
     */
    var userId = $("[name ='id']").val();
    formSelects.config("selectId", {   // 下拉框id
        type: "post",
        searchUrl: ctx + "/role/queryAllRoles?userId=" + userId, // 请求地址
        keyName: "roleName",  // 下拉框的文本内容
        keyVal: "id"
    }, true)

});