package com.software.crm.mapper;

import com.software.crm.base.BaseMapper;
import com.software.crm.bean.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole, Integer> {
    Integer countUserRoleByUserId(Integer userId);

    Integer deleteUserRoleByUserId(Integer userId);
}