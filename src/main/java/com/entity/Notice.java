package com.entity;

import lombok.Data;

/**
 * 公告类
 * Create by HP on 2020/5/13
 * 游魂
 */
@Data
public class Notice {
    private Integer id;
    private String title;
    private String content;
    private String createDate;
    private Integer userId;
    private Integer status;
    private String username;
//    private User user;
}
