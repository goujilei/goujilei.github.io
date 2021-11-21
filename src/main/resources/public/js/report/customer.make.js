layui.use(['layer', 'echarts'], function () {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        echarts = layui.echarts,
        $ = layui.jquery;
    $.ajax({
        type: "get",
        url: ctx + "/customer/countCustomerMake",
        dataType: "json",
        success: function (data) {
            var myChart = echarts.init(document.getElementById("make"));
            var option = {
                title: {
                    text: "客户构成分析"
                },
                // 提示框 .
                tooltip: {},
                //x轴
                xAxis: {
                    type: 'category',
                    data: data.datax
                },
                yAxis: {
                    type: "value",
                },
                // 系列
                series: [{
                    data: data.datay,
                    type: 'line'
                }]
            };
            myChart.setOption(option);
        }
    });

    //发送ajax 请求饼状图
    $.ajax({
        type: "get",
        url: ctx + "/customer/countCustomerMakePie",
        dataType: "json",
        success: function (data) {
            var myChart = echarts.init(document.getElementById("make02"));
            var option = {
                title: {
                    text: '客户分析',
                    subtext: '来自CRM',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)'
                },
                legend: {
                    left: 'center',
                    top: 'bottom',
                    data: data.datax
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                series: [
                    {
                        name: 'Radius Mode',
                        type: 'pie',
                        radius: [20, 140],
                        center: ['25%', '50%'],
                        roseType: 'radius',
                        itemStyle: {
                            borderRadius: 5
                        },
                        label: {
                            show: false
                        },
                        emphasis: {
                            label: {
                                show: true
                            }
                        },
                        data: data.datay
                    },
                    {
                        name: 'Area Mode',
                        type: 'pie',
                        radius: [20, 140],
                        center: ['75%', '50%'],
                        roseType: 'area',
                        itemStyle: {
                            borderRadius: 5
                        },
                        data: data.datay
                    }
                ]
            };
            myChart.setOption(option);
        }

    })
});