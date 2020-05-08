package com.ruoyi.app.mapper;

import java.util.List;

import com.ruoyi.app.domain.UserCircle;
import org.apache.ibatis.annotations.Mapper;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-04-22
 */
public interface UserCircleMapper
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public UserCircle selectUserCircleById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param userCircle 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<UserCircle> selectUserCircleList(UserCircle userCircle);

    /**
     * 新增【请填写功能名称】
     * 
     * @param userCircle 【请填写功能名称】
     * @return 结果
     */
    public int insertUserCircle(UserCircle userCircle);

    /**
     * 修改【请填写功能名称】
     * 
     * @param userCircle 【请填写功能名称】
     * @return 结果
     */
    public int updateUserCircle(UserCircle userCircle);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteUserCircleById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserCircleByIds(String[] ids);
}
