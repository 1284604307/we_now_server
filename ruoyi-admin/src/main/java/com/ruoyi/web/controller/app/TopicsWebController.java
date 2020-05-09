package com.ruoyi.web.controller.app;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.app.Topics;
import com.ruoyi.system.service.ITopicsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 话题系统Controller
 * 
 * @author ming
 * @date 2020-05-09
 */
@Controller
@RequestMapping("/app/topics")
public class TopicsWebController extends BaseController
{
    private String prefix = "app/topics";

    @Autowired
    private ITopicsService topicsService;

    @RequiresPermissions("app:topics:view")
    @GetMapping()
    public String topics()
    {
        return prefix + "/topics";
    }

    /**
     * 查询话题系统列表
     */
    @RequiresPermissions("app:topics:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Topics topics)
    {
        startPage();
        List<Topics> list = topicsService.selectTopicsList(topics);
        return getDataTable(list);
    }

    /**
     * 导出话题系统列表
     */
    @RequiresPermissions("app:topics:export")
    @Log(title = "话题系统", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Topics topics)
    {
        List<Topics> list = topicsService.selectTopicsList(topics);
        ExcelUtil<Topics> util = new ExcelUtil<Topics>(Topics.class);
        return util.exportExcel(list, "topics");
    }

    /**
     * 新增话题系统
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存话题系统
     */
    @RequiresPermissions("app:topics:add")
    @Log(title = "话题系统", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Topics topics)
    {
        return toAjax(topicsService.insertTopics(topics));
    }

    /**
     * 修改话题系统
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Topics topics = topicsService.selectTopicsById(id);
        mmap.put("topics", topics);
        return prefix + "/edit";
    }

    /**
     * 修改保存话题系统
     */
    @RequiresPermissions("app:topics:edit")
    @Log(title = "话题系统", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Topics topics)
    {
        return toAjax(topicsService.updateTopics(topics));
    }

    /**
     * 删除话题系统
     */
    @RequiresPermissions("app:topics:remove")
    @Log(title = "话题系统", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(topicsService.deleteTopicsByIds(ids));
    }
}
