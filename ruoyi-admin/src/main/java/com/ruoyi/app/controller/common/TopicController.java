package com.ruoyi.app.controller.common;

import com.github.pagehelper.Page;
import com.ruoyi.app.domain.Topic;
import com.ruoyi.app.service.TopicService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.PageAjaxResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ming
 * @ClassName: TopicController
 * @Description: (用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/5/2 15:54
 */
@RequestMapping("/public/topic")
@RestController
public class TopicController {

    @Autowired
    TopicService topicService;

    @GetMapping("/nice")
    public PageAjaxResult getNiceTopic(){
        List<Topic> niceTopics= topicService.getNiceTopic();
        return PageAjaxResult.success((Page)niceTopics);
    }

    @GetMapping({"/{page}",""})
    public PageAjaxResult getTopics(@PathVariable Integer page){
        page=page==null?0:page;
        List<Topic> niceTopics= topicService.getTopics(page);
        return PageAjaxResult.success((Page)niceTopics);
    }

    @GetMapping("/info/id/{id}")
    public AjaxResult getTopic(@PathVariable("id")long id){
        Topic topic = topicService.getTopic(id);
        return AjaxResult.success(topic);
    }

    @GetMapping("/info/name/{name}")
    public AjaxResult getTopicByName(@PathVariable("name")String name){
        Topic topic = topicService.getTopicByName(name);
        return AjaxResult.success(topic);
    }

    @ApiOperation("搜索话题")
    @GetMapping({"/search/{topic}","/search/"})
    public PageAjaxResult search(@PathVariable(value = "topic",required = false) String topic){
        if(topic!=null)
            topic = topic.trim();
        else
            topic = "";
        List<Topic> topics = topicService.searchTopics(topic);
        return PageAjaxResult.success(topics);
    }

}
