package com.software.crm.controller;

import com.software.crm.base.BaseController;
import com.software.crm.query.CustomerOrderQuery;
import com.software.crm.service.CustomerOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping("order")
@Controller
public class CustomerOrderController extends BaseController {
    @Resource
    private CustomerOrderService customerOrderService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCustomerOrderByParams(CustomerOrderQuery customerOrderQuery) {
        return customerOrderService.selectByQuery(customerOrderQuery);
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm :打开订单详情页面
     * @date : 2021/10/15 15:13
     */
    @RequestMapping("toOrderDetailPage")
    public String toOrderDetailPage(HttpServletRequest request, Integer orderId) {
        Map<String, Object> map = customerOrderService.queryOrderById(orderId);
        request.setAttribute("order", map);
        return "customer/customer_order_detail";
    }

}
