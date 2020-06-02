package com.controller;

import com.entity.Job;
import com.github.pagehelper.PageInfo;
import com.util.PageUtil;
import com.util.ServiceInterface;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 职能控制层
 * Create by HP on 2020/5/20
 * 游魂
 */
@Controller
@RequestMapping("job")
public class JobController extends ServiceInterface {
    @RequestMapping("selectAll")
    @ResponseBody
    public Map selectAll(@RequestParam(required = false, defaultValue = "1") Integer page,
                         @RequestParam(required = false, defaultValue = "5") Integer limit){
        PageInfo<Job> pageInfo = new PageInfo<>(jobService.selectAll(page, limit, true));
        return PageUtil.pack(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping(value = "selectAllJob", method = RequestMethod.POST)
    @ResponseBody
    public List<Job> selectAllJob(@RequestParam(required = false, defaultValue = "1") Integer page,
                                    @RequestParam(required = false, defaultValue = "5") Integer limit){
        List<Job> list = jobService.selectAll(page, limit, false);
        return list;
    }

    @RequestMapping(value = "selectByParam", method = RequestMethod.POST)
    @ResponseBody
    public Map selectByParam(@RequestParam(required = false, defaultValue = "1") Integer page,
                             @RequestParam(required = false, defaultValue = "5") Integer limit, HttpServletRequest request){
        Job job = new Job();
        job.setName(request.getParameter("key[name]"));
        PageInfo<Job> pageInfo = new PageInfo<>(jobService.selectByParam(job, page, limit));
        return PageUtil.pack(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ResponseBody
    public Integer insert(HttpServletRequest request){
        Job job = new Job();
        job.setName(request.getParameter("jobName"));
        job.setRemark(request.getParameter("jobRemark"));
        Integer res = jobService.insert(job);
        return res;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Integer update(HttpServletRequest request){
        Job job = new Job();
        job.setId(Integer.valueOf(request.getParameter("jobId")));
        job.setName(request.getParameter("jobName"));
        job.setRemark(request.getParameter("jobRemark"));
        Integer res = jobService.update(job);
        return res;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Integer delete(HttpServletRequest request){
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer res = jobService.delete(id);
        return res;
    }
}
