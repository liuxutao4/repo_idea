package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {


    /*
        分页获取所有的广告列表
    */
    public List<PromotionAd> findAllAdByPage();

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
    public void updatePromotionAdStatus(PromotionAd promotionAd);

}
