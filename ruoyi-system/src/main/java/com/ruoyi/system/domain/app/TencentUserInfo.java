package com.ruoyi.system.domain.app;

import lombok.Data;

/**
 * @author ming
 * @ClassName: TencentUserInfo
 * @Description: (用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/5/12 13:02
 */
@Data
public class TencentUserInfo {

    int isLost;
    String nickname;
    String gender; // 男/女
    int genderType; // 男/女 - 1
    String province;
    String city;
    String year;
    String constellation;
    String figureurl;
    String figureurl1;
    String figureurl2;
    String figureurlQq; // 140 * 140
    String figureurlQq1; // 大小为40×40像素的QQ头像URL。
    String figureurlQq2; // 大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100x100的头像，但40x40像素则是一定会有。
    String figureurlType;
    String isYellowVip;
    String vip;
    String yellowVipLevel;
    String level;
    String isYellowYearVip;

    boolean isMale() {
        return gender.equals('男');
    }

    boolean isFemale() {
        return gender.equals('女');
    }

    public String headImgUrl() {
        if (figureurlQq != null && !figureurlQq.isEmpty()) {
            return figureurlQq;
        }
        if (figureurlQq2 != null && !figureurlQq2.isEmpty()) {
            return figureurlQq2;
        }
        if (figureurlQq1 != null && !figureurlQq1.isEmpty()) {
            return figureurlQq1;
        }
        if (figureurl2 != null && !figureurl2.isEmpty()) {
            return figureurl2;
        }
        if (figureurl1 != null && !figureurl1.isEmpty()) {
            return figureurl1;
        }
        return figureurl;
    }
}
