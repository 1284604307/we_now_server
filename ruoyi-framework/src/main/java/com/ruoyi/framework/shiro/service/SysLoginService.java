package com.ruoyi.framework.shiro.service;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.shiro.realm.UserTencentToken;
import com.ruoyi.framework.shiro.realm.UserTicketToken;
import com.ruoyi.system.domain.app.TencentUserInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.UserBlockedException;
import com.ruoyi.common.exception.user.UserDeleteException;
import com.ruoyi.common.exception.user.UserNotExistsException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;

import javax.security.auth.Subject;
import java.util.Random;
import java.util.UUID;

/**
 * 登录校验方法
 * 
 * @author ruoyi
 */
@Component
public class SysLoginService
{
    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysTicketService ticketService;

    @Autowired
    private JpushService jpushService;

    String tencent_getinfo = "https://graph.qq.com/user/get_user_info";

    /**
     * 通过ticket登录
     * @param username
     * @param ticket
     * @return
     */
    public SysUser loginByTicket(String username,String ticket){

        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(ticket))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }

        // 查询用户信息
        SysUser user = queryByUsernameOnMethods(username);

        if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.delete")));
            throw new UserDeleteException();
        }

        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.blocked", user.getRemark())));
            throw new UserBlockedException();
        }

        ticketService.validate(user, ticket);

        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        recordLoginInfo(user);
        return user;
    }

    /**
     * 通过 QQ OPEN_ID 登录
     * @param openId
     * @return
     */
    public SysUser loginByTencent(String openId,String accessId) throws Exception {

        SysUser user  = userService.selectUserByTencentOpenId(openId);

        if (user == null)
        {
            //todo 随机生成一个登录用户名
            String loginName = random(9);
            user = new SysUser();
            user.setLoginName(loginName);
            String s = HttpUtils.sendGet(tencent_getinfo, "access_token=" + accessId + "&oauth_consumer_key=" + 1110421384 + "&openid=" + openId);
            TencentUserInfo tencentUserInfo = JSONObject.parseObject(s,TencentUserInfo.class);
            user.setUserName(tencentUserInfo.getNickname());
            user.setAvatar(tencentUserInfo.headImgUrl());
            user.setTencentOpenid(openId);
            user.setPassword(loginName+"123456");

            if(userService.registerUser(user)) {
                try {
                    jpushService.registerUser(user);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw new Exception(" IM 通讯账户能力获取失败");
                }
            }
        }

        return user;
    }

    private SysUser queryByUsernameOnMethods(String username){
        SysUser user = userService.selectUserByLoginName(username);

        if (user == null && maybeMobilePhoneNumber(username))
        {
            user = userService.selectUserByPhoneNumber(username);
        }

        if (user == null && maybeEmail(username))
        {
            user = userService.selectUserByEmail(username);
        }

        if (user == null)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists")));
            throw new UserNotExistsException();
        }

        return user;
    }

    /**
     * 登录
     */
    public SysUser login(String username, String password)
    {
        // 验证码校验
        if (!StringUtils.isEmpty(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        SysUser user = queryByUsernameOnMethods(username);
        
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.delete")));
            throw new UserDeleteException();
        }
        
        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.blocked", user.getRemark())));
            throw new UserBlockedException();
        }

        passwordService.validate(user, password);

        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        recordLoginInfo(user);
        return user;
    }

    private boolean maybeEmail(String username)
    {
        if (!username.matches(UserConstants.EMAIL_PATTERN))
        {
            return false;
        }
        return true;
    }

    private boolean maybeMobilePhoneNumber(String username)
    {
        if (!username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN))
        {
            return false;
        }
        return true;
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SysUser user)
    {
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserInfo(user);
    }

    /**
     * 随机码生成
     * @author Mo
     *
     * @param length 随机码长度
     * @return
     */
    public static String random(int length){
        /*
         * 这里直接数字代替，没用uuid.length()
         * */
        String uuid = UUID.randomUUID().toString().replace("-", "");
        int len = uuid.length();
        /*定义随机码字符串变量，初始化为""*/
        StringBuilder random = new StringBuilder();
        /*
         * 循环截取UUID
         * len/length 每次循环截取的字符串长度
         * len%length 如果出现32长度除不尽的情况，取余数
         * */
        int subLen = len/length;
        int remainder = len%length;
        /*定义substring的两个参数*/
        int start = 0,end = 0;
        for(int i=0;i<length;i++){
            /*
             * 计算start和end的值
             * 这里涉及两种方法，一种是除不尽的时候，将截取长度分散到头部，一种是分散到尾部
             *
             * uuid的前部分是时间戳构成的，因此前部分截取越少，重复率越底
             * 固本方法采用了将多余的部分分散到尾部
             * */
            /*分散到尾部，如length为7的时候4,4,4,4,5,5,5*/
//			end = start + (length-i <= remainder ? 1 : 0)+subLen;
            /*分散到头部，如length为7 的时候5,5,5,5,4,4,4*/
            end = start + (i < remainder ? 1 : 0)+subLen;
            /*截取到的字符串*/
            String code = uuid.substring(start,end);
            /*对所截取的长度进行16位求和*/
            int count = 0;
            for(char c : code.toCharArray()){
                count += Integer.valueOf(String.valueOf(c),16);
            }
            /*将求和结果转化成36位，并增加到随机码中，36位包含了0-9a-z*/
            random.append(Integer.toString(count % 36, 36));
            start = end;
        }
        /*返回随机码*/
        return random.toString();
    }

}
