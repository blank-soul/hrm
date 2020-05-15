package com.dao;

import com.entity.Job;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobDao {
    // 查询所有职位信息
    List<Job> selectAll();

    // 查询所有职位下的所有员工信息
    List<Job> selectJobEmp();

    // 分页查询
    List<Job> selectByPage(@Param("start") int start, @Param("pageSize") int pageSize);

    // 根据id查询
    List<Job> selectOne(int id);

    // 不定项查询
    List<Job> selectByParam(Job job);

    // 新增
    int insert(Job job);

    // 修改
    int update(Job job);

    // 删除
    int delete(int id);
}
