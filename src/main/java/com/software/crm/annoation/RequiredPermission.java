package com.software.crm.annoation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiredPermission {
    /**
     * @return :
     * @author :wyanjia
     * @parm :  定义权限码
     * @date : 2021/10/13 16:24
     */
    String code() default "";
}
