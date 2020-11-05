package com.lfxwkj.sur.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wanglei
 * @create 2019-09-18 17:43
 */
@Configuration
public class ImgPathConfiguration implements WebMvcConfigurer {
//	@Value("${img.realpath}")
//	private String imgRealPath;
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
////		registry.addResourceHandler("/image/**").addResourceLocations("file:///D:/upload/");
//		registry.addResourceHandler("/image/**").addResourceLocations("file:///"+imgRealPath+"/");
//	}
}