package com.entity;

import lombok.Data;

/**
 * 文档类
 * Create by HP on 2020/5/13
 * 游魂
 */
@Data
public class Document {
    private Integer id;
    private String title;
    private String filename;
    private String remark;
    private String createDate;
    private Integer userId;
    private Integer status;
    private String username;
//    private User user;
}
