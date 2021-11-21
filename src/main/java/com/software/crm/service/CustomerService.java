package com.software.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.software.crm.base.BaseService;
import com.software.crm.bean.Customer;
import com.software.crm.bean.CustomerLoss;
import com.software.crm.bean.CustomerOrder;
import com.software.crm.mapper.CustomerLossMapper;
import com.software.crm.mapper.CustomerMapper;
import com.software.crm.mapper.CustomerOrderMapper;
import com.software.crm.query.CustomerQuery;
import com.software.crm.utils.AssertUtil;
import com.software.crm.utils.PhoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CustomerService extends BaseService<Customer, Integer> {
    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private CustomerOrderMapper customerOrderMapper;
    @Resource
    private CustomerLossMapper customerLossMapper;

    /**
     * @return :
     * @author :wyanjia
     * @parm :分页多多条件 查询客户
     * @date : 2021/10/1 10:04
     */
    public Map<String, Object> queryCustomerByParams(CustomerQuery customerQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(customerQuery.getPage(), customerQuery.getLimit());
        PageInfo<Customer> pageInfo = new PageInfo<>(customerMapper.selectByParams(customerQuery));
        map.put("code", 0);
        map.put("msg", "success");
        map.put("data", pageInfo.getList());
        map.put("count", pageInfo.getTotal());
        return map;
    }

    /**
     * @return :
     * 参数校验
     * 客户名 name 非空  名称唯一
     * 法人代表 非空
     * 手机号码 格式正确
     * 设置默认值
     * isvalid 1
     * 创建时间  修改时间
     * 客户编号
     * 系统生成 唯一 UUID  // 时间戳 // 年月日时分秒KH+ 时间戳
     * @author :wyanjia
     * @parm :添加客户信息
     * @date : 2021/10/14 20:12
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public void addCustomer(Customer customer) {
        // 1.参数校验
        checkCustomerParams(customer.getName(), customer.getFr(), customer.getPhone());
        // 判断顾客名称的唯一性
        Customer temp = customerMapper.queryCustomerByName(customer.getName());
        AssertUtil.isTrue(null != temp, "客户名称已经存在");
        // 设置默认值
        customer.setCreateDate(new Date());
        customer.setUpdateDate(new Date());
        customer.setIsValid(1);
        customer.setState(0);
        String khno = "KH" + System.currentTimeMillis();
        customer.setKhno(khno);
        AssertUtil.isTrue(customerMapper.insertSelective(customer) < 1, "添加客户信息失败");
    }

    private void checkCustomerParams(String name, String fr, String phone) {
        AssertUtil.isTrue(StringUtils.isBlank(name), "客户名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(fr), "法人代表不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(phone), "手机号码不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone), "手机号码格式不正确");
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 修改客户
     * 1.参数校验
     * ID 非空 存在
     * 客户名 name 非空  名称唯一
     * 法人代表 非空
     * 手机号码 格式正确
     * 设置默认值
     * @date : 2021/10/15 10:21
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomer(Customer customer) {
        // 参数校验
        // 判断ID 不能为空
        AssertUtil.isTrue(null == customer.getId(), "待更新记录不存在");
        // 通过ID 查询 返回对象不能为空
        Customer temp = customerMapper.selectByPrimaryKey(customer.getId());
        AssertUtil.isTrue(temp == null, "待更新记录不存在");
        checkCustomerParams(customer.getName(), customer.getFr(), customer.getPhone());
        // 通过
        temp = customerMapper.queryCustomerByName(customer.getName());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(customer.getId())), "客户名称已经存在");
        //设置参数校验值
        customer.setUpdateDate(new Date());
        // 执行更新操作
        AssertUtil.isTrue(customerMapper.updateByPrimaryKeySelective(customer) < 1, "修改客户信息失败");
    }

    /**
     * @return : 1. 参数校验
     * 设置默认值
     * 2. 执行更新 判断受影响的行数
     * @author :wyanjia
     * @parm :删除customer
     * @date : 2021/10/15 11:22
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCustomer(Integer id) {
        AssertUtil.isTrue(null == id, "待删除记录不存在");
        Customer customer = customerMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(null == customer, "待删除记录不存在");
        customer.setIsValid(0);
        customer.setUpdateDate(new Date());
        AssertUtil.isTrue(customerMapper.updateByPrimaryKeySelective(customer) < 1, "删除客户失败");
    }

    /**
     * @return :1.查询待流失的客户数据
     * 将流失客户的数据批量添加到客户流失表中
     * @author :wyanjia
     * @parm :更新客户流失状态
     * @date : 2021/10/18 9:46
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomerState() {
        // 查询待流失的客户数据
        List<Customer> lossCustomerList = customerMapper.queryLossCustomers();
        // 判断流失客户数据是否存在
        if (lossCustomerList != null && lossCustomerList.size() > 0) {
            //定义集合接收流式数据的ID
            List<Integer> lossCustomerIds = new ArrayList<>();
            // 定义客户流失的列表
            List<CustomerLoss> customerLossList = new ArrayList<>();
            // 遍历流失客户对象
            lossCustomerList.forEach(customer -> {
                //定义客户流失对象
                CustomerLoss customerLoss = new CustomerLoss();
                // 创建时间  系统当前时间
                customerLoss.setCreateDate(new Date());
                // 客户经理
                customerLoss.setCusManager(customer.getCusManager());
                // 客户名称
                customerLoss.setCusName(customer.getName());
                //
                customerLoss.setUpdateDate(new Date());
                // 客户编号
                customerLoss.setCusNo(customer.getKhno());
                // 暂缓流失状态0  确认流失状态
                customerLoss.setState(0);
                // 客户最后下单时间
                // 通过客户id 查询最后的订单记录
                CustomerOrder customerOrder = customerOrderMapper.queryLossCustomerOrderByCustomerId(customer.getId());
                // 判断最后订单记录是否为空
                if (customerOrder != null) {
                    customerLoss.setLastOrderTime(customerOrder.getOrderDate());
                }
                customerLossList.add(customerLoss);
                // 将流失客户的ID 放到对应的集合中
                lossCustomerIds.add(customer.getId());
                // 执行批量添加流失客户记录
            });
            AssertUtil.isTrue(customerLossMapper.insertBatch(customerLossList) != customerLossList.size(), "客户流失数据转移失败");
            // 3. 批量更新
            AssertUtil.isTrue(customerMapper.updateCustomerStateByIds(lossCustomerIds) != lossCustomerIds.size(), "客户流失数据转移失败2");
        }
    }

    // 客户贡献分析
    public Map<String, Object> queryCustomerContributionByParams(CustomerQuery customerQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(customerQuery.getPage(), customerQuery.getLimit());
        PageInfo<Map<String, Object>> pageInfo =
                new PageInfo<Map<String, Object>>(customerMapper.queryCustomerContributionByParams(customerQuery));
        map.put("code", 0);
        map.put("msg", "success");
        map.put("data", pageInfo.getList());
        map.put("count", pageInfo.getTotal());
        return map;
    }

    // 客户构成
    public Map<String, Object> countCustomerMake() {
        Map<String, Object> map = new HashMap<>();
        // 查询客户构成数据的列表
        List<Map<String, Object>> dataList = customerMapper.countCustomerMake();
        // 折线x 轴的 数组
        List<String> datax = new ArrayList<>();
        // 折线y轴
        List<String> datay = new ArrayList<>();
        // 判断数据列表 循环判断
        if (dataList != null && dataList.size() > 0) {
            dataList.forEach(m -> {
                // 获取level 对应的数据 设置到x轴
                datax.add(m.get("level").toString());
                // 获取total 对应的数据 设置到y轴
                datay.add(m.get("total").toString());
            });
        }
        // 将x 轴的数据集合 和y 轴的数据集合 设置到map
        map.put("datax", datax);
        map.put("datay", datay);
        return map;
    }

    // 饼图
    public Map<String, Object> countCustomerMakePie() {
        Map<String, Object> map = new HashMap<>();
        // 查询客户构成数据的列表
        List<Map<String, Object>> dataList = customerMapper.countCustomerMake();
        // 饼图数据  数组 字符串
        List<String> datax = new ArrayList<>();
        // 饼图数据  数组 中是对象
        List<Map<String, Object>> datay = new ArrayList<>();
        // 判断 dataist
        if (dataList != null && dataList.size() > 0) {
            // 遍历集合
            dataList.forEach(m -> {
                // 饼图字符串
                datax.add(m.get("level").toString());
                // 饼图对象
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("name", m.get("level").toString());
                dataMap.put("value", m.get("total").toString());
                datay.add(dataMap);
            });
        }
        // 将x 轴的数据集合 和y 轴的数据集合 设置到map
        map.put("datax", datax);
        map.put("datay", datay);
        return map;

    }
}
