package com.xll.sbshiro.config;

import com.xll.sbshiro.interceptor.WebInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  springboot1.x
 *     继承 WebMvcConfigurerAdapter 类
 *  springboot2.x
 *     1、继承 WebMvcConfigurationSupport (会让Spring-boot对mvc的自动配置失效)
 * (或)2、实现 WebMvcConfigurer (首选)
 *
 *  说明
 *  其实以前都是继承 WebMvcConfigurerAdapter 类   不过springBoot2.0以上 WebMvcConfigurerAdapter 方法过时，有两种替代方案：
 *     1、继承 WebMvcConfigurationSupport (会让Spring-boot对mvc的自动配置失效)
 *     2、实现 WebMvcConfigurer (首选)
 * 但是继承WebMvcConfigurationSupport会让Spring-boot对mvc的自动配置失效。根据项目情况选择。现在大多数项目是前后端分离，并没有对静态资源有自动配置的需求所以继承WebMvcConfigurationSupport也未尝不可。
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
    /*  WebMvcConfigurer 接口其他方法  还可以研究研究   */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可以添加多个拦截器  List集合
        registry.addInterceptor(new WebInterceptor());
    }

}
