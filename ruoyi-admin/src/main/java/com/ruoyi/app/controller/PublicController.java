package com.ruoyi.app.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ming
 * @ClassName: UserController
 * @Description: 一般用户可访问的(用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/16 17:33
 */
@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/welcome")
    AjaxResult welcome(){
        return AjaxResult.success("OK");
    }
}
