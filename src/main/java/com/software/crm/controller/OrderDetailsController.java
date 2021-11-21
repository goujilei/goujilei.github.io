package com.software.crm.controller;

import com.software.crm.base.BaseController;
import com.software.crm.query.OrderDetailsQuery;
import com.software.crm.service.OrderDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("order_detail")
public class OrderDetailsController extends BaseController {
    @Resource
    private OrderDetailsService orderDetailsService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryOrderDetailsByParams(OrderDetailsQuery orderDetailsQuery) {
        System.out.println(orderDetailsQuery.getOrderId());
        return orderDetailsService.queryOrderDetails(orderDetailsQuery);
    }


}



