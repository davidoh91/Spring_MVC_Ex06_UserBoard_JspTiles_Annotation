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
	 * ����ó��
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
	 * ���ε� (�޼ҵ��̸� �߿� : multipartResolver)
	 * */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
    	CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
    	return commonsMultipartResolver;
    }
    
    /**
     * �ٿ�ε带 ���� ���� - bean�� �̸����� �䰡 ����ǵ���... 
     * */
    @Bean
    public BeanNameViewResolver getBeanNameViewResolver() {
    	BeanNameViewResolver viewResolver = new BeanNameViewResolver();
    	viewResolver.setOrder(0);
    	return viewResolver;
    }
}





