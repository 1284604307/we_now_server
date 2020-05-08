package com.ruoyi.app.service;

import com.github.pagehelper.PageHelper;
import com.ruoyi.app.domain.Article;
import com.ruoyi.app.mapper.CircleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ming
 * @ClassName: ArticleService
 * @Description: (用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/5/2 9:20
 */
@Service
public class ArticleService {

    @Autowired
    CircleDao circleDao;

    public List<Article> topArticles(int num){
        PageHelper.startPage(1,num);
        return circleDao.getTopArticles();
    }

    public List<Article> articles(int page){
        PageHelper.startPage(page,10);
        return circleDao.getNormalArticles();
    }

}
