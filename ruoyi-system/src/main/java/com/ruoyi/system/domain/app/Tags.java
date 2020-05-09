package com.ruoyi.system.domain.app;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 标签表对象 tags
 * 
 * @author ming
 * @date 2020-05-09
 */
public class Tags extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 索引 */
    private Long id;

    /** 标签名 */
    @Excel(name = "标签名")
    private String name;

    /** 链接 */
    @Excel(name = "链接")
    private String url;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("url", getUrl())
            .toString();
    }
}
