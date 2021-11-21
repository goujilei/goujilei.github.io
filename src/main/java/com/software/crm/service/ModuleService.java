package com.software.crm.service;

import com.software.crm.base.BaseService;
import com.software.crm.bean.Module;
import com.software.crm.mapper.ModuleMapper;
import com.software.crm.mapper.PermissionMapper;
import com.software.crm.model.TreeModel;
import com.software.crm.utils.AssertUtil;
import com.sun.xml.internal.ws.org.objectweb.asm.ByteVector;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;

@Service
public class ModuleService extends BaseService<Module, Integer> {

    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private PermissionMapper permissionMapper;

    /**
     * @return :
     * @author :wyanjia
     * @parm : 查询所有的资源列表
     * @date : 2021/10/13 9:59
     */
    public List<TreeModel> queryAllModules(Integer roleId) {
        // 查询所有的资源列表
        List<TreeModel> treeModelList = moduleMapper.queryAllModules();
        // 查询指定角色已经授权过的的资源列表(查询角色资源id )
        List<Integer> permissionIds = permissionMapper.queryRoleHasModuleByModuleId(roleId);
        // 判断角色是否拥有资源ID
        if (permissionIds != null && permissionIds.size() > 0) {
            // 循环所有的资源列表 判断用户拥有的资源id中 是否有匹配的 如果有则设置check 为true
            treeModelList.forEach(treeModel -> {
                if (permissionIds.contains(treeModel.getId())) {
                    // 如果包含则说明角色授权过
                    treeModel.setChecked(true);
                }
            });
        }
        return treeModelList;
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm :查询资源数据
     * @date : 2021/10/13 17:25
     */
    public Map<String, Object> queryAllModuleList() {
        List<Module> modules = moduleMapper.queryAllModuleList();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", modules.size());
        map.put("data", modules);
        return map;
    }

    /*
     * @author  :wyanjia
     * @parm    :添加资源
     * 1. 参数校验
     * 模块名称 moduleName  地址 url  父级菜单parentId 层级 grade 权限码 optValue
     * 模块名称 同一层级下模块名称唯一  非空
     * 地址 url
     * 当 grade ==1时 非空且不可重复
     * 父级菜单 一级菜单不存在父级菜单
     * 二级或者三级菜单存在 父级菜单   非空且父级菜单必须存在
     * 层级 非空 012
     * 权限码非空
     * 2.设置参数的默认值
     * is_valie 是否有效
     * 创建时间 修改时间
     * 3.执行添加操作
     * @date    : 2021/10/13 19:09
     * @return  :
     * */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addModule(Module module) {
        // 参数校验 判断非空
        // 判断层级
        Integer grade = module.getGrade();
        AssertUtil.isTrue(null == grade || !(grade == 0 || grade == 1 || grade == 2), "菜单层级不合法 ");
        //判断模块名
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()), "模块名称不能为空");
        // 统一层级下 模块名称唯一
        AssertUtil.isTrue(null != moduleMapper.queryModuleByGradeAndModuleName(grade, module.getModuleName()), "该层级下模块名称重复");
        // 地址url 二级菜单非空且同一层级不可重复
        //如果是二级菜单 grade == 1
        if (grade == 1) {
            // 判断地址
            AssertUtil.isTrue(null == module.getUrl(), "url 不能为空");
            AssertUtil.isTrue(null != moduleMapper.queryModulesByGradeAndUrl(grade, module.getUrl()), "url同级别下不可重复");
        }

        // 父级菜单 parentId 一级菜单（目录 -1 ）
        if (grade == 0) {
            module.setParentId(-1);
        }
        if (grade != 0) {
            AssertUtil.isTrue(null == module.getParentId(), "父级菜单不能为空");
            // 父级菜单必须存在 将父级菜单的ID 作为主键 查询资源记录
            AssertUtil.isTrue(null == moduleMapper.selectByPrimaryKey(module.getParentId()), "请指定正确的父级菜单");
        }
        // 授权码  非空不可重复
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()), "权限码不能为空");
        AssertUtil.isTrue(null != moduleMapper.queryModelByOptValue(module.getOptValue()), "权限码不能重复");
        // 设置默认值
        module.setIsValid((byte) 1);
        module.setUpdateDate(new Date());
        module.setCreateDate(new Date());
        // 执行添加操作
        AssertUtil.isTrue(moduleMapper.insertSelective(module) < 1, "添加资源失败");
    }

    /**
     * @return :
     * 1.参数校验
     * id 非空 数据存在
     * 层级grade  非空 0|1|2
     * 模块名称 非空 同一层级下模块名称唯一  不包含当前修改记录本身
     * url地址
     * 二级菜单grade = 1 非空且同一层级下不可重复 不包含当前修改记录
     * 权限码 非空不可重复 不包括当前修改记录本身
     * 设置参数默认值   修改更新时间
     * 判断受影响的行数
     * @author :wyanjia
     * @parm : 修改资源 参数校验 设置参数的默认值  判断受影响的行数
     * @date : 2021/10/14 15:10
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateModule(Module module) {
        // 1. 参数校验  id 不为空且数据存在
        AssertUtil.isTrue(null == module.getId(), "待更新记录不存在 ");
        Module temp = moduleMapper.selectByPrimaryKey(module.getId());
        // 判断记录是否存在
        AssertUtil.isTrue(null == temp, "待更新记录不存在 ");
        // 判断层级 grade
        Integer grade = module.getGrade();
        AssertUtil.isTrue(null == grade || !(grade == 0 || grade == 1 || grade == 2), "菜单层级不合法");
        // 模块名称
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()), "模块名称不能为空");
        // 通过名称去查寻
        temp = moduleMapper.queryModuleByGradeAndModuleName(grade, module.getModuleName());
        if (temp != null) {
            AssertUtil.isTrue(!(temp.getId().equals(module.getId())), "该层级下菜单名字已经存在");
        }
        // 地址url 判断是否是二级菜单
        if (grade == 1) {
            AssertUtil.isTrue(StringUtils.isBlank(module.getUrl()), "url 不能为空");
            //通过层级与 url 查询资源对昂
            temp = moduleMapper.queryModulesByGradeAndUrl(grade, module.getUrl());
            if (temp != null) {
                AssertUtil.isTrue(!(temp.getId().equals(module.getId())), "该层级下菜单url已经存在");
            }
        }
        // 权限码
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()), "权限码不能为空");
        // 通过权限码查询资源对象
        temp = moduleMapper.queryModelByOptValue(module.getOptValue());
        if (temp != null) {
            AssertUtil.isTrue(!(temp.getOptValue().equals(module.getOptValue())), "该菜单层级下权限码不可用");
        }
        //  设置参数默认值
        module.setUpdateDate(new Date());
        // 执行更新
        AssertUtil.isTrue(moduleMapper.updateByPrimaryKeySelective(module) < 1, "修改资源失败");
    }

    /**
     * @return : 1.判断删除的记录是否存在
     * 2. 如果当前资源存在子记录 则不可删除
     * 3. 删除资源时 将对应的权限表的记录 也删除 （先判断权限表中是否存在关联数据 如果存在则删除）
     * 4. 执行更新判断受影响的行数
     * @author :wyanjia
     * @parm : 删除操作
     * @date : 2021/10/14 16:20
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteModule(Integer id) {
        AssertUtil.isTrue(null == id, "待删除记录不存在");
        Module temp = moduleMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(temp == null, "待删除记录不存在 ");
        // 判断是否存在子记录  将id 当作父id 查询资源 记录
        Integer count = moduleMapper.queryByParentId(id);
        // 如果存在子记录 则不可删除
        AssertUtil.isTrue(count > 0, "该资源存在子记录不可删除");
        // 通过资源ID 查询权限表
        count = permissionMapper.countPermissionByModuleId(id);
        //判断是否存在 如果存在则删除
        if (count > 0) {
            // 删除指定资源的ID的权限记录
            permissionMapper.deletePermissionByModuleId(id);
        }


        // 设置记录 无效
        temp.setUpdateDate(new Date());
        temp.setIsValid((byte) 0);
        // 执行跟新
        AssertUtil.isTrue(moduleMapper.updateByPrimaryKeySelective(temp) < 1, "删除数据失败");
    }
}
