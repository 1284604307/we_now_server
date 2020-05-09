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
import com.ruoyi.system.domain.app.Banners;
import com.ruoyi.system.service.IBannersService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * app头部bannerController
 * 
 * @author ming
 * @date 2020-05-09
 */
@Controller
@RequestMapping("/app/banners")
public class BannersWebController extends BaseController
{
    private String prefix = "app/banners";

    @Autowired
    private IBannersService bannersService;

    @RequiresPermissions("app:banners:view")
    @GetMapping()
    public String banners()
    {
        return prefix + "/banners";
    }

    /**
     * 查询app头部banner列表
     */
    @RequiresPermissions("app:banners:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Banners banners)
    {
        startPage();
        List<Banners> list = bannersService.selectBannersList(banners);
        return getDataTable(list);
    }

    /**
     * 导出app头部banner列表
     */
    @RequiresPermissions("app:banners:export")
    @Log(title = "app头部banner", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Banners banners)
    {
        List<Banners> list = bannersService.selectBannersList(banners);
        ExcelUtil<Banners> util = new ExcelUtil<Banners>(Banners.class);
        return util.exportExcel(list, "banners");
    }

    /**
     * 新增app头部banner
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存app头部banner
     */
    @RequiresPermissions("app:banners:add")
    @Log(title = "app头部banner", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Banners banners)
    {
        return toAjax(bannersService.insertBanners(banners));
    }

    /**
     * 修改app头部banner
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Banners banners = bannersService.selectBannersById(id);
        mmap.put("banners", banners);
        return prefix + "/edit";
    }

    /**
     * 修改保存app头部banner
     */
    @RequiresPermissions("app:banners:edit")
    @Log(title = "app头部banner", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Banners banners)
    {
        return toAjax(bannersService.updateBanners(banners));
    }

    /**
     * 删除app头部banner
     */
    @RequiresPermissions("app:banners:remove")
    @Log(title = "app头部banner", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bannersService.deleteBannersByIds(ids));
    }
}
