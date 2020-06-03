package com.service.impl;

import com.entity.Menu;
import com.service.MenuService;
import com.util.DaoInterface;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by HP on 2020/6/2
 * 游魂
 */
@Service
public class MenuServiceImpl extends DaoInterface implements MenuService {
    @Override
    public List<Menu> selectAll() {
        return menuDao.selectAll();
    }
}
