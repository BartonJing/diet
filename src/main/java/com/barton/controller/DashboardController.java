package com.barton.controller;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.barton.service.DietBot;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 项目名称：ssm
 * dashboard
 */
@Controller
public class DashboardController {
    private static final Log logger = LogFactory.get();



    @PostMapping(value = {"/dashboard"})
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 创建Bot
        DietBot bot = new DietBot(request);
        // 打开签名验证
        bot.enableVerify();
        // 关闭签名验证
        // bot.disableVerify();

        try {
            // 调用bot的run方法
            String responseJson = bot.run();
            // 设置response的编码UTF-8
            response.setCharacterEncoding("UTF-8");
            // 返回response
            response.getWriter().append(responseJson);
        } catch (Exception e) {
            // 返回非法结果
            response.getWriter().append("{\"status\":1,\"msg\":\"\"}");
        }

    }

}


