package com.ruoyi.app.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruoyi.app.domain.Article;
import com.ruoyi.app.domain.Comment;
import com.ruoyi.app.mapper.CircleDao;
import com.ruoyi.app.mapper.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ming
 * @ClassName: CircleService
 * @Description: (用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/22 11:44
 */
@Service
@Slf4j
public class CircleService {

    @Autowired
    CircleDao circleDao;
    @Autowired
    NormalUserService userService;

    public void createNewCircle(Article circle){
        circle.setExtra(new JSONObject());
        List topics = topicMatcher(circle.getContent());
        circle.getExtra().put("topics",topics);
        int circleId = circleDao.insertNew(circle);
        // 关联话题
        if(topics.size()>0)
            circleDao.topicArticleRelation(topics,circleId);

    }

    private List<String> topicMatcher(String str){
        List<String> strings = new ArrayList<>();
        Pattern p = Pattern.compile("#[\\s\\S]+?#");
        Matcher m = p.matcher(str);

        while (m.find()) {
            strings.add(m.group(0));
        }
        return strings;
    }

    public Page<Article> getCircles(int page){
        PageHelper.startPage(page,10);
        PageHelper.orderBy("createTime desc");
        return (Page<Article>)circleDao.getAll();
    }

    public Page<Article> getCirclesOrderByLikes(int page){
        PageHelper.startPage(page,5);
        PageHelper.orderBy("likeCount desc");
        return (Page<Article>)circleDao.getOriginalHotsAll();
    }

    public Page<Article> getCirclesBySchool(int page, long schoolId){
        PageHelper.startPage(page,10);
        PageHelper.orderBy("createTime desc");
        return (Page<Article>)circleDao.getAllBySchool(schoolId);
    }

    public Page<Article> getCirclesByTopic(int page, long topicId){
        PageHelper.startPage(page,10);
        return (Page<Article>)circleDao.getAllByTopicId(topicId);
    }

    public Article getCircle(long id) throws NullPointerException{
        Article circle = circleDao.getCircle(id);
        circle.setUser(userService.getUserInfoById(circle.getUserId()));
        return circle;
    }


    public int delCircle(long circleId, long userId) {

        int i = circleDao.delCircle(circleId, userId);
        if(i>0){
            int c = circleDao.delComments(circleId);
            log.info(circleId+"的动态被删除，且删除了多条评论 : Count-"+c);
            return circleDao.delCircle(circleId,userId);
        }
        return i;
    }

    public List<Article> getCirclesByTopicTop(Integer topicId) {
        return circleDao.getAllByTopicTop(topicId);
    }
}
