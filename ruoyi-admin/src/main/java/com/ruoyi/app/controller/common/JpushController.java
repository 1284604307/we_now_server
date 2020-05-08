package com.ruoyi.app.controller.common;

import cn.jmessage.api.JMessageClient;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ming
 * @ClassName: JpushController
 * @Description: (用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/5/3 20:09
 */
@Controller
@RequestMapping("/sms")
@RequiresUser
public class JpushController {

    JMessageClient client = new JMessageClient("3ee80f9fe9855e97c89dd52a", "5e66055cccce0900da48b080");

}
