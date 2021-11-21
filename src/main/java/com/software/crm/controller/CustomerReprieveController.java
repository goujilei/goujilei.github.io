package com.software.crm.controller;

import com.software.crm.base.BaseController;
import com.software.crm.base.ResultInfo;
import com.software.crm.bean.CustomerReprieve;
import com.software.crm.query.CustomerReprieveQuery;
import com.software.crm.service.CustomerReprieveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("customer_rep")
public class CustomerReprieveController extends BaseController {
    @Resource
    private CustomerReprieveService customerReprieveService;


    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCustomerReprieveByParams(CustomerReprieveQuery customerReprieveQuery) {
        return customerReprieveService.queryCustomerReprieveByParams(customerReprieveQuery);
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 添加客户流失的暂缓数据
     * @date : 2021/10/18 16:16
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addCustomerRepr(CustomerReprieve customerReprieve) {
        customerReprieveService.addCustomerRepr(customerReprieve);
        return success("添加成功");
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 添加客户流失的暂缓数据
     * @date : 2021/10/18 16:16
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateCustomerRepr(CustomerReprieve customerReprieve) {
        customerReprieveService.updateCustomerRepr(customerReprieve);
        return success("更新成功");
    }

    @RequestMapping("toAddOrUpdateCustomerPage")
    public String toAddOrUpdateCustomerPage(Integer lossId, HttpServletRequest request, Integer id) {
        request.setAttribute("lossId", lossId);
        // 通过主键id
        if (id != null) {
            CustomerReprieve customerRep = customerReprieveService.selectByPrimaryKey(id);
            request.setAttribute("customerRep", customerRep);
        }
        return "customerLoss/customer_rep_add_update";
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm :删除暂缓数据
     * @date : 2021/10/18 17:46
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCustomerReq(Integer id) {
        customerReprieveService.deleteCustomerReq(id);
        return success("删除成功");
    }
}
