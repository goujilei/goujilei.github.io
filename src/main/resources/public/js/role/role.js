layui.use(['table', 'layer', 'layuimini', 'jquery', 'jquery_cookie'], function () {
    var $ = layui.jquery,
        table = layui.table,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    /**
     * 数据表格
     */
    var tableIns = table.render({
        elem: '#roleList', //绑定table id
        height: 'full-125',
        url: ctx + '/role/list',
        page: true,
        cellMinWidth: 95, // 单元格最小宽度
        toolbar: '#toolbarDemo',
        cols: [[ // 表头
            {type: 'checkbox'},
            {type: "numbers", title: '编号'}
            , {field: 'roleName', title: '角色名称', width: 177}
            , {field: 'roleRemark', title: '备注', width: 177, sort: true}
            , {field: 'createDate', title: '创建时间', width: 177, sort: true}
            , {field: 'updateDate', title: '更新时间', width: 177, sort: true}
            , {title: '操作', templet: '#roleListBar', fixed: 'right', minWidth: '150'}
        ]]
    });

    /**
     *搜索功能
     */
    $(".search_btn").click(function () {
        tableIns.reload({
            where: {
                roleName: $("[name='roleName']").val(),
            }, page: {
                curr: 1
            }
        })
    });

    /*
     头部工具栏
     */
    table.on('toolbar(roles)', function (data) {


        if (data.event == 'add') {// 执行添加操作
            //打开添加窗孔
            openAddOrUpDateRoleDialog();
        } else if (data.event == 'grant') {
            //  授权操作
            var checkStatus = table.checkStatus(data.config.id);
            // 打开一个授权的对话框
            openAddGrantDialog(checkStatus.data);
        }
    });

    /**
     * 打开或者更新对话框
     */
    function openAddOrUpDateRoleDialog(roleId) {
        var title = "<h3>角色管理--- 角色添加</h3>";
        var url = ctx + "/role/toAddOrUpdateRolePage";
        if (roleId != null && roleId != '') {
            title = "<h3>角色管理--- 角色更新</h3>";
            url += "?roleId=" + roleId;
        }
        layui.layer.open({
            type: 2,
            title: title,
            area: ['600px', '350px'],
            content: url,
            shadeClose: true,//点击遮罩关闭层
            // 可以最大化 最小化
            maxmin: true
        });
    }

    /*
    行工具栏
    */
    table.on('tool(roles)', function (data) {
        if (data.event == 'edit') {// 执行编辑操作
            //打开编辑页面
            openAddOrUpDateRoleDialog(data.data.id);
        } else if (data.event == 'del') { // 执行删除
            deleteRole(data.data.id);
        }
    });

    // 删除
    function deleteRole(roleId) {
        layer.confirm("确认删除记录吗", {icon: 3, title: "角色管理"}, function (index) {
            layer.close(index);

            $.ajax({
                type: "post",
                url: ctx + "/role/delete",
                data: {
                    roleId: roleId
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
            })
        })
    }

    /*
      打开授权页面
     */
    function openAddGrantDialog(data) {
        // 判断是否选择了角色记录
        if (data.length == 0) {
            layer.msg("请选择需要授权的角色", {icon: 6})
            return;
        }
        // 只支持单个角色授权
        if (data.length > 1) {
            layer.msg("暂不支持批量授权", {icon: 6})
            return;
        }

        var url = ctx + "/module/toAddGrandPage?roleId=" + data[0].id;
        var title = "<h3>角色管理 ---- 角色授权</h3>";
        layui.layer.open({
            title: title,
            content: url,
            type: 2,
            area: ["600px", "500px"],
            maxmin: true

        }) ;
    }
});
