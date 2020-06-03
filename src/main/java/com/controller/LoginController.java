package com.controller;

import com.entity.Menu;
import com.util.ServiceInterface;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 登录功能
 * Create by HP on 2020/5/18
 * 游魂
 */
@Controller
@SessionAttributes(value = {"menus"})
public class LoginController extends ServiceInterface {
    @RequestMapping("/login")
    public String login(String username, String password, String remember, HttpServletResponse response){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        if(null != remember){
            token.setRememberMe(true);
        }
        try {
            subject.login(token);
            response.getWriter().print("<script>alert('登录成功！')</script>");
            return "redirect:/loginPres";
        } catch (AuthenticationException | IOException e){
            e.printStackTrace();
            System.out.println("登录失败！");
            return "redirect:/login.jsp";
        }
    }

    @RequestMapping("/loginPres")
    public String loginPres(HttpServletRequest request, Model model){
        List<Menu> list = menuService.selectAll();
        model.addAttribute("menus", list);
        return "redirect:/backIndex.jsp";
    }
}
