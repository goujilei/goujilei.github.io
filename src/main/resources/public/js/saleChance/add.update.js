layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;

    /**
     * 监听表单的submit 事件
     */
    form.on('submit(addOrUpdateSaleChance)', function (data) {
         var index = layer.msg("数据提交中，请稍后", {
            icon: 16, // 图标
            time: false,  // 不关闭
            shade: 0.8 // 设置遮罩透明度
        });
        //发送ajax 请求
        var url = ctx + '/sale_chance/add';
        // 通过id 判断 当前是添加操作还是修改操作
        var saleChanceId = $("[name = 'id']").val();
        if (saleChanceId != null && saleChanceId != '') {
            url = ctx + "/sale_chance/update"
        }


        $.post(url, data.field, function (result) {
            // 判断 操作是否返回成功
            if (result.code == 200) {
                // 判断成功  提示
                layer.msg("操作成功", {icon: 6});
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
        })
        return false;  // 阻止表单提交
    })


    /**
     * 在 iframe 页面关闭自身时  关闭自身弹出层
     */
    $("#closeBtn").click(function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    })
    /**
     * 加载下拉框
     */
    $.ajax({
        type: "get",
        url: ctx + "/user/queryAllSales",
        data: {},
        success: function (data) {
            // 判断返回的数据是否为空
            if (data != null) {
                // 获取隐藏域中的指派人id
                var assignManId = $("#assignManId").val();
                for (var i = 0; i < data.length; i++) {
                    // 设置下拉选项
                    // 如果 循环的到的id  等于 隐藏域 id  则表示相等  设置选中
                    var opt = "";
                    if (assignManId == data[i].id) {
                        opt = "<option value='" + data[i].id + "' selected>" + data[i].uname + "</option>";
                    } else {
                        var opt = "<option value='" + data[i].id + "'>" + data[i].uname + "</option>";
                        // 下拉项设置到 下拉框中
                    }
                    $("#assignMan").append(opt);
                }
            }
            //重新渲染下拉框
            layui.form.render("select");
        }
    })

})