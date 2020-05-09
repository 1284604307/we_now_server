package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TopicsMapper;
import com.ruoyi.system.domain.app.Topics;
import com.ruoyi.system.service.ITopicsService;

/**
 * 话题系统Service业务层处理
 * 
 * @author ming
 * @date 2020-05-09
 */
@Service
public class TopicsServiceImpl implements ITopicsService 
{
    @Autowired
    private TopicsMapper topicsMapper;

    /**
     * 查询话题系统
     * 
     * @param id 话题系统ID
     * @return 话题系统
     */
    @Override
    public Topics selectTopicsById(Long id)
    {
        return topicsMapper.selectTopicsById(id);
    }

    /**
     * 查询话题系统列表
     * 
     * @param topics 话题系统
     * @return 话题系统
     */
    @Override
    public List<Topics> selectTopicsList(Topics topics)
    {
        return topicsMapper.selectTopicsList(topics);
    }

    /**
     * 新增话题系统
     * 
     * @param topics 话题系统
     * @return 结果
     */
    @Override
    public int insertTopics(Topics topics)
    {
        topics.setCreateTime(DateUtils.getNowDate());
        return topicsMapper.insertTopics(topics);
    }

    /**
     * 修改话题系统
     * 
     * @param topics 话题系统
     * @return 结果
     */
    @Override
    public int updateTopics(Topics topics)
    {
        return topicsMapper.updateTopics(topics);
    }

    /**
     * 删除话题系统对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTopicsByIds(String ids)
    {
        return topicsMapper.deleteTopicsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除话题系统信息
     * 
     * @param id 话题系统ID
     * @return 结果
     */
    @Override
    public int deleteTopicsById(Long id)
    {
        return topicsMapper.deleteTopicsById(id);
    }
}
