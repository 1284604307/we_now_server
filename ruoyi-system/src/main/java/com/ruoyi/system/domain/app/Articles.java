package com.ruoyi.system.domain.app;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 文章管理对象 articles
 * 
 * @author ming
 * @date 2020-05-09
 */
public class Articles extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 索引 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 简述 */
    @Excel(name = "简述")
    private String prefix;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    /**  创建时间*/
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createtime;

    /** 发布时间 */
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishtime;

    /** 链接 */
    @Excel(name = "链接")
    private String url;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 外链 */
    @Excel(name = "外链")
    private String link;

    /** 缩略展示图 */
    @Excel(name = "缩略展示图")
    private String envelopePic;

    /** 标签 */
    @Excel(name = "标签")
    private String tags;

    /** 查看人数 */
    @Excel(name = "查看人数")
    private Long visible;

    /** 是否收藏 */
    private String collect;

    /** 是否分享 */
    private String fresh;

    /** 是否喜欢 */
    private String like;

    /** 喜欢数 */
    @Excel(name = "喜欢数")
    private Long likecount;

    /** 评论数 */
    @Excel(name = "评论数")
    private Long commentcount;

    /** 是否置顶 */
    @Excel(name = "是否置顶")
    private Integer top;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userid;

    /** 话题ID */
    @Excel(name = "话题ID")
    private Long topicId;

    /** 校园ID */
    @Excel(name = "校园ID")
    private Long schoolId;

    /** 是否原创 */
    @Excel(name = "是否原创")
    private Integer original;

    /** 自定义json */
    @Excel(name = "自定义json")
    private String extra;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setPrefix(String prefix) 
    {
        this.prefix = prefix;
    }

    public String getPrefix() 
    {
        return prefix;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setPublishtime(Date publishtime) 
    {
        this.publishtime = publishtime;
    }

    public Date getPublishtime() 
    {
        return publishtime;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setLink(String link) 
    {
        this.link = link;
    }

    public String getLink() 
    {
        return link;
    }
    public void setEnvelopePic(String envelopePic) 
    {
        this.envelopePic = envelopePic;
    }

    public String getEnvelopePic() 
    {
        return envelopePic;
    }
    public void setTags(String tags) 
    {
        this.tags = tags;
    }

    public String getTags() 
    {
        return tags;
    }
    public void setVisible(Long visible) 
    {
        this.visible = visible;
    }

    public Long getVisible() 
    {
        return visible;
    }
    public void setCollect(String collect) 
    {
        this.collect = collect;
    }

    public String getCollect() 
    {
        return collect;
    }
    public void setFresh(String fresh) 
    {
        this.fresh = fresh;
    }

    public String getFresh() 
    {
        return fresh;
    }
    public void setLike(String like) 
    {
        this.like = like;
    }

    public String getLike() 
    {
        return like;
    }
    public void setLikecount(Long likecount) 
    {
        this.likecount = likecount;
    }

    public Long getLikecount() 
    {
        return likecount;
    }
    public void setCommentcount(Long commentcount) 
    {
        this.commentcount = commentcount;
    }

    public Long getCommentcount() 
    {
        return commentcount;
    }
    public void setTop(Integer top) 
    {
        this.top = top;
    }

    public Integer getTop() 
    {
        return top;
    }
    public void setUserid(Long userid) 
    {
        this.userid = userid;
    }

    public Long getUserid() 
    {
        return userid;
    }
    public void setTopicId(Long topicId) 
    {
        this.topicId = topicId;
    }

    public Long getTopicId() 
    {
        return topicId;
    }
    public void setSchoolId(Long schoolId) 
    {
        this.schoolId = schoolId;
    }

    public Long getSchoolId() 
    {
        return schoolId;
    }
    public void setOriginal(Integer original) 
    {
        this.original = original;
    }

    public Integer getOriginal() 
    {
        return original;
    }
    public void setExtra(String extra) 
    {
        this.extra = extra;
    }

    public String getExtra() 
    {
        return extra;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("prefix", getPrefix())
            .append("content", getContent())
            .append("publishtime", getPublishtime())
            .append("createtime", getCreatetime())
            .append("url", getUrl())
            .append("type", getType())
            .append("link", getLink())
            .append("envelopePic", getEnvelopePic())
            .append("tags", getTags())
            .append("visible", getVisible())
            .append("collect", getCollect())
            .append("fresh", getFresh())
            .append("like", getLike())
            .append("likecount", getLikecount())
            .append("commentcount", getCommentcount())
            .append("top", getTop())
            .append("userid", getUserid())
            .append("topicId", getTopicId())
            .append("schoolId", getSchoolId())
            .append("original", getOriginal())
            .append("extra", getExtra())
            .toString();
    }
}
