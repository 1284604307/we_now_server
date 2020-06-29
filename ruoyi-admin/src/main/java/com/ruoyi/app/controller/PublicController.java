package com.ruoyi.app.controller;

import com.ruoyi.app.service.NormalUserService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.app.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ming
 * @ClassName: UserController
 * @Description: 一般用户可访问的(用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/16 17:33
 */
@RequestMapping("/public")
@Controller
public class PublicController {

    @Autowired
    NormalUserService normalUserService;

    @GetMapping("/welcome")
    @ResponseBody
    AjaxResult welcome(){
        return AjaxResult.success("OK");
    }

    @GetMapping("/newArticle")
    String newArticle(){
        return "app/creation/article";
    }

    @GetMapping("/user/{username}")
    @ResponseBody
    AjaxResult getUserInfo(@PathVariable("username")String username){
        UserInfo userInfo = normalUserService.getUserInfoByLoginName(username);
        if(userInfo==null)return AjaxResult.error("用户不存在");
        else {
            System.out.println(userInfo.toString());
            return AjaxResult.success(userInfo);
        }
    }
}
