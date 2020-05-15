package com.service.impl;

import com.entity.User;
import com.dao.UserDao;
import com.service.UserService;
import com.util.MyBatisUtil;

import java.util.List;

/**
 * Create by HP on 2020/5/13
 * 游魂
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = MyBatisUtil.getMapper(UserDao.class);

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public List<User> selectByPage(int start, int pageSize) {
        return userDao.selectByPage(start, pageSize);
    }

    @Override
    public List<User> selectOne(int id) {
        return userDao.selectOne(id);
    }

    @Override
    public List<User> selectByParam(User user) {
        return userDao.selectByParam(user);
    }

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }

    @Override
    public int delete(int id) {
        return userDao.delete(id);
    }

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        User obj = new User();
        obj.setUsername("r");
        obj.setStatus(2);
        obj.setId(6);
//        userService.selectByParam(obj).forEach(user -> {
//            System.out.println(user);
//        });
        int num = userService.delete(6);
        System.out.println(num);
    }
}
