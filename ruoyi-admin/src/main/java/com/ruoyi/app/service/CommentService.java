package com.ruoyi.app.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ruoyi.app.domain.Comment;
import com.ruoyi.app.mapper.CircleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ming
 * @ClassName: CommentService
 * @Description: (用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/30 11:28
 */
@Service
public class CommentService {

    @Autowired
    CircleDao circleDao ;
    @Autowired NormalUserService userService;

    public Page<Comment> getComments(int id,int page ){
        PageHelper.startPage(page,10);
        List<Comment> comments = circleDao.getComments(id);
        comments.forEach((comment)->{
            comment.setUser(userService.getUserInfoById(comment.getFromId()));
            comment.setChildren(getCommentChildren(comment.getCid(),0).getResult());
        });
        return (Page<Comment>) comments;
    }

    public Page<Comment> getCommentChildren(long cid,int page ){
        PageHelper.startPage(page,10);
        List<Comment> children = circleDao.getCommentChildren(cid);
        children.forEach((comment -> {
            comment.setUser(userService.getUserInfoById(comment.getFromId()));
        }));
        return (Page<Comment>) children;
    }


    public void createNewComment(Comment comment) {
        circleDao.createNewComment(comment);
        circleDao.addCommentCount(comment.getArticleId());
    }

    public int delComment(long cid , long fromId){
        long articleId = circleDao.getArticleIdByComment(cid);
        if(circleDao.delComment(cid,fromId)>0){
            circleDao.subCommentCount(articleId);
            return 1;
        }else
            return 0;
    }
}
