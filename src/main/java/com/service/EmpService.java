package com.service;


import com.entity.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpService {
    // 查询所有员工信息
    List<Emp> selectAll(int pageNum, int pageSize);

    // 根据id查询
    List<Emp> selectOne(int id);

    // 不定项查询
    List<Emp> selectByParam(Emp emp, int pageNum, int pageSize);

    // 新增
    int insert(Emp emp);

    // 修改
    int update(Emp emp);

    // 删除
    int delete(int id);
}
