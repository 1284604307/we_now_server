package com.ruoyi.web.controller.app.creation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.ruoyi.app.domain.Article;
import com.ruoyi.app.domain.ArticleType;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.app.Articles;
import com.ruoyi.system.service.IArticlesService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 文章管理Controller
 * 
 * @author ming
 * @date 2020-05-09
 */
@Controller
@RequestMapping("/app/creation")
public class CreationWebController extends BaseController
{
    private String prefix = "app/creation";

    @Autowired
    private IArticlesService articlesService;



//    @RequiresPermissions("app:creation:view")
    @GetMapping()
    public String articlePage()
    {
        return prefix + "/article";
    }

    @PostMapping()
    @ResponseBody
    @RequiresUser
    public AjaxResult newArticle(String title,String author,String link,String content,String prefix,String envelopePic){
        Articles article = new Articles();
        article.setTitle(title);article.setLink(link);article.setContent(content);article.setPrefix(prefix);article.setEnvelopePic(envelopePic);

        article.setUserid(ShiroUtils.getUserId());
        article.setType(ArticleType.文章.toString());
        Date now = new Date();
        article.setPublishtime(now);
        article.setCreatetime(now);
        JSONObject extra = new JSONObject();
        extra.put("author",author);
        article.setExtra(extra.toJSONString());

        articlesService.insertArticles(article);

        return AjaxResult.success();
    }

}
