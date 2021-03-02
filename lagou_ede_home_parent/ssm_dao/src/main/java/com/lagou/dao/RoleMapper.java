package com.lagou.dao;

import com.lagou.domain.*;

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

    /*
        查询角色拥有的资源信息
     */
    public List<ResourceCategory> findResourceCategoryByRoleId(Integer roleId);

    /*
        查询角色拥有的资源信息
     */
    public List<Resource> findResourceByRoleId(Integer roleId);

    /*
        根据roleId清空中间表的关联关系
     */
    public void deleteRoleContextResource(Integer roleId);

    /*
        为角色分配资源
     */
    public void roleContextResource(Role_resource_relation role_resource_relation);


}
