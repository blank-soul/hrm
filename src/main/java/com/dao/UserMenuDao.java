package com.dao;

import com.entity.Menu;

import java.util.List;

public interface UserMenuDao {
    public List<Menu> selectById(Menu menu);
}
