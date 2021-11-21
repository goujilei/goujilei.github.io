package com.software.crm.controller;

import com.software.crm.base.BaseController;
import com.software.crm.service.UserRoleService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class UserRoleController extends BaseController {
    @Resource
    private UserRoleService userRoleService;
}
