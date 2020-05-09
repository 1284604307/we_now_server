package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.app.Comments;

/**
 * 评论表Mapper接口
 * 
 * @author ming
 * @date 2020-05-09
 */
public interface CommentsMapper 
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
     * @return 评论表集合updateComments
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
     * 删除评论表
     * 
     * @param cid 评论表ID
     * @return 结果
     */
    public int deleteCommentsById(Long cid);

    /**
     * 批量删除评论表
     * 
     * @param cids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCommentsByIds(String[] cids);
}
