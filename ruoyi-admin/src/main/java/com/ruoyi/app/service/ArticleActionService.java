package com.ruoyi.app.service;

import com.ruoyi.app.mapper.CircleDao;
import com.ruoyi.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author ming
 * @Description: 喜欢和收藏
 * @email 17564500258@163.com
 * @Date 2020/4/30 16:38
 */
@Service
public class ArticleActionService {

    @Autowired
    CircleDao circleDao;

    public void like(@PathVariable("articleId") long articleId){

        if (circleDao.selectArticle(articleId)<=0) throw new NullPointerException();
        else {
            int i = circleDao.likeArticle(articleId,ShiroUtils.getUserId());
            if (i>0)
                circleDao.addLikes(articleId);
        }
    }

    public void unlike(@PathVariable("articleId") long articleId){

        if (circleDao.selectArticle(articleId)<=0) throw new NullPointerException();
        else {
            int i = circleDao.unlikeArticle(articleId,ShiroUtils.getUserId());
            if (i>0)
                circleDao.subLikes(articleId);
        }
    }

    public void collect(@PathVariable("articleId") long articleId){

        if (circleDao.selectArticle(articleId)<=0) throw new NullPointerException();
        else {
            circleDao.collectArticle(articleId, ShiroUtils.getUserId());
        }
    }

    public void unCollect(@PathVariable("articleId") long articleId){

        if (circleDao.selectArticle(articleId)<=0) throw new NullPointerException();
        circleDao.unCollectArticle(articleId, ShiroUtils.getUserId());
    }

    public List<Integer> getUserAllCollect(){

        return circleDao.getAllCollect(ShiroUtils.getUserId());
    }

    public List<Integer> getUserAllLikes(){

        return circleDao.getAllLikes(ShiroUtils.getUserId());
    }

    public Integer getLikeCount(long articleId){
        return circleDao.getLikeCount(articleId);
    }

    public boolean isCollect( long articleId){
        return circleDao.getCollectRow(articleId,ShiroUtils.getUserId())!=null;
    }

    public boolean isLike( long articleId){
        return circleDao.getLikeRow(articleId,ShiroUtils.getUserId())!=null;
    }
}
