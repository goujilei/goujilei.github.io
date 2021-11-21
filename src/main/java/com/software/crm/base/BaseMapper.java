package com.software.crm.base;

import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BaseMapper<T, ID> {
    /**
     * @return : 添加记录返回行数
     * @author :wyanjia
     * @parm : 添加记录
     * @date : 2021/9/28 1:30
     */
    public Integer insertSelective(T entity) throws DataAccessException;

    /**
     * @return :返回主键
     * @author :wyanjia
     * @parm :添加记录返回主键
     * @date : 2021/9/28 1:32
     */
    public Integer insertHasKey(T entity) throws DataAccessException;

    /**
     * @return :批量添加
     * @author :wyanjia
     * @parm :批量添加
     * @date : 2021/9/28 1:33
     */
    public Integer insertBatch(List<T> entities) throws DataAccessException;

    /**
     * @return : 对象
     * @author :wyanjia
     * @parm : 根据id 查询详情
     * @date : 2021/9/28 1:35
     */
    public T selectByPrimaryKey(ID id) throws DataAccessException;

    /**
     * @return : 分页
     * @author :wyanjia
     * @parm : 多条件查询
     * @date : 2021/9/28 1:36
     */
    public List<T> selectByParams(BaseQuery baseQuery) throws DataAccessException;

    /**
     * @return :行数
     * @author :wyanjia
     * @parm : 更新单条记录
     * @date : 2021/9/28 1:37
     */
    public Integer updateByPrimaryKeySelective(T entity) throws DataAccessException;
    /**
     * 批量更新
     * @param entities
     * @return
     */
    public Integer updateBatch(List<T> entities) throws DataAccessException;

    /**
     * 删除单条记录
     * @param id
     * @return
     */
    public Integer deleteByPrimaryKey(ID id) throws DataAccessException;

    /**
     * 批量删除
     * @param ids
     * @return
     */
    public Integer deleteBatch(ID[] ids) throws DataAccessException;

}
