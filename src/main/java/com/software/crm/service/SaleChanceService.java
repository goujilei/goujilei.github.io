package com.software.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.software.crm.base.BaseService;
import com.software.crm.bean.SaleChance;
import com.software.crm.enums.DevResult;
import com.software.crm.enums.StateStatus;
import com.software.crm.mapper.SaleChanceMapper;
import com.software.crm.query.SaleChanceQuery;
import com.software.crm.utils.AssertUtil;
import com.software.crm.utils.PhoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SaleChanceService extends BaseService<SaleChance, Integer> {
    @Resource
    private SaleChanceMapper saleChanceMapper;

    /**
     * @return :
     * @author :wyanjia
     * @parm :分页多多条件 查询营销机会
     * @date : 2021/10/1 10:04
     */
    public Map<String, Object> querySaleChanceByParams(SaleChanceQuery saleChanceQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(saleChanceQuery.getPage(), saleChanceQuery.getLimit());
        PageInfo<SaleChance> pageInfo = new PageInfo<>(saleChanceMapper.selectByParams(saleChanceQuery));
        map.put("code", 0);
        map.put("msg", "success");
        map.put("data", pageInfo.getList());
        map.put("count", pageInfo.getTotal());
        return map;
    }

    /**
     * @return : 添加营销机会
     * @author :wyanjia
     * @parm :  添加操作
     * 1.参数校验
     * customerName 必填
     * linkman linkPhone 必填
     * 2. 设置相关参数的默认值
     * createMan  创建人 当前登录用户
     * assignMan   如果 默认 未设置 指派人则 默认未分配 如果 设置 指派人 则 分配状态为1  指派时间 为系统当前时间
     * devResult 开发状态 0 1 2 3
     * isValid  是否有效  0 1
     * creatTime
     * updatedATE 系统默认时间
     * 执行 添加操作
     * @date : 2021/10/1 21:25
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addSaleChance(SaleChance saleChance) {
        /**
         * @author  :wyanjia
         * @parm    : 校验参数
         * @date    : 2021/10/1 21:37
         * @return  :
         * */
        checkSaleChanceParams(saleChance.getCustomerName(), saleChance.getLinkMan(), saleChance.getLinkPhone());
        // 设置默认值
        // 设置  有效值 默认1 有效
        saleChance.setIsValid(1);
        // 创建时间 系统默认时间
        saleChance.setCreateDate(new Date());
        //更新时间 系统默认时间
        saleChance.setUpdateTime(new Date());
        //判断是否设置指派人
        if (StringUtils.isBlank(saleChance.getAssignMan())) {
            // 如果为空表示未设置指派人
            saleChance.setState(StateStatus.UNSTATE.getType());
            // 指派时间设置为空
            saleChance.setAssignTime(null);
            //  设置开发状态
            saleChance.setDevResult(DevResult.UNDEV.getStatus());
        } else {
            // 如果不为空 则表示未设置指派人
            // 设置当前分配状态
            saleChance.setState(StateStatus.STATED.getType());
            // 设置分配时间
            saleChance.setAssignTime(new Date());
            // 设置 开发状态
            saleChance.setDevResult(DevResult.DEVING.getStatus());
        }
        // 执行添加操作
        AssertUtil.isTrue(saleChanceMapper.insertSelective(saleChance) != 1, "添加营销机会失败");
    }


    private void checkSaleChanceParams(String customerName, String linkMan, String linkPhone) {
        AssertUtil.isTrue(StringUtils.isBlank(customerName), "客户名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(linkMan), "联系人不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(linkPhone), "联系号码不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(linkPhone), "手机号码格式不正确");
    }

    /**
     * @return :  更新操作
     * @author :wyanjia
     * @parm :  更新 营销机会
     * 更新营销机会 : 1. 参数校验
     * 营销机会ID  非空   数据库对应记录 存在
     * customerName 客户名称 非空
     * linkMan   联系人  非空
     * linkPhone  联系号码  非空 手机格式正确
     * 2. 设置相关参数
     * upDate 更新事件
     * assignMan:指派人
     * 原始数据未设置
     * 修改后 未设置 : 不需要操作
     * 修改后已经设置 : 设置指派事件  系统当前时间  分配状态 改变为已分配 开发状态 修改开发中
     * 原始数据以设置 :
     * 修改后未设置    指派时间 设置为null  分配状态  未分配 开发状态 未开发
     * 修改后 已设置 : 判断修改前后是否时同一个指派人
     * 如果是 则 不需要操作 如果不是则 更新 assignTime 指派时间  为系统当前时间
     * 3. 执行更新操作
     * @date : 2021/10/2 14:38
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateSaleChance(SaleChance saleChance) {
        // 参数校验
        // 1.  判断id 是否为空
        AssertUtil.isTrue(null == saleChance.getId(), "待更新记录不存在");
        SaleChance temp = saleChanceMapper.selectByPrimaryKey(saleChance.getId());
        AssertUtil.isTrue(temp == null, "待更新记录不存在");
        // 参数校验
        checkSaleChanceParams(saleChance.getCustomerName(), saleChance.getLinkMan(), saleChance.getLinkPhone());
        // 设置相关参数的默认值
        saleChance.setUpdateTime(new Date());
        // 判断  指派人 是否存在
        if (StringUtils.isBlank(temp.getAssignMan())) {
            // 不存在
            // 判断修改后的值 是否存在

            if (!StringUtils.isBlank(saleChance.getAssignMan())) {
                //  修改前 为空 修改后 有值
                // 设置 指派时间
                saleChance.setAssignTime(new Date());
                //  修改分配状态
                saleChance.setState(1);
                // 修改开发状态
                saleChance.setDevResult(1);
            }
        } else {
            // 存在
            if (StringUtils.isBlank(saleChance.getAssignMan())) { //修改前有值  修改后无值
                // 修改指派时间设置 为空
                saleChance.setAssignTime(null);
                //   修改分配状态 设置为 0
                saleChance.setState(StateStatus.UNSTATE.getType());
                // 修改开发状态 设置为  0
                saleChance.setDevResult(0);
            } else {
                //  修改前有值 修改后 有值
                // 判断修改前后是否同一个用户
                if (!saleChance.getAssignMan().equals(temp.getAssignMan())) {
                    //  更新指派时间
                    saleChance.setAssignTime(new Date());
                } else {
                    temp.setAssignTime(new Date());
                }
            }
        }
        /**
         * @author  :wyanjia
         * @parm    :
         * @date    : 2021/10/8 16:49
         * @return  :  执行更新操作
         * */
        AssertUtil.isTrue(saleChanceMapper.updateByPrimaryKeySelective(saleChance) != 1, "更新失败 ");
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 批量删除
     * @date : 2021/10/8 21:55
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSaleChance(Integer[] ids) {
        // 判断id 是否为空
        AssertUtil.isTrue(null == ids || ids.length < 1, "待删除记录不存在");
        // 执行删除
        AssertUtil.isTrue(saleChanceMapper.deleteBatch(ids) != ids.length, "删除失败");
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 更新营销机会的开发状态
     * @date : 2021/10/10 18:45
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateSaleChanceResult(Integer id, Integer devResult) {
        AssertUtil.isTrue(null == id, "待更新记录不存在");
        SaleChance saleChance = saleChanceMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(saleChance == null, "更新记录不存在");

        //设置对应的开发状态
        saleChance.setDevResult(devResult);
        // 执行更新
        AssertUtil.isTrue(saleChanceMapper.updateByPrimaryKeySelective(saleChance) != 1, "开发状态更新失败");
    }
}
