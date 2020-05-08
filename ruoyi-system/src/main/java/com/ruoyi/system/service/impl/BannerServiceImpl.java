package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BannerMapper;
import com.ruoyi.system.domain.Banner;
import com.ruoyi.system.service.IBannerService;
import com.ruoyi.common.core.text.Convert;

/**
 * app头部轮播图Service业务层处理
 * 
 * @author ming
 * @date 2020-04-28
 */
@Service
public class BannerServiceImpl implements IBannerService 
{
    @Autowired
    private BannerMapper bannerMapper;

    /**
     * 查询app头部轮播图
     * 
     * @param id app头部轮播图ID
     * @return app头部轮播图
     */
    @Override
    public Banner selectBannerById(Long id)
    {
        return bannerMapper.selectBannerById(id);
    }

    /**
     * 查询app头部轮播图列表
     * 
     * @param banner app头部轮播图
     * @return app头部轮播图
     */
    @Override
    public List<Banner> selectBannerList(Banner banner)
    {
        return bannerMapper.selectBannerList(banner);
    }

    /**
     * 新增app头部轮播图
     * 
     * @param banner app头部轮播图
     * @return 结果
     */
    @Override
    public int insertBanner(Banner banner)
    {
        banner.setCreateTime(DateUtils.getNowDate());
        return bannerMapper.insertBanner(banner);
    }

    /**
     * 修改app头部轮播图
     * 
     * @param banner app头部轮播图
     * @return 结果
     */
    @Override
    public int updateBanner(Banner banner)
    {
        return bannerMapper.updateBanner(banner);
    }

    /**
     * 删除app头部轮播图对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBannerByIds(String ids)
    {
        return bannerMapper.deleteBannerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除app头部轮播图信息
     * 
     * @param id app头部轮播图ID
     * @return 结果
     */
    @Override
    public int deleteBannerById(Long id)
    {
        return bannerMapper.deleteBannerById(id);
    }
}
