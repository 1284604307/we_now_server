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
import com.ruoyi.system.domain.Banner;
import com.ruoyi.system.service.IBannerService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * app头部轮播图Controller
 * 
 * @author ming
 * @date 2020-04-28
 */
@Controller
@RequestMapping("/system/banner")
public class BannerWebController extends BaseController
{
    private String prefix = "system/banner";

    @Autowired
    private IBannerService bannerService;

    @RequiresPermissions("system:banner:view")
    @GetMapping()
    public String banner()
    {
        return prefix + "/banner";
    }

    /**
     * 查询app头部轮播图列表
     */
    @RequiresPermissions("system:banner:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Banner banner)
    {
        startPage();
        List<Banner> list = bannerService.selectBannerList(banner);
        return getDataTable(list);
    }

    /**
     * 导出app头部轮播图列表
     */
    @RequiresPermissions("system:banner:export")
    @Log(title = "app头部轮播图", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Banner banner)
    {
        List<Banner> list = bannerService.selectBannerList(banner);
        ExcelUtil<Banner> util = new ExcelUtil<Banner>(Banner.class);
        return util.exportExcel(list, "banner");
    }

    /**
     * 新增app头部轮播图
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存app头部轮播图
     */
    @RequiresPermissions("system:banner:add")
    @Log(title = "app头部轮播图", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Banner banner)
    {
        return toAjax(bannerService.insertBanner(banner));
    }

    /**
     * 修改app头部轮播图
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Banner banner = bannerService.selectBannerById(id);
        mmap.put("banner", banner);
        return prefix + "/edit";
    }

    /**
     * 修改保存app头部轮播图
     */
    @RequiresPermissions("system:banner:edit")
    @Log(title = "app头部轮播图", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Banner banner)
    {
        return toAjax(bannerService.updateBanner(banner));
    }

    /**
     * 删除app头部轮播图
     */
    @RequiresPermissions("system:banner:remove")
    @Log(title = "app头部轮播图", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bannerService.deleteBannerByIds(ids));
    }
}
