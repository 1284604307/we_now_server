package com.ruoyi.app.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author ming
 * @ClassName: Topic
 * @Description: (用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/5/2 16:21
 */
@Data
public class Topic {

    private long id;
    String topic;
    String desc;
    String url;
    String type;
    String title;
    String subtitle;
    int fansCount;
    int visible;
    boolean top;
    Date niceTime;
    Date createTime;

}
