package com.controller;

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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
        PageInfo<User> pageInfo = new PageInfo<>(userService.selectAll(page, limit, true));
        return PageUtil.pack(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping("selectAllUser")
    @ResponseBody
    public List<User> selectAllUser(@RequestParam(required = false, defaultValue = "1") Integer page,
                         @RequestParam(required = false, defaultValue = "5") Integer limit){
        List<User> list = userService.selectAll(page, limit, false);
        return list;
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

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ResponseBody
    public Integer insert(HttpServletRequest request){
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setLoginname(request.getParameter("loginname"));
        user.setPassword(request.getParameter("password"));
        user.setCreateDate(request.getParameter("createDate"));
        if(null == user.getCreateDate() || "".equals(user.getCreateDate())){
            user.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        Integer res = userService.insert(user);
        return res;
    }
}
