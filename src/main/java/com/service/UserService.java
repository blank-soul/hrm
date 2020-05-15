package com.service;

import com.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    // 查询所有用户信息
    List<User> selectAll();

    // 分页查询
    List<User> selectByPage(@Param("start") int start, @Param("pageSize") int pageSize);

    // 根据id查询
    List<User> selectOne(int id);

    // 不定项查询
    List<User> selectByParam(User user);

    // 新增
    int insert(User user);

    // 修改
    int update(User user);

    // 删除
    int delete(int id);


}
