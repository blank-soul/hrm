package com.service.impl;

import com.entity.Job;
import com.dao.JobDao;
import com.service.JobService;
import com.util.MyBatisUtil;

import java.util.List;

/**
 * Create by HP on 2020/5/12
 * 游魂
 */
public class JobServiceImpl implements JobService {
    JobDao jobDao = MyBatisUtil.getMapper(JobDao.class);

    @Override
    public List<Job> selectAll() {
        return jobDao.selectAll();
    }

    @Override
    public List<Job> selectJobEmp() {
        return jobDao.selectJobEmp();
    }

    @Override
    public List<Job> selectByPage(int start, int pageSize) {
        return jobDao.selectByPage(start, pageSize);
    }

    @Override
    public List<Job> selectOne(int id) {
        return jobDao.selectOne(id);
    }

    @Override
    public List<Job> selectByParam(Job job) {
        return jobDao.selectByParam(job);
    }

    @Override
    public int insert(Job job) {
        return jobDao.insert(job);
    }

    @Override
    public int update(Job job) {
        return jobDao.update(job);
    }

    @Override
    public int delete(int id) {
        return jobDao.delete(id);
    }

    public static void main(String[] args) {
        JobService jobService = new JobServiceImpl();
        Job obj = new Job();
        obj.setName("程序员");
//        obj.setRemark("美女程序员");
//        obj.setId(3);
        jobService.selectAll().forEach(dept -> {
            System.out.println(dept);
        });

//        int num = jobService.update(obj);
//        System.out.println(num);
    }
}
