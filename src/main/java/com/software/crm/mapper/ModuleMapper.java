package com.software.crm.mapper;

import com.software.crm.base.BaseMapper;
import com.software.crm.bean.Module;
import com.software.crm.model.TreeModel;

import java.util.List;

public interface ModuleMapper extends BaseMapper<Module, Integer> {
    // 查询所有的资源列表
    List<TreeModel> queryAllModules();

    // 查询所有的资源数据
    List<Module> queryAllModuleList();

    // 通过层级域模块名查询 资源对象
    Module queryModuleByGradeAndModuleName(Integer grade, String moduleName);

    // 同各国层级与url 查询资源对象
    Module queryModulesByGradeAndUrl(Integer grade, String url);

    // 通过操作码查询资源对象
    Module queryModelByOptValue(String optValue);

    // 查询指定资源是否存在子记录
    Integer queryByParentId(Integer id);
}