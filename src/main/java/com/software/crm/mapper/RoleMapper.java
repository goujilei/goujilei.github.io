package com.software.crm.mapper;

import com.software.crm.base.BaseMapper;
import com.software.crm.bean.Role;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role, Integer> {
    // 查询所有的角色列表 只需要id 和roleName
    @MapKey("roleName")
    List<Map<String, Object>> queryAllRoles(Integer userId);

    Role selectByRoleName(String roleName);
}