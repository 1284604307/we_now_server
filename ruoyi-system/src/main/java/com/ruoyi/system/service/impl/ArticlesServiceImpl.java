package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ArticlesMapper;
import com.ruoyi.system.domain.app.Articles;
import com.ruoyi.system.service.IArticlesService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文章管理Service业务层处理
 * 
 * @author ming
 * @date 2020-05-09
 */
@Service
public class ArticlesServiceImpl implements IArticlesService 
{
    @Autowired
    private ArticlesMapper articlesMapper;

    /**
     * 查询文章管理
     * 
     * @param id 文章管理ID
     * @return 文章管理
     */
    @Override
    public Articles selectArticlesById(Long id)
    {
        return articlesMapper.selectArticlesById(id);
    }

    /**
     * 查询文章管理列表
     * 
     * @param articles 文章管理
     * @return 文章管理
     */
    @Override
    public List<Articles> selectArticlesList(Articles articles)
    {
        return articlesMapper.selectArticlesList(articles);
    }

    /**
     * 新增文章管理
     * 
     * @param articles 文章管理
     * @return 结果
     */
    @Override
    public int insertArticles(Articles articles)
    {
        return articlesMapper.insertArticles(articles);
    }

    /**
     * 修改文章管理
     * 
     * @param articles 文章管理
     * @return 结果
     */
    @Override
    public int updateArticles(Articles articles)
    {
        return articlesMapper.updateArticles(articles);
    }

    /**
     * 删除文章管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteArticlesByIds(String ids)
    {
        return articlesMapper.deleteArticlesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文章管理信息
     * 
     * @param id 文章管理ID
     * @return 结果
     */
    @Override
    public int deleteArticlesById(Long id)
    {
        return articlesMapper.deleteArticlesById(id);
    }
}
