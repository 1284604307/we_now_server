package com.ruoyi.framework.shiro.web.filter;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.domain.AuthType;
import com.ruoyi.framework.shiro.service.SysTicketService;
import com.ruoyi.framework.util.SpringBeanFactoryUtils;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 退出过滤器
 * 
 * @author ruoyi
 */
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter
{
    private static final Logger log = LoggerFactory.getLogger(LogoutFilter.class);

    /**
     * 退出后重定向的地址
     */
    private String loginUrl;

    private Cache<String, Deque<Serializable>> cache;

    private SysTicketService ticketService;

    public String getLoginUrl()
    {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl)
    {
        this.loginUrl = loginUrl;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception
    {
        if(ticketService==null)
            ticketService = SpringBeanFactoryUtils.getBean(SysTicketService.class);

        SysUser user = ShiroUtils.getSysUser();

        boolean isApp = false;

        HttpServletRequest httpServletRequest = (HttpServletRequest)request;

        if (httpServletRequest.getHeader(AuthType.name)!=null){
            isApp = httpServletRequest.getHeader(AuthType.name).equals(AuthType.APP_MATH.name());
        }

        try
        {
            Subject subject = getSubject(request, response);
            String redirectUrl = getRedirectUrl(request, response, subject);
            try
            {
                if (StringUtils.isNotNull(user))
                {
                    String loginName = user.getLoginName();
                    // 记录用户退出日志
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGOUT, MessageUtils.message("user.logout.success")));
                    // 清理缓存
                    cache.remove(loginName);
                }
                // 退出登录
                subject.logout();
            }
            catch (SessionException ise)
            {
                log.error("logout fail.", ise);
            }

            if(isApp){
                try {
                    ticketService.clearTicket(user.getLoginName());
                }catch (Exception e){
                    log.error("未清除ticket，用户未登录", e);
                }

                PrintWriter out;
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                out = response.getWriter();
                Map<String, Object> map= new HashMap<>();
                map.put("code", 0);
                map.put("msg", "登出成功");
                out.write(JSON.toJSONString(map));
                out.close();
            }else
                issueRedirect(request, response, redirectUrl);
        }
        catch (Exception e)
        {
            log.error("Encountered session exception during logout.  This can generally safely be ignored.", e);
        }
        return false;
    }

    /**
     * 退出跳转URL
     */
    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject)
    {
        String url = getLoginUrl();
        if (StringUtils.isNotEmpty(url))
        {
            return url;
        }
        return super.getRedirectUrl(request, response, subject);
    }

    // 设置Cache的key的前缀
    public void setCacheManager(CacheManager cacheManager)
    {
        // 必须和ehcache缓存配置中的缓存name一致
        this.cache = cacheManager.getCache(ShiroConstants.SYS_USERCACHE);
    }
}
