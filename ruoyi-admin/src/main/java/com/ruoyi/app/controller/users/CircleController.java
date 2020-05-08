package com.ruoyi.app.controller.users;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruoyi.app.Base;
import com.ruoyi.app.domain.Article;
import com.ruoyi.app.domain.Comment;
import com.ruoyi.app.service.ArticleActionService;
import com.ruoyi.app.service.CircleService;
import com.ruoyi.app.service.CommentService;
import com.ruoyi.app.service.NormalUserService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.PageAjaxResult;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author ming
 * @ClassName: CircleController
 * @Description: 朋友圈(用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/22 11:38
 */
@Api(value = "App用户动态",tags = {"App用户动态"})
@RestController
@RequestMapping("/public/circle")
public class CircleController {

    int pageSize = 10;

    @Autowired
    CircleService circleService;
    @Autowired
    NormalUserService userService;
    @Autowired
    ArticleActionService articleActionService;
    @Autowired
    CommentService commentService;

    @ApiOperation("新增一个动态，接受 参数 内容和图片链接数组 ")
    @PostMapping(produces = "application/json;charset=UTF-8")
    @RequiresUser
    public AjaxResult newCircle(@RequestBody JSONObject jsonParam){
        String content = jsonParam.getString("content");
        List<String> urls = jsonParam.getObject("urls",List.class);
        Article circle = new Article();
        SysUser user = ShiroUtils.getSysUser();
        circle.setContent(content);
        circle.setUrl(urls);
        circle.setUserId(user.getUserId());
        circle.setCreateTime(new Date());
        circle.setType("动态");
        circleService.createNewCircle(circle);
        return AjaxResult.success();
    }

    @ApiOperation("删除一个动态，接受一个地址参数 circleId")
    @DeleteMapping("/del/{circleId}")
    @RequiresUser
    public AjaxResult delCircle(@PathVariable("circleId") long circleId){
        long userId = ShiroUtils.getUserId();
        if(circleService.delCircle(circleId,userId)>0){
            return AjaxResult.success();
        }else {
            return AjaxResult.error("动态不存在");
        }
    }

    @ApiOperation("获取app首页热门动态")
    @GetMapping({"/popular","/popular/{page}"})
    public AjaxResult popularCircles(@PathVariable(required = false) Integer page){
        page=page==null?0:page;
        Page<Article> circles = circleService.getCirclesOrderByLikes(page);
        boolean login = SecurityUtils.getSubject().isAuthenticated()||SecurityUtils.getSubject().isRemembered();
        if(login){
            circles.forEach((circleEntity -> {
                circleEntity.setCollect(articleActionService.isCollect(circleEntity.getId()));
                circleEntity.setLike(articleActionService.isLike(circleEntity.getId()));
            }));
        }
        circles.forEach((circleEntity -> {
            circleEntity.setUser(userService.getUserInfoById(circleEntity.getUserId()));
        }));
        return PageAjaxResult.success(circles);
    }




    @ApiOperation("获取推荐动态")
    @GetMapping({"/hots","/hots/{page}"})
    public AjaxResult hotsCircles(@PathVariable(required = false) Integer page){
        page=page==null?0:page;
        Page<Article> circles = circleService.getCircles(page);
        boolean login = SecurityUtils.getSubject().isAuthenticated()||SecurityUtils.getSubject().isRemembered();

        if(login){
            circles.forEach((circleEntity -> {
                circleEntity.setCollect(articleActionService.isCollect(circleEntity.getId()));
                circleEntity.setLike(articleActionService.isLike(circleEntity.getId()));
            }));
        }
        circles.forEach((circleEntity -> {
            circleEntity.setUser(userService.getUserInfoById(circleEntity.getUserId()));
        }));
        return PageAjaxResult.success(circles);
    }


    @ApiOperation("获取校园内动态")
    @GetMapping({"/school","/school/{page}"})
    @RequiresUser
    public AjaxResult schoolCircles(@PathVariable(required = false) Integer page){
        page=page==null?0:page;

        Long schoolId = ShiroUtils.getSysUser().getSchoolId();
        if(schoolId==0) return AjaxResult.error("暂无学校相关信息");

        List<Article> circles = circleService.getCirclesBySchool(page,schoolId);
        circles.forEach((circleEntity -> {
            circleEntity.setCollect(articleActionService.isCollect(circleEntity.getId()));
            circleEntity.setLike(articleActionService.isLike(circleEntity.getId()));
            circleEntity.setUser(userService.getUserInfoById(circleEntity.getUserId()));
        }));

        return PageAjaxResult.success(circles);
    }

    @ApiOperation("通过 circleId 获取指定动态详情信息")
    @GetMapping("/info/{circleId}")
    public AjaxResult circle(@PathVariable("circleId") int circleId){
        Article circle = circleService.getCircle(circleId);
        Page<Comment> circleCommentsPage = commentService.getComments(circleId,0);
        circle.setComments(circleCommentsPage.getResult());
        return AjaxResult.success(circle);
    }

    @ApiOperation("通过 circleId 和 page 获取 指定动态第page页的评论列表")
    @GetMapping("/comments/{cId}/{page}")
    public AjaxResult comments(@PathVariable("cId") int cId , @PathVariable("page") int page){
        List<Comment> circleComments = commentService.getComments(cId,page);
        return  PageAjaxResult.success((Page)circleComments);
    }

    @ApiOperation("通过 pid 和 page 获取 指定评论的第page页的二级评论列表")
    @GetMapping("/commentChildren/{pId}/{page}")
    public AjaxResult commentChildren(@PathVariable("pId") int pId , @PathVariable("page") int page){
        List circleComments = commentService.getCommentChildren(pId,page).getResult();
        return  PageAjaxResult.success((Page)circleComments);
    }


}




