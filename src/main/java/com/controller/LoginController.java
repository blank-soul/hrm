package com.controller;

import com.entity.User;
import com.util.ServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 登录功能
 * Create by HP on 2020/5/18
 * 游魂
 */
@Controller
public class LoginController extends ServiceInterface {
    @ResponseBody
    @RequestMapping("loginResult")
    public List<User> loginResult(String username, String pwd){
        User user = new User();
        user.setLoginname(username);
        user.setPassword(pwd);
        List<User> users = userService.selectByParam(user);
        return users;
    }

    @RequestMapping("loginInit")
    public String loginInit(String id){

        return "index";
    }
}
