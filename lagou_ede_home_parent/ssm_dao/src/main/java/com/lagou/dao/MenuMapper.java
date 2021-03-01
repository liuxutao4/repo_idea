package com.lagou.dao;

import com.lagou.domain.Menu;
import com.lagou.domain.Role;

import java.util.List;

public interface MenuMapper {

    /*
        查询全部父子菜单信息
     */
    public List<Menu> findSubMenuListByPid(Integer pid);

    /*
        查询全部菜单信息
     */
    public List<Menu> findAllMenu();

    /*
        查询id查询菜单信息
     */
    public Menu findMenuById(Integer id);

    /*
        新增菜单
     */
    public void saveMenu(Menu menu);

    /*
        更新菜单
     */
    public void updateMenu(Menu menu);
}
