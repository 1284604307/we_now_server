package com.ruoyi.app.controller;

import com.ruoyi.framework.shiro.service.JpushService;
import com.ruoyi.app.service.NormalUserService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.file.InvalidExtensionException;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.ruoyi.common.core.domain.AjaxResult.error;
import static com.ruoyi.common.core.domain.AjaxResult.success;

/**
 * @author ming
 * @ClassName: UserController
 * @Description: 一般用户可访问的(用一句话描述该文件做什么)
 * @email 17564500258@163.com
 * @Date 2020/4/16 17:33
 */
@Api(value = "普通用户管理",tags = {"普通用户管理"})
@RestController
@RequestMapping("/user")
public class NormalUserController {

    @Autowired
    private NormalUserService normalUserService;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private SysPasswordService passwordService;
    @Autowired
    JpushService jpushService;


//    @PostMapping("/sendEmailCode")
//    AjaxResult sendEmailCode(String email){
//
//
//
//        return success();
//    }

    @PostMapping("/register")
    AjaxResult registerUser(String username,String password,String email){

        SysUser user = new SysUser();
        user.setLoginName(username);
        user.setPassword(password);
        user.setAvatar("http://i2.hdslb.com/bfs/face/819cda3f7ecc37b295638fc73b50251f016ef7d3.gif");
//        user.setEmail(email);
        user.setLv(0L);
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName())))
        {
            return error("注册用户'" + user.getLoginName() + "'失败，登录账号已存在");
        }
        user.setUserName(username);
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setCreateBy("NORMAL_REGISTER");
        if(userService.registerUser(user)){
            try {
                jpushService.registerUser(username,password);
            }catch (Exception e){
                System.out.println(e.getMessage());
                return AjaxResult.error(" IM 通讯账户能力获取失败");
            }
            return AjaxResult.success();
        }else{
            return AjaxResult.error();
        }
    }

    @GetMapping
    @ApiOperation("获取用户信息")
    AjaxResult getInfo(){
        SysUser sysUser = ShiroUtils.getSysUser();
        return success("OK",sysUser);
    }

    @PostMapping
    @ApiOperation("更新用户信息")
    @ApiImplicitParam(name = "userInfo", value = "用户信息", required = true, paramType = "path")
    AjaxResult updateInfo(){
        return error("功能开发中");
    }

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    @RequiresUser
    AjaxResult uploadMedia(
            @ApiParam(name = "file", value = "上传的文件", required = true)MultipartFile file
    ){

        if (file==null) return error("上传文件不能为空");
        Long userId = ShiroUtils.getUserId();
        try {
            // 上传文件路径
            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(Global.getUserUploadPath(userId), file, MimeTypeUtils.CIRCLE_MEDIA);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        } catch (IOException | InvalidExtensionException e) {
            e.printStackTrace();
            return error(e.getMessage());
        }
    }


    @PostMapping("/upload/files")
    @ApiOperation("上传文件")
    @RequiresUser
    AjaxResult uploadMedia(
            @ApiParam(name = "files", value = "上传的文件", required = true)MultipartFile[] files,
            boolean safe
    ){
        if (files==null) return error("至少上传一个文件");
        System.out.println(files.length);
        System.out.println(Arrays.toString(files));
        Long userId = ShiroUtils.getUserId();
        try {
            List<String> urls = new ArrayList<>();
            for (MultipartFile file : files) {
                // 上传文件路径
                String filePath = Global.getUploadPath();
                // 上传并返回新文件名称 ex 只允许上传图片
                String fileName = FileUploadUtils.upload(Global.getUserUploadPath(userId,safe), file, MimeTypeUtils.IMAGE_EXTENSION);
                String url = serverConfig.getUrl() + fileName;
                urls.add(url);
            }
            return success(urls);
        } catch (IOException | InvalidExtensionException e) {
            e.printStackTrace();
            return error(e.getMessage());
        }
    }


    /**
     * 保存头像
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAvatar")
    @ResponseBody
    public AjaxResult updateAvatar(@RequestParam("avatarfile") MultipartFile file, HttpServletRequest request)
    {
        SysUser currentUser = ShiroUtils.getSysUser();
        try
        {
            if (!file.isEmpty())
            {
                String avatar = ShiroUtils.getServerH(request)+FileUploadUtils.upload(Global.getAvatarPath(), file);
                currentUser.setAvatar(avatar);
                if (userService.updateUserInfo(currentUser) > 0)
                {
                    ShiroUtils.setSysUser(userService.selectUserById(currentUser.getUserId()));

                    jpushService.updateAvatar(currentUser.getLoginName(),avatar);

                    return success();
                }
            }
            return error();
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }
}
