layui.use(['form', 'jquery', 'jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);
    /**
     * 表单的submit 监听
     */
    form.on('submit(saveBtn)', function (data) {
        console.log(data.field)
        /**
         * ajax 提交表单数据
         */
        $.ajax({
            type: "post",
            url: ctx + "/user/updatePwd",
            data: {
                oldPassword: data.field.oldPassword,
                newPassword: data.field.newPassword,
                repeatPassword: data.field.repeatPassword
            },
            success: function (result) {
                // 判断是否修改成功
                if (result.code == 200) {
                    // 修改密码成功后， 清空cookie  跳转到登录页面
                    layer.msg("用户密码修改成功 ， 系统将在3 秒后退出", function () {
                        $.removeCookie("userIdStr", {domain: "localhost", path: "/crm"})
                        $.removeCookie("username", {domain: "localhost", path: "/crm"})
                        $.removeCookie("trueName", {domain: "localhost", path: "/crm"})
                        // 跳转到登录页面
                        window.parent.location.href = ctx + "/index";
                    })
                } else {
                    layer.msg(result.msg, {icon: 5})
                }
            }
        })


    })
})