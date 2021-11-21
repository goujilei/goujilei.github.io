$(function () {
    //  加载树形结构
    loadModuleData();
})
var zTreeObj;

/**
 * 加载 树形资源
 */
function loadModuleData() {
    // zTree 的参数配置
    var setting = {
        // 使用复选框
        check: {
            enable: true
        },
        // 使用简单的 json 数据
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            // 当 checkBox 选中 和取消时 触发的函数
            onCheck: zTreeOnCheck
        }

    }
    // 配置数据  通过ajax  查询数据
    // 查询所有的资源列表时 传递一个角色id 查询当前角色的对应的已经授权的资源
    $.ajax({
        type: "get",
        url: ctx + "/module/queryAllModules?roleId=" + $("[name = 'roleId']").val(),
        dataType: "json",

        success: function (data) {
            // 此处 data  为返回的json 数据  加载zTree 树形插件
            zTreeObj = $.fn.zTree.init($("#test1"), setting, data);
        }
    })
}

function zTreeOnCheck(event, treeId, treeNode) {
    // getCheckedNodes(checked) 获取所有被勾选的节点集合
    // 如果check== true  表示获取勾选节点 如何等于false 表示获取未勾选的节点。
    var nodes = zTreeObj.getCheckedNodes(true);
    // 判断并遍历 选中的节点集合
    if (nodes.length > 0) {
        // 遍历节点集合  获取资源ID
        // 定义资源ID
        var mIds = "mIds=";
        for (var i = 0; i < nodes.length; i++) {
            if (i < nodes.length - 1) {
                mIds += nodes[i].id + "&mIds=";
            } else {
                mIds += nodes[i].id;
            }
        }
    }

    // 获取 角色ID 的值  from 隐藏域
    var roleId = $("[name = 'roleId']").val();
    // 发送ajax 请求
    $.ajax({
        type: "post",
        url: ctx + "/role/addGrant",
        data: mIds + "&roleId=" + roleId,
        dataType: "json",
        success: function (data) {
            console.log(data)
        }
    })


}
