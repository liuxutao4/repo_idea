package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    @RequestMapping("/findAllAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVo adVo) {

        PageInfo allAdByPage = promotionAdService.findAllAdByPage(adVo);

        return new ResponseResult(true, 200, "分页查询广告信息成功", allAdByPage);
    }

    /*
        文件上传
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult PromotionAdUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        // 判断接收到的上传文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException();
        }

        // 获取项目部署路径
        String realPath = request.getServletContext().getRealPath("/");

        String substring = realPath.substring(0, realPath.indexOf("ssm_web"));

        // 获取原文件名
        String originalFilename = file.getOriginalFilename();

        // 生成新文件名
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 文件上传
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        // 如果目录不存在就创建目录
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录：" + filePath);
        }

        // 图片进行真正得到上传
        file.transferTo(filePath);

        // 将文件名和文件路径返回，进行响应
        Map<String, String> map = new HashMap<>();
        map.put("filename", newFileName);
        map.put("filePath", "http://localhost:8080/upload/" + newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);

        return responseResult;
    }

    /*
        文件上传
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {

        if (null == promotionAd.getId()) {
            promotionAdService.saveAd(promotionAd);
            return new ResponseResult(true, 200, "保存广告成功", null);
        } else {
            promotionAdService.updateAd(promotionAd);
            return new ResponseResult(true, 200, "修改广告成功", null);
        }

    }

    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(Integer id) {

        PromotionAd ad = promotionAdService.findAdById(id);
        return new ResponseResult(true,200,"根据id查询广告信息成功",ad);
    }

    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id, Integer status) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("status", status);

        return new ResponseResult(true,200,"更新广告上下线成功",map);
    }
}
