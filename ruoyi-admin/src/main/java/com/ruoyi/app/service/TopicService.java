package com.ruoyi.app.service;

import com.github.pagehelper.PageHelper;
import com.ruoyi.app.domain.Topic;
import com.ruoyi.app.mapper.CircleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ming
 * @ClassName: TopicService
 * @Description: (用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/5/2 16:35
 */
@Service
public class TopicService {

    @Autowired
    CircleDao circleDao;

    public List<Topic> getNiceTopic() {

        String where = "where top = 'true'";
        PageHelper.startPage(0,5);
        PageHelper.orderBy("nice_time desc");
        return circleDao.queryNiceTopics();
    }

    public List<Topic> getTopics(int page) {

        PageHelper.startPage(page,10);
        PageHelper.orderBy("nice_time desc");
        return circleDao.queryTopics();
    }

    public Topic getTopic(long id){
        return circleDao.queryTopic(id);
    }

    public List<Topic> searchTopics(String topic) {
        PageHelper.startPage(0,20);
        return  circleDao.searchTopics(topic);
    }

    public Topic getTopicByName(String name) {
        return circleDao.queryTopicByName(name);
    }
}
