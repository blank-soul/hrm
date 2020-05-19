package com.service.impl;

import com.entity.Menu;
import com.service.UserMenuService;
import com.util.DaoInterface;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限操作
 * Create by HP on 2020/5/19
 * 游魂
 */
@Service
public class UserMenuServiceImpl extends DaoInterface implements UserMenuService {

    @Override
    public List<Menu> selectById(Menu menu) {
        List<Menu> list = userMenuDao.selectById(menu);
        list.forEach(menu1 -> {
            menu.setPerentId(menu1.getId());
            List<Menu> list1 = userMenuDao.selectById(menu);
            menu1.setChild(list1);
        });
        return list;
    }
}
