package com.barton.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 项目名称：ssm
 * dashboard
 */
@Controller
public class DashboardController {
    private static final Log logger = LogFactory.get();
    @GetMapping(value = {"/dashboard"})
    public String index() {
        return "/dashboard/index";
    }

    @GetMapping(value = {"/main"})
    public String main() {
        return "/dashboard/mian/index";
    }

}


