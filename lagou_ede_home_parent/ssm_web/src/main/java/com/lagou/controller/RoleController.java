package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role) {

        List<Role> allRole = roleService.findAllRole(role);

        return new ResponseResult(true, 200, "查询所有角色成功", allRole);
    }

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {

        // 表示查询所有父子级菜单
        List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

        // 响应数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList", subMenuListByPid);

        return new ResponseResult(true, 200, "查询所有父子菜单信息成功", map);
    }

    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId) {

        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);
        return new ResponseResult(true, 200, "根据ID查询角色关联菜单信息成功", menuByRoleId);
    }

    @RequestMapping("/roleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVo roleMenuVo) {

        roleService.roleContextMenu(roleMenuVo);

        return new ResponseResult(true, 200, "更新角色关联菜单信息成功", null);
    }

    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer roleId) {

        roleService.deleteRole(roleId);

        return new ResponseResult(true, 200, "删除角色成功", null);
    }

    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role) {

        if (role.getId() == null ) {
            roleService.saveRole(role);
            return new ResponseResult(true, 200, "添加角色成功", null);
        } else {
            roleService.updateRole(role);
            return new ResponseResult(true, 200, "更新角色成功", null);
        }


    }

}
