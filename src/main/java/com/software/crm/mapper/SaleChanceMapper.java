package com.software.crm.mapper;

import com.software.crm.base.BaseMapper;
import com.software.crm.bean.SaleChance;

public interface SaleChanceMapper extends BaseMapper<SaleChance , Integer> {
    /**
     * 多条件查询
     * <p>
     * 多条件查询 的接口不需要定义，因为已在 基类 BaseMapper 中定义了
     * 由于多个模块涉及到多条件查询操作，
     * 所以将对应的多条件查询功能方法定义在父接口的 BaseMapper 中的 selectByParams()方法
     */


    /**
     * 添加记录返回行数
     * <p>
     * 基类 BaseMapper中已有 添加记录返回行数的 selectByParams() 方法
     * 这里直接使用就行，然后再看 SaleChanceMapper.xml 有没有相应的查询sql语句
     * 有sql语句，就相当于 dao层准备好了
     */

    /**
     * 更新单条记录
     * <p>
     * 基类 BaseMapper 中已有 更新单条记录的方法 updateByPrimaryKeySelective() 方法
     * 这里直接使用就行，然后再看 SaleChanceMapper.xml 有没有相应的查询sql语句
     * 有sql语句，就相当于 dao层准备好了
     */
}