package com.controller;

import com.entity.Dept;
import com.github.pagehelper.PageInfo;
import com.util.PageUtil;
import com.util.ServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 部门控制层
 * Create by HP on 2020/5/19
 * 游魂
 */
@Controller
@RequestMapping("dept")
public class DeptController extends ServiceInterface {
    @RequestMapping(value = "selectAll", method = RequestMethod.POST)
    @ResponseBody
    public Map selectAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                         @RequestParam(required = false, defaultValue = "5") Integer limit){
        PageInfo<Dept> pageInfo = new PageInfo<>(deptService.selectAll(page, limit));
        return PageUtil.pack(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping(value = "selectByParam", method = RequestMethod.POST)
    @ResponseBody
    public Map selectByParam(@RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "5") Integer limit, HttpServletRequest request){
        Dept dept = new Dept();
        dept.setName(request.getParameter("key[name]"));
        PageInfo<Dept> pageInfo = new PageInfo<>(deptService.selectByParam(dept, page, limit));
        return PageUtil.pack(pageInfo.getTotal(), pageInfo.getList());
    }
}
