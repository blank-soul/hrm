package com.entity;

import lombok.Data;

import java.util.List;

/**
 * 菜单/权限类
 * Create by HP on 2020/5/18
 * 游魂
 */
@Data
public class Menu {
    private Integer id;
    private String name;
    private String location;
    private Integer perentId;
    private List<Menu> child;
}
