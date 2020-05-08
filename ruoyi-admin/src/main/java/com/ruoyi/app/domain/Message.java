package com.ruoyi.app.domain;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * @author ming
 * @ClassName: Message
 * @Description: 消息数据结构(用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/26 23:15
 */
@Data
public class Message {
    @Id
    long id;
    String type;
    int targetId;
    String targetType;
    int senderId;
    String senderType;
    boolean isRead;
    String avatarId;
    Date createTime;
    String action;
    String content;
}
