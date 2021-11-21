package com.software.crm.interceptors;

import com.software.crm.exceptions.NoLoginException;
import com.software.crm.mapper.UserMapper;
import com.software.crm.utils.LoginUserUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.naming.spi.ObjectFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author :wyanjia
 * @parm : 拦截未登录的非法请求
 * @date : 2021/10/1 0:16
 * @return :
 */
public class NoLoginInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private UserMapper userMapper;

    /**
     * @return : Boolean
     * @author :wyanjia
     * @parm : 拦截用户是否登录状态
     * 在 目标方法执行之前 执行
     * 如果返回ture 表示目标方法可以被执行
     * 如果返回false 表示阻止目标方法执行
     * 如何判断用户是否登录成功状态  判断cookie 中是否存在用户信息    数据库中是否存在指定id 的值
     * @date : 2021/10/1 0:18
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取cookid 的用户id
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        // 判断id是否为空并且  数据库中存在 该对象记录
        if (null == userId || userMapper.selectByPrimaryKey(userId) == null) {
            throw new NoLoginException();
        }
        return true;
    }
}
