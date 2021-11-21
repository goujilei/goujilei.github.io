package com.software.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.software.crm.base.BaseService;
import com.software.crm.bean.CustomerServe;
import com.software.crm.enums.CustomerServeStatus;
import com.software.crm.mapper.CustomerMapper;
import com.software.crm.mapper.CustomerServeMapper;
import com.software.crm.mapper.UserMapper;
import com.software.crm.query.CustomerServeQuery;
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
public class CustomerServeService extends BaseService<CustomerServe, Integer> {
    @Resource
    private CustomerServeMapper customerServeMapper;
    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private UserMapper userMapper;

    public Map<String, Object> queryCustomerServeByParas(CustomerServeQuery customerServeQuery) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(customerServeQuery.getPage(), customerServeQuery.getLimit());
        PageInfo<CustomerServe> pageInfo = new PageInfo<CustomerServe>(customerServeMapper.selectByParams(customerServeQuery));
        map.put("data", pageInfo.getList());
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        return map;
    }

    /**
     * @return : 执行添加操作
     * @author :wyanjia
     * @parm :参数校验  设置默认值
     * @date : 2021/10/19 9:39
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addCustomerServe(CustomerServe customerServe) {
        //1. 客户名存在 customer 存在 记录存在 服务类型存在 服务请求内容非空 serviceRequest
        // 判断客户名是否存在
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getCustomer()), "客户名不能为空");
        AssertUtil.isTrue(customerMapper.queryCustomerByName(customerServe.getCustomer()) == null, "客户不存在");
        // 服务类型非空
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServeType()), "服务类型不能为空");
        // 服务请求内容不能为空
        AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceRequest()), "服务请求内容不能为空");
        // 2. 设置参数默认值   服务状态   服务创建状态 是否有效 创建时间 更新时间  createPeople  前端页面从cookie 获取
        customerServe.setState(CustomerServeStatus.CREATED.getState());
        customerServe.setIsValid(1);
        customerServe.setCreateDate(new Date());
        customerServe.setUpdateDate(new Date());
        // 执行操作
        AssertUtil.isTrue(customerServeMapper.insertSelective(customerServe) < 1, "添加服务失败");
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 参数校验
     * 客户服务id 非空 记录存在
     * 客户服务状态
     * 如果服务状态为  服务分配状态
     * 分配人 非空   分配用户记录存在
     * 分配时间 系统当前时间
     * 更新时间 系统当前时间
     * 如果服务状态为  服务处理状态
     * 服务处理内容 非空
     * 服务处理时间 系统当前时间
     * 更新时间 系统当前时间
     * 如果服务状态是  服务反馈状态
     * 服务反馈内容非空
     * 服务满意度 非空
     * 更新时间 系统当前时间
     * 执行更新操作 判断受影响的行数
     * @date : 2021/10/19 10:20
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomerServe(CustomerServe customerServer) {
        // 1. 客户服务id 非空  记录存在
        AssertUtil.isTrue(null == customerServer.getId(), "待更新服务记录不存在1");
        CustomerServe temp = customerServeMapper.selectByPrimaryKey(customerServer.getId());
        AssertUtil.isTrue(temp == null, "待更新服务记录不存在2 ");
        // 判断客户服务的服务状态
        if (CustomerServeStatus.ASSIGNED.getState().equals(customerServer.getState())) {
            // 服务分配操作
            //1.分配人 非空 记录 存在
            AssertUtil.isTrue(StringUtils.isBlank(customerServer.getAssigner()), "待分配用户不能为空1");
            AssertUtil.isTrue(userMapper.selectByPrimaryKey(Integer.parseInt(customerServer.getAssigner())) == null,
                    "待分配用户不能为空2");
            // 设置分配时间
            customerServer.setAssignTime(new Date());
            customerServer.setUpdateDate(new Date());
        } else if (CustomerServeStatus.PROCED.getState().equals(customerServer.getState())) {
            // 服务 处理操作
            // 服务处理内容非空
            AssertUtil.isTrue(StringUtils.isBlank(customerServer.getServiceProce()), "服务处理内容不能为空");
            customerServer.setServiceProceTime(new Date());
            customerServer.setUpdateDate(new Date());
        } else if (CustomerServeStatus.FEED_BACK.getState().equals(customerServer.getCustomer())) {
            // 服务反馈操作
            // 服务反馈内容非空
            AssertUtil.isTrue(StringUtils.isBlank(customerServer.getServiceProceResult()), "服务反馈内容不能为空");
            AssertUtil.isTrue(StringUtils.isBlank(customerServer.getMyd()), "服务反馈满意度不能为空");
            customerServer.setState(CustomerServeStatus.ARCHIVED.getState());
            customerServer.setUpdateDate(new Date());
        }

        AssertUtil.isTrue(customerServeMapper.updateByPrimaryKeySelective(customerServer) < 1, "服务更新失败");

    }
}
