package com.ruoyi.framework.shiro.web.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.AuthType;
import com.ruoyi.framework.shiro.realm.UserTicketToken;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 *  当APP未登录且访问需要登录的API时，返回未登录JSON
 */
public class CORSAuthenticationFilter extends FormAuthenticationFilter {

    public CORSAuthenticationFilter() {
        super();
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (SecurityUtils.getSubject().isAuthenticated()||SecurityUtils.getSubject().isAuthenticated())
            return true;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 不管登录页和登录请求
        if (getLoginUrl().contentEquals(httpServletRequest.getServletPath())) return true;
        boolean isApp = false;
        if (httpServletRequest.getHeader(AuthType.name)!=null){
            isApp = httpServletRequest.getHeader(AuthType.name).equals(AuthType.APP_MATH.name());
            ShiroUtils.getSession().setAttribute(AuthType.name, AuthType.APP_MATH);
            // 判断是否携带了ticket
            if(httpServletRequest.getHeader("ticket")!=null&&httpServletRequest.getHeader("username")!=null){
                ShiroUtils.getSession().setAttribute("ticket", httpServletRequest.getHeader("ticket"));
                ShiroUtils.getSession().setAttribute("username", httpServletRequest.getHeader("username"));
            }
        }
        return !isApp;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        Session session = ShiroUtils.getSession();
        if(session.getAttribute("ticket") !=null){
            String ticket = (String) session.getAttribute("ticket");
            String username = (String) session.getAttribute("username");
            UserTicketToken ticketToken = new UserTicketToken(username,ticket);
            try {
//                SecurityUtils.getSubject().login(ticketToken);
                SecurityUtils.getSubject().
                        login(ticketToken);
                return true;
            }catch (Exception e){
                session.removeAttribute("ticket");
                session.removeAttribute("username");
                PrintWriter out;
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                out = response.getWriter();
                Map<String, Object> map= new HashMap<>();
                map.put("code", 401);
                map.put("msg", "token已过期");
                out.write(JSON.toJSONString(map));
                out.close();
                return false;
            }
        }

            PrintWriter out;
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
//            res.setStatus(HttpServletResponse.SC_OK);
            Map<String, Object> map= new HashMap<>();
            map.put("code", 401);
            map.put("msg", "未登录");
            out.write(JSON.toJSONString(map));
            out.close();
            return false;


    }
}