package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResourceVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory() {

        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();
        return new ResponseResult(true, 200, "查询所有资源分类信息成功", allResourceCategory);
    }

    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory) {

        if (resourceCategory.getId() == null) {
            resourceCategoryService.saveResourceCategory(resourceCategory);
            return new ResponseResult(true, 200, "新增资源分类成功", null);
        } else {
            resourceCategoryService.updateResourceCategory(resourceCategory);
            return new ResponseResult(true, 200, "修改资源分类成功", null);
        }
    }

    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id) {

        resourceCategoryService.deleteResourceCategory(id);
        return new ResponseResult(true, 200, "删除资源分类并删除资源成功", null);
    }
}
