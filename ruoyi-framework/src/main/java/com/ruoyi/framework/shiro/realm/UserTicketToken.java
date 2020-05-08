package com.ruoyi.framework.shiro.realm;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

/**
 * @author ming
 * @ClassName: UserTicketToken
 * @Description: (用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/5/7 8:24
 */
public class UserTicketToken implements HostAuthenticationToken, RememberMeAuthenticationToken {

    private String username;
    private String ticket;
    private String host;

    public UserTicketToken(String username, String token, String host) {
        this.ticket = token;
        this.username = username;
        this.host = host;
    }

    public UserTicketToken(String username, String token) {
        this.ticket = token;
        this.username = username;
    }

    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public boolean isRememberMe() {
        return true;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return ticket;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
