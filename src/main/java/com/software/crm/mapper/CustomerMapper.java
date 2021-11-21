package com.software.crm.mapper;

import com.software.crm.base.BaseMapper;
import com.software.crm.bean.Customer;
import com.software.crm.query.CustomerQuery;

import java.util.List;
import java.util.Map;

public interface CustomerMapper extends BaseMapper<Customer, Integer> {
    // 通过客户名称 查询客户对象
    Customer queryCustomerByName(String name);

    // 查询待流失的客户数据
    List<Customer> queryLossCustomers();

    // 通过id 批量更新客户流失状态
    int updateCustomerStateByIds(List<Integer> lossCustomerIds);

    // 查询客户贡献
    List<Map<String, Object>> queryCustomerContributionByParams(CustomerQuery customerQuery);

    // 查询客户构成
    List<Map<String, Object>> countCustomerMake();
}