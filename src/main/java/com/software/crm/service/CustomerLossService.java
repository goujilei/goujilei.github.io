package com.software.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.software.crm.base.BaseService;
import com.software.crm.bean.CustomerLoss;
import com.software.crm.mapper.CustomerLossMapper;
import com.software.crm.query.CustomerLossQuery;
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
public class CustomerLossService extends BaseService<CustomerLoss, Integer> {
    @Resource
    private CustomerLossMapper customerLossMapper;

    public Map<String, Object> queryCustomerLossByParams(CustomerLossQuery customerLossQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(customerLossQuery.getPage(), customerLossQuery.getLimit());
        PageInfo<CustomerLoss> pageInfo = new PageInfo<>(customerLossMapper.selectByParams(customerLossQuery));
        map.put("code", 0);
        map.put("data", pageInfo.getList());
        map.put("count", pageInfo.getTotal());
        map.put("msg", "success");
        return map;
    }

    /**
     * @return :  1.参数校验
     * 2. 设置参数的默认值
     * 3. 执行更新
     * 4. 判断受影响的行数
     * @author :wyanjia
     * @parm : 更新流失客户的流失状态
     * @date : 2021/10/18 18:47
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomerLossStateById(Integer id, String lossReason) {
        //1. 参数校验 id 非空且 数据存在
        AssertUtil.isTrue(id == null, "待确认的客户不存在1");
        CustomerLoss customerLoss = customerLossMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(null == customerLoss, "待确认客户不存在2");
        // 流失原因非空
        AssertUtil.isTrue(StringUtils.isBlank(lossReason), "确认原因不能为空");
        // 设置参数的默认值 设置流失状态为1
        customerLoss.setUpdateDate(new Date());
        customerLoss.setState(1);
        customerLoss.setConfirmLossTime(new Date());
        customerLoss.setLossReason(lossReason);
        // 执行更新操作
        AssertUtil.isTrue(customerLossMapper.updateByPrimaryKeySelective(customerLoss) < 1, "确认流失失败");

    }
}
