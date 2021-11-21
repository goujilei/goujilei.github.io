layui.use(['table', 'layer' , 'treetable'], function () {
    var table = layui.table,
        layer = layui.layer,
        treeTable = layui.treetable;
        $ = layui.jquery;

    treeTable.render({
        treeColIndex: 1,
        treeSpid: -1,
        treeIdName: 'id',
        treePidName: 'parentId',
        elem: '#munu-table',
        url: ctx + "/module/list",
        toolbar: "#toolbarDemo",
        treeDefaultClose: true,
        page: true,
        cols: [[

            {type: 'numbers'},
            {field: 'moduleName', minWidth: 100, title: '菜单名称'},
            {field: 'optValue', title: '权限码', width: 80},
            {field: 'url', title: '菜单url'},
            {field: 'createDate', title: '创建时间'},
            {field: 'updateDate', title: '更新时间'},
            {
                field: 'grade', width: 80, align: 'center', templet: function (d) {
                    if (d.grade == 0) {
                        return '<span class="layui-badge layui-bg-blue">目录</span>'
                    }
                    if (d.grade == 1) {
                        return '<span class="layui-badge ">菜单</span>'
                    }
                    if (d.grade == 2) {
                        return '<span class="layui-badge layui-bg-gray">按钮</span>'
                    }
                }, title: '类型'

            },
            {templet: '#auth-state', width: 320, align: 'center', title: '操作'}
        ]],
        done: function () {
            layer.closeAll('loading')
        }
    });
    // 全部展开和全部折叠
    table.on('toolbar(munu-table)', function (data) {
        // 判断layevent
        if (data.event == 'expand') {
            // 全部展开
            treeTable.expandAll("#munu-table")
        } else if (data.event == 'fold') {
            treeTable.foldAll("#munu-table")
        } else if (data.event == 'add') {
            //添加目录 层级为0  父级菜单 -1
            openAddModuleDialog(0, -1);
        }
    })

    /**
     *  监听行工具栏
     */
    table.on('tool(munu-table)', function (data) {
        if (data.event == 'add') {
            // 添加子项
            //判断当前的层级 如果是三级菜单 则不能添加
            if (data.data.grade == 2) {
                layer.msg("暂不支持添加四级菜单", {icon: 5})
                return;
            } else {
                //一级菜单 或者是二级菜单   grade  当前层级 +1  parentId = 当前资源ID
                openAddModuleDialog(data.data.grade + 1, data.data.id);
            }
        } else if (data.event == 'edit') {// 修改资源
            openUpdateModuleDialog(data.data.id);
        } else if (data.event == 'del') {
            // 执行删除资源操作
            deleteModule(data.data.id);
        }
    })


    /**
     * 打开添加资源对话框
     * @param grade
     * @param parentId
     */
    function openAddModuleDialog(grade, parentId) {
        var title = "<h3>菜单添加 -- 添加资源 </h3>"
        var url = ctx + "/module/toAddModulePage?grade=" + grade + "&parentId=" + parentId;
        layui.layer.open({
            title: title,
            type: 2,
            area: ["700px", "450px"],
            maxmin: true,
            content: url,
            shadeClose: true
        })

    }

    /**
     * 打开修改资源页 面
     * @param id  主键ID
     */
    function openUpdateModuleDialog(id) {
        var title = "<h3>菜单添加 -- 更新资源 </h3>"
        var url = ctx + "/module/toUpdateModulePage?id=" + id;
        layui.layer.open({
            title: title,
            type: 2,
            area: ["700px", "450px"],
            maxmin: true,
            content: url,
            shadeClose: true
        })
    }

    /**
     * 删除资源 id
     * @param id
     */
    function deleteModule(id) {
        layer.confirm("确认删除记录吗?", {icon: 3, title: "资源管理"}, function (data) {
            // 确认删除发送ajax 请求
            $.post(ctx + "/module/delete", {id: id}, function (result) {
                // 判断是否成功
                if (result.code == 200) {
                    layer.msg("删除成功" ,{icon:6});
                    window.location.reload() ;
                } else {
                    layer.msg(result.msg, {icon: 5})
                }
            })
        })
    }
});