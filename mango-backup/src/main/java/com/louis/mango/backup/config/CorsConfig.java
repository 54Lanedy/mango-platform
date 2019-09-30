package com.louis.mango.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域资源共享（Cross-Origin Resources Sharing）
 * 解决跨域问题
 * Created by liyue
 * Time 2019-09-16 17:57
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**") //允许跨域访问的路径
                .allowedOrigins("*")  //允许跨域访问的源
                .allowedMethods("POST","GET","PUT","OPTION","DELETE")  //允许请求的方法
                .maxAge(168000)      //预检时间间隔
                .allowedHeaders("*")    //允许头部设置
                .allowCredentials(true);  //是否发送cookie

    }
}
