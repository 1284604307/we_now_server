package com.ruoyi.app.controller.users;

import com.ruoyi.app.domain.Comment;
import com.ruoyi.app.service.CommentService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.util.ShiroUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ming
 * @ClassName: CommentController
 * @Description: 对 Article（文章/动态）评论 (用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/30 8:48
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @ApiOperation("新增一个评论")
    @PostMapping("/{circleId}/{pid}/{toId}")
    @RequiresUser
    public AjaxResult comments(String content, @PathVariable("toId") long toId,
                               @PathVariable("circleId") long circleId,
                               @PathVariable("pid") long pid){
        Comment comment = new Comment();
        comment.setArticleId(circleId);
        comment.setContent(content);
        comment.setPid(pid);
        comment.setToId(toId);
        comment.setFromId(ShiroUtils.getUserId());
        commentService.createNewComment(comment);

        return AjaxResult.success();
    }


    @ApiOperation("删除一个评论，接受一个int类型地址参数 cid（评论索引）")
    @DeleteMapping("/comment/{cid}")
    @RequiresUser
    public AjaxResult comments(@PathVariable("cid") long cid){
        Long userId = ShiroUtils.getUserId();
        int i = commentService.delComment(cid, userId);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

}
