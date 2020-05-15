package com.entity;

import lombok.Data;

/**
 * 用户类
 * Create by HP on 2020/5/13
 * 游魂
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String loginname;
    private String password;
    private Integer status;
    private String createDate;
}
