package com.alligator.realwar.config;

import com.alligator.realwar.interceptor.RateLimitInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * web配置类
 */
@Configuration
@EnableWebMvc
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private RateLimitInterceptor rateLimitInterceptor;


    /**
     * 向web中添加拦截器的方法
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //注册拦截器
        registry.addInterceptor(rateLimitInterceptor)
                .addPathPatterns("/api/**");

    }

    /**
     * 静态资源配置（这里配置的路径不会经过拦截器)
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //配置本地文件夹目录映射
        //将handler请求路径映射到upload下的某个文件
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:D:\\restartSpringBoot\\realwar\\uploads\\");

    }

}
