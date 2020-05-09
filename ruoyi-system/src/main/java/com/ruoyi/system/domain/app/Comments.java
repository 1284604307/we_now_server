package com.ruoyi.system.domain.app;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 评论表对象 comments
 * 
 * @author ming
 * @date 2020-05-09
 */
public class Comments extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论id */
    private Long cid;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 文章ID */
    @Excel(name = "文章ID")
    private String articleId;

    /** 父评论id */
    @Excel(name = "父评论id")
    private Long pid;

    /** 回复人ID */
    @Excel(name = "回复人ID")
    private Long fromid;

    /** 被回复人ID */
    @Excel(name = "被回复人ID")
    private Long toid;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long likecount;

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    /**  创建时间*/
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createtime;


    public void setCid(Long cid) 
    {
        this.cid = cid;
    }

    public Long getCid() 
    {
        return cid;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setArticleId(String articleId) 
    {
        this.articleId = articleId;
    }

    public String getArticleId() 
    {
        return articleId;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setFromid(Long fromid) 
    {
        this.fromid = fromid;
    }

    public Long getFromid() 
    {
        return fromid;
    }
    public void setToid(Long toid) 
    {
        this.toid = toid;
    }

    public Long getToid() 
    {
        return toid;
    }
    public void setLikecount(Long likecount) 
    {
        this.likecount = likecount;
    }

    public Long getLikecount() 
    {
        return likecount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cid", getCid())
            .append("content", getContent())
            .append("createtime", getCreatetime())
            .append("articleId", getArticleId())
            .append("pid", getPid())
            .append("fromid", getFromid())
            .append("toid", getToid())
            .append("likecount", getLikecount())
            .toString();
    }
}
