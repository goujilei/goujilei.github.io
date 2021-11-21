package com.software.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.software.crm.base.BaseService;
import com.software.crm.bean.CustomerLoss;
import com.software.crm.bean.CustomerReprieve;
import com.software.crm.mapper.CustomerLossMapper;
import com.software.crm.mapper.CustomerReprieveMapper;
import com.software.crm.query.CustomerReprieveQuery;
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
public class CustomerReprieveService extends BaseService<CustomerReprieve, Integer> {
    @Resource
    private CustomerReprieveMapper customerReprieveMapper;
    @Resource
    private CustomerLossMapper customerLossMapper;

    /*
     * @return :
     * @author :wyanjia
     * @parm : 分页查询 客户流失暂缓操作的列表
     * @date : 2021/10/18 14:07
     */
    public Map<String, Object> queryCustomerReprieveByParams(CustomerReprieveQuery customerReprieveQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(customerReprieveQuery.getPage(), customerReprieveQuery.getLimit());
        PageInfo<CustomerReprieve> pageInfo = new PageInfo<>(customerReprieveMapper.selectByParams(customerReprieveQuery));
        map.put("code", 0);
        map.put("data", pageInfo.getList());
        map.put("count", pageInfo.getTotal());
        map.put("msg", "success");
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addCustomerRepr(CustomerReprieve customerReprieve) {
        // 参数校验 1. id 非空 并且数据存在
        checkParams(customerReprieve.getLossId(), customerReprieve.getMeasure());
        // 设置参数默认值
        customerReprieve.setIsValid(1);
        customerReprieve.setCreateDate(new Date());
        customerReprieve.setUpdateDate(new Date());

        // 判断受影响的行数
        AssertUtil.isTrue(customerReprieveMapper.insertSelective(customerReprieve) < 1, "添加失败");
    }

    public void updateCustomerRepr(CustomerReprieve customerReprieve) {
        // 参数校验
        //1. id 非空数据存在
        AssertUtil.isTrue(null == customerReprieve.getId(), "待更新记录不存在");
        CustomerReprieve temp = customerReprieveMapper.selectByPrimaryKey(customerReprieve.getId());
        AssertUtil.isTrue(null == temp, "待更新记录不存在  对象为空");
        checkParams(customerReprieve.getLossId(), customerReprieve.getMeasure());
        // 2. 设置默认值
        customerReprieve.setUpdateDate(new Date());
        //执行更新操作
        AssertUtil.isTrue(customerReprieveMapper.updateByPrimaryKeySelective(customerReprieve) < 1, "更新成功");


    }

    private void checkParams(Integer lossId, String measure) {
        AssertUtil.isTrue(lossId == null, "流失客户记录不存在1");
        CustomerLoss customerLoss = customerLossMapper.selectByPrimaryKey(lossId);
        AssertUtil.isTrue(null == customerLoss, "流失客户记录不存在2");
        AssertUtil.isTrue(StringUtils.isBlank(measure), "暂缓措施内容不能为空");

    }

    public void deleteCustomerReq(Integer id) {
        // 参数校验
        AssertUtil.isTrue(null == id, "带删除记录不存在1");
        // 通过id 查询暂缓数据
        CustomerReprieve customerReprieve = customerReprieveMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(null == customerReprieve, "待删除记录不存在2");

        customerReprieve.setIsValid(0);
        customerReprieve.setUpdateDate(new Date());
        AssertUtil.isTrue(customerReprieveMapper.updateByPrimaryKeySelective(customerReprieve) < 1, "删除暂缓数据失败");
    }
}
