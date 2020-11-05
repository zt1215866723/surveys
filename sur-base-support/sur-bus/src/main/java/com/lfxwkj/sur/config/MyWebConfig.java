package com.lfxwkj.sur.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  配置类
 * @author 王南翔
 * @since 2020-11-02
 */

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Value("${img.drillingPath}")
	private String drillingPath;
    @Value("${img.itemPath}")
	private String itemPath;
    //图片虚拟路径 防止本机图片被拦截
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:///"+drillingPath+"/").addResourceLocations("file:///"+itemPath+"/");
    }
}
