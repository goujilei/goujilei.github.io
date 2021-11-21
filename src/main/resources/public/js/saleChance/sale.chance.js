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
        url: ctx + '/sale_chance/list',
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
            , {field: 'uname', title: '分配人', width: 135, sort: true}
            , {field: 'assignTime', title: '分配时间', width: 135, sort: true}
            , {field: 'createDate', title: '创建时间', width: 135, sort: true}
            , {
                field: 'devResult', title: '开发状态', width: 135, sort: true, templet: function (data) {
                    return formaterResult(data.devResult);
                }
            }
            , {
                field: 'state', title: '分配状态', templet: function (data) {
                    // 调用函数返回格式化的结果
                    return formaterState(data.state);
                }
            }
            , {field: 'updateTime', title: '更新时间', width: 135, sort: true}
            , {title: '操作', templet: '#listBar', fixed: 'right', minWidth: '150'}
        ]]
    });

    /**
     * 通过格式化状态值返回
     * @param state
     * 0 表示未分配 1 表示已分配  其他未知
     */
    function formaterState(state) {
        if (state == 0) {
            return "<div style='color: yellowgreen'>未分配</div>"
        } else if (state == 1) {
            return "<div style='color: green'>已分配</div>"
        } else {
            return "<div style='color: red'>未知</div>"
        }
    }

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
                state: $("#state").val(),
            }, page: {
                curr: 1
            }
        })
    });
    /**
     * 监听头部工具栏
     */
    table.on('toolbar(saleChances)', function (data) {
        if (data.event == 'add') {  // 做添加操作
            openSaleChanceDialog();
        } else if (data.event == 'del') {
            // 做删除操作
            deleteSaleChance(data);
        }

    })

    /*添加营销机会的数据接口
     * 如果id 为空 为 添加 否则 为修改
     */
    function openSaleChanceDialog(saleChanceId) {
        var title = "<h3>营销机会管理 --- 添加营销机会</h3>";
        var url = ctx + '/sale_chance/toSaleChancePage';
        if (saleChanceId != null && saleChanceId != '') {
            title = "<h3>营销机会管理 ---更新营销机会</h3>"
            url += '?saleChanceId=' + saleChanceId;
        }

        layui.layer.open({
            type: 2,
            title: title,
            area: ['500px', '620px'],
            content: url,
            shadeClose: true,//点击遮罩关闭层
            // 可以最大化 最小化
            maxmin: true
        });
    }

    table.on('tool(saleChances)', function (data) {
        if (data.event == 'edit') {
            // 编辑操作
            // 打开操作
            var saleChanceId = data.data.id;
            openSaleChanceDialog(saleChanceId);
        } else if (data.event == 'del') {
            // 删除操作
            // 弹出确认框  询问用户是否确认删除
            layer.confirm('确认删除该记录吗？', {icon: 3, title: "营销机会管理"}, function (index) {
                layer.close(index);
                // 发送ajax 请求
                $.ajax({
                    type: "post",
                    url: ctx + "/sale_chance/delete",
                    data: {
                        ids: data.data.id
                    }, success: function (result) {
                        if (result.code == 200) {
                            layer.msg("删除成功", {icon: 6});
                            tableIns.reload();
                        } else {
                            layer.msg("删除失败", {icon: 5})
                        }
                    }
                })
            })
            ;
        }

    });

    /**
     * 删除营销机会 删除多条记录
     */
    function deleteSaleChance(data) {
        var checkStatus = table.checkStatus("saleChanceList");
        // 先获取 所有的被选中的记录
        var saleChanceDate = checkStatus.data;
        if (saleChanceDate.length < 1) {
            layer.msg("未选中数据", {icon: 5});
            return
        }
        layer.confirm('确认删除选中的记录吗', {icon: 3, title: "营销机会管理"}, function (index) {
            //关闭确认框
            layer.close(index);
            // 传递参数为 数组
            var ids = "ids=";
            // 循环选中的记录
            for (var i = 0; i < saleChanceDate.length; i++) {
                if (i < saleChanceDate.length - 1) {
                    ids = ids + saleChanceDate[i].id + "&ids=";
                } else {
                    ids = ids + saleChanceDate[i].id;

                }
            }
             // 发送ajax
            $.ajax({
                type: "post",
                url: ctx + "/sale_chance/delete",
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
})