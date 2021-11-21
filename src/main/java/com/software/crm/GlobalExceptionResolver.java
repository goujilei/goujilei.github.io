package com.software.crm;

import com.alibaba.fastjson.JSON;
import com.software.crm.base.ResultInfo;
import com.software.crm.exceptions.AuthException;
import com.software.crm.exceptions.NoLoginException;
import com.software.crm.exceptions.ParamsException;
import jdk.nashorn.internal.ir.IfNode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author :wyanjia
 * @parm :
 * @date : 2021/9/30 21:40
 * @return :  全局异常统一处理
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    /**
     * @return : 异常处理方法
     * @author :wyanjia
     * @parm :方法返回值
     * 1. 返回视图
     * 2. 返回json
     * 如何判断方法的返回值
     * 通过判断方法上的注解声明   @ResponseBody
     * @date : 2021/9/30 21:41
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse response, Object handler, Exception e) {
        /**
         * @author  :wyanjia
         * @parm    : 非法请求拦截
         * 判断是否抛出为登录异常
         * 如果抛出未登陆异常 则要求用户重定向
         * @date    : 2021/10/1 0:24
         * @return  :
         * */
        if (e instanceof NoLoginException) {
            ModelAndView modelAndView = new ModelAndView("redirect:/index");
            return modelAndView;
        }

        //设置默认异常处理 返回视图
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("code", 500);
        modelAndView.addObject("msg", "系统异常，请重试");
        modelAndView.setViewName("");
        // 判断 handlerMethon
        if (handler instanceof HandlerMethod) {
            //类型转换
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上声明responseBody 注解对象
            ResponseBody responseBody = handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);
            // 判断responseBody 对象是否为空 如果为空 则表示返回视图 如果不为空 则表示返回数据
            if (responseBody == null) {
                // 方法返回视图  判断异常类型
                if (e instanceof ParamsException) {
                    ParamsException p = (ParamsException) e;
                    modelAndView.addObject("code", p.getCode());
                    modelAndView.addObject("msg", p.getMsg());
                } else if (e instanceof AuthException) {
                    // 设置异常信息
                    ParamsException p = (ParamsException) e;
                    modelAndView.addObject("code", p.getCode());
                    modelAndView.addObject("msg", p.getMsg());
                }
                return modelAndView;
            } else {
                // 返回数据
                //设置默认异常处理
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(500);
                resultInfo.setMsg("异常..");

                //判断是否为自定义异常
                if (e instanceof ParamsException) {
                    ParamsException paramsException = (ParamsException) e;
                    resultInfo.setCode(paramsException.getCode());
                    resultInfo.setMsg(paramsException.getMsg());
                } else if (e instanceof AuthException) {
                    //认证异常
                    AuthException authException = (AuthException) e;
                    resultInfo.setCode(authException.getCode());
                    resultInfo.setMsg(authException.getMsg());
                }
                // 设置响应类型 和编码格式
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter out = null;
                try {
                    out = response.getWriter();
                    //将需要返回的对象转换成json格式的字符串
                    String json = JSON.toJSONString(resultInfo);
                    out.write(json);
                } catch (IOException exception) {
                    exception.printStackTrace();
                } finally {
                    // 如果 对象不为空给 则关闭对象
                    if (out != null) {
                        out.close();
                    }
                }
                return null;
            }
        }

        return modelAndView;
    }
}
