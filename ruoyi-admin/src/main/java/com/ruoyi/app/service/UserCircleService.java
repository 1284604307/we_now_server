package com.ruoyi.app.service;

import java.util.List;

import com.ruoyi.app.domain.UserCircle;
import com.ruoyi.app.mapper.UserCircleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-04-22
 */
@Service
public class UserCircleService
{
    @Autowired
    private UserCircleMapper userCircleMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public UserCircle selectUserCircleById(Long id)
    {
        return userCircleMapper.selectUserCircleById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param userCircle 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    public List<UserCircle> selectUserCircleList(UserCircle userCircle)
    {
        return userCircleMapper.selectUserCircleList(userCircle);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param userCircle 【请填写功能名称】
     * @return 结果
     */
    public int insertUserCircle(UserCircle userCircle)
    {
        return userCircleMapper.insertUserCircle(userCircle);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param userCircle 【请填写功能名称】
     * @return 结果
     */
    public int updateUserCircle(UserCircle userCircle)
    {
        return userCircleMapper.updateUserCircle(userCircle);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserCircleByIds(String ids)
    {
        return userCircleMapper.deleteUserCircleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteUserCircleById(Long id)
    {
        return userCircleMapper.deleteUserCircleById(id);
    }
}
