package com.service.impl;

import com.entity.User;
import com.dao.UserDao;
import com.github.pagehelper.PageHelper;
import com.service.UserService;
import com.util.DaoInterface;
import com.util.MyBatisUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Create by HP on 2020/5/13
 * 游魂
 */
@Service
public class UserServiceImpl extends DaoInterface implements UserService {

    @Override
    public List<User> selectAll(int pageNum, int pageSize, boolean flag) {
        if(flag){
            PageHelper.startPage(pageNum, pageSize);
        }
        return userDao.selectAll();
    }

    @Override
    public List<User> selectOne(int id) {
        return userDao.selectOne(id);
    }

    @Override
    public List<User> selectByParam(User user, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userDao.selectByParam(user);
    }

    @Override
    public Boolean login(User user) {
        if(0 != userDao.selectByParam(user).size()){
            return true;
        }
        return false;
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

    @Override
    public User queryByLoginname(String Loginname) {
        System.out.println(userDao);
        return userDao.queryByLoginname(Loginname);
    }

    @Override
    public Set<String> queryRoleByLoginname(String Loginname) {
        return userDao.queryRoleByLoginname(Loginname);
    }

    @Override
    public Set<String> queryPresByLoginname(String Loginname) {
        return userDao.queryPresByLoginname(Loginname);
    }

}
