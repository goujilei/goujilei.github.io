package com.software.crm.controller;

import com.software.crm.base.BaseController;
import com.software.crm.base.ResultInfo;
import com.software.crm.bean.Customer;
import com.software.crm.query.CustomerQuery;
import com.software.crm.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {
    @Resource
    private CustomerService customerService;

    /**
     * @return :
     * @author :wyanjia
     * @parm : 分页查询客户列表
     * @date : 2021/10/14 18:03
     */

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCustomerByParams(CustomerQuery customerQuery) {
        return customerService.queryCustomerByParams(customerQuery);
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 进入客户信息管理页面
     * @date : 2021/10/14 18:13
     */
    @RequestMapping("index")
    public String index() {
        return "customer/customer";
    }

    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addCustomer(Customer customer) {
        customerService.addCustomer(customer);
        return success("添加信息成功");
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm :打开添加或这修改的页面对话框
     * @date : 2021/10/15 10:04
     */
    @RequestMapping("toAddOrUpdateCustomerPage")
    public String toAddOrUpdateCustomerPage(Integer id, HttpServletRequest request) {
        if (null != id) {
            Customer customer = customerService.selectByPrimaryKey(id);
            request.setAttribute("customer", customer);
        }
        return "customer/add_update";
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 修改客户信息
     * @date : 2021/10/15 10:38
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateCustomer(Customer customer) {
        customerService.updateCustomer(customer);
        return success("修改信息成功");
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm :删除客户信息
     * @date : 2021/10/15 11:28
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer id) {
        customerService.deleteCustomer(id);
        return success("删除信息成功");
    }

    @RequestMapping("toOpenCustomerOrderPage")
    public String toOpenCustomerOrderPage(Integer id, HttpServletRequest request) {
        // 通过客户id 查询客户记录 设置到请求域中
        request.setAttribute("customer", customerService.selectByPrimaryKey(id));
        return "customer/order";
    }

    @RequestMapping("toLinkPage")
    public String toLinkPage(Integer id, HttpServletRequest request) {
        request.setAttribute("cusId", id);
        return "customer/link";
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 客户贡献分析
     * @date : 2021/10/19 16:42
     */
    @RequestMapping("queryCustomerContributionByParams")
    @ResponseBody
    public Map<String, Object> queryCustomerContributionByParams(CustomerQuery customerQuery) {
        return customerService.queryCustomerContributionByParams(customerQuery);
    }

    // 查询客户构成
    @RequestMapping("countCustomerMake")
    @ResponseBody
    public Map<String, Object> countCustomerMake() {
        return customerService.countCustomerMake();
    }

    // 查询客户构成饼图
    @RequestMapping("countCustomerMakePie")
    @ResponseBody
    public Map<String, Object> countCustomerMakePie() {
        return customerService.countCustomerMakePie();
    }

}
