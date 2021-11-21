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
        elem: '#saleChanceList', //绑定table id
        height: 'full-125',
        url: ctx + '/sale_chance/list?flag=1',
        page: true,
        cellMinWidth: 95, // 单元格最小宽度
        toolbar: '#toolbarDemo',
        cols: [[ // 表头
            {type: 'checkbox'},
            {type: "numbers"}
            , {field: 'changeSource', title: '机会来源', width: 80}
            , {field: 'customerName', title: '客户姓名', width: 80, sort: true}
            , {field: 'cgji', title: '成功几率', width: 80}
            , {field: 'overview', title: '概要', width: 177}
            , {field: 'linkMan', title: '联系人', width: 80, sort: true}
            , {field: 'linkPhone', title: '联系方式', width: 80, sort: true}
            , {field: 'description', title: '描述', sort: true}
            , {field: 'createMan', title: '创建人', width: 80}
            , {field: 'createDate', title: '创建时间', width: 135, sort: true}
            , {
                field: 'devResult', title: '开发状态', width: 135, sort: true, templet: function (data) {
                    return formaterResult(data.devResult);
                }
            }

            , {field: 'updateTime', title: '更新时间', width: 135, sort: true}
            , {title: '操作', templet: '#op', fixed: 'right', minWidth: '150'}
        ]]
    });

    function formaterResult(devResult) {
        // 格式化开发状态
        if (devResult == 0) {
            return "<div style='color: yellowgreen'>未开发</div>"
        } else if (devResult == 1) {
            return "<div style='color: orange'>开发中</div>"
        } else if (devResult == 2) {
            return "<div style='color: green'>开发成功</div>"
        } else if (devResult == 3) {
            return "<div style='color: red'>开发失败</div>"
        } else {
            return "<div style='color: blue'>未知</div>"
        }
    }

    /**
     * 表格重载 实现多条件查询
     */
    $(".search_btn").click(function () {
        tableIns.reload({
            //
            where: {
                customerName: $("[name='customerName']").val(),
                createMan: $("[name='createMan']").val(),
                devResult: $("#devResult").val(),
            }, page: {
                curr: 1
            }
        })
    });
    /**
     * 详情按钮  绑定行工具栏
     */
    table.on('tool(saleChances)', function (data) {

        if (data.event == 'dev') {
            // 打开 开发 功能
            openCusDevPlanDialog('计划项数据开发', data.data.id);
        } else if (data.event == 'info') {
            // 打开详情
            openCusDevPlanDialog('计划项数据维护', data.data.id);
        }

        /**
         * 打开计划项开发 或详情页面
         * @param title
         * @param id
         */
        function openCusDevPlanDialog(title, id) {
            layui.layer.open({
                type: 2,
                title: title,
                area: ['750px', '550px'],
                content: ctx +"/cus_dev_plan/toCusDevPlanPage?id="+id,
                shadeClose: true,//点击遮罩关闭层
                // 可以最大化 最小化
                maxmin: true
            });
        }
    })

})
