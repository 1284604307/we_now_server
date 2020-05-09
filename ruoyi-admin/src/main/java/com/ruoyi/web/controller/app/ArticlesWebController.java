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
import com.ruoyi.system.domain.app.Articles;
import com.ruoyi.system.service.IArticlesService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章管理Controller
 * 
 * @author ming
 * @date 2020-05-09
 */
@Controller
@RequestMapping("/app/articles")
public class ArticlesWebController extends BaseController
{
    private String prefix = "app/articles";

    @Autowired
    private IArticlesService articlesService;

    @RequiresPermissions("app:articles:view")
    @GetMapping()
    public String articles()
    {
        return prefix + "/articles";
    }

    /**
     * 查询文章管理列表
     */
    @RequiresPermissions("app:articles:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Articles articles)
    {
        startPage();
        List<Articles> list = articlesService.selectArticlesList(articles);
        return getDataTable(list);
    }

    /**
     * 导出文章管理列表
     */
    @RequiresPermissions("app:articles:export")
    @Log(title = "文章管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Articles articles)
    {
        List<Articles> list = articlesService.selectArticlesList(articles);
        ExcelUtil<Articles> util = new ExcelUtil<Articles>(Articles.class);
        return util.exportExcel(list, "articles");
    }

    /**
     * 新增文章管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存文章管理
     */
    @RequiresPermissions("app:articles:add")
    @Log(title = "文章管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Articles articles)
    {
        return toAjax(articlesService.insertArticles(articles));
    }

    /**
     * 修改文章管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Articles articles = articlesService.selectArticlesById(id);
        mmap.put("articles", articles);
        return prefix + "/edit";
    }

    /**
     * 修改保存文章管理
     */
    @RequiresPermissions("app:articles:edit")
    @Log(title = "文章管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Articles articles)
    {
        return toAjax(articlesService.updateArticles(articles));
    }

    /**
     * 删除文章管理
     */
    @RequiresPermissions("app:articles:remove")
    @Log(title = "文章管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(articlesService.deleteArticlesByIds(ids));
    }
}
