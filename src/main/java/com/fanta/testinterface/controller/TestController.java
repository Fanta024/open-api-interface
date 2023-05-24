package com.fanta.testinterface.controller;

import com.fanta.testinterface.model.User;
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
        String secretKey = request.getHeader("secretKey");
        if (!accessKey.equals("fanta") || !secretKey.equals("asdqwe")) {
            throw new RuntimeException("无权限");
        }
        return "POST 你的用户名字是" + User.getUsername();
    }
}
