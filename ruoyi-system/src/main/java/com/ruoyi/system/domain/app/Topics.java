package com.ruoyi.system.domain.app;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 话题系统对象 topics
 * 
 * @author ming
 * @date 2020-05-09
 */
public class Topics extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 索引 */
    private Long id;

    /** 话题 */
    @Excel(name = "话题")
    private String topic;

    /** 描述 */
    @Excel(name = "描述")
    private String desc;

    /** 链接 */
    @Excel(name = "链接")
    private String url;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 标题 */
    @Excel(name = "标题")
    private String titile;

    /** 子标题 */
    @Excel(name = "子标题")
    private String subtitle;

    /** 关注量 */
    @Excel(name = "关注量")
    private Long fansCount;

    /** 访问量 */
    @Excel(name = "访问量")
    private String visible;

    /** 置顶 */
    @Excel(name = "置顶")
    private Integer top;

    /** 置顶时间 */
    @Excel(name = "置顶时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date niceTime;

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
    public void setTopic(String topic) 
    {
        this.topic = topic;
    }

    public String getTopic() 
    {
        return topic;
    }
    public void setDesc(String desc) 
    {
        this.desc = desc;
    }

    public String getDesc() 
    {
        return desc;
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
    public void setTitile(String titile) 
    {
        this.titile = titile;
    }

    public String getTitile() 
    {
        return titile;
    }
    public void setSubtitle(String subtitle) 
    {
        this.subtitle = subtitle;
    }

    public String getSubtitle() 
    {
        return subtitle;
    }
    public void setFansCount(Long fansCount) 
    {
        this.fansCount = fansCount;
    }

    public Long getFansCount() 
    {
        return fansCount;
    }
    public void setVisible(String visible) 
    {
        this.visible = visible;
    }

    public String getVisible() 
    {
        return visible;
    }
    public void setTop(Integer top) 
    {
        this.top = top;
    }

    public Integer getTop() 
    {
        return top;
    }
    public void setNiceTime(Date niceTime) 
    {
        this.niceTime = niceTime;
    }

    public Date getNiceTime() 
    {
        return niceTime;
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
            .append("topic", getTopic())
            .append("desc", getDesc())
            .append("url", getUrl())
            .append("type", getType())
            .append("titile", getTitile())
            .append("subtitle", getSubtitle())
            .append("fansCount", getFansCount())
            .append("visible", getVisible())
            .append("top", getTop())
            .append("createTime", getCreateTime())
            .append("niceTime", getNiceTime())
            .append("extra", getExtra())
            .toString();
    }
}
