package com.ruoyi.app.mapper;

import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.app.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @author ming
 * @ClassName: UserService
 * @Description: 普通用户信息查询(用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/16 17:40
 */
@Mapper
@Component("userDao")
public interface UserDao {

    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
    })
    @Select("select user_id,user_name,avatar,email,likeCount,collectCount,lv from sys_user  where user_id = #{userId}")
    UserInfo getUserInfoById(Long userId);

    @Insert("insert into user() values()")
    void registerUser(SysUser user);

    @Select("select user_id,user_name,avatar,email,likeCount,collectCount,lv from sys_user  where login_name = #{userId}")
    UserInfo getUserInfoByLoginName(String loginName);
}
