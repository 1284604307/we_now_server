package com.ruoyi.app.controller.users;

import com.ruoyi.app.service.ArticleActionService;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

/**
 * @author ming
 * @ClassName: ArticleActionController
 * @Description: 喜欢和收藏 控制器 (用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/30 16:38
 */
@ApiOperation("喜欢与收藏Article")
@RequestMapping("/article")
@RestController
public class ArticleActionController {

    @Autowired
    ArticleActionService articleActionService;

    @ExceptionHandler(value = {DuplicateKeyException.class})
    AjaxResult onDoublePrimaryKeyError(){
        return AjaxResult.error("重复动作！");
    }

    @ExceptionHandler(value = {NullPointerException.class})
    AjaxResult onNullPointerExceptionKeyError(){
        return AjaxResult.error("动态或文章不存在");
    }

    @RequiresUser
    @ApiOperation("点赞Article")
    @PutMapping("/like/{articleId}")
    AjaxResult like(@PathVariable("articleId") long articleId){

        articleActionService.like(articleId);
//        AjaxResult success = AjaxResult.success();
        return AjaxResult.success(articleActionService.getLikeCount(articleId));
    }

    @RequiresUser
    @ApiOperation("取消点赞Article")
    @PutMapping("/unlike/{articleId}")
    AjaxResult unlike(@PathVariable("articleId") long articleId){

        articleActionService.unlike(articleId);
        return AjaxResult.success(articleActionService.getLikeCount(articleId));
    }

    @RequiresUser
    @ApiOperation("收藏Article")
    @PutMapping("/collect/{articleId}")
    AjaxResult collect(@PathVariable("articleId") long articleId){

        articleActionService.collect(articleId);
        return AjaxResult.success();
    }

    @RequiresUser
    @ApiOperation("取消收藏Article")
    @PutMapping("/unCollect/{articleId}")
    AjaxResult unCollect(@PathVariable("articleId") long articleId){

        articleActionService.unCollect(articleId);
        return AjaxResult.success();
    }

}
