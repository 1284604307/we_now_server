package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Banner;

/**
 * app头部轮播图Mapper接口
 * 
 * @author ming
 * @date 2020-04-28
 */
public interface BannerMapper 
{
    /**
     * 查询app头部轮播图
     * 
     * @param id app头部轮播图ID
     * @return app头部轮播图
     */
    public Banner selectBannerById(Long id);

    /**
     * 查询app头部轮播图列表
     * 
     * @param banner app头部轮播图
     * @return app头部轮播图集合
     */
    public List<Banner> selectBannerList(Banner banner);

    /**
     * 新增app头部轮播图
     * 
     * @param banner app头部轮播图
     * @return 结果
     */
    public int insertBanner(Banner banner);

    /**
     * 修改app头部轮播图
     * 
     * @param banner app头部轮播图
     * @return 结果
     */
    public int updateBanner(Banner banner);

    /**
     * 删除app头部轮播图
     * 
     * @param id app头部轮播图ID
     * @return 结果
     */
    public int deleteBannerById(Long id);

    /**
     * 批量删除app头部轮播图
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBannerByIds(String[] ids);
}
