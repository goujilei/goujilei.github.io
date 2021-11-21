package com.software.crm.controller;

import com.software.crm.base.BaseController;
import com.software.crm.base.ResultInfo;
import com.software.crm.bean.CustomerServe;
import com.software.crm.query.CustomerServeQuery;
import com.software.crm.service.CustomerServeService;
import com.software.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("customer_serve")
public class CustomerServeController extends BaseController {
    @Resource
    private CustomerServeService customerServeService;


    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCustomerServeByParas(CustomerServeQuery customerServeQuery, Integer flag, HttpServletRequest request) {
        // 判断是否执行 服务处理 如果是则查询分配给当前登录用户的服务记录
        if (flag != null && flag == 1) {
            customerServeQuery.setAssigner(LoginUserUtil.releaseUserIdFromCookie(request));
        }
        return customerServeService.queryCustomerServeByParas(customerServeQuery);
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 通过不同的类型进入不同的服务页面
     * @date : 2021/10/18 19:50
     */

    @RequestMapping("index/{type}")
    public String index(@PathVariable Integer type) {
        if (type != null) {
            if (type == 1) {
                // 服务创建
                return "customerServe/customer_serve";
            } else if (type == 2) {
                // 服务分配
                return "customerServe/customer_serve_assign";
            } else if (type == 3) {
                // 服务处理
                return "customerServe/customer_serve_proce";
            } else if (type == 4) {
                // 服务反馈
                return "customerServe/customer_serve_feed_back";
            } else if (type == 5) {
                // 服务归档
                return "customerServe/customer_serve_archive";
            } else {
                return "";
            }


        } else {
            return "error";
        }
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm :打开添加服务页面
     * @date : 2021/10/19 9:27
     */
    @RequestMapping("openAddCusterServePage")
    public String openAddCusterServePage() {
        return "customerServe/customer_serve_add";
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm :add添加服务
     * @date : 2021/10/19 9:35
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addCustomerServe(CustomerServe customerServe) {
        customerServeService.addCustomerServe(customerServe);
        return success("添加成功");
    }


    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateCustomerServe(CustomerServe customerServe) {
        customerServeService.updateCustomerServe(customerServe);
        return success("操作成功");
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm :打开服务分配页面
     * @date : 2021/10/19 11:35
     */
    @RequestMapping("toCustomerServerAssignPage")
    public String toCustomerServerAssignPage(Integer id, HttpServletRequest request) {
        CustomerServe customerServe = customerServeService.selectByPrimaryKey(id);
        request.setAttribute("customerServe", customerServe);
        return "customerServe/customer_serve_assign_add";
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm :打开服务处理
     * @date : 2021/10/19 15:03
     */
    @RequestMapping("openCustomerServeProcePage")
    public String openCustomerServeProcePage(Integer id, HttpServletRequest request) {
        CustomerServe customerServe = customerServeService.selectByPrimaryKey(id);
        request.setAttribute("customerServe", customerServe);
        return "customerServe/customer_serve_proce_add";
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 打开服务反馈页面
     * @date : 2021/10/19 15:52
     */
    @RequestMapping("toCustomerServeFeedBackPage")
    public String toCustomerServeFeedBackPage(Integer id, HttpServletRequest request) {
        CustomerServe customerServe = customerServeService.selectByPrimaryKey(id);
        request.setAttribute("customerServe", customerServe);
        return "customerServe/customer_serve_feed_back_add";
    }

}
