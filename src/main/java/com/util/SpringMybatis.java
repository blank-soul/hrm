package com.util;

import com.dao.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * MyBatis-Spring整合
 * Create by HP on 2020/5/14
 * 游魂
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-datasource.xml")
public class SpringMybatis {
    @Autowired
    DeptDao deptDao;

    @Autowired
    DocumentDao documentDao;

    @Autowired
    EmpDao empDao;

    @Autowired
    JobDao jobDao;

    @Autowired
    NoticeDao noticeDao;

    @Autowired
    UserDao userDao;

    @Test
    public void test(){
        empDao.selectAll().forEach(emp -> {
            System.out.println(emp);
        });
    }
}
