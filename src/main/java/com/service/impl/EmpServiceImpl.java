package com.service.impl;

import com.entity.Emp;
import com.service.EmpService;
import com.util.DaoInterface;

import java.util.List;

/**
 * 员工业务
 * Create by HP on 2020/5/11
 * 游魂
 */
public class EmpServiceImpl extends DaoInterface implements EmpService {
    @Override
    public List<Emp> selectAll() {
        return empDao.selectAll();
    }

    @Override
    public List<Emp> selectByPage(int start, int pageSize) {
        return empDao.selectByPage(start, pageSize);
    }

    @Override
    public List<Emp> selectOne(int id) {
        return empDao.selectOne(id);
    }

    @Override
    public List<Emp> selectByParam(Emp emp) {
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

    public static void main(String[] args) {
        EmpService empService = new EmpServiceImpl();
        Emp obj = new Emp();
//        obj.setDeptId(2);
//        obj.setJobId(2);
//        obj.setName("3");
//        obj.setCardId("004");
//        obj.setId(3);
        empService.selectAll().forEach(emp -> {
            System.out.println(emp);
        });
//        int num =  empService.delete(3);
//        System.out.println(num);
    }

}
