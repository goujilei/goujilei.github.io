package com.software.crm.service;

import com.software.crm.base.BaseService;
import com.software.crm.bean.Permission;
import com.software.crm.bean.Role;
import com.software.crm.bean.UserRole;
import com.software.crm.mapper.ModuleMapper;
import com.software.crm.mapper.PermissionMapper;
import com.software.crm.mapper.RoleMapper;
import com.software.crm.utils.AssertUtil;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RoleService extends BaseService<Role, Integer> {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private ModuleMapper moduleMapper;

    public List<Map<String, Object>> queryAllRoles(Integer userId) {
        return roleMapper.queryAllRoles(userId);
    }


    /**
     * @return :
     * 1.角色名称 非空 唯一
     * 2.设置参数的默认值 是否有效  创建时间
     * 3. 执行添加操作判断受影响的行数
     * @author :wyanjia
     * @parm :参数校验
     * @date : 2021/10/12 17:55
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRole(Role role) {
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "角色名称不能为空");
        Role temp = roleMapper.selectByRoleName(role.getRoleName());
        AssertUtil.isTrue(temp != null, "角色名称已经存在 ");
        // 设置默认值
        role.setIsValid(1);
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());

        //执行添加操作
        AssertUtil.isTrue(roleMapper.insertSelective(role) < 1, "添加失败");
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 修改角色  参数校验  角色ID 非空 且 数据存在 角色名称非空且 唯一
     * @date : 2021/10/12 19:22
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRole(Role role) {
        // 1.参数校验
        AssertUtil.isTrue(null == role.getId(), "待更新记录不存在");
        // 通过角色ID 查询角色记录
        Role temp = roleMapper.selectByPrimaryKey(role.getId());
        AssertUtil.isTrue(null == temp, "待更新记录不存在");
        // 非空
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "角色名称不能为空");
        temp = roleMapper.selectByRoleName(role.getRoleName());
        // 判断角色记录是否存在 如果不存在 表示可以使用 如果存在且角色id 与当前更新角色id 不一致 表示角色 不可用
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(role.getId())), "角色名称已经存在 ");
        //2.设置默认值
        role.setUpdateDate(new Date());
        //3.修改操作
        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(role) < 1, "修改角色失败");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRole(Integer roleId) {
        // 1.参数校验
        AssertUtil.isTrue(null == roleId, "待更新记录不存在");
        Role role = roleMapper.selectByPrimaryKey(roleId);
        // 2. 设置相关参数的默认值3. 修改时间
        AssertUtil.isTrue(null == role, "待更新记录不存在");
        role.setIsValid(0);
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(role) != 1, "删除失败");
    }

    /**
     * @return :  将对应的角色ID 和资源id 添加到 权限表中
     * 直接 添加  会造成数据重复
     * 推荐使用   先将已有的权限记录删除 再将需要设置的权限记录添加
     * 1. 通过角色ID 查询对应的权限记录
     * 2. 如果权限记录存在 则删除对应的权限记录
     * 3. 如果有权限记录添加新的权限记录
     * @author :wyanjia
     * @parm :  角色收授权
     * @date : 2021/10/13 11:45
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addGrant(Integer roleId, Integer[] mIds) {
        // 1. 通过角色ID 查询对应的权限记录
        Integer count = permissionMapper.countPermissionByRoleId(roleId);
        if (count > 0) {
            // 执行删除
            permissionMapper.deletePermissionByRoleId(roleId);
        }
        // 批量添加 
        if (mIds != null && mIds.length > 0) {
            List<Permission> permissionList = new ArrayList<>();
            // 遍历资源ID 数组
            for (Integer mId : mIds) {
                Permission permission = new Permission();
                permission.setUpdateDate(new Date());
                permission.setModuleId(mId);
                permission.setRoleId(roleId);
                permission.setAclValue(moduleMapper.selectByPrimaryKey(mId).getOptValue());
                permission.setCreateDate(new Date());
                // 将对象设置到集合中
                permissionList.add(permission) ;
            }
            // 执行批量添加的操作
            AssertUtil.isTrue(permissionMapper.insertBatch(permissionList) != permissionList.size(), "角色授权失败");
        }
    }
}
