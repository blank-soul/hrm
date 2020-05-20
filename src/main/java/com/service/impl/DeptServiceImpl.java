package com.service.impl;

import com.entity.Dept;
import com.github.pagehelper.PageHelper;
import com.service.DeptService;
import com.util.DaoInterface;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by HP on 2020/5/11
 * 游魂
 */
@Service
public class DeptServiceImpl extends DaoInterface implements DeptService {
    @Override
    public List<Dept> selectAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return deptDao.selectAll();
    }

    @Override
    public List<Dept> selectDeptEmp() {
        return deptDao.selectDeptEmp();
    }

    @Override
    public List<Dept> selectOne(int id) {
        return deptDao.selectOne(id);
    }

    @Override
    public List<Dept> selectByParam(Dept dept) {
        return deptDao.selectByParam(dept);
    }

    @Override
    public int insert(Dept dept) {
        return deptDao.insert(dept);
    }

    @Override
    public int update(Dept dept) {
        return deptDao.update(dept);
    }

    @Override
    public int delete(int id) {
        return deptDao.delete(id);
    }

}
