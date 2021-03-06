package com.service;

import com.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface UserService {
    // 查询所有用户信息
    List<User> selectAll(int pageNum, int pageSize, boolean flag);

    // 根据id查询
    List<User> selectOne(int id);

    // 不定项查询
    List<User> selectByParam(User user, int pageNum, int pageSize);

    // 系统登录
    Boolean login(User user);

    // 新增
    int insert(User user);

    // 修改
    int update(User user);

    // 删除
    int delete(int id);

    User queryByLoginname(String Loginname);

    Set<String> queryRoleByLoginname(String Loginname);

    Set<String> queryPresByLoginname(String Loginname);

}
