package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TagsMapper;
import com.ruoyi.system.domain.app.Tags;
import com.ruoyi.system.service.ITagsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 标签表Service业务层处理
 * 
 * @author ming
 * @date 2020-05-09
 */
@Service
public class TagsServiceImpl implements ITagsService 
{
    @Autowired
    private TagsMapper tagsMapper;

    /**
     * 查询标签表
     * 
     * @param id 标签表ID
     * @return 标签表
     */
    @Override
    public Tags selectTagsById(Long id)
    {
        return tagsMapper.selectTagsById(id);
    }

    /**
     * 查询标签表列表
     * 
     * @param tags 标签表
     * @return 标签表
     */
    @Override
    public List<Tags> selectTagsList(Tags tags)
    {
        return tagsMapper.selectTagsList(tags);
    }

    /**
     * 新增标签表
     * 
     * @param tags 标签表
     * @return 结果
     */
    @Override
    public int insertTags(Tags tags)
    {
        return tagsMapper.insertTags(tags);
    }

    /**
     * 修改标签表
     * 
     * @param tags 标签表
     * @return 结果
     */
    @Override
    public int updateTags(Tags tags)
    {
        return tagsMapper.updateTags(tags);
    }

    /**
     * 删除标签表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTagsByIds(String ids)
    {
        return tagsMapper.deleteTagsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除标签表信息
     * 
     * @param id 标签表ID
     * @return 结果
     */
    @Override
    public int deleteTagsById(Long id)
    {
        return tagsMapper.deleteTagsById(id);
    }
}
