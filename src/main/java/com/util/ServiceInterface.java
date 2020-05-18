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
    DeptService deptService;

    @Autowired
    DocumentService documentService;

    @Autowired
    EmpService empService;

    @Autowired
    JobService jobService;

    @Autowired
    NoticeService noticeService;

    @Autowired
    UserService userService;
}
