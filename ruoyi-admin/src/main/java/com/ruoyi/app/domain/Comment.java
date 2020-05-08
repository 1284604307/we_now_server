package com.ruoyi.app.domain;

import com.ruoyi.system.domain.app.UserInfo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author ming
 * @ClassName: CircleComment
 * @Description: 动态的评论(用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/25 11:15
 */
@Data
public class Comment {
    private long cid;
    private long articleId;
    private String content;
    private Date createTime;
    private long pid;
    private long fromId;
    private long toId;
    private long likeCount;
    private List<Comment> children;
    UserInfo user;
}
