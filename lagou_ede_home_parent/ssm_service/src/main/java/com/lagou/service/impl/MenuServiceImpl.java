package com.lagou.service.impl;

import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.Role;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> findSubMenuListByPid(Integer pid) {

        return menuMapper.findSubMenuListByPid(pid);
    }

    @Override
    public List<Menu> findAllMenu() {
        return menuMapper.findAllMenu();
    }

    @Override
    public Menu findMenuById(Integer id) {
        return menuMapper.findMenuById(id);
    }

    @Override
    public void saveMenu(Menu menu) {

        menu.setCreatedTime(new Date());
        menu.setUpdatedTime(new Date());
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");

        menuMapper.saveMenu(menu);

    }

    @Override
    public void updateMenu(Menu menu) {

        menu.setUpdatedTime(new Date());
        menu.setUpdatedBy("system");

        menuMapper.updateMenu(menu);
    }
}
