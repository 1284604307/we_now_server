package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.app.Articles;

/**
 * 文章管理Service接口
 * 
 * @author ming
 * @date 2020-05-09
 */
public interface IArticlesService 
{
    /**
     * 查询文章管理
     * 
     * @param id 文章管理ID
     * @return 文章管理
     */
    public Articles selectArticlesById(Long id);

    /**
     * 查询文章管理列表
     * 
     * @param articles 文章管理
     * @return 文章管理集合
     */
    public List<Articles> selectArticlesList(Articles articles);

    /**
     * 新增文章管理
     * 
     * @param articles 文章管理
     * @return 结果
     */
    public int insertArticles(Articles articles);

    /**
     * 修改文章管理
     * 
     * @param articles 文章管理
     * @return 结果
     */
    public int updateArticles(Articles articles);

    /**
     * 批量删除文章管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteArticlesByIds(String ids);

    /**
     * 删除文章管理信息
     * 
     * @param id 文章管理ID
     * @return 结果
     */
    public int deleteArticlesById(Long id);
}
