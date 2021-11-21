package com.software.crm.mapper;

import com.software.crm.base.BaseMapper;
import com.software.crm.bean.CustomerReprieve;

import java.util.List;

public interface CustomerReprieveMapper extends BaseMapper<CustomerReprieve, Integer> {
    List<CustomerReprieve> queryAllByLossId();
}