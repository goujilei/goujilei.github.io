layui.use(['table', 'layer'], function () {
    var table = layui.table,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;


    var tableIns = table.render({
        elem: "#customerLinkList",
        url: ctx + "/customer_linkman/list?cusId=" + $("input[name='cusId']").val(),
        cellMinWidth: 95,
        height: "full-125",
        page: true,
        limit: 10,
        toolbar: "#toolbarDemo",
        cols: [[
            {type: "checkbox", fixed: "center"},
            {type: "numbers"},
            {field: "linkName", title: "联系人姓名", align: "center"},
            {field: "sex", title: "性别", align: "center"},
            {field: "zhiwei", title: "职位", align: "center"},
            {field: "officePhone", title: "工作号码", align: "center"},
            {field: "phone", title: "联系方式", align: "center"},
            {field: "createDate", title: "创建时间", align: "center"},
            {field: "updateDate", title: "修改时间", align: "center"},
            {title: "操作", templet: "#linkListBar", align: "center"}
        ]]
    })

    /**
     * 监听头部工具栏
     */
    table.on('toolbar(customerLinks)', function (data) {
        // 判断layevent
        if (data.event == 'add') {
            // 添加联系人
            openAddLinkManDialog();
        } else if (data.event == 'del') {
            // 删除操作
            deleteLinkManByIds();
        }
    })

    function openAddLinkManDialog(id) {
        var title = "<h3>客户管理---添加联系人</h3>"
        var url = ctx + "/customer_linkman/toAddOrUpdateLinkManPage?cusId=" + $("[name ='cusId']").val();
        if (id != null) {
            title = "<h3>客户管理---更新联系人</h3>"
            url += "&&id=" + id;
        }
        layui.layer.open({
            type: 2,
            title: title,
            area: ["650px", "400px"],
            content: url,
            shadeClose: true
        })

    };

    table.on('tool(customerLinks)', function (data) {
        if (data.event == 'edit') {
            openAddLinkManDialog(data.data.id);
        } else if (data.event == "del") {
            // 执行删除操作
            deleteLinkManById(data);
        }

    });

    function deleteLinkManByIds() {
        var checkStatus = table.checkStatus("customerLinkList");
        var deleteLinkManData = checkStatus.data;
        if (deleteLinkManData.length < 1) {
            layer.msg("请选中记录", {icon: 5})
            return;
        }
        layer.confirm('确定删除联系人嘛', {icon: 5, title: '联系人管理'}, function (index) {
            layer.close(index);
            var ids = "ids=";
            // 循环选中的记录
            for (var i = 0; i < deleteLinkManData.length; i++) {
                if (i < deleteLinkManData.length - 1) {
                    ids = ids + deleteLinkManData[i].id + "&ids=";
                } else {
                    ids = ids + deleteLinkManData[i].id;
                }
            }

            // 发送ajax
            $.ajax({
                type: "post",
                url: ctx + "/customer_linkman/deleteLinkMan",
                data: ids,
                success: function (result) {
                    if (result.code == 200) {
                        layer.msg("删除成功", {icon: 6});
                        tableIns.reload();
                    } else {
                        layer.msg("删除失败", {icon: 5})
                    }
                }
            })
        });
    }

    function deleteLinkManById(data) {
        layer.confirm('确定删除联系人嘛', {icon: 5, title: '联系人管理'}, function (index) {
            layer.close(index);
            $.ajax({
                    type: "post",
                    data: {
                        ids: data.data.id
                    },
                    url: ctx + "/customer_linkman/deleteLinkMan",
                    success: function (result) {
                        if (result.code == 200) {
                            layer.msg("删除成功", {icon: 6});
                            tableIns.reload();
                        } else {
                            layer.msg("删除失败", {icon: 5})
                        }
                    }
                }
            )
        })
    }
});