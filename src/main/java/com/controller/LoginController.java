package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录功能
 * Create by HP on 2020/5/18
 * 游魂
 */
@Controller
public class LoginController {
    @RequestMapping("loginResult")
    public void loginResult(String username, String pwd){
        System.out.println(username);
        System.out.println(pwd);
    }

    @RequestMapping("loginInit")
    public void loginInit(){

    }
}
