package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(), userVo.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);
        PageInfo<User> userPageInfo = new PageInfo<>(allUserByPage);

        return userPageInfo;
    }

    @Override
    public void updateUserStatus(Integer id, String status) {

        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdate_time(new Date());

        userMapper.updateUserStatus(user);
    }

    @Override
    public User login(User user) throws Exception {

        User user2 = userMapper.login(user);

        if (user2 != null && Md5.verify(user.getPassword(), "lagou", user2.getPassword())) {
            return user2;
        } else {
            return null;
        }

    }

    @Override
    public List<Role> findUserRoleById(Integer id) {
        return userMapper.findUserRoleById(id);
    }

    @Override
    public void userContextRole(UserVo userVo) {

        userMapper.deleteUserContextRole(userVo.getUserId());

        for (Integer roleId: userVo.getRoleIdList()) {
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setCreatedTime(new Date());
            user_role_relation.setUpdatedTime(new Date());
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedBy("system");
            user_role_relation.setRoleId(roleId);
            user_role_relation.setUserId(userVo.getUserId());

            userMapper.userContextRole(user_role_relation);
        }
    }

    @Override
    public ResponseResult getUserPermission(Integer userId) {
        
        // 1.获取用户拥有的都角色信息
        List<Role> roleList = userMapper.findUserRoleById(userId);
        
        // 2.获取角色ID，保存到List中
        ArrayList<Integer> roleIds = new ArrayList<>();

        for (Role role : roleList) {
            roleIds.add(role.getId());
        }

        // 3.根据角色ID查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleIds(roleIds);

        // 4.查询父菜单关联的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }

        // 5.获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleIds(roleIds);

        // 6.封装数据并返回
        HashMap<String, Object> map = new HashMap<>();
        map.put("menuList", parentMenu);
        map.put("resourceList", resourceList);

        return new ResponseResult(true, 200, "获取用户权限信息成功", map);
    }
}
