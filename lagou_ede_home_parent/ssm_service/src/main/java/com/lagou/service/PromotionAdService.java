package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;


public interface PromotionAdService {

    public PageInfo findAllAdByPage(PromotionAdVo adVo);

    /*
        新建广告信息
    */
    public void saveAd(PromotionAd promotionAd);

    /*
        修改广告信息
    */
    public void updateAd(PromotionAd promotionAd);

    /*
        修改广告信息
    */
    public PromotionAd findAdById(Integer id);

    /*
        更新广告上下线状态
    */
    public void updatePromotionAdStatus(Integer id, Integer status);
}
