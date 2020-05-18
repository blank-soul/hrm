package com.util;

import com.dao.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * dao注入
 * Create by HP on 2020/5/18
 * 游魂
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-datasource.xml")
public class DaoInterface {
    @Autowired
    public DeptDao deptDao;

    @Autowired
    public DocumentDao documentDao;

    @Autowired
    public EmpDao empDao;

    @Autowired
    public JobDao jobDao;

    @Autowired
    public NoticeDao noticeDao;

    @Autowired
    public UserDao userDao;
}
