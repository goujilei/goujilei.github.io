package com.software.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.software.crm.base.BaseService;
import com.software.crm.bean.OrderDetails;
import com.software.crm.mapper.OrderDetailsMapper;
import com.software.crm.query.OrderDetailsQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderDetailsService extends BaseService<OrderDetails, Integer> {
    @Resource
    private OrderDetailsMapper orderDetailsMapper;

    public Map<String, Object> queryOrderDetails(OrderDetailsQuery orderDetailsQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(orderDetailsQuery.getPage(), orderDetailsQuery.getLimit());
        PageInfo<OrderDetails> pageInfo = new PageInfo<OrderDetails>(orderDetailsMapper.selectByParams(orderDetailsQuery));
        map.put("code", 0);
        map.put("msg", "success");
        map.put("data", pageInfo.getList());
        map.put("count", pageInfo.getTotal());
        return map;
    }
}
