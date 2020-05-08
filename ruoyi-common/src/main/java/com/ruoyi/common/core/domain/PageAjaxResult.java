package com.ruoyi.common.core.domain;

import com.github.pagehelper.Page;
import com.ruoyi.common.utils.StringUtils;

import java.util.HashMap;

/**
 * 操作消息提醒
 * 
 * @author ruoyi
 */
public class PageAjaxResult extends AjaxResult
{
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 状态类型
     */
    public enum Type
    {
        /** 成功 */
        SUCCESS(0),
        /** 警告 */
        WARN(301),
        /** 未登录 */
        NO_LOGIN(401),
        /** 没有权限 */
        NO_PERMISSION(403),
        /** 错误 */
        ERROR(500);
        private final int value;

        Type(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public PageAjaxResult()
    {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     * @param msg 返回内容
     */
    public PageAjaxResult(Type type, String msg)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     * @param msg 返回内容
     * @param data 数据对象
     */
    public PageAjaxResult(Type type, String msg, Object data)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     * 
     * @return 成功消息
     */
    public static PageAjaxResult success()
    {
        return PageAjaxResult.success("操作成功");
    }

    /**
     * 返回成功数据
     * 
     * @return 成功消息
     */
    public static PageAjaxResult success(Object data)
    {
        return PageAjaxResult.success("操作成功", data);
    }

    /**
     * 返回成功的分页数据
     *
     * @return 成功消息
     */
    public static PageAjaxResult success(Page data)
    {
        PageAjaxResult success = PageAjaxResult.success("操作成功", data.getResult());
        success.put("total",data.getTotal());
        success.put("page",data.getPageNum());
        success.put("pageSize",data.getPageSize());
        success.put("pages",data.getPages());
        return success;
    }

    /**
     * 返回成功消息
     * 
     * @param msg 返回内容
     * @return 成功消息
     */
    public static PageAjaxResult success(String msg)
    {
        return PageAjaxResult.success(msg, null);
    }

    /**
     * 返回成功消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static PageAjaxResult success(String msg, Object data)
    {
        return new PageAjaxResult(Type.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     * 
     * @param msg 返回内容
     * @return 警告消息
     */
    public static PageAjaxResult warn(String msg)
    {
        return PageAjaxResult.warn(msg, null);
    }

    /**
     * 返回警告消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static PageAjaxResult warn(String msg, Object data)
    {
        return new PageAjaxResult(Type.WARN, msg, data);
    }

    /**
     * 返回错误消息
     * 
     * @return
     */
    public static PageAjaxResult error()
    {
        return PageAjaxResult.error("操作失败");
    }

    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @return 警告消息
     */
    public static PageAjaxResult error(String msg)
    {
        return PageAjaxResult.error(msg, null);
    }

    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static PageAjaxResult error(String msg, Object data)
    {
        return new PageAjaxResult(Type.ERROR, msg, data);
    }
}
