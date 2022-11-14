package com.example.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages = { "com.example.controller", "com.example.exception" })// 컨트롤러를 찾는다.
public class ServletConfig implements WebMvcConfigurer {

	// view 파일에 대한 경로 설정
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/view/");
		bean.setSuffix(".jsp");
		registry.viewResolver(bean);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getResolver() throws IOException {
		
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		
		// 10MB
		resolver.setMaxUploadSize(1024 * 1024 * 10);
		
		// 2MB
		resolver.setMaxUploadSizePerFile(1024 * 1024 * 2);
	
		// 1MB
		resolver.setMaxInMemorySize(1024 * 1024 * 1);
		
		// temp upload
		resolver.setUploadTempDir(new FileSystemResource("E:\\upload\\tmp"));
		
		resolver.setDefaultEncoding("UTF-8");
	
		return resolver;
	}
}
