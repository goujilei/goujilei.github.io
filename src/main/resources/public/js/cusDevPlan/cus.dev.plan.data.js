layui.use(['table', 'layer', 'layuimini', 'jquery', 'jquery_cookie'], function () {
    var $ = layui.jquery,
        table = layui.table,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);

    /**
     * 加载数据表格
     */
    var tableIns = table.render({

        elem: '#cusDevPlanList', //绑定table id
        height: 'full-125',
        url: ctx + '/cus_dev_plan/list?saleChanceId=' + $("[name='id']").val(),
        page: true,
        cellMinWidth: 95, // 单元格最小宽度
        toolbar: '#toolbarDemo',
        cols: [[
            {type: 'checkbox'},
            {type: "numbers"}
            , {field: 'planItem', title: '计划项', width: 80}
            , {field: 'planDate', title: '计划时间', width: 80, sort: true}
            , {field: 'exeAffect', title: '执行效果', width: 80}
            , {field: 'createDate', title: '创建时间', width: 135, sort: true}
            , {field: 'updateTime', title: '修改时间', width: 135, sort: true}
            , {title: '操作', templet: '#cusDevPlanListBar', fixed: 'right', minWidth: '150'}
        ]]
    });
    /**
     *监听头部工具栏
     */
    table.on('toolbar(cusDevPlans)', function (data) {


        if (data.event == 'add') {
            // 添加计划项
            openAddOrUpdateCusDevPlan();
        } else if (data.event == 'success') {
            // 添加成功
            // 更新营销机会 的开发状态
            updateSaleChanceDevResult(2);

        } else if (data.event == 'failed') {
            // 开发失败
            updateSaleChanceDevResult(3);

        }
    });

    /*
    打开 添加计划项
     */
    function openAddOrUpdateCusDevPlan(id) {

        var title = '计划项管理--- 添加计划项';
        var url = ctx + "/cus_dev_plan/toAddOrUpdateCusDevPlanPage?sId=" + $("[name = 'id']").val();
        // 判断计划项ID 是否为空 为空表示i添加 不为空表示修改
        if (id != null && id != '') {
            title = '计划项管理--- 更新计划项'
            url += "&id=" + id;
        }
        layui.layer.open({
            type: 2,
            title: title,
            area: ['500px', '300px'],
            content: url,
            shadeClose: true,//点击遮罩关闭层
            // 可以最大化 最小化
            maxmin: true
        });
    }

    // 监听行工具栏
    table.on('tool(cusDevPlans)', function (data) {


        if (data.event == 'edit') {
            // 监听行工具栏 打开窗口
            openAddOrUpdateCusDevPlan(data.data.id);
        } else if (data.event == 'del') {
            // 删除计划项
            deleteCusDevPlan(data.data.id);
        }
    });

    function deleteCusDevPlan(id) {
        // 弹出确认框 询问用户确认删除
        layer.confirm("确认删除吗？", {icon: 3, title: "开发项数据管理"}, function (index) {
            // 发送ajax 请求
            $.post(ctx + '/cus_dev_plan/delete', {id: id}, function (result) {
                if (result.code == 200) {
                    // 提示成功
                    layer.msg("删除成功", {icon: 6});
                    tableIns.reload();
                } else {
                    layer.msg(result.msg, {icon: 5})
                }
            });
        });
    }

    /*
       更新营销机会的状态
     */

    function updateSaleChanceDevResult(devResult) {
        //弹出确认框 询问用户
        layer.confirm("确认执行该操作吗", {icon: 3, title: "营销机会管理"}, function (index) {
            // 通过隐藏域获取ID
            var sId = $("[name = 'id']").val();
            $.post(ctx + "/sale_chance/updateSaleChanceResult", {id: sId, devResult: devResult}, function (result) {
                if (result.code == 200) {
                    layer.msg("更新成功", {icon: 6});
                    layer.closeAll("iframe");
                    parent.location.reload();
                } else {
                    layer.msg(result.msg, {icon: 5})
                }
            })
        })
    }
});