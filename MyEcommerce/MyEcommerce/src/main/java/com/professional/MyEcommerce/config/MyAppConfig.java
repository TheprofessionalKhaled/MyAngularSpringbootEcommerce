package com.professional.MyEcommerce.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MyAppConfig implements WebMvcConfigurer {
	
	private String[] theAllowedOrigins;
	
	private String basePath;
	
	
	public void addCorsMappings(CorsRegistry cors) {
		cors.addMapping(basePath + "/**").allowedOrigins(theAllowedOrigins);
	}

}
