package com.software.crm.mapper;

import com.software.crm.base.BaseMapper;
import com.software.crm.bean.Permission;
import com.sun.org.apache.xml.internal.utils.res.IntArrayWrapper;

import java.net.InetAddress;
import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission, Integer> {
    Integer countPermissionByRoleId(Integer roleId);

    Integer deletePermissionByRoleId(Integer roleId);

    List<Integer> queryRoleHasModuleByModuleId(Integer roleId);

    List<String> queryUserHasRoleHasPermissionByUserId(Integer userId);

    //通过资源ID 查询权限记录
    Integer countPermissionByModuleId(Integer id);
    //通过资源ID 删除权限记录
    Integer deletePermissionByModuleId(Integer id);

}