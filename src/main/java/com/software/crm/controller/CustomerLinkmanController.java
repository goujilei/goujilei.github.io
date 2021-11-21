package com.software.crm.controller;

import com.software.crm.base.BaseController;
import com.software.crm.base.ResultInfo;
import com.software.crm.bean.CustomerLinkman;
import com.software.crm.query.CustomerLinkmanQuery;
import com.software.crm.service.CustomerLinkmanService;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("customer_linkman")
public class CustomerLinkmanController extends BaseController {
    @Resource
    private CustomerLinkmanService customerLinkmanService;

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryCustomerOrderByParams(CustomerLinkmanQuery customerLinkmanQuery) {
        return customerLinkmanService.selectByQuery(customerLinkmanQuery);
    }

    @RequestMapping("toAddOrUpdateLinkManPage")
    public String toAddLinkManPage(Integer cusId, HttpServletRequest request, Integer id) {
        request.setAttribute("cusId", cusId);
        if (id != null) {
            CustomerLinkman customerLinkman = customerLinkmanService.selectByPrimaryKey(id);
            request.setAttribute("linkMan", customerLinkman);
        }
        return "customer/add_update_LinkMan";
    }
    @ConfigurationProperties(prefix = "bhu")
    @RequestMapping("addLinkMan")
    @ResponseBody
    public ResultInfo addLinkMan(CustomerLinkman customerLinkman) {
        customerLinkmanService.addLinkMan(customerLinkman);
        return success("添加新联系人成功");
    }

    @RequestMapping("updateLinkMan")
    @ResponseBody
    public ResultInfo updateLinkMan(CustomerLinkman customerLinkman) {
        customerLinkmanService.updateLinkMan(customerLinkman);
        return success("更新联系人成功");
    }

    @RequestMapping("deleteLinkMan")
    @ResponseBody
    public ResultInfo deleteLinkMan(Integer[] ids) {
        customerLinkmanService.deleteLinkManByIds(ids);
        return success("删除成功");
    }

}
