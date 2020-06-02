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
import java.text.SimpleDateFormat;
import java.util.Date;
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
        Map<String, Object> map = PageUtil.pack(pageInfo.getTotal(), pageInfo.getList());
        return map;
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ResponseBody
    public Integer insert(HttpServletRequest request){
        Emp emp = new Emp();
        emp.setName(request.getParameter("empName"));
        emp.setCardId(request.getParameter("cardId"));
        emp.setAddress(request.getParameter("address"));
        emp.setPhone(request.getParameter("phone"));
        emp.setEmail(request.getParameter("email"));
        emp.setSex(request.getParameter("sex"));
        emp.setEducation(request.getParameter("education"));
        emp.setCreateDate(request.getParameter("createDate"));
        if(null == emp.getCreateDate() || "".equals(emp.getCreateDate())){
            emp.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        String deptId = request.getParameter("deptId");
        if(null != deptId && !"".equals(deptId)){
            emp.setDeptId(Integer.valueOf(deptId));
        }
        String jobId = request.getParameter("jobId");
        if(null != jobId && !"".equals(jobId)){
            emp.setJobId(Integer.valueOf(jobId));
        }
        Integer res = empService.insert(emp);
        return res;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Integer update(HttpServletRequest request){
        Emp emp = new Emp();
        emp.setId(Integer.valueOf(request.getParameter("empId")));
        emp.setName(request.getParameter("empName"));
        emp.setCardId(request.getParameter("cardId"));
        emp.setAddress(request.getParameter("address"));
        emp.setPhone(request.getParameter("phone"));
        emp.setEmail(request.getParameter("email"));
        emp.setSex(request.getParameter("sex"));
        emp.setEducation(request.getParameter("education"));
        emp.setCreateDate(request.getParameter("createDate"));
        String deptId = request.getParameter("userId");
        if(null != deptId && !"".equals(deptId)){
            emp.setDeptId(Integer.valueOf(deptId));
        }
        String jobId = request.getParameter("userId");
        if(null != jobId && !"".equals(jobId)){
            emp.setJobId(Integer.valueOf(jobId));
        }
        Integer res = empService.update(emp);
        return res;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Integer delete(HttpServletRequest request){
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer res = empService.delete(id);
        return res;
    }
}
