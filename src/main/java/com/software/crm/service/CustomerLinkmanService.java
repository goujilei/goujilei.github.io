package com.software.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.software.crm.base.BaseService;
import com.software.crm.bean.Customer;
import com.software.crm.bean.CustomerLinkman;
import com.software.crm.mapper.CustomerLinkmanMapper;
import com.software.crm.mapper.CustomerMapper;
import com.software.crm.query.CustomerLinkmanQuery;
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
public class CustomerLinkmanService extends BaseService<CustomerLinkman, Integer> {
    @Resource
    private CustomerLinkmanMapper customerLinkmanMapper;

    @Resource
    private CustomerMapper customerMapper;

    /**
     * @return :
     * @author :wyanjia
     * @parm :联系人查询 分页
     * @date : 2021/10/15 19:12
     */

    public Map<String, Object> selectByQuery(CustomerLinkmanQuery customerLinkmanQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(customerLinkmanQuery.getPage(), customerLinkmanQuery.getLimit());
        PageInfo<CustomerLinkman> pageInfo = new PageInfo<CustomerLinkman>(customerLinkmanMapper.selectByParams(customerLinkmanQuery));
        map.put("code", 0);
        map.put("msg", "success");
        map.put("data", pageInfo.getList());
        map.put("count", pageInfo.getTotal());
        return map;
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm :添加联系人
     * 1. 参数校验
     * cusId 不能为空
     * 姓名不能为空
     * @date : 2021/10/15 19:44
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addLinkMan(CustomerLinkman customerLinkman) {
        // 参数校验
        checkAddLinkParams(customerLinkman);
        //设置默认值
        customerLinkman.setIsValid(1);
        customerLinkman.setCreateDate(new Date());
        customerLinkman.setUpdateDate(new Date());
        //执行操作
        AssertUtil.isTrue(customerLinkmanMapper.insertSelective(customerLinkman) < 1, "添加新联系人失败");
    }

    private void checkAddLinkParams(CustomerLinkman customerLinkman) {
        AssertUtil.isTrue(null == customerLinkman.getCusId(), "客户编号异常");
        Customer temp = customerMapper.selectByPrimaryKey(customerLinkman.getCusId());
        AssertUtil.isTrue(null == temp, "客户对象异常");
        AssertUtil.isTrue(StringUtils.isBlank(customerLinkman.getLinkName()), "联系人姓名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(customerLinkman.getZhiwei()), "联系人职位不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(customerLinkman.getSex()), "性别不能为空");
        AssertUtil.isTrue(!(customerLinkman.getSex().equals("男") || customerLinkman.getSex().equals("女")), "性别不合法");
        AssertUtil.isTrue(StringUtils.isBlank(customerLinkman.getPhone()), "联系方式不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(customerLinkman.getPhone()), "手机号码格式不正确");
        AssertUtil.isTrue(StringUtils.isBlank(customerLinkman.getOfficePhone()), "工作号码不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(customerLinkman.getOfficePhone()), "工作号码不正确");
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm :更新
     * @date : 2021/10/17 16:26
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateLinkMan(CustomerLinkman customerLinkman) {
        checkUpdateLinkParams(customerLinkman);
        customerLinkman.setIsValid(1);
        customerLinkman.setUpdateDate(new Date());
        AssertUtil.isTrue(customerLinkmanMapper.updateByPrimaryKeySelective(customerLinkman) < 1, "更新失败");
    }

    private void checkUpdateLinkParams(CustomerLinkman customerLinkman) {
        AssertUtil.isTrue(customerLinkman.getId() == null, "待更新记录不存在1");
        CustomerLinkman temp = customerLinkmanMapper.selectByPrimaryKey(customerLinkman.getId());
        AssertUtil.isTrue(temp == null, "待更新记录不存在");
        AssertUtil.isTrue(!temp.getSex().equals(customerLinkman.getSex()), "性别不能改变");
        AssertUtil.isTrue(!temp.getLinkName().equals(customerLinkman.getLinkName()), "姓名不能改变");
        AssertUtil.isTrue(StringUtils.isBlank(customerLinkman.getPhone()), "联系方式不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(customerLinkman.getPhone()), "手机号码格式不正确");
        AssertUtil.isTrue(StringUtils.isBlank(customerLinkman.getOfficePhone()), "工作号码不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(customerLinkman.getOfficePhone()), "工作号码不正确");
    }

    /*
     * @author  :wyanjia
     * @parm    : 批量删除
     * @date    : 2021/10/17 19:07
     * @return  :
     * */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteLinkManByIds(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length < 1, "待删除记录不存在");
        AssertUtil.isTrue(customerLinkmanMapper.deleteBatch(ids) != ids.length, "删除失败");
    }

}
