package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {

    /*
        用户分页&多条件组合查询
     */
    public List<User> findAllUserByPage(UserVo userVo);

    /*
        更新用户状态
     */
    public void updateUserStatus(User user);

    /*
        用户登录
     */
    public User login(User user);

    /*
        根据用户id清空中间表
     */
    public void deleteUserContextRole(Integer userId);

    /*
        分配角色
     */
    public void userContextRole(User_Role_relation user_role_relation);

    /*
        根据用户id查询角色信息
     */
    public List<Role> findUserRoleById(Integer id);

    /*
        根据角色ID，查询角色所有拥有的父级菜单
     */
    public List<Menu> findParentMenuByRoleIds(List<Integer> ids);

    /*
        根据父ID，查询子菜单信息
     */
    public List<Menu> findSubMenuByPid(Integer pid);

    /*
        获取用户拥有的资源权限信息
     */
    public List<Resource> findResourceByRoleIds(List<Integer> ids);

}
