package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.app.Topics;

/**
 * 话题系统Mapper接口
 * 
 * @author ming
 * @date 2020-05-09
 */
public interface TopicsMapper 
{
    /**
     * 查询话题系统
     * 
     * @param id 话题系统ID
     * @return 话题系统
     */
    public Topics selectTopicsById(Long id);

    /**
     * 查询话题系统列表
     * 
     * @param topics 话题系统
     * @return 话题系统集合
     */
    public List<Topics> selectTopicsList(Topics topics);

    /**
     * 新增话题系统
     * 
     * @param topics 话题系统
     * @return 结果
     */
    public int insertTopics(Topics topics);

    /**
     * 修改话题系统
     * 
     * @param topics 话题系统
     * @return 结果
     */
    public int updateTopics(Topics topics);

    /**
     * 删除话题系统
     * 
     * @param id 话题系统ID
     * @return 结果
     */
    public int deleteTopicsById(Long id);

    /**
     * 批量删除话题系统
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTopicsByIds(String[] ids);
}
