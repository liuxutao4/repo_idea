package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        return roleMapper.findAllRole(role);
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        return roleMapper.findMenuByRoleId(roleId);
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {

        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        for (Integer menuId : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(menuId);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            role_menu_relation.setCreatedTime(new Date());
            role_menu_relation.setUpdatedTime(new Date());
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedBy("system");

            roleMapper.roleContextMenu(role_menu_relation);
        }

    }

    @Override
    public void deleteRole(Integer roleId) {
        roleMapper.deleteRoleContextMenu(roleId);
        roleMapper.deleteRole(roleId);

    }

    @Override
    public void saveRole(Role role) {

        role.setCreatedTime(new Date());
        role.setUpdatedTime(new Date());
        role.setCreatedBy("system");
        role.setUpdatedBy("system");

        roleMapper.saveRole(role);

    }

    @Override
    public void updateRole(Role role) {

        role.setUpdatedTime(new Date());
        role.setUpdatedBy("system");

        roleMapper.updateRole(role);
    }

    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId) {

        List<Resource> resourceList = roleMapper.findResourceByRoleId(roleId);
        if (resourceList.isEmpty()) {
            return null;
        }

        List<ResourceCategory> resourceCategoryList = roleMapper.findResourceCategoryByRoleId(roleId);

        for (Resource resource : resourceList) {
            for (ResourceCategory resourceCategory : resourceCategoryList) {
                if (resource.getCategoryId().equals(resourceCategory.getId())) {
                    resourceCategory.getResourceList().add(resource);
                    break;
                }
            }
        }

        return resourceCategoryList;

    }

    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {

        roleMapper.deleteRoleContextResource(roleResourceVo.getRoleId());

        for (Integer ResourceId : roleResourceVo.getResourceIdList()) {
            Role_resource_relation role_resource_relation = new Role_resource_relation();
            role_resource_relation.setCreatedBy("system");
            role_resource_relation.setUpdatedBy("system");
            role_resource_relation.setCreatedTime(new Date());
            role_resource_relation.setUpdatedTime(new Date());
            role_resource_relation.setResourceId(ResourceId);
            role_resource_relation.setRoleId(roleResourceVo.getRoleId());

            roleMapper.roleContextResource(role_resource_relation);
        }

    }

}
