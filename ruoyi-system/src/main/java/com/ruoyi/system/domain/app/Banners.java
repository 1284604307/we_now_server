package com.ruoyi.system.domain.app;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * app头部banner对象 banners
 * 
 * @author ming
 * @date 2020-05-09
 */
public class Banners extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 索引 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 图片链接 */
    @Excel(name = "图片链接")
    private String url;

    /** 跳转类型 */
    @Excel(name = "跳转类型")
    private String type;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 跳转地址 */
    @Excel(name = "跳转地址")
    private String path;

    /** 跳转文章id */
    @Excel(name = "跳转文章id")
    private Long articleId;

    /** 轮播图作者 */
    @Excel(name = "轮播图作者")
    private String author;

    /** 轮播图组 */
    @Excel(name = "轮播图组")
    private Long dropId;

    /** 审查人 */
    @Excel(name = "审查人")
    private String reviewer;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

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
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
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
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
    }
    public void setArticleId(Long articleId) 
    {
        this.articleId = articleId;
    }

    public Long getArticleId() 
    {
        return articleId;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setDropId(Long dropId) 
    {
        this.dropId = dropId;
    }

    public Long getDropId() 
    {
        return dropId;
    }
    public void setReviewer(String reviewer) 
    {
        this.reviewer = reviewer;
    }

    public String getReviewer() 
    {
        return reviewer;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("url", getUrl())
            .append("type", getType())
            .append("description", getDescription())
            .append("path", getPath())
            .append("articleId", getArticleId())
            .append("author", getAuthor())
            .append("dropId", getDropId())
            .append("createTime", getCreateTime())
            .append("reviewer", getReviewer())
            .append("status", getStatus())
            .toString();
    }
}
