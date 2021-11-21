<!DOCTYPE html>
<html>
<head>
    <title>客户订单查看</title>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<input type="hidden" name="cusId" value="${(cusId)!}">

<div class="layui-col-md12">
    <table id="customerLinkList" class="layui-table" lay-filter="customerLinks"></table>
</div>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
            <i class="layui-icon">&#xe608;</i>
            添加联系人
        </a>
        <a class="layui-btn layui-btn-normal addNews_btn" lay-event="del">
            <i class="layui-icon">&#xe608;</i>
            删除联系人
        </a>

    </div>
</script>

<script id="linkListBar" type="text/html">
    <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
</script>

<script type="text/javascript" src="${ctx}/js/customer/customer.link.js"></script>
</body>
</html>