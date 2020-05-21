package com.controller;

import com.entity.Emp;
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
 * 员工控制层
 * Create by HP on 2020/5/20
 * 游魂
 */
@Controller
@RequestMapping("emp")
public class EmpController extends ServiceInterface {
    @RequestMapping("selectAll")
    @ResponseBody
    public Map selectAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                         @RequestParam(required = false, defaultValue = "5") Integer limit){
        PageInfo<Emp> pageInfo = new PageInfo<>(empService.selectAll(page, limit));
        return PageUtil.pack(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping(value = "selectByParam", method = RequestMethod.POST)
    @ResponseBody
    public Map selectByParam(@RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "5") Integer limit, HttpServletRequest request){
        Emp emp = new Emp();
        if(request.getParameter("key[jobId]") != ""){
            emp.setJobId(Integer.valueOf(request.getParameter("key[jobId]")));
        }
        if(request.getParameter("key[deptId]") != ""){
            emp.setDeptId(Integer.valueOf(request.getParameter("key[deptId]")));
        }
        emp.setName(request.getParameter("key[name]"));
        emp.setCardId(request.getParameter("key[cardId]"));
        emp.setPhone(request.getParameter("key[phone]"));
        emp.setSex(request.getParameter("key[sex]"));
        PageInfo<Emp> pageInfo = new PageInfo<>(empService.selectByParam(emp, page, limit));
        return PageUtil.pack(pageInfo.getTotal(), pageInfo.getList());
    }
}
