package com.software.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.software.crm.base.BaseService;
import com.software.crm.bean.CustomerOrder;
import com.software.crm.mapper.CustomerOrderMapper;
import com.software.crm.query.CustomerOrderQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerOrderService extends BaseService<CustomerOrder, Integer> {
    @Resource
    private CustomerOrderMapper customerOrderMapper;


    public Map<String, Object> selectByQuery(CustomerOrderQuery customerOrderQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(customerOrderQuery.getPage(), customerOrderQuery.getLimit());
        PageInfo<CustomerOrder> pageInfo = new PageInfo<CustomerOrder>(customerOrderMapper.selectByParams(customerOrderQuery));
        map.put("code", 0);
        map.put("msg", "success");
        map.put("data", pageInfo.getList());
        map.put("count", pageInfo.getTotal());
        return map;
    }
    /**
     * @author  :wyanjia
     * @parm    : 通过订单id 查询对应的订单记录
     * @date    : 2021/10/15 15:32
     * @return  :
     * */

    public Map<String, Object> queryOrderById(Integer orderId){
        return  customerOrderMapper.queryOrderById(orderId);
    }
}
