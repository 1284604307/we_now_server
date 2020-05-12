package com.ruoyi.framework.shiro.realm;

import com.ruoyi.common.exception.user.*;
import com.ruoyi.framework.shiro.service.SysLoginService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysMenuService;
import com.ruoyi.system.service.ISysRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Realm 处理登录 权限
 * 
 * @author ruoyi
 */
public class TencentTokenRealm extends AuthorizingRealm
{
    private static final Logger log = LoggerFactory.getLogger(TencentTokenRealm.class);

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private SysLoginService loginService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0)
    {
        SysUser user = ShiroUtils.getSysUser();
        // 角色列表
        Set<String> roles = new HashSet<String>();
        // 功能列表
        Set<String> menus = new HashSet<String>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 管理员拥有所有权限
        if (user.isAdmin())
        {
            info.addRole("admin");
            info.addStringPermission("*:*:*");
        }
        else
        {
            roles = roleService.selectRoleKeys(user.getUserId());
            menus = menuService.selectPermsByUserId(user.getUserId());
            // 角色加入AuthorizationInfo认证对象
            info.setRoles(roles);
            // 权限加入AuthorizationInfo认证对象
            info.setStringPermissions(menus);
        }
        return info;
    }


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UserTencentToken;
    }


    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {

            UserTencentToken tToken = (UserTencentToken) token;
            SysUser user;
            try
            {
                user = loginService.loginByTencent(tToken.getOpenId(),tToken.getAccessToken());
            }
            catch (CaptchaException e)
            {
                throw new AuthenticationException(e.getMessage(), e);
            }
            catch (UserNotExistsException e)
            {
                throw new UnknownAccountException(e.getMessage(), e);
            }
            catch (UserTicketNotMatchException e)
            {
                throw new IncorrectCredentialsException(e.getMessage(), e);
            }
            catch (UserPasswordRetryLimitExceedException e)
            {
                throw new ExcessiveAttemptsException(e.getMessage(), e);
            }
            catch (UserBlockedException | RoleBlockedException e)
            {
                throw new LockedAccountException(e.getMessage(), e);
            } catch (Exception e)
            {
                log.info("对QQ用户[" + token.getPrincipal() + "]进行验证..验证未通过{}", e.getMessage());
                throw new AuthenticationException(e.getMessage(), e);
            }

            System.out.println(user.getTencentOpenid());

            return new SimpleAuthenticationInfo(user, user.getTencentOpenid(), getName());
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo()
    {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
