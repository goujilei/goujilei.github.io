package com.software.crm.mapper;

import com.software.crm.base.BaseMapper;
import com.software.crm.bean.CustomerOrder;

import java.util.Map;

public interface CustomerOrderMapper extends BaseMapper<CustomerOrder, Integer> {
    //通过订单ID 查询订单记录
    Map<String, Object> queryOrderById(Integer orderId);

    //通过客户ID 查询最后一条订单记录
    CustomerOrder queryLossCustomerOrderByCustomerId(Integer id);
}