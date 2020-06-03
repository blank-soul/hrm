package com.util;

import com.service.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * service注入
 * Create by HP on 2020/5/18
 * 游魂
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class ServiceInterface {
    @Autowired
    public DeptService deptService;

    @Autowired
    public DocumentService documentService;

    @Autowired
    public EmpService empService;

    @Autowired
    public JobService jobService;

    @Autowired
    public MenuService menuService;

    @Autowired
    public NoticeService noticeService;

    @Autowired
    public UserService userService;
}
