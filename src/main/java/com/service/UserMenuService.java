package com.service;

import com.entity.Menu;

import java.util.List;

public interface UserMenuService {
    public List<Menu> selectById(Menu menu);
}
