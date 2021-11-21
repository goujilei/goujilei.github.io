package com.software.crm.mapper;

import com.software.crm.base.BaseMapper;
import com.software.crm.bean.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User, Integer> {
    User queryUserByName(String username);

    /**
     * @return :
     * @author :wyanjia
     * @parm : 下拉框加载
     * @date : 2021/10/8 21:02
     */
    List<Map<String, Object>> queryAllSaless();

    /**
     * @return :
     * @author :wyanjia
     * @parm : 查询所有的客户经理
     * @date : 2021/10/19 12:42
     */
    List<Map<String, Object>> queryAllCustomerManager();

}