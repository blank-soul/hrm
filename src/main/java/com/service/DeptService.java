package com.service;

import com.entity.Dept;

import java.util.List;

public interface DeptService {
    // 查询所有部门信息
    List<Dept> selectAll(int pageNum, int pageSize, boolean flag);

    // 查询所有部门下的所有员工信息
    List<Dept> selectDeptEmp();

    // 根据id查询
    List<Dept> selectOne(int id);

    // 不定项查询
    List<Dept> selectByParam(Dept dept, int pageNum, int pageSize);

    // 新增
    int insert(Dept dept);

    // 修改
    int update(Dept dept);

    // 删除
    int delete(int id);
}
