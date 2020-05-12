package com.ruoyi.web.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.app.service.NormalUserService;
import com.ruoyi.common.core.domain.AuthType;
import com.ruoyi.framework.shiro.realm.UserTencentToken;
import com.ruoyi.framework.shiro.realm.UserTicketToken;
import com.ruoyi.framework.shiro.service.SysTicketService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.app.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@Controller
public class SysLoginController extends BaseController
{

    @Autowired
    NormalUserService normalUserService;
    @Autowired
    SysTicketService ticketService;


    @PostMapping("/login/qq")
    @ResponseBody
    public AjaxResult login(String openid,String access_token)
    {
        UserTencentToken token = new UserTencentToken(openid,access_token);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            SysUser user = ShiroUtils.getSysUser();
            UserInfo userInfo = normalUserService.getUserInfoById(ShiroUtils.getUserId());
            subject.getSession().setAttribute("user",userInfo);

            if(getRequest().getHeader(AuthType.name)!=null&&getRequest().getHeader(AuthType.name).equals(AuthType.APP_MATH.name())){
                // 如果是APP端登录，则设置ticket并返回
                String tikcet = ticketService.newTicket(user.getLoginName());
                return AjaxResult.success("登录成功",tikcet);
            }
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        boolean authenticated = SecurityUtils.getSubject().isAuthenticated();
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request)){
            if (authenticated)
                return ServletUtils.renderString(response, "{\"code\":\"0\",\"msg\":\"已登录\"}");
            else
                return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }else{
            if (authenticated)
                return "index";
            else
                return "login";
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            UserInfo userInfo = normalUserService.getUserInfoById(ShiroUtils.getUserId());
            subject.getSession().setAttribute("user",userInfo);
            if(getSession().getAttribute(AuthType.name).equals(AuthType.APP_MATH)){
                // 如果是APP端登录，则设置ticket并返回
                String tikcet = ticketService.newTicket(username);
                return AjaxResult.success("登录成功",tikcet);
            }
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }
}
