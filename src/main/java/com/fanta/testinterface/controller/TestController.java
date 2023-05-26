package com.fanta.testinterface.controller;

import com.fanta.testinterface.model.User;
import com.fanta.testinterface.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/name")
public class TestController {
    @GetMapping
    public String getNameByGet(String name) {
        return "GET 你的名字是" + name;
    }

    @PostMapping
    public String getNameByPost(@RequestParam("name") String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/username")
    public String getUserNameByPost(@RequestBody User User, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
//        String secretKey = request.getHeader("secretKey");
        String sign = request.getHeader("sign");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String body = request.getHeader("body");

        //todo 从数据库中查询 ak 是否分配给用户
        if (!accessKey.equals("fanta")) {
            throw new RuntimeException("无权限");
        }
        //todo 签名校验  例如 时间不超过五分钟
//        if(timestamp){
//
//        }
        // todo 从数据库查出 sk
        String serverSign = SignUtils.getSign(body, "asdqwe");

        //校验签名是否一致
        if (!serverSign.equals(sign)) {
            throw new RuntimeException("无权限");
        }
        return "POST 你的用户名字是" + User.getUsername();
    }
}
