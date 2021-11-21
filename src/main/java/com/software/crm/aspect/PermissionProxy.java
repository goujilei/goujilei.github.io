package com.software.crm.aspect;

import com.software.crm.annoation.RequiredPermission;
import com.software.crm.exceptions.AuthException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
@Aspect
public class PermissionProxy {
    @Resource
    private HttpSession session;

    @Around(value = "@annotation(com.software.crm.annoation.RequiredPermission)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        // 从session 作用域中获取 当前用户所拥有的权限
        List<String> permissions = (List<String>) session.getAttribute("permissions");
        if (null == permissions || permissions.size() < 1) {
            // 抛出异常
            throw new AuthException();
        }
        //得到对应的目标方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        // 得到方法上的注解
        RequiredPermission requiredPermission = methodSignature.getMethod().getDeclaredAnnotation(RequiredPermission.class);
        // 判断注解上对应的状态码
        if (!permissions.contains(requiredPermission.code())) {
            // 如果权限中不包含当前方法注解指定的权限码 则抛出异常
            throw new AuthException();
        }
        result = pjp.proceed();
        return result;
    }
}
