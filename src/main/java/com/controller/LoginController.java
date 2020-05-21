package com.controller;

import com.entity.Menu;
import com.entity.User;
import com.util.ServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 登录功能
 * Create by HP on 2020/5/18
 * 游魂
 */
@Controller
@SessionAttributes(value = {"menus", "userMessage"})
public class LoginController extends ServiceInterface {
    @ResponseBody
    @RequestMapping("loginResult")
    public List<User> loginResult(String username, String pwd, Model model){
        User user = new User();
        user.setLoginname(username);
        user.setPassword(pwd);
        List<User> users = userService.selectByParam(user, 1, 1);
        if(0 != users.size()){
            model.addAttribute("userMessage", users.get(0));
        }
        return users;
    }

    @RequestMapping("loginInit")
    public String loginInit(String id, Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userMessage");
        if(null == user || !id.equals(user.getId().toString())){
            return "notLogin";
        }
        Menu menu = new Menu();
        menu.setId(Integer.valueOf(id));
        List<Menu> list = userMenuService.selectById(menu);
        model.addAttribute("menus", list);
        return "backIndex";
    }
}
