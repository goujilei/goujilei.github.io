package com.software.crm.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 登录用户数据相关工具类
 */
public class LoginUserUtil {

    /**
     * 从cookie中获取userId
     *
     * @param request
     * @return
     */
    public static int releaseUserIdFromCookie(HttpServletRequest request) {
        String userIdString = CookieUtil.getCookieValue(request, "userIdStr"); // 如果你的cookie中存的不是userIdStr,则需要修改，因为这一套过程的方法可以拿到其他项目中使用
        if (StringUtils.isBlank(userIdString)) {
            return 0;
        }
        Integer userId = UserIDBase64.decoderUserID(userIdString);
        return userId;
    }
}
