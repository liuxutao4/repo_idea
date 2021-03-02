package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceCategoryMapper {

    /*
        查询所有资源分类信息
     */
    public List<ResourceCategory> findAllResourceCategory();

    /*
        新增资源分类
     */
    public void saveResourceCategory(ResourceCategory resourceCategory);

    /*
        修改资源分类
     */
    public void updateResourceCategory(ResourceCategory resourceCategory);

    /*
        根据资源id删除资源
     */
    public void deleteResourceByCategoryId(Integer categoryId);

    /*
        删除资源分类
     */
    public void deleteResourceCategory(Integer id);


}
