package com.dao;

import com.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface UserDao {
    // 查询所有用户信息
    List<User> selectAll();

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

    /**
     * 根据登录名查询用户信息
     * @param Loginname 登录名
     * @return
     */
    User queryByLoginname(String Loginname);

    /**
     * 根据登录名查询用户角色信息
     * @param Loginname 登录名
     * @return
     */
    Set<String> queryRoleByLoginname(String Loginname);

    /**
     * 根据登录名查询用户权限信息
     * @param Loginname 登录名
     * @return
     */
    Set<String> queryPresByLoginname(String Loginname);

}
