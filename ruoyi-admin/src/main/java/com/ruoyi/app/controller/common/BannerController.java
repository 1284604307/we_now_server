package com.ruoyi.app.controller.common;

import com.ruoyi.app.mapper.CircleDao;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ming
 * @ClassName: BannerController
 * @Description: App 首页banner(用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/26 14:02
 */
@RestController
@RequestMapping("/public/banner")
public class BannerController {

    @Autowired
    CircleDao circleDao;

    @GetMapping
    public AjaxResult banners(){
        List<Banner> banners = circleDao.getNewBanners();
        return AjaxResult.success(banners);
    }

    @PostMapping AjaxResult newBanner(){
        return AjaxResult.success();
    }

    @PutMapping("/public/banner/{id}/follow")
    AjaxResult follow(){

        return AjaxResult.success();
    }

    @PutMapping("/public/banner/{id}/share")
    AjaxResult share(){
        return AjaxResult.success();
    }

}
