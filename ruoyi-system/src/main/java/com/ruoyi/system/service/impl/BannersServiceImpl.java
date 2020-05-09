package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BannersMapper;
import com.ruoyi.system.domain.app.Banners;
import com.ruoyi.system.service.IBannersService;
import com.ruoyi.common.core.text.Convert;

/**
 * app头部bannerService业务层处理
 * 
 * @author ming
 * @date 2020-05-09
 */
@Service
public class BannersServiceImpl implements IBannersService 
{
    @Autowired
    private BannersMapper bannersMapper;

    /**
     * 查询app头部banner
     * 
     * @param id app头部bannerID
     * @return app头部banner
     */
    @Override
    public Banners selectBannersById(Long id)
    {
        return bannersMapper.selectBannersById(id);
    }

    /**
     * 查询app头部banner列表
     * 
     * @param banners app头部banner
     * @return app头部banner
     */
    @Override
    public List<Banners> selectBannersList(Banners banners)
    {
        return bannersMapper.selectBannersList(banners);
    }

    /**
     * 新增app头部banner
     * 
     * @param banners app头部banner
     * @return 结果
     */
    @Override
    public int insertBanners(Banners banners)
    {
        banners.setCreateTime(DateUtils.getNowDate());
        return bannersMapper.insertBanners(banners);
    }

    /**
     * 修改app头部banner
     * 
     * @param banners app头部banner
     * @return 结果
     */
    @Override
    public int updateBanners(Banners banners)
    {
        return bannersMapper.updateBanners(banners);
    }

    /**
     * 删除app头部banner对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBannersByIds(String ids)
    {
        return bannersMapper.deleteBannersByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除app头部banner信息
     * 
     * @param id app头部bannerID
     * @return 结果
     */
    @Override
    public int deleteBannersById(Long id)
    {
        return bannersMapper.deleteBannersById(id);
    }
}
