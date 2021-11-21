package com.software.crm.controller;

import com.software.crm.base.BaseController;
import com.software.crm.base.ResultInfo;
import com.software.crm.bean.User;
import com.software.crm.exceptions.ParamsException;
import com.software.crm.model.UserModel;
import com.software.crm.query.UserQuery;
import com.software.crm.service.UserService;
import com.software.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author :wyanjia
 * @parm : controller层
 * @date : 2021/9/29 0:40
 * @return :
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @PostMapping("login")
    @ResponseBody
    public ResultInfo userLogin(String username, String password) {
        ResultInfo resultInfo = new ResultInfo();
        // 通过 trycatch 捕获service 异常 如果 service 存在异常 登陆失败
        try {
            UserModel userModel = userService.userLogin(username, password);
            // 设置reusltinfo 的result 值
            resultInfo.setResult(userModel);
        } catch (ParamsException e) {
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg(e.getMsg());
            e.printStackTrace();
        } catch (Exception e) {
            resultInfo.setCode(500);
            resultInfo.setMsg("登录失败");
        }
        return resultInfo;
    }

    @PostMapping("updatePwd")
    @ResponseBody
    public ResultInfo updateUserPassword(HttpServletRequest request, String oldPassword,
                                         String newPassword, String repeatPassword) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            // 获取cookie 中的用户id
            Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
            userService.updatePassword(userId, oldPassword, newPassword, repeatPassword);
        } catch (ParamsException p) {
            resultInfo.setCode(500);
            resultInfo.setMsg(p.getMsg());
            p.printStackTrace();
        } catch (Exception e) {
            resultInfo.setCode(500);
            resultInfo.setMsg("修改密码失败");
        }
        return resultInfo;
    }

    /**
     * @return : 进入修改密码的页面
     * @author :wyanjia
     * @parm :
     * @date : 2021/9/30 20:46
     */
    @RequestMapping("toPasswordPage")
    public String toPasswordPage() {
        return "user/password";
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 查询所有的销售人员
     * @date : 2021/10/8 21:13
     */

    @RequestMapping("queryAllSales")
    @ResponseBody
    public List<Map<String, Object>> queryAllSales() {
        return userService.queryAllSales();
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 分页多条价查询 用户列表
     * @date : 2021/10/11 9:59
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> selectByParams(UserQuery userQuery) {
        return userService.queryByParamsForTable(userQuery);
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 进入用户列表页面
     * @date : 2021/10/11 10:31
     */
    @RequestMapping("index")
    public String index() {
        return "user/user";
    }

    /**
     * @return : resultInfo
     * @author :wyanjia
     * @parm :添加用户
     * @date : 2021/10/11 11:01
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo addUser(User user) {
        userService.addUser(user);
        return success("用户添加成功");
    }

    @RequestMapping("toAddOrUpdateUserPage")
    public String toAddOrUpdateUserPage(Integer id, HttpServletRequest request) {
        if (id != null) {
            User user = userService.selectByPrimaryKey(id);
            request.setAttribute("userInfo", user);
        }
        return "user/add_update";
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateUser(User user) {
        userService.updateUser(user);
        return success("用户更新成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteUser(Integer[] ids) {
        userService.deleteByIds(ids);
        return success("用户删除成功");
    }

    @RequestMapping("queryAllCustomerManager")
    @ResponseBody
    public List<Map<String, Object>> queryAllCustomerManager() {
        return userService.queryAllCustomerManager();
    }
}
