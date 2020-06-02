package com.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 员工类
 * Create by HP on 2020/5/11
 * 游魂
 */
@Data
public class Emp implements Serializable {
    private Integer id;
    private Integer deptId;
    private Integer jobId;
    private String name;
    private String cardId;
    private String address;
    private String phone;
    private String email;
    private String sex;
    private String education;
    private String createDate;
    private Integer status;
    private String deptName;
    private String jobName;
//    private Dept dept;
//    private Job job;
}
