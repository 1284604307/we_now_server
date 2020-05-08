package com.ruoyi.framework.shiro.service;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.exception.user.UserPasswordRetryLimitExceedException;
import com.ruoyi.common.exception.user.UserTicketNotMatchException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 登录密码方法
 * 
 * @author ruoyi
 */
@Component
public class SysTicketService
{
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private SysUserMapper userMapper;

    private Cache<String, AtomicInteger> loginRecordCache;

    @PostConstruct
    public void init()
    {
        loginRecordCache = cacheManager.getCache(ShiroConstants.LOGINRECORDCACHE);
    }

    public void validate(SysUser user, String ticket)
    {
        String loginName = user.getLoginName();

        if (!matches(user, ticket))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.count", 0)));
            throw new UserTicketNotMatchException();
        }
        else
        {
            clearLoginRecordCache(loginName);
        }
    }

    public boolean matches(SysUser user, String ticket)
    {
        String daoTicket = user.getTicket();
        if(daoTicket==null||daoTicket.equals("null")) return false;
        return daoTicket.equals(ticket);
    }

    public void clearLoginRecordCache(String username)
    {
        loginRecordCache.remove(username);
    }


    public String newTicket(String username) {
        String uuid = UUID.randomUUID().toString();
        userMapper.updateTicket(uuid,username);
        return uuid;
    }

    public void clearTicket(String username) {
        userMapper.updateTicket("null",username);
    }
}
