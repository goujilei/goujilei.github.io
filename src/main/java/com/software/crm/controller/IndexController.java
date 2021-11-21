package com.software.crm.controller;

import com.software.crm.base.BaseController;
import com.software.crm.bean.User;
import com.software.crm.service.PermissionService;
import com.software.crm.service.UserService;
import com.software.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController extends BaseController {
    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;

    /**
     * 系统登录页
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "index";
    }


    // 系统界面欢迎页
    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 后端管理主页面
     *
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest request) {
        // 查询用户对象  设置session作用域
        /**
         * @author  :wyanjia
         * @parm    :
         * @date    : 2021/9/30 1:40
         * @return  : 1. 通过 cookide 中的userIdStr  解码 获得userid
         * */
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.selectByPrimaryKey(userId);
        request.getSession().setAttribute("user", user);
        //  通过当前用户ID 查询当前 登录用户拥有的资源列表  （查询对应的资源授权码）
        List<String> permissions = permissionService.queryUserHasRoleHasPermissionByUserId(userId);
        // 将资源授权码 设置到 session作用域中
        request.getSession().setAttribute("permissions", permissions);
        return "main";
    }


}
