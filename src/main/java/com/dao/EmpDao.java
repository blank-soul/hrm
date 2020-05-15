package com.dao;

import com.entity.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpDao {
    // 查询所有员工信息
    List<Emp> selectAll();

    // 分页查询
    List<Emp> selectByPage(@Param("start") int start, @Param("pageSize") int pageSize);

    // 根据id查询
    List<Emp> selectOne(int id);

    // 不定项查询
    List<Emp> selectByParam(Emp emp);

    // 新增
    int insert(Emp emp);

    // 批量新增
    int batchInsert(List<Emp> list);

    // 修改
    int update(Emp emp);

    // 删除
    int delete(int id);
}
