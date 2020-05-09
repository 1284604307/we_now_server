package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.app.Comments;

/**
 * 评论表Service接口
 * 
 * @author ming
 * @date 2020-05-09
 */
public interface ICommentsService 
{
    /**
     * 查询评论表
     * 
     * @param cid 评论表ID
     * @return 评论表
     */
    public Comments selectCommentsById(Long cid);

    /**
     * 查询评论表列表
     * 
     * @param comments 评论表
     * @return 评论表集合
     */
    public List<Comments> selectCommentsList(Comments comments);

    /**
     * 新增评论表
     * 
     * @param comments 评论表
     * @return 结果
     */
    public int insertComments(Comments comments);

    /**
     * 修改评论表
     * 
     * @param comments 评论表
     * @return 结果
     */
    public int updateComments(Comments comments);

    /**
     * 批量删除评论表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCommentsByIds(String ids);

    /**
     * 删除评论表信息
     * 
     * @param cid 评论表ID
     * @return 结果
     */
    public int deleteCommentsById(Long cid);
}
