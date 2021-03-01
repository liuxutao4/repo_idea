package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;

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
}
