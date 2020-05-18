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
    public List<Dept> selectAll() {
        return deptDao.selectAll();
    }

    @Override
    public List<Dept> selectDeptEmp() {
        return deptDao.selectDeptEmp();
    }

    @Override
    public List<Dept> selectByPage(int start, int pageSize) {
        PageHelper.startPage(start, pageSize);
        return deptDao.selectByPage();
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

    public static void main(String[] args) {
        DeptService deptService = new DeptServiceImpl();
//        Dept obj = new Dept();
//        obj.setName("部");
//        obj.setRemark("人事部");
//        obj.setId(3);

        deptService.selectByPage(2, 5).forEach(dept -> {
            System.out.println(dept);
        });

//        int num = deptService.delete(3);
//        System.out.println(num);
    }
}
