package com.ruoyi.app.controller;

import com.ruoyi.common.core.domain.AjaxResult;
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
public class PublicController {

    @GetMapping("/welcome")
    @ResponseBody
    AjaxResult welcome(){
        return AjaxResult.success("OK");
    }

    @GetMapping("/newArticle")
    String newArticle(){
        return "app/creation/article";
    }
}
