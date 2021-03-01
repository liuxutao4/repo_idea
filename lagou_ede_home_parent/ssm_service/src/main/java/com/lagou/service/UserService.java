package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.*;

import java.util.List;

public interface UserService {

    /*
        用户分页&多条件组合查询
     */
    public PageInfo findAllUserByPage(UserVo userVo);

    /*
        更新用户状态
     */
    public void updateUserStatus(Integer id, String status);

    /*
        用户登录
     */
    public User login(User user) throws Exception;

    /*
        根据用户id查询角色信息
     */
    public List<Role> findUserRoleById(Integer id);

    /*
        分配角色
     */
    public void userContextRole(UserVo userVo);

    /*
        获取用户权限，进行菜单动态展示
     */
    public ResponseResult getUserPermission(Integer userId);
}
