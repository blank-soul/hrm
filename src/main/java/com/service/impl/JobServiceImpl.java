package com.service.impl;

import com.entity.Job;
import com.github.pagehelper.PageHelper;
import com.service.JobService;
import com.util.DaoInterface;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by HP on 2020/5/12
 * 游魂
 */
@Service
public class JobServiceImpl extends DaoInterface implements JobService {
    @Override
    public List<Job> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return jobDao.selectAll();
    }

    @Override
    public List<Job> selectJobEmp() {
        return jobDao.selectJobEmp();
    }

    @Override
    public List<Job> selectOne(int id) {
        return jobDao.selectOne(id);
    }

    @Override
    public List<Job> selectByParam(Job job, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
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

}
