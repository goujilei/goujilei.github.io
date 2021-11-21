layui.use(['form', 'jquery', 'jquery_cookie', 'table'], function () {
    var form = layui.form,
        layer = layui.layer,
        table = layui.table,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);
    /**
     * 数据表格
     */
    var tableIns = table.render({
        elem: '#userList', //绑定table id
        height: 'full-125',
        url: ctx + '/user/list',
        page: true,
        cellMinWidth: 95, // 单元格最小宽度
        toolbar: '#toolbarDemo',
        cols: [[ // 表头
            {type: 'checkbox'},
            {type: "numbers"}
            , {field: 'userName', title: '用户名称', width: 177}
            , {field: 'trueName', title: '真实姓名', width: 177, sort: true}
            , {field: 'email', title: '邮箱', width: 177}
            , {field: 'phone', title: '手机号码', width: 177}
            , {field: 'createDate', title: '创建时间', width: 177, sort: true}
            , {field: 'updateDate', title: '更新时间', width: 177, sort: true}
            , {title: '操作', templet: '#userListBar', fixed: 'right', minWidth: '150'}
        ]]
    });
    /**
     *搜索功能
     */
    $(".search_btn").click(function () {
        tableIns.reload({
            where: {
                userName: $("[name='userName']").val(),
                phone: $("[name='phone']").val(),
                email: $("[name='email']").val(),
            }, page: {
                curr: 1
            }
        })
    });

    /**
     * 头部工具栏
     */
    table.on('toolbar(users)', function (data) {
        if (data.event == 'add') {            // 添加操作 打开添加或修改对话框
            openAddOrUpdateUserDialog();
        } else if (data.event == 'del') {
            // 删除操作
            //获取被选中的信息
            var checkStatus = table.checkStatus(data.config.id);
            deleteUsers(checkStatus.data);

        }
    });

    /**
     * 监听行工具栏
     */
    table.on('tool(users)', function (data) {
        if (data.event == 'edit') {
            // 添加操作 打开添加或修改对话框
            openAddOrUpdateUserDialog(data.data.id);
        } else if (data.event == 'del') {
            // 删除操作  删除单条记录
            deleteUser(data.data.id);
        }
    });

    function openAddOrUpdateUserDialog(id) {
        var title = "<h3> 用户管理-- 添加用户</h3>";
        var url = ctx + "/user/toAddOrUpdateUserPage";
        //  判断id 是否为空
        if (id != null && id != '') {
            title = "<h3> 用户管理-- 更新用户</h3>";
            url += "?id=" + id; // 主键
        }
        layui.layer.open({
            type: 2,
            title: title,
            area: ['650px', '400px'],
            content: url,
            shadeClose: true,//点击遮罩关闭层
            // 可以最大化 最小化
            maxmin: true
        });
    }

    function deleteUsers(data) {
        // 判断用户是否选择了要删除的记录la
        if (data.length == 0) {
            layer.msg("请选择要删除的记录", {icon: 5})
            return false;
        }
        // 询问用户是否删除
        layer.confirm("确认删除选中的记录吗", {icon: 3, title: "用户管理"}, function (index) {
            // 关闭确认框
            layer.close(index);
            // 传递数组
            var ids = "ids=";
            for (var i = 0; i < data.length; i++) {
                if (i < data.length - 1) {
                    ids = ids + data[i].id + "&ids=";
                } else {
                    ids = ids + data[i].id;
                }
            }
            // 发送请求
            $.ajax({
                type: "post",
                url: ctx + "/user/delete",
                data: ids,
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
        });
    }

    function deleteUser(id) {
        // 询问用户是否删除
        layer.confirm("确认删除选中的记录吗", {icon: 3, title: "用户管理"}, function (index) {
            // 关闭确认框
            layer.close(index);
            // 发送请求
            $.ajax({
                type: "post",
                url: ctx + "/user/delete",
                data: {
                    ids: id
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
        });
    }
})