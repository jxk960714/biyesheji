package com.jxk.sqmy.config.web;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.jxk.sqmy.intercepter.MyIntercepter;

@Configuration
//等价<mvc:annotation-driven></mvc:annotation-driven>
@PropertySource("classpath:yanzhengma.properties")
public class MvcConfiguration implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyIntercepter()).addPathPatterns("/frontend/denglu");
	}
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowCredentials(true)
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				.maxAge(3600);
	}
//处理文件上传
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("utf-8");
		multipartResolver.setMaxUploadSize(20971520);
		multipartResolver.setMaxInMemorySize(20971520);
		return multipartResolver;

	}
	@Value("${kaptcha.border}")
	private String border;
	@Value("${kaptcha.textproducer.font.color}")
	private String fcolor;
	@Value("${kaptcha.image.width}")
	private String width;
	@Value("${kaptcha.textproducer.char.string}")
	private String cString;
	@Value("${kaptcha.image.height}")
	private String height;
	@Value("${kaptcha.textproducer.font.size}")
	private String fsize;
	@Value("${kaptcha.noise.color}")
	private String ncolor;
	@Value("${kaptcha.textproducer.char.length}")
	private String clength;
	@Value("${kaptcha.textproducer.font.names}")
	private String fname;
	@Bean
	public ServletRegistrationBean<KaptchaServlet> servletRegistrationBean() throws ServletException {
		ServletRegistrationBean<KaptchaServlet> servlet = new ServletRegistrationBean<KaptchaServlet>(
				new KaptchaServlet(), "/Kaptcha");
		servlet.addInitParameter("kaptcha.border", border);
		servlet.addInitParameter("kaptcha.textproducer.font.color", fcolor);
		servlet.addInitParameter("kaptcha.image.width", width);
		servlet.addInitParameter("kaptcha.textproducer.char.string", cString);
		servlet.addInitParameter("kaptcha.image.height", height);
		servlet.addInitParameter("kaptcha.textproducer.font.size", fsize);
		servlet.addInitParameter("kaptcha.noise.color", ncolor);
		servlet.addInitParameter("kaptcha.textproducer.char.length", clength);
		servlet.addInitParameter("kaptcha.textproducer.font.names", fname);
		return servlet;

	}

}
