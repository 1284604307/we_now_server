package com.ruoyi.app.service;

import com.ruoyi.app.mapper.UserDao;
import com.ruoyi.system.domain.app.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ming
 * @ClassName: UserService
 * @Description: 普通用户信息查询(用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/16 17:40
 */
@Service
public class NormalUserService {

    @Autowired
    UserDao userDao;

    public UserInfo getUserInfoById(Long userId){
        return userDao.getUserInfoById(userId);
    }
}
