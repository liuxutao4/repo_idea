package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    /*
        查询所有角色（条件）
     */
    public List<Role> findAllRole(Role role);

    /*
        根据角色ID查询菜单信息
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*
        角色菜单关联
     */
    public void roleContextMenu(Role_menu_relation role_menu_relation);

    /*
        根据roleId清空中间表的关联关系
     */
    public void deleteRoleContextMenu(Integer roleId);

    /*
        根据roleId删除角色
     */
    public void deleteRole(Integer roleId);

    /*
        新增角色
     */
    public void saveRole(Role role);

    /*
        更新角色
     */
    public void updateRole(Role role);


}
