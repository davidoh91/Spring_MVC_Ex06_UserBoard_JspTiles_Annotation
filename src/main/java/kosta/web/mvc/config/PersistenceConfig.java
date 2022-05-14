package kosta.web.mvc.config;

import java.io.IOException;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import kosta.web.mvc.user.repository.UserMapper;

@Configuration
@EnableTransactionManagement
@PropertySource("/WEB-INF/spring/appServlet/dbInfo.properties")
public class PersistenceConfig implements TransactionManagementConfigurer{ //<tx:annotation-driven transaction-manager="transactionManager"/>��� 

	@Autowired
	private Environment env;//@PropertySource����� ���Ͽ� �ִ� ��� key, value�� ������ ����ȴ�.
	
	@Value("${db.userName}")
	private String username;
	
	@Value("${db.userPass}")
	private String password;
	
	
	/**
	 * PropertySourcesPlaceholderConfigurer �� ��� �Ҷ� static �����ؼ� �ٸ� �� �� ���� �� ���� ����ɼ� �ֵ����Ѵ�.
	 * @Configuration�� ������ ��ü���� ���� bean���� ����Ѵ�. 
	 * �� static�޼ҵ�� ������ �����̳ʰ� ����� �����Ҷ� ���� ���� ������ش�.
	 * */
	@Bean
	public static PropertySourcesPlaceholderConfigurer getPlaceholderConfigurer() {
		System.out.println("getPlaceholderConfigurer() call......................");
		PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		
		return placeholderConfigurer;
	}
	
	
	/**
	 * PathMatchingResourcePatternResolver �� ���丮�� ���ϵ��� ��θ� �����ϴ°��� �ƴ϶�
	 * �������� ���̳����ϰ� �����ϱ� ���ؼ� �ʿ��ϴ�. (Ant-style�̴�)
	 * */
	@Bean
	public SqlSessionFactoryBean getFactoryBean() throws IOException {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(getBasicDataSource());
		
		PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
		Resource []  resources = patternResolver.getResources("classpath:mapper/*Mapper.xml");
		
        factoryBean.setMapperLocations(resources);
        factoryBean.setTypeAliasesPackage("kosta.web.mvc.*.dto");
        PathMatchingResourcePatternResolver patternResolver2 = new PathMatchingResourcePatternResolver();
        factoryBean.setConfigLocation( patternResolver2.getResource("classpath:SqlMapConfig.xml") );
        
		return factoryBean;
	}


	@Bean
	public SqlSessionTemplate getSqlSesionTemplate() throws Exception{
		SqlSessionTemplate sqlSession = new SqlSessionTemplate( getFactoryBean().getObject() );
		System.out.println("getSqlSesionTemplate() .........................");
		return sqlSession;
		
	}


	/**
	 * interface����� Mapper ��� 
	 * */
	@Bean
	public MapperFactoryBean<UserMapper> getUserMapper() throws Exception{
		
		MapperFactoryBean<UserMapper> userMapper = new MapperFactoryBean<UserMapper>();
		userMapper.setMapperInterface(UserMapper.class);
		userMapper.setSqlSessionFactory(getFactoryBean().getObject());
		
		System.out.println("getUserMapper() call.........");
		return userMapper;
	}
	
	
	@Bean
	public BasicDataSource getBasicDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("driverName"));
		dataSource.setUrl(env.getProperty("url"));
		
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		dataSource.setMaxActive(10);
		
		return dataSource;
	}


	@Bean
	public DataSourceTransactionManager getTransactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource( getBasicDataSource() ); //DataSource
		return transactionManager;
	}


	@Override
	public TransactionManager annotationDrivenTransactionManager() {
		return getTransactionManager();
	}

}
