layui.use(['form', 'jquery', 'jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    form.on('submit(login)', function (data) {
        console.log(data.field)
        $.ajax({
            type: "post",
            url: ctx + "/user/login",
            data: {
                username: data.field.username,
                password: data.field.password
            }
            , success: function (result) { //接收后端数据
                console.log(result)
                if (result.code == 200) {
                    // 登录成功 设置用户登录状态
                    // 1. 利用session 保存用户信息 如果 会话存在 用户登录状态 否则非登录状态 服务器关闭 会话失效
                    // 利用cookie   cookie 未失效 则 用户登录状态
                    // 判断是否 选中记住我
                    if ($("#rememberMe").prop("checked")) {
                        console.log("选中记住我")
                        $.cookie("userIdStr", result.result.userIdStr, {expires: 7});
                        $.cookie("username", result.result.username, {expires: 7});
                        $.cookie("trueName", result.result.trueName, {expires: 7});
                    } else {
                        // 将用户信息 设置到cookie中
                        $.cookie("userIdStr", result.result.userIdStr);
                        $.cookie("username", result.result.username);
                        $.cookie("trueName", result.result.trueName);

                        // 登陆成功后跳转到首页
                    }
                    window.location.href = ctx + "/main"
                } else {
                    layer.msg(result.msg, {icon: 5})
                }
            }

        });
        return false;
    });


});