package com.ruoyi.app.domain;

/**
 * @author ming
 * @ClassName: Article
 * @Description: (用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/26 16:21
 */

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.system.domain.app.UserInfo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Article {
    int id;
    String title;
    // desc 内容简述
    String prefix;
    // desc 内容
    String content;
    Date createTime;
    List<String> url;
    List<Comment> comments;
    // desc 类型分 本地 和 外链
    String type;
    String link;
    // desc 展示小图 如果有
    String envelopePic;
    // desc 标签 那些跟随的小件
    List<TagsBean> tags;
    // desc 访问量
    int visible;
    // desc 点赞数量
    long likeCount;
    long commentCount;
    // desc 当前用户关联标记
    boolean collect;
    boolean like;
    // desc 新文章 (权重++ )
    boolean fresh;
    // 置顶
    boolean top;
    // 原创
    boolean original;

    long userId;
    long top_id;
    long school_id;
    UserInfo user;

    // desc 扩展属性
    String extra;

    public void setUrl(Object url) {
        System.out.println(url);
        if (url instanceof List)
            this.url = (List<String>) url;
        else
            this.url = JSONObject.parseObject(url.toString(),List.class);
    }
}

@Data
class TagsBean {
    String name;
    String url;
}