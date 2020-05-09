package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.app.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CommentsMapper;
import com.ruoyi.system.service.ICommentsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 评论表Service业务层处理
 * 
 * @author ming
 * @date 2020-05-09
 */
@Service
public class CommentsServiceImpl implements ICommentsService 
{
    @Autowired
    private CommentsMapper commentsMapper;

    /**
     * 查询评论表
     * 
     * @param cid 评论表ID
     * @return 评论表
     */
    @Override
    public Comments selectCommentsById(Long cid)
    {
        return commentsMapper.selectCommentsById(cid);
    }

    /**
     * 查询评论表列表
     * 
     * @param comments 评论表
     * @return 评论表
     */
    @Override
    public List<Comments> selectCommentsList(Comments comments)
    {
        return commentsMapper.selectCommentsList(comments);
    }

    /**
     * 新增评论表
     * 
     * @param comments 评论表
     * @return 结果
     */
    @Override
    public int insertComments(Comments comments)
    {
        return commentsMapper.insertComments(comments);
    }

    /**
     * 修改评论表
     * 
     * @param comments 评论表
     * @return 结果
     */
    @Override
    public int updateComments(Comments comments)
    {
        return commentsMapper.updateComments(comments);
    }

    /**
     * 删除评论表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCommentsByIds(String ids)
    {
        return commentsMapper.deleteCommentsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除评论表信息
     * 
     * @param cid 评论表ID
     * @return 结果
     */
    @Override
    public int deleteCommentsById(Long cid)
    {
        return commentsMapper.deleteCommentsById(cid);
    }
}
