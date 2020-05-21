package com.service.impl;

import com.entity.Emp;
import com.github.pagehelper.PageHelper;
import com.service.EmpService;
import com.util.DaoInterface;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工业务
 * Create by HP on 2020/5/11
 * 游魂
 */
@Service
public class EmpServiceImpl extends DaoInterface implements EmpService {
    @Override
    public List<Emp> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return empDao.selectAll();
    }

    @Override
    public List<Emp> selectOne(int id) {
        return empDao.selectOne(id);
    }

    @Override
    public List<Emp> selectByParam(Emp emp, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return empDao.selectByParam(emp);
    }

    @Override
    public int insert(Emp emp) {
        return empDao.insert(emp);
    }

    @Override
    public int update(Emp emp) {
        return empDao.update(emp);
    }

    @Override
    public int delete(int id) {
        return empDao.delete(id);
    }
}
