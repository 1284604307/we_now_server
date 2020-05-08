package com.ruoyi.app.controller.users;

import com.ruoyi.app.domain.Article;
import com.ruoyi.app.service.ArticleActionService;
import com.ruoyi.app.service.ArticleService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ming
 * @ClassName: ArticleController
 * @Description: 首页文章(用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/26 16:26
 */
@RestController
@RequestMapping("/public/article")
public class ArticleController {


    @Autowired
    ArticleActionService articleActionService;
    @Autowired
    ArticleService articleService;

    @GetMapping("/list/{pageNum}")
    public AjaxResult articles(@PathVariable("pageNum")int pageNum){
        List<Article> articles = articleService.articles(pageNum);
        return AjaxResult.success(articles);
    }

    @GetMapping("/top5")
    public AjaxResult top5Articles(){
        List<Article> topArticles = articleService.topArticles(5);
        return AjaxResult.success(topArticles);
    }

    @GetMapping("/top")
    public AjaxResult topArticles(){
        List<Article> topArticles = articleService.topArticles(10);
        return AjaxResult.success(topArticles);
    }

    @PostMapping
    AjaxResult newArticle(){

        return AjaxResult.success();
    }

    private List<Article> getTestData(){
        List<Article> articles = new ArrayList<>();
        Article article = new Article();
        article.setContent("测试");
        article.setId(99);
        article.setTitle("测试标题");
        article.setLink("");
        article.setEnvelopePic("");
        article.setPrefix("");
        article.setTags(new ArrayList<>());

        for (int i = 0; i < 10; i++) {
            articles.add(article);
        }
        return articles;
    }


}
