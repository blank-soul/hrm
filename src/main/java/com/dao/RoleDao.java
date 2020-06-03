package com.dao;

import com.entity.Role;

import java.util.List;

public interface RoleDao {
    List<Role> selectAll();

    int addRole();

    int addPrems();
}
