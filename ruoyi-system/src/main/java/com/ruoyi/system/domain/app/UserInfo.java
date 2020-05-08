package com.ruoyi.system.domain.app;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.system.domain.SysUser;
import lombok.Data;

/**
 * @author ming
 * @ClassName: UserInfo
 * @Description: 用户信息(用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/16 17:41
 */
@Data
public class UserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;


    long userId;
    String userName;
    String avatar;
    String email;
    long likeCount;
    long collectCount;
    long lv;

}
