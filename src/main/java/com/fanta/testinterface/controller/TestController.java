package com.fanta.testinterface.controller;

import com.fanta.fantaapicommon.model.entity.ClientUser;
import com.fanta.fantaapicommon.model.entity.User;
import com.fanta.fantaapicommon.service.InnerUserService;
import com.fanta.fantaclientsdk.utils.SignUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/name")
public class TestController {
    private static final long FIVE_TIME = 5 * 60L;
    @DubboReference
    private InnerUserService innerUserService;

    @GetMapping("/test")
    public String test(String name) {
        return "GET 你的名字是" + name;
    }

    @GetMapping("/")
    public String getNameByGet(String name) {
        return "GET 你的名字是" + name;
    }

    @PostMapping
    public String getNameByPost(@RequestParam("name") String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/username")
    public String getUserNameByPost(@RequestBody ClientUser clientUser, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
//        String secretKey = request.getHeader("secretKey");
        String sign = request.getHeader("sign");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String body = request.getHeader("body");

        //从数据库中查询 ak 是否分配给用户
        User user = innerUserService.getInvokeUser(accessKey);
        if (user.getAccessKey() != null && user.getAccessKey().isEmpty()) {
            throw new RuntimeException("无权限");
        }
        //时间不超过五分钟
        if ((System.currentTimeMillis() / 1000) - Long.parseLong(timestamp) >= FIVE_TIME) {
            throw new RuntimeException("已过期");
        }
        //数据库查出 sk
        String serverSign = SignUtils.getSign(body, user.getSecretKey());
        //校验签名是否一致
        if (!serverSign.equals(sign)) {
            throw new RuntimeException("无权限");
        }
        return "POST 你的用户名字是" + clientUser.getUsername();
    }
}
