package com.software.crm.controller;

import com.software.crm.base.BaseController;
import com.software.crm.base.ResultInfo;
import com.software.crm.bean.Role;
import com.software.crm.query.RoleQuery;
import com.software.crm.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
    @Resource
    private RoleService roleService;

    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String, Object>> queryAllRoles(Integer userId) {
        return roleService.queryAllRoles(userId);
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 分页条件查询角色列表
     * @date : 2021/10/12 17:42
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(RoleQuery roleQuery) {
        return roleService.queryByParamsForTable(roleQuery);
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 进入角色管理页面
     * @date : 2021/10/12 17:46
     */
    @RequestMapping("index")
    public String index() {
        return "role/role";
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addRole(Role role) {
        roleService.addRole(role);
        return success("角色添加成功");
    }

    @RequestMapping("toAddOrUpdateRolePage")
    public String toAddOrUpdateRolePage(Integer roleId, HttpServletRequest request) {
        if (roleId != null) {
            Role role = roleService.selectByPrimaryKey(roleId);
            request.setAttribute("roleInfo", role);
        }
        return "role/add_update";
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateRole(Role role) {
        roleService.updateRole(role);
        return success("角色添加成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteRole(Integer roleId) {
        roleService.deleteRole(roleId);
        return success("角色删除成功");
    }
    /**
     * @author  :wyanjia
     * @parm    : 添加角色授权信息
     * @date    : 2021/10/13 11:38
     * @return  :
     * */
    @RequestMapping("addGrant")
    @ResponseBody
    public ResultInfo addGrant(Integer roleId, Integer[] mIds) {
             roleService.addGrant(roleId , mIds);
        return success("添加授权信息成功");
    }
}