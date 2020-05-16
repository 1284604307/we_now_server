package com.ruoyi.framework.shiro.service;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.common.model.RegisterInfo;
import cn.jmessage.api.common.model.UserPayload;
import com.ruoyi.framework.web.domain.server.Sys;
import com.ruoyi.system.domain.SysUser;
import org.springframework.stereotype.Service;

import javax.validation.Payload;

/**
 * @author ming
 * @ClassName: JpushService
 * @Description: (用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/5/3 20:22
 */
@Service
public class JpushService {

    JMessageClient client = new JMessageClient("3ee80f9fe9855e97c89dd52a", "5e66055cccce0900da48b080");

    public void registerUser(String username,String password) throws APIConnectionException, APIRequestException {
        RegisterInfo.Builder builder = RegisterInfo.newBuilder();
        builder.setUsername(username);
        builder.setPassword(password);
        builder.setAvatar("http://i2.hdslb.com/bfs/face/819cda3f7ecc37b295638fc73b50251f016ef7d3.gif");
        RegisterInfo info = builder.build();
        client.registerUsers(new RegisterInfo[]{info});
    }

    public void registerUser(SysUser user) throws APIConnectionException, APIRequestException {
        RegisterInfo.Builder builder = RegisterInfo.newBuilder();
        builder.setUsername(user.getLoginName());
        builder.setPassword(user.getPassword());
        builder.setNickname(user.getUserName());
        builder.setAvatar(user.getAvatar()==null?"http://i2.hdslb.com/bfs/face/819cda3f7ecc37b295638fc73b50251f016ef7d3.gif":user.getAvatar());
        RegisterInfo info = builder.build();
        client.registerUsers(new RegisterInfo[]{info});
    }

    public void updateAvatar(String username,String avatarPath) throws APIConnectionException, APIRequestException {
        UserPayload.Builder builder = UserPayload.newBuilder();
        builder.setAvatar(avatarPath);
        client.updateUserInfo(username,builder.build());
    }


    public void updateUserInfo(SysUser user) throws APIConnectionException, APIRequestException {
        UserPayload.Builder builder = UserPayload.newBuilder();
        builder.setNickname(user.getUserName());
        builder.setGender(user.getSex());
        builder.setSignature(user.getSignature());
        builder.setBirthday(user.getBirthday().toString());
        client.updateUserInfo(user.getLoginName(),builder.build());
    }
}
