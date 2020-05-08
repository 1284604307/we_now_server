package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class },scanBasePackages = "com.ruoyi")
public class WeNowApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(WeNowApplication.class, args);
        System.out.println("\n\n\n (♥◠‿◠)ﾉﾞ  校园助手服务端启动成功   ლ(´ڡ`ლ)ﾞ  \n\n\n");
    }
}