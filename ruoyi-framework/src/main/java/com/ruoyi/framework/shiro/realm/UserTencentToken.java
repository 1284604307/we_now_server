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
public class UserTencentToken implements HostAuthenticationToken {

    private String openId;
    private String accessToken;
    private String host;

    public UserTencentToken(String openid,String accessToken, String host) {
        this.openId = openid;
        this.accessToken = accessToken;
        this.host = host;
    }

    public UserTencentToken(String openid,String accessToken) {
        this.openId = openid;
        this.accessToken = accessToken;
    }

    public UserTencentToken(String token) {
        this.openId = token;
    }

    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public Object getPrincipal() {
        return openId;
    }

    @Override
    public Object getCredentials() {
        return openId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
