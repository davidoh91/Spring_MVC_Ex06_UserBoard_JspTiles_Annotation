package kosta.web.mvc.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;

@Configuration
public class AppConfig {
	
	/**
	 * 예외처리
	 * */
	@Bean
	public SimpleMappingExceptionResolver getSimpleMappingException() {
		SimpleMappingExceptionResolver exceptionResolover = new SimpleMappingExceptionResolver();
		
		Properties pro = new Properties();
		pro.put("Exception", "error/errorMessage");
		
		exceptionResolover.setExceptionMappings(pro);
		return exceptionResolover;
	}
	
	/**
	 * 업로드 (메소드이름 중요 : multipartResolver)
	 * */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
    	CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
    	return commonsMultipartResolver;
    }
    
    /**
     * 다운로드를 위한 설정 - bean의 이름으로 뷰가 실행되도록... 
     * */
    @Bean
    public BeanNameViewResolver getBeanNameViewResolver() {
    	BeanNameViewResolver viewResolver = new BeanNameViewResolver();
    	viewResolver.setOrder(0);
    	return viewResolver;
    }
}





