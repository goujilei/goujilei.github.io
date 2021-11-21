package com.software.crm.service;

import com.software.crm.base.BaseService;
import com.software.crm.bean.Permission;
import com.software.crm.mapper.PermissionMapper;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionService extends BaseService<Permission, Integer> {

    @Resource
    private PermissionMapper permissionMapper;

    /**
     * @return : 用户拥有的资源列表
     * @author :wyanjia
     * @parm : 通过查询用户拥有的角色 角色拥有的资源
     * @date : 2021/10/13 15:03
     */
    public List<String> queryUserHasRoleHasPermissionByUserId(Integer userId) {
        return  permissionMapper.queryUserHasRoleHasPermissionByUserId(userId);
    }
}
