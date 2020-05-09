package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.app.Banners;

/**
 * app头部bannerService接口
 * 
 * @author ming
 * @date 2020-05-09
 */
public interface IBannersService 
{
    /**
     * 查询app头部banner
     * 
     * @param id app头部bannerID
     * @return app头部banner
     */
    public Banners selectBannersById(Long id);

    /**
     * 查询app头部banner列表
     * 
     * @param banners app头部banner
     * @return app头部banner集合
     */
    public List<Banners> selectBannersList(Banners banners);

    /**
     * 新增app头部banner
     * 
     * @param banners app头部banner
     * @return 结果
     */
    public int insertBanners(Banners banners);

    /**
     * 修改app头部banner
     * 
     * @param banners app头部banner
     * @return 结果
     */
    public int updateBanners(Banners banners);

    /**
     * 批量删除app头部banner
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBannersByIds(String ids);

    /**
     * 删除app头部banner信息
     * 
     * @param id app头部bannerID
     * @return 结果
     */
    public int deleteBannersById(Long id);
}
