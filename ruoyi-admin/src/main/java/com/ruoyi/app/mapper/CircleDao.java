package com.ruoyi.app.mapper;

import com.ruoyi.app.domain.Article;
import com.ruoyi.app.domain.Comment;
import com.ruoyi.app.domain.Topic;
import com.ruoyi.system.domain.Banner;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ming
 * @ClassName: CircleDao
 * @Description: (用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/22 11:44
 */
@Mapper
@Component("circleDao")
public interface CircleDao {

    static String articleSelect =  " id,title,prefix,createTime,url,type,link,envelope_pic,visible,fresh,likeCount,commentCount,top,userId,school_id,original,extra ";

    @Insert("insert into articles(content,createTime,url,type,userId) values(" +
            "#{c.content},Now(),#{c.url, jdbcType=VARCHAR, typeHandler= com.ruoyi.app.mapper.ArrayTypeHandler}," +
            "#{c.type},#{c.userId})")
    void insertNew(@Param("c") Article circle);

    @Select("select * from articles where type = '动态'")
    @Results(id="articleMap", value={
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="prefix", property="prefix", jdbcType=JdbcType.VARCHAR),
            @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="createTime", property="createTime", jdbcType=JdbcType.DATETIMEOFFSET),
            @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="link", property="link", jdbcType=JdbcType.VARCHAR),
            @Result(column="envelope_pic", property="envelopePic", jdbcType=JdbcType.VARCHAR),
            @Result(column="visible", property="visible", jdbcType=JdbcType.INTEGER),
            @Result(column="likeCount", property="likeCount", jdbcType=JdbcType.INTEGER),
            @Result(column="commentCount", property="commentCount", jdbcType=JdbcType.INTEGER),
            @Result(column="fresh", property="fresh", jdbcType=JdbcType.VARCHAR),
            @Result(column="top", property="top", jdbcType=JdbcType.VARCHAR),
            @Result(column="userId", property="userId", jdbcType= JdbcType.INTEGER),
            @Result(column="topic_id", property="topicId", jdbcType= JdbcType.INTEGER),
            @Result(column="school_id", property="schoolId", jdbcType= JdbcType.INTEGER),
            @Result(column="original", property="original", jdbcType= JdbcType.BOOLEAN),
    })
    List<Article> getAll();

    @ResultMap("articleMap")
    @Select("select * from articles where type = '动态' and top = 1 and original = 1")
    List<Article> getOriginalHotsAll();

    @ResultMap("articleMap")
    @Select("select * from articles where type = '动态' and school_id = #{sid}")
    List<Article> getAllBySchool(@Param("sid")long schoolId);

    @ResultMap("articleMap")
    @Select("select * from articles where type = '动态' and topic_id = #{sid} and top = false")
    List<Article> getAllByTopicId(@Param("sid")long topicId);

    @ResultMap("articleMap")
    @Select("select * from articles where id = #{id}")
    Article getCircle(@Param("id")long id);

    @Select("select "+articleSelect+" from articles where top = 1 and type = '文章' ")
    List<Article> getTopArticles();

    @ResultMap("articleMap")
    @Select("select "+articleSelect+" from articles where top = 0 and type = '文章' order by id desc ")
    List<Article> getNormalArticles();

    /**
     *  以下为 Comment Dao
     */
    @Select("select article_id from comments where cid = #{cid}")
    long getArticleIdByComment(@Param("cid")long cid);

    @Insert("insert into comments(content,createTime,article_id,pid,fromId,toId) values(" +
            "#{c.content},Now(),#{c.articleId},#{c.pid},#{c.fromId},#{c.toId})")
    void createNewComment(@Param("c") Comment comment);

    @Select("select * from comments where article_id = #{circleId} and pid = 0")
    List<Comment> getComments(@Param("circleId")long circleId);

    @Select("select * from comments where pid = #{pid}")
    List<Comment> getCommentChildren(@Param("pid")long pid);

    @Delete("delete from articles where id = #{circleId} and userId = #{userId}")
    int delCircle(@Param("circleId")long circleId, @Param("userId")long userId);

    @Delete("delete from comments where article_id = #{circleId}")
    int delComments(@Param("circleId")long circleId);

    @Delete("delete from comments where cid = #{cid} and fromId = #{fromId}")
    int delComment(@Param("cid")long cid,@Param("fromId") long fromId);


    /**
     * 以下为 Article相关事件
     */
    @Select("select id from articles where id = #{id}")
    long selectArticle(@Param("id") long id);

    @Insert("insert into article_like(article_id,user_id,create_time) values(#{aid},#{uid},Now());")
    int likeArticle(@Param("aid") long articleId,@Param("uid") long uid);

    @Delete("delete from article_like where article_id = #{aid} and user_id = #{uid};")
    int unlikeArticle(@Param("aid") long articleId,@Param("uid") long uid);

    @Insert("insert into article_collect(article_id,user_id,create_time) values(#{aid},#{uid},Now());")
    int collectArticle(@Param("aid") long articleId,@Param("uid") long uid);

    @Delete("delete from article_collect where article_id = #{aid} and user_id = #{uid};")
    int unCollectArticle(@Param("aid") long articleId,@Param("uid") long uid);

    @Select("select * from article_like where user_id = #{uid}")
    List<Integer> getAllLikes(@Param("uid") long uid);

    @Select("select * from article_collect where user_id = #{uid}")
    List<Integer> getAllCollect(@Param("uid") long uid);

    @Update("update set commentCount = commentCount+1 where id = #{aid}")
    void addCommentCount(@Param("aid") long articleId);

    @Update("update set commentCount = commentCount-1 where id = #{aid}")
    void subCommentCount(@Param("aid") long articleId);

    @Update("update articles set likeCount = likeCount+1 where id = #{aid}")
    void addLikes(@Param("aid") long articleId);

    @Update("update articles set likeCount = likeCount-1 where id = #{aid}")
    void subLikes(@Param("aid") long articleId);

    @Select("select user_id from article_like where user_id = #{uid} and article_id = #{aid}")
    Integer getLikeRow(@Param("aid") long articleId ,@Param("uid") long uid );

    @Select("select user_id from article_collect where user_id = #{uid} and article_id = #{aid}")
    Integer getCollectRow(@Param("aid") long articleId ,@Param("uid") long uid );

    @Select("select likeCount from articles where id = #{aid}")
    Integer getLikeCount(@Param("aid") long articleId);

    /**
     * banner
     */
    @Select("select * from banners where status = '投放中'")
    @Results(id="bannerMap", value={
            @Result(column="article_id", property="articleId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="drop_id", property="dropId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.DATE, id=true),
    }
    )
    List<Banner> getNewBanners();

    @Select("select * from topics ${where} ")
    List<Topic> queryTopicsByWhere(@Param("where") String where);

    @Select("select * from topics where `top` = true ")
    List<Topic> queryNiceTopics();

    @Select("select * from topics")
    List<Topic> queryTopics();

    @Select("select * from topics where id = #{id}")
    Topic queryTopic(@Param("id") long id);

    @Select("select * from articles where type = '动态' and topic_id = #{tid} and top = 1")
    List<Article> getAllByTopicTop(@Param("tid") Integer topicId);
}
