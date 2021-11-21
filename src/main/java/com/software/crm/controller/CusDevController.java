package com.software.crm.controller;

import com.software.crm.base.BaseController;
import com.software.crm.base.ResultInfo;
import com.software.crm.bean.CusDevPlan;
import com.software.crm.bean.SaleChance;
import com.software.crm.query.CusDevPlanQuery;
import com.software.crm.service.CusDevPlanService;
import com.software.crm.service.SaleChanceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("cus_dev_plan")
@Controller
public class CusDevController extends BaseController {
    @Resource
    private SaleChanceService saleChanceService;
    @Resource
    private CusDevPlanService cusDevPlanService;

    /**
     * @return : 进入客户开发计划 视图
     * @author :wyanjia
     * @parm :
     * @date : 2021/10/9 9:39
     */
    @RequestMapping("index")
    public String index() {
        return "cusDevPlan/cus_dev_plan";
    }

    /**
     * 打开计划项开发与详情页面，即 cus_dev_plan_data.ftl 页面
     *
     * @param id
     * @return
     */
    @RequestMapping("toCusDevPlanPage")
    public String toCusDevPlanPage(Integer id, HttpServletRequest request) {
        // 通过id查询营销机会对象
        SaleChance saleChance = saleChanceService.selectByPrimaryKey(id);
        // 将对象设置到请求域中，把数据放到请求域中，然后返回给前端，通过EL表达式去获取
        request.setAttribute("saleChance", saleChance);
        return "cusDevPlan/cus_dev_plan_data";
    }

    /**

     * @return :json
     * @author :wyanjia
     * @parm : 客户开发列表 多条件查询
     * @date : 2021/10/9 16:58
     */
    @RequestMapping(value = "list", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> queryCusDevPlanByParams(CusDevPlanQuery cusDevPlanQuery) {
        return cusDevPlanService.querySaleChanceByParams(cusDevPlanQuery);
    }
    @RequestMapping("toAddOrUpdateCusDevPlanPage")
    public String toAddOrUpdateCusDevPlanPage(Integer sId, HttpServletRequest request, Integer id) {




    /**
     * @return :进入开发计划项 页面
     * @author :wyanjia
     * @parm :
     * @date : 2021/10/10 14:00
     */
        // 将营销机会id 设置到 请求域中 传递到 计划项页面
        request.setAttribute("sId", sId);
        CusDevPlan cusDevPlan = cusDevPlanService.selectByPrimaryKey(id);
        request.setAttribute("cusDevPlan", cusDevPlan);
        return "cusDevPlan/add_update";
    }
    @RequestMapping("add")
    public ResultInfo   addCusDevPlan(CusDevPlan cusDevPlan) {
        cusDevPlanService.addCusDevPlan(cusDevPlan);
        return success("计划项添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateCusDevPlan(CusDevPlan cusDevPlan) {
        cusDevPlanService.updateCusDevPlan(cusDevPlan);
        return success("计划项更新成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCusDevPlan(Integer id) {
        cusDevPlanService.deleteCusDevPlan(id);
        return success("计划项更新成功");
    }


}
