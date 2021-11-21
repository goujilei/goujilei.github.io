<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <#-- 用户ID -->
    <input name="cusId" type="hidden" value="${(cusId)!}"/>
    <input name="id" type="hidden" value="${(linkMan.id)!}"/>
    <input id="sex" type="hidden" value="${(linkMan.sex)!}"/>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   lay-verify="required" name="linkName" id="linkName" value="${(linkMan.linkName)!}"
                   placeholder="请输入联系人姓名">
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" id="man" name="sex" value="男" title="男" checked/>
            <input type="radio" id="woman" name="sex" value="女" title="女"/>
        </div>
    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">职位</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail"
                   lay-verify="required" name="zhiwei"
                   id="zhiwei" value="${(linkMan.zhiwei)!}"
                   placeholder="请输入职位">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">工作号码</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail" value="${(linkMan.officePhone)!}"
                   lay-verify="required" name="officePhone" id="officePhone" placeholder="请输入工作联系方式">
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">联系方式</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userEmail" value="${(linkMan.phone)!}"
                   lay-verify="required" name="phone" id="phone" placeholder="请输入个人联系方式">
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateLinkMan">确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>


<script type="text/javascript" src="${ctx}/js/customer/addLinkMan.js"></script>

 </body>
</html>