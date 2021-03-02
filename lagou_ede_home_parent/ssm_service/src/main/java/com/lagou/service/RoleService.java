package com.lagou.service;

import com.lagou.domain.*;

import java.util.List;

public interface RoleService {

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
    public void roleContextMenu(RoleMenuVo roleMenuVo);

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
        获取当前角色拥有的资源分类和资源信息
     */
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId);

    /*
        为角色分配资源
     */
    public void roleContextResource(RoleResourceVo roleResourceVo);

}
