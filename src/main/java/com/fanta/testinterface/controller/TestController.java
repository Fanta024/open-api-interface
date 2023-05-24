package com.fanta.testinterface.controller;

import org.springframework.web.bind.annotation.*;

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
    public String getUserNameByPost(@RequestBody String username) {
        return "POST 你的用户名字是" + username;
    }
}
