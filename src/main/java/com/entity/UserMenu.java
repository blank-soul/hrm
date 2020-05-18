package com.entity;

import lombok.Data;

import java.util.List;

/**
 * 用户权限表
 * Create by HP on 2020/5/18
 * 游魂
 */
@Data
public class UserMenu {
    private Integer uid;
    private Integer mid;
    private List<Menu> menus;
}
