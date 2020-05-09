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
import com.ruoyi.system.domain.app.Comments;
import com.ruoyi.system.service.ICommentsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 评论表Controller
 * 
 * @author ming
 * @date 2020-05-09
 */
@Controller
@RequestMapping("/app/comments")
public class CommentsWebController extends BaseController
{
    private String prefix = "app/comments";

    @Autowired
    private ICommentsService commentsService;

    @RequiresPermissions("app:comments:view")
    @GetMapping()
    public String comments()
    {
        return prefix + "/comments";
    }

    /**
     * 查询评论表列表
     */
    @RequiresPermissions("app:comments:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Comments comments)
    {
        startPage();
        List<Comments> list = commentsService.selectCommentsList(comments);
        return getDataTable(list);
    }

    /**
     * 导出评论表列表
     */
    @RequiresPermissions("app:comments:export")
    @Log(title = "评论表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Comments comments)
    {
        List<Comments> list = commentsService.selectCommentsList(comments);
        ExcelUtil<Comments> util = new ExcelUtil<Comments>(Comments.class);
        return util.exportExcel(list, "comments");
    }

    /**
     * 新增评论表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存评论表
     */
    @RequiresPermissions("app:comments:add")
    @Log(title = "评论表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Comments comments)
    {
        return toAjax(commentsService.insertComments(comments));
    }

    /**
     * 修改评论表
     */
    @GetMapping("/edit/{cid}")
    public String edit(@PathVariable("cid") Long cid, ModelMap mmap)
    {
        Comments comments = commentsService.selectCommentsById(cid);
        mmap.put("comments", comments);
        return prefix + "/edit";
    }

    /**
     * 修改保存评论表
     */
    @RequiresPermissions("app:comments:edit")
    @Log(title = "评论表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Comments comments)
    {
        return toAjax(commentsService.updateComments(comments));
    }

    /**
     * 删除评论表
     */
    @RequiresPermissions("app:comments:remove")
    @Log(title = "评论表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(commentsService.deleteCommentsByIds(ids));
    }
}
