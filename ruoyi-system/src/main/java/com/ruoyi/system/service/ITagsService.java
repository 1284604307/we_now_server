package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.app.Tags;

/**
 * 标签表Service接口
 * 
 * @author ming
 * @date 2020-05-09
 */
public interface ITagsService 
{
    /**
     * 查询标签表
     * 
     * @param id 标签表ID
     * @return 标签表
     */
    public Tags selectTagsById(Long id);

    /**
     * 查询标签表列表
     * 
     * @param tags 标签表
     * @return 标签表集合
     */
    public List<Tags> selectTagsList(Tags tags);

    /**
     * 新增标签表
     * 
     * @param tags 标签表
     * @return 结果
     */
    public int insertTags(Tags tags);

    /**
     * 修改标签表
     * 
     * @param tags 标签表
     * @return 结果
     */
    public int updateTags(Tags tags);

    /**
     * 批量删除标签表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTagsByIds(String ids);

    /**
     * 删除标签表信息
     * 
     * @param id 标签表ID
     * @return 结果
     */
    public int deleteTagsById(Long id);
}
