package com.software.crm.controller;

import com.software.crm.base.BaseController;
import com.software.crm.base.ResultInfo;
import com.software.crm.bean.CustomerLoss;
import com.software.crm.query.CustomerLossQuery;
import com.software.crm.service.CustomerLossService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("customer_loss")
public class CustomerLossController extends BaseController {
    @Resource
    private CustomerLossService customerLossService;

    /**
     * @return :
     * @author :wyanjia
     * @parm : 进入客户流失管理页面
     * @date : 2021/10/18 11:22
     */
    @RequestMapping("index")
    public String index() {
        return "customerLoss/customer_loss";
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 分页条件查询 流失列表
     * @date : 2021/10/18 11:35
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCustomerLossByParams(CustomerLossQuery customerLossQuery) {
        return customerLossService.queryCustomerLossByParams(customerLossQuery);
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm :打开添加暂缓 或者 详情页面
     * @date : 2021/10/18 12:23
     */


    @RequestMapping("toCustomerLossPage")
    public String toCustomerLossPage(Integer id, HttpServletRequest request) {
        CustomerLoss customerLoss = customerLossService.selectByPrimaryKey(id);
        request.setAttribute("customerLoss", customerLoss);
        return "customerLoss/customer_rep";
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm :更新流失客户的流失状态
     * @date : 2021/10/18 18:46
     */
    @RequestMapping("updateCustomerLossStateById")
    @ResponseBody
    public ResultInfo updateCustomerLossStateById(Integer id, String lossReason) {
        customerLossService.updateCustomerLossStateById(id, lossReason);
        return success("确认成功");
    }
}
