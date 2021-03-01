package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;

    @Override
    public PageInfo findAllAdByPage(PromotionAdVo adVo) {

        PageHelper.startPage(adVo.getCurrentPage(), adVo.getPageSize());
        List<PromotionAd> adByPage = promotionAdMapper.findAllAdByPage();
        PageInfo<PromotionAd> adPageInfo = new PageInfo<>(adByPage);
        return adPageInfo;
    }

    @Override
    public void saveAd(PromotionAd promotionAd) {

        Date date = new Date();

        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);

        promotionAdMapper.saveAd(promotionAd);
    }

    @Override
    public void updateAd(PromotionAd promotionAd) {
        promotionAd.setUpdateTime(new Date());

        promotionAdMapper.updateAd(promotionAd);
    }

    @Override
    public PromotionAd findAdById(Integer id) {

        return promotionAdMapper.findAdById(id);
    }

    @Override
    public void updatePromotionAdStatus(Integer id, Integer status) {

        PromotionAd promotionAd = new PromotionAd();

        promotionAd.setUpdateTime(new Date());
        promotionAd.setId(id);
        promotionAd.setStatus(status);

        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }

}
