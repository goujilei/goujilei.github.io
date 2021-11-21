package com.software.crm.config;

import com.software.crm.interceptors.NoLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author :wyanjia
 * @parm : 配置类
 * @date : 2021/10/1 0:27
 * @return :
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {


    @Bean // 将方法返回值交给ioc 管理
    public NoLoginInterceptor noLoginInterceptor() {
        return new NoLoginInterceptor();
    }

    /**
     * @return :
     * @author :wyanjia
     * @parm : 添加拦截器
     * @date : 2021/10/1 0:29
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 需要一个实现了拦截器功能的实例
        registry.addInterceptor(noLoginInterceptor()).addPathPatterns("/**")  // 默认拦截全部
                .excludePathPatterns("/css/**", "/images/**", "/js/**", "/lib/**","/index","/user/login"); // 放行静态资源
    }

}
