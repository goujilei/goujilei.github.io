package com.software.crm.service;

import com.software.crm.base.BaseService;
import com.software.crm.bean.UserRole;
import com.software.crm.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserRoleService extends BaseService<UserRole, Integer> {
    @Resource
    private UserRoleMapper userRoleMapper ;
}
