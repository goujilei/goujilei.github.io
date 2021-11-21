package com.software.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.software.crm.base.BaseService;
import com.software.crm.bean.CusDevPlan;
import com.software.crm.mapper.CusDevPlanMapper;
import com.software.crm.mapper.SaleChanceMapper;
import com.software.crm.query.CusDevPlanQuery;
import com.software.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CusDevPlanService extends BaseService<CusDevPlan, Integer> {
    @Resource
    private CusDevPlanMapper cusDevPlanMapper;
    @Resource
    private SaleChanceMapper saleChanceMapper;

    /**
     * @return :
     * @author :wyanjia
     * @parm :分页多多条件 查询营销机会
     * @date : 2021/10/1 10:04
     */
    public Map<String, Object> querySaleChanceByParams(CusDevPlanQuery cusDevPlanQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(cusDevPlanQuery.getPage(), cusDevPlanQuery.getLimit());
        PageInfo<CusDevPlan> pageInfo = new PageInfo<CusDevPlan>(cusDevPlanMapper.selectByParams(cusDevPlanQuery));
        map.put("code", 0);
        map.put("msg", "success");
        map.put("data", pageInfo.getList());
        map.put("count", pageInfo.getTotal());
        return map;
    }

    /**
     * @return :
     * 1. 参数校验  营销机会 id  非空  数据存在
     * 计划项内容非空  计划时间非空
     * 2.设置参数默认值  是否有效 默认有效
     * 创建时间 系统当前时间
     * 修改时间
     * @author :wyanjia
     * @parm : 添加客户开发计划数据
     * @date : 2021/10/10 13:24
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public void addCusDevPlan(CusDevPlan cusDevPlan) {
        // 参数校验
        checkCusDevPlan(cusDevPlan);
        cusDevPlan.setIsValid(1);
        cusDevPlan.setCreateDate(new Date());
        cusDevPlan.setUpdateTime(new Date());

        AssertUtil.isTrue(cusDevPlanMapper.insertSelective(cusDevPlan) != 1, "计划项数据添加失败");
    }

    private void checkCusDevPlan(CusDevPlan cusDevPlan) {
        // 营销机会  id
        Integer sId = cusDevPlan.getSaleChanceId();
        AssertUtil.isTrue(null == sId || saleChanceMapper.selectByPrimaryKey(sId) == null, "数据异常请重试1");
        // 计划项内容非空
        AssertUtil.isTrue(StringUtils.isBlank(cusDevPlan.getPlanItem()), "计划项内容不能为空2");
        //  计划时间不为空
        AssertUtil.isTrue(null == cusDevPlan.getPlanDate(), "计划项时间不能为空3 ");
    }

    /**
     * @return :
     * 1. 参数校验
     * 计划项 ID  非空 数据存在
     * 营销机会ID  非空数据存在
     * 计划项内容 非空
     * 计划时间非空
     * 2. 设置 默认值
     * 修改 时间 系统当前时间
     * 3. 执行更新操作
     * @author :wyanjia
     * @parm : 更新
     * @date : 2021/10/10 16:28
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCusDevPlan(CusDevPlan cusDevPlan) {
        // 参数校验
        AssertUtil.isTrue(null == cusDevPlan.getId() ||
                        cusDevPlanMapper.selectByPrimaryKey(cusDevPlan.getId()) == null
                , "数据异常 请重试4 ");
        checkCusDevPlan(cusDevPlan);
        // 2. 设置默认值
        cusDevPlan.setUpdateTime(new Date());
        // 执行更新操作
        AssertUtil.isTrue(cusDevPlanMapper.updateByPrimaryKeySelective(cusDevPlan) != 1, "计划项更新失败");
    }

    /**
     * @return :
     * 1. 判断ID 是否为空
     * 2. 修改对应的isValid 属性
     * 3. 执行更新操作
     * @author :wyanjia
     * @parm : 删除计划项
     * @date : 2021/10/10 18:14
     */

    public void deleteCusDevPlan(Integer id) {
        AssertUtil.isTrue(null == id, "待删除记录不存在");
        // 通过id 查询计划项
        CusDevPlan cusDevPlan = cusDevPlanMapper.selectByPrimaryKey(id);
        cusDevPlan.setIsValid(0);
        cusDevPlan.setUpdateTime(new Date());
        AssertUtil.isTrue(cusDevPlanMapper.updateByPrimaryKeySelective(cusDevPlan) != 1, "计划项数据删除失败");
    }
}
