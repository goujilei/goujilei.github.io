package com.software.crm.controller;

import com.software.crm.base.BaseController;
import com.software.crm.base.ResultInfo;
import com.software.crm.bean.Module;
import com.software.crm.model.TreeModel;
import com.software.crm.service.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("module")
@Controller
public class ModuleController extends BaseController {
    @Resource
    private ModuleService moduleService;

    @RequestMapping("queryAllModules")
    @ResponseBody
    public List<TreeModel> queryAllModules(Integer roleId) {
        return moduleService.queryAllModules(roleId);
    }

    @RequestMapping("toAddGrandPage")
    public String toAddGrandPage(Integer roleId, HttpServletRequest request) {
        // 将 需要授权的角色放入 request 请求域
        request.setAttribute("roleId", roleId);
        return "role/grant";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryAllModuleList() {
        return moduleService.queryAllModuleList();
    }

    @RequestMapping("index")
    public String index() {
        return "module/module";
    }

    /**
     * @return : json
     * @author :wyanjia
     * @parm :  添加资源
     * @date : 2021/10/13 19:51
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo add(Module module) {
        moduleService.addModule(module);
        return success("添加资源成功");
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm :grade 层级   parentId 父级ID
     * @date : 2021/10/14 14:06
     */
    @RequestMapping("toAddModulePage")
    public String toAddModulePage(Integer grade, Integer parentId, HttpServletRequest request) {
        request.setAttribute("grade", grade);
        request.setAttribute("parentId", parentId);
        return "module/add";
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 修改资源
     * @date : 2021/10/14 15:31
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateModule(Module module) {
        moduleService.updateModule(module);
        return success("更新成功");
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 打开修改资源页面
     * @date : 2021/10/14 15:40
     */
    @RequestMapping("toUpdateModulePage")
    public String toUpdateModulePage(Integer id, HttpServletRequest request) {
        // 将需要修改的资源对象设置到请求域中
        request.setAttribute("module", moduleService.selectByPrimaryKey(id));
        return "module/update";
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 删除资源
     * @date : 2021/10/14 16:19
     */

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer id) {
        moduleService.deleteModule(id);
        return success("删除成功");
    }
}
