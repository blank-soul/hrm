package com.controller;

import com.entity.Dept;
import com.entity.User;
import com.github.pagehelper.PageInfo;
import com.util.PageUtil;
import com.util.ServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户控制层
 * Create by HP on 2020/5/20
 * 游魂
 */
@Controller
@RequestMapping("user")
public class UserController extends ServiceInterface {
    @RequestMapping("selectAll")
    @ResponseBody
    public Map selectAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                         @RequestParam(required = false, defaultValue = "5") Integer limit){
        PageInfo<User> pageInfo = new PageInfo<>(userService.selectAll(page, limit));
        return PageUtil.pack(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping(value = "selectByParam", method = RequestMethod.POST)
    @ResponseBody
    public Map selectByParam(@RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "5") Integer limit, HttpServletRequest request){
        User user = new User();
        user.setUsername(request.getParameter("key[username]"));
        if(request.getParameter("key[status]") != ""){
            user.setStatus(Integer.valueOf(request.getParameter("key[status]")));
        }
        PageInfo<User> pageInfo = new PageInfo<>(userService.selectByParam(user, page, limit));
        return PageUtil.pack(pageInfo.getTotal(), pageInfo.getList());
    }
}
