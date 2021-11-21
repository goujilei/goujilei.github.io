package com.software.crm.controller;

import com.software.crm.annoation.RequiredPermission;
import com.software.crm.base.BaseController;
import com.software.crm.base.ResultInfo;
import com.software.crm.bean.SaleChance;
import com.software.crm.enums.StateStatus;
import com.software.crm.query.SaleChanceQuery;
import com.software.crm.service.SaleChanceService;
import com.software.crm.utils.CookieUtil;
import com.software.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("sale_chance")
public class SaleChanceController extends BaseController {
    @Resource
    private SaleChanceService saleChanceService;


    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> querySaleChanceByParams(SaleChanceQuery saleChanceQuery, Integer flag, HttpServletRequest request) {
        // 判断 是 客户开发计划 还是营销机会  如果flag 不为空且 值 为 1  则 当前查询 客户开发计划
        if (flag != null && flag == 1) {
            // 查询的客户开发机会
            // 设置分配状态
            saleChanceQuery.setState(StateStatus.STATED.getType());
            // 当前登录用户的id
            Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
            saleChanceQuery.setAssignMan(userId);
        }
        return saleChanceService.querySaleChanceByParams(saleChanceQuery);
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 进入营销机会管理页面  权限码1010
     * @date : 2021/10/1 10:33
     */
    @RequestMapping("index")
    public String index() {
        return "saleChance/sale_chance";
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 添加 营销机会
     * @date : 2021/10/1 22:04
     */
    @RequiredPermission(code = "101002")
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addSaleChance(SaleChance saleChance, HttpServletRequest request) {
        // 获取 当前用户名
        String username = CookieUtil.getCookieValue(request, "username");
        saleChance.setCreateMan(username);
        saleChanceService.addSaleChance(saleChance);
        return success("营销机会数据添加成功");
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 进入添加 或者 修改  营销机会页面
     * @date : 2021/10/2 11:25
     */
    @RequestMapping("toSaleChancePage")
    public String toSaleChancePage(Integer saleChanceId, HttpServletRequest request) {
        if (saleChanceId != null) {
            // 通过id 查询一条记录
            SaleChance saleChance = saleChanceService.selectByPrimaryKey(saleChanceId);
            // 将数据  设置到请求域
            request.setAttribute("saleChance", saleChance);
        }
        return "saleChance/add_update";
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm :  更新机会
     * @date : 2021/10/8 20:07
     */

    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateSalaChance(SaleChance saleChance) {
        saleChanceService.updateSaleChance(saleChance);
        return success("营销机会更新成功");
    }

    /**
     * @return :101003
     * @author :wyanjia
     * @parm : 删除营销机会
     * @date : 2021/10/8 22:01
     * 利用自定义注解
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteSaleChance(Integer[] ids) {
        // 调用service 层的删除方法
        saleChanceService.deleteSaleChance(ids);
        return success("删除成功");
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 更新营销机会的开发状态
     * @date : 2021/10/10 18:44
     */
    @RequestMapping("updateSaleChanceResult")
    @ResponseBody
    public ResultInfo updateSaleChanceResult(Integer id, Integer devResult) {
        saleChanceService.updateSaleChanceResult(id, devResult);
        return success("开发状态更新成功");
    }
}
